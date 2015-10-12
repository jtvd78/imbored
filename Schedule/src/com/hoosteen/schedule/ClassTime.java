package com.hoosteen.schedule;

import java.util.ArrayList;

public class ClassTime{
	
	public enum Day{
		MON, TUES, WED, THURS, FRI
	}
	
	Time startTime;
	Time endTime;
	
	boolean hidden = true;
	
	ArrayList<Day> dayList = new ArrayList<Day>();
	
	public ClassTime(String startTime, String endTime, Day... days ){
		this.startTime = new Time(startTime);
		this.endTime = new Time(endTime);
		
		for(Day d : days){
			dayList.add(d);
		}
	}
	
	public int getStartInMinutes(){
		return startTime.toMinutes();
	}
	
	public int getEndInMinutes(){
		return endTime.toMinutes();
	}
	
	public ArrayList<Day> getDayList(){
		return dayList;
	}
	
	public String toString(){
		return startTime.toString() + " - " + endTime.toString();
	}
	
	public boolean isHidden(){
		return hidden;
	}
	
	public void setHidden(boolean hidden){
		this.hidden = hidden;
	}
}