package pe.com.claro.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import pe.com.claro.common.property.Constantes;



@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property=Constantes.ID)
public class BaseEntity{

	private Long id;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=Constantes.ID)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}