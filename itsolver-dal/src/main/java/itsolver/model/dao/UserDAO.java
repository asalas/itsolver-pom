package itsolver.model.dao;

import itsolver.model.entity.User;

public interface UserDAO extends GenericDAO<User, Long> {
	public User findByUserName(String userName); 
	public User findByEmail(String email);
}
