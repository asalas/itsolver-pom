package itsolver.utils;

public class TreeItem {	
	private String nodeLabel;
	private Long categoryResourceId;
	//It is used to to indicate if the tree item is a resource other case the tree item is a category
	private boolean isResource= false;
	private boolean hasResourceList = false;
	
	public TreeItem(String label, boolean _isResource) {	
		this.isResource = _isResource;
		this.nodeLabel = label;
	}
	public TreeItem(String label, boolean _isResource, Long categoryId) {
		this.categoryResourceId = categoryId;
		this.isResource = _isResource;
		this.nodeLabel = label;
	}
	public String getNodeLabel() {
		return nodeLabel;
	}
	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}
	public Long getCategoryResourceId() {
		return categoryResourceId;
	}
	public void setCategoryResourceId(Long categoryResourceId) {
		this.categoryResourceId = categoryResourceId;
	}
	public boolean isResource() {
		return isResource;
	}
	public void setResource(boolean isResource) {
		this.isResource = isResource;
	}
	public boolean getHasResourceList() {
		return hasResourceList;
	}
	public void setHasResourceList(boolean hasResourceList) {
		this.hasResourceList = hasResourceList;
	}
}
