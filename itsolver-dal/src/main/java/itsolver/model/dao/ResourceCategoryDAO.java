package itsolver.model.dao;

import itsolver.model.entity.ResourceCategory;

public interface ResourceCategoryDAO extends GenericDAO<ResourceCategory, Long > {
	
	public ResourceCategory getResourceTree();

}
