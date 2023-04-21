package pe.com.claro.common.bean;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.HttpHeaders;

import pe.com.claro.common.property.Constantes;

public class HeaderRequest {

	@NotNull
	private String idTransaccion;
	private String msgid;
	private String timestamp;
	private String userId;
	private String accept;
	
	// Nuevos Párametros
	private String consumer;
	private String country;
	private String dispositivo;
	private String language;
	private String modulo;
	private String msgType;
	private String operation;
	private String pid;
	private String system;
	private String wsIp;

	public HeaderRequest() {
		super();
	}

	public HeaderRequest(HttpHeaders httpHeaders) {
		super();
		this.idTransaccion = httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) != null
				? httpHeaders.getRequestHeader(Constantes.IDTRANSACCION).get(Constantes.CERO)
				: null;
		this.msgid = httpHeaders.getRequestHeader(Constantes.MSGID) != null
				? httpHeaders.getRequestHeader(Constantes.MSGID).get(Constantes.CERO)
				: null;
		this.timestamp = httpHeaders.getRequestHeader(Constantes.TIMESTAMP) != null
				? httpHeaders.getRequestHeader(Constantes.TIMESTAMP).get(Constantes.CERO)
				: null;
		this.userId = httpHeaders.getRequestHeader(Constantes.USERID) != null
				? httpHeaders.getRequestHeader(Constantes.USERID).get(Constantes.CERO)
				: null;
		this.accept = httpHeaders.getRequestHeader(Constantes.ACCEPT) != null
				? httpHeaders.getRequestHeader(Constantes.ACCEPT).get(Constantes.CERO)
				: null;
		
		// Nuevos Párametors
		this.consumer = httpHeaders.getRequestHeader(Constantes.CONSUMER) != null
				? httpHeaders.getRequestHeader(Constantes.CONSUMER).get(Constantes.CERO)
				: null;
		this.country = httpHeaders.getRequestHeader(Constantes.COUNTRY) != null
				? httpHeaders.getRequestHeader(Constantes.COUNTRY).get(Constantes.CERO)
				: null;
		this.dispositivo = httpHeaders.getRequestHeader(Constantes.DISPOSITIVO) != null
				? httpHeaders.getRequestHeader(Constantes.DISPOSITIVO).get(Constantes.CERO)
				: null;
		this.language = httpHeaders.getRequestHeader(Constantes.LANGUAGE) != null
				? httpHeaders.getRequestHeader(Constantes.LANGUAGE).get(Constantes.CERO)
				: null;
		this.modulo = httpHeaders.getRequestHeader(Constantes.MODULO) != null
				? httpHeaders.getRequestHeader(Constantes.MODULO).get(Constantes.CERO)
				: null;
		this.msgType = httpHeaders.getRequestHeader(Constantes.MSG_TYPE) != null
				? httpHeaders.getRequestHeader(Constantes.MSG_TYPE).get(Constantes.CERO)
				: null;
		this.operation = httpHeaders.getRequestHeader(Constantes.OPERATION) != null
				? httpHeaders.getRequestHeader(Constantes.OPERATION).get(Constantes.CERO)
				: null;
		this.pid = httpHeaders.getRequestHeader(Constantes.PID) != null
				? httpHeaders.getRequestHeader(Constantes.PID).get(Constantes.CERO)
				: null;
		this.system = httpHeaders.getRequestHeader(Constantes.SYSTEM) != null
				? httpHeaders.getRequestHeader(Constantes.SYSTEM).get(Constantes.CERO)
				: null;
		this.wsIp = httpHeaders.getRequestHeader(Constantes.WSIP) != null
				? httpHeaders.getRequestHeader(Constantes.WSIP).get(Constantes.CERO)
				: null;
		
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	// Nuevos Parametros
	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getWsIp() {
		return wsIp;
	}

	public void setWsIp(String wsIp) {
		this.wsIp = wsIp;
	}

	@Override
	public String toString() {
		return "HeaderRequest [idTransaccion=" + idTransaccion + ", msgid=" + msgid + ", timestamp=" + timestamp
				+ ", userId=" + userId + ", accept=" + accept + "]";
	}

}
