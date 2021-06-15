import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton {

	// Declare the necessary instance variables

	private Color color;
	private ImageIcon image;
	private String fileName;

	private int row, col;

	public Tile(String fileName) {

		// Call the parent constructor

		super();

		// Set the color to white

		color = Color.white;

		// Initialize the file name corresponding to the tile

		this.fileName = fileName;

		// Configure the tile image correctly
		
		image = new ImageIcon(Tile.class.getResource(fileName));

		// Call the parent helper methods to complete the image configuration

		super.setIcon(image);
		super.setBackground(color);
	}

	public Tile(String fileName, int row, int col) {

		// Call the first constructor

		this(fileName);

		// Initialize the row and column values

		this.row = row;
		this.col = col;

	}

	// Getters and setters

	public void setRowCol(int row, int col) {

		this.row = row;
		this.col = col;

	}

	public int getRow() {

		return row;

	}

	public int getCol() {

		return col;

	}

	public String getFileName() {

		return fileName;

	}

}
