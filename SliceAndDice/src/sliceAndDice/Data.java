package sliceAndDice;

import sliceAndDice.Data;

public class Data {
	double score;
	int rank;
	int totalGameCount;
	int totalWinCount;
	//int totalNumTurns;
	int totalNumAttacks;
	int totalNumMeals;
	// int totalNumSPAttacks;
	int totalHealthLost;
	int totalManaUsed;
	int totalFoodUsed;
	// float totalGameTime;
	// float totalTurnTime;

	Data() {
	score = 1000;
	rank = 0;
	totalGameCount = 0;
	totalWinCount = 0;
	//totalNumTurns = 0;
	totalNumAttacks = 0;
	totalNumMeals = 0;
	// totalNumSPAttacks = 0;
	totalHealthLost = 0;
	totalManaUsed = 0;
	totalFoodUsed = 0;
	// totalGameTime = 0;
	// totalTurnTime = 0;
	}
	
	public String toString() { // put this as a method in Data
		String dataString = "";
		dataString += score + "\n";
		dataString += rank + "\n";
		dataString += totalGameCount + "\n";
		dataString += totalWinCount + "\n";
		dataString += totalNumAttacks + "\n";
		dataString += totalNumMeals + "\n";
		dataString += totalHealthLost + "\n";
		dataString += totalManaUsed + "\n";
		dataString += totalFoodUsed;
		
		return dataString;
	}
	
	void loadData(Data data) {
	score = data.getScore();
	rank = data.getRank();
	totalGameCount = data.getGame();
	totalWinCount = data.getWin();
	//totalNumTurns = data.getTurn();
	totalNumAttacks = data.getAttack();
	totalNumMeals = data.getMeal();
	// totalNumSPAttacks = data.getSPAttack();
	totalHealthLost = data.getHPLost();
	totalManaUsed = data.getManaUsed();
	totalFoodUsed = data.getFoodUsed();
	// totalGameTime = data.getGameTime();
	// totalTurnTime = data.getTurnTime();
	}
	/* set methods */
	void setScore(double score) {
		this.score = score;
	}
	void setRank(int rank) {
		this.rank = rank;
	}
	void setGame(int totalGameCount) {
		this.totalGameCount = totalGameCount;
	}
	void setWin(int totalWinCount) {
		this.totalWinCount = totalWinCount;
	}
	
	//void setTurn(int totalNumTurns) {
	//	this.totalNumTurns = totalNumTurns;
	//}
	
	void setAttack(int totalNumAttacks) {
		this.totalNumAttacks = totalNumAttacks;
	}
	
	void setMeal(int totalNumMeals) {
		this.totalNumMeals = totalNumMeals;
	}
	
	void setHPLost(int totalHealthLost) {
		this.totalHealthLost = totalHealthLost;
	}
	
	void setManaUsed(int totalManaUsed) {
		this.totalManaUsed = totalManaUsed;
	}
	
	void setFoodUsed(int totalFoodUsed) {
		this.totalFoodUsed = totalFoodUsed;
	}
	/* get methods */
	double getScore() {
		return score;
	}
	int getRank() {
		return rank;
	}
	int getGame() {
		return totalGameCount;
	}
	int getWin() {
		return totalWinCount;
	}
	
	//int getTurn() {
	//	return totalNumTurns;
	//}
	
	int getAttack() {
		return totalNumAttacks;
	}
	
	int getMeal() {
		return totalNumMeals;
	}
	
	int getHPLost() {
		return totalHealthLost;
	}
	
	int getManaUsed() {
		return totalManaUsed;
	}
	
	int getFoodUsed() {
		return totalFoodUsed;
	}
	
	// called at end of each game
	void incrGameCount() {
		totalGameCount++;
	}
	// called at end of each game
	public void incrWinCount() {
		totalWinCount++;
	}
	// called at end of each turn
	//public void incrNumTurns() {
	//	totalNumTurns++;
	//}
	public void incrNumAttacks() {
		totalNumAttacks++;
	}
	public void incrNumMeals() {
		totalNumMeals++;
	}
	// called at end of each game
	public void updateHealthLost(int finalHP) {
		totalHealthLost = 100 - finalHP; // 100 is maxHP
	}
	// called at end of each game
	public void updateManaUsed(int finalMana) {
		totalManaUsed = 30 - finalMana; // 30 is maxMana
	}
	// called at end of each game
	public void updateFoodUsed(int finalFood) {
		totalFoodUsed = 5 - finalFood; // 5 is maxFood
	}
}
/* 
public void incrNumSPAttacks() {
	totalNumSPAttacks++;
}
public void updateTotalGameTime() {
}
public void update totalTurnTime() {
}
*/