package itsolver.model.dao;

import itsolver.model.entity.ResourceCategory;

import javax.persistence.NoResultException;

public class ResourceCategoryDAOImpl extends GenericDAOImpl<ResourceCategory, Long> implements
		ResourceCategoryDAO {
	
	public ResourceCategory getResourceTree(){
		ResourceCategory resourceCategory	= null;		
		try{
			resourceCategory	= (ResourceCategory) getEntityManager().createQuery(
			"FROM ResourceCategory WHERE parentCategory = null")
			.getSingleResult();
		}catch (NoResultException e) {
			
		}
		return resourceCategory;
	}

}
