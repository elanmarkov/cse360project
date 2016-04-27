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
	 * Status: Passed
	@Test
	public void testScoreboardConstructor1() {
		Scoreboard board = new Scoreboard();
		assertNotNull(board);
	}
	 */

	/*
	 * Status: Passed
	@Test
	public void testScoreboardConstructor2() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		System.out.println(board.toString());
	}
	 */
	/*
	 * Status: Passed
	@Test
	public void testGetPlayerDataFromFile() throws IOException{
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		Scoreboard board = new Scoreboard();
		readPlayers.nextInt();
		assertNotNull(Scoreboard.getPlayerArrayList());
		System.out.println(board.getPlayerDataFromFile(readPlayers));
	}
	 */

	/*
	 * Status: Passed
	@Test
	public void testGetPlayerArrayList() throws IOException {
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.addNewPlayerFromUsername("newUser");
		System.out.println(Scoreboard.getPlayerArrayList().toString());
	}
	 */

	/*
	 * Status: Passed
	@Test
	public void testGetPlayerByUsername() throws IOException {
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(Scoreboard.getPlayerByUsername("user3").toString());
		board.addNewPlayerFromUsername("newUser");
		System.out.println(Scoreboard.getPlayerByUsername("newUser").toString());
		try{
			System.out.println(Scoreboard.getPlayerByUsername("wrongUser").toString());
		}
		catch(NullPointerException npe) {
			Scoreboard.getPlayerByUsername("user3").getPlayerData().resetData();
			System.out.println(Scoreboard.getPlayerByUsername("user3").toString());
		}
		//System.out.println(board.toString());
	} 
	 */
	
	/*
	 * Status: Passed
	 * 	@Test
	public void testGetPlayerByID() throws IOException {
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(Scoreboard.getPlayerByID(0).toString());
		System.out.println(Scoreboard.getPlayerByID(2).toString());
		System.out.println(Scoreboard.getPlayerByID(1).toString());
		System.out.println(Scoreboard.getPlayerByID(3).toString());
	}
	 */
	
	/*
	 * Status: Passed
	@Test
	public void testGetIDByUsername() throws IOException {
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.addNewPlayerFromUsername("user4");
		System.out.println(Scoreboard.getIDByUsername("user1"));
		System.out.println(board.toString());
	}
	 */
	/*
	 * Status: Passed
	@Test
	public void testGetUsernameByID() throws IOException {
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.addNewPlayerFromUsername("user4");
		board.addNewPlayerFromUsername("user5");
		System.out.println(Scoreboard.getUsernameByID(1));
	}
	 */

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
	 * 	@Test
	public void testReadDataIntoArrayListFromFile() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(board.toString());
		System.out.println("\n");
	}
	 */

	/*
	 * Status: Passed
		@Test
	public void testResetPlayerDataInArrayList() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(board.toString());
		board.resetPlayerDataInArrayList(0);
		System.out.println(board.toString());
		board.resetPlayerDataInArrayList(2);
		System.out.println(board.toString());
		board.resetPlayerDataInArrayList(1);
		System.out.println(board.toString());
	}
	 */

	/*
	 * Status: Passed, because the constructor initializes username to empty String
	 * 	@Test
	public void testAddNewPlayerToArrayList() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		Player newPlayer = new Player();
		board.addNewPlayerToArrayList(newPlayer);
		System.out.println(board.toString());
		System.out.println("\n");
	}
	 */

	/*
	 * Status: Passed - returned true because user3 is in ArrayList
	@Test
	public void testCheckForPlayerInArrayList1() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(board.toString());
		System.out.print(board.checkForPlayerInArrayList(2));
		System.out.println("\n");
	}
	 */

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
	 * 	@Test
	public void testUpdatePlayerDataInArrayList() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(board.getPlayerByID(2).toString());
		board.getPlayerByID(2).getPlayerData().incrGameCount();
		System.out.println(board.getPlayerByID(2).toString());
		board.getPlayerByID(2).getPlayerData().resetData();
		System.out.println(board.getPlayerByID(2).toString());
	}
	 */

	/*
	 * Status: Passed - it outputted the data to outputData.txt file
	 * Setup: change the method to write to outputData.txt
	 * Note: it does not print the IDs, so it is correct
	 */
	/*
	 * Status: Passed
	 */
	/*
	 * 	@Test
	public void testSendPlayerDataToFile() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.sendPlayerDataToFile();
	}
	 */
	/*
	 * Status: Passed
	 */
	/*
	 * 	@Test
	public void testAddPlayerAndSendPlayerDataToFile() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.addNewPlayerFromUsername("joseph");
		board.sendPlayerDataToFile();
	}
	 */
	/*
	 * Status: Passed
	 */
	/*
	@Test
	public void testAddPlayerAndSortAndSendPlayerDataToFile() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.addNewPlayerFromUsername("user4");
		board.addNewPlayerFromUsername("user5");
		board.addNewPlayerFromUsername("user6");
		Scoreboard.getPlayerByUsername("user4").getPlayerData().incrGameCount();
		Scoreboard.getPlayerByUsername("user5").getPlayerData().incrWinCount();
		Scoreboard.getPlayerByUsername("user6").getPlayerData().incrNumBaseAttacks();
		Scoreboard.calculateNewScore(3,0);
		board.sortArrayListByScore();
		board.sendPlayerDataToFile();
	}
	 */

	@Test
	public void testWinPercentage() {
		Scoreboard board = new Scoreboard();
		assertEquals(.5, board.winPercentage(5, 10), .0001);
	}
/*
	@Test
	public void testCalculateScore2Test1() {
		Scoreboard board = new Scoreboard();
		assertEquals(2016, Scoreboard.calculateScore2(2000, 3000, 1), .1);
	}
	@Test
	public void testCalculateScore2Test2() {
		Scoreboard board = new Scoreboard();
		assertEquals(2115.9, Scoreboard.calculateScore2(2100, 3100, 1), .1);
	}
 */

	@Test
	public void testCalculateScore0() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertNotNull(Scoreboard.getPlayerByID(0));
	}
	@Test
	public void testCalculateScore00() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertNotNull(Scoreboard.getPlayerByID(1));
	}
	@Test
	public void testCalculateScore000() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		board.addNewPlayerFromUsername("user3");
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		System.out.println(Scoreboard.getPlayerByID(0).toString());
		System.out.println(Scoreboard.getPlayerByID(1).toString());
		System.out.println(Scoreboard.getPlayerByID(2).toString());
		assertNotNull(Scoreboard.getPlayerByID(2));
	}
	
	@Test
	public void testCalculateScore1() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertEquals(1020, Scoreboard.getPlayerByUsername("user1").getPlayerData().getScore(), .000001);
	}
	@Test
	public void testCalculateScore2() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertEquals(980, Scoreboard.getPlayerByUsername("user2").getPlayerData().getScore(), .000001);
	}
	@Test
	public void testCalculateScore3() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertEquals(1039.215686, Scoreboard.getPlayerByUsername("user1").getPlayerData().getScore(), .000001);
	}
	@Test
	public void testCalculateScore4() {
		//quick comment
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertEquals(960.7843137, Scoreboard.getPlayerByUsername("user2").getPlayerData().getScore(), .0000001);
	}
	@Test
	public void testCalculateScore5() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.getPlayerByUsername("user1").getPlayerData().setScore(100);
		Scoreboard.getPlayerByUsername("user2").getPlayerData().setScore(2000);
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertEquals(500, Scoreboard.getPlayerByUsername("user1").getPlayerData().getScore(), .0000001);
	}
	@Test
	public void testCalculateScore6() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.getPlayerByUsername("user1").getPlayerData().setScore(100);
		Scoreboard.getPlayerByUsername("user2").getPlayerData().setScore(2000);
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		assertEquals(1500, Scoreboard.getPlayerByUsername("user2").getPlayerData().getScore(), .0000001);
	}
	@Test
	public void testCalculateScore7() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.getPlayerByUsername("user1").getPlayerData().setScore(1900);
		Scoreboard.getPlayerByUsername("user2").getPlayerData().setScore(2100);
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user2"), Scoreboard.getIDByUsername("user1"));
		assertEquals(1922.105263, Scoreboard.getPlayerByUsername("user1").getPlayerData().getScore(), .0000001);
	}
	@Test
	public void testCalculateScore8() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		board.addNewPlayerFromUsername("user2");
		Scoreboard.getPlayerByUsername("user1").getPlayerData().setScore(1900);
		Scoreboard.getPlayerByUsername("user2").getPlayerData().setScore(2100);
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Scoreboard.getIDByUsername("user2"));
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user2"), Scoreboard.getIDByUsername("user1"));
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user3"), Scoreboard.getIDByUsername("user1"));
		assertEquals(2077.894737, Scoreboard.getPlayerByUsername("user2").getPlayerData().getScore(), .0000001);
	}

	@Test
	public void testCalculateScoreRobot1() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user1");
		Player robotPlayer = new Robot();
		Scoreboard.calculateNewScore(Scoreboard.getIDByUsername("user1"), Robot.getRobotID());
		assertEquals(1020, Scoreboard.getPlayerByUsername("user1").getPlayerData().getScore(), .000001);
	}
 
	/*
	@Test
	public void testCalculateScoreRobot2() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user2");
		Player robotPlayer = new Robot();
		Scoreboard.calculateNewScore(Robot.getRobotID(), Scoreboard.getIDByUsername("user2"));
		assertEquals(980, Scoreboard.getPlayerByUsername("user2").getPlayerData().getScore(), .000001);
	}
	 */

	


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
	 * Status: Passed
	 	@Test
	public void testAddNewPlayerFromUsername2() {
		Scoreboard board = new Scoreboard();
		board.addNewPlayerFromUsername("user222");
		board.addNewPlayerFromUsername("user333");
		board.addNewPlayerFromUsername("user444");
		board.sortArrayListByScore();
		System.out.println(board.toString());
		System.out.println("ID = " + Scoreboard.getIDByUsername("user222"));
		System.out.println("ID = " + Scoreboard.getIDByUsername("user333"));
		System.out.println("ID = " + Scoreboard.getIDByUsername("user444"));
	}
	 */
	/*
	 * Status: Passed
	@Test
	public void testAddNewPlayerFromUsername2() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		board.addNewPlayerFromUsername("user222");
		board.addNewPlayerFromUsername("user333");
		board.addNewPlayerFromUsername("user444");
		board.sortArrayListByScore();
		System.out.println(board.toString());
		System.out.println("ID = " + Scoreboard.getIDByUsername("user222"));
		System.out.println("ID = " + Scoreboard.getIDByUsername("user333"));
		System.out.println("ID = " + Scoreboard.getIDByUsername("user444"));
	}
	 */
	/*
	 * Status: Passed
	@Test
	public void testClearAllData() throws IOException{
		Scoreboard board = new Scoreboard();
		board.readDataIntoArrayListFromFile();
		System.out.println(board.toString());
		board.addNewPlayerFromUsername("newUser");
		System.out.println(board.toString());
		board.clearAllData();
		System.out.println(board.toString());
		board.addNewPlayerFromUsername("newUser2");
		System.out.println(board.toString());
		board.addNewPlayerFromUsername("newUser3");
		System.out.println(board.toString());
	}
	 */

}
