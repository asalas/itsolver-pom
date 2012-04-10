package itsolver.model.entity;

import java.io.Serializable;
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
 @Entity  
 @Table(name = "resource_category")  
public class ResourceCategory implements Serializable{
	
	private static final long serialVersionUID = 2691129195395980281L;

	@Id  
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="resource_category_id", nullable=false)	
	 private Long id;
	 
	 @Column(name="category_name")
	 private String categoryName;
	 
	 @ManyToOne
	 @JoinColumn(name="parent_category_id")
	 private ResourceCategory parentCategory;
	  
	 @OneToMany(mappedBy="parentCategory", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<ResourceCategory> subCategoryList;
	 
	 @OneToMany( cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<Resource> resourceList;
	 
	 public ResourceCategory() {
		
	}
	 
	 public ResourceCategory(String name) {
			this.categoryName = name;
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}	

	public void setSubCategoryList(List<ResourceCategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public List<ResourceCategory> getSubCategoryList() {
		return subCategoryList;
	}

	public void setParentCategory(ResourceCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public ResourceCategory getParentCategory() {
		return parentCategory;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}
	
}
