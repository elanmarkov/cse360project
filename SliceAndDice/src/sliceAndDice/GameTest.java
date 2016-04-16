package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

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
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		assertEquals(game.isPlayerOneTurn(), true);
	}
	
	@Test
	public void playerTwoGoesSecond() {
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		game.PlayNextTurn(Move.ATTACK);
		assertEquals(game.isPlayerOneTurn(), false);
	}
	
	@Test
	public void getTotalTurnsTest() {
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		
		assertEquals(game.getTotalTurns(), 6);
	}
	
	@Test
	public void getLastRollTest() {
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		
		game.PlayNextTurn(Move.ATTACK);
		int[] roll = game.getLastRoll();
		
		assertEquals(roll.length, 4);
	}
	
	@Test
	public void gameHasWinner() {
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		boolean isWinner = false;
		Winner result = Winner.NONE;
		
		int turnCount = 0;
		while(!isWinner && turnCount < 40) {
			result = game.PlayNextTurn(Move.ATTACK);
			if(result != Winner.NONE) {
				isWinner = true;
			}
			turnCount++;
		}
		
		assertTrue(isWinner);
	}
	
	@Test
	public void gameWinnerHasID() {
		Player player1 = new Player("test1", 1);
		Player player2 = new Player("test2", 2);
		Game game = new Game(player1, player2);	
		boolean isWinner = false;
		Winner result = Winner.NONE;
		
		int turnCount = 0;
		while(!isWinner && turnCount < 40) {
			result = game.PlayNextTurn(Move.ATTACK);
			if(result != Winner.NONE) {
				isWinner = true;
			}
			turnCount++;
		}
		
		boolean winnerHasID = (game.getWinnerID() != -1);
		assertTrue(winnerHasID);
	}
	
	@Test
	public void gameLoserHasID() {
		Player player1 = new Player("test1", 1);
		Player player2 = new Player("test2", 2);
		Game game = new Game(player1, player2);	
		boolean isWinner = false;
		Winner result = Winner.NONE;
		
		int turnCount = 0;
		while(!isWinner && turnCount < 40) {
			result = game.PlayNextTurn(Move.ATTACK);
			if(result != Winner.NONE) {
				isWinner = true;
			}
			turnCount++;
		}
		
		boolean loserHasID = (game.getWinnerID() != -1);
		assertTrue(loserHasID);
	}
	
	//Class Turn Test
	/*
	@Test
	public void playTurnPlayerOneTest() {
		
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		assertEquals(turn.playTurnPlayerOne(Move.ATTACK), Winner.NONE);
		
	}
	
	@Test
	public void playTurnPlayerTwoTest() {
		
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		assertEquals(turn.playTurnPlayerTwo(Move.ATTACK), Winner.NONE);
	}
	
	@Test
	public void playNextTurnTest2() {
		
		Game game = new Game("playerOne", "playerTwo");
		game.nextTurn.playNextTurn(Move.ATTACK, game.getPlayerOneStatus(), game.getPlayerTwoStatus());
		assertTrue(turn.statusP1.getHitPts() > turn.statusP2.getHitPts());
	}
	
	@Test
	public void getLastRollTest2() {
		
	}

	@Test
	public void moveIsLegalTest() {
		
	}
	@Test
	public void attackTest() {
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.attack(turn.statusP1, turn.statusP2);
		assertTrue(turn.statusP2.getHitPts() < turn.statusP1.getHitPts());
		
	}
	
	@Test
	public void foodTest() {
		
	}
	
	@Test
	public void freezeTest() {
		
	}
	
	@Test
	public void doubleAttackTest() {
		
	}
	
	@Test
	public void spAtk3Test() {
		
	}
	
	@Test
	public void spAtk4Test() {
		
	}*/
}
