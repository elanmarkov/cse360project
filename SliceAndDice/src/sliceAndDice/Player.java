package sliceAndDice;
/**
 * Player class to store information about each player, 
 * including username, ID, and game statistics.
 * Status class to store player status within a game.
 * 
 * @author Team 1, CSE 360 Spring 2016
 * Group members:
 * Jacob Loden
 * Elan Markov
 * Daniel Saman
 * Eisher Saroya
 * Andrew Stanton
 *
 */
import java.util.Scanner;

import sliceAndDice.Condition;
import sliceAndDice.Move;
import sliceAndDice.Status;

enum Move {ATTACK, FOOD, FREEZE, DOUBLEATK, POISON, AURA, CHARGE};
enum Condition {NONE, FROZEN, POISON1, POISON2, POISON3, POISON4, POISON5, AURA1, AURA2}

/**
 * Player class. Stores identifying information (username and 
 * unique ID) along with statistical data (Data object) for each
 * player.
 * @author Elan Markov, PIN 525, CSE 360, Spring 2016
 *
 */
public class Player {
	private String username;
	private int playerID;
	private Data playerData;
	/**
	 * Default constructor for Player class.
	 */
	Player() {
		username = "";
		playerID = -1;
		playerData = new Data();
	}
	/**
	 * Constructor for Player class from username and ID.
	 * @param username player's(unique) username
	 * @param playerID unique ID for player
	 */
	Player(String username, int playerID) {
		this.username = username;
		this.playerID = playerID;
		playerData = new Data();
	}
	/**
	 * Constructor for Player class from Scanner.
	 * @param playerReader Scanner object from which ID and username is read.
	 */
	Player(Scanner playerReader) { 
		playerID = playerReader.nextInt();
		username = playerReader.next();
		playerData = new Data(playerReader);
	}
	/**
	 * Setter method for ID.
	 * @param playerID ID value; corresponds to player location in storage, integer.
	 */
	void setID(int playerID) {
		this.playerID = playerID;
	}
	/**
	 * Setter method for player's username.
	 * @param username Player's username, String.
	 */
	void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Setter method for player Data statistics.
	 * @param playerData Data to be assigned to this player.
	 */
	void setPlayerData(Data playerData) {
		this.playerData =  playerData;
	}
	/**
	 * Getter method for ID.
	 * @return User's unique ID, integer.
	 */
	int getID() {
		return playerID;
	}
		/**
	 * Getter method for player's username.
	 * @return Player's username, String.
	 */
	String getUsername() {
		return username;
	}
	/**
	 * Getter method for user's data statistics.
	 * @return Data object containing statistics.
	 */
	Data getPlayerData() {
		return playerData;
	}
	/**
	 * toString method for Player class.
	 * Prints username and invokes the toString of Data
	 * to print the statistics data.
	 * Does not print ID - ID is unique to the current game
	 * and not relevant to the user.
	 * @return String representation of the current player. 
	 */
	public String toString() { 
		String playerString = "";
		playerString += username + "\n";
		return playerString + playerData;
	}
	/**
	 * Method for getting next move. Used for inheritance by Robot class,
	 * when called within player it will throw an exception.
	 * @param ownStatus Turn player's status
	 * @param oppStatus Opponent's status
	 * @return Exception
	 */
	Move getNextMove(Status ownStatus, Status oppStatus) {
		throw new IllegalArgumentException("Error: Player should select move from GUI");
	}
}
/**
 * Status class. Stores the player's current status in the middle
 * of a game. Contains static methods to pass maximum HP/MP/food.
 * Stores player's hitpoints (HP), mana points (MP), food, and 
 * condition (from special attacks).
 * 
 * @author Elan Markov, PIN 525, CSE 360, Spring 2016
 */
class Status {
	private int hitPt;
	private int mana;
	private int food;
	private final static int maxHP = 100;
	private final static int maxMana = 30;
	private final static int maxFood = 5;
	private int atk;
	private int def;
	private Condition playerCondition;
	/**
	 * Default constructor for status. 
	 * Gives full HP, mana, and food.
	 * No player condition(ailment)
	 */
	Status() {
		hitPt = maxHP;
		mana = maxMana;
		food = maxFood;
		atk = 0;
		def = 0;
		playerCondition = Condition.NONE;
	}
	/**
	 * Creates a Status as a copy of an existing status.
	 * @param oldStatus Status to be copied.
	 */
	Status(Status oldStatus) {
		hitPt = oldStatus.getHitPts();
		mana = oldStatus.getMana();
		food = oldStatus.getFoodCount();
		playerCondition = oldStatus.getCondition();
	}
	/**
	 * Getter method for hitpoints.
	 * @return Current hitpoints.
	 */
	int getHitPts() {
		return hitPt;
	}
	/**
	 * Getter method for mana.
	 * @return Current mana.
	 */
	int getMana() {
		return mana;
	}
	/**
	 * Getter method for food.
	 * @return Current food count.
	 */
	int getFoodCount() {
		return food;
	}
	/**
	 * Getter method for attack booster.
	 * @return Current attack booster.
	 */
	int getAtk() {
		return atk;
	}
	/**
	 * Getter method for defense booster.
	 * @return Current defense booster.
	 */
	int getDef() {
		return def;
	}
	/**
	 * Getter method for condition.
	 * @return Current condition.
	 */
	Condition getCondition() {
		return playerCondition;
	}
	/**
	 * Setter method for hitpoints.
	 * @param newHP New hitpoints for the player.
	 */
	void setHitPts(int newHP) {
		hitPt = newHP;
	}
	/**
	 * Reduces HP by given value, or sets to 0 if damage
	 * is higher than remaining hp.
	 * @param newHP New hitpoints for the player.
	 */
	void reduceHP(int damage) {
		if(damage < hitPt) {
			hitPt -= damage;
		}
		else {
			hitPt = 0;
		}
	}
	/**
	 * Setter method for mana.
	 * @param newMana New mana for the player.
	 */
	void setMana(int newMana) {
		mana = newMana;
	}
	/**
	 * Method for reducing mana by a given amount.
	 * @param reduceCount Reduce mana by this value.
	 */
	void reduceMana(int reduceCount) {
		mana -= reduceCount;
	}
	/**
	 * Method for increasing mana by a given amount.
	 * @param increaseCount Increase mana by this value.
	 */
	void increaseMana(int increaseCount) {
		mana += increaseCount;
	}
	/**
	 * Reduces the player's food count (ate food).
	 */
	void reduceFoodCount() {
		food--;
	}
	/**
	 * Setter method for attack booster.
	 * @param atk New attack booster.
	 */
	void setAtk(int atk) {
		this.atk = atk;
	}
	/**
	 * Setter method for defense booster.
	 * @param def New defense booster.
	 */
	void setDef(int def) {
		this.def = def;
	}
	/**
	 * Setter method for player condition.
	 * @param newCondition Condition to be assigned for the player.
	 */
	void setCondition(Condition newCondition) {
		playerCondition = newCondition;
	}
	/**
	 * Static method to track maximum player HP.
	 * @return current maximum HP level
	 */
	public static int getMaxHP() {
		return maxHP;
	}
	/**
	 * Static method to track maximum player mana.
	 * @return current maximum MP level
	 */
	public static int getMaxMana() {
		return maxMana;
	}
	/**
	 * Static method to track maximum player food count.
	 * @return maximum food count
	 */
	public static int getMaxFood(){
		return maxFood;
	}
}