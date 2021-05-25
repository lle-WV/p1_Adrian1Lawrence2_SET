import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MainPanel implements MouseListener {
	//size of the grid
	int gridRows = 3; 
	int gridCols = 3;
	Tile[][] tiles = new Tile[gridRows][gridCols];

	//constructor for the mainpanel class
	public MainPanel(){

	//create a JFrame object with title
	JFrame f = new JFrame("SET");

	//Set the size of the window
	f.setSize(800,800);

	//exit on close method
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setLayout(new GridLayout(gridRows,gridCols));

	//setup the board
	for(int r = 0; r < tiles.length; r++){
		for(int c = 0; c < tiles[r].length; c++){
			tiles[r][c] = new Tile("queen.jpg", r, c);
			
			//testing the card images on jFrame
			//tiles[0][0] = new Tile("dmRedHashed.jpg", r, c);
			//tiles[0][1] = new Tile("sqBlueHashed.jpg", r, c);
			//tiles[2][1] = new Tile("lnGreenHol.jpg", r, c);
			//add the title to the jFrame
			f.add(tiles[r][c]);
	
			//add mouse listener
			tiles[r][c].addMouseListener(this);
		}
	}

	f.setVisible(true);
}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}