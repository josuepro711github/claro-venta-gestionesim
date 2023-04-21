package pe.com.claro.venta.gestionesim.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.claro.common.bean.ELKLogLegadoBean;
import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;
import pe.com.claro.venta.gestionesim.domain.service.GestionesimService;


@Stateless
@Path(Constantes.PATH)
@Api(value = Constantes.API_VALUE_GESTIONESIM, description = Constantes.API_DESCRIPTION_GESTIONESIM)
@Produces({ MediaType.APPLICATION_JSON + Constantes.CHARSETUTF8 })
public class GestioneSimResource {

	private static final Logger logger = Logger.getLogger(GestioneSimResource.class);

	@Context
	private Configuration configuration;

	@EJB
	private GestionesimService gestionesimService;

	@EJB
	private PropertiesExterno propertiesExterno;

	@POST
	@Path("/reservarCodigo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "[reservarCodigo] - Operación para actualizar el estado", notes = "", response = ReservarCodigoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = Constantes.CODIGO200, message = Constantes.MENSAJE200),
			@ApiResponse(code = Constantes.CODIGO400, message = Constantes.MENSAJE400),
			@ApiResponse(code = Constantes.CODIGO500, message = Constantes.MENSAJE500) })
	public Response reservarCodigo(@Context HttpHeaders httpHeaders,
			@ApiParam(value = "El objeto request", required = true) ReservarCodigoRequest request)
			throws JsonProcessingException {
		long tiempoInicio = System.currentTimeMillis();
		long tiempoTotal = 0L;
		Response resJSON = Response.ok().entity(Constantes.TEXTOVACIO).build();
		Response.Status httpCode = Response.Status.CREATED;
		String result = Constantes.TEXTO_VACIO;
		ReservarCodigoResponse response = null;
		String mensajeTransaccion = Constantes.TEXTO_VACIO;
		String nombreMetodo = "reservarCodigo";
		HeaderRequest headerRequest = null;
		String idTransaccion = Constantes.TEXTOVACIO;
		String trazabilidad = null;
		ELKLogLegadoBean elkLegadoBean = null;
		try {
			headerRequest = new HeaderRequest(httpHeaders);

			if (null == headerRequest.getIdTransaccion()) {
				headerRequest.setIdTransaccion(ClaroUtil.obtenerIdTransaccion());
			}
			
			idTransaccion = headerRequest.getIdTransaccion();
			mensajeTransaccion = "[" + nombreMetodo + Constantes.IDTX + idTransaccion + "] ";

			logger.info(mensajeTransaccion + Constantes.INICIO + nombreMetodo);
			logger.info(Constantes.HEADERREQUEST + ClaroUtil.printPrettyJSONString(headerRequest));
	
			response = gestionesimService.reservarCodigo(mensajeTransaccion, request, headerRequest, trazabilidad, elkLegadoBean);

			response.setIdTransaccion(idTransaccion);
			result = new ObjectMapper().writeValueAsString(response);

			httpCode = Response.Status.OK;
			resJSON = Response.status(httpCode).entity(result).build();

		} catch (JsonProcessingException e) {
			httpCode = Response.Status.INTERNAL_SERVER_ERROR;
			try {
				response = new ReservarCodigoResponse();

				response.setCodigoRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF2CODIGO));
				response.setMensajeRespuesta(propertiesExterno.getValueProperty(PropertiesExternos.IDF2MSG));
				response.setIdTransaccion(idTransaccion);
				result = new ObjectMapper().writeValueAsString(response);
			} catch (JsonProcessingException ex) {
				logger.error(mensajeTransaccion + Constantes.ERROR + ex.getCause());
			}

			resJSON = Response.status(httpCode).entity(result).build();
			logger.error(mensajeTransaccion + Constantes.ERROR + e.getCause());
		} finally {
			logger.info(Constantes.RESPONSE + ClaroUtil.printPrettyJSONString(response));
			tiempoTotal = System.currentTimeMillis() - tiempoInicio;
			logger.info(ClaroUtil.loggerFin(mensajeTransaccion, tiempoTotal, nombreMetodo));
		}

		return resJSON;
	}
}
