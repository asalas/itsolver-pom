package itsolver.controller.projectmgmt;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Project;
import itsolver.model.entity.ProjectResource;
import itsolver.model.entity.Resource;
import itsolver.model.entity.ResourceCategory;
import itsolver.model.entity.SelectedResource;
import itsolver.service.ProjectMgmtService;
import itsolver.utils.ResourceRow;
import itsolver.utils.ResourceTreeitemRenderer;
import itsolver.utils.TreeItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Rows;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;

@SuppressWarnings("serial")
public class ProjectResources extends GenericZkComposer {
	public Rows rowsResourceList;
	public Tree resourcesTree;
	public Grid  grdEditResource;
	public Button btnSaveResource;
	
	private Project project;	
	private ProjectMgmtService projectMgmtService;
	private ResourceCategory resourceCategorySelected;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        this.project = (Project)desktop.getAttribute("currentProject");        
        this.projectMgmtService = ServiceLocator.getProjectMgmtService();        
        initResourceTree();        
    }
		
	/*
	 * Creacion del arbol de recursos
	 */
	private void initResourceTree(){				
		ResourceCategory resourceCategory = this.projectMgmtService.getResourceTree();		
		SimpleTreeNode treeNode = getTreeNode(resourceCategory);
		List<SimpleTreeNode> treeNodeList = new ArrayList<SimpleTreeNode>();
		SimpleTreeNode simpleTreeNode;
		//Se revisa la lista para no poner el primer nodo que corresponde a la categoria padre
		for (Iterator iterator = treeNode.getChildren().iterator(); iterator.hasNext();) {
			simpleTreeNode = (SimpleTreeNode) iterator.next();
			treeNodeList.add(simpleTreeNode);
			
		}
		
		SimpleTreeNode root = new SimpleTreeNode("ROOT",treeNodeList);
		
		//create treemodel and assigned its root
		SimpleTreeModel stm = new SimpleTreeModel(root);
		
		//create a PersonTreeitemRenderer
		ResourceTreeitemRenderer resourceTreeitemRenderer = new ResourceTreeitemRenderer(this);
		
		resourcesTree.setModel(stm);
		resourcesTree.setTreeitemRenderer(resourceTreeitemRenderer);
		
	}
	
	private SimpleTreeNode getTreeNode(ResourceCategory resourceCategory){
		SimpleTreeNode treeNode = null; 
		if (resourceCategory.getSubCategoryList() == null || resourceCategory.getSubCategoryList().size() <= 0){
			TreeItem categoryResourceTreeItem = new TreeItem(resourceCategory.getCategoryName(), false, resourceCategory.getId());			
			List<SimpleTreeNode> resourceTreeNodeList = new ArrayList<SimpleTreeNode>();
			TreeItem resourceTreeItem;
			if (resourceCategory.getResourceList() != null && resourceCategory.getResourceList().size() >0 ){
				categoryResourceTreeItem.setHasResourceList(true);
				for (Resource  resource : resourceCategory.getResourceList()) {
					resourceTreeItem = new TreeItem(resource.getResourceName(), true);
					resourceTreeNodeList.add(new SimpleTreeNode(resourceTreeItem, new ArrayList()));
				}
			}
			treeNode = new SimpleTreeNode(categoryResourceTreeItem, resourceTreeNodeList);
			
		}else{
			List<SimpleTreeNode> treeNodeList = new ArrayList<SimpleTreeNode>();
			for (ResourceCategory category : resourceCategory.getSubCategoryList()) {
				treeNodeList.add(getTreeNode(category));
			}
			TreeItem categoryResourceTreeItem = new TreeItem(resourceCategory.getCategoryName(), false, resourceCategory.getId());
			treeNode = new SimpleTreeNode(categoryResourceTreeItem, treeNodeList);
		}
		return treeNode;
	}
	/*
	 * Fin de creacion del arbol de recursos
	 */
	
	public void createResourceForm(Long categoryId){
		resourceCategorySelected = this.projectMgmtService.getResourceCategoryById(categoryId);		
		this.clearResourceForm();
		SelectedResource selectedResource;
		ResourceRow resourceRow;
		ProjectResource projectResourceFound = getProjectResource(categoryId);
		for (Resource resource : resourceCategorySelected.getResourceList()) {
			resourceRow = new ResourceRow(resource);
			rowsResourceList.getChildren().add(resourceRow);
			//Sets selected recources
			if (projectResourceFound == null){
				continue;
			}else{
				for (int j = 0; j < projectResourceFound.getSelectedResourceList().size(); j++) {
					selectedResource = projectResourceFound.getSelectedResourceList().get(j);
					if (selectedResource.getResource().getId().compareTo( resource.getId()) == 0){
						resourceRow.selectedResource();
						resourceRow.setSelectedValue(selectedResource.getSelectedValue());
					}
				}
			}
			
		}
		//Sets selected recources
		
		grdEditResource.setVisible(true);
		btnSaveResource.setVisible(true);
	}
	
	private ProjectResource getProjectResource( Long resourceCategoryId){
		ProjectResource projectResource = null;	
		if (this.project.getProjectResourceList() == null){
			return null;
		}
		this.project.getProjectResourceList().size();
		if (this.project != null && this.project.getProjectResourceList() != null){
			for (ProjectResource pResource : this.project.getProjectResourceList()) {
				if (pResource.getResourceCategoryId().compareTo(resourceCategoryId) == 0){
					projectResource = pResource;
					break;
				}
			}
		}
		return projectResource;
	}
	
	public void clearResourceForm(){		
		//gridResourceContainer.getChildren().clear();
		grdEditResource.setVisible(false);
		rowsResourceList.getChildren().clear();
		btnSaveResource.setVisible(false);
	}
	
	public void onClick$btnSaveResource(){
		List componenttList = rowsResourceList.getChildren();
		///List<SelectedResource> selectedResourceList;
		//Valida si hay lista de recursos en el formulario
		if (componenttList.size() > 0){
			ResourceRow resourceRow;
			int projectResourceIndex = -1;
			ProjectResource projectResource = new ProjectResource();
			projectResource.setResourceCategoryId(this.resourceCategorySelected.getId());
			projectResource.setSelectedResourceList(new ArrayList<SelectedResource>());
			//Valida si hay recursos seleccionados en el proyecto que corresponden a la categoria seleccionada
			// para eliminarlos
			if (this.project.getProjectResourceList() != null){
				projectResourceIndex = getProjectResourceIndex(this.resourceCategorySelected.getId());
				if (projectResourceIndex >= 0){
					ProjectResource resource = (ProjectResource) this.project.getProjectResourceList().get(projectResourceIndex);
					this.project.getProjectResourceList().remove(projectResourceIndex);
					this.projectMgmtService.projectUpdate(this.project);
					this.projectMgmtService.deleteprojectResource(resource);
				}
			}
			saveProjectResources(componenttList, projectResource);
			
			desktop.removeAttribute("currentProject");
			desktop.setAttribute("currentProject", this.project);
			grdEditResource.setVisible(false);
			btnSaveResource.setVisible(false);
		}
	}

	private void saveProjectResources(List componenttList,	
			ProjectResource projectResource) throws WrongValueException{
		ResourceRow resourceRow;
		//Determina si hay recursos seleccionados
		boolean isSelectedtResources = false;
		SelectedResource selectedResource;
		for (Iterator iterator = componenttList.iterator(); iterator.hasNext();) {
			resourceRow = (ResourceRow) iterator.next();
			if( resourceRow.isSelectedResource() ){
				String selectedValue = resourceRow.getSelectedValue();
				if (selectedValue == null || Strings.isBlank(selectedValue)){
					throw new WrongValueException(resourceRow.getComponentValue(), "Value is requeried");
				}				
				selectedResource = new SelectedResource();
				selectedResource.setResource(resourceRow.getResource());
				selectedResource.setSelectedValue(selectedValue);
				projectResource.getSelectedResourceList().add(selectedResource);
				isSelectedtResources = true;
			}
		}
		if ( isSelectedtResources ){			
			this.project.getProjectResourceList().add(projectResource);				
			this.projectMgmtService.projectUpdate(this.project);				
		}
	}
	private int getProjectResourceIndex (Long categoryResourceId){
		int indexFound = -1;
		if (this.project.getProjectResourceList() != null && this.project.getProjectResourceList().size() > 0 ){
			ProjectResource projectResource;
			
			for (int j = 0; j < this.project.getProjectResourceList().size(); j++) {
				projectResource = this.project.getProjectResourceList().get(j);
				if ( projectResource.getResourceCategoryId().compareTo(categoryResourceId)== 0){
					//this.project.getProjectResourceList().remove(j);
					indexFound = j;
					break;
				}
			}
		}
		return indexFound;
	}
}
