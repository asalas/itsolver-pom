package itsolver.controller.marketing;

import itsolver.controller.utils.NoValidationZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.User;
import itsolver.service.UserService;
import itsolver.utils.CurrentSession;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

@SuppressWarnings("serial")
public class RequiredRegistration extends NoValidationZkComposer{

	public Label lblUserName;
	public Label lblPassword;
	public Label lblErrorMessage;
	
	private Textbox txtUserName;
	private Textbox txtPassword;
	public Button btnSignin;	
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }
	
	public void onClick$btnSignin(Event event) throws Exception {
		UserService userService = ServiceLocator.getUserService();
		User user = new User();		
		user.setUserName(txtUserName.getValue());
		user.setPassword(txtPassword.getValue());
		User userLogged = userService.validateUser(user);
		if ( userLogged != null ){			
			CurrentSession currentSession = new CurrentSession();
			currentSession.setProfile( userLogged.getProfile());
			currentSession.setUserName(userLogged.getUserName());
			session.setAttribute("currentSession",currentSession);			
			Executions.sendRedirect("/home/");
		}else{
			lblErrorMessage.setValue("Username or password incorrect");
			lblErrorMessage.setVisible(true);	
		}
    }
}
