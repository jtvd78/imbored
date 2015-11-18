package com.hoosteen.bored;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Start {

	public static void main(String[] args) {
		 new Start().run();
	}

	void run() {	
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		
		BoredComp bc = new BoredComp();
		
		JMenuBar jmb = new JMenuBar();
			JMenu file = new JMenu("File");
				JMenuItem newBox = new JMenuItem("New Box");
				newBox.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						bc.addWindow(new Window(bc,0,0));
					}
					
				});
				file.add(newBox);
		jmb.add(file);
		f.setJMenuBar(jmb);
		
		f.add(bc);
		f.setVisible(true);
		
		bc.start();
	}
}