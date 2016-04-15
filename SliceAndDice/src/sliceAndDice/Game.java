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
	boolean first;	// Added this; Keeps track of who's turn it is
	
	/*
	 * Changed to string parameters
	 * 
	 * GUI will pass in user names only
	 */
	Game(String playerOne, String playerTwo) {
		totalTurns = 0;
		this.playerOne = new Player(playerOne, 1);	// TODO provide playerID assignment logic
		this.playerTwo = new Player(playerTwo, 2);
		playerOneStatus = new Status();
		playerTwoStatus = new Status();
		first = true;
	}

	Winner  PlayNextTurn(Move newMove) {	// Changed to accept one move
		Winner gameWinner = Winner.NONE;
				
		Turn nextTurn = new Turn(playerOneStatus, playerTwoStatus);
		
		if(first){	// Determines who's move it is
			gameWinner = nextTurn.playTurnPlayerOne(newMove);
			first = false;
		}else{
			gameWinner = nextTurn.playTurnPlayerTwo(newMove);
			first = true;
		}

		totalTurns++;
		
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
		
		return gameWinner;
		
	}
	
	/*
	 * Lets GUI see who's turn it is
	 */
	boolean getFirst(){	// Changed by Jacob
		return first;
	}
	Status getPlayerOneStatus() {
		return playerOneStatus;
	}
	Status getPlayerTwoStatus() {
		return playerTwoStatus;
	}
	int getWinnerID() {
		return winnerID;
	}
	int getLoserID() {
		return loserID;
	}
	int getTotalTurns() {
		return totalTurns;
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
	}
}

class Turn {
	Status statusP1;
	Status statusP2;
	Winner gameWinner;	// Changed by Jacob
	static int[] roll;

	Turn(Status statusP1, Status statusP2) {
		this.statusP1 = statusP1;
		this.statusP2 = statusP2;
		gameWinner = Winner.NONE;	// Changed by Jacob
	}
	
	Winner playTurnPlayerOne(Move moveP1) {
		//Winner gameWinner = Winner.NONE;	// Changed by Jacob
		int numRoll = 4;					// Added by Jacob
		roll = DiceRoll.roll(numRoll);		// Added by Jacob; Gui needs to see the roll result
		
		if(!Turn.moveIsLegal(statusP1, moveP1)) {
			throw new IllegalArgumentException("Error: Illegal move not caught.");
		}
		
		switch(moveP1) {
		case ATTACK:
			attack(statusP1, statusP2, roll);	// Added this
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
		return gameWinner;
	}
	
	Winner playTurnPlayerTwo(Move moveP2){	// Changed by Jacob: Added additional method Winner method
		int numRoll = 4;					// Added by Jacob
		roll = DiceRoll.roll(numRoll);		// Added by Jacob; Gui needs to see the roll result
		
		if(gameWinner == Winner.NONE) {
			
			if(!Turn.moveIsLegal(statusP2, moveP2)) {
				throw new IllegalArgumentException("Error: Illegal move not caught.");
			}
			
			switch(moveP2) {
			case ATTACK:
				attack(statusP2, statusP1, roll);
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
		return gameWinner;
	}
	
	static int[] getRoll(){
		return roll;
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
	
	/*
	 * Changed attack method to accept a dice roll
	 */
	void attack(Status turnPlayer, Status otherPlayer, int[] turnRoll) {
		// Roll 4 dice, do damage equal to combined result.
		int numRoll = turnRoll.length;
		//int[] diceResult = DiceRoll.roll(numRoll);
		int sumDamage = 0;
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			sumDamage += turnRoll[rollCount];
		}
		
		int oppHP = otherPlayer.getHitPts();
		if(oppHP <= sumDamage) {
			otherPlayer.setHitPts(0);
		}
		else {
			otherPlayer.setHitPts(oppHP - sumDamage);
		}
		turnPlayer.setLastRoll(turnRoll);
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
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	
	void doubleAtk(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	
	void spAtk3(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	
	void spAtk4(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
}

