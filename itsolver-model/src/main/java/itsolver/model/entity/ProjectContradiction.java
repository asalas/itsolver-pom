package itsolver.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="project_contradiction")
public class ProjectContradiction implements Serializable{

	private static final long serialVersionUID = -3241804857956558366L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="project_contradiction_id")
	private Long id;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
	//@JoinColumn(name="characteristic_id")
	private Characteristic positiveCharacteristic ;
	
	@Column(name="positive_description")
	private String positiveDescription;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
	//@JoinColumn(name="characteristic_id")
	private Characteristic negativeCharacteristic ;
	
	@Column(name="negative_description")
	private String negativeDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getPositiveDescription() {
		return positiveDescription;
	}

	public void setPositiveDescription(String positiveDescription) {
		this.positiveDescription = positiveDescription;
	}

	public Characteristic getNegativeCharacteristic() {
		return negativeCharacteristic;
	}

	public void setNegativeCharacteristic(Characteristic negativeCharacteristic) {
		this.negativeCharacteristic = negativeCharacteristic;
	}

	public String getNegativeDescription() {
		return negativeDescription;
	}

	public void setNegativeDescription(String negativeDescription) {
		this.negativeDescription = negativeDescription;
	}

	public Characteristic getPositiveCharacteristic() {
		return positiveCharacteristic;
	}

	public void setPositiveCharacteristic(Characteristic positiveCharacteristic) {
		this.positiveCharacteristic = positiveCharacteristic;
	}
	
}
