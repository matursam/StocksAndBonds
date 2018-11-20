package game_data;

import java.util.HashMap;

public class Player {
	
	/** The default amount of starting cash for the player **/
	public static final int STARTING_CASH = 5000;
	
	/** The name of the player **/
	private String name;
	/** The amount of cash the player has **/
	private int cash;
	/** Maps stocks to holdings, to make updating specific holdings fast and easy **/
	private HashMap<Stock, Holding> holdings;
	
	public Player(String name) {
		this.name = name;
		this.cash = STARTING_CASH;
		holdings = new HashMap<Stock, Holding>();
	}
	
	public Holding getHoldingForStock(Stock s) {
		return holdings.get(s);
	}
	
	public boolean purchaseStock(Stock s, int quantity) {
		//See if we have enough money to purchase the stock
		if(s.getPrice() * quantity > cash || quantity <= 0) {
			return false;
		}
		Holding h = holdings.get(s);
		if(h == null) {
			//Make a new holding for the stock
			holdings.put(s, new Holding(s, quantity));
		}
		else {
			h.setQuantity(h.getQuantity() + quantity);
		}
		//Subtract the value of the stock from the player's cash
		this.cash -= s.getPrice() * quantity;
		return true;
	}
	
	/**
	 * Sells the specified stock and returns the total value of the sale
	 * @param s the stock to sell
	 * @param quantity the number of shares of the specified stock to sell
	 * @return the value of the shares sold (quantity * price per share)
	 */
	public int sellStock(Stock s, int quantity) {
		Holding h = holdings.get(s);
		if(h != null) {
			int quantityLeft = h.getQuantity() - quantity;
			if(quantityLeft <= 0) {
				//We sold every share of the stock we have, remove the holding from the map and return the value of every share in it
				holdings.remove(s);
				return h.getQuantity() * s.getPrice();
			}
			else {
				//We still have shares of the stock left, don't delete the holding
				h.setQuantity(quantityLeft);
				return quantity * s.getPrice();
			}
		}
		// The player had no shares of the specified stock, return 0
		return 0;
	}
	
	public int getTotalWorth() {
		int totalWorth = cash;
		for(Holding h : holdings.values()) {
			totalWorth += h.getQuantity() * h.getStock().getPrice();
		}
		return totalWorth;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCash() {
		return cash;
	}
	
	public String getPortfolio() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		sb.append(":\n");
		if(holdings.size() == 0) {
			sb.append("  No holdings found");
			return sb.toString();
		}
		else {
			for(Holding h : holdings.values()) {
				sb.append("  ");
				sb.append(h.getStock().getName());
				sb.append("  ");
				sb.append(h.getQuantity());
				sb.append(" Shares at $");
				sb.append(h.getStock().getPrice());
				sb.append(" per share.");
			}
			return sb.toString();
		}
	}
	
}
