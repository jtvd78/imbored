package com.hoosteen.bored;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Taskbar{
	
	int height = 40;
	
	Image logo;
	BoredComp parentComp;
	
	//ArrayList<Window> windowList = new ArrayList<Window>();
	
	public Taskbar(BoredComp parentComp){
		this.parentComp = parentComp;
		logo = ImageLoader.loadImage("/logo.png");
	}	
	
	public void draw(Graphics g){		
		fillRect(g,getFrameRect(),Color.blue);	
		g.drawImage(logo,0,parentComp.getHeight()-height,40,40,null);	
		
		int ctr = 1;
		for(Window w : parentComp.getWindowList()){
			g.setColor(Color.black);
			if(w.isMinimized()){
				g.fillRect(42 * ctr, parentComp.getHeight() - 40, 40, 40);
			}else{
				g.drawRect(42 * ctr, parentComp.getHeight() - 40, 40, 40);
			}			
			ctr++;
		}
	}
	
	public Rectangle getFrameRect(){
		return new Rectangle(0,parentComp.getHeight() - 40,parentComp.getWidth(), height);
	}
	
	public int getHeight(){
		return height;
	}
	
	void fillRect(Graphics g, Rectangle r, Color c){
		g.setColor(c);
		g.fillRect(r.x,r.y,r.width,r.height);
	}

	public void handleInput(int x, int y) {
		int ctr = 1;
		for(Window w : parentComp.getWindowList()){
			Rectangle r = new Rectangle(42 * ctr, parentComp.getHeight() - 40, 40, 40);
			if(w.isMinimized() && r.contains(x,y)){
				w.unminimize();
				parentComp.repaint();
			}				
			ctr++;
		}
	}
}