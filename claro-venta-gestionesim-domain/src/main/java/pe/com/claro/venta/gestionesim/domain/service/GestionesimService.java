package pe.com.claro.venta.gestionesim.domain.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.bean.ELKLogLegadoBean;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ActualizarEstadoRequest;
import pe.com.claro.venta.gestionesim.canonical.request.DownloadOrderRequest;
import pe.com.claro.venta.gestionesim.canonical.request.HeaderRequestBean;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.DownloadOrderResponse;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;
import pe.com.claro.venta.gestionesim.domain.dao.MssapDao;
import pe.com.claro.venta.gestionesim.integration.HubEsim;

@Stateless
public class GestionesimService {

	private static final Logger logger = Logger.getLogger(GestionesimService.class);

	@EJB
	private PropertiesExterno propertiesExterno;

	@EJB
	private MssapDao mssapDao;

	@EJB
	private HubEsim hubEsim;

	public ReservarCodigoResponse reservarCodigo(String mensajeTransaccion, ReservarCodigoRequest reservarCodigoRequest,
			HeaderRequestBean header, String trazabilidad, ELKLogLegadoBean elkLegadoBean,
			DownloadOrderRequest orderRequest, String message, ActualizarEstadoRequest actualizarEstadoRequest) {
		logger.info(mensajeTransaccion + " Inicio Proceso Reservar Codigo ");
		ReservarCodigoResponse obtenerCodigoResponse = new ReservarCodigoResponse();
		DownloadOrderResponse descargarPedidoResponse = new DownloadOrderResponse();
		BodyResponse actualizarEstadoResponse = new BodyResponse();
		try {
			logger.info(mensajeTransaccion + "*********** 1. Inicia Proceso Obtener Codigo ***********");
			obtenerCodigoResponse = mssapDao.obtenerCodigo(message, reservarCodigoRequest);
			logger.info(
					mensajeTransaccion + Constantes.RESPONSE + ClaroUtil.printPrettyJSONString(obtenerCodigoResponse));
			
			logger.info("header???? " + header.getIdTransaccion());
			
			if (Constantes.CERO_CADENA.equals(obtenerCodigoResponse.getCodigoRespuesta())) {
				obtenerCodigoResponse.setIdTransaccion(header.getIdTransaccion());
				logger.info(mensajeTransaccion + " Termino Proceso Obtener Codigo ");

				String codigoSerie = Constantes.TEXTOVACIO;
				logger.info(mensajeTransaccion + "Obteniendo codigo serie de la actividad 1");
				codigoSerie = obtenerCodigoResponse.getCodserie();

				if (codigoSerie != null || !Constantes.TEXTOVACIO.equals(codigoSerie)) {
					logger.info(mensajeTransaccion + "codigoSerie obtenido: " + codigoSerie);
					logger.info(mensajeTransaccion + "*********** 2. Inicia Proceso Descargar Pedido ***********");
					descargarPedidoResponse = hubEsim.descargarPedido(trazabilidad, header,
							orderRequest, elkLegadoBean);

				} else if (Constantes.RPTA_SUCCESS.equals(descargarPedidoResponse.getStatus())) {
					logger.info(mensajeTransaccion + "*********** 3. Inicia Proceso Actualizar Estado ***********");
					actualizarEstadoResponse = mssapDao.actualizarEstado(message,
							actualizarEstadoRequest);
				}

			} else {
				logger.info(mensajeTransaccion + " Problema Reservar Codigo ");
				obtenerCodigoResponse.setCodigoRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF1CODIGO));
				obtenerCodigoResponse.setMensajeRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF1MSG));
			}

		} catch (Exception e) {
			logger.error(mensajeTransaccion + Constantes.ERROR + e.getMessage(), e);
			obtenerCodigoResponse = new ReservarCodigoResponse();
			obtenerCodigoResponse.setCodigoRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF2CODIGO));
			obtenerCodigoResponse.setMensajeRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF2MSG));
			obtenerCodigoResponse.setIdTransaccion(header.getIdTransaccion());
		} finally {
			logger.info(mensajeTransaccion + " Fin Proceso Reservar Codigo ");
		}
		return obtenerCodigoResponse;
	}
}
