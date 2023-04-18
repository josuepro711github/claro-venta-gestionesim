
package pe.com.claro.common.resource.exception;

public class NotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8992570382210399390L;
	private int code;
	private Integer status;
	String developerMessage;

	public NotFoundException(int code, String msg) {
		super(code, msg);
		this.code = code;
	}

	public NotFoundException(int status, int code, String msg, String developerMessage) {
		super(status, code, msg, developerMessage);
		this.status = status;
		this.code = code;
		this.developerMessage = developerMessage;
	}

	public NotFoundException(int code, String msg, String developerMessage) {
		super(code, msg, developerMessage);
		this.code = code;
		this.developerMessage = developerMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

}
