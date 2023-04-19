package pe.com.claro.venta.gestionesim.canonical.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import pe.com.claro.common.bean.AuditRequest;
import pe.com.claro.common.bean.ListaRequestOpcional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservarCodigoRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private AuditRequest auditRequest;
	
	private List<ListaRequestOpcional> listaRequestOpcional;
	 
	public AuditRequest getAuditRequest() {
		return auditRequest;
	}

	public void setAuditRequest(AuditRequest auditRequest) {
		this.auditRequest = auditRequest;
	}

	public List<ListaRequestOpcional> getListaAdicionalRequest() {
		return listaRequestOpcional;
	}

	public void setListaAdicionalRequest(List<ListaRequestOpcional> listaAdicionalRequest) {
		this.listaRequestOpcional = listaAdicionalRequest;
	}
}