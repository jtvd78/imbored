package com.hoosteen.schedule;

import java.awt.Color;
import java.util.ArrayList;

public class Lecture{
	
	Color color;
	Professor professor;
	ClassTime lectureTime;
	ArrayList<ClassTime> discussionList = new ArrayList<ClassTime>();
	
	public Lecture(Professor p, ClassTime lectureTime){
		professor = p;
		this.lectureTime = lectureTime;
		color = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
	}
	
	public void addDiscussion(ClassTime time){
		discussionList.add(time);
	}
	
	public ArrayList<ClassTime> getDiscussionList(){
		return discussionList;
	}
	
	public ClassTime getLectureTime(){
		return lectureTime;
	}
	
	public Professor getProfessor(){
		return professor;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String toString(){
		return lectureTime.toString();
	}

}