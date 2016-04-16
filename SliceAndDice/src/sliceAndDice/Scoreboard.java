package sliceAndDice;
 
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import sliceAndDice.Player;

import java.lang.Integer;



/**
 * Scoreboard2 reads and writes data to and from data.txt, loads ArrayList of Players with data, computes
 * scores for Players, and sorts by scores to determine ranks.
 */
public class Scoreboard{
static ArrayList<Player> players;

/**
 * constructor creating new ArrayList of Players
 */
public Scoreboard() {
players = new ArrayList<Player>();
}
	/**
	 * @return ArrayList of Players
	 */
	static public ArrayList<Player> getPlayerArrayList() {
		return players;
	}
	
	/**
	 * given ID, returns Player or null if not found
	 * @param userID ID of Player to get
	 * @return Player with given ID or null
	 */
	static public Player getPlayerByID(int userID) {
		for(int index = 0; index < players.size(); index++){
			if(players.get(index).getID() == userID) {
				return players.get(index);
			}
		}
		return null;
	}
	/**
	 * given Player username, returns Player or null if not found
	 * @param username username of Player to get
	 * @return Player with given username or null
	 */
	static public Player getPlayerByUsername(String username) {
		for(int index = 0; index < players.size(); index++){
			if(players.get(index).getUsername() == username) {
				return players.get(index);
			}
		}
		return null;
	}
	
	/**
	 * given username, returns ID or -1 if Player not found
	 * @param username username of Player
	 * @return ID or -1
	 */
	static public int getIDByUsername(String username) {
		for(int index = 0; index < players.size(); index++) {
			if(players.get(index).getUsername() == username) {
				return players.get(index).getID();
			}
		}
		return -1;
	}
	
	/**
	 * given ID, returns username of Player with ID
	 * @param userID ID of Player to get
	 * @return username or empty String
	 */
	static public String getUsernameByID(int userID) {
		for(int index = 0; index < players.size(); index++) {
			if(players.get(index).getID() == userID) {
				return players.get(index).getUsername();
			}
		}
		return "";
	}
	
	/**
	 * returns Player with data from data.txt
	 * @param readPlayers Scanner used to read players
	 * @return Player with information from data.txt
	 */
	public Player getPlayerDataFromFile(Scanner readPlayers) {
		Player newPlayer = new Player(readPlayers);			
		return newPlayer;
	}
	
	/**
	 * fills ArrayList of Players with Players from file
	 * @throws IOException
	 */
	public void readDataIntoArrayListFromFile() throws IOException{
		Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("data.txt")));
		int numberOfPlayers = readPlayers.nextInt();
		for(int count = 0; count < numberOfPlayers; count++) {
			players.set(count, getPlayerDataFromFile(readPlayers));
		}
	}
	
	/**
	 * resets a Player's data in the ArrayList
	 * @param userID ID of Player with data to be reset
	 */
    public void resetPlayerDataInArrayList(int userID) {
    	for(int index = 0; index < players.size(); index++) {
    		if(players.get(index).getID() == userID) {
    			players.get(index).getPlayerData().setScore(1000);
    			players.get(index).getPlayerData().setRank(0);
    			players.get(index).getPlayerData().setGame(0);
    			players.get(index).getPlayerData().setWin(0);
    			players.get(index).getPlayerData().setAttack(0);
    			players.get(index).getPlayerData().setMeal(0);
    			players.get(index).getPlayerData().setHPLost(0);
    			players.get(index).getPlayerData().setManaUsed(0);
    			players.get(index).getPlayerData().setFoodUsed(0);
    		}
    	}
    }
    
    /**
     * adds new Player to the ArrayList
     * @param newPlayer Player to be added
     */
    public void addNewPlayerToArrayList(Player newPlayer) {
    	players.add(newPlayer);
    }
    
    /**
     * returns true if Player is in ArrayList and false if Player is not in ArrayList
     * @param userID ID of Player to be checked for
     * @return true or false
     */
    public boolean checkForPlayerInArrayList(int userID) {
    	for(int index = 0; index < players.size(); index++) {
    		if(players.get(index).getID() == userID) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * updates data of Player in ArrayList using the Player's updated data
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
prints out a player, but not longer needed
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
     * toString for Scoreboard, prints out the ArrayList of Players with information on separate lines
     */
    public String toString() {
    	String playerArrayListString = "";
    	for(int index = 0; index < players.size(); index++) {
    		playerArrayListString += (players.get(index).toString());
    	}
    	return playerArrayListString;
    }
    
	/**
	 * writes numberOfPlayers and ArrayList of Players to file
	 * @throws IOException
	 */
	public void sendPlayerDataToFile() throws IOException{
		FileWriter writer = new FileWriter("data.txt");
        BufferedWriter playerWriter = new BufferedWriter(writer);
        Integer numPlayers = players.size();
    	playerWriter.write(numPlayers.toString());
        for(int count = 0; count < players.size(); count++) {
        	playerWriter.write((players.get(count).toString()));
        }
        playerWriter.close();
	}
     
    /**
     * given numberOfWins and numberOfGames, returns win percentage
     * @param numberOfWins number of times Player has won a battle
     * @param numberOfGames number of battles the Player has played
     * @return win percentage
     */
    public float winPercentage(int numberOfWins, int numberOfGames) {
    	return (numberOfWins / numberOfGames);
    }
    
    /**
     * given scores of two Players and the winner, returns the newly computed score for Player with oldScore1
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
     * bubble sorts the ArrayList of Players by score, allowing the rank to be determined by position in ArrayList
     * @param players ArrayList of Players to sort
     */
    public void sortArrayListByScore() {
    	boolean sorted = false;
    	boolean swapped = false;
    	Player tempPlayer;
    	while(!sorted) {
    		swapped = false;
    		for(int index = 0; index < players.size(); index++) {
    			if(players.get(index).getPlayerData().getScore() > players.get(index + 1).getPlayerData().getScore()) {
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