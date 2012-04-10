package itsolver.model.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.PictureSolution;

public class PictureSolutionDAOImpl extends GenericDAOImpl<PictureSolution, Long> implements
		PictureSolutionDAO {

	public List<PictureSolution> getPicturesSolutionByProjId(Long projSolutionId) {
		
		List<PictureSolution> pictures = null;
		
		if(projSolutionId!=null) {
			Query query = getEntityManager().createQuery("FROM PictureSolution picSolution WHERE " +
															"picSolution.projectSolution.id = :projId");			
			query.setParameter("projId", projSolutionId);
			
			try {
				pictures = (List<PictureSolution>)query.getResultList();
			} catch (NoResultException e) {
				e.printStackTrace();
			}
		}
		
		return pictures;
	}

}
