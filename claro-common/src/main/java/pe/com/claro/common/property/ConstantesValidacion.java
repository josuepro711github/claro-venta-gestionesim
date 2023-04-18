package pe.com.claro.common.property;

public class ConstantesValidacion {
	public static final String SIZE = "El campo debe de tener de {min} a {max} caracteres";
	public static final String SIZE_MAX = "El campo debe de tener maximo {max} caracteres";
	public static final String NOT_NULL = "El campo debe de tener un valor";
	public static final String REGEXP = "El formato debe ser ";

	public static final String REGEXP_FECHA_ISO = "^(\\d{4})(-)(0[1-9]|1[0-2])(-)([0-2][0-9]|3[0-1])(T)([0-1][0-9]|2[0-3])(:)([0-5][0-9])(:)([0-5][0-9])$";
	public static final String REGEXP_FECHA_3 = "^(\\d{4})(0[1-9]|1[0-2])([0-2][0-9]|3[0-1])([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";
	public static final String REGEXP_FECHA_4 = "^(\\d{4})-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])$";

}
