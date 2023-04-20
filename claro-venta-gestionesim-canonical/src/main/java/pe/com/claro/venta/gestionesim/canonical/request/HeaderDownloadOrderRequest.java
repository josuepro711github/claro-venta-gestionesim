package pe.com.claro.venta.gestionesim.canonical.request;

public class HeaderDownloadOrderRequest {

	private String functionCallIdentifier;
	private String functionRequesterIdentifier;
	
	public String getFunctionCallIdentifier() {
		return functionCallIdentifier;
	}
	public void setFunctionCallIdentifier(String functionCallIdentifier) {
		this.functionCallIdentifier = functionCallIdentifier;
	}
	public String getFunctionRequesterIdentifier() {
		return functionRequesterIdentifier;
	}
	public void setFunctionRequesterIdentifier(String functionRequesterIdentifier) {
		this.functionRequesterIdentifier = functionRequesterIdentifier;
	}
	
}
