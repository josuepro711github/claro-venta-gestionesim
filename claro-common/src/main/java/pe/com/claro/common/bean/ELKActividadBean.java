package pe.com.claro.common.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ELKActividadBean<T> {
  private transient double numeroActividad;
  private transient T actividad;

  @XmlTransient
  public T getActividad() {
    return actividad;
  }

  public void setActividad(T actividad) {
	this.actividad = actividad;
  }

  @XmlTransient
  public double getNumeroActividad() {
    return numeroActividad;
  }

  public void setNumeroActividad(double numeroActividad) {
    this.numeroActividad = numeroActividad;
  }
}