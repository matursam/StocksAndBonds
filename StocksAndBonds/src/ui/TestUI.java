package ui;

import game_data.Stock;
import game_logic.GameManager;

public class TestUI {

	public static void main(String args[]) {
		GameManager m = new GameManager();
		System.out.println("Initial Market Type: " + (m.wasBearMarket() ? "Bear Market" : "Bull Market"));
		System.out.println("Initial Stock Values:");
		for(Stock s : m.getStocks()) {
			System.out.println("    " + s.getName() + ": $" + s.getPrice());
		}
		
	}
	
}
