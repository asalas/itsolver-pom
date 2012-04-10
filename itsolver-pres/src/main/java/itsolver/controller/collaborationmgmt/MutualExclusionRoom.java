package itsolver.controller.collaborationmgmt;

import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;

import java.util.Collection;
import java.util.LinkedList;


public class MutualExclusionRoom {
	
	private Collection<Collaborator> collaborators;
	
	public MutualExclusionRoom() {
		collaborators = new LinkedList<Collaborator>();
	}
	
	public void subscribe(Collaborator collaborator) {
		synchronized(collaborators) {
			collaborators.add(collaborator);
		}
	}
	
	public void unsubscribe(Collaborator collaborator) {
		collaborators.remove(collaborator);
	}
	
	public Collaborator findCollaboratorByProject(Project project) {
		synchronized(collaborators) {			
			for(Collaborator collaborator: collaborators) {
				Project proj = collaborator.getProject();
				if(project.equals(proj)) {
					return collaborator;
				}
			}
		}
		
		return null;
	}
	
	public void removeCollaboratorByProfile(Profile profile) {
		Collaborator markedToBeDeleted = null;
		synchronized(collaborators) {
			for(Collaborator collaborator: collaborators) {
				Profile collaboratorProfile = collaborator.getProfile();
				if(collaboratorProfile.equals(profile)) {
					collaborator.setDone();
					markedToBeDeleted = collaborator;
					break;
				}
			}
			
			if(markedToBeDeleted!=null) {
				collaborators.remove(markedToBeDeleted);
			}		
		}		
	}
	
}
