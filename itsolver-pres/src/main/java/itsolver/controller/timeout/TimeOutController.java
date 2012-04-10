package itsolver.controller.timeout;

import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Timer;

public class TimeOutController extends GenericForwardComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5751829717254413322L;
	
	public Timer timeOut;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		
		if(collaborator!=null) {
			MutualExclusionControl.onExit();
		}
	}

	
	public void onTimer$timeOut(Event event)throws Exception {
		timeOut.stop();
		Executions.sendRedirect("/");
	}
}
