package itsolver.model.dao;

import java.util.List;

import itsolver.model.entity.Project;


public interface ProjectDAO extends GenericDAO<Project, Long>{

	public List<Project> findProjectsByProfileId(long profileId);
}
