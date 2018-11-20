package game_logic;

import java.util.ArrayList;

import holdings.Holding;

/**
 * Interface between ui and data, contains all game logic
 * @author Michael Tursam
 *
 */
public class GameManager {
	
	private ArrayList<Holding> holdings;
	
	
	private void nextTurn() {
		boolean bearMarket;
		// See if this turn is a bull market or bear market
		if(Math.random() > 0.5) {
			//Bull Market
			bearMarket = false;
		}
		else {
			//Bear Market
			bearMarket = true;
		}
		// Update the stock prices
		for(Holding h : holdings) {
			h.updateValue(bearMarket);
		}
	}
}
