package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the DiceRoll class.
 * Methods are static; no instances of
 * DiceRoll will be created.
 * @author Elan Markov
 *
 */
public class DiceRollTest {
	@Test
	public void arrayIsEmpty() {
		// Check that the dice roll array is empty for an empty input.
		int[] roll = DiceRoll.roll(0);
		assertEquals(0, roll.length);
	}
	
	@Test
	public void arrayIsEmptyCustom() {
		// Check that the dice roll array is empty for an empty input, custom array length.
		int diceSides = 8;
		int[] roll = DiceRoll.roll(0, diceSides);
		assertEquals(0, roll.length);
	}
	
	@Test
	public void properArrayLength() {
		// Check that the dice roll returns the proper number of rolls.
		int numRoll = 5;
		int[] roll = DiceRoll.roll(numRoll);
		assertEquals(numRoll, roll.length);
	}
	
	@Test
	public void properArrayLengthCustom() {
		// Check that the custom dice roll returns the proper number of rolls.
		int numRoll = 5;
		int diceSides = 8;
		int[] roll = DiceRoll.roll(numRoll, diceSides);
		assertEquals(numRoll, roll.length);
	}
	
	@Test
	public void properDiceRange() {
		// Check that the dice roll returns values within the proper range.
		int numRoll = 500;
		boolean outOfRange = false;
		int[] roll = DiceRoll.roll(numRoll);
		int count = 0;
		while(!outOfRange && count < numRoll) {
			if(roll[count] < 1 || roll[count] > 6) {
				outOfRange = true;
			}
			count++;
		}
		assertFalse(outOfRange);
	}
	
	@Test
	public void properDiceRangeCustom() {
		// Check that the dice roll returns values within the proper range for custom die size.
		int numRoll = 500;
		int diceSides = 12;
		boolean outOfRange = false;
		int[] roll = DiceRoll.roll(numRoll, diceSides);
		int count = 0;
		while(!outOfRange && count < numRoll) {
			if(roll[count] < 1 || roll[count] > diceSides) {
				outOfRange = true;
			}
			count++;
		}
		assertFalse(outOfRange);
=======

public class DiceRollTest {

	@Test
	public void rollTest() {
		DiceRoll dice = new DiceRoll();
		assertEquals(dice.roll(5).length, 5);
	}
	
	@Test //assert statement confirms that the roll[i] are divisible by the number of sides 
	public void rollTest2() {
		DiceRoll dice = new DiceRoll();
		assertTrue( (dice.roll(5, 8)[0] - 1) % 8 == 0 );
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git
	}

}
