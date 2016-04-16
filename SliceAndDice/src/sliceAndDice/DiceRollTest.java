package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

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
	}

}
