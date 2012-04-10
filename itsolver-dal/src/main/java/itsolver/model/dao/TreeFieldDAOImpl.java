package itsolver.model.dao;

import javax.persistence.Query;

import itsolver.model.entity.TreeField;

public class TreeFieldDAOImpl extends GenericDAOImpl<TreeField, Long> implements
		TreeFieldDAO {

	public TreeField getTreeField() {
		TreeField treeField = null;
		
		try {
			Query query = getEntityManager().createQuery("FROM TreeField WHERE parentField = null");
			treeField = (TreeField)query.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return treeField;
	}	
}