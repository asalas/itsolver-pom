package itsolver.controller.usermanagement;

import itsolver.controller.utils.ItSolverEvents;
import itsolver.controller.utils.NoValidationZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.User;
import itsolver.service.UserService;
import itsolver.utils.CurrentSession;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;

@SuppressWarnings("serial")
public class Login extends NoValidationZkComposer{
	
	private static final String NO_EMPTY = "This field may not be empty or contain only spaces.";

	public Label lblUserName;
	public Label lblPassword;
	public Label lblErrorMessage;
	
	private Textbox txtUserName;
	private Textbox txtPassword;
	public Button btnSignin;
	public Checkbox ckbRememberMe;
	
	public Popup login_popup;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        login_popup.addEventListener(ItSolverEvents.CHANGE_LANGUAGE_MARKETING, new EventListener() {
        	public void onEvent(Event event) throws Exception {
        		String lang = (String)event.getData();
        		setLanguage(lang);
        		
        	}
        });
    }
	
	public void onOpen$login_popup(Event event) throws Exception {
		cleanFields();
	}
	
	private void cleanFields(){		
		lblErrorMessage.setVisible(false);
		txtUserName.clearErrorMessage();
		if (!Strings.isBlank(txtUserName.getValue())){
			txtUserName.setValue("");
		}
		txtPassword.clearErrorMessage();
		if (!Strings.isBlank(txtPassword.getValue())){
			txtPassword.setValue("");
		}		
		
	}
	
	private String getTxtUserName() throws WrongValueException {
		String userName = txtUserName.getValue();
		if(Strings.isBlank(userName)) {
			throw new WrongValueException(txtUserName, Login.NO_EMPTY);
		}
		return userName;
	}
	
	private String getTxtPassword() throws WrongValueException {
		String password = txtPassword.getValue();
		if(Strings.isBlank(password)) {
			throw new WrongValueException(txtPassword, Login.NO_EMPTY);
		}
		return password;
	}
	
	public void onClick$btnSignin(Event event) throws Exception {
		UserService userService = ServiceLocator.getUserService();
		User user = new User();		
		user.setUserName(getTxtUserName());
		user.setPassword(getTxtPassword());
		User userLogged = userService.validateUser(user);
		if ( userLogged != null ){
			CurrentSession currentSession = super.createUserSession(userLogged.getProfile(), user);
			session.setAttribute("currentSession",currentSession);
			Executions.sendRedirect("/home/");
		}else{
			lblErrorMessage.setValue("Username or password incorrect");
			lblErrorMessage.setVisible(true);			
		}
    }  	
}
