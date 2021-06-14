
public class Card {

	// Each card possesses four attributes

	private int shape;
	private int color;
	private int shading;
	private int quantity;

	private String fileName;

	public Card(int shape, int color, int shading, int quantity) {

		// Initialize each attribute

		this.shape = shape;
		this.color = color;
		this.shading = shading;
		this.quantity = quantity;

		// Initialize the file name using the card's attributes

		fileName = "" + shape + color + shading + quantity + ".jpg";

	}

	// Getters and setters
	
	public int getShape() {

		return shape;

	}

	public int getColor() {

		return color;

	}

	public int getShading() {

		return shading;

	}

	public int getQuantity() {

		return quantity;

	}

	public String getFileName() {

		return fileName;

	}

}
