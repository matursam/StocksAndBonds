package ui;

import java.util.ArrayList;

import game_data.Player;
import game_data.Stock;
import game_logic.GameManager;

public class TestUI {

	public static void main(String args[]) {
		Player p = new Player("Mike");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		GameManager m = new GameManager(players);
		System.out.println("Initial Market Type: " + (m.wasBearMarket() ? "Bear Market" : "Bull Market"));
		System.out.println("Initial Stock Values:");
		for(Stock s : m.getStocks()) {
			System.out.println("    " + s.getName() + ": $" + s.getPrice());
		}
	}
	
}
