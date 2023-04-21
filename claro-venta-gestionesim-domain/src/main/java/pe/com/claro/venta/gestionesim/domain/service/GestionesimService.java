package pe.com.claro.venta.gestionesim.domain.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import pe.com.claro.common.bean.ELKLogLegadoBean;
import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ActualizarEstadoRequest;
import pe.com.claro.venta.gestionesim.canonical.request.DownloadOrderRequest;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ActualizarEstadoResponse;
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

	public ReservarCodigoResponse reservarCodigo(String trazabilidad, ReservarCodigoRequest reservarCodigoRequest,
			HeaderRequest header, ELKLogLegadoBean elkLegadoBean) {
		logger.info(trazabilidad + " Inicio Proceso Reservar Codigo ");
		
		ActualizarEstadoRequest actualizarEstadoRequest = new ActualizarEstadoRequest();
		DownloadOrderRequest downloadOrderRequest = new DownloadOrderRequest();
		
		ReservarCodigoResponse obtenerCodigoResponse = new ReservarCodigoResponse();
		DownloadOrderResponse descargarPedidoResponse = new DownloadOrderResponse();
		ActualizarEstadoResponse actualizarEstadoResponse = new ActualizarEstadoResponse();
		try {
			logger.info(trazabilidad + "*********** 1. Inicia Proceso Obtener Codigo ***********");
			String status = propertiesExterno.getValueProperty(PropertiesExternos.STATUS);
			obtenerCodigoResponse = mssapDao.obtenerCodigo(trazabilidad, status);
			logger.info(
					trazabilidad + Constantes.RESPONSE + ClaroUtil.printPrettyJSONString(obtenerCodigoResponse));
			
			if (Constantes.CERO_CADENA.equalsIgnoreCase(obtenerCodigoResponse.getCodigoRespuesta())) {
				obtenerCodigoResponse.setIdTransaccion(header.getIdTransaccion());
				logger.info(trazabilidad + " Termino Proceso Obtener Codigo ");

				String codigoSerie = Constantes.TEXTOVACIO;
				logger.info(trazabilidad + "Obteniendo codigo serie de la actividad 1");
				codigoSerie = obtenerCodigoResponse.getCodserie();
				logger.info(trazabilidad + "COD SERIE OBTENIDO: " + codigoSerie);

				if (codigoSerie != null || !Constantes.TEXTOVACIO.equals(codigoSerie)) {
					logger.info(trazabilidad + "*********** 2. Inicia Proceso Descargar Pedido ***********");
					downloadOrderRequest.setIccid(codigoSerie);
					// forzando status
					String respuestaSuccess = Constantes.RPTA_SUCCESS;
					descargarPedidoResponse.setStatus(respuestaSuccess);
					//descargarPedidoResponse = hubEsim.descargarPedido(trazabilidad, header, downloadOrderRequest ,elkLegadoBean);

				} else if (!Constantes.RPTA_SUCCESS.equals(descargarPedidoResponse.getStatus())) {
					logger.info(trazabilidad + "*********** 3. Inicia Proceso Actualizar Estado ***********");
					actualizarEstadoRequest.setCodserie(codigoSerie);
					actualizarEstadoRequest.setStatus(obtenerCodigoResponse.getStatus());
					actualizarEstadoResponse = mssapDao.actualizarEstado(trazabilidad,
							actualizarEstadoRequest);
				}

			} else {
				logger.info(trazabilidad + " Problema Reservar Codigo ");
				obtenerCodigoResponse.setCodigoRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF1CODIGO));
				obtenerCodigoResponse.setMensajeRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF1MSG));
			}

		} catch (Exception e) {
			logger.error(trazabilidad + Constantes.ERROR + e.getMessage(), e);
			obtenerCodigoResponse = new ReservarCodigoResponse();
			obtenerCodigoResponse.setCodigoRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF2CODIGO));
			obtenerCodigoResponse.setMensajeRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF2MSG));
			obtenerCodigoResponse.setIdTransaccion(header.getIdTransaccion());
		} finally {
			logger.info(trazabilidad + " Fin Proceso Reservar Codigo ");
		}
		return obtenerCodigoResponse;
	}
}
