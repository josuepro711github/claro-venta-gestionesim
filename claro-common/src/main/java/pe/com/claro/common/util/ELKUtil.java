package pe.com.claro.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.claro.common.bean.ELKLogLegadoBean;
import pe.com.claro.common.bean.ELKLogServicioBean;
import pe.com.claro.common.property.Constantes;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public class ELKUtil {
	public static final String DETALLE_BD_CONSULTA = "Consulta a: ";

    public static final String DETALLE_BD_TRANSACCION = "Transaccion en: ";

    public static final String DETALLE_WS = "Llamada a servicio: ";

    /**
     * Caracter para separar campos en el log
     */
    static final String SEPARADOR_ELK = "|";

    static final int TIPO_AUDITORIA_INVOCACION_METODO = 1;
    static final int TIPO_AUDITORIA_OBJETO_LEGADO = 2;

    /**
     * Formato de la fecha que para el log
     */
//    static final DateTimeFormatter ELK_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSSZ");
    static final DateTimeFormatter ELK_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * Instancia de log4j para ELKLog
     */
    private static final Logger LOGGER = Logger.getLogger("ELKUtilOrqDebito");

    public static final int TIPO_CONSULTA_WS = 1;
    public static final int TIPO_CONSULTA_BD = 2;

    public static final int TIPO_MENSAJE_REQUEST = 10;
    public static final int TIPO_MENSAJE_RESPONSE = 20;

    public static final int TIPO_ID_IDF = 100;
    public static final int TIPO_ID_IDT = 200;

    public static final int TIPO_ACTIVIDAD_PROCESO = 0;
    public static final int TIPO_ACTIVIDAD_SUBPROCESO = 1;

    public static final int INTERVALO_PROCESO = 1;
    public static final double INTERVALO_SUBPROCESO = 0.1;

    private ELKUtil(){
    }

    /**
     * Escribe en el log de ELKLog cuando se invoca un metodo de servicio
     * @param elkBean Bean con los datos para Elasticsearch
     */
    public static String logInvocacionMetodo(ELKLogServicioBean elkBean) {
        //ID unico por llamada
        String correlativoUnico = UUID.randomUUID().toString();

        //Se arma la cadena con el formato ELKLog
        StringBuilder logSB = new StringBuilder(TIPO_AUDITORIA_INVOCACION_METODO + SEPARADOR_ELK)
                .append(correlativoUnico, 0, correlativoUnico.indexOf('-'))
                .append(SEPARADOR_ELK)
                .append(elkBean.getIdTransaccional() != null ? elkBean.getIdTransaccional() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(ZonedDateTime.now().format(ELK_DATE_TIME_FORMAT))
                .append(SEPARADOR_ELK)
                .append(elkBean.getServicioPadre() != null ? elkBean.getServicioPadre() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getCanal() != null ? elkBean.getCanal() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getTiempoRespuesta())
                .append(SEPARADOR_ELK)
                .append(elkBean.getIdentificadorServicio() != null ? elkBean.getIdentificadorServicio() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getTipoMensaje() != 0 ? elkBean.getTipoMensaje() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK);

        LOGGER.info(logSB);

        return logSB.toString();
    }

    /**
     * Escribe en el log de ELKLog cuando se invoca un objeto Legado (BD o WS)
     * @param elkBean Bean con los datos a logear del legado
     */
    public static String logObjetoLegado(ELKLogLegadoBean elkBean) {
        //ID unico por llamada
        String correlativoUnico = UUID.randomUUID().toString();

        StringBuilder logSB = new StringBuilder(TIPO_AUDITORIA_OBJETO_LEGADO + SEPARADOR_ELK)
                .append(correlativoUnico, 0, correlativoUnico.indexOf('-'))
                .append(SEPARADOR_ELK)
                .append(elkBean.getIdTransaccional() != null ? elkBean.getIdTransaccional() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(ZonedDateTime.now().format(ELK_DATE_TIME_FORMAT))
                .append(SEPARADOR_ELK)
                .append(elkBean.getServicioPadre() != null ? elkBean.getServicioPadre() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getCanal() != null ? elkBean.getCanal() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getTipoActividad())
                .append(SEPARADOR_ELK)
                .append(elkBean.getNumeroActividad() != 0
                        ? ((elkBean.getNumeroActividad() % 1 == 0) ? String.format(Locale.US, "%.0f", elkBean.getNumeroActividad()) : String.format(Locale.US, "%.1f", elkBean.getNumeroActividad()))//((int)elkBean.getNumeroActividad() == ((int)(elkBean.getNumeroActividad() * 10)) / 10 ? String.format("%.0f", elkBean.getNumeroActividad()) : String.format("%.2f", elkBean.getNumeroActividad()))
                        : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getNombreActividad() != null ? elkBean.getNombreActividad() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getDetalleActividad() != null ? elkBean.getDetalleActividad() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getTipoConsulta() != 0 ? elkBean.getTipoConsulta() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getTiempoRespuesta() != 0 ? elkBean.getTiempoRespuesta() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getDuenoConsulta() != null ? elkBean.getDuenoConsulta() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getOrigenConsulta() != null ? elkBean.getOrigenConsulta() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getDetalleConsulta() != null ? elkBean.getDetalleConsulta() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getIdentificadorServicio() != null ? elkBean.getIdentificadorServicio() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK)
                .append(elkBean.getTipoMensaje() != 0 ? elkBean.getTipoMensaje() : StringUtils.EMPTY)
                .append(SEPARADOR_ELK);

        LOGGER.info(logSB);

        return logSB.toString();
    }

    public static double obtenerNumeroActividad(double actividadPrevia, int tipoActividad) {
        return tipoActividad == ELKUtil.TIPO_ACTIVIDAD_SUBPROCESO
                ? (int)(actividadPrevia + ELKUtil.INTERVALO_PROCESO)
                : actividadPrevia;
    }
    
    public static ELKLogLegadoBean obtenerELKLogLegadoBean(String idTransaccion, String endpointURI, String codigoCanal,
			double numeroActividadActual) {
		ELKLogLegadoBean elkLegadoBean = new ELKLogLegadoBean();
		elkLegadoBean.setIdTransaccional(idTransaccion);
		elkLegadoBean.setServicioPadre(Constantes.SERVICE_URI);
		elkLegadoBean.setIdentificadorServicio(endpointURI);
		elkLegadoBean.setCanal(codigoCanal);
		elkLegadoBean.setNumeroActividad(numeroActividadActual);
		return elkLegadoBean;
	}
}
