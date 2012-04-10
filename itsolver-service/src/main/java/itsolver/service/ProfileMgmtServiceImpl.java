package itsolver.service;

import itsolver.model.dao.ProfileDAO;
import itsolver.model.entity.Profile;

public class ProfileMgmtServiceImpl implements ProfileMgmtService {

	private ProfileDAO profileDAO;
	
	
	public void mergeProfile(Profile profile) {
		profileDAO.merge(profile);

	}

	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}

	public void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

}
