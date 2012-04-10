package itsolver.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.InventiveStandard;

public class InventiveStandardDAOImpl extends GenericDAOImpl<InventiveStandard, String> implements
		InventiveStandardDAO {

	@Override
	public InventiveStandard findById(String id) {
		InventiveStandard standard = null;
		
		try {
			Query query = getEntityManager().createQuery("FROM InventiveStandard WHERE inventiveStandardNumber = :inventiveStandardNumber");
			query.setParameter("inventiveStandardNumber", id);
			standard = (InventiveStandard)query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return standard;
	}
}
