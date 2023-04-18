package pe.com.claro.venta.gestionesim.domain.service;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;
import pe.com.claro.venta.gestionesim.domain.repository.MSSAPRepository;

@Stateless
public class GestionesimService {
	private static final Logger logger = Logger
			.getLogger(GestionesimService.class);

	@EJB
	private PropertiesExterno propertiesExterno;
	@EJB
	private MSSAPRepository mssapRepository;

	public ReservarCodigoResponse reservarCodigo(
			String mensajeTransaccion,
			ReservarCodigoRequest request, HeaderRequest header) {
		logger.info(mensajeTransaccion
				+ " Inicio Proceso Reservar Codigo ");
		ReservarCodigoResponse response;
		try {
			logger.info(mensajeTransaccion
					+ "*********** 1. Reservar Codigo ***********");
			response = mssapRepository.reservarCodigo(mensajeTransaccion,
					request);
			logger.info(mensajeTransaccion + Constantes.RESPONSE
					+ ClaroUtil.printPrettyJSONString(response));
			if (!Constantes.ZERO.equalsIgnoreCase(response
					.getCodigoRespuesta())) {
				logger.info(mensajeTransaccion
						+ " Problema Reservar Codigo ");
				response.setCodigoRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDF1CODIGO));
				response.setMensajeRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDF1MSG));
			}
			response.setIdTransaccion(header.getIdTransaccion());
			logger.info(mensajeTransaccion
					+ " Termino Proceso Reservar Codigo ");
		} catch (Exception e) {
			logger.error(
					mensajeTransaccion + Constantes.ERROR + e.getMessage(), e);
			response = new ReservarCodigoResponse();
			response.setCodigoRespuesta(propertiesExterno
					.getValueProperty(PropertiesExternos.IDF2CODIGO));
			response.setMensajeRespuesta(propertiesExterno
					.getValueProperty(PropertiesExternos.IDF2MSG));
			response.setIdTransaccion(header.getIdTransaccion());
		} finally {
			logger.info(mensajeTransaccion
					+ " Fin Proceso Reservar Codigo ");
		}
		return response;
	}
}
