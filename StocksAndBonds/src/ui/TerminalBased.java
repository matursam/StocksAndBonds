package ui;

import java.util.ArrayList;
import java.util.Scanner;

import game_data.Player;
import game_data.Stock;
import game_logic.GameManager;

public class TerminalBased {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		//Print a welcome message, then get the number of players
		System.out.println("Welcome to Stocks and Bonds.");
		System.out.println("Please enter the number of players.");
		int numPlayers = input.nextInt();
		ArrayList<Player> players = new ArrayList<Player>();
		input.nextLine();
		for(int i = 1; i <= numPlayers; i++) {
			System.out.println("Enter Name for player " + i + ": ");
			String name = input.nextLine();
			players.add(new Player(name.trim()));	
		}
		// Create the game manager
		GameManager m = new GameManager(players);
		
		// Print out the initial stock prices
		System.out.println(m.getStockBanner());
		m.updateStockValues();
		System.out.println(m.getStockBanner());
		m.updateStockValues();
		m.updateStockValues();
		System.out.println(m.getStockBanner());
		m.updateStockValues();
		System.out.println(m.getStockBanner());
	}
	
}
