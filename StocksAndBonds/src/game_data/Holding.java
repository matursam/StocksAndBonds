package game_data;

/**
 * Represents a stock holding for a player
 * 
 * Tracks the type of stock and quantity of said stock owned by the player
 * @author Michael Tursam
 *
 */
public class Holding {
	private Stock s;
	private int quantity;
	
	public Holding(Stock s, int quantity) {
		this.s = s;
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Stock getStock() {
		return s;
	}
	
	public void setQuantity(int q) {
		if(q <= 0) {
			throw new IllegalArgumentException("Quantity cannot be negative.");
		}
		this.quantity = q;
	}
}
