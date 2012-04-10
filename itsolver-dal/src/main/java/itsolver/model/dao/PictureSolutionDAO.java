package itsolver.model.dao;

import java.util.List;

import itsolver.model.entity.PictureSolution;

public interface PictureSolutionDAO extends GenericDAO<PictureSolution, Long> {

	public List<PictureSolution> getPicturesSolutionByProjId(Long projSolutionId);
	
}
