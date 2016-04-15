package sliceAndDice;

import sliceAndDice.Data;

public class Data {
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
void loadData(Data data) {
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
public void incrNumMeals() {
	totalNumMeals++;
}
public void incrNumAttacks() {
	totalNumAttacks++;
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