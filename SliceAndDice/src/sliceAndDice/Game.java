package sliceAndDice;

import java.util.ArrayList;
enum Winner {NONE, PLAYER_ONE, PLAYER_TWO};

public class Game {
	ArrayList<Turn> gameTurns;
	Player playerOne;
	Player playerTwo;
	Data playerOneData;
	Data playerTwoData;
	Player winner;
	Player loser;
	int totalTurns;
	boolean reversePlayerOrder;

	Game(Player Player1, Player Player2, Data Player1Data, Data Player2Data) {
		//Determine who is first and second by dice roll
		//TODO: Add GUI interface where needed
		totalTurns = 0;
		boolean playersOrdered = false;
		boolean reversePlayerOrder = false; // determines which data object is loaded into which 
		int[] turnRoll;
		while (!playersOrdered) {
			turnRoll = DiceRoll.roll(2);
			if(turnRoll[0] > turnRoll[1]) {
				playerOne = Player1;
				playerTwo = Player2;
				playerOneData = Player1Data;
				playerTwoData = Player2Data;
				playersOrdered = true;
				// reversePlayerOrder already false
			}
			else if(turnRoll[0] < turnRoll[1]) {
				playerOne = Player2;
				playerTwo = Player1;
				playerOneData = Player2Data;
				playerTwoData = Player1Data;
				playersOrdered = true;
				reversePlayerOrder = true;
			}
		}
		gameTurns = new ArrayList<Turn>(0);
		playerOne.resetStatus();
		playerTwo.resetStatus();
	}
	void PlayGame () {
		Winner gameWinner = Winner.NONE;
		while(gameWinner == Winner.NONE) {
			gameTurns.add(new Turn(playerOne, playerTwo, playerOneData, playerTwoData));
			Turn nextTurn = gameTurns.get(totalTurns);
			gameWinner = nextTurn.playTurn(playerOneData, playerTwoData);
			totalTurns++;
		}
		if(gameWinner == Winner.PLAYER_ONE){
			winner = playerOne;
			loser = playerTwo;
			playerOneData.incrWinCount();
		}
		else if(gameWinner == Winner.PLAYER_TWO){
			winner = playerTwo;
			loser = playerOne;
			playerTwoData.incrWinCount();
		}
		else {
			throw new IllegalArgumentException("Game ended without a winner.");
		}
		playerOneData.incrGameCount();
		playerOneData.updateHealthLost((playerOne.getStatus()).getHitPts());
		playerOneData.updateManaUsed(playerOne.getStatus().getMana());
		playerOneData.updateFoodUsed(playerOne.getStatus().getFoodCount());
		playerTwoData.incrGameCount();
		playerTwoData.updateHealthLost((playerTwo.getStatus()).getHitPts());
		playerTwoData.updateManaUsed(playerTwo.getStatus().getMana());
		playerTwoData.updateFoodUsed(playerTwo.getStatus().getFoodCount());
		
		playerOne.removeStatus();
		playerTwo.removeStatus();
	}
	Player getWinner() {
		return winner;
	}
	Player getLoser() {
		return loser;
	}
	boolean getPlayerOrder() {
		if(!reversePlayerOrder) { // if player order not reversed, return true
			return true;
		}
		else { // if player order reversed, return false
			return false;
		}
	}
	Data getPlayerOneData() {
		return playerOneData;
	}
	Data getPlayerTwoData() {
		return playerTwoData;
	}
}

class Turn {
	Player playerOne;
	Player playerTwo;
	Data playerOneData;
	Data playerTwoData;
	Status statusP1;
	Status statusP2;

	Turn(Player playerOne, Player playerTwo, Data playerOneData, Data playerTwoData) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.playerOneData = playerOneData;
		this.playerTwoData = playerTwoData;
		statusP1 = new Status(playerOne.getStatus());
		statusP2 = new Status(playerTwo.getStatus());
	}
	Winner playTurn(Data playerOneData, Data playerTwoData) {
		Winner gameWinner = Winner.NONE;
		Move nextMove = Move.ATTACK;;
		boolean legalMove = false;
		
		while(!legalMove) {
			nextMove = playerOne.getNextMove();
			legalMove = moveIsLegal(statusP1, nextMove);
			if(!legalMove) {
				// GUI prompt user for new move
			}
		}
		playerOneData.incrNumTurns();
		
		switch(nextMove) {
		case ATTACK:
			attack(statusP1, statusP2);
			playerOneData.incrNumAttacks();
			break;
		case FOOD:
			food(statusP1);
			playerTwoData.incrNumMeals();
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
			legalMove = false;
			nextMove = Move.ATTACK; // Default next move
			
			while(!legalMove) {
				nextMove = playerTwo.getNextMove();
				legalMove = moveIsLegal(statusP1, nextMove);
				if(!legalMove) {
					// GUI prompt user for new move
				}
			}
			playerTwoData.incrNumTurns();
			
			switch(nextMove) {
			case ATTACK:
				attack(statusP2, statusP1);
				playerTwoData.incrNumAttacks();
				break;
			case FOOD:
				food(statusP2);
				playerTwoData.incrNumMeals();
				break;
			case FREEZE:
				freeze(statusP2, statusP1);
				// incrNumFreeze();
				break;
			case DOUBLEATK:
				doubleAtk(statusP2, statusP1);
				// incrNumDoubleAtk
				break;
			case SPATK3:
				spAtk3(statusP2, statusP1);
				// incrNumSPATK3
				break;
			case SPATK4:
				spAtk4(statusP2, statusP1);
				// incrNumSPATK4
				break;
			default:
				throw new IllegalArgumentException("Error: Illegal move not caught.");	
			}
			
			if(statusP1.getHitPts() == 0) {
				// Player 2 wins on the next attack
				gameWinner = Winner.PLAYER_TWO;
			}
		}
		
		updateStats();
		return Winner.NONE;
	}
	boolean moveIsLegal(Status turnPlayer, Move nextMove) {
		// check if last move was legal
		return false;
	}
	void attack(Status turnPlayer, Status otherPlayer) {
		// Roll 5 dice, do damage equal to combined result.
		int numRoll = 5;
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
	void updateStats() {
		playerOne.setStatus(statusP1);
		playerTwo.setStatus(statusP2);
	}
}
