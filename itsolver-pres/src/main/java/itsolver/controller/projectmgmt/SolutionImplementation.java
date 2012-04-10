package itsolver.controller.projectmgmt;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.PictureSolution;
import itsolver.model.entity.Project;
import itsolver.model.entity.ProjectResource;
import itsolver.model.entity.Restriction;
import itsolver.model.entity.SelectedResource;
import itsolver.service.ProjectMgmtService;
import itsolver.utils.SolutionConstrainItem;
import itsolver.utils.SolutionResourceItem;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;


public class SolutionImplementation extends GenericZkComposer{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -3821957054034721325L;
	
	public Tabbox tabBoxImplementation;	
	public Vbox vBoxResourceList;
	public Rows  rowsRestrictionsList;
	public SaveChanges2Project saveSolutionImplementation;
	
	private Project project;
	
	public Button btnUpload;
	public Div hbPics;
	public Window winImg;
	
	private Media media;
	
	private List<PictureSolution> picturesSolutionList;
	private ProjectMgmtService projMgmService;
	private PictureSolution picSolution;
	
	public Tab tabSolutionResource, tabSolutionConstraints, tabSolutionPictures;
	public Label lblSelectedResources, lblHowWereTheyUsed, lblConstraints;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {	
		super.doAfterCompose(comp);
		saveSolutionImplementation.init((Window)arg.get("win"));
		this.project = (Project)desktop.getAttribute("currentProject");
		
		projMgmService = ServiceLocator.getProjectMgmtService();
		
		String language = this.getLanguage();
		String msgSave = "Save", msgCancel ="Cancel"; 
		if(language.equals("ES")) {
			msgSave = "Guardar"; msgCancel ="Cancelar";
		}else if(language.equals("FR")) {
			msgSave = "Enregistrer"; msgCancel ="Annuler";
		}		
		saveSolutionImplementation.setButtonLabel(msgSave);
		saveSolutionImplementation.setCancelButtonLabel(msgCancel);
		
		init();
	}
	
	private void init() throws Exception{
		initSolutionResources();
		initSolutionConstraint();
		initPicturesSolution();
	}
	private void initSolutionResources(){
		vBoxResourceList.getChildren().clear();
		List<ProjectResource> projectResourceList = project.getProjectResourceList();
		List<SelectedResource> selectedResourceList;
		for (ProjectResource projectResource : projectResourceList) {
			selectedResourceList = projectResource.getSelectedResourceList();
			for (SelectedResource selectedResource : selectedResourceList) {
				vBoxResourceList.getChildren().add(new SolutionResourceItem(selectedResource));
			}
		}
		SolutionResourceItem solutionResourceItem;
		List<SelectedResource> utilizedResourceList =  this.project.getProjectSolution().getUtilizedResourceList();
		for (SelectedResource selectedResource : utilizedResourceList) {
			for (Iterator iterator = vBoxResourceList.getChildren().iterator(); iterator.hasNext();) {
				solutionResourceItem = (SolutionResourceItem) iterator.next();
				if ( selectedResource.getId().equals(solutionResourceItem.getSelectedResource().getId()) ){
					solutionResourceItem.selectResource();
					break;
				}
			}
		}
	}
	
	private void initSolutionConstraint(){
		rowsRestrictionsList.getChildren().clear();
		List<Restriction> restrictionList = this.project.getRestrictionList();
		for (Restriction restriction : restrictionList) {
			rowsRestrictionsList.getChildren().add(new SolutionConstrainItem(restriction));
		}
		SolutionConstrainItem solutionConstrainItem;
		List<Restriction> satisfiedConstrainList = this.project.getProjectSolution().getSatisfiedConstrainList();
		for (Restriction restriction : satisfiedConstrainList) {
			for (Iterator iterator = rowsRestrictionsList.getChildren().iterator(); iterator.hasNext();) {
				solutionConstrainItem = (SolutionConstrainItem) iterator.next();
				if ( solutionConstrainItem.getRestriction().getId().equals(restriction.getId()) ){
					solutionConstrainItem.selectConstrain();
					break;
				}
			}
		}
	}
	
	public void initPicturesSolution() throws Exception {
		Long projSolutionId = project.getProjectSolution().getId();
		picturesSolutionList = projMgmService.getPicturesByProjectSolutionId(projSolutionId);
		
		InputStream is = null;
		AImage imgTmp = null;
		
		for(PictureSolution picture: picturesSolutionList) {
			is = new ByteArrayInputStream(picture.getPicture()); 
			imgTmp = new AImage("imgTmp", is);
			
			org.zkoss.zul.Image imgGallery = new org.zkoss.zul.Image();
			imgGallery.setId(picture.getId().toString());
			imgGallery.setContent(imgTmp);
			imgGallery.setWidth("100px");
			imgGallery.setStyle("margin: 3px;");
			imgGallery.addEventListener(Events.ON_CLICK, new ImageZoomer());
			imgGallery.setParent(hbPics);			
		}
		
		
	}
	
	public void onClick$saveSolutionImplementation(Event event) throws Exception {		
		updateProjectSolution();
	}
	
	private void updateProjectSolution()throws Exception{
		Tab selectedTab = tabBoxImplementation.getSelectedTab();
		String selectedTabId = selectedTab.getId();
		if(selectedTabId.equals("tabSolutionResource")) {
			updateSolutionResource();    			
		}else if(selectedTabId.equals("tabSolutionConstraints")) {
			updateSolutionConstrains(); 			
		}/*else if(selectedTabId.equals("tabSolutionIFR")) {
			updateSolutionIFR();
		}*/
			
	}
	
	private void updateSolutionResource()throws Exception{		
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		SolutionResourceItem solutionResourceItem;		
		List<SelectedResource> utilizedResourceList =  this.project.getProjectSolution().getUtilizedResourceList();		
		utilizedResourceList.clear();
		for (Iterator iterator = vBoxResourceList.getChildren().iterator(); iterator.hasNext();) {
			solutionResourceItem = (SolutionResourceItem) iterator.next();
			if ( solutionResourceItem.isSelectedResource() ){
				utilizedResourceList.add(solutionResourceItem.getSelectedResource());
			}
		}		
		this.project.getProjectSolution().setUtilizedResourceList(utilizedResourceList);
		
		String language = this.getLanguage();
		String msg = "Utilized resources in the project have been updated";
		if(language.equals("ES")) {
			msg = "Los recursos utilizados en el proyecto se han actualizado";
		}else if(language.equals("FR")) {
			msg = "finis!";
		}
		saveSolutionImplementation.setMessage(msg);
		
		saveSolutionImplementation.performProjectUpdate(project, desktop, collaborator, arg.get("win"));
	}
	
	private void updateSolutionConstrains()throws Exception{
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		SolutionConstrainItem solutionConstrainItem;
		
		List<Restriction> satisfiedConstrainList = this.project.getProjectSolution().getSatisfiedConstrainList();
		satisfiedConstrainList.clear();
		
		for (Iterator iterator = rowsRestrictionsList.getChildren().iterator(); iterator.hasNext();) {
			solutionConstrainItem = (SolutionConstrainItem) iterator.next();
			if ( solutionConstrainItem.isSelectedConstrain() ){
				satisfiedConstrainList.add(solutionConstrainItem.getRestriction());
			}
		}
		this.project.getProjectSolution().setSatisfiedConstrainList(satisfiedConstrainList);
		
		String language = this.getLanguage();
		String msg = "Satisfied constrains have been updated";
		if(language.equals("ES")) {
			msg = "Las restricciones satisfechas se han actualizado";
		}else if(language.equals("FR")) {
			msg = "finis!";
		}
		saveSolutionImplementation.setMessage(msg);		
		
		saveSolutionImplementation.performProjectUpdate(project, desktop, collaborator, arg.get("win"));
	}
	
	private void updateSolutionIFR()throws Exception{
		//TODO implementar actualizacion del IFR en la solucion
	}
	
	public void onUpload$btnUpload(UploadEvent event) throws Exception {
		String path = application.getRealPath("/repo_pics");		
		
		media = event.getMedia();		
		
		if(media instanceof Image) {
			/* IMPORTANTE
			String name = media.getName();
			String picName = path+"/"+name;
			
			FileOutputStream fos = new FileOutputStream(picName);
			fos.write(media.getByteData());
			fos.close();	
			
			File picFile = new File(picName);
			byte[] bFile = new byte[(int)picFile.length()];
			
			FileInputStream fis = new FileInputStream(picFile);
			fis.read(bFile);
			fis.close();
			
			AImage imgTmp = new AImage("imgTmp", bFile);
			
			org.zkoss.zul.Image img = new org.zkoss.zul.Image();		
			img.setWidth("100px");
			img.setContent(imgTmp);
			img.setParent(hbPics);
			 */		
			
			picSolution = new PictureSolution();
			picSolution.setPicture(media.getByteData());
			picSolution.setProjectSolution(this.project.getProjectSolution());
			
			projMgmService.savePictureSolution(picSolution);
			
			org.zkoss.zul.Image img = new org.zkoss.zul.Image();		
			img.setId(picSolution.getId().toString());
			img.setStyle("margin: 3px;");
			img.setWidth("100px");
			img.setContent((Image)media);
			img.setParent(hbPics);
			
			img.addEventListener(Events.ON_CLICK, new ImageZoomer());
			
		}else {
			 Messagebox.show("Not an image: "+media, "Error", Messagebox.OK, Messagebox.ERROR);
		}
		
	}
	
	private class ImageZoomer implements EventListener {
		public void onEvent(Event event) throws Exception {
			winImg.getChildren().clear();
			
			org.zkoss.zul.Image imgTarget = (org.zkoss.zul.Image)event.getTarget();					
			
			String imageId = imgTarget.getId();					
			PictureSolution pictureSolutionById = projMgmService.getPictureSolutionById(Long.parseLong(imageId));					
			
			InputStream is = new ByteArrayInputStream(pictureSolutionById.getPicture()); 
			AImage imgTmp = new AImage("imgTmp", is);
			
			org.zkoss.zul.Image imgZoom = new org.zkoss.zul.Image();
			imgZoom.setWidth("400px");
			imgZoom.setContent(imgTmp);
			imgZoom.setParent(winImg);
			
			winImg.setTitle("Image Zoom");
			winImg.setPosition("center");					
			winImg.doPopup();
			winImg.setVisible(true);					
		}
	}
}

