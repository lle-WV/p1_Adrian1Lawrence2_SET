import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board implements KeyListener, MouseListener {

	// The deck list contains all undealt cards
	private ArrayList<Card> deck;

	// The discard list contains all discarded cards
	private ArrayList<Card> discard;

	private ArrayList<Integer> selectedTileIndices;

	// Number of rows and columns of cards displayed on the board
	private int rows = 3;
	private int cols = 4;

	// 2D Array of tiles displayed on the JFrame
	private Tile[][] tileBoard = new Tile[rows][cols];

	// 2D Array of cards that correspond to the tiles
	private Card[][] cardBoard = new Card[rows][cols];

	JFrame frame;

	public void mouseClicked(MouseEvent event) {

		// Obtains the origin of the tile from the MouseEvent object
		int row = ((Tile) event.getSource()).getRow();
		int col = ((Tile) event.getSource()).getCol();

		// Adds the indices of the selected tile to the selectedTileIndices list
		selectedTileIndices.add(row);
		selectedTileIndices.add(col);

		// Runs if the player selects three cards (six indices)
		if (selectedTileIndices.size() == 6) {

			// Runs if the three selected cards constitute a set
			if (isSet(cardBoard[selectedTileIndices.get(0)][selectedTileIndices.get(1)],
					cardBoard[selectedTileIndices.get(2)][selectedTileIndices.get(3)],
					cardBoard[selectedTileIndices.get(4)][selectedTileIndices.get(5)])) {

				/*
				 * Discards the three selected cards and replaces them with three new cards
				 * randomly chosen from the deck
				 */

				replace(selectedTileIndices.get(0), selectedTileIndices.get(1));
				replace(selectedTileIndices.get(2), selectedTileIndices.get(3));
				replace(selectedTileIndices.get(4), selectedTileIndices.get(5));

			}

			/*
			 * Clears the player's selected cards (prevents the player from selecting more
			 * than three cards
			 */

			selectedTileIndices.clear();

		}

	}

	// None of these methods are necessary for the functionality of the game

	public void mouseEntered(MouseEvent event) {

	}

	public void mouseExited(MouseEvent event) {

	}

	public void mousePressed(MouseEvent event) {

	}

	public void mouseReleased(MouseEvent event) {

	}

	public void keyPressed(KeyEvent event) {

		System.out.println("HA");

		// Runs when the player presses "x" on the keyboard
		if (event.getKeyCode() == 88) {

			System.out.println("HA");

			boolean containsSet = false;

			/*
			 * Cycles through all possible combinations of three cards that are displayed on
			 * the screen and checks if any of them constitute a set
			 */

			for (int i = 0; i < rows * cols - 2; i++) {

				for (int j = i + 1; j < rows * cols - 1; j++) {

					for (int k = j + 1; k < rows * cols; k++) {

						if (isSet(cardBoard[i % rows][i / rows], cardBoard[j % rows][j / rows],
								cardBoard[k % rows][k / rows])) {

							containsSet = true;

						}

					}

				}

			}

			// Runs if no set is found
			if (containsSet) {

			}

		}

	}

	public void keyReleased(KeyEvent event) {

	}

	public void keyTyped(KeyEvent event) {

	}

	public Board() {

		frame = new JFrame("SET");
		frame.setSize(700, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);

		GridLayout layout = new GridLayout(rows, cols);

		layout.setHgap(10);
		layout.setVgap(10);

		frame.setLayout(layout);

		deck = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		selectedTileIndices = new ArrayList<Integer>();

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

		for (int i = 0; i < cardBoard.length; i++) {

			for (int j = 0; j < cardBoard[i].length; j++) {

				if (cardBoard[i][j] == null) {

					replace(i, j);

				}

			}

		}

	}

	public boolean isSet(Card card1, Card card2, Card card3) {

		boolean shape = false;
		boolean color = false;
		boolean shading = false;
		boolean quantity = false;

		if ((card1.getColor() == card2.getColor() && card1.getColor() == card3.getColor())
				|| (card1.getColor() != card2.getColor() && card1.getColor() != card3.getColor()
						&& card2.getColor() != card3.getColor())) {

			color = true;

		}

		if ((card1.getShape() == card2.getShape() && card1.getShape() == card3.getShape())
				|| (card1.getShape() != card2.getShape() && card1.getShape() != card3.getShape()
						&& card2.getShape() != card3.getShape())) {

			shape = true;

		}

		if ((card1.getShading() == card2.getShading() && card1.getShading() == card3.getShading())
				|| (card1.getShading() != card2.getShading() && card1.getShading() != card3.getShading()
						&& card2.getShading() != card3.getShading())) {

			shading = true;

		}

		if ((card1.getQuantity() == card2.getQuantity() && card1.getQuantity() == card3.getQuantity())
				|| (card1.getQuantity() != card2.getQuantity() && card1.getQuantity() != card3.getQuantity()
						&& card2.getQuantity() != card3.getQuantity())) {

			quantity = true;

		}

		if (color && shape && shading && quantity) {

			return true;

		} else {

			return false;

		}

	}

	public void clear() {

		for (int i = 0; i < cardBoard.length; i++) {

			for (int j = 0; j < cardBoard[i].length; j++) {

				if (cardBoard[i][j] != null) {

					replace(i, j);

				}

			}

		}

	}

	public void replace(int row, int col) {

		int random = (int) (Math.random() * deck.size());

		Card temp = deck.remove(random);

		cardBoard[row][col] = temp;

		discard.add(temp);

		if (tileBoard[row][col] != null) {

			frame.remove(tileBoard[row][col]);

		}

		tileBoard[row][col] = new Tile(temp.getFileName(), row, col);
		tileBoard[row][col].addMouseListener(this);

		frame.add(tileBoard[row][col]);
		frame.setVisible(true);

		cardBoard[row][col] = temp;

	}

}
