package itsolver.model.dao;

import java.util.List;

import itsolver.model.entity.Invitation;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;

public interface InvitationDAO extends GenericDAO<Invitation, Long> {

	public List<Invitation> getSentInvitationsByProfile(Profile profile);
	
	public List<Invitation> getReceivedInvitationsByProfile(Profile profile);
	
	public List<Invitation> getInvitationsListByProject(Project project);
	
	public Invitation getInvitationByProject(Project project, Profile profile);
}
