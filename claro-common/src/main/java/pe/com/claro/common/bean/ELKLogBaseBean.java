package pe.com.claro.common.bean;

public class ELKLogBaseBean {
	private String tipoAuditoria;
	private String correlativo;
	private String idTransaccional;
	private String fechaYHora;
	private String servicioPadre;
	private String canal;
	private long tiempoRespuesta;
	private String identificadorServicio;
	private int tipoMensaje;
	private String mensaje;
	private int tipoId;
	private String idfIdtCodigo;
	private String idfIdtMensaje;

	public String getTipoAuditoria() {
		return tipoAuditoria;
	}

	public void setTipoAuditoria(String tipoAuditoria) {
		this.tipoAuditoria = tipoAuditoria;
	}

	public String getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public String getIdTransaccional() {
		return idTransaccional;
	}

	public void setIdTransaccional(String idTransaccional) {
		this.idTransaccional = idTransaccional;
	}

	public String getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public String getServicioPadre() {
		return servicioPadre;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public void setServicioPadre(String servicioPadre) {
		this.servicioPadre = servicioPadre;
	}

	public long getTiempoRespuesta() {
		return tiempoRespuesta;
	}

	public void setTiempoRespuesta(long tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}

	public String getIdentificadorServicio() {
		return identificadorServicio;
	}

	public void setIdentificadorServicio(String identificadorServicio) {
		this.identificadorServicio = identificadorServicio;
	}

	public int getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(int tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getTipoId() {
		return tipoId;
	}

	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}

	public String getIdfIdtCodigo() {
		return idfIdtCodigo;
	}

	public void setIdfIdtCodigo(String idtIdfCodigo) {
		this.idfIdtCodigo = idtIdfCodigo;
	}

	public String getIdfIdtMensaje() {
		return idfIdtMensaje;
	}

	public void setIdfIdtMensaje(String idtIdfMensaje) {
		this.idfIdtMensaje = idtIdfMensaje;
	}
}
