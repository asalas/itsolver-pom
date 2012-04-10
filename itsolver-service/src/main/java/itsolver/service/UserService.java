package itsolver.service;

import java.util.List;

import itsolver.model.entity.User;

public interface UserService {
	public boolean registerUser(User user);
	public User validateUser(User user);
	public User findByUserName(String userName);
	public List<User> getAllUsers();
	
	/**
	 * Validates if a user is registered, checks the user name 
	 * @param user
	 * @return true is the user is register, otherwise returns false
	 */
	public boolean isRegisteredUserName(String userName);
	
	/**
	 * Validates if a user is registered, checks the user name 
	 * @param user
	 * @return true is the user is register, otherwise returns false
	 */
	public boolean isRegisteredEmail(String email);
}
