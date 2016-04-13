package sliceAndDice;

import java.util.ArrayList;
enum Winner {NONE, PLAYER_ONE, PLAYER_TWO};

public class Game {
	Player playerOne;
	Player playerTwo;
	int winnerID;
	int loserID;
	Status playerOneStatus;
	Status playerTwoStatus;
	int totalTurns;

	Game(Player playerOne, Player playerTwo) {
		//Determine who is first and second by dice roll
		//TODO: Add GUI interface where needed
		totalTurns = 0;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		playerOneStatus = new Status();
		playerTwoStatus = new Status();
	}
	void PlayGame () {
		Winner gameWinner = Winner.NONE;
		while(gameWinner == Winner.NONE) {
			Turn nextTurn = new Turn(playerOneStatus, playerTwoStatus);
			gameWinner = nextTurn.playTurn();
			totalTurns++;
		}
		if(gameWinner == Winner.PLAYER_ONE){
			winnerID = playerOne.getID();
			loserID = playerTwo.getID();
			playerOne.getPlayerData().incrWinCount();
		}
		else if(gameWinner == Winner.PLAYER_TWO){
			winnerID = playerTwo.getID();
			loserID = playerOne.getID();
			playerTwo.getPlayerData().incrWinCount();
		}
		else {
			throw new IllegalArgumentException("Game ended without a winner.");
		}
		
		
	}
	boolean validMoveP1(Move moveP1) {
		return false;
	}
	boolean validMoveP2(Move moveP2) {
		return false;
	}
	int getWinner() {
		return winnerID;
	}
	int getLoser() {
		return loserID;
	}
	void updateStats() {
		playerOne.getPlayerData().incrGameCount();
		playerOne.getPlayerData().updateHealthLost(playerOneStatus.getHitPts());
		playerOne.getPlayerData().updateManaUsed(playerOneStatus.getMana());
		playerOne.getPlayerData().updateFoodUsed(playerOneStatus.getFoodCount());
		playerTwo.getPlayerData().incrGameCount();
		playerTwo.getPlayerData().updateHealthLost(playerTwoStatus.getHitPts());
		playerTwo.getPlayerData().updateManaUsed(playerTwoStatus.getMana());
		playerTwo.getPlayerData().updateFoodUsed(playerTwoStatus.getFoodCount());
	}
	void updateMoveCount(Move playerOneMove, Move playerTwoMove) {
		switch (playerOneMove) {
		case ATTACK:
			playerOne.getPlayerData().incrNumAttacks();
			break;
		case FOOD:
			playerOne.getPlayerData().incrNumMeals();
			break;
		case FREEZE:
			break;
		case DOUBLEATK:
			break;
		case SPATK3:
			break;
		case SPATK4:
			break;
		default:
			throw new IllegalArgumentException("Error: Illegal move not caught.");	
		}
		playerOne.getPlayerData().incrNumTurns();
		
		switch (playerTwoMove) {
		case ATTACK:
			playerTwo.getPlayerData().incrNumAttacks();
			break;
		case FOOD:
			playerTwo.getPlayerData().incrNumMeals();
			break;
		case FREEZE:
			break;
		case DOUBLEATK:
			break;
		case SPATK3:
			break;
		case SPATK4:
			break;
		default:
			throw new IllegalArgumentException("Error: Illegal move not caught.");	
		}
		playerTwo.getPlayerData().incrNumTurns();
	}
}

class Turn {
	Status statusP1;
	Status statusP2;
	Move moveP1;
	Move moveP2;

	Turn(Status statusP1, Status statusP2) {
		this.statusP1 = statusP1;
		this.statusP2 = statusP2;
	}
	Winner playTurn() {
		Winner gameWinner = Winner.NONE;
		
		if(Turn.moveIsLegal(statusP1, moveP1) == false) {
			throw new IllegalArgumentException("Error: Illegal move not caught.");
		}
		
		switch(moveP1) {
		case ATTACK:
			attack(statusP1, statusP2);
			break;
		case FOOD:
			food(statusP1);
			break;
		case FREEZE:
			freeze(statusP1, statusP2);
			// incrNumFreeze
			break;
		case DOUBLEATK:
			doubleAtk(statusP1, statusP2);
			// incrNumDoubleAttack
			break;
		case SPATK3:
			spAtk3(statusP1, statusP2);
			// incrNumSPATK3
			break;
		case SPATK4:
			spAtk4(statusP1, statusP2);
			// incrNumSPATK4
			break;
		default:
			throw new IllegalArgumentException("Error: Illegal move not caught.");	
		}
		
		if(statusP2.getHitPts() == 0) {
			// Return if player one has won before P2 gets to play
			gameWinner = Winner.PLAYER_ONE; 
		}
		
		if(gameWinner == Winner.NONE) {
			
			if(Turn.moveIsLegal(statusP2, moveP2) == false) {
				throw new IllegalArgumentException("Error: Illegal move not caught.");
			}
			
			switch(moveP2) {
			case ATTACK:
				attack(statusP2, statusP1);
				break;
			case FOOD:
				food(statusP2);
				break;
			case FREEZE:
				freeze(statusP2, statusP1);
				break;
			case DOUBLEATK:
				doubleAtk(statusP2, statusP1);
				break;
			case SPATK3:
				spAtk3(statusP2, statusP1);
				break;
			case SPATK4:
				spAtk4(statusP2, statusP1);
				break;
			default:
				throw new IllegalArgumentException("Error: Illegal move not caught.");	
			}
			
			if(statusP1.getHitPts() == 0) {
				gameWinner = Winner.PLAYER_TWO;
			}
		}
		return Winner.NONE;
	}
	static boolean moveIsLegal(Status turnPlayer, Move nextMove) {
		boolean legalMove = true;
		if(nextMove == Move.FOOD && turnPlayer.getFoodCount() == 0) {
			legalMove = false;
		}
		else if (nextMove != Move.ATTACK) {
			legalMove = false;
		}
		return legalMove;
	}
	void attack(Status turnPlayer, Status otherPlayer) {
		// Roll 4 dice, do damage equal to combined result.
		int numRoll = 4;
		int[] diceResult = DiceRoll.roll(numRoll);
		int sumDamage = 0;
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			sumDamage += diceResult[rollCount];
		}
		
		int oppHP = otherPlayer.getHitPts();
		if(oppHP <= sumDamage) {
			otherPlayer.setHitPts(0);
		}
		else {
			otherPlayer.setHitPts(oppHP - sumDamage);
		}
	}
	void food(Status turnPlayer) {
		// Add 25 hp, do not overmax hp
		int healValue = 25;
		int currHP = turnPlayer.getHitPts();
		if(currHP + healValue > Status.getMaxHP()) {
			turnPlayer.setHitPts(Status.getMaxHP());
		}
		else {
			turnPlayer.setHitPts(currHP + healValue);
		}
		turnPlayer.reduceFoodCount();
	}
	void freeze(Status turnPlayer, Status otherPlayer) {
		// Special attacks not yet implemented
	}
	void doubleAtk(Status turnPlayer, Status otherPlayer) {
		// Special attacks not yet implemented
	}
	void spAtk3(Status turnPlayer, Status otherPlayer) {
		// Special attacks not yet implemented
	}
	void spAtk4(Status turnPlayer, Status otherPlayer) {
		// Special attacks not yet implemented
	}
}
