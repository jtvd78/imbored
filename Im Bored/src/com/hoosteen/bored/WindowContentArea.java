package com.hoosteen.bored;

import java.awt.Color;
import java.awt.Graphics;

public class WindowContentArea extends Drawable{
	
	Box b;
	Window parent;
	
	public WindowContentArea(Window parent){
		super(parent.getX(), parent.getY() + 20, parent.getWidth(), parent.getHeight()-20);	
		b = new Box(x,y,30,30);
		this.parent = parent;
	}	

	@Override
	public void draw(Graphics g) {
		fillRect(g,getFrameRect(),Color.white);		
		b.draw(g);
	}
	
	public void update(){
		b.update();
		
		if(b.getX() < x || b.getX() + b.getWidth() > width + x){
			b.bounceX();
		}
		if(b.getY() < y || b.getY() + b.getHeight()> height + y){
			b.bounceY();
		}
	}
	
	public void refresh(){
		x = parent.getX();
		y = parent.getY() + 20;
		width = parent.getWidth();
		height = parent.getHeight() - 20;
	}
}