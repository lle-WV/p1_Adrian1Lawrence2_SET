import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements ActionListener, KeyListener {
	//handles drawing animation
	Timer animationTimer;
	Ball b;
	
	public void paint(Graphics g) {
		super.paintComponent(g);//wipes the previous paint screen
		b.paint(g);
	}

	public MainPanel() {
		JFrame f = new JFrame("Window name here");
		f.setSize(800,600); //width and height of window
		
		//set default action for x button
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add this panel to the JFrame
		//allows connection with "drawing"
		f.add(this);
		
		//setup animation timer
		animationTimer = new Timer(16, this); 
		animationTimer.start();
		
		//instantiate the rest of the instance variables
		b = new Ball();
		
		f.setVisible(true);
	}
	
	//this method is invoked/called by the timer
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//call the frame to refresh
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
