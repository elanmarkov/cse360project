package sliceAndDice;

import sliceAndDice.Data;
import java.util.Scanner;



/**
 * Data class stores data for Players.
 * @author Andrew Stanton, PIN: 817, CSE 360, Spring 2016
 * @version 4/25/16
 */
public class Data {
	double score; // updated at end of game, only if there is winner
	int numAborts; // updated at end if aborted
	int totalGameCount; // updated at end of game, aborted or not
	int totalWinCount; // updated where winner is evaluated, updated only when winner
	//int totalNumActions; // NOT STORED - this is the sum of totalNumAttacks and totalNumMeals
	//int totalNumAttacks; // NOT STORED - this is the sum of totalNumBaseAttacks and totalNumSPAttacks
	int totalNumBaseAttacks; // updated every base attack
	//int totalNumSPAttacks; // NOT STORED - this is the sum of the numbers of each of the five special attacks
	int totalNumFreezes; // updated every freeze
	int totalNumDoubles;
	int totalNumPoisons;
	int totalNumAuras;
	int totalNumCharges;
	int totalNumMeals;// updated every meal
	int totalHealthLost; // updated at end of game
	int totalManaUsed; // end of game
	//int totalFoodUsed; // NOT STORED - this is the same as totalNumMeals
	int totalHPHealed; // updated every heal
	int totalMPCharged; // updated every charge

	/**
	 * Constructor for Data, setting all of the data but score to 0 and score to 0.
	 */
	Data() {
		score = 1000;
		numAborts = 0;
		totalGameCount = 0;
		totalWinCount = 0;
		//totalNumActions = 0;
		//totalNumAttacks = 0;
		totalNumBaseAttacks = 0;
		//totalNumSPAttacks = 0;
		totalNumFreezes = 0;
		totalNumDoubles = 0;
		totalNumPoisons = 0;
		totalNumAuras = 0;
		totalNumCharges = 0;
		totalNumMeals = 0;
		totalHealthLost = 0;
		totalManaUsed = 0;
		//totalFoodUsed = 0;
		totalHPHealed = 0;
		totalMPCharged = 0;
	}
	/**
	 * Constructor for Data that uses a Scanner to read in data from data.txt.
	 * @param dataReader Scanner used to read in data from data.txt
	 */
	Data(Scanner dataReader) {
		score = dataReader.nextDouble();
		numAborts = dataReader.nextInt();
		totalGameCount = dataReader.nextInt();
		totalWinCount = dataReader.nextInt();
		//totalNumActions = dataReader.nextInt();
		//totalNumAttacks = dataReader.nextInt();
		totalNumBaseAttacks = dataReader.nextInt();
		//totalNumSPAttacks = dataReader.nextInt();
		totalNumFreezes = dataReader.nextInt();
		totalNumDoubles = dataReader.nextInt();
		totalNumPoisons = dataReader.nextInt();
		totalNumAuras = dataReader.nextInt();
		totalNumCharges = dataReader.nextInt();
		totalNumMeals = dataReader.nextInt();
		totalHealthLost = dataReader.nextInt();
		totalManaUsed = dataReader.nextInt();
		//totalFoodUsed = dataReader.nextInt();
		totalHPHealed = dataReader.nextInt();
		totalMPCharged = dataReader.nextInt();
	}
	/**
	 * Returns Data in String form with information on separate lines.
	 */
	public String toString() { // put this as a method in Data
		String dataString = "";
		dataString += score + "\n";
		dataString +=  numAborts + "\n";
		dataString += totalGameCount + "\n";
		dataString += totalWinCount + "\n";
		//dataString += totalNumActions + "\n";
		//dataString += totalNumAttacks + "\n";
		dataString += totalNumBaseAttacks + "\n";
		//dataString += totalNumSPAttacks + "\n";
		dataString += totalNumFreezes + "\n";
		dataString += totalNumDoubles + "\n";
		dataString += totalNumPoisons + "\n";
		dataString += totalNumAuras + "\n";
		dataString += totalNumCharges + "\n";
		dataString += totalNumMeals + "\n";
		dataString += totalHealthLost + "\n";
		dataString += totalManaUsed + "\n";
		//dataString += totalFoodUsed + "\n";
		dataString += totalHPHealed + "\n";
		dataString += totalMPCharged + "\n";
		
		return dataString;
	}
	/**
	 * resets data in Data object to initial setting
	 */
	public void resetData() {
		score = 1000;
		numAborts = 0;
		totalGameCount = 0;
		totalWinCount = 0;
		//totalNumActions = 0;
		//totalNumAttacks = 0;
		totalNumBaseAttacks = 0;
		//totalNumSPAttacks = 0;
		totalNumFreezes = 0;
		totalNumDoubles = 0;
		totalNumPoisons = 0;
		totalNumAuras = 0;
		totalNumCharges = 0;
		totalNumMeals = 0;
		totalHealthLost = 0;
		totalManaUsed = 0;
		//totalFoodUsed = 0;
		totalHPHealed = 0;
		totalMPCharged = 0;
	}
	/**
	 * Sets the score to the given score.
	 * @param score the new score
	 */
	void setScore(double score) {
		this.score = score;
	}
	/**
	 * Sets the number of aborts to given number of aborts.
	 * @param abort new number of aborts
	 */
	void setAbort(int numAborts) {
		this.numAborts = numAborts;
	}
	/**
	 * Sets the number of games to the given number.
	 * @param totalGameCount new number of games
	 */
	void setGame(int totalGameCount) {
		this.totalGameCount = totalGameCount;
	}
	 /**
	  * Sets the number of wins to the given number.
	  * @param totalWinCount new number of wins
	  */
	void setWin(int totalWinCount) {
		this.totalWinCount = totalWinCount;
	}
	/* NOT STORED
	 * Sets the number of actions to the given number.
	 * @param totalNumActions new number of actions
	void setAction(int totalNumActions) {
		this.totalNumActions = totalNumActions;
	}
	 */
	/**
	 * Sets the number of attacks to the given number.
	 * @param totalNumAttacks new number of attacks
	 */
	/* NOT STORED
	 * Sets the number of attacks to the given number.
	 * @param totalNumAttacks new number of attacks
	void setAttack(int totalNumAttacks) {
		this.totalNumAttacks = totalNumAttacks;
	}
	 */
	/**
	 * Sets the number of base attacks to the given number.
	 * @param totalNumBaseAttacks new number of base attacks
	 */
	void setBaseAttack(int totalNumBaseAttacks) {
		this.totalNumBaseAttacks = totalNumBaseAttacks;
	}
	/* NOT STORED
	 * Sets the number of special attacks to the given number.
	 * @param totalNumSPAttacks new number of special attacks
	void setSPAttack(int totalNumSPAttacks) {
		this.totalNumSPAttacks = totalNumSPAttacks;
	}
	 */
	/**
	 * Sets the total number of freezes to the given number.
	 * @param totalNumFreezes new number of freezes
	 */
	void setFreeze(int totalNumFreezes) {
		this.totalNumFreezes = totalNumFreezes;
	}
	/**
	 * Sets the total number of double attacks to the given number.
	 * @param totalNumDoubles new number of double attacks
	 */
	void setDouble(int totalNumDoubles) {
		this.totalNumDoubles = totalNumDoubles;
	}
	/**
	 * Sets the total number of poisons to the given number.
	 * @param totalNumPoisons new number of poisons
	 */
	void setPoison(int totalNumPoisons) {
		this.totalNumPoisons = totalNumPoisons;
	}
	/**
	 * Sets the total number of auras to the given number.
	 * @param totalNumAuras new number of auras
	 */
	void setAura(int totalNumAuras) {
		this.totalNumAuras = totalNumAuras;
	}
	/**
	 * Sets the total number of charges to the given number.
	 * @param totalNumCharges new number of charges
	 */
	void setCharge(int totalNumCharges) {
		this.totalNumCharges = totalNumCharges;
	}
	/**
	 * Sets the number of meals to the given number.
	 * @param totalNumMeals new number of meals
	 */
	void setMeal(int totalNumMeals) {
		this.totalNumMeals = totalNumMeals;
	}
	/**
	 * Sets the amount of health points lost to the given number.
	 * @param totalHealthLost new health points lost
	 */
	void setHPLost(int totalHealthLost) {
		this.totalHealthLost = totalHealthLost;
	}
	/**
	 * Sets the amount of mana points used to the given number.
	 * @param totalManaUsed new number of mana points used
	 */
	void setManaUsed(int totalManaUsed) {
		this.totalManaUsed = totalManaUsed;
	}
	/**
	 * Sets the amount of food points used to the given number.
	 * @param totalFoodUsed new amount of food points used
	 */
	/* NOT STORED
	void setFoodUsed(int totalFoodUsed) {
		this.totalFoodUsed = totalFoodUsed;
	}
	 */
	/**
	 * Sets the amount of health points restored to the given number.
	 * @param totalHPHealed new number of restored health points
	 */
	void setHPHealed(int totalHPHealed) {
		this.totalHPHealed = totalHPHealed;
	}
	/**
	 * Sets the amount of mana points restored to the given number.
	 * @param totalMPCharged new number of restored mana points
	 */
	void setMPCharged(int totalMPCharged) {
		this.totalMPCharged = totalMPCharged;
	}
	/**
	 * Returns the score of the Data object.
	 * @return score
	 */
	double getScore() {
		return score;
	}
	/**
	 * Returns the number of games aborted of the Data object.
	 * @return number of games aborted
	 */
	int getAbort() {
		return numAborts;
	}
	/**
	 * Returns the number of games of the Data object.
	 * @return number of games
	 */
	int getGame() {
		return totalGameCount;
	}
	/**
	 * Returns the number of wins of the Data object.
	 * @return number of wins
	 */
	int getWin() {
		return totalWinCount;
	}
	int getTotalActions() {
		return totalNumBaseAttacks + totalNumFreezes + totalNumDoubles + totalNumPoisons + totalNumAuras + totalNumCharges + totalNumMeals;
	}
	int getTotalNumAttacks() {
		return totalNumBaseAttacks + totalNumFreezes + totalNumDoubles + totalNumPoisons + totalNumAuras + totalNumCharges;
	}
	int getTotalNumSPAttacks() {
		return totalNumFreezes + totalNumDoubles + totalNumPoisons + totalNumAuras + totalNumCharges;
	}
	int getTotalFoodUsed() {
		return totalNumMeals;
	}
	
	/* NOT STORED
	 * Returns the total number of actions of the Data object.
	 * @return total number of actions
	int getAction() {
		return totalNumActions;
	}
	 */
	/* NOT STORED
	 * Returns the total number of attacks of the Data object.
	 * @return total number of attacks
	int getAttack() {
		return totalNumAttacks;
	}
	 */
	/**
	 * Returns the number of base attacks of the Data object.
	 * @return number of base attacks
	 */
	int getBaseAttack() {
		return totalNumBaseAttacks;
	}
	/* NOT STORED
	 * Returns the number of special attacks of the Data object.
	 * @return number of special attacks
	int getSPAttack() {
		return totalNumSPAttacks;
	}
	 */
	/**
	 * Returns the number of freeze attacks of the Data object.
	 * @return number of freeze attacks
	 */
	public int getFreeze() {
		return totalNumFreezes;
	}
	/**
	 * Returns the number of double attacks of the Data object.
	 * @return number of double attacks
	 */
	public int getDouble() {
		return totalNumDoubles;
	}
	/**
	 * Returns the number of poison attacks of the Data object.
	 * @return number of poison attacks
	 */
	public int getPoison() {
		return totalNumPoisons;
	}
	/**
	 * Returns the number of auras of the Data object.
	 * @return number of auras
	 */
	public int getAura() {
		return totalNumAuras;
	}
	/**
	 * Returns the number of charges of the Data object.
	 * @return number of charges
	 */
	public int getCharge() {
		return totalNumCharges;
	}
	/**
	 * Returns the number of meals of the Data object.
	 * @return number of meals
	 */
	int getMeal() {
		return totalNumMeals;
	}
	/**
	 * Returns the amount of health points lost of the Data object.
	 * @return amount of health points lost
	 */
	int getHPLost() {
		return totalHealthLost;
	}
	/**
	 * Returns the amount of mana points used of the Data object.
	 * @return amount of mana points used
	 */
	int getManaUsed() {
		return totalManaUsed;
	}
	int getHPHealed() {
		return totalHPHealed;
	}
	int getMPCharged() {
		return totalMPCharged;
	}
	/* NOT STORED
	 * Returns the amount of food points used of the Data object.
	 * @return amount of food points used
	int getFoodUsed() {
		return totalFoodUsed;
	}
	 */
	/**
	 * Increments the Data object's number of aborted games. Called when a Player ends a Game before a Player wins.
	 */
	public void incrNumAborts() {
		numAborts++;
	}
	/**
	 * Increments the Data object's number of games. Called at the end of games.
	 */
	void incrGameCount() {
		totalGameCount++;
	}
	/**
	 * Increments the Data object's number of wins. Called at the end of games if the given Player wins.
	 */
	public void incrWinCount() {
		totalWinCount++;
	}
	/**
	 * Increments the Data object's number of base attacks. Called when a Player performs a base attack.
	 */
	public void incrNumBaseAttacks() {
		totalNumBaseAttacks++;
	}
	/**
	 * Increments Data object's number of freezes
	 */
	public void incrNumFreezes() {
		totalNumFreezes++;
	}
	/**
	 * Increments the Data object's number of poisons
	 */
	public void incrNumDoubles() {
		totalNumDoubles++;
	}
	/**
	 * Increments the Data object's number of poisons
	 */
	public void incrNumPoisons() {
		totalNumPoisons++;
	}
	/**
	 * Increments the Data object's number of auras.
	 */
	public void incrNumAuras() {
		totalNumAuras++;
	}
	/**
	 * Increments the Data object's number of charges.
	 */
	public void incrNumCharges() {
		totalNumCharges++;
	}
	/**
	 * Increments the Data object's number of meals. Called when a Player heals.
	 */
	public void incrNumMeals() {
		totalNumMeals++;
	}
	/**
	 * Updates the amount of health points a Player has lost. Called at the end of games.
	 * @param finalHP amount of health points the Player has at the end of the game
	 */
	public void updateTotalHealthLost(int finalHP) {
		totalHealthLost += (100 - finalHP); // 100 is maxHP
	}
	/**
	 * Updates the amount of mana points a Player has used. Called at the end of games.
	 * @param finalMana amount of mana points the Player has at the end of the game
	 */
	public void updateTotalManaUsed(int finalMana) {
		totalManaUsed += (30 - finalMana); // 30 is maxMana
	}
	/* NOT STORED
	 * Updates the amount of food points a Player has used. Called at the end of games.
	 * @param finalFood amount of food points the Player has at the end of the game
	public void updateFoodUsed(int finalFood) {
		totalFoodUsed += (5 - finalFood); // 5 is maxFood
	}
	 */
	/**
	 * Updates the number of health points the Player has healed.
	 * @param hpHealed amount of HP healed by a meal
	 */
	public void updateHPHealedAndFoodUsed(int hpHealed) {
		totalHPHealed += hpHealed;
		totalHealthLost += hpHealed;
	}
	/**
	 * Updates the number of mana points the Player has charged.
	 * @param mpCharged amount of MP healed by a charge
	 */
	public void updateMPChargedAndUsed(int mpCharged) {
		totalMPCharged += mpCharged;
		totalManaUsed += mpCharged;
	}
}