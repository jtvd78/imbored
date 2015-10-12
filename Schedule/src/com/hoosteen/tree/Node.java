package com.hoosteen.tree;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.hoosteen.schedule.ClassTime;

public class Node {
	
	Node parent;
	String name;
	ClassTime classTime;
	boolean expanded = false;
	
	ArrayList<Node> nodeList = new ArrayList<Node>();
	
	public Node(String name, ClassTime classTime){
		this.name = name;
		this.classTime = classTime;
	}
	
	public ClassTime getClassTime(){
		return classTime;
	}
	public String getName(){
		return name;
	}
	
	private void setParent(Node n){
		this.parent = n;
	}
	
	public synchronized void addNode(Node n){
		n.setParent(this);
		nodeList.add(n);
	}
	
	public synchronized void removeNode(Node n){
		nodeList.remove(n);
	}
	
	public int size(){
		return nodeList.size();
	}
	
	public Node getNode(int index){
		return nodeList.get(index);
	}	
	
	public void setExpanded(boolean e){
		expanded = e;
	}
	
	public boolean isExpanded(){
		return expanded;
	}
	
	public Path getPath(){
		return parent.getPath().add(name);	
	}
	
	public int getLevel(){
		return 1+parent.getLevel();
	}
	
	public void toggleExpanded(){
		expanded = !expanded;
	}
	
	//The next two funtions work well. dont mess them up unless you know what your are doing. Because I dont. 
	
	public Node getVisibleNode(int nu){		
		for(Node n : nodeList){
			if(nu - 1 == 0){
				return n;
			}
			
			//to account for current node
			nu -= 1; 
			
			int count = n.getExpandedNodeCount();
			
			if(n.isExpanded() && n.size() != 0){
				if(nu - count > 0){
					nu-= count;		//next n
				}else if(nu - count <= 0){
					return n.getVisibleNode(nu);
				}
			}			
		}
		return null;
	}
	
	public int getExpandedNodeCount(){
		int ctr = 0;
		
		if(expanded && size() > 0){
			for(Node n : nodeList){
				ctr += n.getExpandedNodeCount()+1;
			}			
		}
		return ctr;
	}

	public void setName(String name) {
		this.name = name;
	}
}