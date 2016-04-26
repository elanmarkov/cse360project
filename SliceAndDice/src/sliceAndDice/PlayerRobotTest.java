package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * JUnit tests for the Player, Turn, and Robot classes.
 * @author Eisher Saroya
 *
 */
public class PlayerRobotTest {

	@Test
	public void playerNotNull() {
		//Ensure the Player initialized.
		Player player1 = new Player("PlayerOne", 7);
		assertNotNull(player1);
	}
	
	@Test
	public void playerDataNotNull() {
		// Ensure the Player Data storage initialized
		Player player1 = new Player("PlayerOne", 7);
		assertNotNull(player1.getPlayerData());
	}
	
	@Test
	public void setAndGetIDTest() {
		// Test the setter and getter for the ID
		Player player1 = new Player("PlayerOne", 7);
		player1.setID(32);
		assertEquals(player1.getID(), 32);
	}
	
	@Test
	public void setAndGetUsernameTest() {
		// Test the setter and getter for the username
		Player player2 = new Player("PlayerTwo", 0);
		player2.setUsername("bob");
		assertEquals(player2.getUsername(), "bob");
	}
	@Test
	public void setAndGetPlayerDataTest() {
		// Test the setter and getter for the Data
		Player player1 = new Player();
		Data data = new Data();
		player1.setPlayerData(data);
		
		assertSame(data, player1.getPlayerData());
	}
	
	@Test
	public void testToString() {
		//test that the toString method of Player works as intended
		Player player1 = new Player("bob", 1);
		Data sampleData = new Data();
		String testString = "bob" + "\n" + sampleData;
		
		assertEquals(player1.toString(), testString);
	}
	
// Player class testing ends, Status class testing begins
	@Test
	public void statusNotNull() {
		// Check that the Status initialized
		Status status1 = new Status();
		assertNotNull(status1);
	}
	
	@Test
	public void getAndSetHitPtsTest() {
		// Test the setter and getter for the HP
		Status status1 = new Status();
		status1.setHitPts(50);
		assertEquals(status1.getHitPts(), 50);
	}
	
	@Test
	public void getAndSetManaTest() {
		// Test the setter and getter for the mana
		Status status1 = new Status();
		status1.setMana(15);
		assertEquals(status1.getMana(), 15);
	}
	
	@Test
	public void getAndReduceFoodCountTest() {
		// Test the setter and getter for the food count
		Status status1 = new Status();
		
		status1.reduceFoodCount();
		assertEquals(status1.getFoodCount(), Status.getMaxFood() - 1);
	}	
	
	@Test
	public void getAndSetConditionTest() {
		// Test the setter and getter for the player condition
		Status status1 = new Status();
		assertEquals(status1.getCondition(), Condition.NONE);
	}
	
	@Test
	public void getNextMoveThrows() {
		// Check that the Player getNextMove throws an exception
		boolean thrown = false;
		Player human = new Player();
		Status ownStatus = new Status();
		Status oppStatus = new Status();
		try {
			human.getNextMove(ownStatus, oppStatus);
		}
		catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void getNextMoveInheritance() {
		// Check that the inheritance works properly for getNextMove of Robot
		boolean thrown = false;
		Player robot = new Robot();
		Status ownStatus = new Status();
		Status oppStatus = new Status();
		try {
			robot.getNextMove(ownStatus, oppStatus);
		}
		catch (Exception e) {
			thrown = true;
		}
		assertTrue(!thrown);
	}
	
	@Test
	public void robotDataPass() {
		Player robot = new Robot();
		Data roboData = robot.getPlayerData();
		assertNotNull(roboData);
	}
}
