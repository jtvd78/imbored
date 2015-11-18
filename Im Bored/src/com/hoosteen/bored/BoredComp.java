package com.hoosteen.bored;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class BoredComp extends JComponent{
	
	int width = -1;
	int height = -1;	
	
	Boolean running = true;
	
	Taskbar taskbar;
	
	Window movingWindow;
	Window resizingWindow;
	ArrayList<Window> windowList = new ArrayList<Window>();
	ArrayList<Window> windowDrawOrderList = new ArrayList<Window>();
	
	public BoredComp(){
		setFocusable(true);	
		
		Listener l = new Listener();
		addMouseListener(l);
		addKeyListener(l);
		addMouseMotionListener(l);
		
		ComponentUpdateListener cul = new ComponentUpdateListener();
		addComponentListener(cul);	
		
		taskbar = new Taskbar(this);
	}
	
	public void start(){
		while(running){
			for(Window w : windowList){
				w.update();
				repaint();
			}
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** Draws everything on screen */
	public void paintComponent(Graphics g){	
		
		//Draw Background as plain black background
		g.setColor(Color.BLACK);
		g.fillRect(0,0,width,height);		
		
		//Draw each window, do not draw if minimized
		for(Window w : windowList){					
			if(!w.isMinimized()){
				w.draw(g);
			}			
		}	
		
		//Draw taskbar
		taskbar.draw(g);
	}
	
	class Listener implements MouseListener, KeyListener, MouseMotionListener{
		
		int px = 0;
		int py = 0;

		@Override
		public void mousePressed(MouseEvent e) {
			
			Window moveToTop = null;
			
			//Iterates backwards through list
			for(int i = windowList.size() - 1; i >= 0; i--){
				Window w = windowList.get(i);
				
				//if window is minimized, do not accept input
				if(w.isMinimized()){
					continue;
				}
				
				if(e.getButton() == 1 && w.getFrameRect().contains(e.getX(), e.getY())){	
					
					if(!w.handleInput(e.getX(), e.getY())){
						if(w.getResizeBoxRect().contains(e.getX(), e.getY())){
							resizingWindow = w;
						}else if(w.getTitleBarRect().contains(e.getX(),e.getY())){
							if(w.isMaximized()){
								w.restore();
								w.setX(e.getX());
								w.setY(e.getY());
							}
							movingWindow = w;
						}
						
						moveToTop = w;
						repaint();
					}			
					
					//Stop searching for boxes once a box is clicked on
					break;
				}
			}
			
			if(moveToTop != null){
				windowList.remove(moveToTop);
				windowList.add(moveToTop);	
				repaint();
			}
			
			//Mouse pressed in taskbar
			if(taskbar.getFrameRect().contains(e.getX(), e.getY())){
				taskbar.handleInput(e.getX(),e.getY());
			}
		}
		
		/** When mouse is released, any window being moved or resized will no longer be moved or resized*/
		public void mouseReleased(MouseEvent arg0) {
			movingWindow = null;
			resizingWindow = null;
		}			

		/**The program handles mouse buttons by itself. Call mouse moved when mouse is dragged*/
		public void mouseDragged(MouseEvent e) {
			mouseMoved(e);
		}

		/** Handles input when the mouse moves on the screen */
		public void mouseMoved(MouseEvent e) {
			
			//Moves box on screen if there is a box to move
			if(movingWindow != null){
				if(e.getX() > 0 && e.getX() < width){
					movingWindow.setX(movingWindow.getX() + (float)(e.getX() - px));
				}
				if(e.getY() > 0 && e.getY() < height - taskbar.getHeight()){
					movingWindow.setY(movingWindow.getY() + (float)(e.getY() - py));
				}		
				movingWindow.updateContentArea();
				repaint();
				
			//Resizes box on screen if there is a box to resize
			}else if(resizingWindow != null){				
				if(e.getX() > resizingWindow.getX() + 60){
					resizingWindow.setWidth(e.getX() -(int) resizingWindow.getX());
				}
				if(e.getY() > resizingWindow.getY() + 25){
					resizingWindow.setHeight(e.getY() - (int)resizingWindow.getY());
				}			
				resizingWindow.updateContentArea();
				repaint();
			}
			
			//Updates previous x and y whenever mouse is moved
			px = e.getX();
			py = e.getY();			
		} 	
		
		//Unused Inherited Methods
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}		
	}
	
	/** Handles resizing of window */
	class ComponentUpdateListener implements ComponentListener{
		
		public void componentResized(ComponentEvent e) {			
			width = e.getComponent().getWidth();
			height = e.getComponent().getHeight();
			repaint();
		}
		
		//Unused Inherited Methods
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}

	public void addWindow(Window window) {
		windowList.add(window);
		repaint();
	}
	
	public ArrayList<Window> getWindowList(){
		return windowList;
	}
}