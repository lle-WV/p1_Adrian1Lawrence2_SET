import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Board implements MouseListener {

	private ArrayList<Card> deck;
	private ArrayList<Card> discard;
	private ArrayList<Integer> selectedTiles;

	// size of the grid

	int rows = 3;
	int cols = 3;

	Tile[][] board1 = new Tile[rows][cols];
	Card[][] board2 = new Card[rows][cols];

	JFrame frame;

	// constructor for the mainpanel class

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub

		int row = ((Tile) event.getSource()).getRow();
		int col = ((Tile) event.getSource()).getCol();

		// System.out.println(board1[row][col].getFileName());

		selectedTiles.add(row);
		selectedTiles.add(col);

		if (selectedTiles.size() == 6) {

			checkAndRemoveIfSet(selectedTiles.get(0), selectedTiles.get(1), selectedTiles.get(2), selectedTiles.get(3),
					selectedTiles.get(4), selectedTiles.get(5));

			selectedTiles.clear();

		}

	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	public Board() {

		frame = new JFrame("SET");

		// Set the size of the window

		frame.setSize(700, 1000); // makes the tiles just the right size if each card is decreased to 20% size

		// exit on close method

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GridLayout object

		GridLayout layout = new GridLayout(rows, cols);

		// set H and V gaps

		layout.setHgap(10);
		layout.setVgap(10);

		frame.setLayout(layout);

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
		discard = new ArrayList<Card>();

		selectedTiles = new ArrayList<Integer>();

		clear();

		for (int i = 1; i <= 3; i++) {

			for (int j = 1; j <= 3; j++) {

				for (int k = 1; k <= 3; k++) {

					for (int l = 1; l <= 3; l++) {

						deck.add(new Card(i, j, k, l));

					}

				}

			}

		}

		deal();

		frame.setVisible(true);

	}

	public void deal() {

		for (int i = 0; i < board2.length; i++) {

			for (int j = 0; j < board2[i].length; j++) {

				if (board2[i][j] == null) {

					replace(i, j);

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

			replace(rowCard1, colCard1);
			replace(rowCard2, colCard2);
			replace(rowCard3, colCard3);

			return true;

		} else {

			return false;

		}
	}

	public void clear() {

		for (int i = 0; i < board2.length; i++) {

			for (int j = 0; j < board2[i].length; j++) {

				if (board2[i][j] != null) {

					replace(i, j);

				}

			}

		}

	}

	public void replace(int row, int col) {

		int random = (int) (Math.random() * deck.size());

		Card temp = deck.remove(random);

		if (board1[row][col] != null) {

			frame.remove(board1[row][col]);

		}

		board1[row][col] = new Tile(temp.getFileName(), row, col);
		board1[row][col].addMouseListener(this);

		frame.add(board1[row][col]);
		frame.setVisible(true);

		board2[row][col] = temp;

	}

}
