package itsolver.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.InventiveStandardClass;

public class InventiveStandardClassDAOImpl extends GenericDAOImpl<InventiveStandardClass, String>
		implements InventiveStandardClassDAO {

	@Override
	public InventiveStandardClass findById(String id) {
		InventiveStandardClass inventiveStandardClass = null;
		
		try {
			Query query = getEntityManager().createQuery("FROM InventiveStandardClass WHERE " +
															"classificationNumber = :classificationNumber");
			query.setParameter("classificationNumber", id);
			inventiveStandardClass = (InventiveStandardClass)query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return inventiveStandardClass;
	}

}
