package pe.com.claro.venta.gestionesim.canonical.request;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import pe.com.claro.common.bean.AuditRequest;
import pe.com.claro.common.bean.ListaRequestOpcional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservarCodigoRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private AuditRequest auditRequest;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String status;
	private ListaRequestOpcional listaAdicionalRequest;
	 
	public AuditRequest getAuditRequest() {
		return auditRequest;
	}

	public void setAuditRequest(AuditRequest auditRequest) {
		this.auditRequest = auditRequest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ListaRequestOpcional getListaAdicionalRequest() {
		return listaAdicionalRequest;
	}

	public void setListaAdicionalRequest(ListaRequestOpcional listaAdicionalRequest) {
		this.listaAdicionalRequest = listaAdicionalRequest;
	}	
}