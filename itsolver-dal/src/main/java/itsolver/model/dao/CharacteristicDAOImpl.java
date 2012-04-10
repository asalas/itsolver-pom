package itsolver.model.dao;

import itsolver.model.entity.Characteristic;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class CharacteristicDAOImpl extends GenericDAOImpl<Characteristic, Long> implements CharacteristicDAO {

	@SuppressWarnings("unchecked")
	public List<Characteristic> getCharacteristicByCategory(String category) {
		List<Characteristic> characteristicList = null;
		
		Query query = getEntityManager().createQuery("FROM Characteristic  WHERE category= :category");
		query.setParameter("category", category);
		
		try {
			characteristicList = (List<Characteristic>)query.getResultList();
		}catch(NoResultException e) {
			e.printStackTrace();			
		}		
		
		return characteristicList;
	}

}
