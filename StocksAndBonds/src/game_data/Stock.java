package game_data;

import java.util.HashMap;
import java.util.Random;

public class Stock {
	private String name;
	private int price;
	private HashMap<Integer, Integer> bullMarketValues;
	private HashMap<Integer, Integer> bearMarketValues;
	
	public Stock(String name, int price, HashMap<Integer, Integer> bullMarketValues, HashMap<Integer, Integer> bearMarketValues) {
		this.name = name;
		this.price = price;
		this.bullMarketValues = bullMarketValues;
		this.bearMarketValues = bearMarketValues;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void updateStockPrice(boolean bearMarket) {
		Random r = new Random();
		int diceRoll = 2 + r.nextInt(6) + r.nextInt(6);
		int adjustment;
		if(bearMarket) {
			adjustment = bearMarketValues.get(diceRoll);
		}
		else {
			adjustment = bullMarketValues.get(diceRoll);
		}
		this.price += adjustment;
	}
	
}
