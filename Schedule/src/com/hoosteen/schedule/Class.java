package com.hoosteen.schedule;

import java.awt.Color;
import java.util.ArrayList;

public class Class {
	
	Color color;
	String className;
	
	ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	
	public Class(String className){
		this.className = className;
		color = new Color((int)(255*Math.random()), (int)(255*Math.random()), (int)(255*Math.random()));
	}
	
	public void addLecture(Lecture lecture){
		lectureList.add(lecture);
	}
	
	public ArrayList<Lecture> getLectureList(){
		return lectureList;
	}
	
	public Color getColor(){
		return color;
	}
	
}