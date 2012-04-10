package itsolver.controller.collaborationmgmt;

import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;

public class MutualExclusionControl {

	private static Desktop desktop;	
	private static MutualExclusionRoom mutualExclusionRoom;	
	private static Collaborator collaborator;
	
	public static void start(Profile profile) throws Exception {
		desktop = Executions.getCurrent().getDesktop();
		
		mutualExclusionRoom = (MutualExclusionRoom) desktop.getWebApp().getAttribute("mutualExclusionRoom");
		if(mutualExclusionRoom == null) {
			mutualExclusionRoom = new MutualExclusionRoom();
			desktop.getWebApp().setAttribute("mutualExclusionRoom", mutualExclusionRoom);
		}else {
			// eliminar el perfil del usuario conectado del cuarto de exclusiones! n_n lol!
			mutualExclusionRoom.removeCollaboratorByProfile(profile);
		}
	}
	
	public static Collaborator onEditProject(Project project, Profile profile)throws Exception {
		
		Collaborator foundCollaborator = mutualExclusionRoom.findCollaboratorByProject(project);
		
		if(foundCollaborator == null) {
			collaborator = new Collaborator(mutualExclusionRoom, project, profile);
			collaborator.start();
		}
		
		return foundCollaborator;		
	}
	
	
	public static void onExit() {		
		collaborator.setDone();								
	}
	
	public static Collaborator getCurrentCollaborator() {
		return collaborator;
	}
	
	public static void increaseTimeout(long timeout) {
		collaborator.increaseTimeout(timeout);
	}
}
