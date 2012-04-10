package itsolver.controller.projectmgmt;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Invitation;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.model.entity.User;
import itsolver.service.CollaborationMgmtService;
import itsolver.service.InvitationByEmailService;
import itsolver.service.UserService;
import itsolver.utils.CurrentSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Textbox;

public class InvitationForm extends GenericZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -9066105416246331858L;
	
	public Textbox txtUserName;	
	public Textbox txtMessageInvitation;	
	public Window winInvitationForm;
	
	private Profile profile;
	private Project project;
	
	private UserService userService;	
	private CollaborationMgmtService collaborationMgmtService;
	private InvitationByEmailService invitationByEmailService;
	
	private List<Profile> collaboratorList = new ArrayList<Profile>();
	private Profile selectedCollaborator;
	
	public Textbox txtEmailAddresses;
	public Textbox txtCustomEmailMessage;
	
	public Listbox collaboratorListBox;
	
	//autocomplete		
	public Combobox cmbUsers;
	
	// cambio de idioma
	public Caption capInvForm;
	
	public Tab tabInvByUname;
	public Tab tabInvByEmail;
	
	public Label lblInvProjName;
	public Label lblInvProjBDesc;
	public Label lblInvCollabs;
	public Label lblInvAddPeople;
	public Label lblInvMsg;
	public Label lblInvCustomEmail;
	
	public Listheader headerInvUser;
	public Listheader headerInvFullName;
	
	public Button btnSendInvitation;
	public Button btnCancelInvitation;
	public Button btnSendEmail;
	public Button btnCancelEmail;
	public Button btnAddUser;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {		
		super.doAfterCompose(comp);
		
		comp.setAttribute(comp.getId() + "Ctrl", this, true);	
		
		// service locator
		userService = ServiceLocator.getUserService();
		collaborationMgmtService = ServiceLocator.getCollaborationMgmtService();
		invitationByEmailService = ServiceLocator.getInvitationByEmailService();
		
		// get profile from the session
		CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");        
        profile = currentSession.getProfile();       
        
		project = (Project)arg.get("project");		
		List<Invitation> invitationsByProject = collaborationMgmtService.getInvitationsByProject(project);		
		
		for(Invitation invitation: invitationsByProject) {
			Profile sendTo = invitation.getSentToProfile();
			collaboratorList.add(sendTo);
		}	
		
		collaboratorListBox.setItemRenderer(new CollaboratorListBoxRenderer());
		collaboratorListBox.setModel(new ListModelList(collaboratorList));
		
		// modelo para el autocomplete de nombres de usuario
		String[] usernameDictionary = getUsernameDictionary(userService.getAllUsers());
		
		ListModel dictModel = new SimpleListModel(usernameDictionary);
		cmbUsers.setModel(dictModel);
	}
	
	private String getUserName() throws WrongValueException {
		String userName = txtUserName.getValue();
		if(Strings.isBlank(userName)) {
			throw new WrongValueException(txtUserName, "Enter the username(s)");
		}
		return userName;
	}
	
	private String getMessageInvitation() throws WrongValueException {
		String messageInvitation = txtMessageInvitation.getValue().trim();
		if(Strings.isBlank(messageInvitation)) {
			throw new WrongValueException(txtMessageInvitation, "Enter the message");
		}
		
		return messageInvitation;
	}
	
	private String getEmailAddresses() throws WrongValueException {
		String emailAddresses = txtEmailAddresses.getValue().trim();
		if(Strings.isBlank(emailAddresses)) {
			throw new WrongValueException(txtEmailAddresses, "Enter the email address");
		}
		return emailAddresses;
	}
	
	private String getEmailMessage() throws WrongValueException {
		String emailMessage = txtCustomEmailMessage.getValue().trim();
		if(Strings.isBlank(emailMessage)) {
			throw new WrongValueException(txtCustomEmailMessage, "Enter the message");
		}
		return emailMessage;
	}
	
	// validacion para el autocomplete de usernames
	private String getAutoUsername() throws WrongValueException {
		String autoUsername = cmbUsers.getValue().trim();
		if(Strings.isBlank(autoUsername)) {
			throw new WrongValueException(cmbUsers, "Enter the username");
		}
		
		return autoUsername;
	}
	
	// invitation by email
	public void onClick$btnSendEmail() throws Exception {
		
		String recipients = getEmailAddresses();
		String emailMessage = getEmailMessage();
		
		try{
			// sending invitations by email
			invitationByEmailService.sendMailInvitations(recipients, emailMessage, profile, project);
			// close the form
			winInvitationForm.detach();
			// show message
			Messagebox.show("The e-mail(s) has been sent", "Sending e-mail", Messagebox.OK, Messagebox.INFORMATION);
		}catch(Exception e) {
			Messagebox.show(e.getMessage(), "Error sending e-mail", Messagebox.OK, Messagebox.ERROR);
		}		
	}
	
	public void onClick$btnCancelEmail() throws Exception {
		// se cierra la ventana del formulario
		winInvitationForm.detach();
	}
	
	// usar el boton "add" para seleccionar un usuaril del autocomplete
	public void onClick$btnAddUser(Event event) throws Exception {
		String username = getAutoUsername();
		
		String users = txtUserName.getValue();
		if(!Strings.isBlank(users)) {
			users +=", "+username;
		}else {
			users = username;
		}
		
		cmbUsers.setValue("");
		txtUserName.setValue(users);
	}
	
	// invitation by usernames
	public void onClick$btnSendInvitation() throws Exception {
		// se obtiene el proyecto que se paso como parametro a la invitacion
		Project project = (Project)arg.get("project");		
				
		// aqui se recopilan los usuarios que no esten registrados
		List<String> unregisteredUsers = new ArrayList<String>();
		// aqui se recopilan los uaurios registrados
		List<User> registeredUsers = new ArrayList<User>();			

		// se solicitan los nombre de usuarios ingresados y el mensaje
		String userNames = getUserName();
		String messageInvitation = getMessageInvitation();
		
		String[] users = userNames.split(",");	
		
		User foundUser;
		
		for(String user:users) {
			user = user.trim();
			foundUser = userService.findByUserName(user);
			if(foundUser != null) {
				registeredUsers.add(foundUser);
			}else {
				unregisteredUsers.add(user);
			}
		}
		
		// si los nombres de usuario no estan registrados
		if(unregisteredUsers.size()>0) {
			String warningMsg = "";
			for(String userName: unregisteredUsers) {
				warningMsg+=userName+"\n";
			}
			Messagebox.show(warningMsg, "These usernames not exist", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		// se enviara una invitacion a colaborar a cada usuario encontrado
		if(registeredUsers.size()>0) {
			
			Invitation invitation;
			
			// No se deben duplicar invitaciones al mismo proyecto para usuario seleccionado			
			List<User> usersInvitedToThisProject = new ArrayList<User>();
			
			List<Invitation> invitationList = collaborationMgmtService.getInvitationsByProject(project);
			
			for(Invitation _invitation: invitationList) {
				Profile sendTo = _invitation.getSentToProfile();
				long sendToId = sendTo.getId();
				
				for(User registerUser: registeredUsers) {
					if(sendToId == registerUser.getId()) {
						usersInvitedToThisProject.add(registerUser);
					}					
				}				
			}
			
			if(usersInvitedToThisProject.size()>0) {
				String warningMsg = "";
				for(User user: usersInvitedToThisProject) {
					warningMsg+=user.getUserName()+"\n";
				}
				Messagebox.show(warningMsg, "These users have been invited to this project", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			
			boolean isOwner = false;
			String owner = "";
			
			// send invitations
			for(User user: registeredUsers) {				
				if(!profile.getUser().equals(user)) {
					invitation = new Invitation();
					invitation.setProject(project);
					invitation.setInvitationDate(new Date());
					invitation.setSentByProfile(profile);
					invitation.setSentToProfile(user.getProfile());
					invitation.setMessage(messageInvitation);
					
					collaborationMgmtService.persistInvitation(invitation);
					
					// model profile list
					Profile prof = user.getProfile();
					collaboratorList.add(prof);
				}else {
					isOwner = true;
					owner = user.getUserName();
					continue;
				}
			}
			
			if(isOwner) {
				Messagebox.show(owner, "This user is the owner of the project", Messagebox.OK, Messagebox.EXCLAMATION);
			}

			collaboratorListBox.setModel(new ListModelList(collaboratorList));
			
			txtUserName.setValue("");
			txtMessageInvitation.setValue("");
		}						
		
	}
	
	private String[] getUsernameDictionary(List<User> allUsers) {		
		List<String> allUserNames = new ArrayList<String>();

		for(User user: allUsers) {
			allUserNames.add(user.getUserName());
		}		
		String[] userDictionary = (String[])allUserNames.toArray(new String[0]);
		return userDictionary;
	}
	
	public void onClick$btnCancelInvitation() throws Exception {
		// se cierra la ventana del formulario
		winInvitationForm.detach();
	}

	public List<Profile> getModelProfileList() {
		return collaboratorList;
	}

	public Profile getSelectedProfileWithPerms() {
		return selectedCollaborator;
	}

	public void setSelectedProfileWithPerms(Profile selectedProfileWithPerms) {
		this.selectedCollaborator = selectedProfileWithPerms;
	}
	
	class CollaboratorListBoxRenderer implements ListitemRenderer {		
		public void render(Listitem item, Object data) throws Exception {			
			Profile profile = (Profile)data;
			new Listcell(profile.getUser().getUserName()).setParent(item);
			new Listcell(profile.getFullName()).setParent(item);
			
			Listcell delCell = new Listcell();
			Toolbarbutton delButton = new Toolbarbutton("remove");
			delButton.setImage("/assets/image/delete-icon.png");
			delButton.setParent(delCell);
			delButton.addEventListener(Events.ON_CLICK, new RemoveCollaboratorListener(profile));

			delCell.setParent(item);
			
		}
	}
	//end CollaboratorListBoxRenderer
	
	class RemoveCollaboratorListener implements EventListener{
		private Profile profile;
		
		public RemoveCollaboratorListener(Profile profile) {
			this.profile = profile;
		}
		
		public void onEvent(Event event) throws Exception {
			
			Messagebox.show("Remove this collaborator?", "Remove?", 
							Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {
				public void onEvent(Event event) throws Exception {
					switch (((Integer)event.getData()).intValue()) {
			            case Messagebox.YES: doYes(); break; //the Yes button is pressed
			            case Messagebox.NO: break; //the No button is pressed
		            }					
				}
			});			
		}

		private void doYes() {
			collaborationMgmtService.removeProjectInvitation(this.profile, project);
			collaboratorList.remove(this.profile);
			collaboratorListBox.setModel(new ListModelList(collaboratorList));
		}
	}
	// end RemoveCollaboratorListener
}