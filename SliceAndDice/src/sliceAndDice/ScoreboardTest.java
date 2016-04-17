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
	
	@Test
	public void testScoreboardConstructor1() {
		Scoreboard board = new Scoreboard();
		assertNotNull(board);
	}
//	@Test
//	public void testGetPlayerDataFromFile() throws IOException{
//		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
//		Scoreboard board = new Scoreboard();
//		board.getPlayerDataFromFile(readPlayers);
//		assertNotNull(Scoreboard.getPlayerArrayList());
//	}
	
//	@Test
//	public void testGetPlayerArrayList() throws IOException {
//		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
//		Scoreboard board = new Scoreboard();
//		board.getPlayerDataFromFile(readPlayers);
//		System.out.println(board.getPlayerArrayList().toString());	
//	}
	/*
	 * Status: Passed
	 */
//	@Test
//	public void testGetPlayerByUsername() throws IOException {
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		System.out.println(Scoreboard.getPlayerByUsername("user1").toString());
//		//System.out.println(board.toString());
//	}
	/*
	 * Status: Passed
	 */
//	@Test
//	public void testGetPlayerByID() throws IOException {
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		System.out.println(Scoreboard.getPlayerByID(3).toString());
//	}
	/*
	 * Status: Passed
	 */
//	@Test
//	public void testGetIDByUsername() throws IOException {
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		System.out.println(Scoreboard.getIDByUsername("user1"));
//	}
	/*
	 * Status: Passed
	 */
	@Test
	public void testGetUsernameByID() throws IOException {
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(Scoreboard.getUsernameByID(2));
	}

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
//	@Test
//	public void testReadDataIntoArrayListFromFile() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		System.out.println(board.toString());
//		System.out.println("\n");
//	}
	/*
	 * Status: Passed
	 */
//	@Test
//	public void testResetPlayerDataInArrayList() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		board.resetPlayerDataInArrayList(2);
//		System.out.println(board.toString());
//		System.out.println("\n");
//	}
	/*
	 * Status: Passed, because the constructor initializes username to empty String
	 */
//	@Test
//	public void testAddNewPlayerToArrayList() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		Player newPlayer = new Player();
//		board.addNewPlayerToArrayList(newPlayer);
//		System.out.println(board.toString());
//		System.out.println("\n");
//	}
	/*
	 * Status: Passed - returned true because user3 is in ArrayList
	 */
//	@Test
//	public void testCheckForPlayerInArrayList1() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		System.out.print(board.checkForPlayerInArrayList(3));
//		System.out.println("\n");
//	}
	/*
	 * Status: Passed - returned false because no user has ID 4 in ArrayList
	 */
//	@Test
//	public void testCheckForPlayerInArrayList2() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		System.out.print(board.checkForPlayerInArrayList(4));
//		System.out.println("\n");
//	}
	/*
	 * Status: Passed - incremented the game count to 23 for user2
	 */
//	@Test
//	public void testUpdatePlayerDataInArrayList() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		board.getPlayerByID(2).getPlayerData().incrGameCount();
//		board.updatePlayerDataInArrayList(board.getPlayerByID(2));
//		System.out.print(board.toString());
//		System.out.println("\n");
//	}
	/*
	 * Status: Passed - it outputted the data to outputData.txt file
	 * Setup: change the method to write to outputData.txt
	 * Note: it does not print the IDs, so it is correct
	 */
//	@Test
//	public void testSendPlayerDataToFile() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		board.sendPlayerDataToFile();
//	}

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


	/*
	 * Status: Passed
	 * Expected output: Prints out Players in order of score: user2, user1, user3
	 */
//	@Test
//	public void testSortArrayListByScore1() throws IOException{
//		Scoreboard board = new Scoreboard();
//		board.readDataIntoArrayListFromFile();
//		//System.out.println(board.toString());
//		board.sortArrayListByScore();
//		System.out.println(board.toString());
//	}
	/*
	 * Status: Passed
	 */
//	@Test
//	public void testAddNewPlayerFromUsername1() {
//		Scoreboard board = new Scoreboard();
//		board.addNewPlayerFromUsername("user222");
//		System.out.println(board.toString());
//		System.out.println("ID = " + Scoreboard.getIDByUsername("user222"));
//	}
	/*
	 * Status:
	 */
//	@Test
//	public void testAddNewPlayerFromUsername2() {
//		Scoreboard board = new Scoreboard();
//		board.addNewPlayerFromUsername("user222");
//		board.addNewPlayerFromUsername("user333");
//		board.addNewPlayerFromUsername("user444");
//		System.out.println(board.toString());
//		System.out.println("ID = " + Scoreboard.getIDByUsername("user222"));
//		System.out.println("ID = " + Scoreboard.getIDByUsername("user333"));
//		System.out.println("ID = " + Scoreboard.getIDByUsername("user444"));
//	}
}
