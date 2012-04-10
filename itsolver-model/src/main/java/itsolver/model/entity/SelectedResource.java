package itsolver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  
@Table(name = "selected_resource")
public class SelectedResource {
	
	@Id  
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="selected_resource_id", nullable=false)	
	 private Long id;
	
	@OneToOne( fetch=FetchType.LAZY)
	 private Resource resource;
	
	@Column(name="selected_value")
	private String selectedValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

}
