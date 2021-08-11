package kbase.obj;

import java.util.ArrayList;
import java.util.List;

public class TreeObj {
	private String pageId ;
	
	private String title ; 
	
	private List<TreeObj> child = new ArrayList<TreeObj>(); 
	
	public TreeObj() {
		
	} 

	public TreeObj(String pageId) {
		this.pageId = pageId ; 
	}

	public void addChild(TreeObj obj) {
		this.child.add(obj);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public List<TreeObj> getChild() {
		return child;
	}

	public void setChild(List<TreeObj> child) {
		this.child = child;
	} 
	
	
}
