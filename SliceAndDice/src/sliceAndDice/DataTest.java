package sliceAndDice;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class DataTest {
	
	/*
	 * Tests Data constructor
	 */
	@Test
	public void testData() {
		Data newData = new Data();
		assertNotNull(newData);
	}
	
	/*
	 * Tests Data constructor with with Scanner
	 */
	@Test
	public void testDataScanner(){
		Scanner readPlayers1 = null;
		try{
			readPlayers1 = new Scanner(new BufferedReader(new FileReader("data.txt")));
			int numberOfPlayers = readPlayers1.nextInt();
			Player newPlayer = new Player(readPlayers1);
			assertNotNull(newPlayer);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			readPlayers1.close();
		}
	}
	
	/*
	 * Tests toString method of Data class
	 */
	@Test
	public void testToString() throws IOException{
		Scanner readPlayers2 = null;
		try {
			readPlayers2 = new Scanner(new BufferedReader(new FileReader("data.txt")));
			int numberOfPlayers = readPlayers2.nextInt();
			Player newPlayer = new Player(readPlayers2);
			System.out.println(newPlayer.toString());
			assertNotNull(newPlayer);
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			readPlayers2.close();
		}
	}
	
	/*
	 * Tests reset data method to make sure the data is reset properly
	 */
	@Test
	public void testResetData(){
		Player newPlayer = new Player();
		newPlayer.setID(1);
		newPlayer.setUsername("user22");
		newPlayer.getPlayerData().setScore(1200);
		newPlayer.getPlayerData().setAbort(1);
		newPlayer.getPlayerData().setGame(2);
		newPlayer.getPlayerData().setWin(3);
		newPlayer.getPlayerData().setBaseAttack(10);
		newPlayer.getPlayerData().setFreeze(11);
		newPlayer.getPlayerData().setDouble(12);
		newPlayer.getPlayerData().setPoison(13);
		newPlayer.getPlayerData().setAura(14);
		newPlayer.getPlayerData().setCharge(15);
		newPlayer.getPlayerData().setMeal(20);
		newPlayer.getPlayerData().setHPLost(30);
		newPlayer.getPlayerData().setManaUsed(31);
		newPlayer.getPlayerData().setHPHealed(40);
		newPlayer.getPlayerData().setMPCharged(41);
		System.out.println(newPlayer.toString());
		newPlayer.getPlayerData().resetData();
		System.out.println(newPlayer.toString());
	}

	@Test
	public void testSetScore() {
		Data newData = new Data();
		newData.setScore(2000);
		assertEquals(2000, newData.getScore(), .00001);
	}
	
	@Test
	public void testSetAbort() {
		Data newData = new Data();
		newData.setAbort(5);
		assertEquals(5, newData.getAbort());
	}
	
	@Test
	public void testSetGame() {
		Data newData = new Data();
		newData.setGame(100);
		assertEquals(100, newData.getGame());
	}

	@Test
	public void testSetWin() {
		Data newData = new Data();
		newData.setWin(100);
		assertEquals(100, newData.getWin());
	}

	@Test
	public void testSetBaseAttack() {
		Data newData = new Data();
		newData.setBaseAttack(100);
		assertEquals(100, newData.getBaseAttack());
	}
	
	@Test
	public void testSetFreeze() {
		Data newData = new Data();
		newData.setFreeze(100);
		assertEquals(100, newData.getFreeze());
	}
	
	@Test
	public void testSetDouble() {
		Data newData = new Data();
		newData.setDouble(100);
		assertEquals(100, newData.getDouble());
	}
	
	@Test
	public void testSetPoison1() {
		Data newData = new Data();
		newData.setPoison(100);
		assertEquals(100, newData.getPoison());
	}
	@Test
	public void testSetPoison2() {
		Data newData = new Data();
		newData.incrNumPoisons();
		newData.setPoison(20);
		assertEquals(20, newData.getPoison());
	}
	
	@Test
	public void testSetAura() {
		Data newData = new Data();
		newData.setAura(100);
		assertEquals(100, newData.getAura());
	}
	
	@Test
	public void testSetCharge() {
		Data newData = new Data();
		newData.setCharge(100);
		assertEquals(100, newData.getCharge());
	}

	@Test
	public void testSetMeal() {
		Data newData = new Data();
		newData.setMeal(100);
		assertEquals(100, newData.getMeal());
	}

	@Test
	public void testSetHPLost() {
		Data newData = new Data();
		newData.setHPLost(100);
		assertEquals(100, newData.getHPLost());
	}

	@Test
	public void testSetManaUsed() {
		Data newData = new Data();
		newData.setManaUsed(100);
		assertEquals(100, newData.getManaUsed());
	}
	
	/* NOT STORED
	@Test
	public void testSetFoodUsed() {
		Data newData = new Data();
		newData.setFoodUsed(100);
		assertEquals(100, newData.getFoodUsed());
	}
	 */
	
	@Test
	public void testSetHPHealed() {
		Data newData = new Data();
		newData.setHPHealed(100);
		assertEquals(100, newData.getHPHealed());
	}

	@Test
	public void testSetMPCharged() {
		Data newData = new Data();
		newData.setMPCharged(100);
		assertEquals(100, newData.getMPCharged());
	}

	/* Tested with the set methods
	@Test
	public void testGetScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRank() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAttack() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMeal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHPLost() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetManaUsed() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFoodUsed() {
		fail("Not yet implemented");
	}
	 */

	@Test
	public void testIncrAbort() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAborts();
		assertEquals(1, newPlayer.getPlayerData().getAbort());
	}
	
	@Test
	public void testIncrGameCount() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrGameCount();
		assertEquals(1, newPlayer.getPlayerData().getGame());
	}

	@Test
	public void testIncrWinCount() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrWinCount();
		assertEquals(1, newPlayer.getPlayerData().getWin());
	}
	
	/* NOT STORED
	@Test
	public void testIncrNumAttacks() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAttacks();
		assertEquals(1, newPlayer.getPlayerData().getAttack());
	}
	 */
	
	@Test
	public void testIncrNumBaseAttacks() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumBaseAttacks();
		assertEquals(1, newPlayer.getPlayerData().getBaseAttack());
	}
	
	/* NOT STORED
	@Test
	public void testIncrNumSPAttacks() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumSPAttacks();
		assertEquals(1, newPlayer.getPlayerData().getSPAttack());
	}
	 */
	
	@Test
	public void testIncrNumFreezes() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumFreezes();
		assertEquals(1, newPlayer.getPlayerData().getFreeze());
	}
	
	@Test
	public void testIncrNumDoubles() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumDoubles();
		assertEquals(1, newPlayer.getPlayerData().getDouble());
	}
	
	@Test
	public void testIncrNumPoisons() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumPoisons();
		assertEquals(1, newPlayer.getPlayerData().getPoison());
	}
	
	@Test
	public void testIncrNumAuras() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAuras();
		assertEquals(1, newPlayer.getPlayerData().getAura());
	}

	@Test
	public void testIncrNumCharges() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumCharges();
		assertEquals(1, newPlayer.getPlayerData().getCharge());
	}
	
	@Test
	public void testIncrNumMeals() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumMeals();
		assertEquals(1, newPlayer.getPlayerData().getMeal());
	}

	@Test
	public void testUpdateHealthLost() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateTotalHealthLost(60);
		assertEquals(40, newPlayer.getPlayerData().getHPLost());
	}

	@Test
	public void testUpdateManaUsed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateTotalManaUsed(20);
		assertEquals(10, newPlayer.getPlayerData().getManaUsed());
	}
	
	/* NOT STORED
	@Test
	public void testUpdateFoodUsed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateFoodUsed(2);
		assertEquals(3, newPlayer.getPlayerData().getFoodUsed());
	}
	 */
	
	@Test
	public void testUpdateHPHealed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateHPHealedAndFoodUsed(10);
		assertEquals(10, newPlayer.getPlayerData().getHPHealed());
	}
	
	@Test
	public void testUpdateMPCharged() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateMPChargedAndUsed(20);
		assertEquals(20, newPlayer.getPlayerData().getMPCharged());
	}
}