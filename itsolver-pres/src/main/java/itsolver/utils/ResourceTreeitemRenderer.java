package itsolver.utils;

import java.util.List;

import itsolver.controller.projectmgmt.ProjectResources;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

public class ResourceTreeitemRenderer implements TreeitemRenderer {
	
	private ProjectResources projectResources;
	
	public ResourceTreeitemRenderer(ProjectResources _projectResources) {
		this.projectResources	= _projectResources;
	}

	public void render(Treeitem item, Object data) throws Exception {
		SimpleTreeNode t = (SimpleTreeNode)data;
		TreeItem treeItem = (TreeItem)t.getData();
		//Contruct treecells
		//Treecell tcEmail = new Treecell(person.getEmail());
		Treecell tcName = new Treecell(treeItem.getNodeLabel());
		String categoryId = "";
		if (!treeItem.isResource()){//Validates if it's a category resource
			categoryId = treeItem.getCategoryResourceId().toString();
		}
		String hasResourceList = (treeItem.getHasResourceList())?"1":"0";
		Treecell tcCategoryId = new Treecell(categoryId);
		Treecell tcHasResourceList = new Treecell(hasResourceList);
		//TODO mejorar  esto cambiar por un Treerow coustomitzado como el ResourceRow para
		// no hacer la operacio nde recuperar la categorya seleccionada evitar una operacion de base de datos
		Treerow tr = null;
		/*
		 * Since only one treerow is allowed, if treerow is not null,
		 * append treecells to it. If treerow is null, contruct a new
		 * treerow and attach it to item.
		 */
		if(item.getTreerow()==null){
			tr = new Treerow();			
			tr.setParent(item);
		}else{
			tr = item.getTreerow(); 
			tr.getChildren().clear();
		}
		//Attach treecells to treerow
		
		tcName.setParent(tr);		
		tcCategoryId.setParent(tr);
		tcHasResourceList.setParent(tr);
		item.setOpen(false);
		if (!treeItem.isResource() ){
			tr.addEventListener("onClick", new EventListener() {
				
				public void onEvent(Event arg0) throws Exception {
					MouseEvent component = (MouseEvent)arg0;
					Treeitem rowParent = (Treeitem)component.getTarget().getParent();	
					List children = rowParent.getChildren();
					Treerow treerow = (Treerow)children.get(0);
					Treecell lblHasResource = (Treecell)treerow.getChildren().get(2);
					if (lblHasResource.getLabel()  == "1"){
						Treecell lblCategoryId = (Treecell)treerow.getChildren().get(1);
						projectResources.createResourceForm(Long.parseLong(lblCategoryId.getLabel()));
					}else{
						projectResources.clearResourceForm();
					}
				}
			});
		}
	}

}

