package itsolver.service;

import itsolver.model.dao.CharacteristicDAO;
import itsolver.model.dao.PictureSolutionDAO;
import itsolver.model.dao.ProjectDAO;
import itsolver.model.dao.ProjectResourceDAO;
import itsolver.model.dao.ResourceCategoryDAO;
import itsolver.model.dao.RestrictionDAO;
import itsolver.model.dao.SuFieldGraphDAO;
import itsolver.model.dao.SuFieldModelDAO;
import itsolver.model.entity.Characteristic;
import itsolver.model.entity.PictureSolution;
import itsolver.model.entity.Project;
import itsolver.model.entity.ProjectResource;
import itsolver.model.entity.ResourceCategory;
import itsolver.model.entity.Restriction;

import java.util.List;

public class ProjectMgmtServiceImpl implements ProjectMgmtService {

	private ProjectDAO projectDAO;	
	private ResourceCategoryDAO resourceCategoryDAO;	
	private ProjectResourceDAO projectResourceDAO;
	private CharacteristicDAO characteristicDAO;
	private RestrictionDAO restrictionDAO;
	
	private SuFieldGraphDAO suFieldGraphDAO;
	private SuFieldModelDAO suFieldModelDAO;
	
	private PictureSolutionDAO pictureSolutionDAO;
	
	
	public Project getProject(long projectId) {
		return projectDAO.findById(projectId);
	}

	
	public void projectCreate(Project project) {
		projectDAO.persist(project);		
	}
	public void projectRefresh(Project project){
		projectDAO.refresh(project);
	}

	
	public void projectUpdate(Project project) {
		projectDAO.merge(project);

	}
	

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	
	public List<Project> getProjectsByProfileId(long profileId) {		
		List<Project> projectsByProfileId = 
			projectDAO.findProjectsByProfileId(profileId);
		
		return projectsByProfileId;
	}
	
	
	public void savePictureSolution(PictureSolution picSolution) {
		pictureSolutionDAO.persist(picSolution);		
	}
	
	
	public void removePictureSolution(PictureSolution picSolution) {
		pictureSolutionDAO.remove(picSolution);		
	}
	
	
	public List<PictureSolution> getPicturesByProjectSolutionId(Long projSolutionId) {		
		return pictureSolutionDAO.getPicturesSolutionByProjId(projSolutionId);
	}
	
	
	public PictureSolution getPictureSolutionById(Long id) {
		return pictureSolutionDAO.findById(id);
	}

	/* Resource managment methods */
	
	public void resourceCategoryCreate(ResourceCategory category){
		resourceCategoryDAO.persist(category);		
	}
	
	public ResourceCategory getResourceTree(){
		return resourceCategoryDAO.getResourceTree();
	}
	
	public ResourceCategory getResourceCategoryById(Long id){
		ResourceCategory foundById = resourceCategoryDAO.findById(id);
		return foundById;
	}
	
	
	public void deleteprojectResource(ProjectResource projectResource){
		projectResourceDAO.remove(projectResourceDAO.findById(projectResource.getId()));
	}
	
	public void setResourceCategoryDAO(ResourceCategoryDAO resourceCategoryDAO) {
		this.resourceCategoryDAO = resourceCategoryDAO;
	}
	public ResourceCategoryDAO getResourceCategoryDAO() {
		return resourceCategoryDAO;
	}	

	public ProjectResourceDAO getProjectResourceDAO() {
		return projectResourceDAO;
	}

	public void setProjectResourceDAO(ProjectResourceDAO projectResourceDAO) {
		this.projectResourceDAO = projectResourceDAO;
	}
	
	public SuFieldGraphDAO getSuFieldGraphDAO() {
		return suFieldGraphDAO;
	}

	public void setSuFieldGraphDAO(SuFieldGraphDAO suFieldGraphDAO) {
		this.suFieldGraphDAO = suFieldGraphDAO;
	}

	public SuFieldModelDAO getSuFieldModelDAO() {
		return suFieldModelDAO;
	}

	public void setSuFieldModelDAO(SuFieldModelDAO suFieldModelDAO) {
		this.suFieldModelDAO = suFieldModelDAO;
	}
	/*End Resource managment methods */
	
	/*Constrain management operatinos*/
	
	public void saveCharacteristic(Characteristic characteristic){
		this.characteristicDAO.persist(characteristic);
	}
	
	public List<Characteristic> getCharacteristicByCategory(String category){
		return characteristicDAO.getCharacteristicByCategory(category);
	}
	
	public Characteristic getCharacteristicById(Long id){
		return this.characteristicDAO.findById(id);
	}
	
	public void removeRestriction(Restriction restriction){
		this.restrictionDAO.remove(restriction);
	}
	public CharacteristicDAO getCharacteristicDAO() {
		return characteristicDAO;
	}
	public void setCharacteristicDAO(CharacteristicDAO characteristicDAO) {
		this.characteristicDAO = characteristicDAO;
	}
	public RestrictionDAO getRestrictionDAO() {
		return restrictionDAO;
	}
	public void setRestrictionDAO(RestrictionDAO restrictionDAO) {
		this.restrictionDAO = restrictionDAO;
	}
	/*End constrain management operatinos*/

	public PictureSolutionDAO getPictureSolutionDAO() {
		return pictureSolutionDAO;
	}

	public void setPictureSolutionDAO(PictureSolutionDAO pictureSolutionDAO) {
		this.pictureSolutionDAO = pictureSolutionDAO;
	}	
}