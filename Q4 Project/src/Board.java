import java.util.ArrayList;

public class Board {

	private ArrayList<Card> deck;
	private ArrayList<Card> table;
	private ArrayList<Card> discard;

	public Board() {

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

	}

	public void deal() {

		int random;

		for (int i = 0; i < 9 - table.size(); i++) {

			random = (int) (Math.random() * deck.size());

			table.add(deck.remove(random));

		}

	}

	public boolean checkAndRemoveIfSet(int i, int j, int k) {

		boolean shape = false;
		boolean color = false;
		boolean shading = false;
		boolean quantity = false;

		if ((table.get(i).getColor() == table.get(j).getColor() && table.get(i).getColor() == table.get(k).getColor())
				|| (table.get(i).getColor() != table.get(j).getColor()
						&& table.get(i).getColor() != table.get(k).getColor()
						&& table.get(j).getColor() != table.get(k).getColor())) {

			color = true;

		}

		if ((table.get(i).getShape() == table.get(j).getShape() && table.get(i).getShape() == table.get(k).getShape())
				|| (table.get(i).getShape() != table.get(j).getShape()
						&& table.get(i).getShape() != table.get(k).getShape()
						&& table.get(j).getShape() != table.get(k).getShape())) {

			shape = true;

		}

		if ((table.get(i).getShading() == table.get(j).getShading()
				&& table.get(i).getShading() == table.get(k).getShading())
				|| (table.get(i).getShading() != table.get(j).getShading()
						&& table.get(i).getShading() != table.get(k).getShading()
						&& table.get(j).getShading() != table.get(k).getShading())) {

			shading = true;

		}

		if ((table.get(i).getQuantity() == table.get(j).getQuantity()
				&& table.get(i).getQuantity() == table.get(k).getQuantity())
				|| (table.get(i).getQuantity() != table.get(j).getQuantity()
						&& table.get(i).getQuantity() != table.get(k).getQuantity()
						&& table.get(j).getQuantity() != table.get(k).getQuantity())) {

			quantity = true;

		}

		if (shape && color && shading && quantity) {

			discard.add(table.remove(i));
			discard.add(table.remove(j));
			discard.add(table.remove(k));

			return true;

		} else {

			return false;

		}
	}

}
