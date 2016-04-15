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
public class Scoreboard2{
static ArrayList<Player> players;
	
public Scoreboard2() {
players = new ArrayList<Player>();
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
}