package itsolver.model.dao;

import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.utils.CBRCaseType;

import java.util.List;

import javax.persistence.Query;



public class CbrDAOImpl extends GenericDAOImpl<CBRCaseEntity, Long> implements
		CbrDAO {
	
	@SuppressWarnings("unchecked")
	public List<CBRCaseEntity> findAllCBRCaseByType(CBRCaseType caseType) {		
		Query createQuery = super.getEntityManager().createQuery("from CBRCaseEntity caseBase"+ 
				" WHERE ( caseBase.cbrCaseType  = :caseType) ")
				.setParameter("caseType", caseType);
		List<CBRCaseEntity> resultList	 = null;
		try{
			resultList = createQuery.getResultList();
		}catch (IllegalStateException e) {
			logger.info("error" + e.getMessage());
		}catch (Exception e) {
			logger.info("error" + e.getMessage());
		}
		return resultList;
	}	
}
