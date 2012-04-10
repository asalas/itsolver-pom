package itsolver.model.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.Invitation;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;

public class InvitationDAOImpl extends GenericDAOImpl<Invitation, Long> implements
		InvitationDAO {

	public List<Invitation> getSentInvitationsByProfile(Profile profile) {
		
		List<Invitation> invitationList = null;
		
		if(profile!=null) {
			Long profileId = profile.getId();
			if(profileId!=null) {				
				
				Query query = getEntityManager().createQuery("FROM Invitation inv WHERE sentByProfile.id = :profileId");
				query.setParameter("profileId", profileId);
				
				try {
					invitationList = (List<Invitation>)query.getResultList();
				}catch(NoResultException e) {
					e.printStackTrace();
				}				
			}
		}
		
		return invitationList;
	}
	
	public List<Invitation> getReceivedInvitationsByProfile(Profile profile) {
		
		List<Invitation> invitationList = null;
		
		if(profile!=null) {
			Long profileId = profile.getId();
			if(profileId!=null) {				
				
				Query query = getEntityManager().createQuery("FROM Invitation inv WHERE sentToProfile.id = :profileId");
				query.setParameter("profileId", profileId);
				
				try {
					invitationList = (List<Invitation>)query.getResultList();
				}catch(NoResultException e) {
					e.printStackTrace();
				}				
			}
		}
		
		return invitationList;
	}
	
	public List<Invitation> getInvitationsListByProject(Project project) {
		
		List<Invitation> invitationList = null;
		
		if(project!=null) {
			Long projectId = project.getId();
			if(projectId!=null) {
				
				Query query = getEntityManager().createQuery("FROM Invitation inv WHERE inv.project.Id = :projectId");
				query.setParameter("projectId", projectId);
				
				try {
					invitationList = (List<Invitation>)query.getResultList();
				}catch(NoResultException e) {
					e.printStackTrace();
				}
			}
		}
		
		return invitationList;
	}
	
	public Invitation getInvitationByProject(Project project, Profile profile) {
		
		Invitation invitation = null;
		
		Query query = getEntityManager().createQuery("FROM Invitation inv WHERE inv.project.Id = :projectId " +
				"AND inv.sentToProfile.Id = :profileId");
		query.setParameter("projectId", project.getId());
		query.setParameter("profileId", profile.getId());
		
		try{
			invitation = (Invitation) query.getSingleResult();
		}catch(NoResultException ex) {
			ex.printStackTrace();
		}
		
		return invitation;
	}

}
