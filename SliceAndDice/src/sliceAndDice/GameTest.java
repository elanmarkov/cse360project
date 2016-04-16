package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void playNextTurnTest() {
	
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.PlayNextTurn(Move.ATTACK), Winner.NONE);
		
	}
	
	@Test
	public void nextMoveLegalityTest() {
		
	}
	
	@Test
	public void isPlayerOneTurnTest() {
		
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.isPlayerOneTurn(), true);
	}
	
	@Test
	public void getPlayerOneStatusTest() {
		
		fail("cannot access private variables");
	}
	
	@Test
	public void getPlayerTwoStatusTest() {
		
		fail("cannot access private variables");
	}
	
	@Test
	public void getWinnerIDTest() {
		
		Game game = new Game("Playerone", "playertwo");
		assertEquals(game.getWinnerID(), -1);
		
	}
	
	@Test
	public void getLoserIDTest() {

		Game game = new Game("Playerone", "playertwo");
		assertEquals(game.getLoserID(), -1);
	}
	
	@Test
	public void getTotalTurnsTest() {
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.getTotalTurns(), 0);
		
	}
	
	@Test
	public void getLastRollTest() {
		Game game = new Game("playerone", "playertwo");
		
	}
	
	@Test
	public void updateMoveCountTest() {
		Game game = new Game("playerone", "playertwo");
		Player player1 = new Player();
		game.updateMoveCount(player1, Move.ATTACK );
		assertEquals(player1.getPlayerData().totalNumAttacks, 1);
		
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
		Status status1 = new Status();
		Status status2 = new Status();		
		Turn turn = new Turn(status1, status2);
		turn.playNextTurn(Move.ATTACK, status1, status2);
		assertTrue(status1.getHitPts() > status2.getHitPts());
	}
	
	@Test
	public void getLastRollTest2() {
		
	}

	@Test
	public void moveIsLegalTest() {
		
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		
		assertEquals(turn.moveIsLegal(status1, Move.SPATK4), IllegalMove.NOTIMPLEMENTED);
	}
	@Test
	public void attackTest() {
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.attack(status1, status2);
		assertTrue(status2.getHitPts() < status1.getHitPts());
		
	}
	
	@Test
	public void foodTest() {
		Status status1 = new Status();
		Status status2 = new Status();
		Turn turn = new Turn(status1, status2);
		turn.food(status1);
		assertEquals(status1.getFoodCount(), status1.getMaxFood() - 1);
	}
	
	@Test
	public void freezeTest() {

		Status status1 = new Status();
		Status status2 = new Status();		
		Turn turn = new Turn(status1, status2);
		boolean thrown = false;
		
		try{
			turn.freeze(status1, status2);
		}
		catch(IllegalArgumentException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void doubleAtkTest() {
		
		Status status1 = new Status();
		Status status2 = new Status();		
		Turn turn = new Turn(status1, status2);
		boolean thrown = false;
		
		try{
			turn.doubleAtk(status1, status2);
		}
		catch(IllegalArgumentException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void spAtk3Test() {
		
		Status status1 = new Status();
		Status status2 = new Status();		
		Turn turn = new Turn(status1, status2);
		boolean thrown = false;
		
		try{
			turn.spAtk3(status1, status2);
		}
		catch(IllegalArgumentException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void spAtk4Test() {
		Status status1 = new Status();
		Status status2 = new Status();		
		Turn turn = new Turn(status1, status2);
		boolean thrown = false;
		
		try{
			turn.spAtk4(status1, status2);
		}
		catch(IllegalArgumentException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
}
