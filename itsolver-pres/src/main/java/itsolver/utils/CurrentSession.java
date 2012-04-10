package itsolver.utils;

import itsolver.model.entity.Profile;
/**
 * Entity used for manage the objects needed on Session
 * @author hermes
 *
 */
public class CurrentSession{
	
	private Profile profile;
	private String userName;
	
	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
