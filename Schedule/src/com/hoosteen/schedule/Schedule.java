package com.hoosteen.schedule;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.hoosteen.schedule.ClassTime.Day;
import com.hoosteen.tree.Node;
import com.hoosteen.tree.Tree;
import com.hoosteen.tree.TreeComp;

public class Schedule {

	static Schedule mainSchedule;
	public static ScheduleDisplay display;
	
	ArrayList<Class> classList = new ArrayList<Class>();
	
	public static void main(String[] args){
		mainSchedule = new Schedule();
		
		TreeComp tc = new TreeComp(mainSchedule.getScheduleTree());		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.add(tc);
		f.setVisible(true);
	}
	
	public Schedule(){
		
		/*
		Professor den = new Professor("Denny Gulick");
		Professor fran = new Professor("Franecs Gulick");
		ClassTime denLecture = new ClassTime("8:00 AM", "8:50 AM", Day.MON, Day.WED, Day.FRI);
		ClassTime franLecture = new ClassTime("10:00 AM", "10:50 AM", Day.MON, Day.WED, Day.FRI);
		math.addSection(new Section(den, denLecture, new ClassTime("8:00 AM", "9:20 AM", Day.TUES, Day.THURS)));
		math.addSection(new Section(den, denLecture, new ClassTime("9:30 AM", "10:50 AM", Day.TUES, Day.THURS)));
		math.addSection(new Section(den, denLecture, new ClassTime("11:00 AM", "12:20 PM", Day.TUES, Day.THURS)));		
		
		math.addSection(new Section(fran, franLecture, new ClassTime("8:00 AM", "9:20 AM", Day.TUES, Day.THURS)));
		math.addSection(new Section(fran, franLecture, new ClassTime("9:30 AM", "10:50 AM", Day.TUES, Day.THURS)));
		math.addSection(new Section(fran, franLecture, new ClassTime("11:00 AM", "12:20 PM", Day.TUES, Day.THURS)));
		addClass(math);
		*/
		
		Class math = new Class("MATH140");
		Professor brad = new Professor("Bradford Sanders");		
		Lecture bradLec = new Lecture(brad, new ClassTime("3:00 PM", "3:50 PM", Day.MON, Day.WED, Day.FRI));
		bradLec.addDiscussion(new ClassTime("12:30 PM", "1:50 PM", Day.TUES, Day.THURS));
		bradLec.addDiscussion(new ClassTime("2:00 PM", "3:20 PM", Day.TUES, Day.THURS));
		bradLec.addDiscussion(new ClassTime("3:30 PM", "4:50 PM", Day.TUES, Day.THURS));
		math.addLecture(bradLec);
		
		Class physics = new Class("PHYS161");
		Professor david = new Professor("Daid Buehrle");
		Lecture davidLec1 = new Lecture(david, new ClassTime("2:00 PM", "3:15 PM", Day.TUES, Day.THURS));
		davidLec1.addDiscussion(new ClassTime("9:00 AM", "9:50 AM", Day.MON));
		davidLec1.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.WED));
		davidLec1.addDiscussion(new ClassTime("9:00 AM", "9:50 AM", Day.FRI));
		davidLec1.addDiscussion(new ClassTime("10:00 AM", "10:50 AM", Day.FRI));
		davidLec1.addDiscussion(new ClassTime("3:00 PM", "3:50 PM", Day.MON));
		davidLec1.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.MON));
		davidLec1.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.WED));
		physics.addLecture(davidLec1);
		
		Lecture davidLec2 = new Lecture(david, new ClassTime("2:00 PM", "2:50 PM", Day.MON, Day.WED, Day.FRI));
		davidLec2.addDiscussion(new ClassTime("3:00 PM", "4:50 PM", Day.TUES));
		davidLec2.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.TUES));
		davidLec2.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.THURS));
		davidLec2.addDiscussion(new ClassTime("10:00 AM", "10:50 AM", Day.FRI));
		davidLec2.addDiscussion(new ClassTime("11:00 AM", "11:50 AM", Day.FRI));
		davidLec2.addDiscussion(new ClassTime("3:00 PM", "3:50 PM", Day.THURS));
		davidLec2.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.TUES));
		davidLec2.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.THURS));
		physics.addLecture(davidLec2);		
		
		Class compSci = new Class("CMSC132");
		Professor laur = new Professor("Laurence Herman");
		Lecture laurLec1 = new Lecture(laur, new ClassTime("10:00 AM", "10:50 AM", Day.MON, Day.WED, Day.FRI));
		laurLec1.addDiscussion(new ClassTime("11:00 AM", "11:50 AM", Day.MON, Day.WED));
		laurLec1.addDiscussion(new ClassTime("12:00 PM", "12:50 PM", Day.MON, Day.WED));
		laurLec1.addDiscussion(new ClassTime("1:00 PM", "1:50 PM", Day.MON, Day.WED));
		laurLec1.addDiscussion(new ClassTime("2:00 PM", "2:50 PM", Day.MON, Day.WED));
		compSci.addLecture(laurLec1);
		
		Lecture laurLec2 = new Lecture(laur, new ClassTime("11:00 AM", "11:50 AM", Day.MON, Day.WED, Day.FRI));
		laurLec2.addDiscussion(new ClassTime("8:00 AM", "8:50 AM", Day.MON, Day.WED));
		laurLec2.addDiscussion(new ClassTime("10:00 AM", "10:50 AM", Day.MON, Day.WED));
		compSci.addLecture(laurLec2);
		
		Lecture laurLec3 = new Lecture(laur, new ClassTime("2:00 PM", "2:50 PM", Day.MON, Day.WED, Day.FRI));
		laurLec3.addDiscussion(new ClassTime("8:00 AM", "8:50 AM", Day.MON, Day.WED));
		laurLec3.addDiscussion(new ClassTime("9:00 AM", "9:50 AM", Day.MON, Day.WED));
		laurLec3.addDiscussion(new ClassTime("11:00 AM", "11:50 AM", Day.MON, Day.WED));
		compSci.addLecture(laurLec3);
		
		Professor compSciTBA = new Professor("Comp Sci TBA");
		Lecture compSciTBALec = new Lecture(compSciTBA, new ClassTime("12:00 PM", "12:50 PM", Day.MON, Day.WED, Day.FRI));
		compSciTBALec.addDiscussion(new ClassTime("10:00 AM", "10:50 AM", Day.MON, Day.WED));
		compSciTBALec.addDiscussion(new ClassTime("3:00 PM", "3:50 PM", Day.MON, Day.WED));
		compSciTBALec.addDiscussion(new ClassTime("4:00 PM", "4:50 PM", Day.MON, Day.WED));
		compSci.addLecture(compSciTBALec);
		
		Class enee = new Class("ENEE101");
		Professor eneeTBA = new Professor("ENEE TBA");
		Lecture eneeTBALec = new Lecture(eneeTBA, new ClassTime("4:00 PM", "5:15 PM", Day.THURS));
		eneeTBALec.addDiscussion(new ClassTime("8:00 AM", "9:50 AM", Day.MON, Day.WED));
		eneeTBALec.addDiscussion(new ClassTime("10:00 AM", "11:50 AM", Day.MON, Day.WED));
		eneeTBALec.addDiscussion(new ClassTime("12:00 PM", "1:50 PM", Day.MON, Day.WED));
		eneeTBALec.addDiscussion(new ClassTime("2:00 PM", "3:50 PM", Day.MON, Day.WED));
		eneeTBALec.addDiscussion(new ClassTime("8:00 AM", "9:50 AM", Day.TUES, Day.THURS));
		eneeTBALec.addDiscussion(new ClassTime("10:00 AM", "11:50 AM", Day.TUES, Day.THURS));
		eneeTBALec.addDiscussion(new ClassTime("12:00 PM", "1:50 PM", Day.TUES, Day.THURS));
		eneeTBALec.addDiscussion(new ClassTime("2:00 PM", "3:50 PM", Day.TUES, Day.THURS));
		enee.addLecture(eneeTBALec);
		
		addClass(enee);
		addClass(physics);
		addClass(math);
		addClass(compSci);
		
		//Displays Schedule
		display = new ScheduleDisplay(this);	
	}
	
	public Tree getScheduleTree(){
		Tree t = new Tree();
		for(Class c : mainSchedule.getClassList()){
			Node classNode = new Node(c.className,null);
			for(Lecture l : c.getLectureList()){
				Node lectureNode = new Node(l.toString(),l.getLectureTime());
				for(ClassTime dis : l.getDiscussionList()){				
					lectureNode.addNode(new Node(dis.toString(), dis));
				}
				classNode.addNode(lectureNode);
			}
			t.addNode(classNode);
		}
		return t;
	}
	
	public ArrayList<Class> getClassList(){
		return classList;
	}
	
	public void addClass(Class c){
		classList.add(c);
	}
}