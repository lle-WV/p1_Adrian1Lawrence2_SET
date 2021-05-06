import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton{

	private Color color;
	ImageIcon img1, img2; //add any other images
	int r, c; //possible location variables

	public Tile(String fileName){
		super(); //call parent Constructor
		color = Color.white;

		//setup icon image
		img1 = new ImageIcon(Tile.class.getResource(fileName));
		//setup addition images here

		//call parent helper methods with super
		//this indicates it is from parent class
		super.setIcon(img1);
		super.setBackground(color);
	}

	//second Tile constructor
	public Tile(String fileName, int row, int col){
		this(fileName);

		r = row;
		c= col;

	}
	
	public void setRowCol(int r, int c){
		this.r = r;
		this.c = c;
	}

}

