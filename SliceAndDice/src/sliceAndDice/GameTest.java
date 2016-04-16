package sliceAndDice;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
<<<<<<< HEAD
	public void gameNotNull() {
		// Ensure that a Game instance properly initializes
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		assertNotNull(game);
		
	} 
=======
	public void playNextTurnTest() {
	
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.PlayNextTurn(Move.ATTACK), Winner.NONE);
		
	}
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git
	
	@Test
	public void player1StatusNotNull() {
		// Ensure that a Status instance properly initializes for player 1
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		
<<<<<<< HEAD
		assertNotNull(game.getPlayerOneStatus());
=======
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
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git

<<<<<<< HEAD
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
=======
		Game game = new Game("Playerone", "playertwo");
		assertEquals(game.getLoserID(), -1);
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git
	}
	
	@Test
	public void getTotalTurnsTest() {
<<<<<<< HEAD
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
=======
		Game game = new Game("playerone", "playertwo");
		assertEquals(game.getTotalTurns(), 0);
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git
		
<<<<<<< HEAD
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		game.PlayNextTurn(Move.ATTACK);
		
		assertEquals(game.getTotalTurns(), 6);
=======
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git
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
<<<<<<< HEAD
	public void gameHasWinner() {
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game(player1, player2);	
		boolean isWinner = false;
		Winner result = Winner.NONE;
=======
	public void updateMoveCountTest() {
		Game game = new Game("playerone", "playertwo");
		Player player1 = new Player();
		game.updateMoveCount(player1, Move.ATTACK );
		assertEquals(player1.getPlayerData().totalNumAttacks, 1);
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git
		
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
		
<<<<<<< HEAD
	}*/
=======
		try{
			turn.spAtk4(status1, status2);
		}
		catch(IllegalArgumentException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
>>>>>>> branch 'master' of https://github.com/emarkovcse360/cse360project.git
}
