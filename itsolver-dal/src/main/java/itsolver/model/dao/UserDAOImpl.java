package itsolver.model.dao;
import itsolver.model.entity.User;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO{

	public User findByUserName(String userName) {
		User user	= null;		
		try{
			user	= (User) getEntityManager().createQuery(
			"FROM User WHERE userName = :userName")
			.setParameter("userName", userName)
			.getSingleResult();
		}catch (NoResultException e) {
			
		}
		return user;
	}
	
	public User findByEmail(String email) {
		User user	= null;		
		try{
			user	= (User) getEntityManager().createQuery(
			"FROM User WHERE email = :email")
			.setParameter("email", email)
			.getSingleResult();
		}catch (NoResultException e) {
			
		}
		return user;
	}
}
