package pe.com.claro.common.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.HttpHeaders;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pe.com.claro.common.property.Constantes;

public class ClaroUtil {
	private static final Logger log = Logger.getLogger(ClaroUtil.class);

	public static String getAllHttpHeaders(HttpHeaders httpHeaders) {
		StringBuffer data = new StringBuffer();

		Set<String> headerKeys = httpHeaders.getRequestHeaders().keySet();
		for (String header : headerKeys) {
			data.append(header + ":" + httpHeaders.getRequestHeader(header).get(Constantes.CERO) + "\n");
		}
		return data.toString().trim();
	}

	public static String getHttpHeadersNoNull(HttpHeaders httpHeaders) {

		String idTransaccion = httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) != null
				? httpHeaders.getRequestHeader(Constantes.IDTRANSACCION).get(0) : "";
		String msgid = httpHeaders.getRequestHeader(Constantes.MSGID) != null
				? httpHeaders.getRequestHeader(Constantes.MSGID).get(0) : "";
		String timestamp = "";
		Calendar a = ClaroUtil.toCalendar(httpHeaders.getRequestHeader(Constantes.TIMESTAMP) != null
				? httpHeaders.getRequestHeader(Constantes.TIMESTAMP).get(0).toString() : Constantes.TEXTOVACIO);
		if (a != null)
			timestamp = a.getTime().toString();
		String userId = httpHeaders.getRequestHeader(Constantes.USERID) != null
				? httpHeaders.getRequestHeader(Constantes.USERID).get(0) : "";
		String accept = httpHeaders.getRequestHeader(Constantes.ACCEPT) != null
				? httpHeaders.getRequestHeader(Constantes.ACCEPT).get(0) : "";
		StringBuffer data = new StringBuffer();
		data.append("[" + Constantes.IDTRANSACCION + "=");
		data.append(idTransaccion);
		data.append(" " + Constantes.MSGID + "=");
		data.append(msgid);
		data.append(" " + Constantes.TIMESTAMP + "=");
		data.append(timestamp);
		data.append(" " + Constantes.USERID + "=");
		data.append(userId);
		data.append(" " + Constantes.ACCEPT + "=");
		data.append(accept);
		data.append(Constantes.CHAR_CORCHETE_IZQUIERDO);
		return data.toString();
	}

	public static String getHttpHeaders(HttpHeaders httpHeaders) {

		if (httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) == null)
			return null;
		if (httpHeaders.getRequestHeader(Constantes.MSGID) == null)
			return null;
		if (httpHeaders.getRequestHeader(Constantes.TIMESTAMP) == null)
			return null;
		if (httpHeaders.getRequestHeader(Constantes.USERID) == null)
			return null;

		String idTransaccion = httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) != null
				? httpHeaders.getRequestHeader(Constantes.IDTRANSACCION).get(0) : "";
		String msgid = httpHeaders.getRequestHeader(Constantes.MSGID) != null
				? httpHeaders.getRequestHeader(Constantes.MSGID).get(0) : "";
		String timestamp = httpHeaders.getRequestHeader(Constantes.TIMESTAMP) != null
				? httpHeaders.getRequestHeader(Constantes.TIMESTAMP).get(0) : "";
		String userId = httpHeaders.getRequestHeader(Constantes.USERID) != null
				? httpHeaders.getRequestHeader(Constantes.USERID).get(0) : "";

		StringBuffer data = new StringBuffer();
		data.append("[" + Constantes.IDTRANSACCION + "=");
		data.append(idTransaccion);
		data.append(" " + Constantes.MSGID + "=");
		data.append(msgid);
		data.append(" " + Constantes.TIMESTAMP + "=");
		data.append(timestamp);
		data.append(" " + Constantes.USERID + "=");
		data.append(userId);
		data.append(" " + Constantes.ACCEPT + "=");
		data.append(httpHeaders.getMediaType());
		data.append(Constantes.CHAR_CORCHETE_IZQUIERDO);
		return data.toString();
	}

	public static String getExceptionToMensaje(Exception e) {
		String msg = Constantes.TEXTOVACIO;
		if (e.getCause() != null) {
			msg = e.getCause().toString();
		} else {
			msg = e.toString();
		}
		return msg;
	}

	public static DateFormat getLocalFormat() {
		DateFormat dateFormat = new SimpleDateFormat(Constantes.FORMATOFECHACABECERA);
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat;
	}

	public static String printPrettyJSONString(Object o)
			throws JsonProcessingException {
		return new ObjectMapper()
				.setDateFormat(ClaroUtil.getLocalFormat())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
						false).writerWithDefaultPrettyPrinter()
				.writeValueAsString(o);
	}

	public static String printJSONString(Object o) {
		try {
			return new ObjectMapper().setDateFormat(ClaroUtil.getLocalFormat())
					.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return Constantes.TEXTOVACIO;
		}
	}

	public static String nuloAVacio(Object object) {

		if (object == null) {
			return Constantes.TEXTOVACIO;
		} else {
			return object.toString();
		}
	}

	public static Object nuloAVacioObject(Object object) {
		if (object == null) {
			return Constantes.TEXTOVACIO;
		} else {
			return object;
		}
	}

	public static String verifiyNull(Object object) {
		String a = null;
		if (object != null) {
			a = object.toString();
		}
		return a;
	}

	public static String convertProperties(Object object) {
		String a = null;
		if (object != null) {
			a = object.toString();
			try {
				a = new String(a.getBytes(Constantes.DEFAULTENCONDIGPROPERTIES), Constantes.DEFAULTENCONDINGAPI);
			} catch (Exception e) {
				log.error("Error getProperties Encoding Failed, trayendo Encoding por defecto", e);
			}
		}
		return a;
	}

	public static Integer convertirInteger(Object object) {

		Integer res = null;
		if (object != null) {
			if (object instanceof BigDecimal) {
				BigDecimal bd = (BigDecimal) object;
				res = bd.intValueExact();
			} else {
				System.out.println(object.getClass().getSimpleName());
			}
		}
		return res;
	}

    public static String removerPrefijoPais(String operatorUserID, String prefix) {
        String operatorUserIDIn = operatorUserID;
        if (operatorUserID.substring(0, 2).equals(prefix)) {
                operatorUserIDIn = operatorUserID.substring(2, operatorUserID.length());
        }
        return operatorUserIDIn;
    }
	
    public static String agregarPrefijoPais(String operatorUserID, String prefix) {
        String operatorUserIDIn = operatorUserID;
        if (!operatorUserID.substring(0, 2).equals(prefix)) {
                operatorUserIDIn = prefix + operatorUserID;
        }
        return operatorUserIDIn;
    }

	public static Float convertirFloat(Object object) {
		Float res = null;
		if (object != null) {
			if (object instanceof BigDecimal) {
				BigDecimal bd = (BigDecimal) object;
				res = bd.floatValue();
			}
		}
		return res;
	}

	/**
	 * Genera un String a partir de un Date, si fecha es NULL retorna ""
	 * (vacio).
	 *
	 * @param fecha
	 *            tipo Date
	 * @return String de la forma dd/MM/yyyy
	 */
	public static String dateAString(Date fecha) {
		if (fecha == null) {
			return Constantes.TEXTOVACIO;
		}
		return dateAString(fecha, Constantes.FORMATOFECHACABECERA);
	}

	/**
	 * Genera un String a partir de un Date de acuerdo al fomrato enviado, si
	 * fecha es NULL toma la fecha actual.
	 *
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String dateAString(Date fecha, String formato) {

		if (fecha != null) {
			SimpleDateFormat formatoDF = new SimpleDateFormat(formato, Locale.ROOT);
			return formatoDF.format(fecha);
		} else {
			return null;
		}

	}

	public static Calendar toCalendar(final String iso8601string) {
		Calendar calendar = GregorianCalendar.getInstance();
		String s = iso8601string.replace("Z", "+00:00");
		try {
			s = s.substring(0, 22) + s.substring(23); // to get rid of the ":"
			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT).parse(s);
			calendar.setTime(date);
		} catch (IndexOutOfBoundsException e) {
			log.error("Ocurrio un error al recorrer la cadena de Fecha", e);
			calendar = null;
		} catch (ParseException e) {
			log.error("Ocurrio un error al convertir a Date la cadena de la fecha", e);
			calendar = null;
		}
		return calendar;
	}

	public static boolean isValidFormat(String format, String value) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ROOT);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException ex) {
			date = null;
		}
		return date != null;
	}

	public static Date getValidFormatDate(String format, String value) {
		Date date = null;
		if (value != null && !value.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ROOT);
				date = sdf.parse(value);
				if (!value.equals(sdf.format(date))) {
					date = null;
				}
			} catch (ParseException ex) {
				date = null;
			}
		}
		return date;
	}

	public static Date stringADate(String fecha, String formatoFecha) {
		Date fechaGenerada = null;
		if (fecha != null) {
			try {
				SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
				fechaGenerada = formato.parse(fecha);
			} catch (Exception e) {
				log.error("Error al formatear fecha :" + fecha, e);
			}
		}

		return fechaGenerada;
	}

	public static String transformacion(String properties, String request) {
		String salida = "";
		String array[] = properties.split("\\|");

		for (String objeto : array) {

			String otro[] = objeto.split("\\:");

			for (int i = 0; i < otro.length; i++) {
				if (request.equals(otro[0])) {
					salida = otro[1];
				}
			}

		}
		return salida;

	}

	public static XMLGregorianCalendar convertDateToGregorianCalendar(Date fecha) {
		GregorianCalendar calender = new GregorianCalendar();
		calender.setTime(fecha);

		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(calender.get(Calendar.YEAR),
					calender.get(Calendar.MONTH) + 1, calender.get(Calendar.DAY_OF_MONTH),
					DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] devolverValoresConfigurables(String datoBuscar, String variableProperties) {
		boolean flagEncontroTipo = false;
		String[] arregloDatos = { Constantes.TEXTO_VACIO, Constantes.TEXTO_VACIO, Constantes.TEXTO_VACIO };
		String[] tipos = variableProperties.split(Constantes.BUSCARPIPELINE);
		log.info("Se buscar la informacion para el siguiente dato: " + datoBuscar);
		log.info("Se buscar en el siguiente bloque: " + variableProperties);
		for (String bean : tipos) {
			String[] datosFin = bean.split(";");
			if (datosFin[0].equals(datoBuscar)) {
				arregloDatos = datosFin;
				flagEncontroTipo = true;
				break;
			}
		}
		if (!flagEncontroTipo) {
			log.info("No se encontro el dato a buscar " + datoBuscar);
		}
		return arregloDatos;
	}

	public static String convertProp(Object object) {
		String a = null;
		if (object != null) {
			a = object.toString();
			try {
				a = new String(a.getBytes(Constantes.DEFAULTENCONDIGPROPERTIES), Constantes.DEFAULTENCONDINGAPI);
			} catch (Exception e) {
				log.error("Error getProperties Encoding Failed, trayendo Encoding por defecto", e);
			}
		}
		return a;
	}
	public static String getIdTransaccion(HttpHeaders httpHeaders) {
      String idTransaccion = httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) != null
              ? httpHeaders.getRequestHeader(Constantes.IDTRANSACCION).get(0) : "";
      StringBuffer data = new StringBuffer();
      data.append("[");
      data.append(Constantes.IDTRANSACCION);
      data.append("=");
      data.append(idTransaccion);
      data.append("]");
      return data.toString();
  }
	public static Connection getJNDIConnection(String mensajeTransaccion,
			String jndi, String tiempoConexion) throws SQLException, ClassNotFoundException, NamingException {

		long tiempoInicio = System.currentTimeMillis();
		String metodoJava = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		String cadenaMensaje = mensajeTransaccion + " [" + metodoJava + "]";
		log.info(cadenaMensaje + "[INICIO] - METODO: [" + metodoJava + "]");

		Connection result = null;
		try {
			Class.forName(Constantes.ORACLEDRIVER);
			Class.forName(Constantes.ORACLEDRIVER);
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(jndi);
			datasource.setLoginTimeout(Integer.parseInt(tiempoConexion));
			result = datasource.getConnection();
		} catch (SQLException | NamingException ex) {
			log.error(mensajeTransaccion + Constantes.CANNOT_GET_CONNECTION
					+ ex);
			throw ex;
		} finally {
			log.info(cadenaMensaje + "Tiempo TOTAL Proceso: ["
					+ (System.currentTimeMillis() - tiempoInicio)
					+ " milisegundos ]");
			log.info(cadenaMensaje + "[FIN] - METODO: [" + metodoJava + "]");
		}
		return result;
	}
	public static String loggerFin(String mensajeTransaccion, long tiempoTotal,
			String nomMetodo) {
		StringBuilder stringBuffer = new StringBuilder();

		stringBuffer.append(mensajeTransaccion);
		stringBuffer.append(Constantes.FIN_METODO);
		stringBuffer.append(nomMetodo);
		stringBuffer.append(Constantes.TIEMPO_TOTAL);
		stringBuffer.append(tiempoTotal);
		stringBuffer.append(Constantes.MILISEG);
		return stringBuffer.toString();
	}
	public static String getFechaString(Date fecha, String formato) {
		DateFormat dateFormat = new SimpleDateFormat(formato,
				Locale.getDefault());
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat.format(fecha);
	}
	public static String obtenerIdTransaccion() {
		return getFechaString(new Date(), "yyyyMMddHHmmss")
				+ getRandomIntegerBetweenRange(1000, 9999);
	}
	public static int getRandomIntegerBetweenRange(double min, double max) {
		Double x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x.intValue();
	}
}