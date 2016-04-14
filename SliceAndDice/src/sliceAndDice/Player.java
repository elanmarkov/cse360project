package sliceAndDice;

import sliceAndDice.Condition;
import sliceAndDice.Move;
import sliceAndDice.Status;

enum Move {ATTACK, FOOD, FREEZE, DOUBLEATK, SPATK3, SPATK4};
enum Condition {NONE}

public class Player {
	String username;
	int playerID;
	Data playerData;
	Player() {
		playerID = -1;
		username = "";
		playerData = null;
	}
	Player(String username, int playerID) {
		this.username = username;
		this.playerID = playerID;
		playerData = new Data();
	}
	void setID(int playerID) {
		this.playerID = playerID;
	}
	int getID() {
		return playerID;
	}
	String getUsername() {
		return username;
	}
	void setUsername(String username) {
		this.username = username;
	}
	void setPlayerData(Data PlayerData) {
		this.playerData =  playerData;
	}
	Data getPlayerData() {
		return playerData;
	}
}
class Status {
	private int hitPt;
	private int mana;
	private int food;
	private int[] lastRoll;
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
	int[] getLastRoll() {
		return lastRoll;
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
	void setLastRoll(int[] lastRoll) {
		this.lastRoll = lastRoll;
	}
	void setCondition(Condition newCondition) {
		playerCondition = newCondition;
	}
	static int getMaxHP() {
		return maxHP;
	}
}