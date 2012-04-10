package itsolver.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.InventiveStandardGroup;

public class InventiveStandardGroupDAOImpl extends GenericDAOImpl<InventiveStandardGroup, String>
		implements InventiveStandardGroupDAO {

	@Override
	public InventiveStandardGroup findById(String id) {
		InventiveStandardGroup inventiveStandardGroup = null;
		
		try {
			Query query = getEntityManager().createQuery("FROM InventiveStandardGroup group WHERE groupNumber = :groupNumber");		
			query.setParameter("groupNumber", id);
			inventiveStandardGroup = (InventiveStandardGroup)query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return inventiveStandardGroup;
	}
}
