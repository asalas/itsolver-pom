package itsolver.service;

import itsolver.model.dao.UserDAO;
import itsolver.model.entity.Dashboard;
import itsolver.model.entity.User;

import java.util.List;


/**
 * Clase servicio con las operaciones para administrar a los usuarios
 * @author rene
 *
 */
public class UserServiceImpl implements UserService {
	//DAO con las operaciones de persistencia para administrar usuarios 
	private UserDAO userDAO;
	
	public boolean registerUser(User user) {
		Dashboard dashboard = new Dashboard();
		//dashboard.setProfile(user.getProfile());
		user.getProfile().setDashboard(dashboard);
		userDAO.persist(user);
		if (user.getId() != null && user.getId() > 0){
			return true;
		}
		return false;
	}
	
	public User validateUser(User user){
		User userFound = userDAO.findByUserName(user.getUserName());		
		
		if(userFound != null){
			if(userFound.getPassword().equals(user.getPassword())) {
				return userFound;
			}
		}
		
		return null;
	}
	
	
	public User findByUserName(String userName) {
		User user = userDAO.findByUserName(userName);		
		return user;
	}
	
	
	public boolean isRegisteredUserName(String userName) {
		User userFound = userDAO.findByUserName(userName);		
		if (userFound != null){
			return true;
		}
		return false;
	}
	
	
	public boolean isRegisteredEmail(String email) {
		User userFound = userDAO.findByEmail(email);		
		if (userFound != null){
			return true;
		}
		return false;
	}
	
	
	public List<User> getAllUsers() {		
		return userDAO.findAll();
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}
