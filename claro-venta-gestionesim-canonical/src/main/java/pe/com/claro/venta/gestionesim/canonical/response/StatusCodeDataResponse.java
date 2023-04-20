package pe.com.claro.venta.gestionesim.canonical.response;

public class StatusCodeDataResponse {

	private String message;
	private String reasonCode;
	private String subjectCode;
	private String subjectIdentifier;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectIdentifier() {
		return subjectIdentifier;
	}
	public void setSubjectIdentifier(String subjectIdentifier) {
		this.subjectIdentifier = subjectIdentifier;
	}
	
}
