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
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;

import pe.com.claro.common.domain.repository.AbstractRepository;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExterno;
import pe.com.claro.common.resource.exception.DBException;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ActualizarEstadoRequest;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ActualizarEstadoResponse;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;

@Stateless
public class MSSAPRepository extends AbstractRepository<Object> implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(MSSAPRepository.class);

	@EJB
	private PropertiesExterno propertiesExterno;

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = Constantes.EAIPERSISTENCEPACKAGEUNIT)
	public void setPersistenceUnit00(final EntityManager em) {
		this.entityManager = em;
		logger.info("Cargando el contexto de PERSISTENCE CONTEXT MSSAP");
	}

	public ReservarCodigoResponse obtenerCodigo(String trazabilidad, String status) throws DBException {

		long tiempoInicio = System.currentTimeMillis();
		String nombreMetodo = "obtenerCodigo";
		String mensajeTransaccion = trazabilidad + "[" + nombreMetodo + "]";

		StringBuffer storeProcedure = new StringBuffer();
		logger.info(trazabilidad + Constantes.MENSAJE_INICIO_SERVICIO + nombreMetodo + Constantes.MENSAJE_FINAL_REPOSITORY);
		ReservarCodigoResponse response = new ReservarCodigoResponse();
		String nombrebd = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPBD);
		String owner = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPOWNER);
		String packagebd = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPPACKAGEGESTIONESIM);
		String procedure = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPSPOBTENERCODIGO);
		String timeouteje = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPTIMEOUTEJECUCIONOBTENERCODIGO);

		logger.info(mensajeTransaccion + Constantes.INICIO + Constantes.REPOSITORY + nombreMetodo);
		storeProcedure.append(owner);
		storeProcedure.append(Constantes.PUNTO);
		storeProcedure.append(packagebd);
		storeProcedure.append(Constantes.PUNTO);
		storeProcedure.append(procedure);
		try {

			logger.info(mensajeTransaccion + "PROCEDURE: [" + storeProcedure.toString() + "]");
			logger.info(mensajeTransaccion + "Tiempo ejecucion (segundos): " + timeouteje);
			logger.info(mensajeTransaccion + "Datos 'INPUT': ");
			logger.info(mensajeTransaccion + Constantes.PARAMETROINPUT + " PI_STATUSC : ["
					+ propertiesExterno.getValueProperty(PropertiesExternos.STATUS) + "]");

			Session session = entityManager.unwrap(Session.class);
			session.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {

					try {
						CallableStatement call = connection
								.prepareCall("call " + storeProcedure.toString() + "(?,?,?,?,?,?,?,?)");
						call.setQueryTimeout(Integer.parseInt(timeouteje));
						call.setString(1, status);
						call.registerOutParameter(2, Types.VARCHAR);
						call.registerOutParameter(3, Types.VARCHAR);
						call.registerOutParameter(4, Types.VARCHAR);
						call.registerOutParameter(5, Types.CHAR);
						call.registerOutParameter(6, Types.INTEGER);
						call.registerOutParameter(7, Types.VARCHAR);
						call.registerOutParameter(8, Types.VARCHAR);
						call.execute();

						logger.info(mensajeTransaccion + Constantes.INVOCOSP + storeProcedure);

						response.setCodserie(call.getString(2));
						response.setCodmaterial(call.getString(3));
						response.setCodinterlocutor(call.getString(4));
						response.setStatus(call.getString(5));;
						response.setNroentrega(call.getString(6));
						response.setCodigoRespuesta(call.getString(7));
						response.setMensajeRespuesta(call.getString(8));

						logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT + Constantes.POCODMSG
								+ response.getCodigoRespuesta() + "]");
						logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT + Constantes.POMSJ
								+ response.getMensajeRespuesta() + "]");
						call.close();

					} catch (Exception e) {
						logger.error("ERROR :::: " + e.getMessage(), e);
						throw new SQLException(e);
					}
				}
			});
		} catch (Exception e) {
			logger.error(mensajeTransaccion + "ERROR: [Exception] - [" + e.getMessage() + "] ", e);

			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			String descripcionError = String.valueOf(errors.toString());

			if (descripcionError.toUpperCase(Locale.getDefault()).contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
				throw new DBException(propertiesExterno.getValueProperty(PropertiesExternos.IDT1CODIGO),
						propertiesExterno.getValueProperty(PropertiesExternos.IDT1MSG).replace("[BD]", nombrebd).replace("[SP]", procedure.toString()),
						e.getCause().getMessage(), e, Constantes.STATUS_TIME_OUT);
			} else {
				throw new DBException(propertiesExterno.getValueProperty(PropertiesExternos.IDT2CODIGO),
						propertiesExterno.getValueProperty(PropertiesExternos.IDT2MSG).replace("[BD]", nombrebd).replace("[SP]", procedure.toString()),
						e.getCause().getMessage(), e, Constantes.STATUS_DISPONIBILIDAD);
			}
		} finally {
			logger.info(mensajeTransaccion + "Tiempo TOTAL Proceso: [" + (System.currentTimeMillis() - tiempoInicio)
					+ " milisegundos ]");
			logger.info(mensajeTransaccion + " -------- [FIN - DAO] - Metodo: [" + nombreMetodo + "] --------");
		}

		return response;
	}

	public ActualizarEstadoResponse actualizarEstado(String message, ActualizarEstadoRequest request) throws DBException {

		long tiempoInicio = System.currentTimeMillis();
		String nombreMetodo = "actualizarEstado";
		String mensajeTransaccion = message + "[" + nombreMetodo + "]";
		ActualizarEstadoResponse response = new ActualizarEstadoResponse();
		StringBuilder storeProcedure = new StringBuilder();
		logger.info(message + Constantes.MENSAJE_INICIO_SERVICIO + nombreMetodo + Constantes.MENSAJE_FINAL_REPOSITORY);

		String nombrebd = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPBD);
		String owner = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPOWNER);
		String packagebd = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPPACKAGEGESTIONESIM);
		String procedure = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPSPACTUALIZARESTADO);
		String timeouteje = propertiesExterno.getValueProperty(PropertiesExternos.MSSAPTIMEOUTEJECUCIONACTUALIZARESTADO);

		logger.info(mensajeTransaccion + Constantes.INICIO + Constantes.REPOSITORY + nombreMetodo);
		storeProcedure.append(owner);
		storeProcedure.append(Constantes.PUNTO);
		storeProcedure.append(packagebd);
		storeProcedure.append(Constantes.PUNTO);
		storeProcedure.append(procedure);

		try {
			logger.info(message + Constantes.MENSAJE_INVOCANDO_SP + storeProcedure.toString());
			logger.info(message + Constantes.MENSAJE_TIMEOUT_EJEC + timeouteje);
			logger.info(
					mensajeTransaccion + Constantes.PARAMETROINPUT + " PI_CODSERIE : [" + request.getCodserie() + "]");
			logger.info(mensajeTransaccion + Constantes.PARAMETROINPUT + " PI_STATUS : [" + request.getStatus() + "]");

			Session session = entityManager.unwrap(Session.class);
			session.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {

					try {

						CallableStatement call = connection
								.prepareCall("call " + storeProcedure.toString() + "(?,?,?,?)");
						call.setQueryTimeout(Integer.parseInt(timeouteje));
						call.setString(1, request.getCodserie());
						call.setString(2, request.getStatus());
						call.registerOutParameter(3, Types.VARCHAR);
						call.registerOutParameter(4, Types.VARCHAR);
						call.execute();

						logger.info(mensajeTransaccion + Constantes.INVOCOSP + storeProcedure);

						response.setCodigoRespuesta(call.getString(3));
						response.setMensajeRespuesta(call.getString(4));

						logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT + Constantes.POCODMSG
								+ response.getCodigoRespuesta() + "]");
						logger.info(mensajeTransaccion + Constantes.PARAMETROOUTPUT + Constantes.POMSJ
								+ response.getMensajeRespuesta() + "]");
						call.close();

					} catch (Exception e) {
						logger.error("ERROR :::: " + e.getMessage(), e);
						throw new SQLException(e);
					}
				}
			});
		} catch (Exception e) {
			logger.error(mensajeTransaccion + "ERROR: [Exception] - [" + e.getMessage() + "] ", e);

			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			String descripcionError = String.valueOf(errors.toString());

			if (descripcionError.toUpperCase(Locale.getDefault()).contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
				throw new DBException(propertiesExterno.getValueProperty(PropertiesExternos.IDT1CODIGO),
						propertiesExterno.getValueProperty(PropertiesExternos.IDT1MSG).replace("[BD]", nombrebd).replace("[SP]", procedure.toString()),
						e.getCause().getMessage(), e, Constantes.STATUS_TIME_OUT);
			} else {
				throw new DBException(propertiesExterno.getValueProperty(PropertiesExternos.IDT2CODIGO),
						propertiesExterno.getValueProperty(PropertiesExternos.IDT2MSG).replace("[BD]", nombrebd).replace("[SP]", procedure.toString()),
						e.getCause().getMessage(), e, Constantes.STATUS_DISPONIBILIDAD);
			}
		} finally {
			logger.info(mensajeTransaccion + "Tiempo TOTAL Proceso: [" + (System.currentTimeMillis() - tiempoInicio)
					+ " milisegundos ]");
			logger.info(mensajeTransaccion + " -------- [FIN - DAO] - Metodo: [" + nombreMetodo + "] --------");
		}

		return response;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<Object> root, Object example) {
		return new Predicate[0];
	}
}
