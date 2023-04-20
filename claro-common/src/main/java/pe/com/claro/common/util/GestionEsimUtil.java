package pe.com.claro.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pe.com.claro.common.property.Constantes;


public class GestionEsimUtil {
		private static final Logger wlLogger = LogManager.getLogger(GestionEsimUtil.class);
		private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
		private static final String MD5_ALGORITHM = "MD5";
		private static final String DES_ALGORITHM = "DESede";

		@SuppressWarnings("rawtypes")
		private static Map<Class, JAXBContext> mapContexts = new HashMap<Class, JAXBContext>();

		public static DateFormat getLocalFormat() {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX", Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getDefault());
			return dateFormat;
		}

		public static String printPrettyJSONString(Object o) {
			try {
				return new ObjectMapper().setDateFormat(GestionEsimUtil.getLocalFormat())
						.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writerWithDefaultPrettyPrinter()
						.writeValueAsString(o);
			} catch (JsonProcessingException e) {
				wlLogger.error("Error JsonProcessingException en metodo printPrettyJSONString", e);
				return null;
			}
		}

		public static <T> T convertirJsonHaciaObjeto(String json, Class<T> objeto) {
			try {
				return new ObjectMapper().readValue(json, objeto);
			} catch (IOException e) {
				System.out.println("Error IOException en metodo convertirJsonHaciaObjeto" + e);
				wlLogger.info("Error IOException en metodo convertirJsonHaciaObjeto", e);
				return null;
			}
		}

		public static String obtenerIdTransaccion() {
			return getFechaString(new Date(), "yyyyMMddHHmmss") + getRandomIntegerBetweenRange(1000, 9999);
		}

		public static int getRandomIntegerBetweenRange(double min, double max) {
			Double x = (int) (Math.random() * ((max - min) + 1)) + min;
			return x.intValue();
		}

		public static String getFechaString(Date fecha, String formato) {
			DateFormat dateFormat = new SimpleDateFormat(formato, Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getDefault());
			return dateFormat.format(fecha);
		}

		@SuppressWarnings("rawtypes")
		private static JAXBContext obtainJaxBContextFromClass(Class clas) {
			JAXBContext context;
			context = mapContexts.get(clas);
			if (context == null) {
				try {
					wlLogger.info("Inicializando jaxcontext... para la clase " + clas.getName());
					context = JAXBContext.newInstance(clas);
					mapContexts.put(clas, context);
				} catch (Exception e) {
					wlLogger.error("Error creando JAXBContext:", e);
				}
			}
			return context;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static String anyObjectToXmlText(Object objJaxB) {
			String commandoRequestEnXml = null;
			if (objJaxB != null) {
				JAXBContext context;
				try {
					context = obtainJaxBContextFromClass(objJaxB.getClass());
					Marshaller marshaller = context.createMarshaller();
					StringWriter xmlWriter = new StringWriter();
					marshaller.marshal(
							new JAXBElement(new QName("", objJaxB.getClass().getName()), objJaxB.getClass(), objJaxB),
							xmlWriter);
					XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());
					commandoRequestEnXml = xmlObj.toString();
				} catch (Exception e) {
					wlLogger.error("Error parseando object to xml:", e);
				}
			} else {
				commandoRequestEnXml = "El objeto es nulo";
			}
			return commandoRequestEnXml;
		}

		public static Object xmlTextToJaxB(String xmlText, @SuppressWarnings("rawtypes") Class clas) throws Exception {
			JAXBContext context;
			Object object = null;

			try {
				context = obtainJaxBContextFromClass(clas); // se hace esto para mejorar performance.
				Unmarshaller um = context.createUnmarshaller();

				InputStream is = new ByteArrayInputStream(xmlText.getBytes(Constantes.CHARSETUTF8));

				object = um.unmarshal(is);

			} catch (Exception e) {
				wlLogger.error("Error unmarshalling xmlObject:" + xmlText + ". Detalle Error:", e);
				throw e;
			}

			return object;
		}

		public static String extraerCuerpoResponse(String responseSoapXML)
				throws UnsupportedEncodingException, IOException, SOAPException, TransformerFactoryConfigurationError,
				TransformerConfigurationException, TransformerException {
			InputStream is = new ByteArrayInputStream(responseSoapXML.getBytes(Constantes.CHARSETUTF8));
			SOAPMessage responseSOAP = MessageFactory.newInstance().createMessage(null, is);

			DOMSource source = new DOMSource(responseSOAP.getSOAPBody().extractContentAsDocument());
			StringWriter stringResult = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(source, new StreamResult(stringResult));
			String cuerpoResponse = stringResult.toString();

			return cuerpoResponse;
		}

		public static String concatenarString(List<Object> lista) {
			String result = "";

			for (int i = 0; i < lista.size(); i++) {

				result = result.concat(lista.get(i).toString());

			}

			return result;
		}

		/**
		 * Funcion para generar la firma HMAC - SHA1
		 * computes RFC 2104-compliant HMAC signature.
		 * 
		 * @param data => La data que sera firmada.
		 * @param key  => La clave de la firma.
		 * @return => La firma HMAC en Base64-enconded RFC 2104-compliant.
		 * @throws java.security.SignatureException
		 */
		public static String calculateRFC2104HMAC(String data, String key) throws java.security.SignatureException {

			String result = null;

			try {

				SecretKeySpec signinqkey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
				Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
				mac.init(signinqkey);

				byte[] rawHmac = mac.doFinal(data.getBytes());

				result = EncodingBase64(rawHmac);

			} catch (Exception e) {
				throw new SignatureException("Failed to generate HMAC: " + e.getMessage());
			}

			return result;
		}

		public static String encriptarMD5(String texto, String llave) {
			String base64EncryptedString = "";

			try {

				wlLogger.info("Inicio encriptacion MD5");

				MessageDigest md = MessageDigest.getInstance(MD5_ALGORITHM);
				byte[] digestOfPassword = md.digest(llave.getBytes("utf-8"));
				byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

				SecretKey key = new SecretKeySpec(keyBytes, DES_ALGORITHM);
				Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, key);

				byte[] plainTextBytes = texto.getBytes(Constantes.CHARSETUTF8);
				byte[] buf = cipher.doFinal(plainTextBytes);
				byte[] base64Bytes = org.apache.commons.codec.binary.Base64.encodeBase64(buf);
				base64EncryptedString = new String(base64Bytes);

				wlLogger.info("Encriptacion MD5 correcta");

			} catch (Exception ex) {
				wlLogger.error("Error encriptacion MD5", ex);
			}

			return base64EncryptedString;
		}

		/**
		 * Funcion para codificar la firma generada en BASE 64
		 * 
		 * @param Data => Arreglo de Bytes para ser codificado.
		 * @return => La representaci�n de cadena codificada en base 64
		 * @throws UnsupportedEncodingException
		 */
		public static String EncodingBase64(byte[] Data) throws UnsupportedEncodingException {
			return java.net.URLEncoder.encode(Base64.getEncoder().encodeToString(Data), Constantes.CHARSETUTF8);
		}

		public static String decodingBase64(String base64) {
			byte[] result = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
			return new String(result, StandardCharsets.UTF_8);
		}

		public static Date convertirStringADate(String fecha, String formato) {
			try {
				SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);

				Date fechaFormateada = formatoFecha.parse(fecha);

				return fechaFormateada;
			} catch (Exception e) {
				return null;
			}
		}

		public static Date modificarFecha(Date fecha, int campo, int cantidad) {

			Calendar fechaFinal = Calendar.getInstance();
			fechaFinal.setTime(fecha);

			fechaFinal.add(campo, cantidad);

			return fechaFinal.getTime();

		}

		public static String getIp() {
			String ip = Constantes.TEXTOVACIO;
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception e) {
				wlLogger.error("Ocurrio un error al obtener la IP: ", e);
			}
			return ip;
		}

		public static XMLGregorianCalendar convertirDateHaciaXMLGregorianCalendar(Date fecha) {
			try {
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(fecha);
				return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
			} catch (DatatypeConfigurationException e) {
				wlLogger.error("Ocurrio un error en metodo convertirDateHaciaXMLGregorianCalendar", e);
				return null;
			}
		}

		/**
		 * Remueve los saltos de linea de una cadena
		 * 
		 * @param cadena cadena a modificar
		 * @return cadena sin saltos de linea
		 */
		public static String cleanStringJson(String cadena) {
			return printPrettyJSONString(
					GestionEsimUtil.removerSaltosLinea(StringEscapeUtils.unescapeJava(cadena), StringUtils.EMPTY,
							true));
		}

		/**
		 * Remueve los saltos de linea de una cadena
		 * 
		 * @param cadena cadena a modificar
		 * @return cadena sin saltos de linea
		 */
		public static String removerSaltosLinea(String cadena) {
			return removerSaltosLinea(cadena, StringUtils.EMPTY);
		}

		/**
		 * Reemplaza los saltos de linea de una cadena
		 * 
		 * @param cadena    cadena a modificar
		 * @param reemplazo reemplazo para los saltos de linea
		 * @return nueva cadena
		 */
		public static String removerSaltosLinea(String cadena, String reemplazo) {
			return removerSaltosLinea(cadena, reemplazo, false);
		}

		/**
		 * Reemplaza los saltos de linea de una cadena y tambien los espacios en blanco
		 * de ser solicitado
		 * 
		 * @param cadena      cadena a modificar
		 * @param reemplazo   reemplazo para los saltos de linea
		 * @param sinEspacios reemplazar espacios tambien
		 * @return nueva cadena
		 */
		public static String removerSaltosLinea(String cadena, String reemplazo, boolean sinEspacios) {
			return cadena != null ? cadena.replaceAll("\r?\n|\r" + (sinEspacios ? "|\\s" : StringUtils.EMPTY), reemplazo)
					: null;
		}

	}
