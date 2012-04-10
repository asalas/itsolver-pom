package itsolver.controller.chat;

import itsolver.controller.utils.GenericZkComposer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ChatWindow extends GenericZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -4133865917710058905L;

	private String sender;

	private ChatRoom chatroom;

	private Chatter chatter;

	private Desktop desktop;

	private boolean isLogin;
	
	private Window winChat;
	
	/**
	 * setup initilization
	 * 
	 */
	public void init() {
		desktop = Executions.getCurrent().getDesktop();
		
		chatroom = (ChatRoom) desktop.getWebApp().getAttribute("chatroom");
		if (chatroom == null) {
			chatroom = new ChatRoom();
			desktop.getWebApp().setAttribute("chatroom", chatroom);
		}
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
		//TODO: el login en el chat es automatico
		onLogin();
	}

	public void onOK() {
		if (isLogin())
			onSendMsg();
		else
			onLogin();
	}

	/**
	 * used for login
	 * 
	 */
	public void onLogin() {
		// enable server push for this desktop
		desktop.enableServerPush(true);
		
		// get sender from arguments
		sender = arg.get("userName").toString();
		
		// start the chatter thread
		chatter = new Chatter(chatroom, sender, winChat.getFellow("msgBoard"));
		chatter.start();

		// chage state of user
		setLogin(true);

		// refresh UI		
		winChat.getFellow("dv").setVisible(true);
		winChat.getFellow("input").setVisible(true);
	}

	/**
	 * used for exit
	 * 
	 */
	public void onExit() {
		// clean up
		chatter.setDone();
		
		//disable server push
		desktop.enableServerPush(false);
		
		setLogin(false);
		
		// refresh the UI
		winChat.getFellow("msgBoard").getChildren().clear();
		winChat.getFellow("dv").setVisible(false);
		winChat.getFellow("input").setVisible(false);
		
		// the divLogo is the parent
		Div divLogo = (Div)winChat.getParent();
		// dispatch onExitChat event
		Events.postEvent("onExitChat", divLogo, null);		
		// close window
		winChat.detach();
	}

	/**
	 * used to send messages
	 * 
	 */
	public void onSendMsg() {
		// add comment
		Label message = new Label();
		message.setValue(sender + ":" + ((Textbox) winChat.getFellow("msg")).getValue());
		winChat.getFellow("msgBoard").appendChild(message);
		chatter.sendMessage(((Textbox) winChat.getFellow("msg")).getValue());
		((Textbox) winChat.getFellow("msg")).setRawValue("");		
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin( boolean bool) {
		isLogin = bool;
	}
}
