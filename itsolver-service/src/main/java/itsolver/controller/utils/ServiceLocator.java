package itsolver.controller.utils;
import itsolver.service.AlgorithmStandardsService;
import itsolver.service.CollaborationMgmtService;
import itsolver.service.ContradictionMatrixService;
import itsolver.service.InvitationByEmailService;
import itsolver.service.ProfileMgmtService;
import itsolver.service.ProjectMgmtService;
import itsolver.service.SuFieldService;
import itsolver.service.UserService;
import itsolver.service.cbr.CbrService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLocator {
	

	private static ApplicationContext context;
	static{
		context = new ClassPathXmlApplicationContext("ApplicationContext.xml");		
	}
	
	public static UserService getUserService(){
		UserService myBean = (UserService) context.getBean("userService");
		return myBean;
	}
	
	public static ProjectMgmtService getProjectMgmtService(){
		ProjectMgmtService myBean = (ProjectMgmtService) context.getBean("projectMgmtService");
		return myBean;
	}
	
	public static CollaborationMgmtService getCollaborationMgmtService() {
		CollaborationMgmtService myBean = (CollaborationMgmtService) context.getBean("collaborationMgmtService");
		return myBean;
	}
	
	public static ProfileMgmtService getProfileMgmtService() {
		ProfileMgmtService myBean = (ProfileMgmtService) context.getBean("profileMgmtService");
		return myBean;
	}
	
	public static InvitationByEmailService getInvitationByEmailService() {
		InvitationByEmailService myBean = (InvitationByEmailService) context.getBean("invitationByEmailService");
		return myBean;
	}

	public static SuFieldService getSuFieldService() {
		SuFieldService myBean = (SuFieldService) context.getBean("suFieldService");
		return myBean;
	}
	public static CbrService getCbrService() {
		CbrService myBean = (CbrService) context.getBean("cbrService");
		return myBean;
	}
	public static ContradictionMatrixService getContradictionMatrixService() {
		ContradictionMatrixService myBean = (ContradictionMatrixService) context.getBean("contradictionMatrixService");
		return myBean;
	}
	
	public static AlgorithmStandardsService getAlgorithmStandardsService() {
		AlgorithmStandardsService myBean = (AlgorithmStandardsService) context.getBean("algorithmStandardsService");
		return myBean;
	}
}