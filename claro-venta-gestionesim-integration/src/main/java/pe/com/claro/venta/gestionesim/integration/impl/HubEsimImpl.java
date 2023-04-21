package pe.com.claro.venta.gestionesim.integration.impl;

import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import pe.com.claro.common.bean.ELKLogLegadoBean;
import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.ELKUtil;
import pe.com.claro.common.util.GestionEsimUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.common.util.RestTemplateEE;
import pe.com.claro.venta.gestionesim.canonical.request.DownloadOrderRequest;
import pe.com.claro.venta.gestionesim.canonical.response.DownloadOrderResponse;
import pe.com.claro.venta.gestionesim.integration.HubEsim;

@Stateless
public class HubEsimImpl implements HubEsim {

	private static final Logger logger = LogManager.getLogger(HubEsimImpl.class);
	
	@EJB
	private PropertiesExterno propertiesExterno;
	
	@EJB
	private RestTemplateEE restTemplates;

	private void imprimirFinMetodo(String trazabilidad, String responseJson, Long tiempoInicio) {
		logger.info(trazabilidad + "Datos de Salida:\n" + responseJson);
		logger.info(
				trazabilidad + "Tiempo invocacion: " + (System.currentTimeMillis() - tiempoInicio) + " milisegundos");
	}

	@Override
	public DownloadOrderResponse descargarPedido(String trazabilidad, HeaderRequest headerRequest,
			DownloadOrderRequest request, ELKLogLegadoBean elkLegadoBean) throws WSException {
		long tiempoInicio = System.currentTimeMillis();
		DownloadOrderResponse response = null;
		String responseJson = null;
		String nombreMetodo = "descargarPedido";
		String URL = propertiesExterno.getValueProperty(PropertiesExternos.URLHUBESIM);
		int TIMEOUT_CONEXION = Integer.parseInt(propertiesExterno.getValueProperty(PropertiesExternos.DOWNLOADORDERTIMEOUTCONEXION));
		int TIMEOUT_EJECUCION = Integer.parseInt(propertiesExterno.getValueProperty(PropertiesExternos.DOWNLOADORDERTIMEOUTEJECUCION));
		
		try {
			String requestJson = GestionEsimUtil.printPrettyJSONString(request);

			logger.info(trazabilidad + "URL para descargar pedido: " + URL);
			logger.info(trazabilidad + "Tipo: " + HttpMethod.POST);
			logger.info(trazabilidad + "Datos de Entrada:\n" + requestJson);

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.set(Constantes.CONSUMER, headerRequest.getConsumer());
			httpHeaders.set(Constantes.DISPOSITIVO, headerRequest.getDispositivo());
			httpHeaders.set(Constantes.LANGUAGE, headerRequest.getLanguage());
			httpHeaders.set(Constantes.MODULO, headerRequest.getModulo());
			httpHeaders.set(Constantes.MSG_TYPE, headerRequest.getMsgType());
			httpHeaders.set(Constantes.OPERATION, headerRequest.getOperation());
			httpHeaders.set(Constantes.PID, headerRequest.getPid());
			httpHeaders.set(Constantes.SYSTEM, headerRequest.getSystem());
			httpHeaders.set(Constantes.TIMESTAMP, headerRequest.getTimestamp());
			httpHeaders.set(Constantes.USERID, headerRequest.getUserId());
			httpHeaders.set(Constantes.WSIP, headerRequest.getWsIp());

			HttpEntity<String> httpEntity = new HttpEntity<String>(requestJson, httpHeaders);

			SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
			httpRequestFactory.setConnectTimeout(TIMEOUT_CONEXION);
			httpRequestFactory.setReadTimeout(TIMEOUT_EJECUCION);

			logger.info(trazabilidad + "Timeout conexion (ms): " + TIMEOUT_CONEXION);
			logger.info(trazabilidad + "Timeout ejecucion (ms): " + TIMEOUT_EJECUCION);

			elkLegadoBean.setNombreActividad(nombreMetodo);
			elkLegadoBean.setDetalleActividad("Crear sesion para el pago");
			elkLegadoBean.setTipoConsulta(ELKUtil.TIPO_CONSULTA_WS);
			elkLegadoBean.setDuenoConsulta(Constantes.NOMBRE_SERVICIO_HUBSIM);
			elkLegadoBean.setOrigenConsulta(URL);
			elkLegadoBean.setDetalleConsulta("descargarPedido");
			elkLegadoBean.setTipoMensaje(ELKUtil.TIPO_MENSAJE_REQUEST);
			elkLegadoBean.setTiempoRespuesta(System.currentTimeMillis() - tiempoInicio);
			elkLegadoBean.setMensaje(requestJson);

			ELKUtil.logObjetoLegado(elkLegadoBean);

			RestTemplate restTemplate = restTemplates.getRestTemplate(httpRequestFactory);
			ResponseEntity<DownloadOrderResponse> responseEntity = restTemplate.exchange(URL, HttpMethod.POST,
					httpEntity, DownloadOrderResponse.class);

			logger.info(trazabilidad + "Servicio REST ejecutado");

			responseJson = GestionEsimUtil.printPrettyJSONString(responseEntity.getBody());

			response = responseEntity.getBody();

			elkLegadoBean.setTipoMensaje(ELKUtil.TIPO_MENSAJE_RESPONSE);
			elkLegadoBean.setMensaje(responseJson);
			elkLegadoBean.setTipoId(ELKUtil.TIPO_ID_IDF);
			elkLegadoBean.setIdfIdtCodigo(response.getReasonCode());
			elkLegadoBean.setIdfIdtMensaje(response.getMessage());
			elkLegadoBean.setTiempoRespuesta(System.currentTimeMillis() - tiempoInicio);

			ELKUtil.logObjetoLegado(elkLegadoBean);
		} catch (HttpClientErrorException e) {
			logger.info(trazabilidad + "Respuesta Error HttpClientErrorException:\n" + e.getResponseBodyAsString());
			response = new DownloadOrderResponse();
			response.setReasonCode(String.valueOf(e.getStatusCode()));
			response.setMessage(e.getResponseBodyAsString());
		} catch (Exception e) {
			logger.info(trazabilidad + "Error en crear sesion por seguridad motor: " + e.getMessage(), e);

			String trazaError = ExceptionUtils.getStackTrace(e);
			String codigoError;

			if (trazaError.toUpperCase(Locale.getDefault()).contains(Constantes.WS_TIMEOUT_EXCEPTION)) {
				codigoError = Constantes.CODIGO_ERROR_TIMEOUT;
			} else {
				codigoError = Constantes.CODIGO_ERROR_DISPONIBILIDAD;
			}

			elkLegadoBean.setTipoMensaje(ELKUtil.TIPO_MENSAJE_RESPONSE);
			elkLegadoBean.setTiempoRespuesta(System.currentTimeMillis() - tiempoInicio);
			elkLegadoBean.setMensaje(GestionEsimUtil
					.removerSaltosLinea(String.join("", ExceptionUtils.getRootCauseStackTrace(e)), "\\n"));
			elkLegadoBean.setTipoId(ELKUtil.TIPO_ID_IDT);
			elkLegadoBean.setIdfIdtCodigo(codigoError);
			elkLegadoBean.setIdfIdtMensaje(GestionEsimUtil.removerSaltosLinea(
					GestionEsimUtil.removerSaltosLinea(e.getMessage(), Constantes.SEPARADOR_ESPACIO)));

			ELKUtil.logObjetoLegado(elkLegadoBean);

			response = new DownloadOrderResponse();
			response.setReasonCode(codigoError);
			response.setMessage(e.getMessage());

		} finally {
			imprimirFinMetodo(trazabilidad, responseJson, tiempoInicio);
		}

		return response;
	}

}
