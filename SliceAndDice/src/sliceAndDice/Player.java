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
import sliceAndDice.Condition;
import sliceAndDice.Move;
import sliceAndDice.Status;

enum Move {ATTACK, FOOD, FREEZE, DOUBLEATK, SPATK3, SPATK4};
enum Condition {NONE}

/**
 * Player class. Stores identifying information (username and 
 * unique ID) along with statistical data (Data object) for each
 * player.
 *
 */
public class Player {
	String username;
	int playerID;
	Data playerData;
	/**
	 * Constructor for player class.
	 * @param username player's(unique) username
	 * @param playerID unique ID for player
	 */
	Player(String username, int playerID) {
		this.username = username;
		this.playerID = playerID;
		playerData = new Data();
	}
	/**
	 * Setter class for ID.
	 * @param playerID ID value; corresponds to player location in storage, integer.
	 */
	void setID(int playerID) {
		this.playerID = playerID;
	}
	/**
	 * Getter class for ID.
	 * @return User's unique ID, integer.
	 */
	int getID() {
		return playerID;
	}
	/**
	 * Setter class for player's username.
	 * @param username Player's username, String.
	 */
	void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Getter class for player's username.
	 * @return Player's username, String.
	 */
	String getUsername() {
		return username;
	}
	void setPlayerData(Data playerData) {
		this.playerData =  playerData;
	}
	/**
	 * Getter class for user's data statistics.
	 * @return Data object containing statistics.
	 */
	Data getPlayerData() {
		return playerData;
	}
}
class Status {
	private int hitPt;
	private int mana;
	private int food;
	private final static int maxHP = 100;
	private final static int maxMana = 30;
	private final static int maxFood = 5;
	private Condition playerCondition;
	Status() {
		hitPt = maxHP;
		mana = maxMana;
		food = maxFood;
		playerCondition = Condition.NONE;
	}
	Status(Status oldStatus) {
		hitPt = oldStatus.getHitPts();
		mana = oldStatus.getMana();
		food = oldStatus.getFoodCount();
		playerCondition = oldStatus.getCondition();
	}
	int getHitPts() {
		return hitPt;
	}
	int getMana() {
		return mana;
	}
	int getFoodCount() {
		return food;
	}
	Condition getCondition() {
		return playerCondition;
	}
	void setHitPts(int newHP) {
		hitPt = newHP;
	}
	void setMana(int newMana) {
		mana = newMana;
	}
	void reduceFoodCount() {
		food--;
	}
	void setCondition(Condition newCondition) {
		playerCondition = newCondition;
	}
	public static int getMaxHP() {
		return maxHP;
	}
	public static int getMaxMana() {
		return maxMana;
	}
	public static int getMaxFood(){
		return maxFood;
	}
}