package game_logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
	
	private boolean bearMarket;
	
	
	public GameManager() {
		// Read the stocks in from the stock folder
		readStocks();
		// Determine if the starting market price will be bull or bear
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
	
}
