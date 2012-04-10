package itsolver.controller.usermanagement;

import itsolver.controller.utils.ItSolverEvents;
import itsolver.controller.utils.NoValidationZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Invitation;
import itsolver.model.entity.InvitationByEmail;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.model.entity.User;
import itsolver.service.CollaborationMgmtService;
import itsolver.service.InvitationByEmailService;
import itsolver.service.UserService;
import itsolver.utils.CurrentSession;

import java.util.Date;

import org.zkforge.bwcaptcha.Captcha;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.WrongValuesException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class Signup extends NoValidationZkComposer {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String NO_EMPTY = "This field may not be empty or contain only spaces.";

	public Label lblUsrName;
	public Label lblName;
	public Label lblLastName;
	public Label lblField;
	public Label lblPassword;
	public Label lblPasswordConfirmation;
	public Label lblCaptcha;
	
	public Textbox txtUserName;
	public Textbox txtName;
	public Textbox txtLastName;
	public Textbox txtEmail;
	public Textbox txtPassword;	
	public Textbox txtPasswordConfirmation;
	
	public Textbox txtCaptchaValue;
	public Button btnSignup;
	public Captcha captcha;
	public Caption signUpCaption;
	
	public Window winSignUp;
	
	private UserService userService;
	
	private String hiloId;
	private InvitationByEmailService invitationByEmailService;
	private InvitationByEmail invitationByEmail = null;
	private CollaborationMgmtService collaborationMgmtService;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {	
		super.doAfterCompose(comp);		
		
		userService = ServiceLocator.getUserService();
		invitationByEmailService = ServiceLocator.getInvitationByEmailService();
		collaborationMgmtService = ServiceLocator.getCollaborationMgmtService();
		
		// cambio de idioma
		winSignUp.addEventListener(ItSolverEvents.CHANGE_LANGUAGE_MARKETING, new EventListener() {
			public void onEvent(Event event) throws Exception {
				String lang = (String)event.getData();
				setLanguage(lang);
			}
		});
		
		// Se obtiene el parametro hilo_id a traves de GET
		Object[] hiloId = (Object[])Executions.getCurrent().getParameterMap().get("hilo_id");		
		
		if(hiloId != null) {
			this.hiloId = hiloId[0].toString();			
			invitationByEmail = invitationByEmailService.findByHiloId(this.hiloId);
			
			if(invitationByEmail != null) {				
				Boolean isAccepted = false;
				isAccepted = invitationByEmail.getIsAccepted();
				
				if(!isAccepted) {
					txtEmail.setValue(invitationByEmail.getEmail());
					txtEmail.setDisabled(true);
				}else {					
					final Window win = (Window)Executions.createComponents("notification.zul", null, null);				
					win.setMaximizable(false);
					win.setMinimizable(false);
					win.setClosable(false);
					win.doModal();
				}				
			}			
		}
		
	}
	
	private String getTxtUserName() throws WrongValueException {
		String userName = txtUserName.getValue();
		if(Strings.isBlank(userName)) {
			throw new WrongValueException(txtUserName, Signup.NO_EMPTY);
		}
		return userName;
	}
	
	private String getTxtName() throws WrongValueException {
		String name = txtName.getValue();
		if(Strings.isBlank(name)) {
			throw new WrongValueException(txtName, Signup.NO_EMPTY);
		}
		return name;
	}
	
	private String getTxtLastName() throws WrongValueException {
		String lastName = txtLastName.getValue();
		if(Strings.isBlank(lastName)) {
			throw new WrongValueException(txtLastName, Signup.NO_EMPTY);
		}
		return lastName;
	}
	
	private String getTxtEmail() throws WrongValueException {
		String email = txtEmail.getValue();
		if(Strings.isBlank(email)) {
			throw new WrongValueException(txtEmail, Signup.NO_EMPTY);
		}		
		return email;
	}
	
	private String getTxtPassword() throws WrongValueException {
		String password = txtPassword.getValue();
		if(Strings.isBlank(password)) {
			throw new WrongValueException(txtPassword, Signup.NO_EMPTY);
		}
		return password;
	}
	private String getTxtPasswordConfirmation() throws WrongValueException {
		String password = txtPasswordConfirmation.getValue();
		if(Strings.isBlank(password)) {
			throw new WrongValueException(txtPasswordConfirmation, Signup.NO_EMPTY);
		}
		return password;
	}
		
	private boolean validateExistingAccount(User userToValidate) throws WrongValuesException{
		if(userService.isRegisteredUserName(userToValidate.getUserName())){
			throw new WrongValueException(txtUserName, "Username already registered");
		}
		if(userService.isRegisteredEmail(userToValidate.getEmail())){
			throw new WrongValueException(txtEmail, "Email already registered");
		}		
		return true;
	}
	
	private boolean isValidPasswordConfirmation() throws WrongValueException{
		if ( getTxtPassword().compareTo(getTxtPasswordConfirmation()) != 0 ){
			throw new WrongValueException(txtPasswordConfirmation, "Password isn't the same");
		}
		return true;
	}
	
	private boolean isValidCaptcha() throws WrongValueException{
		if ( txtCaptchaValue.getValue().compareTo(captcha.getValue()) != 0){
			captcha.setValue(captcha.randomValue());
			throw new WrongValueException(txtCaptchaValue, "Wrong captcha value");			
		}
		return true;
	}
	
	
	public void onClick$btnSignup(Event event) throws Exception {
		
		Profile profile = new Profile();
		profile.setName(getTxtName());
		profile.setLastName(getTxtLastName());
		
		User user = new User();		
		user.setUserName(getTxtUserName());			
		user.setEmail(getTxtEmail());
		user.setPassword(getTxtPassword());
		user.setProfile(profile);
		
		profile.setUser(user);
		
		validateExistingAccount(user);		
		isValidPasswordConfirmation();		
		isValidCaptcha();				
		
		boolean isRegistered = userService.registerUser(user);
		
		if (isRegistered){
			
			// si el usuario se registra se toma como aceptada la invitacion por email
			// si es que el signup procede de dicha invitacion
			if(invitationByEmail != null) {				
				if(invitationByEmail.getId() != null && invitationByEmail.getId() > 0) {
					
					try {
						invitationByEmail.setIsAccepted(true);
						invitationByEmailService.mergeInvitationByEmail(invitationByEmail);
						
						Project project = invitationByEmail.getProject();						
						Profile sendFrom = invitationByEmail.getSendFrom();
						String message = invitationByEmail.getMessage();
						
						Invitation invitation = new Invitation();
						invitation.setProject(project);
						invitation.setInvitationDate(new Date());
						invitation.setSentByProfile(sendFrom);
						invitation.setSentToProfile(user.getProfile());
						invitation.setMessage(message);
						
						collaborationMgmtService.persistInvitation(invitation);
						
					}catch(Exception e) {
						e.printStackTrace();
					}					
				}
			}
			
			CurrentSession currentSession = super.createUserSession(profile, user);
			session.setAttribute("currentSession",currentSession);
			Executions.sendRedirect("/home/");
		}else{
			Messagebox.show("Error on signup", "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
}