package itsolver.service;

import java.util.List;

import itsolver.model.entity.Dashboard;
import itsolver.model.entity.Invitation;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;

public interface CollaborationMgmtService {
	public void updateDashboard(Dashboard dashboard);
	public void refreshDashboard(Dashboard dashboard);
	
	// invitations
	public boolean persistInvitation(Invitation invitation);	
	public List<Invitation> getSentInvitationsByProfile(Profile profile);	
	public List<Invitation> getReceivedInvitationsByProfile(Profile profile);	
	public void mergeInvitation(Invitation invitation);	
	public List<Invitation> getInvitationsByProject(Project project);
	
	public void removeProjectInvitation(Profile profile, Project project);
}
