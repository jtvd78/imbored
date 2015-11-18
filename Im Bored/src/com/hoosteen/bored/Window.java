package com.hoosteen.bored;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Window extends Drawable{
	
	float savedX;
	float savedY;
	int savedWidth;
	int savedHeight;
	
	BoredComp parent;
	
	boolean minimized = false;
	boolean maximized = false;
	
	WindowContentArea contentArea;
	
	public Window(BoredComp parent, int x, int y){	
		//x,y,width,height
		super(x,y,100,100);
		
		this.parent = parent;
		contentArea = new WindowContentArea(this);
		saveWindowState();
	}
	
	public void draw(Graphics g){
		
		fillRect(g,getTitleBarRect(),Color.blue);
		fillRect(g,getCloseBoxRect(),Color.red);	
		drawRect(g,new Rectangle((int)x + getWidth() - 20, (int)y, 20, 19),Color.black);
		drawRect(g,new Rectangle((int)x + getWidth() - 40, (int)y, 20, 19),Color.black);
		drawRect(g,new Rectangle((int)x + getWidth() - 60, (int)y, 20, 19),Color.black);		
		
		g.setColor(Color.black);
		//Line for minimize
		g.drawLine((int)x + getWidth() - 60 + 4, (int)y + 10, (int)x + getWidth() - 60 + 16, (int)y + 10);
		
		//Rect for Minimize/Maximize
		if(maximized){
			g.drawRect((int)x + getWidth() - 40 +  7, (int)y + 5, 8, 8);
			g.drawRect((int)x + getWidth() - 40 + 5, (int)y + 7, 8, 8);
		}else{
			g.drawRect((int)x + getWidth() - 40 + 5, (int)y + 5, 10,10);
		}
		
		//X Marks for close box		
		g.drawLine((int)x + width - 17, (int)y + 3, (int)x + width - 3, (int)y + 17);
		g.drawLine((int)x + width - 3, (int)y + 3, (int)x + width - 17, (int)y + 17);	
		
		contentArea.draw(g);
		
		//Rect for resize box
		drawRect(g,getResizeBoxRect(),Color.black);
		
		//Surrounding Black Frame
		drawRect(g,getFrameRect(),Color.black);
	}
	
	public void update(){
		if(contentArea != null)
		contentArea.update();
	}
	
	public void updateContentArea(){
		contentArea.refresh();
	}
	
	/** Returns true if action is taken, returns false if no action is taken */
	public boolean handleInput(int mouseX, int mouseY) {
		if(getCloseBoxRect().contains(mouseX, mouseY)){
			parent.getWindowList().remove(this);
		}else if(getMaximizeBoxRect().contains(mouseX, mouseY)){
			if(maximized){
				restore();
			}else{
				maximize(parent.getWidth(),parent.getHeight());
			}
		}else if(getMinimizeBoxRect().contains(mouseX, mouseY)){
			minimize();					
		}else{
			return false;
		}
		
		//repaint if action is taken
		parent.repaint();	
		return true;		
	}
	
	public void minimize(){
		minimized = true;
	}
	
	private void saveWindowState(){
		savedX = x;
		savedY = y;
		savedWidth = width;
		savedHeight = height;
	}
	
	private void loadWindowState(){
		x = savedX;
		y = savedY;
		width = savedWidth;
		height = savedHeight;
	}
	
	public void unminimize(){		
		minimized = false;
	}
	
	public void restore(){
		loadWindowState();
		updateContentArea();
		maximized = false;		
	}
	
	public void maximize(int w, int h){
		saveWindowState();
		
		x = 0;
		y = 0;
		width = w;
		height = h - 40;
		
		updateContentArea();
		maximized = true;
	}
	
	public boolean isMaximized() {
		return maximized;
	}
	
	public boolean isMinimized(){
		return minimized;
	}
	
	public Rectangle getResizeBoxRect(){
		return new Rectangle((int)x + width - 5, (int)y + height - 5, 5, 5);
	}
	
	public Rectangle getCloseBoxRect(){
		return new Rectangle((int)x + getWidth() - 20, (int)y, 20, 20);
	}
	
	public Rectangle getMaximizeBoxRect(){
		return new Rectangle((int)x + getWidth() - 40, (int)y, 20,20);
	}
	
	public Rectangle getMinimizeBoxRect(){
		return new Rectangle((int)x + getWidth() - 60, (int)y, 20,20);
	}
	
	public Rectangle getTitleBarRect(){
		return new Rectangle((int)x,(int) y, width, 20);
	}
}