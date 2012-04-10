package itsolver.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import itsolver.model.entity.InvitationByEmail;

public class InvitationByEmailDAOImpl extends GenericDAOImpl<InvitationByEmail, Long> implements InvitationByEmailDAO {

	@Override
	public InvitationByEmail findByHiloId(String hiloId) {
		InvitationByEmail invitationByEmail = null;
		
		Query query = getEntityManager().createQuery("FROM InvitationByEmail WHERE hiloId = :hiloId");
		query.setParameter("hiloId", hiloId);
		
		try {
			invitationByEmail = (InvitationByEmail)query.getSingleResult();
		}catch(NoResultException e) {
			e.printStackTrace();
		}
		
		return invitationByEmail;
	}
}
