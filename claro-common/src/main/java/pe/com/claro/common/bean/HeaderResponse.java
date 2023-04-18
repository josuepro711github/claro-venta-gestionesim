package pe.com.claro.common.bean;

public class HeaderResponse {
	
	private String idTransaccion;
	
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	private String versionHttp;
	private String contentType;
	private Integer statusCode;
	
	public String getVersionHttp() {
		return versionHttp;
	}
	public void setVersionHttp(String versionHttp) {
		this.versionHttp = versionHttp;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
