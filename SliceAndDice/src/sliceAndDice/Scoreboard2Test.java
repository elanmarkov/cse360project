package sliceAndDice;
 
import static org.junit.Assert.*;
 
//import java.io.IOException;
 
import org.junit.Test;

public class Scoreboard2Test extends Scoreboard2{
	 /*
	@Test // passed - add user1 when statistics.txt just has 0
    public void testAddNewPlayerStatsToStats1() throws IOException {    
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.addNewPlayerStatsToStats("user1"); // this should add 1 then user1 then five 0s
    }
  * */

    /*
    @Test // passed - add user1 when statistics.txt just has 0
    public void testAddNewPlayerStatsToStats1() throws IOException {    
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.addNewPlayerStatsToStats("user1"); // this should add 1 then user1 then five 0s
    }
 
     @Test // passed - add user1 when statistics.txt just has 0
    public void testAddNewPlayerStatsToStats2() throws IOException {
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.addNewPlayerStatsToStats("user1"); // this should add 1 then user1 then five 0s
        scoreboard.addNewPlayerStatsToStats("user2"); // this should add user2 then five 0s
    }
     
    @Test // passed - add user1 when statistics.txt just has 0
    public void testAddNewPlayerStatsToStats3() throws IOException {
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.addNewPlayerStatsToStats("user1"); // this should add 1 then user1 then five 0s
        scoreboard.addNewPlayerStatsToStats("user2"); // this should add user2 then five 0s
        scoreboard.addNewPlayerStatsToStats("user3"); // this should add user3 then five 0s
    }
     */
/*
    @Test // seemed to pass - it did not modify statistics.txt when statistics.txt only had 0
    public void testResetPlayerStatsInStats1() throws IOException{
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.resetPlayerStatsInStats("user1");
    }
*/
     
/*
    @Test // passed - it changed 5s to 0s when statistics.txt only had user1 with five 5s
    public void testResetPlayerStatsInStats2() throws IOException{
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.resetPlayerStatsInStats("user1");
    }
*/
 
/*
    @Test // passed - it changed nothing when statistics.txt only had user2 with five 5s
    public void testResetPlayerStatsInStats3() throws IOException{
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.resetPlayerStatsInStats("user1");
    }
*/
 
/*
    @Test // passed - changed user1 to have five 0s when statistics.txt had user1 (first) with five 5s, user2 (second) with five 6s, user3 (third) with five 7s
    public void testResetPlayerStatsInStats4() throws IOException{
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.resetPlayerStatsInStats("user1");
    }
*/
 
/*
    @Test // passed - changed user2 to have five 0s when statistics.txt had user1 (first) with five 5s, user2 (second) with five 6s, user3 (third) with five 7s
    public void testResetPlayerStatsInStats4() throws IOException{
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.resetPlayerStatsInStats("user2");
    }
*/
 
/*
    @Test // passed - changed user3 to have five 0s when statistics.txt had user1 (first) with five 5s, user2 (second) with five 6s, user3 (third) with five 7s
    public void testResetPlayerStatsInStats4() throws IOException{
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.resetPlayerStatsInStats("user3");
    }
*/
 
/*
    @Test // passed - changed user1 to have five 16s and user2 to have five 32s when statistics.txt had user1 (first) with five 5s, user2 (second) with five 6s, user3 (third) with five 7s
    public void testUpdateTwoPlayersStatsInStats() throws IOException{
        PlayerStats stats1 = new PlayerStats();
        PlayerStats stats2 = new PlayerStats();
        stats1.username = "user1";
        stats1.numberOfWins = 16;
        stats1.numberOfGames = 16;
        stats1.numberOfAttacks = 16;
        stats1.numberOfSPAttacks = 16;
        stats1.numberOfMeals = 16;
        stats2.username = "user2";
        stats2.numberOfWins = 32;
        stats2.numberOfGames = 32;
        stats2.numberOfAttacks = 32;
        stats2.numberOfSPAttacks = 32;
        stats2.numberOfMeals = 32;
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.updateTwoPlayersStatsInStats(stats1, stats2);
    }
*/
 
/*
    @Test // passed - changed user1 to have five 16s and user3 to have five 32s when statistics.txt had user1 (first) with five 5s, user2 (second) with five 6s, user3 (third) with five 7s
    public void testUpdateTwoPlayersStatsInStats() throws IOException{
        PlayerStats stats1 = new PlayerStats();
        PlayerStats stats2 = new PlayerStats();
        stats1.username = "user1";
        stats1.numberOfWins = 16;
        stats1.numberOfGames = 16;
        stats1.numberOfAttacks = 16;
        stats1.numberOfSPAttacks = 16;
        stats1.numberOfMeals = 16;
        stats2.username = "user3";
        stats2.numberOfWins = 32;
        stats2.numberOfGames = 32;
        stats2.numberOfAttacks = 32;
        stats2.numberOfSPAttacks = 32;
        stats2.numberOfMeals = 32;
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.updateTwoPlayersStatsInStats(stats1, stats2);
    }
*/
 
/*
    @Test // passed - changed user2 to have five 16s and user3 to have five 32s when statistics.txt had user1 (first) with five 5s, user2 (second) with five 6s, user3 (third) with five 7s
    public void testUpdateTwoPlayersStatsInStats() throws IOException{
        PlayerStats stats1 = new PlayerStats();
        PlayerStats stats2 = new PlayerStats();
        stats1.username = "user2";
        stats1.numberOfWins = 16;
        stats1.numberOfGames = 16;
        stats1.numberOfAttacks = 16;
        stats1.numberOfSPAttacks = 16;
        stats1.numberOfMeals = 16;
        stats2.username = "user3";
        stats2.numberOfWins = 32;
        stats2.numberOfGames = 32;
        stats2.numberOfAttacks = 32;
        stats2.numberOfSPAttacks = 32;
        stats2.numberOfMeals = 32;
        Scoreboard2 scoreboard = new Scoreboard2();
        scoreboard.updateTwoPlayersStatsInStats(stats1, stats2);
    }
*/
 
/*
    @Test // seems to pass - both methods of printing printed the correct values when statistics.txt had user1 (first) with five 5s, user2 (second) with five 6s, user3 (third) with five 7s
    public void testReadPlayerStatsFromStats() throws IOException{
        PlayerStats stats1 = new PlayerStats();
        PlayerStats stats2 = new PlayerStats();
        PlayerStats stats3 = new PlayerStats();
        Scoreboard2 scoreboard = new Scoreboard2();
        stats1 = scoreboard.readPlayerStatsFromStats("user1");
        stats2 = scoreboard.readPlayerStatsFromStats("user2");
        stats3 = scoreboard.readPlayerStatsFromStats("user3");
        scoreboard.printPlayerStats(stats1);
        scoreboard.printPlayerStats(stats2);
        scoreboard.printPlayerStats(stats3);
        System.out.print(stats1.playerStatsToString());
        System.out.print(stats2.playerStatsToString());
        System.out.print(stats3.playerStatsToString());
    }
*/
 
    @Test
    public void testMain() {
        fail("Not yet implemented");
    }
    @Test
    public void testAddPlayerToPlayers() {
        fail("Not yet implemented");
    }
 
    @Test
    public void testCheckPlayerInPlayers() {
        fail("Not yet implemented");
    }
}