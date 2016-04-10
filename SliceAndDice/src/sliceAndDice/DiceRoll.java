package sliceAndDice;

abstract class DiceRoll {
	static int[] roll(int numRoll) {
		// Default dice roll, 6-sided dice
		int[] rolls = new int[numRoll];
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			rolls[rollCount] = (int) (Math.random() * 6 + 1);
		}
		return rolls;
	}
	
	static int[] roll(int numRoll, int numSides) {
		// Specify any x-sided dice
		int[] rolls = new int[numRoll];
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			rolls[rollCount] = (int) (Math.random() * numSides + 1);
		}
		return rolls;
	}
}
