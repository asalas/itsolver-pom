package itsolver.model.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="tree_field")
@XmlRootElement(name="treefield")
public class TreeField implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5298795112516559679L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tree_field_id")
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="parent_field_id")	
	private TreeField parentField;
	
	@OneToMany(mappedBy="parentField", cascade=CascadeType.ALL, fetch=FetchType.LAZY)	
	private List<TreeField> subFields = new LinkedList<TreeField>();	

	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="subfields")
	public List<TreeField> getSubFields() {
		return subFields;
	}

	public void setSubFields(List<TreeField> subCategories) {
		this.subFields = subCategories;
	}

	@XmlTransient
	public TreeField getParentField() {
		return parentField;
	}

	public void setParentField(TreeField parentField) {
		this.parentField = parentField;
	}

}
