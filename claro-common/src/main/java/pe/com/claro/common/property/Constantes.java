package pe.com.claro.common.property;

public class Constantes {

	// 1.Constantes Generales de Servicio 
	public static final String NOMBRERECURSO = "claro-venta-gestionesim";
	public static final String SEPARADORPUNTO = ".";
	public static final String VERSION = "1.0.0";
	public static final String PATH = "/venta/gestionesim/v1.0.0";
	public static final String API_VALUE_GESTIONESIM = "/venta/gestionesim/v1.0.0";
	public static final String BASEPATH = "claro-venta-gestionesim-resource/api";
	public static final String RESOURCE = "/reservarCodigo";
	public static final String CHARSETUTF8 = ";charset=UTF-8";
	public static final String API_DESCRIPTION_GESTIONESIM = "Servicio que actualiza estado de registro";
	public static final String PERSISTENCE_CONTEXT_OAC = "pe.com.claro.venta.data.source.mssap";
	public static final String EAIPERSISTENCEPACKAGEUNIT = "pe.com.claro.venta.gestionesim.mssap";
//	public static final String EAIPERSISTENCEPACKAGEUNIT = "pe.com.claro.jdbc.datasources.noXA.mssapDSjdbc";
	public static final String RPTA_SUCCESS = "Executed-Success";
	
	public static final String SERVICE_URI = "/" + NOMBRERECURSO.replaceAll("/", "");
	public static final String NOMBRE_SERVICIO_HUBSIM = "hubSim";
	public static final String WS_TIMEOUT_EXCEPTION = "SocketTimeoutException";
	public final static int STATUS_TIME_OUT = 504;
	public final static int STATUS_DISPONIBILIDAD = 503;
	public static final String CODIGO_ERROR_TIMEOUT = "-1";
	public static final String CODIGO_ERROR_DISPONIBILIDAD = "-2";
	public static final String SEPARADOR_ESPACIO = " ";

	public static final String ERROREX = "[$ex]";
	public static final String ERROR = "[ERROR]: ";

	public static final String NOMBREMETODO = "reservarCodigo";
	public static final String PATHMETODO1 = "/reservarCodigo";
	public static final String NOMBRE_REG_ACTIVIDAD = "reservarCodigo";
	public static final String BD_MSSAP = "MSSAP";
	public static final String IDTX = " idTx=";
	
	public static final String APLICATION_PRODUCES = "application/json;charset=UTF-8";
	public static final String FILTER_NAME = "HTML5CorsFilter";
	public static final String URL_PATTERNS = "/api/*";
	public static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	public static final String ALLOW_ORIGIN_VALUE = "*";
	public static final String ALLOW_METHODS = "Access-Control-Allow-Methods";
	public static final String ALLOW_METHODS_VALUE = "GET, POST, DELETE, PUT";
	public static final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String ALLOW_HEADERS_VALUE = "Content-Type";
	public static final String SERVLET_NAME = "SwaggerJaxrsConfig";
	public static final String SERVLET_VALUE = "/SwaggerJaxrsConfig";
	public static final String CONTEXTO = "weblogic.jndi.WLInitialContextFactory";
	public static final String BASE_PATH = "/claro-venta-gestionesim-resource/api";
	public static final String API_VERSION = "1.0.0";
	
	public static final String INICIO = "Inicio del metodo";
	public static final String REPOSITORY = " - REPOSITORY(DAO)";
	public static final String MENSAJE_INICIO_SERVICIO = "[INICIO de metodo: ";
	public static final String MENSAJE_FINAL_REPOSITORY = " - Repository]";
	public static final String MENSAJE_FINAL_SERVICIO = "[FIN de metodo: ";
	public static final String MENSAJE_TIEMPO_PROCESO = "Tiempo total del proceso(ms): {}";
	public static final String MENSAJE_EXCEPTION = "ERROR: [Exception] - [";
	public static final String MENSAJE_INVOCANDO_SP = "Invocando SP ";
	public static final String MENSAJE_TIMEOUT_EJEC = "Timeout ejecucion (segundos): ";
	public static final String MENSAJE_OUTPUT = "[OUTPUT][";
	public static final String PARAMETROINPUT = " PARAMETROS [INPUT]: ";
	public static final String PARAMETROOUTPUT = " PARAMETROS [OUTPUT]: ";
	public static final String INVOCOSP = "Se invocó con éxito el SP: {}";
	public static final String POCODMSG = "PO_COD_RPTA: [";
	public static final String POMSJ = "PO_MSG_RPTA: [";
	public static final String SQL_TIMEOUTEXCEPTION = "SQLTIMEOUTEXCEPTION";
	public static final String RESPONSE = " Response : ";
	
	public static final int CODIGO200 = 200;
	public static final String MENSAJE200 = "Success";
	public static final int CODIGO204 = 204;
	public static final String MENSAJE204 = "No Content";
	public static final int CODIGO400 = 400;
	public static final String MENSAJE400 = "Bad Request";
	public static final int CODIGO401 = 401;
	public static final String MENSAJE401 = "Unauthorized";
	public static final int CODIGO403 = 403;
	public static final String MENSAJE403 = "Forbidden";
	public static final int CODIGO404 = 404;
	public static final String MENSAJE404 = "Not Found";
	public static final int CODIGO405 = 405;
	public static final String MENSAJE405 = "Method Not Allowed";
	public static final int CODIGO422 = 422;
	public static final String MENSAJE422 = "Unprocessable entity Functional error";
	public static final int CODIGO500 = 500;
	public static final String MENSAJE500 = "Internal Server Error";

	
	public static final String ORACLEDRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String CANNOT_GET_CONNECTION = "Cannot get connection: ";

	// 4.Constantes palabras claves
	public static final String ACTIVIDAD1PARAMETROSOBLIGATORIOS = "[Actividad 1 - Validar parametros obligatorio]";
	public final static String SALTO_LINEA = "\n";
	public static final String INICIO_TEXT = "[INICIO]";
	public static final String PARAMETROSBODY = "Body Request: ";
	public static final String PARAMETROSENTRADA = "Parametros de entrada: ";
	public static final String REQUESTINICIO = "**** Body Request Recibido ....  \n{}";
	public static final String HEADERREQUEST = "Request Header: ";
	public static final String MENSAJE = "mensaje";
	public static final String CODIGO = "codigo";
	public static final String NOMBRESP = "$nombre_SP";
	public static final String NOMBREJNDI = "$nombre_JNDI";
	public static final String RESPUESTA = "RESPUESTA: ";
	public static final String VALUE = "] value = [";
	public final static String FIN_METODO = "[ FIN de metodo ";
	public final static String TIEMPO_TOTAL = " ] Tiempo total de proceso (ms):";
	public final static String MILISEG = " milisegundos.";	
	public final static String TIEMPOTOTALPROCESO = "Tiempo TOTAL del proceso";
	public static final String DEVOLVERRESPONSE = " Response a devolver:";
	public static final String TEXTOOWNER = " OWNER : ";
	public static final String TEXTOPAQUETE = " PAQUETE : ";
	public static final String TEXTOPROCEDURE = " PROCEDURE : ";
	public static final String TEXTONOMBREBD = " BASE DE DATOS : ";
    public static final String TEXTOUSUARIO = " USUARIO : ";
	public static final String TEXTOESPACIO = " ";
	public static final String TEXTOVACIO = "";
	public static final String CONSULTAINICIO = " Consulta Inicio : ";
	public static final String METODOINICIO = " Inicio Metodo: ";
	public static final String CALL = "call ";
	public static final String DATAAUDIT = "dataAudit";
	public static final String TEXTOERRORTIMEOUT = "[Error De TimeOut en: ";
	public static final String TEXTOERRORDISPONIBILIDAD = "[Error De Disponibilidad en ] ";
	public static final String PARAMINPUT = " PARAMETROS [INPUT]: ";
    public static final String PARAMOUTPUT = " PARAMETROS [OUTPUT]: ";
    public static final String MENSAJERROR = "[ERROR]: ";
    public static final String ACTIVIDAD1 = "------- 1.insertar los datos de una venta de chip a un sol o pack a 9 soles------------";
    public static final String VALIDACIONPARAMETROSCORRECTOS = " Validacion correcta de parametros de entrada";
    public static final String VALIDACIONPARAMETROSINCORRECTOS = " Parametros de entrada no cumple validacion";
    public static final String PROPERTIESEXTERNOS = ".properties";
    public static final String SQLTIMEOUTEXCEPTION = "SQLTIMEOUTEXCEPTION";
    public static final String SEPARADOR = "-----------------------------------------------------------------------------";
    public static final String MILISEGUND = " milisegundos";
    
	//UTIL
    public static final String CONNECT_TIMEOUT = "com.sun.xml.ws.connect.timeout";
    public static final String REQUEST_TIMEOUT = "com.sun.xml.ws.request.timeout";
	public static final String ID = "id";
	public final static String VERSION_HTTP = "Version-HTTP";
	public final static String HEADER_PARAM_ID_TRANSACCION = "idTransaccion";
	public final static String HEADER_PARAM_MSG_ID = "msgid";
	public static final String IGUAL = "=";
	public static final String TEXTO_VACIO = "";
	public static final String IDTRANSACCION = "idTransaccion";
	public static final String MSGID = "msgid";
	public static final String ACCEPT = "accept";
	public static final String API = "api";
	public static final String SWAGGERJAXRSCONFIG = "SwaggerJaxrsConfig";
	public static final String URLSWAGGERJAXRSCONFIG = "/SwaggerJaxrsConfig";
	public static final String HTML5CORSFILTER = "HTML5CorsFilter";
	public static final String URLPATTERNS = "/api/*";
    public static final String ACCESSCONTROLALLOWORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESSCONTROLALLOWMETHODS = "Access-Control-Allow-Methods";
    public static final String ACCESSCONTROLALLOWHEADERS = "Access-Control-Allow-Headers";
    public static final String ASTERISCO = "*";
    public static final String METODOSPERMITIDOS = "GET, POST, DELETE, PUT";
    public static final String CONTENTTYPE = "Content-Type";
    public static final String DEFAULTENCONDIGPROPERTIES = "ISO-8859-1";
    public static final String DEFAULTENCONDINGAPI = "UTF-8";
	public static final String VALOR = "valor";
	public static final String ERROR_EXCEPTION = "Error Exception: ";
	public static final String ERROREXCEPTION = "Error [Exception]";
	public static final String CONFIGPROPERTIES = "config.properties";
	public static final String UNO = "1";
    public static final String DOS = "2";
    public static final String CINCO = "5";
    public static final String CERO_CADENA = "0";
    public static final int MENOS_UNO =-1;
    public static final int CERO =0;	
	public static final int NUM_ZERO =0;
	public static final String COMPONENTE = "$componente";

	// 5.Constantes caracteres claves
	public static final String CHAR_PARENTESIS_IZQUIERDO = "(";
	public static final String CHAR_PARENTESIS_DERECHO = ")";
	public static final String CHAR_CORCHETE_IZQUIERDO = " [";
	public static final String CHAR_CORCHETE_DERECHO = "] ";
	public static final String CHAR_CORCHETE_PUNTOS = ": [";
	public static final String CHAR_INTERROGACION = "?";
	public static final String CHAR_COMA = ",";
	public static final String ID_TXT = " idTx=";
	public static final String FORMATOFECHACABECERA = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String FORMATO_FECHA_DEFAULT = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMATO_FECHA_SP = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATO_FECHA_Z = "yyyy-MM-dd'T'HH:mm:ss+00:00";
	public static final String FORMATO_FECHA_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String FORMATOFECHAISO = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String BUSCARPIPELINE = "\\|";
	public static final String PUNTO = ".";
	public static final String DOSPUNTOS = ":";
	public static final String PUNTOCOMA = ";";
	public static final String NULL=null;
	public static final String CLARO_PROPERTIES = "claro.properties";
	
	// 6.Headers
	public static final String CONSUMER = "consumer";
	public static final String DISPOSITIVO = "dispositivo";
	public static final String LANGUAGE = "language";
	public static final String MODULO = "modulo";
	public static final String MSG_TYPE = "msgType";
	public static final String OPERATION = "operation";
	public static final String PID = "pid";
	public static final String SYSTEM = "system";
	public static final String TIMESTAMP = "timestapm";
	public static final String USRID = "userId";
	public static final String WSIP = "wsIp";
	public static final String USERID = null;

	
}