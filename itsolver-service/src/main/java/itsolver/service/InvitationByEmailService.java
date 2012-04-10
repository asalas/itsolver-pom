package itsolver.service;

import itsolver.model.entity.InvitationByEmail;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;

public interface InvitationByEmailService {

	/**
	 * Envia la invitacion via email
	 *  
	 * @param recipients
	 * @param message
	 * @param profileHost
	 * @param project
	 */
	public void sendMailInvitations(String recipients, String message, Profile profileHost, Project project) throws Exception;
	
	public InvitationByEmail findByHiloId(String hiloId);
	
	public void mergeInvitationByEmail(InvitationByEmail invitationByEmail);
	
}