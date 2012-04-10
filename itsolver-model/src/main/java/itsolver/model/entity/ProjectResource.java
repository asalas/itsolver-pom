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
import javax.persistence.Table;

@Entity  
@Table(name = "project_resource")  
public class ProjectResource {
	@Id  
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="project_resource_id", nullable=false)	
	 private Long id;
	
	
	 @Column(name="resource_category_id", nullable=false)	
	 private Long resourceCategoryId;
	 
	 @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 @org.hibernate.annotations.Cascade(
	    value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	 )
	 private List<SelectedResource> selectedResourceList;
	 
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResourceCategoryId() {
		return resourceCategoryId;
	}

	public void setResourceCategoryId(Long resourceCategoryId) {
		this.resourceCategoryId = resourceCategoryId;
	}

	public List<SelectedResource> getSelectedResourceList() {
		return selectedResourceList;
	}

	public void setSelectedResourceList(List<SelectedResource> selectedResourceList) {
		this.selectedResourceList = selectedResourceList;
	}
}
