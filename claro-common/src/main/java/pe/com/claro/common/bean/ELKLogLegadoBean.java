package pe.com.claro.common.bean;

import pe.com.claro.common.util.ELKUtil;

public class ELKLogLegadoBean extends ELKLogBaseBean {

	private int tipoActividad = ELKUtil.TIPO_ACTIVIDAD_PROCESO;
	private double numeroActividad;
	private String nombreActividad;
	private String detalleActividad;
	private int tipoConsulta;
	private String duenoConsulta;
	private String origenConsulta;
	private String detalleConsulta;

	public int getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(int tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public double getNumeroActividad() {
		return numeroActividad;
	}

	public void setNumeroActividad(double numeroActividad) {
		this.numeroActividad = numeroActividad;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDetalleActividad() {
		return detalleActividad;
	}

	public void setDetalleActividad(String detalleActividad) {
		this.detalleActividad = detalleActividad;
	}

	public int getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getDuenoConsulta() {
		return duenoConsulta;
	}

	public void setDuenoConsulta(String duenoConsulta) {
		this.duenoConsulta = duenoConsulta;
	}

	public String getOrigenConsulta() {
		return origenConsulta;
	}

	public void setOrigenConsulta(String origenConsulta) {
		this.origenConsulta = origenConsulta;
	}

	public String getDetalleConsulta() {
		return detalleConsulta;
	}

	public void setDetalleConsulta(String detalleConsulta) {
		this.detalleConsulta = detalleConsulta;
	}

}
