package sliceAndDice;
 
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import sliceAndDice.Player;

import java.lang.Integer;



/**
 * Scoreboard2 reads and writes data to a file, computes statistics from the data, and loads
 * player objects with data. It contains the main method for the program.
 */
public class Scoreboard{
static ArrayList<Player> players;

public Scoreboard() {
players = new ArrayList<Player>();
}
	static public ArrayList<Player> getPlayerArrayList() {
		return players;
	}

	static public Player getPlayerByID(int userID) {
		for(int index = 0; index < players.size(); index++){
			if(players.get(index).getID() == userID) {
				return players.get(index);
			}
		}
		return null;
	}
	
	static public Player getPlayerByUsername(String username) {
		for(int index = 0; index < players.size(); index++){
			if(players.get(index).getUsername() == username) {
				return players.get(index);
			}
		}
		return null;
	}
	
	static public int getIDByUsername(String username) {
		for(int index = 0; index < players.size(); index++) {
			if(players.get(index).getUsername() == username) {
				return players.get(index).getID();
			}
		}
		return -1;
	}
	
	static public String getUsernameByID(int userID) {
		for(int index = 0; index < players.size(); index++) {
			if(players.get(index).getID() == userID) {
				return players.get(index).getUsername();
			}
		}
		return "";
	}
	
	/**
	 * returns a player loaded with values from file
	 * @param readPlayers Scanner used to read players
	 * @return player with values from file
	 */
	public Player getPlayerDataFromFile(Scanner readPlayers) {
		Player playerToLoad = new Player();
		playerToLoad.setID(readPlayers.nextInt());
		playerToLoad.setUsername(readPlayers.next());
		playerToLoad.getPlayerData().setScore(readPlayers.nextDouble());
		playerToLoad.getPlayerData().setRank(readPlayers.nextInt());
		playerToLoad.getPlayerData().setGame(readPlayers.nextInt());
		playerToLoad.getPlayerData().setWin(readPlayers.nextInt());
		playerToLoad.getPlayerData().setAttack(readPlayers.nextInt());
		playerToLoad.getPlayerData().setMeal(readPlayers.nextInt());
		playerToLoad.getPlayerData().setHPLost(readPlayers.nextInt());
		playerToLoad.getPlayerData().setManaUsed(readPlayers.nextInt());
		playerToLoad.getPlayerData().setFoodUsed(readPlayers.nextInt());
		return playerToLoad;
	}
	
	/**
	 * fills ArrayList of Players with players from file
	 * @param players ArrayList
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
	 * resets a player's data in the ArrayList to zero
	 * @param players
	 * @param userID
	 * @param numberOfPlayers
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
     * adds new player to the ArrayList
     * @param players
     * @param newPlayer
     */
    public void addNewPlayerToArrayList(Player newPlayer) {
    	players.add(newPlayer);
    }
    
    /**
     * checks if the player is in the ArrayList
     * @param players
     * @param userID
     * @param numberOfPlayers
     * @return
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
     * updates the Player data in the ArrayList
     * @param players
     * @param playerToUpdate
     * @param numberOfPlayers
     */
    public void updatePlayerDataInArrayList(Player playerToUpdate) {
    	for(int index = 0; index < players.size(); index++) {
    		if(players.get(index).getID() == playerToUpdate.getID()) {
    			players.set(index, playerToUpdate);
    		}
    	}
    }
	
	/**
	 * converts the given player object to string and returns it
	 * @param player
	 * @return String containing player's username, user ID, and data on separate lines
	 */
    public String playerToString(Player player) {
        String dataString = "";
        dataString = dataString + player.getID() + "\n";
        dataString = dataString + player.getUsername() + "\n";
        dataString = dataString + player.getPlayerData().getScore() + "\n";
        dataString = dataString + player.getPlayerData().getRank() + "\n";
        dataString = dataString + player.getPlayerData().getGame() + "\n";
        dataString = dataString + player.getPlayerData().getWin() + "\n";
       //dataString = dataString + (player.getPlayerData().getGame() / player.getPlayerData().getWin()) + "\n";
        //dataString = dataString + player.getPlayerData().getTurn() + "\n";
        dataString = dataString + player.getPlayerData().getAttack() + "\n";
        //dataString = dataString + player.getPlayerData().getSPAttack() + "\n";
        dataString = dataString + player.getPlayerData().getMeal() + "\n";
        dataString = dataString + player.getPlayerData().getHPLost() + "\n";
        dataString = dataString + player.getPlayerData().getManaUsed() + "\n";
        dataString = dataString + player.getPlayerData().getFoodUsed() + "\n";
        return dataString;
    }
	
    public String playerArrayListToString() {
    	String playerArrayListString = "";
    	for(int index = 0; index < players.size(); index++) {
    		playerArrayListString += playerToString(players.get(index));
    	}
    	return playerArrayListString;
    }
    
	/**
	 * writes ArrayList of Players to file
	 * @param players ArrayList to be written
	 * @param numberOfPlayers value to write at start of file
	 * @throws IOException
	 */
	public void sendPlayerDataToFile(int numberOfPlayers) throws IOException{
		FileWriter writer = new FileWriter("data.txt");
        BufferedWriter playerWriter = new BufferedWriter(writer);
        Integer numPlayers = numberOfPlayers;
        for(int count = 0; count < numberOfPlayers; count++) {
        	playerWriter.write(numPlayers.toString());
        	playerWriter.write(playerToString(players.get(count)));
        }
        playerWriter.close();
	}
     
    /**
     * prints player data on separate lines
     * @param player player
     */
    public void printData(Player player) {
        System.out.print(playerToString(player));
    }
     
    /**
     * returns win percentage
     * @param numberOfWins
     * @param numberOfGames
     * @return
     */
    public float winPercentage(int numberOfWins, int numberOfGames) {
    	return (numberOfWins / numberOfGames);
    }
    
    /**
     * returns the newly computed score for player whose score was
     * inputted first based on scores of player 1 and player 2
     * @param oldScore1 current score of player 1
     * @param oldScore2 current score of player 2
     * @param winner which player won
     * @return new score of first player input
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
     * bubble sort the ArrayList of Players by score, the rank is determined by position
     * @param players ArrayList to sort
     * @param numberOfPlayers number of players in ArrayList
     */
    public void sortArrayListByScore(int numberOfPlayers) {
    	boolean sorted = false;
    	boolean swapped = false;
    	Player tempPlayer;
    	while(!sorted) {
    		swapped = false;
    		for(int index = 0; index < numberOfPlayers; index++) {
    			if(players.get(index).getPlayerData().getScore() > players.get(index + 1).getPlayerData().getScore()) {
    				tempPlayer = players.get(index);
    				players.set(index, players.get(index + 1));
    				players.set(index + 1, tempPlayer);
    				swapped = true;
    			}
    		}
    		if(swapped = false) {
    			sorted = true;
    		}
    	}
    }
    
}