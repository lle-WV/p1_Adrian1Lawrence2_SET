import java.util.ArrayList;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import java.io.File;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Board implements KeyListener, MouseListener {

	// This list contains all undealt cards

	private ArrayList<Card> undealt;

	// This list contains all dealt cards

	private ArrayList<Card> dealt;

	private ArrayList<Integer> selectedTileIndices;

	// Number of rows and columns of cards displayed on the board

	private int rows = 3;
	private int cols = 3;

	// 2D Array of tiles displayed on the JFrame

	private Tile[][] tileBoard = new Tile[rows][cols];

	// 2D Array of cards that correspond to the tiles

	private Card[][] cardBoard = new Card[rows][cols];

	// Declare the JFrame and GridLayout objects

	private JFrame frame;
	private GridLayout layout;

	private final File SET = new File("SET.wav");
	private final File NO_SET = new File("NO_SET.wav");

	public void mouseClicked(MouseEvent event) {

		// Obtain the origin of the tile from the MouseEvent object

		int row = ((Tile) event.getSource()).getRow();
		int col = ((Tile) event.getSource()).getCol();

		// Add the indices of the selected tile to the selectedTileIndices list

		selectedTileIndices.add(row);
		selectedTileIndices.add(col);

		System.out.println(cardBoard[row][col].getFileName());

		/*
		 * If the player selects three cards (equivalent to six indices since each card
		 * has a corresponding row and column)
		 */

		if (selectedTileIndices.size() == 6) {

			// If the three selected cards constitute a set

			if (isSet(cardBoard[selectedTileIndices.get(0)][selectedTileIndices.get(1)],
					cardBoard[selectedTileIndices.get(2)][selectedTileIndices.get(3)],
					cardBoard[selectedTileIndices.get(4)][selectedTileIndices.get(5)])) {

				// Play the SET sound to indicate that the player's set is valid

				playSound(SET);

				/*
				 * Replace the selected cards with with new cards randomly chosen from the
				 * undealt list
				 */

				replaceCard(selectedTileIndices.get(0), selectedTileIndices.get(1), false);
				replaceCard(selectedTileIndices.get(2), selectedTileIndices.get(3), false);
				replaceCard(selectedTileIndices.get(4), selectedTileIndices.get(5), false);

			}

			/*
			 * Clear the player's selected cards (this prevents the player from selecting
			 * more than three cards
			 */

			selectedTileIndices.clear();

		}

		// Bring the frame back into focus so the KeyListener works as intended

		frame.requestFocus();

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

		// If the escape key is pressed, close the window

		if (event.getKeyCode() == 27) {

			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

		}

		// When the player presses "x" on the keyboard

		if (event.getKeyCode() == 88) {

			// Play the NO_SET sound

			playSound(NO_SET);

			// If no set is found

			if (!containsSet()) {

				JOptionPane.showMessageDialog(null, "There is no set! Repopulating board...");

				// Create a list that will contain three distinct random integers

				ArrayList<Integer> randomNums = new ArrayList<Integer>();

				/*
				 * Populate the list with three distinct random integers (each random integer
				 * ranges from zero to one less than the number of tiles on the frame)
				 */

				for (int i = 0; i < 3; i++) {

					int random = (int) (Math.random() * (rows * cols));

					while (randomNums.contains(random)) {

						random = (int) (Math.random() * (rows * cols));

					}

					randomNums.add(random);

				}

				/*
				 * Convert the three random integers to indices and swap the cards belonging to
				 * those indices with random cards from the undealt pile
				 */

				replaceCard(randomNums.get(0) % rows, randomNums.get(0) / cols, true);
				replaceCard(randomNums.get(1) % rows, randomNums.get(1) / cols, true);
				replaceCard(randomNums.get(2) % rows, randomNums.get(2) / cols, true);

			} else {

				JOptionPane.showMessageDialog(null, "There IS a set! Look harder!");

			}

		}

	}

	// None of these methods are necessary for the functionality of the game

	public void keyReleased(KeyEvent event) {

	}

	public void keyTyped(KeyEvent event) {

	}

	public void playSound(File sound) {

		// Play the sound

		try {

			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public Board() {

		// Initialize the JFrame object with the correct settings

		frame = new JFrame("SET");
		frame.setSize(700, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.getContentPane().setBackground(Color.BLUE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setFocusable(true);

		// Initialize the GridLayout object with the correct settings

		layout = new GridLayout(rows, cols);
		layout.setHgap(5);
		layout.setVgap(5);

		// Set the layout of the frame

		frame.setLayout(layout);

		// Initialize the lists containing undealt, dealt, and player selected cards

		undealt = new ArrayList<Card>();
		dealt = new ArrayList<Card>();
		selectedTileIndices = new ArrayList<Integer>();

		/*
		 * Populate the undealt pile with all possible distinct cards (there are 81 in
		 * total because each card can have three possible shapes, colors, shadings, and
		 * quantities)
		 */

		for (int i = 1; i <= 3; i++) {

			for (int j = 1; j <= 3; j++) {

				for (int k = 1; k <= 3; k++) {

					for (int l = 1; l <= 3; l++) {

						undealt.add(new Card(i, j, k, l));

					}

				}

			}

		}

		// Deal the cards onto the frame

		deal();

		// Make the frame visible to the player

		frame.setVisible(true);

	}

	public void deal() {

		/*
		 * Cycle through all indices in the 2D array of cards. If an index is null, fill
		 * it with a card from the undealt pile
		 */

		for (int i = 0; i < cardBoard.length; i++) {

			for (int j = 0; j < cardBoard[i].length; j++) {

				if (cardBoard[i][j] == null) {

					replaceCard(i, j, false);

				}

			}

		}

	}

	public boolean isSet(Card card1, Card card2, Card card3) {

		boolean shape = false;
		boolean color = false;
		boolean shading = false;
		boolean quantity = false;

		/*
		 * If the three cards are either all different colors or all the same color,
		 * update the boolean corresponding to color to reflect that the three cards
		 * satisfy the color condition for a SET
		 */

		if ((card1.getColor() == card2.getColor() && card1.getColor() == card3.getColor())
				|| (card1.getColor() != card2.getColor() && card1.getColor() != card3.getColor()
						&& card2.getColor() != card3.getColor())) {

			color = true;

		}

		// Repeat the same logical process for shape, shading, and quantity

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

		/*
		 * Return true if the three cards satisfy the color, shape, shading, and
		 * quantity conditions for a SET, otherwise, return false
		 */

		if (color && shape && shading && quantity) {

			return true;

		} else {

			return false;

		}

	}

	public boolean containsSet() {

		/*
		 * Sample all possible combinations of three cards from the board and return
		 * true if at least one of them is a set, otherwise return false
		 */

		for (int i = 0; i < rows * cols - 2; i++) {

			for (int j = i + 1; j < rows * cols - 1; j++) {

				for (int k = j + 1; k < rows * cols; k++) {

					if (isSet(cardBoard[i % rows][i / rows], cardBoard[j % rows][j / rows],
							cardBoard[k % rows][k / rows])) {

						return true;

					}

				}

			}

		}

		return false;

	}

	public void replaceCard(int row, int col, boolean swap) {

		/*
		 * If the recycle parameter is true, execute the code that recycles the selected
		 * card from the board back into the undealt pile, otherwise don't recycle
		 */

		if (swap) {

			/*
			 * Swap the selected card from the board (the selected card is determined from
			 * the index parameters) with a random card from the undealt pile
			 */

			int random = (int) (Math.random() * undealt.size());

			Card temp = cardBoard[row][col];

			cardBoard[row][col] = undealt.remove(random);

			undealt.add(temp);

		} else {

			/*
			 * Replace the selected card from the board with a random card from the undealt
			 * pile
			 */

			int random = (int) (Math.random() * undealt.size());

			cardBoard[row][col] = undealt.remove(random);

			dealt.add(cardBoard[row][col]);

		}

		// Update the corresponding 2D array of visual tiles

		if (tileBoard[row][col] != null) {

			frame.remove(tileBoard[row][col]);

		}

		tileBoard[row][col] = new Tile(cardBoard[row][col].getFileName(), row, col);
		tileBoard[row][col].addMouseListener(this);

		frame.add(tileBoard[row][col]);
		frame.requestFocus();
		frame.setVisible(true);

	}

}
