package com.hoosteen.tree;

import java.io.File;
import java.util.ArrayList;

public class Path implements Cloneable{

	ArrayList<String> pathList = new ArrayList<String>();
	
	@Override
	public String toString(){
		String s = "";
		for(String ss : pathList){
			s += ss + File.separator;
		}
		return s;
	}
	
	private ArrayList<String> getPathList(){
		return pathList;
	}
	
	private void setPathList(ArrayList<String> pathList){
		this.pathList = pathList;
	}
	
	
	public Path add(String s){
		Path newPath = new Path();
		newPath.setPathList((ArrayList<String>)getPathList().clone());
		newPath.getPathList().add(s);
		return newPath;
	}
	
	public File getFile(){		
		File f = new File(toString());
		return f;		
	}	
}