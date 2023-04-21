package pe.com.claro.venta.gestionesim.domain.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import pe.com.claro.common.bean.ELKLogLegadoBean;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ActualizarEstadoRequest;
import pe.com.claro.venta.gestionesim.canonical.request.DownloadOrderRequest;
import pe.com.claro.venta.gestionesim.canonical.request.HeaderRequestBean;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ActualizarEstadoResponse;
import pe.com.claro.venta.gestionesim.canonical.response.DownloadOrderResponse;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;
import pe.com.claro.venta.gestionesim.domain.repository.MSSAPRepository;
import pe.com.claro.venta.gestionesim.integration.HubEsim;

@Stateless
public class GestionesimService {
	
	private static final Logger logger = Logger.getLogger(GestionesimService.class);
	
	@EJB
	private PropertiesExterno propertiesExterno;

	@EJB
	private MSSAPRepository mssapRepository;

	@EJB
	private HubEsim hubEsim;

	public ReservarCodigoResponse reservarCodigo(String mensajeTransaccion, ReservarCodigoRequest request,
			HeaderRequestBean header, String trazabilidad, ELKLogLegadoBean elkLegadoBean, String message) {
		logger.info(mensajeTransaccion + " Inicio Proceso Reservar Codigo ");
		ActualizarEstadoRequest actualizarEstadoRequest = new ActualizarEstadoRequest();
		DownloadOrderRequest orderRequest = new DownloadOrderRequest();
		
		ReservarCodigoResponse obtenerCodigoResponse = new ReservarCodigoResponse();
		DownloadOrderResponse descargarPedidoResponse = new DownloadOrderResponse();
		ActualizarEstadoResponse actualizarEstadoResponse = new ActualizarEstadoResponse();
		try {
			logger.info("PROPERTIES LLAVE " + propertiesExterno.getValueProperty(PropertiesExternos.IDF0MSG));
			logger.info(mensajeTransaccion + "*********** 1. Inicia Proceso Obtener Codigo ***********");
			obtenerCodigoResponse = mssapRepository.obtenerCodigo(mensajeTransaccion, request);
			logger.info(
					mensajeTransaccion + Constantes.RESPONSE + ClaroUtil.printPrettyJSONString(obtenerCodigoResponse));

			if (Constantes.ZERO.equalsIgnoreCase(obtenerCodigoResponse.getCodigoRespuesta())) {
				obtenerCodigoResponse.setIdTransaccion(header.getIdTransaccion());
				logger.info(mensajeTransaccion + " Termino Proceso Obtener Codigo ");

				String codigoSerie = Constantes.TEXTOVACIO;
				logger.info(mensajeTransaccion + "Obteniendo codigo serie de la actividad 1");
				codigoSerie = obtenerCodigoResponse.getCodserie();

				if (codigoSerie != null || !Constantes.TEXTOVACIO.equals(codigoSerie)) {
					logger.info(mensajeTransaccion + "codigoSerie obtenido: " + codigoSerie);
					logger.info(mensajeTransaccion + "*********** 2. Inicia Proceso Descargar Pedido ***********");
					orderRequest.setIccid(codigoSerie);
					descargarPedidoResponse = hubEsim.descargarPedido(trazabilidad, header, orderRequest, elkLegadoBean);
					
				} else if (Constantes.RPTA_SUCCESS.equals(descargarPedidoResponse.getStatus())){
					logger.info(mensajeTransaccion + "*********** 3. Inicia Proceso Actualizar Estado ***********");
					actualizarEstadoRequest.setCodserie(codigoSerie);
					actualizarEstadoRequest.setStatus(propertiesExterno.getValueProperty(PropertiesExternos.STATUS));
					actualizarEstadoResponse = mssapRepository.actualizarEstado(message, actualizarEstadoRequest);
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
