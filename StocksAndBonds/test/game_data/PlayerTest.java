package game_data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game_logic.GameManager;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player p = new Player("Mike");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		GameManager m = new GameManager(players);
		
		Stock s = m.getStocks().get(0);
		p.purchaseStock(m.getStocks().get(0), 10);
		assertEquals(10, p.getHoldingForStock(s).getQuantity());
		assertEquals(5000 + 10 * s.getPrice(), p.getTotalWorth());
	}

}
