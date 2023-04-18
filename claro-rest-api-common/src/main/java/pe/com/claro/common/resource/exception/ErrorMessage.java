package pe.com.claro.common.resource.exception;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;

@XmlRootElement
public class ErrorMessage {
	int status;
	int code;
	String message;
	String developerMessage;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public ErrorMessage(ApiException ex) {
		try {
			BeanUtils.copyProperties(this, ex);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public ErrorMessage(NotFoundException ex) {
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.code = ex.getCode();
		this.developerMessage = ex.getDeveloperMessage();
		this.message = ex.getMessage();
	}

	public ErrorMessage(BadRequestException ex) {
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.code = ex.getCode();
		this.developerMessage = ex.getDeveloperMessage();
		this.message = ex.getMessage();
	}

	public ErrorMessage(BadUsageException ex) {
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.code = ex.getCode();
		this.developerMessage = ex.getDeveloperMessage();
		this.message = ex.getMessage();
	}

	public ErrorMessage() {
	}
}
