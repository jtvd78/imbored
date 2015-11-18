package com.hoosteen.bored;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Drawable {
	
	float x;
	float y;
	int width;
	int height;
	
	public Drawable(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void draw(Graphics g);
	
	protected void drawRect(Graphics g, Rectangle r, Color c){
		g.setColor(c);
		g.drawRect(r.x,r.y,r.width,r.height);
	}
	
	protected void fillRect(Graphics g, Rectangle r, Color c){
		g.setColor(c);
		g.fillRect(r.x,r.y,r.width,r.height);
	}
	
	public Rectangle getFrameRect(){
		return new Rectangle((int)x,(int) y,width,height);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}