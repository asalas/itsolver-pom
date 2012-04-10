package itsolver.service;

import itsolver.model.entity.Characteristic;
import itsolver.model.entity.PictureSolution;
import itsolver.model.entity.Project;
import itsolver.model.entity.ProjectResource;
import itsolver.model.entity.ResourceCategory;
import itsolver.model.entity.Restriction;

import java.util.List;

public interface ProjectMgmtService {
	public void projectCreate(Project project);
	public void projectRefresh(Project project);
	public Project getProject(long projectId);
	public void projectUpdate(Project project);
	public List<Project> getProjectsByProfileId(long profileId);	
	
	/* Resource management operations */
	public void resourceCategoryCreate(ResourceCategory category);	
	public ResourceCategory getResourceCategoryById(Long id);	
	public void deleteprojectResource(ProjectResource projectResource);	
	public ResourceCategory getResourceTree();
	
	/* Constrains management operations*/
	public void saveCharacteristic(Characteristic characteristic);
	public List<Characteristic> getCharacteristicByCategory(String category);
	public Characteristic getCharacteristicById(Long id);	
	public void removeRestriction(Restriction restriction);
	
	// pictures
	public void savePictureSolution(PictureSolution picSolution);
	public void removePictureSolution(PictureSolution picSolution);
	public PictureSolution getPictureSolutionById(Long id);
	public List<PictureSolution> getPicturesByProjectSolutionId(Long projSolutionId);
	
}
