package sliceAndDice;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import sliceAndDice.Player;
import sliceAndDice.Data;
import java.util.ArrayList;

import org.junit.Test;

public class ScoreboardTest extends Scoreboard{
	/*
	 * Test: Load one player from file and print
	 * Status: Passed
	 * Setup: data.txt has 1 user with username user1 and ID 1 and default data
	 * Expected Result: Should print out user1 then 1000 (score) then 1 (rank) then 7 zeroes (rest of data)
	 * 		Note: Should not print user IDs.
	 */
//	@Test 
//	public void testGetPlayerDataFromFile1() throws IOException{
//		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
//		readPlayers.nextInt();
//		System.out.println(getPlayerDataFromFile(readPlayers).toString());
//		System.out.println("\n"); // to separate outputs
//		readPlayers.close();
//	}
	/*
	 * Test: Load two players from file and print
	 * Status: Passed
	 * Setup: data.txt has 2 users, usernames user1 and user2, IDs 1 and 2, and both have default data
	 * Expected Result: Should print out user1 then 1000 (score) then 1 (rank) then 7 zeroes (rest of data)
	 * 		Then should print out user2 then 1000 (score) then 1 then 7 zeroes (rest of data)
	 * 		Note: Should not print user IDs.
	 */
//	@Test 
//	public void testGetPlayerDataFromFile2() throws IOException{
//		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
//		readPlayers.nextInt();
//		System.out.println(getPlayerDataFromFile(readPlayers).toString());
//		System.out.println(getPlayerDataFromFile(readPlayers).toString());
//		System.out.println("\n");
//		readPlayers.close();
//	}
	/*
	 * Test: Load three players from file with different stats and print
	 * Status: Passed
	 * Setup: data.txt has three players with usernames user1, user2, user3 ranks 1, 2, 3, IDs 1, 2, 3
	 * and each has different stats
	 * Expected Output: user1 then 1000 then 1 then 7 pieces of data with 1s, then user2 then 222 then 2 then 7 pieces
	 * of data having 2s, then user3 then 3333 then 3 then 7 pieces of data having 3s
	 * 		Note: Should not print user IDs.
	 */
//	@Test
//	public void testGetPlayerDataFromFile3() throws IOException{
//		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
//		readPlayers.nextInt();
//		System.out.println(getPlayerDataFromFile(readPlayers).toString());
//		System.out.println(getPlayerDataFromFile(readPlayers).toString());
//		System.out.println(getPlayerDataFromFile(readPlayers).toString());
//		System.out.println("\n");
//		readPlayers.close();
//	}
	/*
	 * Status: Passed
	 * 
	 */
	@Test
	public void testReadDataIntoArrayListFromFile() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(board.toString());
		System.out.println("\n");
	}
	/*
	 * Status: Passed
	 */
	@Test
	public void testResetPlayerDataInArrayList() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.resetPlayerDataInArrayList(2);
		System.out.println(board.toString());
		System.out.println("\n");
	}
	/*
	 * Status: Passed, because the constructor initializes username to empty String
	 */
	@Test
	public void testAddNewPlayerToArrayList() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		Player newPlayer = new Player();
		board.addNewPlayerToArrayList(newPlayer);
		System.out.println(board.toString());
		System.out.println("\n");
	}

	@Test
	public void testCheckForPlayerInArrayList() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePlayerDataInArrayList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendPlayerDataToFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testWinPercentage() {
		Scoreboard board = new Scoreboard();
		assertEquals(.5, board.winPercentage(5, 10), .0001);
	}

	@Test
	public void testCalculateScore1() {
		Scoreboard board = new Scoreboard();
		assertEquals(2016, board.calculateScore(2000, 3000, 1), .1);
	}

	@Test
	public void testSortArrayListByScore() throws IOException{
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		Scoreboard board = new Scoreboard();
		board.getPlayerDataFromFile(readPlayers);
		assertNotNull(board.getPlayerArrayList());
	}
	
	@Test
	public void testGetPlayerArrayList() throws IOException{
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		Scoreboard board = new Scoreboard();
		board.getPlayerDataFromFile(readPlayers);
		assertNotNull(board.getPlayerArrayList());
	}

}
