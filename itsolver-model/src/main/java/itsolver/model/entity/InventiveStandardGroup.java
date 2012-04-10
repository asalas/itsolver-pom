package itsolver.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="inventive_standard_group")
@XmlRootElement(name="standard_group")
public class InventiveStandardGroup implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -2780337397697917403L;
	
	@Id
	@Column(name="group_number")
	private String groupNumber;
	
	@Column(name="group_name")
	private String name;
	
	@ManyToOne(targetEntity = InventiveStandardClass.class)
	@JoinColumn(name = "class_number", nullable = false)
	private InventiveStandardClass classification;
	
	@OneToMany(mappedBy="group", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<InventiveStandard> standardsList;	

	@XmlAttribute(name="label")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public InventiveStandardClass getClassification() {
		return classification;
	}

	public void setClassification(InventiveStandardClass classification) {
		this.classification = classification;
	}
	
	@XmlElements(
			@XmlElement(name="standard", type=InventiveStandard.class)
	)
	public List<InventiveStandard> getStandardsList() {
		return standardsList;
	}

	public void setStandardsList(List<InventiveStandard> standardsList) {
		this.standardsList = standardsList;
	}

	@XmlAttribute(name="group_number")
	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

}
