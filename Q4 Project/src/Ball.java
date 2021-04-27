import java.awt.Color;
import java.awt.Graphics;

public class Ball {

		private int x, y; //location
		private int width;
		private Color c;
		
		public Ball() {
			// job of the const is to give values to instance variables
			x = 0;
			y = 0;
			width = 50;
			c= new Color(50,50,50);
		}
		
		public void paint(Graphics g) {
			g.setColor(this.c);
			g.fillOval(x, y, width, width);
			x++; // if x is constantly updating, the ball should move to the right IF the paint method is called repeatedly with actionPerformed
		}
}
