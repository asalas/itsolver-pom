package itsolver.service.aop;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Dashboard;
import itsolver.model.entity.Invitation;
import itsolver.model.entity.Notification;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.model.entity.User;
import itsolver.service.CollaborationMgmtService;
import itsolver.utils.NotificationType;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class NotificationAspect {
	private String  userName;//For user authenticated
	private Profile profile;//For user authenticated
	private CollaborationMgmtService collaborationMgmtService;
		
	@AfterReturning(
			pointcut= "execution(* itsolver.service.UserService.validateUser(..))", 
			returning= "userLogged")
	public void doAccessCheck(Object userLogged) {
		User user = (User)userLogged;
		if (userLogged!= null){
			this.userName 	= user.getUserName();
			this.profile	= user.getProfile();
			this.collaborationMgmtService = ServiceLocator.getCollaborationMgmtService();
		}
	}
	@After(
			value= "execution(* itsolver.service.UserService.registerUser(..))&& args(userCreated)",
			argNames="userCreated")
	public void doSignUpCheck(Object userCreated) {		
		User user = (User)userCreated;
		if (user != null && user.getId() > 0){
			this.userName 	= user.getUserName();
			this.profile	= user.getProfile();
			this.collaborationMgmtService = ServiceLocator.getCollaborationMgmtService();
		}
	}
	
	
	@After(value= "execution(* itsolver.service.ProjectMgmtService.projectCreate(..)) && args(project)", 
			argNames= "project" )
	public void projectCreationNotification(Object project) {
		Project project_ = (Project)project;
		if (project_!= null){
			updateDashboard(project_);			
		}
	}
	
	@After(value= "execution(* itsolver.service.ProjectMgmtService.projectUpdate(..)) && args(project)", 
			argNames= "project" )
	public void projectEditionNotification(Object _project) {
		Project project = (Project)_project;
		if (project!= null){
			//Si son iguales quiere decir que el mismo que creo el projecto es el que lo esta editando
			if ( project.getProfile().getId().equals(profile.getId()) ){
				projectEditionUpdateDashboard(project);
			}else{//Se trata de una colaboracion o contribucion
				collaborationUpdateDashboard(project);
			}						
		}
	}
	
	@After(value= "execution(* itsolver.service.CollaborationMgmtService.persistInvitation(..)) && args(_invitation)", 
			argNames= "_invitation" )
	public void invitationPersisted(Object _invitation) {
		Invitation invitation = (Invitation)_invitation;
		if (invitation!= null){
			updateDashboard(invitation);			
		}
	}
	
	private void updateDashboard(Project project){		
		Dashboard dashboard = this.profile.getDashboard();
		Notification notification = new Notification(NotificationType.PROJECT_CREATED);
		notification.setDescription("Project creation");
		notification.setFullName(this.profile.getFullName());
		notification.setProjectName(project.getProjectName());
		dashboard.getNotificationList().add(notification);
		
		this.collaborationMgmtService.updateDashboard(dashboard);
		//TODO agregar el resumen del proyecto brief description
		//notification.setDescription(project.getP)
		
	}
	
	private void projectEditionUpdateDashboard(Project project){		
		Dashboard dashboard = this.profile.getDashboard();
		Notification notification = new Notification(NotificationType.PROJECT_EDITED);
		notification.setDescription("Project edition");
		notification.setFullName(this.profile.getFullName());
		notification.setProjectName(project.getProjectName());
		dashboard.getNotificationList().add(notification);
		
		this.collaborationMgmtService.updateDashboard(dashboard);
		//TODO agregar el resumen del proyecto brief description
		//notification.setDescription(project.getP)
		
	}
	
	private void collaborationUpdateDashboard(Project project){		
		Dashboard dashboard = this.profile.getDashboard();
		Notification notification = new Notification(NotificationType.COLLABORATION_DONE);
		notification.setDescription("Project collaboration done");
		notification.setFullName(this.profile.getFullName());
		notification.setProjectName(project.getProjectName());
		dashboard.getNotificationList().add(notification);	
		this.collaborationMgmtService.updateDashboard(dashboard);
		
		dashboard = project.getProfile().getDashboard();
		notification = new Notification(NotificationType.COLLABORATION_DONE);
		notification.setDescription("Project collaboration done");
		notification.setFullName(this.profile.getFullName());
		notification.setProjectName(project.getProjectName());
		dashboard.getNotificationList().add(notification);	
		this.collaborationMgmtService.updateDashboard(dashboard);
		
		for (Invitation invitation : project.getInvitationList()) {
			if (invitation.isAccepted()){
				if ( invitation.getSentToProfile().getId().equals(profile.getId()) ){
					continue;
				}else{
					dashboard = invitation.getSentToProfile().getDashboard();
					notification = new Notification(NotificationType.COLLABORATION_DONE);
					notification.setDescription("Project collaboration done");
					notification.setFullName(this.profile.getFullName());
					notification.setProjectName(project.getProjectName());
					dashboard.getNotificationList().add(notification);	
					this.collaborationMgmtService.updateDashboard(dashboard);
				}
			}
		}
		//TODO agregar el resumen del proyecto brief description
		//notification.setDescription(project.getP)
		
	}
	
	private void updateDashboard(Invitation invitation){		
		Dashboard dashboard = this.profile.getDashboard();
		Notification notification = new Notification(NotificationType.INVITATION_TO_COLABORATE);
		notification.setFullName(this.profile.getFullName());
		notification.setDescription("Sent invitation to colaborate to user " + invitation.getSentToProfile().getFullName());
		notification.setProjectName(invitation.getProject().getProjectName());
		dashboard.getNotificationList().add(notification);
		this.collaborationMgmtService.updateDashboard(dashboard);
		
		dashboard = invitation.getSentToProfile().getDashboard();
		notification = new Notification(NotificationType.INVITATION_RECEIVED);
		notification.setFullName(this.profile.getFullName());
		notification.setDescription("Invitation to colaborate by user "+ profile.getFullName() +" received");
		notification.setProjectName(invitation.getProject().getProjectName());
		dashboard.getNotificationList().add(notification);
		
		this.collaborationMgmtService.updateDashboard(dashboard);
		//TODO agregar el resumen del proyecto brief description
		//notification.setDescription(project.getP)
		
	}

}
