package com.hoosteen.tree;


public class Tree extends Node{
	
	public Tree(){
		super("This is the top node. Cool, right?",null);
		expanded = true;
	}	
	
	public int getLevel(){
		return 0;
	}
	
	public void getNodeByPath(Path p){
		//Implement
	}
	
	public Path getPath(){
		return new Path();
	}
}