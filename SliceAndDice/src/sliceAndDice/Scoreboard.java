package sliceAndDice;
 
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import sliceAndDice.Player;

import java.lang.Integer;

/* Sources:
 * https://metinmediamath.wordpress.com/2013/11/27/how-to-calculate-the-elo-rating-including-example/
 */


/**
 * Scoreboard2 reads and writes data to and from data.txt, loads ArrayList of Players with data, computes
 * scores for Players, and sorts by scores to determine ranks.
 * @author Andrew Stanton, PIN: 817, CSE 360, Spring 2016
 * @version 4/15/16
 */
public class Scoreboard{
static ArrayList<Player> players;

	/**
	 * Constructor that creates a new ArrayList of Players.
	 */
	public Scoreboard() {
	players = new ArrayList<Player>();
	}
	/**
	 * Returns Scoreboard's ArrayList of Players.
	 * @return ArrayList of Players
	 */
	public static ArrayList<Player> getPlayerArrayList() {
		return players;
	}
	/**
	 * Given an ID, returns Player or null, if Id was not found.
	 * @param userID ID of Player to get
	 * @return Player with given ID or null
	 */
	public static Player getPlayerByID(int userID) {
		boolean found = false;
		int index = 0;
		Player playerToFind = null;
		while(index < players.size() && !found) {
			if(players.get(index).getID() == userID) {
				playerToFind = players.get(index);
				found = true;
			}
			index++;
		}
		return playerToFind;
	}
	/**
	 * Given Player username, returns Player or null if not found.
	 * @param username username of Player to get
	 * @return Player with given username or null
	 */
	public static Player getPlayerByUsername(String username) {
		boolean found = false;
		int index = 0;
		Player playerToFind = null;
		while(index < players.size() && !found) {
			if(players.get(index).getUsername().equals(username) == true) {
				playerToFind = players.get(index);
				found = true;
			}
			index++;
		}
		return playerToFind;
	}
	/**
	 * Given username, returns ID or -1 if Player not found.
	 * @param username username of Player
	 * @return ID or -1
	 */
	public static int getIDByUsername(String username) {
		boolean found = false;
		int index = 0;
		int playerID = -1;
		while(index < players.size() && !found) {
			if(players.get(index).getUsername().equals(username) == true) {
				playerID = players.get(index).getID();
				found = true;
			}
			index++;
		}
		return playerID;
	}
	/**
	 * Given ID, returns username of Player with ID.
	 * @param userID ID of Player to get
	 * @return username or empty String
	 */
	public static String getUsernameByID(int userID) {
		boolean found = false;
		int index = 0;
		String playerUsername = "";
		while(index < players.size() && !found) {
			if(players.get(index).getID() == userID) {
				playerUsername = players.get(index).getUsername();
				found = true;
			}
			index++;
		}
		return playerUsername;
	}
	/**
	 * Returns Player with data from data.txt. Used by readDataIntoArrayListFromFile.
	 * @param readPlayers Scanner used to read players
	 * @return Player with information from data.txt
	 */
	public Player getPlayerDataFromFile(Scanner readPlayers) {
		Player newPlayer = new Player(readPlayers);			
		return newPlayer;
	}
	/**
	 * Fills Scoreboard's ArrayList of Players with Players from file.
	 * @throws IOException
	 */
	public void readDataIntoArrayListFromFile() throws IOException{
//		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
//		int numberOfPlayers = readPlayers.nextInt();
//		for(int count = 0; count < numberOfPlayers; count++) {
//			players.add(getPlayerDataFromFile(readPlayers));
//		}
//		readPlayers.close();
		
		Scanner readPlayers = null;
		try {
			readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		}
		catch(FileNotFoundException fnfe) {
		PrintWriter createNewFile = new PrintWriter("data.txt", "UTF-8");
		createNewFile.write(String.valueOf(0));
		createNewFile.write("\n");
		createNewFile.close();
		}
		finally { // if numberOfPlayers is 0, it should skip the for loop
			readPlayers.close();
		}
		readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		int numberOfPlayers = readPlayers.nextInt();
		for(int count = 0; count < numberOfPlayers; count++) {
			players.add(getPlayerDataFromFile(readPlayers));
		}
		readPlayers.close();
	}
	/**
	 * Resets a Player's data in Scoreboard's ArrayList.
	 * @param userID ID of Player with data to be reset
	 */
    public void resetPlayerDataInArrayList(int userID) {
    	for(int index = 0; index < players.size(); index++) {
    		if(players.get(index).getID() == userID) {
    			players.get(index).getPlayerData().resetData();
    		}
    	}
    }
    /**
     * Adds a new Player to Scoreboard's ArrayList.
     * @param newPlayer Player to be added
     */
    public void addNewPlayerToArrayList(Player newPlayer) {
    	players.add(newPlayer);
    }
    /**
     * Adds a new Player with given username and default ID to Scoreboard's ArrayList. 
     * @param username username for new Player
     */
    public void addNewPlayerFromUsername(String username) {
    	Player newPlayer = new Player(username, players.size());
    	players.add(players.size(), newPlayer);
    }
    /**
     * Returns true if Player is in Scoreboard's ArrayList and false if Player is not in Scoreboard's ArrayList.
     * @param userID ID of Player to be checked for
     * @return true or false
     */
    public boolean checkForPlayerInArrayList(int userID) {
    	boolean found = false;
    	int index = 0;
    	while(index < players.size() && !found) {
    		if(players.get(index).getID() == userID) {
    			found = true;
    		}
    		index++;
    	}
    	return found;
    }
    /*
     * Note: It seems like the following method is not needed because Players will be updated automatically
     */
 //   /**
 //    * Updates data of Player in Scoreboard's ArrayList using the Player's updated data.
 //    * @param playerToUpdate Player with data to update
 //    */
 //   public static void updatePlayerDataInArrayList(Player playerToUpdate) {
 //   	for(int index = 0; index < players.size(); index++) {
 //   		if(players.get(index).getID() == playerToUpdate.getID()) {
 //   			players.set(index, playerToUpdate);
 //   		}
 //   	}
 //   }

    /**
     * Returns Scoreboard's ArrayList of Players with information on separate lines.
     */
    public String toString() {
    	String playerArrayListString = "";
    	for(int index = 0; index < players.size(); index++) {
    		playerArrayListString += (players.get(index).toString());
    	}
    	return playerArrayListString;
    }
	/**
	 * Writes numberOfPlayers and Scoreboard's ArrayList of Players to file.
	 * @throws IOException
	 */
	public void sendPlayerDataToFile() throws IOException{
		FileWriter writer = new FileWriter("data.txt");
        BufferedWriter playerWriter = new BufferedWriter(writer);
        Integer numPlayers = players.size();
        Integer id;
    	playerWriter.write(numPlayers.toString());
    	playerWriter.write("\n");
        for(int count = 0; count < players.size(); count++) {
        	id = players.get(count).getID();
        	playerWriter.write(id.toString());
        	playerWriter.write("\n");
        	playerWriter.write((players.get(count).toString()));
        }
        playerWriter.close();
	}
    /**
     * Given numberOfWins and numberOfGames, returns win percentage.
     * @param numberOfWins number of times Player has won a battle
     * @param numberOfGames number of battles the Player has played
     * @return win percentage
     */
    public float winPercentage(int numberOfWins, int numberOfGames) {
    	float numWins = (float) numberOfWins;
    	float numGames = (float) numberOfGames;
    	return (numWins / numGames);
    }
    /**
     * Given scores of two Players and the winner, returns the newly computed score for Player with oldScore1.
     * @param oldScore1 current score of player 1
     * @param oldScore2 current score of player 2
     * @param winner which player won
     * @return new score of Player whose old score was oldScore1
     */
    /*
    public static double calculateScore2(double oldScore1, double oldScore2, int winner) {
    	final int kValue = 16;
    	double number1 = Math.pow(10, (oldScore1 / 400));
    	double number2 = Math.pow(10, (oldScore2 / 400));
    	double expectedScore1 = number1 / (number1 + number2);
    	//double expectedScore2 = number2 / (number1 + number2);
    	double actualScore1;
    	//double actualScore2;
    	if(winner == 1) { // p1 is winner
    		actualScore1 = 1;
    		//actualScore2 = 0;
    	}
    	else { // p2 is winner
    		actualScore1 = 0;
    		//actualScore2 = 1;
    	}
    	double newScore1 = oldScore1 + (kValue * (actualScore1 - expectedScore1));
    	//oldScore2 = oldScore2 + (kValue * (actualScore2 - expectedScore2));
    	return newScore1;
    }
     */
    /**
     * Calculates new scores for the two input Players based on order of input.
     * @param winner Player object of Player who won the Game
     * @param loser Player object of Player who lost the Game
     */
    public static void calculateNewScore(int winnerID, int loserID) {
    	Player winner;
    	Player loser;
    	if(winnerID == Robot.getRobotID()) {
    		winner = new Robot();
    		loser = Scoreboard.getPlayerByID(loserID);
    	}
    	else if(loserID == Robot.getRobotID()) {
    		winner = Scoreboard.getPlayerByID(winnerID);
    		loser = new Robot();
    	}
    	else {
    		winner = Scoreboard.getPlayerByID(winnerID);
    		loser = Scoreboard.getPlayerByID(loserID);
    	}
    	double differential;
    	// calculate differential
    	differential = (double)((double)loser.getPlayerData().getScore() / winner.getPlayerData().getScore()) * 20;
    	// update winner and loser scores
    	winner.getPlayerData().setScore(winner.getPlayerData().getScore() + differential);
    	loser.getPlayerData().setScore(loser.getPlayerData().getScore() - differential);
    }
    /**
     * Bubble sorts Scoreboard's ArrayList of Players by score, allowing the rank to be determined by position in the ArrayList.
     * @param players ArrayList of Players to sort
     */
    public void sortArrayListByScore() {
    	boolean sorted = false;
    	boolean swapped = false;
    	Player tempPlayer;
    	while(!sorted) {
    		swapped = false;
    		for(int index = 0; index < players.size() - 1; index++) {
    			if((players.get(index).getPlayerData().getScore()) < (players.get(index + 1).getPlayerData().getScore())) {
    				tempPlayer = players.get(index);
    				players.set(index, players.get(index + 1));
    				players.set(index + 1, tempPlayer);
    				swapped = true;
    			}
    		}
    		if(swapped == false) {
    			sorted = true;
    		}
    	}
    }
    /**
     * Removes all Players from Scoreboard's ArrayList of Players. Used when the user wants to reset all data.
     */
    public void clearAllData() {
    	players.clear();
    }
}