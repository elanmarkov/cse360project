package sliceAndDice;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;


/**
 * Unit tests for methods for the Data class
 * @author Andrew Stanton, PIN: 817, CSE 360, Spring 2016
 * @version 4/29/16
 */
public class DataTest {
	
	/*
	 * Testing Data Constructor
	 */
	@Test // ensures the variable is not null after using constructor
	public void testData() {
		Data newData = new Data();
		assertNotNull(newData);
	}
	@Test // ensures that a double variable has correct value from constructor
	public void testConstructor() {
		Data newData = new Data();
		assertEquals(1000, newData.getScore(), .0000001);
	}
	@Test // ensures that an int variable has correct value from constructor
	public void testConstructor2() {
		Data newData = new Data();
		assertEquals(0, newData.getTotalNumAttacks());
	}
	@Test // ensures variable is not null after using overloaded Scanner constructor
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
	 * Test toString method of Data class
	 */
	@Test // ensures that Data toString() method is called from Player toString() method and that Data is not null
	public void testToString() throws IOException{
		Scanner readPlayers2 = null;
		try {
			readPlayers2 = new Scanner(new BufferedReader(new FileReader("data.txt")));
			int numberOfPlayers = readPlayers2.nextInt();
			Player newPlayer = new Player(readPlayers2);
			System.out.println(newPlayer.getPlayerData().toString());
			assertNotNull(newPlayer.getPlayerData());
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			readPlayers2.close();
		}
	}
	
	/*
	 * Testing reset data method
	 */
	@Test // ensures data is reset properly - prints data before and after reset
	public void testResetData(){
		Player newPlayer = new Player();
		// manually set Data
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
		newPlayer.getPlayerData().resetData(); // reset Data
		System.out.println(newPlayer.toString());
	}

	/*
	 * Testing Get and Set Methods
	 */
	@Test // tests that score is set and gotten properly
	public void testSetScore() {
		Data newData = new Data();
		newData.setScore(2000);
		assertEquals(2000, newData.getScore(), .00001);
	}
	@Test // tests that aborts is set and gotten properly
	public void testSetAbort() {
		Data newData = new Data();
		newData.setAbort(5);
		assertEquals(5, newData.getAbort());
	}
	@Test // tests that number of games is set and gotten properly
	public void testSetGame() {
		Data newData = new Data();
		newData.setGame(100);
		assertEquals(100, newData.getGame());
	}
	@Test // tests that number of wins is set and gotten properly
	public void testSetWin() {
		Data newData = new Data();
		newData.setWin(100);
		assertEquals(100, newData.getWin());
	}
	@Test // tests that number of base attacks is set and gotten properly
	public void testSetBaseAttack() {
		Data newData = new Data();
		newData.setBaseAttack(100);
		assertEquals(100, newData.getBaseAttack());
	}
	@Test // tests that number of freezes is set and gotten properly
	public void testSetFreeze() {
		Data newData = new Data();
		newData.setFreeze(100);
		assertEquals(100, newData.getFreeze());
	}
	@Test // tests that number of double attacks is set and gotten properly
	public void testSetDouble() {
		Data newData = new Data();
		newData.setDouble(100);
		assertEquals(100, newData.getDouble());
	}
	@Test // tests that number of poison attacks is set and gotten properly
	public void testSetPoison1() {
		Data newData = new Data();
		newData.setPoison(100);
		assertEquals(100, newData.getPoison());
	}
	@Test // tests that number of wins is set and gotten properly
	public void testSetPoison2() {
		Data newData = new Data();
		newData.incrNumPoisons();
		newData.setPoison(20);
		assertEquals(20, newData.getPoison());
	}
	@Test// tests that number of auras is set and gotten properly
	public void testSetAura() {
		Data newData = new Data();
		newData.setAura(100);
		assertEquals(100, newData.getAura());
	}
	@Test// tests that number of charges is set and gotten properly
	public void testSetCharge() {
		Data newData = new Data();
		newData.setCharge(100);
		assertEquals(100, newData.getCharge());
	}
	@Test// tests that number of meals is set and gotten properly
	public void testSetMeal() {
		Data newData = new Data();
		newData.setMeal(100);
		assertEquals(100, newData.getMeal());
	}
	@Test// tests that amount of HP lost is set and gotten properly
	public void testSetHPLost() {
		Data newData = new Data();
		newData.setHPLost(100);
		assertEquals(100, newData.getHPLost());
	}
	@Test// tests that amount of mana used is set and gotten properly
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
	}*/
	@Test// tests that amount of HP healed is set and gotten properly
	public void testSetHPHealed() {
		Data newData = new Data();
		newData.setHPHealed(100);
		assertEquals(100, newData.getHPHealed());
	}
	@Test// tests that amount of MP charged is set and gotten properly
	public void testSetMPCharged() {
		Data newData = new Data();
		newData.setMPCharged(100);
		assertEquals(100, newData.getMPCharged());
	}

	/*
	 * Testing methods that increment data in Data
	 */
	@Test // tests that number of aborts gets incremented
	public void testIncrAbort() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAborts();
		assertEquals(1, newPlayer.getPlayerData().getAbort()); // 0 incremented = 1
	}
	@Test // tests that number of aborts gets incremented correctly three times
	public void testIncrAbort2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAborts();
		newPlayer.getPlayerData().incrNumAborts();
		newPlayer.getPlayerData().incrNumAborts();
		assertEquals(3, newPlayer.getPlayerData().getAbort());
	}
	@Test // tests that number of games gets incremented
	public void testIncrGameCount() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrGameCount();
		assertEquals(1, newPlayer.getPlayerData().getGame());
	}
	@Test // tests that number of games gets incremented correctly three times
	public void testIncrGameCount2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrGameCount();
		newPlayer.getPlayerData().incrGameCount();
		newPlayer.getPlayerData().incrGameCount();
		assertEquals(3, newPlayer.getPlayerData().getGame());
	}
	@Test // tests that number of wins gets incremented
	public void testIncrWinCount() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrWinCount();
		assertEquals(1, newPlayer.getPlayerData().getWin());
	}
	@Test // tests that number of wins gets incremented correctly three times
	public void testIncrWinCount2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrWinCount();
		newPlayer.getPlayerData().incrWinCount();
		newPlayer.getPlayerData().incrWinCount();
		assertEquals(3, newPlayer.getPlayerData().getWin());
	}
	/* NOT STORED
	@Test
	public void testIncrNumAttacks() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAttacks();
		assertEquals(1, newPlayer.getPlayerData().getAttack());
	}*/
	@Test // tests that number of base attacks gets incremented
	public void testIncrNumBaseAttacks() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumBaseAttacks();
		assertEquals(1, newPlayer.getPlayerData().getBaseAttack());
	}
	@Test // tests that number of base attacks gets incremented correctly three times
	public void testIncrNumBaseAttacks2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumBaseAttacks();
		newPlayer.getPlayerData().incrNumBaseAttacks();
		newPlayer.getPlayerData().incrNumBaseAttacks();
		assertEquals(3, newPlayer.getPlayerData().getBaseAttack());
	}
	/* NOT STORED
	@Test
	public void testIncrNumSPAttacks() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumSPAttacks();
		assertEquals(1, newPlayer.getPlayerData().getSPAttack());
	}*/
	@Test // tests that number of freezes gets incremented
	public void testIncrNumFreezes() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumFreezes();
		assertEquals(1, newPlayer.getPlayerData().getFreeze());
	}
	@Test // tests that number of freezes gets incremented correctly three times
	public void testIncrNumFreezes2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumFreezes();
		newPlayer.getPlayerData().incrNumFreezes();
		newPlayer.getPlayerData().incrNumFreezes();
		assertEquals(3, newPlayer.getPlayerData().getFreeze());
	}
	@Test // tests that number of double attacks gets incremented
	public void testIncrNumDoubles() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumDoubles();
		assertEquals(1, newPlayer.getPlayerData().getDouble());
	}
	@Test // tests that number of double attacks gets incremented correctly three times
	public void testIncrNumDoubles2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumDoubles();
		newPlayer.getPlayerData().incrNumDoubles();
		newPlayer.getPlayerData().incrNumDoubles();
		assertEquals(3, newPlayer.getPlayerData().getDouble());
	}
	@Test // tests that number of poisons gets incremented
	public void testIncrNumPoisons() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumPoisons();
		assertEquals(1, newPlayer.getPlayerData().getPoison());
	}
	@Test // tests that number of poisons gets incremented correctly three times
	public void testIncrNumPoisons2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumPoisons();
		newPlayer.getPlayerData().incrNumPoisons();
		newPlayer.getPlayerData().incrNumPoisons();
		assertEquals(3, newPlayer.getPlayerData().getPoison());
	}
	@Test // tests that number of auras gets incremented
	public void testIncrNumAuras() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAuras();
		assertEquals(1, newPlayer.getPlayerData().getAura());
	}
	@Test // tests that number of auras gets incremented correctly three times
	public void testIncrNumAuras2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumAuras();
		newPlayer.getPlayerData().incrNumAuras();
		newPlayer.getPlayerData().incrNumAuras();
		assertEquals(3, newPlayer.getPlayerData().getAura());
	}
	@Test // tests that number of charges gets incremented
	public void testIncrNumCharges() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumCharges();
		assertEquals(1, newPlayer.getPlayerData().getCharge());
	}
	@Test // tests that number of charges gets incremented three times
	public void testIncrNumCharges2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumCharges();
		newPlayer.getPlayerData().incrNumCharges();
		newPlayer.getPlayerData().incrNumCharges();
		assertEquals(3, newPlayer.getPlayerData().getCharge());
	}
	@Test // tests that number of meals gets incremented
	public void testIncrNumMeals() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumMeals();
		assertEquals(1, newPlayer.getPlayerData().getMeal());
	}
	@Test // tests that number of meals gets incremented correctly three times
	public void testIncrNumMeals2() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().incrNumMeals();
		newPlayer.getPlayerData().incrNumMeals();
		newPlayer.getPlayerData().incrNumMeals();
		assertEquals(3, newPlayer.getPlayerData().getMeal());
	}
	
	/*
	 * Testing methods that update data in Data
	 */
	@Test // tests that amount of health lost is properly updated
	public void testUpdateHealthLost() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateTotalHealthLost(60);
		assertEquals(40, newPlayer.getPlayerData().getHPLost()); // 100 - 60 = 40
	}
	@Test // tests that amount of mana used is properly updated
	public void testUpdateManaUsed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateTotalManaUsed(20);
		assertEquals(10, newPlayer.getPlayerData().getManaUsed()); // 30 - 20 = 10
	}
	/* NOT STORED
	@Test
	public void testUpdateFoodUsed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateFoodUsed(2);
		assertEquals(3, newPlayer.getPlayerData().getFoodUsed());
	}*/
	@Test // tests that amount of health healed is properly updated
	public void testUpdateHPHealed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateHPHealedAndFoodUsed(10);
		assertEquals(10, newPlayer.getPlayerData().getHPHealed());
	}
	@Test // tests that amount of mana charged is properly updated
	public void testUpdateMPCharged() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateMPChargedAndUsed(20);
		assertEquals(20, newPlayer.getPlayerData().getMPCharged());
	}
	@Test // tests that amount of HP healed is added back into HP lost so this is added to "net" HP updated at end of game
	public void testUpdateFoodUsed() {
		Player newPlayer = new Player();
		newPlayer.getPlayerData().updateHPHealedAndFoodUsed(10);
		assertEquals(10, newPlayer.getPlayerData().getHPLost());
	}
	@Test // tests that amount of MP charged is added back into MP used so this is added to "net" MP updated at end of game
	public void testUpdateMPUsed() {
	Player newPlayer = new Player();
	newPlayer.getPlayerData().updateMPChargedAndUsed(20);
	assertEquals(20, newPlayer.getPlayerData().getManaUsed());
	}
}