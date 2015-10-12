package com.hoosteen.tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.hoosteen.schedule.Schedule;

public class TreeComp extends JComponent {
	
	int nodeHeight = 20;
	int levelSpacing = 15;
	int boxSize = 9;
	
	Tree tree;
	Node servers;
	Node transfers;
		Node downloads;
		Node uploads;
	Node selectedNode = null;
	
	public TreeComp(Tree tree){		
		Listener l = new Listener();
		addMouseListener(l);
		addMouseMotionListener(l);
		addKeyListener(l);
		
		setFocusable(true);
		
		this.tree = tree;
		selectedNode = tree.getVisibleNode(1);
	}
	
	//Drawing is sketchy, but it works. 
	//I don't like this nodeCtr
	int nodeCtr = -1;
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if(tree == null){
			g.setColor(Color.black);
			g.drawString("loading", 20, 20);
		}else{
			drawNode(tree, 0, g);			
			
			System.out.println(nodeCtr);
			nodeCtr = -1;
		}
		
		
	}
	
	public void drawNode(Node n, int level, Graphics g){	
		
		g.setColor(Color.white);
		
		if(n.getClassTime() != null && !n.getClassTime().isHidden()){
			g.setColor(Color.GREEN);
		}
		
		if(n.equals(selectedNode)){
			g.setColor(new Color(200,200,255));
		}		
		
		Rect nodeRect = nodeRect(nodeCtr,level);
		
		if(level != 0){			
			
			fillRect(nodeRect,g);
			
			g.setColor(Color.black);
			drawRect(nodeRect,g);	
			drawString(n.getName(),nodeRect,g);
			g.drawLine(nodeRect.getX()-levelSpacing/2-levelSpacing,nodeRect.getY()+nodeHeight/2, nodeRect.getX()-levelSpacing/2, nodeRect.getY()+nodeHeight/2);
			
		}
		
		//Draw Expand box if node has children.
		if(n.size() != 0){
			drawExpand(expandRect(nodeCtr,level),g,n.isExpanded());			
		}
		
		nodeCtr++;
		if(n.isExpanded()){
			
			g.setColor(Color.black);
			g.drawLine(nodeRect.getX()-levelSpacing/2,nodeRect.getY()+nodeHeight/2+boxSize/2,nodeRect.getX()-levelSpacing/2,nodeRect.getY()+nodeHeight/2+n.getExpandedNodeCount()*nodeHeight);			
			
			for(int c = 0; c < n.size(); c++){
				drawNode(n.getNode(c),level+1,g);	
			}			
		}
	}
	
	void drawExpand(Rect r, Graphics g, boolean expanded){
		
		g.setColor(Color.black);		
		g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		
		g.setColor(Color.white);
		
		//minus
		g.drawLine(r.getX() + 1, r.getY()  + r.getHeight()/2, r.getX() + r.getWidth() - 2, r.getY()  + r.getHeight()/2);
		
		if(!expanded){
			//plus
			g.drawLine(r.getX() + r.getWidth()/2, r.getY() + 1, r.getX() + r.getWidth()/2, r.getY() + r.getHeight() - 2);
		}
	
	}
	
	void drawString(String s, Rect r, Graphics g){
		
		FontMetrics fm = g.getFontMetrics();
		
		int y = r.getHeight()/2 + r.getY() - (fm.getAscent() + fm.getDescent())/2 + fm.getAscent();
		
		g.drawString(s, r.getX() + 5, y);
	}
	
	void fillRect(Rect r, Graphics g){
		g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
	
	void drawRect(Rect r, Graphics g){
		g.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
	
	public Rect nodeRect(int node, int level){
		return new Rect(level*levelSpacing, node*nodeHeight, getWidth() - level*levelSpacing - 1, nodeHeight);
	}
	
	public Rect expandRect(int node, int level){
		return new Rect((level-1)*levelSpacing + (levelSpacing)/2 - boxSize/2, node*nodeHeight  + nodeHeight/2 -boxSize/2, boxSize,boxSize);
	}
	
	public void nodeLeftClicked(Node n){		
		selectedNode = n;
	}
	
	public void nodeRightClicked(Node n, int x, int y){		
		System.out.println("Right Clicked " + n.getName());
	}
	
	class Listener implements MouseListener, MouseMotionListener, KeyListener{

		int button = 0;
		
		public void mouseDragged(MouseEvent e) {
			//mousePressed(e);
		}
		
		public void mousePressed(MouseEvent e) {
		
			if(tree == null){
				return;
			}
			
			//button
			int b = e.getButton();
			if(b != 0){
				button = b;
			}
			
			int num = e.getY()/nodeHeight+1;
			Node clickedNode = tree.getVisibleNode(num);
			
			//Null is no node was clicked on. 
			if(clickedNode == null){
				return;
			}
			
			int level = clickedNode.getLevel();
			Rect nodeRect = nodeRect(num-1,level);			
			Rect expandRect = expandRect(num-1, level);
			
			if(nodeRect.contains(e.getX(), e.getY())){
				if(button == 1){
					nodeLeftClicked(clickedNode);
				}else if(button == 3){
					nodeRightClicked(clickedNode,e.getX(),e.getY());
				}		
			}else if(expandRect.contains(e.getX(),e.getY())){
				clickedNode.toggleExpanded();
				new Dimension(getWidth(),tree.getExpandedNodeCount()*nodeHeight);
				
			}else{
				return;
			}
			
			repaint();			
		}
		
		public void mouseMoved(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == ' '){
				selectedNode.getClassTime().setHidden(!selectedNode.getClassTime().isHidden());
				Schedule.display.repaint();
				System.out.println("hey");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
	
	class Rect{
		
		int x;
		int y;
		int width;
		int height;
		
		public Rect(int x, int y, int width, int height){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public boolean contains(int xx, int yy){
			if(xx >= x && xx <= (x+width)){
				if(yy >= y && yy <=(y+height)){
					return true;
				}
			}
			return false;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
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
}