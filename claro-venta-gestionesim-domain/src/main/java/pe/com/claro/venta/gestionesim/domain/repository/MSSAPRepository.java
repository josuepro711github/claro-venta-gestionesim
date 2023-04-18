package pe.com.claro.venta.gestionesim.domain.repository;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.domain.repository.AbstractRepository;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ActualizarEstadoRequest;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;

@Stateless
public class MSSAPRepository extends AbstractRepository<ReservarCodigoRequest> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MSSAPRepository.class);
	@EJB
	private PropertiesExterno propertiesExterno;
	
	@PersistenceContext(unitName = Constantes.EAIPERSISTENCEPACKAGEUNIT)
	public void setPersistenceUnit00(final EntityManager em) {
		this.entityManager = em;
		logger.info("Cargando el contexto de PERSISTENCE CONTEXT MSSAP");
	}
	
	public ReservarCodigoResponse reservarCodigo(String message,
			ReservarCodigoRequest request) throws SQLException {
		
		long tiempoInicio = System.currentTimeMillis();
		String nombreMetodo = "reservarCodigo";
		String mensajeTransaccion = message + "[" + nombreMetodo + "]";
		ReservarCodigoResponse response = new ReservarCodigoResponse();
		StringBuilder storeProcedure = new StringBuilder();
		logger.info(message + Constantes.MENSAJE_INICIO_SERVICIO + nombreMetodo
				+ Constantes.MENSAJE_FINAL_REPOSITORY);
		Connection connection = null;
		CallableStatement call = null;

		String nombrebd = Constantes.TEXTO_VACIO;
		String owner = Constantes.TEXTO_VACIO;
		String packagebd = Constantes.TEXTO_VACIO;
		String procedure = Constantes.TEXTO_VACIO;
		String jndi = Constantes.TEXTO_VACIO;
		String timeoutconn = Constantes.TEXTO_VACIO;
		String timeouteje = Constantes.TEXTO_VACIO;

		try {
			nombrebd = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPBD);
			owner = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPOWNER);
			packagebd = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPPACKAGEGESTIONESIM);
			procedure = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPSPOBTENERCODIGO);
			jndi = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPJNDI);
			timeoutconn = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPTIMEOUTCONEXIONOBTENERCODIGO);
			timeouteje = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPTIMEOUTEJECUCIONOBTENERCODIGO);
			
			logger.info(mensajeTransaccion + Constantes.INICIO
					+ Constantes.REPOSITORY + nombreMetodo);
			storeProcedure.append(owner);
			storeProcedure.append(Constantes.PUNTO);
			storeProcedure.append(packagebd);
			storeProcedure.append(Constantes.PUNTO);
			storeProcedure.append(procedure);

			connection = ClaroUtil
					.getJNDIConnection(message, jndi, timeoutconn);

			logger.info(message + Constantes.MENSAJE_INVOCANDO_SP
					+ storeProcedure.toString());
			logger.info(message + Constantes.MENSAJE_TIMEOUT_EJEC + timeouteje);
			logger.info(mensajeTransaccion + Constantes.PARAMETROINPUT
					+ " PI_STATUSC : [" + request.getStatus() + "]");
			
			call = connection.prepareCall(Constantes.CALL
					+ storeProcedure.toString() + " (?,?,?,?,?,?,?,?)");
			call.setQueryTimeout(Integer.parseInt(timeouteje));
			call.setString(1, request.getStatus());
			call.registerOutParameter(2, Types.VARCHAR);
			call.registerOutParameter(3, Types.VARCHAR);
			call.registerOutParameter(4, Types.VARCHAR);
			call.registerOutParameter(5, Types.CHAR);
			call.registerOutParameter(6, Types.INTEGER);
			call.registerOutParameter(7, Types.VARCHAR);
			call.registerOutParameter(8, Types.VARCHAR);
			call.execute();

			logger.info(mensajeTransaccion + Constantes.INVOCOSP
					+ storeProcedure);
			
			response.setCodigoRespuesta(call.getString(7));
			response.setMensajeRespuesta(call.getString(8));

			logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT
					+ Constantes.POCODMSG + response.getCodigoRespuesta() + "]");
			logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT
					+ Constantes.POMSJ + response.getMensajeRespuesta() + "]");
			call.close();

		} catch (Exception e) {
			logger.error(
					message + Constantes.MENSAJE_EXCEPTION + e.getMessage()
							+ "] ", e);

			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			String descripcionError = String.valueOf(errors.toString());

			if (descripcionError.toUpperCase(Locale.getDefault()).contains(
					Constantes.SQL_TIMEOUTEXCEPTION)) {
				response.setCodigoRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT1CODIGO));
				response.setMensajeRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT1MSG)
						.replace("[BD]", nombrebd)
						.replace("[SP]", storeProcedure.toString()));
			} else {
				response.setCodigoRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT2CODIGO));
				response.setMensajeRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT2MSG)
						.replace("[BD]", nombrebd)
						.replace("[SP]", storeProcedure.toString()));
			}
			
		} finally {
			if (null != call) {
				call.close();
			}
			if (null != connection) {
				connection.close();
			}
			logger.info(message + Constantes.MENSAJE_TIEMPO_PROCESO
					+ (System.currentTimeMillis() - tiempoInicio));
			logger.info(message + Constantes.MENSAJE_FINAL_SERVICIO
					+ nombreMetodo + Constantes.MENSAJE_FINAL_REPOSITORY);
		}
		return response;
	}
	
	public BodyResponse actualizarEstado(String message,
			ActualizarEstadoRequest request) throws SQLException {
		
		long tiempoInicio = System.currentTimeMillis();
		String nombreMetodo = "actualizarEstado";
		String mensajeTransaccion = message + "[" + nombreMetodo + "]";
		BodyResponse response = new BodyResponse();
		StringBuilder storeProcedure = new StringBuilder();
		logger.info(message + Constantes.MENSAJE_INICIO_SERVICIO + nombreMetodo
				+ Constantes.MENSAJE_FINAL_REPOSITORY);
		Connection connection = null;
		CallableStatement call = null;

		String nombrebd = Constantes.TEXTO_VACIO;
		String owner = Constantes.TEXTO_VACIO;
		String packagebd = Constantes.TEXTO_VACIO;
		String procedure = Constantes.TEXTO_VACIO;
		String jndi = Constantes.TEXTO_VACIO;
		String timeoutconn = Constantes.TEXTO_VACIO;
		String timeouteje = Constantes.TEXTO_VACIO;

		try {
			nombrebd = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPBD);
			owner = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPOWNER);
			packagebd = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPPACKAGEGESTIONESIM);
			procedure = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPSPACTUALIZARESTADO);
			jndi = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPJNDI);
			timeoutconn = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPTIMEOUTCONEXIONACTUALIZARESTADO);
			timeouteje = propertiesExterno
					.getValueProperty(PropertiesExternos.MSSAPTIMEOUTEJECUCIONACTUALIZARESTADO);
			
			logger.info(mensajeTransaccion + Constantes.INICIO
					+ Constantes.REPOSITORY + nombreMetodo);
			storeProcedure.append(owner);
			storeProcedure.append(Constantes.PUNTO);
			storeProcedure.append(packagebd);
			storeProcedure.append(Constantes.PUNTO);
			storeProcedure.append(procedure);

			connection = ClaroUtil
					.getJNDIConnection(message, jndi, timeoutconn);

			logger.info(message + Constantes.MENSAJE_INVOCANDO_SP
					+ storeProcedure.toString());
			logger.info(message + Constantes.MENSAJE_TIMEOUT_EJEC + timeouteje);
			logger.info(mensajeTransaccion + Constantes.PARAMETROINPUT
					+ " PI_CODSERIE : [" + request.getCodserie() + "]");
			logger.info(mensajeTransaccion + Constantes.PARAMETROINPUT
					+ " PI_STATUS : [" + request.getStatus() + "]");
			
			call = connection.prepareCall(Constantes.CALL
					+ storeProcedure.toString() + " (?,?,?,?)");
			call.setQueryTimeout(Integer.parseInt(timeouteje));
			call.setString(1, request.getCodserie());
			call.setString(2, request.getStatus());
			call.registerOutParameter(3, Types.VARCHAR);
			call.registerOutParameter(4, Types.VARCHAR);
			call.execute();

			logger.info(mensajeTransaccion + Constantes.INVOCOSP
					+ storeProcedure);
			
			response.setCodigoRespuesta(call.getString(3));
			response.setMensajeRespuesta(call.getString(4));

			logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT
					+ Constantes.POCODMSG + response.getCodigoRespuesta() + "]");
			logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT
					+ Constantes.POMSJ + response.getMensajeRespuesta() + "]");
			call.close();

		} catch (Exception e) {
			logger.error(
					message + Constantes.MENSAJE_EXCEPTION + e.getMessage()
							+ "] ", e);

			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			String descripcionError = String.valueOf(errors.toString());

			if (descripcionError.toUpperCase(Locale.getDefault()).contains(
					Constantes.SQL_TIMEOUTEXCEPTION)) {
				response.setCodigoRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT1CODIGO));
				response.setMensajeRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT1MSG)
						.replace("[BD]", nombrebd)
						.replace("[SP]", storeProcedure.toString()));
			} else {
				response.setCodigoRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT2CODIGO));
				response.setMensajeRespuesta(propertiesExterno
						.getValueProperty(PropertiesExternos.IDT2MSG)
						.replace("[BD]", nombrebd)
						.replace("[SP]", storeProcedure.toString()));
			}
			
		} finally {
			if (null != call) {
				call.close();
			}
			if (null != connection) {
				connection.close();
			}
			logger.info(message + Constantes.MENSAJE_TIEMPO_PROCESO
					+ (System.currentTimeMillis() - tiempoInicio));
			logger.info(message + Constantes.MENSAJE_FINAL_SERVICIO
					+ nombreMetodo + Constantes.MENSAJE_FINAL_REPOSITORY);
		}
		return response;
	}
	@Override
	protected Predicate[] getSearchPredicates(
			Root<ReservarCodigoRequest> root, ReservarCodigoRequest example) {
		return new Predicate[0];
	}
}
