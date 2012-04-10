package itsolver.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.AlgorithmQuestion;

public class AlgorithmQuestionDAOImpl extends GenericDAOImpl<AlgorithmQuestion, String> implements
		AlgorithmQuestionDAO {

	@Override
	public AlgorithmQuestion findById(String id) {
		AlgorithmQuestion question = null;
		
		try {
			Query query = getEntityManager().createQuery("FROM AlgorithmQuestion WHERE questionNumber = :questionNumber");
			query.setParameter("questionNumber", id);
			question=(AlgorithmQuestion)query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return question;
	}
	
}
