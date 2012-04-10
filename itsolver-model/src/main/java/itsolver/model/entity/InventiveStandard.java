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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;

@Entity
@Table(name="inventive_standard")
@XmlRootElement(name="standard")
public class InventiveStandard implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 6230288914529743037L;
	
	@Id	
	@Column(name = "standard_number", nullable = false)
	private String inventiveStandardNumber;
	
	@ManyToOne(targetEntity = InventiveStandardGroup.class)
	@JoinColumn(name = "group_name", nullable = false)
	private InventiveStandardGroup group;	
	
	@Type(type="text")
	@Column(name="description")
	private String description;
	
	@Type(type="text")
	@Column(name="image_description")
	private String imageDesc;
	
	@Column(name="image_url")
	private String imageURL;
	
	@OneToMany(mappedBy="inventiveStandard", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<SuField> suFieldList;
	
	@XmlAttribute(name="label")
	public String getInventiveStandardNumber() {
		return inventiveStandardNumber;
	}

	public void setInventiveStandardNumber(String inventiveStandardNumber) {
		this.inventiveStandardNumber = inventiveStandardNumber;
	}	
	
	@XmlAttribute(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute(name="image_url")
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@XmlTransient
	public InventiveStandardGroup getGroup() {
		return group;
	}

	public void setGroup(InventiveStandardGroup group) {
		this.group = group;
	}

	@XmlAttribute(name="image_desc")
	public String getImageDesc() {
		return imageDesc;
	}

	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}

	@XmlTransient
	public List<SuField> getSuFieldList() {
		return suFieldList;
	}

	public void setSuFieldList(List<SuField> suFieldList) {
		this.suFieldList = suFieldList;
	}

	@Transient
	@XmlAttribute(name="standard_number")
	public String getStandardNumber() {
		return inventiveStandardNumber;
	}
	
	@Transient
	public boolean equals(Object obj) {
		if(obj instanceof InventiveStandard) {
			InventiveStandard inventiveStandard = (InventiveStandard)obj;
			if(inventiveStandard.getStandardNumber().equals(this.getStandardNumber())) {
				return true;
			}
		}

		return false;
	}
}