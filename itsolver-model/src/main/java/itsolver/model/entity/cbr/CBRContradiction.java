package itsolver.model.entity.cbr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

@Entity
@Table(name="cbr_contradiction")
public class CBRContradiction implements CaseComponent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cbr_contradiction_id", nullable=false)	
	private Long id;
	
	@Column(name="positive_id")
	private Long positiveCharacteristicId;
	
	@Column(name="negative_id")
	private Long negativeCharacteristicId;
	
	
	public Attribute getIdAttribute() {
		return new Attribute("id", this.getClass());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPositiveCharacteristicId() {
		return positiveCharacteristicId;
	}

	public void setPositiveCharacteristicId(Long positiveCharacteristicId) {
		this.positiveCharacteristicId = positiveCharacteristicId;
	}

	public Long getNegativeCharacteristicId() {
		return negativeCharacteristicId;
	}

	public void setNegativeCharacteristicId(Long negativeCharacteristicId) {
		this.negativeCharacteristicId = negativeCharacteristicId;
	}

}
