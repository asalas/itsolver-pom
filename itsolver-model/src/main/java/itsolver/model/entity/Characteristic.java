package itsolver.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Representa a una caracteristica de la Matriz de contradicciones
 * @author rene
 *
 */
@Entity
@Table(name="characteristic")
public class Characteristic implements Serializable{	
	private static final long serialVersionUID = -952166333788854128L;

	@Id  
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="characteristic_id", nullable=false)	
	 private Long id;
	
	@Column(name="name")
	 private String name;
	
	@Column(name="description")
	 private String description;
	
	@Column(name="category")
	 private String category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getNameToXML(){
		return StringEscapeUtils.escapeXml(this.name);
	}
}
