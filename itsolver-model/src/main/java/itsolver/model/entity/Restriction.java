package itsolver.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringEscapeUtils;

@Entity
@Table(name="restriction")
public class Restriction implements Serializable{

	private static final long serialVersionUID = -2311936927194872872L;
	@Id  
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="restriction_id", nullable=false)	
	 private Long id;
	
	@OneToOne( fetch=FetchType.LAZY)
	 private Characteristic characteristic;
	
	@Column(name="value_type")
	 private String valueType;
	
	@Column(name="initial_value")
	 private String initialValue;

	@Column(name="final_value")
	 private String finalValue;
	
	//Se ocupo el prefijo str_ por que condition es palabra reservada en MySQL y ocurre un error cuando se hace el insert
	@Column(name="str_condition")
	 private String condition;
	
	@Column(name="description")
	 private String description;
	
	@Column(name = "is_single_value")
	private boolean isSingleValue = false;
	
	public String getFormattedMessage(){
		String strRestriction;
		if (this.isSingleValue()){
			strRestriction = this.getCharacteristic().getName() + ": Value " + this.getCondition() + " than " + this.getInitialValue();
		}else{
			strRestriction = this.getCharacteristic().getName() + " : " + this.getInitialValue() + " " + this.getCondition() + " than " + this.getFinalValue();
		}
		return strRestriction;
	}
	
	public String getFormattedMessageXML(){
		String strRestriction;
		if (this.isSingleValue()){
			strRestriction = this.getCharacteristic().getName() + " Value " + this.getCondition() + " than " + this.getInitialValue();
		}else{
			strRestriction = this.getCharacteristic().getName() + "  " + this.getInitialValue() + " " + this.getCondition() + " than " + this.getFinalValue();
		}
		String forXML = StringEscapeUtils.escapeXml(strRestriction);
		return forXML;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Characteristic getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(Characteristic characteristic) {
		this.characteristic = characteristic;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	public String getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(String finalValue) {
		this.finalValue = finalValue;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSingleValue() {
		return isSingleValue;
	}

	public void setSingleValue(boolean isSingleValue) {
		this.isSingleValue = isSingleValue;
	}
}
