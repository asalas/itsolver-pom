package itsolver.service;

import itsolver.model.dao.DashboardDAO;
import itsolver.model.dao.InvitationDAO;
import itsolver.model.entity.Dashboard;
import itsolver.model.entity.Invitation;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;

import java.util.List;

public class CollaborationMgmtServiceImpl implements CollaborationMgmtService {
	
	private DashboardDAO dashboardDAO;
	
	private InvitationDAO invitationDAO;
	
	
	public void updateDashboard(Dashboard dashboard){
		dashboardDAO.merge(dashboard);
	}
	
	public void refreshDashboard(Dashboard dashboard){
		dashboardDAO.refresh(dashboard);
	}
	
	public boolean persistInvitation(Invitation invitation) {
		invitationDAO.persist(invitation);		
		if(invitation.getId() != null && invitation.getId() > 0){
			return true;
		}
		return false;
	}
	
	
	public List<Invitation> getSentInvitationsByProfile(Profile profile) {		
		List<Invitation> sentInvitationsByProfile = invitationDAO.getSentInvitationsByProfile(profile);		
		return sentInvitationsByProfile;
	}
	
	
	public List<Invitation> getReceivedInvitationsByProfile(Profile profile) {
		List<Invitation> receivedInvitationsByProfile = invitationDAO.getReceivedInvitationsByProfile(profile);
		return receivedInvitationsByProfile;
	}
	
	
	public void mergeInvitation(Invitation invitation) {		
		invitationDAO.merge(invitation);
	}
	
	
	public List<Invitation> getInvitationsByProject(Project project) {
		List<Invitation> invitationsByProject = invitationDAO.getInvitationsListByProject(project);
		return invitationsByProject;
	}
	
	
	public void removeProjectInvitation(Profile profile, Project project) {
		Invitation invitation = invitationDAO.getInvitationByProject(project, profile);
		invitationDAO.remove(invitation);
	}


	public DashboardDAO getDashboardDAO() {
		return dashboardDAO;
	}

	public void setDashboardDAO(DashboardDAO dashboardDAO) {
		this.dashboardDAO = dashboardDAO;
	}
	public InvitationDAO getInvitationDAO() {
		return invitationDAO;
	}
	public void setInvitationDAO(InvitationDAO invitationDAO) {
		this.invitationDAO = invitationDAO;
	}

}
