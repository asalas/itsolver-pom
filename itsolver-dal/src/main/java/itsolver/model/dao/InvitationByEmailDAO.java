package itsolver.model.dao;

import itsolver.model.entity.InvitationByEmail;

public interface InvitationByEmailDAO extends GenericDAO<InvitationByEmail, Long> {

	public InvitationByEmail findByHiloId(String hiloId);
}
