package sliceAndDice;
/**
 * Class to perform random dice rolls for the dice game.
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

/**
 * DiceRoll class.
 * Final class with static methods, returns an array containing
 * a specified number of rolls.
 * Uses six-sided dice by default, can be configured to use any
 * number of sides.
 * 
 * @author Elan Markov
 *
 */
final class DiceRoll {
	/**
	 * Default roll method.
	 * @param numRoll number of rolls to perform
	 * @return array containing the value of all random rolls performed
	 */
	static int[] roll(int numRoll) {
		// Default dice roll, 6-sided dice
		int[] rolls = new int[numRoll];
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			rolls[rollCount] = (int) (Math.random() * 6 + 1);
		}
		return rolls;
	}
	/**
	 * Roll method with a specified dice size.
	 * @param numRoll number of rolls to perform
	 * @param numSides how many sides the dice for this roll will have
	 * @return array containing the value of all random rolls performed
	 */
	static int[] roll(int numRoll, int numSides) {
		// Specify any x-sided dice
		int[] rolls = new int[numRoll];
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			rolls[rollCount] = (int) (Math.random() * numSides + 1);
		}
		return rolls;
	}
}
