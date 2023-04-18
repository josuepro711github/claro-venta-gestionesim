package pe.com.claro.common.resource.util;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.HttpHeaders;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.util.ClaroUtil;

@Entity
public class HeaderRequestBean{

	@NotNull(message="idTransaccion")
	private String	idTransaccion;
	@NotNull(message="msgid")
	private String	msgid;
	@NotNull(message="timestamp")
	private Date	timestamp;
	@NotNull(message="userId")
	private String	userId;
	@NotNull(message="accept")
	private String	accept;

	public HeaderRequestBean(){
		super();
	}

	public HeaderRequestBean( HttpHeaders httpHeaders ){
		this.idTransaccion = httpHeaders.getRequestHeader( Constantes.IDTRANSACCION ) != null? httpHeaders.getRequestHeader( Constantes.IDTRANSACCION ).get( 0 ): null;
		this.msgid = httpHeaders.getRequestHeader( Constantes.MSGID ) != null? httpHeaders.getRequestHeader( Constantes.MSGID ).get( 0 ): null;
		
		Calendar a = ClaroUtil.toCalendar(httpHeaders.getRequestHeader(Constantes.TIMESTAMP) != null
				? httpHeaders.getRequestHeader(Constantes.TIMESTAMP).get(0) : Constantes.TEXTO_VACIO);
		if (a != null)
			this.timestamp = a.getTime();
		
		this.userId  = httpHeaders.getRequestHeader( Constantes.USRID ) != null? httpHeaders.getRequestHeader( Constantes.USRID ).get( 0 ): null;
		this.accept =  httpHeaders.getRequestHeader( Constantes.ACCEPT ) != null? httpHeaders.getRequestHeader( Constantes.ACCEPT ).get( 0 ): null;
	}

	public String getIdTransaccion(){
		return idTransaccion;
	}

	public void setIdTransaccion( String idTransaccion ){
		this.idTransaccion = idTransaccion;
	}

	public String getMsgid(){
		return msgid;
	}

	public void setMsgid( String msgid ){
		this.msgid = msgid;
	}

	
	public Date getTimestamp(){
		return timestamp;
	}

	
	public void setTimestamp( Date timestamp ){
		this.timestamp = timestamp;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId( String userId ){
		this.userId = userId;
	}

	public String getAccept(){
		return accept;
	}

	public void setAccept( String accept ){
		this.accept = accept;
	}

	@Override
	public String toString(){
		return "HeaderRequestBean [idTransaccion=" + idTransaccion + ", msgid=" + msgid + ", timestamp=" + timestamp + ", userId=" + userId + ", accept=" + accept + "]";
	}

}
