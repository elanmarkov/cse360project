package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void setIDTest() {
		Player player1 = new Player("PlayerOne", 7);
		assertEquals(player1.playerID, 7);
		
		player1.setID(32);
		assertEquals(player1.playerID, 32);
	}
	
	@Test
	public void getIDTest() {
		Player player1 = new Player("PlayerOne", 7);
		assertEquals(player1.getID(), 7);
		
		player1.setID(32);
		assertEquals(player1.getID(), 32);
	}
	
	
	@Test
	public void setAndGetUsernameTest() {
		Player player1 = new Player();
		assertEquals(player1.getUsername(), "");
		
		Player player2 = new Player("PlayerTwo", 0);
		assertEquals(player2.getUsername(), "PlayerTwo");
		
		player2.setUsername("bob");
		assertEquals(player2.getUsername(), "bob");
		
		assertEquals(player2.getUsername(), player2.username);
	}
	
	@Test
	public void setAndGetPlayerDataTest() {
		
		Player player1 = new Player();
		assertSame(player1.playerData, player1.getPlayerData());
		
		Data temp = player1.getPlayerData();
		Data data = new Data();
		player1.setPlayerData(data);
		
		assertNotSame(temp, player1.getPlayerData());
		assertSame(data, player1.getPlayerData());
		
	}
// Player class testing ends, Status class testing begins
	
	@Test
	public void getAndSetHitPtsTest() {
		
		Status status1 = new Status();
		assertEquals(status1.getHitPts(), status1.getMaxHP());
		
		status1.setHitPts(50);
		assertEquals(status1.getHitPts(), 50);
	}
	
	@Test
	public void getAndSetManaTest() {

		
		Status status1 = new Status();
		assertEquals(status1.getMana(), status1.getMaxMana());
		
		status1.setMana(15);
		assertEquals(status1.getMana(), 15);
		
	}
	
	@Test
	public void getAndReduceFoodCountTest() {
		
		Status status1 = new Status();
		
		assertEquals(status1.getFoodCount(), status1.getMaxFood());
		
		status1.reduceFoodCount();
		assertEquals(status1.getFoodCount(), status1.getMaxFood() - 1);

		status1.reduceFoodCount();
		assertEquals(status1.getFoodCount(), status1.getMaxFood() - 2);
	}	
	
	@Test
	public void getAndSetConditionTest() {
		
		Status status1 = new Status();
		assertEquals(status1.getCondition(), Condition.NONE);
		
		fail("not fully implemented");
		
	}
}
