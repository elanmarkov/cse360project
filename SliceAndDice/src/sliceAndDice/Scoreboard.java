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
	 * Returns Player with data from data.txt.
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
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
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
    	Player newPlayer = new Player(username, players.size() + 1);
    	players.add(newPlayer);
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
    /**
     * Updates data of Player in Scoreboard's ArrayList using the Player's updated data.
     * @param playerToUpdate Player with data to update
     */
    public void updatePlayerDataInArrayList(Player playerToUpdate) {
    	for(int index = 0; index < players.size(); index++) {
    		if(players.get(index).getID() == playerToUpdate.getID()) {
    			players.set(index, playerToUpdate);
    		}
    	}
    }
/*
prints out a Player, but not longer needed.
 */
//    public String playerToString(Player player) {
//        String dataString = "";
//        dataString = dataString + player.getID() + "\n";
//        dataString = dataString + player.getUsername() + "\n";
//        dataString = dataString + player.getPlayerData().getScore() + "\n";
//        dataString = dataString + player.getPlayerData().getRank() + "\n";
//        dataString = dataString + player.getPlayerData().getGame() + "\n";
//        dataString = dataString + player.getPlayerData().getWin() + "\n";
//       //dataString = dataString + (player.getPlayerData().getGame() / player.getPlayerData().getWin()) + "\n";
//        //dataString = dataString + player.getPlayerData().getTurn() + "\n";
//        dataString = dataString + player.getPlayerData().getAttack() + "\n";
//        //dataString = dataString + player.getPlayerData().getSPAttack() + "\n";
//        dataString = dataString + player.getPlayerData().getMeal() + "\n";
//        dataString = dataString + player.getPlayerData().getHPLost() + "\n";
//        dataString = dataString + player.getPlayerData().getManaUsed() + "\n";
//        dataString = dataString + player.getPlayerData().getFoodUsed() + "\n";
//      return dataString;
//    }
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
    	playerWriter.write(numPlayers.toString());
    	playerWriter.write("\n");
        for(int count = 0; count < players.size(); count++) {
        	playerWriter.write((players.get(count).toString()));
        	playerWriter.write("\n");
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
    public double calculateScore(double oldScore1, double oldScore2, int winner) {
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
    			if((players.get(index).getPlayerData().getScore()) > (players.get(index + 1).getPlayerData().getScore())) {
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
}