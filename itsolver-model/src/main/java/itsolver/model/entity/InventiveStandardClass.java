package itsolver.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="inventive_standard_classification")
@XmlRootElement
public class InventiveStandardClass implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1759345229712783783L;

	@Id
	@Column(name="class_number")
	private String classificationNumber;
	
	@Column(name="class_name")
	private String name;
	
	@OneToMany(mappedBy="classification", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<InventiveStandardGroup> groupsList;	
	
	@XmlAttribute(name="label")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElements(
			@XmlElement(name="standard_group", type=InventiveStandardGroup.class)
	)
	public List<InventiveStandardGroup> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(List<InventiveStandardGroup> groupsList) {
		this.groupsList = groupsList;
	}

	@XmlAttribute(name="class_number")
	public String getClassificationNumber() {
		return classificationNumber;
	}

	public void setClassificationNumber(String classificationNumber) {
		this.classificationNumber = classificationNumber;
	}	
}