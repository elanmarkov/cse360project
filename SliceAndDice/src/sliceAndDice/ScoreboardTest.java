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
	@Test 
	public void testGetPlayerDataFromFile1() throws IOException{
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		readPlayers.nextInt();
		System.out.println(getPlayerDataFromFile(readPlayers).toString());
		System.out.println("\n"); // to separate outputs
	}
	/*
	 * Test: Load two players from file and print
	 * Status: Passed
	 * Setup: data.txt has 2 users, usernames user1 and user2, IDs 1 and 2, and both have default data
	 * Expected Result: Should print out user1 then 1000 (score) then 1 (rank) then 7 zeroes (rest of data)
	 * 		Then should print out user2 then 1000 (score) then 1 then 7 zeroes (rest of data)
	 * 		Note: Should not print user IDs.
	 */
	@Test 
	public void testGetPlayerDataFromFile2() throws IOException{
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		readPlayers.nextInt();
		System.out.println(getPlayerDataFromFile(readPlayers).toString());
		System.out.println(getPlayerDataFromFile(readPlayers).toString());
		System.out.println("\n");
	}
	/*
	 * Test: Load three players from file with different stats and print
	 * Status: Passed
	 * Setup: data.txt has three players with usernames user1, user2, user3 ranks 1, 2, 3, IDs 1, 2, 3
	 * and each has different stats
	 * Expected Output: user1 then 1000 then 1 then 7 pieces of data with 1s, then user2 then 222 then 2 then 7 pieces
	 * of data having 2s, then user3 then 3333 then 3 then 7 pieces of data having 3s
	 * 		Note: Should not print user IDs.
	 */
	@Test
	public void testGetPlayerDataFromFile3() throws IOException{
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		readPlayers.nextInt();
		System.out.println(getPlayerDataFromFile(readPlayers).toString());
		System.out.println(getPlayerDataFromFile(readPlayers).toString());
		System.out.println(getPlayerDataFromFile(readPlayers).toString());
		System.out.println("\n");
	}
	/*
	 * 
	 */
	@Test
	public void testReadDataIntoArrayListFromFile() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(board.toString());
		System.out.println("\n");
	}

	@Test
	public void testResetPlayerDataInArrayList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNewPlayerToArrayList() {
		fail("Not yet implemented");
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
	public void testSortArrayListByScore() {
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		Scoreboard board = new Scoreboard();
		board.getPlayerDataFromFile(readPlayers);
	}

}
