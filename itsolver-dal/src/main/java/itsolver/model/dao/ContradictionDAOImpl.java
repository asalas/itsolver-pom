package itsolver.model.dao;

import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Contradiction;

import javax.persistence.NoResultException;

public class ContradictionDAOImpl extends GenericDAOImpl<Contradiction, Long> implements ContradictionDAO {
	
	public Contradiction getContradiction(Characteristic positive,Characteristic negative){
		Contradiction contradiction = null;
		try{
			contradiction	= (Contradiction) getEntityManager().createQuery(
			"FROM Contradiction WHERE positiveCharacteristic.id = :positiveCharacteristicId " +
			"					AND negativeCharacteristic.id = :negativeCharacteristicId")
			.setParameter("positiveCharacteristicId", positive.getId())
			.setParameter("negativeCharacteristicId", negative.getId())
			.getSingleResult();
		}catch (NoResultException e) {
			
		}
		return contradiction;
	}
}
