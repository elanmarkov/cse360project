package sliceAndDice;

import sliceAndDice.Condition;
import sliceAndDice.Move;
import sliceAndDice.Status;

enum Move {ATTACK, FOOD, FREEZE, DOUBLEATK, SPATK3, SPATK4};
enum Condition {NONE}

public class Player {
	String username;
	Status playerStatus;
	Player() {
		playerStatus = null;
	}
	void resetStatus() {
		// Create a new status at the start of a game
		playerStatus = new Status();
	}
	void removeStatus() {
		// remove status at the end of a game
		playerStatus = null;
	}
	Status getStatus() {
		return playerStatus;
	}
	void setStatus(Status newStatus) {
		playerStatus = newStatus;
	}
	Move getNextMove() {
		// Prompt user for their next attack
		return Move.ATTACK;
	}
	String getUsername() {
		return username;
	}
	void setUsername(String username) {
		this.username = username;
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
	static int getMaxHP() {
		return maxHP;
	}
}