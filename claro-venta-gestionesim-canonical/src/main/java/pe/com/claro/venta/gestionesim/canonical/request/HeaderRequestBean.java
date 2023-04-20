package pe.com.claro.venta.gestionesim.canonical.request;

import javax.validation.constraints.NotNull;

public class HeaderRequestBean {
	@NotNull
	private String idTransaccion;
	@NotNull
	private String msgid;
	@NotNull
	private String accept;
	@NotNull
	private String timestamp;
	@NotNull
	private String userId;
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

	public HeaderRequestBean() {
		super();
	}

	public HeaderRequestBean(String idTransaccion, String msgid, String accept, String timestamp, String userId,
			String consumer, String country, String dispositivo, String language, String modulo, String msgType,
			String operation, String pid, String system, String wsIp) {
		super();
		this.idTransaccion = idTransaccion;
		this.msgid = msgid;
		this.accept = accept;
		this.timestamp = timestamp;
		this.userId = userId;
		this.consumer = consumer;
		this.country = country;
		this.dispositivo = dispositivo;
		this.language = language;
		this.modulo = modulo;
		this.msgType = msgType;
		this.operation = operation;
		this.pid = pid;
		this.system = system;
		this.wsIp = wsIp;
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

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
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

}
