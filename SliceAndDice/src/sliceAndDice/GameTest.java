package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for the Game class.
 * @author Eisher Saroya
 *
 */
public class GameTest {

	@Test
	public void gameNotNull() {
		// Ensure that a Game instance properly initializes
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		assertNotNull(game);
		
	} 
	
	@Test
	public void player1StatusNotNull() {
		// Ensure that a Status instance properly initializes for player 1
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		
		assertNotNull(game.getPlayerOneStatus());

	}
	
	@Test
	public void player2StatusNotNull() {
		// Ensure that a Status instance properly initializes for player 2
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		
		assertNotNull(game.getPlayerTwoStatus());

	}
	
	@Test
	public void playerOneGoesFirst() {
		// Check that player one gets the first turn.
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		assertEquals(game.isPlayerOneTurn(), true);
	}
	
	@Test
	public void playerTwoGoesSecond() {
		// Check that player two gets the second turn.
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		assertEquals(game.isPlayerOneTurn(), false);
	}
	
	@Test
	public void getTotalTurnsTest() {
		// Check that the turn counter is accurate.
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		
		assertEquals(game.getTotalTurns(), 6);
	}
	
	@Test
	public void getLastRollTest() {
		// Check that Game returns the last roll.
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		game.updateCondition();
		game.PlayNextTurn(Move.ATTACK);
		int[] roll = game.getLastRoll();
		
		assertEquals(roll.length, 4);
	}
	
	@Test
	public void gameHasWinner() {
		// Check that a winner has been determined
		// After a game must have ended.
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		boolean isWinner = false;
		Winner result = Winner.NONE;
		
		int turnCount = 0;
		while(!isWinner && turnCount < 40) {
			result = game.updateCondition();
			if(result != Winner.NONE) {
				isWinner = true;
			}
			else {
				result = game.PlayNextTurn(Move.ATTACK);
				if(result != Winner.NONE) {
					isWinner = true;
				}
			}
			turnCount++;
		}
		
		assertTrue(isWinner);
	}
		
	@Test
	public void evaluateLegalityInGame() {
		// Check that the Turn.nextMoveLegality method
		// can be used properly by Game for a legal move.
		Player player1 = new Player("test1", 1);
		Player player2 = new Player("test2", 2);
		Game game = new Game(player1, player2);	
		
		IllegalMove nextMove = game.nextMoveLegality(Move.ATTACK);
		assertEquals(nextMove, IllegalMove.NONE);
	}
	
	//Class Turn Test
	@Test
	public void turnNotNull() {
		// Check that the Turn constructor initializes.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		assertNotNull(turn);
		
	}
	
	@Test
	public void playTurnPlayerOneTest() {
		// Check that player 1 can play a turn.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		assertEquals(turn.playTurnPlayerOne(Move.ATTACK), Winner.NONE);
		
	}
	
	@Test
	public void playTurnPlayerTwoTest() {
		// Check that player 2 can play a turn.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		assertEquals(turn.playTurnPlayerTwo(Move.ATTACK), Winner.NONE);
	}
	
	@Test
	public void playerOneTakesDamage() {
		// Check that player 1 takes damage from an attack.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.playTurnPlayerTwo(Move.ATTACK);
		
		boolean damageTaken = false;
		if(status1.getHitPts() < Status.getMaxHP()) {
			damageTaken = true;
		}
		assertTrue(damageTaken);
		
	}
	
	@Test
	public void playerOneTakesDamageDouble() {
		// Check that player 1 takes damage from a double attack.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.playTurnPlayerTwo(Move.DOUBLEATK);
		
		boolean damageTaken = false;
		if(status1.getHitPts() < Status.getMaxHP()) {
			damageTaken = true;
		}
		assertTrue(damageTaken);
		
	}
	
	@Test
	public void playerTwoTakesDamage() {
		// Check that player 2 takes damage from an attack.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.playTurnPlayerOne(Move.ATTACK);
		
		boolean damageTaken = false;
		if(status2.getHitPts() < Status.getMaxHP()) {
			damageTaken = true;
		}
		assertTrue(damageTaken);
		
	}
	
	@Test
	public void playerTwoTakesDamageDouble() {
		// Check that player 2 takes damage from a double attack.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.playTurnPlayerTwo(Move.DOUBLEATK);
		
		boolean damageTaken = false;
		if(status1.getHitPts() < Status.getMaxHP()) {
			damageTaken = true;
		}
		assertTrue(damageTaken);
		
	}
	
	@Test
	public void playerOneHealsFromFood() {
		// Check that player one heals the previous damage
		// after eating food.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.playTurnPlayerTwo(Move.ATTACK);
		turn.playTurnPlayerOne(Move.FOOD);
		
		boolean damageHealed = false;
		if(status1.getHitPts() == Status.getMaxHP()) {
			damageHealed = true;
		}
		assertTrue(damageHealed);
		
	}
	
	@Test
	public void playerTwoHealsFromFood() {
		// Check that player two heals the previous damage
		// after eating food.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.playTurnPlayerOne(Move.ATTACK);
		turn.playTurnPlayerTwo(Move.FOOD);
		
		boolean damageHealed = false;
		if(status2.getHitPts() == Status.getMaxHP()) {
			damageHealed = true;
		}
		assertTrue(damageHealed);
		
	}
	
	@Test
	public void freezeWhenFrozen() {
		// Check that player is frozen when freeze condition is met.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		Winner winner = Winner.NONE;
		boolean frozen = false;
		boolean freezeApplied = false;
		int[] lastRoll;
		while(!freezeApplied && winner == Winner.NONE) {
			turn.playTurnPlayerOne(Move.FREEZE);
			lastRoll = turn.getLastRoll();
			if(lastRoll[0] + lastRoll[1] > 6) {
				freezeApplied = true;
				if(status2.getCondition() == Condition.FROZEN) {
					frozen = true;
				}
			}
		}
		
		assertTrue(frozen && freezeApplied);
		
	}

	@Test
	public void poisonWhenPoisoned() {
		// Check that player is poisoned when the poison condition is met.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		Winner winner = Winner.NONE;
		boolean poisoned = false;
		boolean poisonApplied = false;
		int[] lastRoll;
		while(!poisonApplied && winner == Winner.NONE) {
			turn.playTurnPlayerOne(Move.POISON);
			lastRoll = turn.getLastRoll();
			if(lastRoll[0] + lastRoll[1] > 6) {
				poisonApplied = true;
				if(status2.getCondition() == Condition.POISON1) {
					poisoned = true;
				}
			}
		}
		
		assertTrue(poisoned && poisonApplied);
		
	}
	
	@Test
	public void chargeIncreasesMana() {
		// Check that charge attack increases mana.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		status1.setMana(0);
		turn.playTurnPlayerOne(Move.CHARGE);
		assertTrue(status1.getMana() > 0);
	}
	
	@Test
	public void auraWhenAura() {
		// Check that player has Aura after it is used.
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		Winner winner = Winner.NONE;
		boolean aura = false;
		boolean auraApplied = false;
		int[] lastRoll;
		int counter = 0;
		while(!auraApplied && counter < 40) {
			status1.setMana(Status.getMaxMana());
			turn.playTurnPlayerOne(Move.AURA);
			lastRoll = turn.getLastRoll();
			if(lastRoll[0] > 3) {
				auraApplied = true;
				if(status1.getCondition() == Condition.AURA1) {
					aura = true;
				}
			}
			counter++;
		}
		
		assertTrue(aura && auraApplied);
		
	}
	
	@Test
	public void allowsLegalMove() {
		// Check that Turn allows a move that is legal.
		Status status1 = new Status();
		IllegalMove move = Turn.moveIsLegal(status1, Move.ATTACK);
		
		assertEquals(move, IllegalMove.NONE);
	}
	
	@Test
	public void disallowEatOverLimit() {
		// Check that Turn prevents the user from eating beyond their food count.
		Status status1 = new Status();
		for(int count = 1; count <= Status.getMaxFood(); count++) {
			status1.reduceFoodCount();
		}
		IllegalMove move = Turn.moveIsLegal(status1, Move.FOOD);
		
		assertEquals(move, IllegalMove.NOFOOD);
	}
	
}
