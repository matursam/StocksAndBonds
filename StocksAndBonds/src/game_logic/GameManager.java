package game_logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import game_data.Player;
import game_data.Stock;

/**
 * Contains logic for running the game. Holds all game related objects and calls appropriate functions.
 * @author Michael Tursam
 *
 */
public class GameManager {
	
	private static final String STOCKS_FOLDER = "gamedata/stocks/";
	
	private static final int DEFAULT_STOCK_VALUE = 100;
	
	/** ArrayList to hold all stocks available for purchase */
	private ArrayList<Stock> stocks;
	/** ArrayList to hold all players **/
	private ArrayList<Player> players;
	/** True if the last market was a bear market **/
	private boolean bearMarket;
	
	
	public GameManager(ArrayList<Player> p) {
		this.players = p;
		// Read the stocks in from the stock folder
		readStocks();
		// Initialize the stock values for the first turn
		updateStockValues();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Updates stock values for the next turn
	 */
	public void updateStockValues() {
		// Determine if the market will be bull or bear
		bearMarket = Math.random() < 0.5 ? true : false;
		// Update the starting price of each stock
		for(Stock s : stocks) {
			s.updateStockPrice(bearMarket);
		}
	}
	
	/**
	 * Reads stocks in from the csv file gamedata/stocks/stocks.csv
	 * Stock information is stored as follows:
	 * 		Line 1: Name
	 * 		Line 2: Bull Market Values
	 * 		Line 3: Bear Market Values
	 * 
	 * @throws FileNotFoundException
	 */
	private void readStocks() {
		stocks = new ArrayList<Stock>();
		File folder = new File(STOCKS_FOLDER);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    try {
		    	if (file.isFile()) {
		    		Stock s = makeStockFromFile(file);
		    		stocks.add(s);
		    	}
		    }
		    catch(Exception e) {
	        	// Skip this file and move on to the next one
	        }
		}
	}

	/**
	 * Reads the specified file and makes a new stock from it
	 * @param f the file to read
	 * @return the new stock created from the file
	 * @throws FileNotFoundException If the specified file doesn't exist
	 * @throws NoSuchElementException If the file is incomplete or improperly formatted
	 */
	private Stock makeStockFromFile(File f) throws FileNotFoundException, NoSuchElementException {
		Scanner s = new Scanner(f);
		s.useDelimiter(",");
		String name = s.next();
		HashMap<Integer, Integer> bullMarketValues = new HashMap<Integer, Integer>();
		for(int i = 2; i <= 12; i++) {
			int current = Integer.valueOf(s.next().trim());
			bullMarketValues.put(i, current);
		}
		HashMap<Integer, Integer> bearMarketValues = new HashMap<Integer, Integer>();
		for(int i = 2; i <= 12; i++) {
			int current = Integer.valueOf(s.next().trim());
			bearMarketValues.put(i, current);
		}
		s.close();
		Stock newStock = new Stock(name, DEFAULT_STOCK_VALUE, bullMarketValues, bearMarketValues);
		return newStock;
	}
	
	
	public ArrayList<Stock> getStocks() {
		return stocks;
	}
	
	public boolean wasBearMarket() {
		return bearMarket;
	}
	
	public String getStockBanner() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%25s", "Stock Name"));
		sb.append(" |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10  | Final\n");
		for(Stock s : stocks) {
			sb.append(String.format("%25s", s.getName()));
			for(Integer i : s.getPastPrices()) {
				sb.append(" | ");
				sb.append(String.format("%3d", i));
			}
			sb.append(" |\n");
		}
		return sb.toString();
	}
	
}
