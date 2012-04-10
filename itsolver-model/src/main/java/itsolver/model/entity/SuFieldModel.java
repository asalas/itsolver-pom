package itsolver.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="sufield_model")
public class SuFieldModel implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -4091249858422055740L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sufield_model_id", nullable=false)
	private Long id;
	
	@Type(type="text")	
	@Column(name="model_graph_ml")
	private String modelGraphML;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelGraphML() {
		return modelGraphML;
	}

	public void setModelGraphML(String modelGraphML) {
		this.modelGraphML = modelGraphML;
	}	
}