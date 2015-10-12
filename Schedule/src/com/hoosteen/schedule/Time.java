package com.hoosteen.schedule;

public class Time {

	int hour;
	int min;
	boolean PM;
	
	public Time(int hour, int min, boolean PM){
		this.hour = hour;
		this.min = min;
		this.PM = PM;
	}
	
	public Time(String timeString){
		//System.out.println(timeString);
		
		int colanPos = timeString.indexOf(':');
		int spacePos = timeString.indexOf(' ');
		String first = timeString.substring(0, colanPos);
		String second = timeString.substring(colanPos+1, spacePos);
		String end = timeString.substring(spacePos+1, timeString.length());
		
		hour = Integer.parseInt(first);
		min = Integer.parseInt(second);
		if(end.toLowerCase().equals("am")){
			PM = false;
		}else{
			PM = true;
		}
	}
	
	public String toString(){
		String out = hour + ":";
		if(min < 10){
			out += "0" + min;
		}else{
			out += min;
		}
		if(PM){
			out += " PM";
		}else{
			out+= " AM";
		}
		return out;
	}
	
	public int toMinutes(){
		int result = 0;
		if(PM && hour != 12){
			result += 12 * 60;
		}else if(!PM && hour == 12){
			result -= hour*60;
		}
		result += hour * 60;
		result += min;
		return result;
	}
}