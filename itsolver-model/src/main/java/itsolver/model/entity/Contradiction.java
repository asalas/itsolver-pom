package itsolver.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="contradiction")
public class Contradiction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="contradiction_id")
	private Long id;
	
	@OneToOne(cascade = { CascadeType.ALL })
	private Characteristic positiveCharacteristic ;
	
	@OneToOne(cascade = { CascadeType.ALL })
	private Characteristic negativeCharacteristic ;
	
	@OneToMany( cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<ContradictionPrinciple> contradictionPrinciple;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Characteristic getPositiveCharacteristic() {
		return positiveCharacteristic;
	}

	public void setPositiveCharacteristic(Characteristic positiveCharacteristic) {
		this.positiveCharacteristic = positiveCharacteristic;
	}

	public Characteristic getNegativeCharacteristic() {
		return negativeCharacteristic;
	}

	public void setNegativeCharacteristic(Characteristic negativeCharacteristic) {
		this.negativeCharacteristic = negativeCharacteristic;
	}

	public List<ContradictionPrinciple> getContradictionPrinciple() {
		return contradictionPrinciple;
	}

	public void setContradictionPrinciple(
			List<ContradictionPrinciple> contradictionPrinciple) {
		this.contradictionPrinciple = contradictionPrinciple;
	}	
	
}
