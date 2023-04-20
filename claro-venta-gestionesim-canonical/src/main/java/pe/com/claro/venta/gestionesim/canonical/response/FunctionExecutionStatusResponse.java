package pe.com.claro.venta.gestionesim.canonical.response;

public class FunctionExecutionStatusResponse extends StatusCodeDataResponse {
	
	private String status;
	
	private StatusCodeDataResponse statusCodeDataResponse;

	public StatusCodeDataResponse getStatusCodeDataResponse() {
		return statusCodeDataResponse;
	}

	public void setStatusCodeDataResponse(StatusCodeDataResponse statusCodeDataResponse) {
		this.statusCodeDataResponse = statusCodeDataResponse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
