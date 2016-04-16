package sliceAndDice;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
//import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class DataTest {

	@Test
	public void testData() {
		Data newData = new Data();
		assertNotNull(newData);
	}

	@Test
	public void testDataScanner(){
		try{
			Scanner readPlayers1 = new Scanner(new BufferedReader(new FileReader("data.txt")));
			int numberOfPlayers = readPlayers1.nextInt();
			Data newData = new Data(readPlayers1);
			readPlayers1.close();
			assertNotNull(newData);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testToString(){
		try {
			Scanner readPlayers2 = new Scanner(new BufferedReader(new FileReader("data.txt")));
			int numberOfPlayers = readPlayers2.nextInt();
			Data newData = new Data(readPlayers2);
			System.out.println(newData.toString());
			readPlayers2.close();
			assertNotNull(newData);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	@Test
	public void testSetScore() {
		Data newData = new Data();
		newData.setScore(2000);
		assertEquals(2000, newData.getScore(), .001);
	}

	@Test
	public void testSetRank() {
		Data newData = new Data();
		newData.setRank(7);
		assertEquals(7, newData.getRank());
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
	public void testSetAttack() {
		Data newData = new Data();
		newData.setAttack(100);
		assertEquals(100, newData.getAttack());
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

	@Test
	public void testSetFoodUsed() {
		Data newData = new Data();
		newData.setFoodUsed(100);
		assertEquals(100, newData.getFoodUsed());
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

	@Test
	public void testIncrNumAttacks() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAttacks();
		assertEquals(1, newPlayer.getPlayerData().getAttack());
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
		newPlayer.getPlayerData().updateHealthLost(60);
		assertEquals(40, newPlayer.getPlayerData().getHPLost());
	}

	@Test
	public void testUpdateManaUsed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateManaUsed(20);
		assertEquals(10, newPlayer.getPlayerData().getManaUsed());
	}

	@Test
	public void testUpdateFoodUsed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateFoodUsed(2);
		assertEquals(3, newPlayer.getPlayerData().getFoodUsed());
	}

}
