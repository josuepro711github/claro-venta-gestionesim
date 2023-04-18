package pe.com.claro.common.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.HttpHeaders;

import pe.com.claro.common.property.Constantes;

public class HeaderRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String idTransaccion;
	@NotNull
	private String msgid;
	@NotNull
	private String timestamp;
	@NotNull
	private String userId;
	@NotNull
	private String accept;
	
	public HeaderRequest(){
		super();
	}

	public HeaderRequest(HttpHeaders httpHeaders) {
		super();
		this.idTransaccion = httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) != null
				? httpHeaders.getRequestHeader(Constantes.IDTRANSACCION).get(Constantes.CERO) : null;		
		this.msgid = httpHeaders.getRequestHeader(Constantes.MSGID) != null
				? httpHeaders.getRequestHeader(Constantes.MSGID).get(Constantes.CERO) : null;
		this.userId = httpHeaders.getRequestHeader(Constantes.USERID) != null
				? httpHeaders.getRequestHeader(Constantes.USERID).get(Constantes.CERO) : null;
		this.accept = httpHeaders.getRequestHeader(Constantes.ACCEPT) != null
				? httpHeaders.getRequestHeader(Constantes.ACCEPT).get(Constantes.CERO) : null;
		this.timestamp = httpHeaders.getRequestHeader(Constantes.TIMESTAMP) != null
						? httpHeaders.getRequestHeader(Constantes.TIMESTAMP).get(Constantes.CERO) : null;
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

	@Override
	public String toString() {
		return "HeaderRequest [idTransaccion=" + idTransaccion + ", msgid="
				+ msgid + ", timestamp=" + timestamp + ", userId=" + userId + ", accept=" + accept + "]";
	}

}



