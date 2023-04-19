package pe.com.claro.venta.gestionesim.canonical.response;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.bean.ListaResponseOpcional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservarCodigoResponse extends BodyResponse implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String codserie;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String codmaterial;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String codinterlocutor;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String status;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String nroentrega;
	private List<ListaResponseOpcional> listaResponseOpcional;
	
	public String getCodserie() {
		return codserie;
	}
	public void setCodserie(String codserie) {
		this.codserie = codserie;
	}
	public String getCodmaterial() {
		return codmaterial;
	}
	public void setCodmaterial(String codmaterial) {
		this.codmaterial = codmaterial;
	}
	public String getCodinterlocutor() {
		return codinterlocutor;
	}
	public void setCodinterlocutor(String codinterlocutor) {
		this.codinterlocutor = codinterlocutor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNroentrega() {
		return nroentrega;
	}
	public void setNroentrega(String nroentrega) {
		this.nroentrega = nroentrega;
	}
	public List<ListaResponseOpcional> getListaResponseOpcional() {
		return listaResponseOpcional;
	}
	public void setListaResponseOpcional(List<ListaResponseOpcional> listaResponseOpcional) {
		this.listaResponseOpcional = listaResponseOpcional;
	}
	}	

