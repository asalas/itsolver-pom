package itsolver.components;

import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zul.Label;

public class NotifyCollaboration extends HtmlMacroComponent {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -6712095245613743937L;

	public void setNotification(String notification) {
		setDynamicProperty("notification", notification);
		final Label lbl = (Label)getFellow("_notification");
		if(lbl!=null) lbl.setValue(notification);
	}
	
	public String getNotification() {
		return (String)getDynamicProperty("notification");
	}
}
