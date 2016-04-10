package sliceAndDice;
enum Move {ATTACK, FOOD, FREEZE, DOUBLEATK, SPATK3, SPATK4};

public class Player {
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
	int getHitPts() {
		return playerStatus.getHitPts();
	}
	int getMana() {
		return playerStatus.getMana();
	}
	int getFoodCount() {
		return playerStatus.getFoodCount();
	}
	void setHitPts(int newHP) {
		playerStatus.setHitPts(newHP);
	}
	void setMana(int newMana) {
		playerStatus.setMana(newMana);
	}
	void reduceFoodCount() {
		playerStatus.reduceFoodCount();
	}
	Move getNextMove() {
		// Prompt user for their next attack
		return Move.ATTACK;
	}
}
class Status {
	private int hitPt;
	private int mana;
	private int food;
	Status() {
		hitPt = 100;
		mana = 30;
		food = 5;
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
	void setHitPts(int newHP) {
		hitPt = newHP;
	}
	void setMana(int newMana) {
		mana = newMana;
	}
	void reduceFoodCount() {
		food--;
	}
}