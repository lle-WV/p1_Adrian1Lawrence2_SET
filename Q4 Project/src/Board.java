import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Board implements MouseListener {

	private ArrayList<Card> deck;
	private ArrayList<Card> discard;

	// size of the grid

	int gridRows = 3;
	int gridCols = 3;

	Tile[][] board1 = new Tile[gridRows][gridCols];
	Card[][] board2 = new Card[gridRows][gridCols];

	// constructor for the mainpanel class

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

	public Board() {

		JFrame f = new JFrame("SET");

		// Set the size of the window

		f.setSize(700, 1000); // makes the tiles just the right size if each card is decreased to 20% size

		// exit on close method

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GridLayout object

		GridLayout layout = new GridLayout(gridRows, gridCols);

		// set H and V gaps

		layout.setHgap(10);
		layout.setVgap(10);

		f.setLayout(layout);

		// set visible to false until player selects start

		// setup the board

		/*
		 * 
		 * int random;
		 * 
		 * for (int r = 0; r < tiles.length; r++) {
		 * 
		 * for (int c = 0; c < tiles[r].length; c++) {
		 * 
		 * random = (int) (Math.random() * (81)) + 1;
		 * 
		 * String randomToString;
		 * 
		 * if (random < 10) {
		 * 
		 * randomToString = "0" + random;
		 * 
		 * } else {
		 * 
		 * randomToString = "" + random;
		 * 
		 * }
		 * 
		 * tiles[r][c] = new Tile("00" + randomToString + ".jpg", r, c);
		 * 
		 * // add the title to the jFrame f.add(tiles[r][c]);
		 * 
		 * // add mouse listener tiles[r][c].addMouseListener(this); } }
		 * 
		 */

		deck = new ArrayList<Card>();

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				for (int k = 0; k < 3; k++) {

					for (int l = 0; l < 3; l++) {

						deck.add(new Card(i, j, k, l));

					}

				}

			}

		}

		deal();

		f.setVisible(true);

	}

	public void deal() {

		int random;

		for (int i = 0; i < board2.length; i++) {

			for (int j = 0; j < board2[i].length; j++) {

				if (board2[i][j] == null) {

					random = (int) (Math.random() * deck.size());

					Card temp = deck.remove(random);

					board1[i][j] = new Tile(temp.getFileName(), i, j);
					board2[i][j] = temp;

				}

			}

		}

	}

	public boolean checkAndRemoveIfSet(int rowCard1, int colCard1, int rowCard2, int colCard2, int rowCard3,
			int colCard3) {

		boolean shape = false;
		boolean color = false;
		boolean shading = false;
		boolean quantity = false;

		if ((board2[rowCard1][colCard1].getColor() == board2[rowCard2][colCard2].getColor()
				&& board2[rowCard1][colCard1].getColor() == board2[rowCard3][colCard3].getColor())
				|| (board2[rowCard1][colCard1].getColor() != board2[rowCard2][colCard2].getColor()
						&& board2[rowCard1][colCard1].getColor() != board2[rowCard3][colCard3].getColor()
						&& board2[rowCard2][colCard2].getColor() != board2[rowCard3][colCard3].getColor())) {

			color = true;

		}

		if ((board2[rowCard1][colCard1].getShape() == board2[rowCard2][colCard2].getShape()
				&& board2[rowCard1][colCard1].getShape() == board2[rowCard3][colCard3].getShape())
				|| (board2[rowCard1][colCard1].getShape() != board2[rowCard2][colCard2].getShape()
						&& board2[rowCard1][colCard1].getShape() != board2[rowCard3][colCard3].getShape()
						&& board2[rowCard2][colCard2].getShape() != board2[rowCard3][colCard3].getShape())) {

			shape = true;

		}

		if ((board2[rowCard1][colCard1].getShading() == board2[rowCard2][colCard2].getShading()
				&& board2[rowCard1][colCard1].getShading() == board2[rowCard3][colCard3].getShading())
				|| (board2[rowCard1][colCard1].getShading() != board2[rowCard2][colCard2].getShading()
						&& board2[rowCard1][colCard1].getShading() != board2[rowCard3][colCard3].getShading()
						&& board2[rowCard2][colCard2].getShading() != board2[rowCard3][colCard3].getShading())) {

			shading = true;

		}

		if ((board2[rowCard1][colCard1].getQuantity() == board2[rowCard2][colCard2].getQuantity()
				&& board2[rowCard1][colCard1].getQuantity() == board2[rowCard3][colCard3].getQuantity())
				|| (board2[rowCard1][colCard1].getQuantity() != board2[rowCard2][colCard2].getQuantity()
						&& board2[rowCard1][colCard1].getQuantity() != board2[rowCard3][colCard3].getQuantity()
						&& board2[rowCard2][colCard2].getQuantity() != board2[rowCard3][colCard3].getQuantity())) {

			quantity = true;

		}

		if (shape && color && shading && quantity) {

			discard.add(board2[rowCard1][colCard1]);
			discard.add(board2[rowCard2][colCard2]);
			discard.add(board2[rowCard3][colCard3]);

			board1[rowCard1][colCard1] = null;
			board1[rowCard2][colCard2] = null;
			board1[rowCard3][colCard3] = null;

			board2[rowCard1][colCard1] = null;
			board2[rowCard2][colCard2] = null;
			board2[rowCard3][colCard3] = null;

			deal();

			return true;

		} else {

			return false;

		}
	}

}
