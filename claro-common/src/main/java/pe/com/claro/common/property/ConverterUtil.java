package pe.com.claro.common.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.exception.ConvertException;
import pe.com.claro.common.util.ClaroUtil;

public class ConverterUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ConverterUtil.class);
	
	private static String ERROR_CONVERSION= "Error en la conversión ... ";

	public static void isConvertToDate(String campo, String field) throws ConvertException {
		try {
			if (!ClaroUtil.isValidFormat(Constantes.FORMATO_FECHA_SP, field)) {
				LOG.error("Error en la conversión ... ");
				LOG.error(ERROR_CONVERSION);
				throw new ConvertException("No se pudo convertir a Date el siguiente elemento [" + campo + "] value = ["
						+ field + "] usando el formato = [" + Constantes.FORMATO_FECHA_SP + "]");
			}
		} catch (Exception e) {
			LOG.error("Error en la conversión ... ", e);
			LOG.error(ERROR_CONVERSION, e);
			throw new ConvertException("No se pudo convertir a Date el siguiente elemento [" + campo
					+ "] value = [" + field + "] usando el formato = [" + Constantes.FORMATO_FECHA_SP + "]");
		}
	}

	public static void isConvertToInteger(String campo, String field) throws ConvertException {
		try {
			Integer.parseInt(field);
		} catch (Exception e) {
			LOG.error("Error en la conversión ... ", e);
			LOG.error(ERROR_CONVERSION, e);
			throw new ConvertException("No se pudo convertir a Integer el siguiente elemento [" + campo
					+ "] value = [" + field + "]");
		}
	}

	public static void isConvertToDoubleOnlyEnteros(String campo, String field) throws ConvertException {
		try {
			if (field != null && !field.isEmpty() && field.contains(".")) {
				LOG.error("Error en la conversión ... ");
				LOG.error(ERROR_CONVERSION);
				throw new ConvertException("No se pudo convertir a Float (Solo enteros) el siguiente elemento [" + campo
						+ "] value = [" + field + "]");
			}
			Float.parseFloat(field);
		} catch (Exception e) {
			LOG.error("Error en la conversión ... ", e);
			LOG.error(ERROR_CONVERSION, e);
			throw new ConvertException("No se pudo convertir a Float (Solo enteros) el siguiente elemento [" + campo
					+ "] value = [" + field + "]");
		}
	}

	public static void isConvertToDouble(String campo, String field) throws ConvertException {
		try {
			Double.parseDouble(field);
		} catch (Exception e) {
			LOG.error("Error en la conversión ... ", e);
			LOG.error(ERROR_CONVERSION, e);
			throw new ConvertException("No se pudo convertir a Double el siguiente elemento [" + campo
					+ "] value = [" + field + "]");
		}
	}

	public static void isConvertToString(String campo, Object field) throws ConvertException {
		try {
			field.toString();
		} catch (Exception e) {
			LOG.error("Error en la conversión ... ", e);
			LOG.error(ERROR_CONVERSION, e);
			throw new ConvertException("No se pudo convertir a String el siguiente elemento [" + campo
					+ "] value = [" + field + "]");
		}
	}

	public static void isNotNull(String campo, Object field) throws ConvertException {
		if (field == null) {
			throw new ConvertException(
					"La siguiente variable no puede ser NULL [" + campo + "] value = [" + field + "]");
		}
	}
}