package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void playNextTurnTest() {
	
		Game game = new Game("playerone", "playertwo");
		game.PlayNextTurn(Move.ATTACK);
		
		assertTrue(game.playerOneStatus.get);
		
	}
	
	@Test
	public void nextMoveLegalityTest() {
		
	}
	
	@Test
	public void isPlayerOneTurnTest() {
		
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.isPlayerOneTurn(), game.playerOneTurn);
	}
	
	@Test
	public void getPlayerOneStatusTest() {
		
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.getPlayerOneStatus(), game.playerOneStatus);
	}
	
	@Test
	public void getPlayerTwoStatusTest() {
		
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.getPlayerTwoStatus(), game.playerTwoStatus);
	}
	
	@Test
	public void getWinnerIDTest() {
		
		Game game = new Game("Playerone", "playertwo");
		game.winnerID = 5;
		
		assertEquals(game.getWinnerID(), 5);
		
	}
	
	@Test
	public void getLoserIDTest() {

		Game game = new Game("Playerone", "playertwo");
		game.loserID = 5;
		
		assertEquals(game.getLoserID(), 5);
	}
	
	@Test
	public void getTotalTurnsTest() {
		Game game = new Game("playerone", "playertwo");
		game.totalTurns = 7;
		
		assertEquals(game.getTotalTurns(), 7);
	}
	
	@Test
	public void getLastRollTest() {
		Game game = new Game("playerone", "playertwo");
		
	}
	
	@Test
	public void updateMoveCountTest() {
		Game game = new Game("playerone", "playertwo");
		game.updateMoveCount(game.playerOne, Move.ATTACK );
		assertEquals(game.playerOne.getPlayerData().totalNumAttacks, 1);
		
	}
	
	//Class Turn Test
	
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
		
	}
}
