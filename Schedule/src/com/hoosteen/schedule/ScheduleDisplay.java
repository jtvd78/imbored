package com.hoosteen.schedule;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class ScheduleDisplay extends JComponent{
	
	Schedule schedule;
	JFrame frame;
	
	public ScheduleDisplay(Schedule s){
		this.schedule = s;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.add(this);	
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		int width = getWidth();
		int height = getHeight();
		
		double minPerPixel = 24.0*60/width;
		double pixelPerDay = ((double)height)/5;
		
		g.setColor(Color.BLACK);		
		g.fillRect(0, 0, width, height);
		
		for(Class c : schedule.getClassList()){
			for(Lecture l : c.getLectureList()){
				
				if(l.getLectureTime().isHidden()){
					continue;
				}
				
				for(ClassTime.Day d : l.getLectureTime().getDayList()){
					int day = getDayInt(d);
					
					int start = l.getLectureTime().getStartInMinutes();
					int end = l.getLectureTime().getEndInMinutes();
					int length = end-start;
					
					g.setColor(l.getColor());
					g.fillRect((int)(start/minPerPixel), (int)(day*pixelPerDay),(int) (length/minPerPixel), (int)pixelPerDay);
					g.setColor(Color.GREEN);
					g.drawRect((int)(start/minPerPixel), (int)(day*pixelPerDay),(int) (length/minPerPixel), (int)pixelPerDay);
				}
				
				for(ClassTime dis : l.getDiscussionList()){				
					
					if(dis.isHidden()){
						continue;
					}					
					
					//Discussion
					for(ClassTime.Day d : dis.getDayList()){
						int day = getDayInt(d);
						
						int start = dis.getStartInMinutes();
						int end = dis.getEndInMinutes();
						int length = end-start;
						
						g.setColor(l.getColor());
						g.fillRect((int)(start/minPerPixel), (int)(day*pixelPerDay),(int) (length/minPerPixel), (int)pixelPerDay);
						g.setColor(Color.RED);
						g.drawRect((int)(start/minPerPixel), (int)(day*pixelPerDay),(int) (length/minPerPixel), (int)pixelPerDay);
					}	
				}
			}
		}
	}
	
	public int getDayInt(ClassTime.Day d){
		switch(d){
		case MON: return 0;
		case TUES: return 1;
		case WED: return 2;
		case THURS: return 3;
		case FRI: return 4;
		default: return 6;
		}
	}
}