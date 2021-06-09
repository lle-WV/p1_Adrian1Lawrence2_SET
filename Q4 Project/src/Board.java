import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Board implements KeyListener, MouseListener {

	private ArrayList<Card> deck;
	private ArrayList<Card> discard;
	private ArrayList<Integer> selectedTiles;

	private int rows = 3;
	private int cols = 3;

	private Tile[][] tileBoard = new Tile[rows][cols];
	private Card[][] cardBoard = new Card[rows][cols];

	JFrame frame;

	@Override
	public void mouseClicked(MouseEvent event) {

		int row = ((Tile) event.getSource()).getRow();
		int col = ((Tile) event.getSource()).getCol();

		selectedTiles.add(row);
		selectedTiles.add(col);

		System.out.println(selectedTiles.size() == 6);

		if (selectedTiles.size() == 6) {

			if (isSet(cardBoard[selectedTiles.get(0)][selectedTiles.get(1)],
					cardBoard[selectedTiles.get(2)][selectedTiles.get(3)],
					cardBoard[selectedTiles.get(4)][selectedTiles.get(5)])) {

				replace(selectedTiles.get(0), selectedTiles.get(1));
				replace(selectedTiles.get(2), selectedTiles.get(3));
				replace(selectedTiles.get(4), selectedTiles.get(5));

			}

			selectedTiles.clear();

		}

	}

	public void mouseEntered(MouseEvent event) {

	}

	public void mouseExited(MouseEvent event) {

	}

	public void mousePressed(MouseEvent event) {

	}

	public void mouseReleased(MouseEvent event) {

	}

	public void keyPressed(KeyEvent event) {

		if (event.getKeyChar())
		
	}

	public void keyReleased(KeyEvent event) {

	}

	public void keyTyped(KeyEvent event) {

	}

	public Board() {

		frame = new JFrame("SET");
		frame.setSize(700, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout layout = new GridLayout(rows, cols);

		layout.setHgap(10);
		layout.setVgap(10);

		frame.setLayout(layout);

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
