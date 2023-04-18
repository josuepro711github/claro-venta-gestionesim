package pe.com.claro.common.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AuditRequest {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected String idTransaccion;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected String nombreAplicacion;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected String ipAplicacion;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected String usuarioAplicacion;
	
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}
	public String getIpAplicacion() {
		return ipAplicacion;
	}
	public void setIpAplicacion(String ipAplicacion) {
		this.ipAplicacion = ipAplicacion;
	}
	public String getUsuarioAplicacion() {
		return usuarioAplicacion;
	}
	public void setUsuarioAplicacion(String usuarioAplicacion) {
		this.usuarioAplicacion = usuarioAplicacion;
	}	
}
