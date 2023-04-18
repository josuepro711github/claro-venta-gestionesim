package pe.com.claro.venta.gestionesim.canonical.request;

public class ActualizarEstadoRequest {
	private String codserie;
	private String status;
	
	public String getCodserie() {
		return codserie;
	}
	public void setCodserie(String codserie) {
		this.codserie = codserie;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
