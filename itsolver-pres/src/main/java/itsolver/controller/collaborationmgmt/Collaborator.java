package itsolver.controller.collaborationmgmt;

import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.utils.DateUtils;

import org.zkoss.lang.Threads;
import org.zkoss.util.logging.Log;
/**
 * Class for controlling mutual exclusion for accessing an artifact named Project. 
 * It blocks the project for 10 minutes in order that  only one user can modify it.
 * @author rene
 *
 */
public class Collaborator extends Thread {
	
	private static final Log log = Log.lookup(Collaborator.class);
	/**
	 * Validates if this instance is active
	 */
	private boolean ceased;
	/**
	 * Instance of the container that has the blocked projects
	 */
	private MutualExclusionRoom mutualExclusionRoom;
	/**
	 * Instance of the project that is blocked
	 */
	private Project project;
	/**
	 * Profile that is blocking the project
	 */	
	private Profile profile;
	/**
	 * Time for blocking the project is set to 10 minutes
	 */
	private long timeout = 10L;
	/**
	 * Validates if there is time available.
	 */
	private boolean timedOut;
	
	private long startTimeMillis = System.currentTimeMillis();
	
	public Collaborator(MutualExclusionRoom mutualExclusionRoom, Project project, Profile profile) {
		this.mutualExclusionRoom = mutualExclusionRoom;
		this.project = project;
		this.profile = profile;
	}	
	/**
	 * Starts the thread with the process for doing the validation if the project is still blocked
	 */
	public void run() {		
		mutualExclusionRoom.subscribe(this);
		
		try {
			//The thread is active while there is time
			while(!ceased) {
				Threads.sleep(1000);
				//We retrieve the time that has elapsed after the threat was started 
				long minutesElapsed = DateUtils.currentTimeMinutes(startTimeMillis);
				//if you are over 10 minutes, we change the flag for finishing the thread
				if(minutesElapsed >= timeout) {
					ceased = true;
					timedOut = true;
				}				
			}
		}finally {
			//The threat is unsubscribe from the thread's container
			mutualExclusionRoom.unsubscribe(this);
		}		
	}
	/**
	 * Increase the thread is active
	 * @param timeout
	 */
	public void increaseTimeout(long timeout) {
		this.timeout += timeout;		
	}
	public void setDone() {
		ceased = true;
	}

	public Project getProject() {
		return project;
	}

	public Profile getProfile() {
		return profile;
	}
	
	public boolean isTimedOut() {
		return timedOut;
	}

}