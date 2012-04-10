package itsolver.model.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.Project;


public class ProjectDAOImpl extends GenericDAOImpl<Project, Long> implements ProjectDAO{

	public List<Project> findProjectsByProfileId(long profileId) {
		
		List<Project> projectList = null;
		
		Query query = getEntityManager().createQuery("FROM Project pr WHERE pr.profile.Id = :profileId ORDER BY pr.createdOn DESC");
		query.setParameter("profileId", profileId);
		
		try {
			projectList = (List<Project>)query.getResultList();
		}catch(NoResultException e) {
			e.printStackTrace();			
		}		
		
		return projectList;
	}
	
}
