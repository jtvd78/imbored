package com.hoosteen.bored;

import java.awt.Color;
import java.awt.Graphics;

public class Box extends Drawable{
	
	float vx = 1.0f;
	float vy = 1.0f;
	
	Color color;
	
	public Box(){
		super(0,0,100,100);
		color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}
	
	public Box(float x, float y, int width, int height){
		super(x,y,width,height);
		color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}

	@Override
	public void draw(Graphics g) {
		fillRect(g,getFrameRect(),color);
	}
	
	public void update(){
		x += vx;
		y += vy;
	}
	
	public void bounceX(){
		vx *= -1;
	}
	
	public void bounceY(){
		vy *= -1;
	}
}