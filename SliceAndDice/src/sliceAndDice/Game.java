package sliceAndDice;

import java.util.ArrayList;
enum Winner {NONE, PLAYER_ONE, PLAYER_TWO};

public class Game {
	ArrayList<Turn> gameTurns;
	Player playerOne;
	Player playerTwo;
	Player winner;
	Player loser;
	int totalTurns;
	Game(Player Player1, Player Player2) {
		//Determine who is first and second by dice roll
		//TODO: Add GUI interface where needed
		totalTurns = 0;
		boolean playersOrdered = false;
		int[] turnRoll;
		while (!playersOrdered) {
			turnRoll = DiceRoll.roll(2);
			if(turnRoll[0] > turnRoll[1]) {
				playerOne = Player1;
				playerTwo = Player2;
				playersOrdered = true;
			}
			else if(turnRoll[0] < turnRoll[1]) {
				playerOne = Player2;
				playerTwo = Player1;
				playersOrdered = true;
			}
		}
		gameTurns = new ArrayList<Turn>(0);
		playerOne.resetStatus();
		playerTwo.resetStatus();
	}
	void PlayGame () {
		Winner gameWinner = Winner.NONE;
		while(gameWinner == Winner.NONE) {
			gameTurns.add(new Turn(playerOne, playerTwo));
			Turn nextTurn = gameTurns.get(totalTurns);
			gameWinner = nextTurn.playTurn();
			totalTurns++;
		}
		if(gameWinner == Winner.PLAYER_ONE){
			winner = playerOne;
			loser = playerTwo;
		}
		else if(gameWinner == Winner.PLAYER_TWO){
			winner = playerTwo;
			loser = playerOne;
		}
		else {
			throw new IllegalArgumentException("Game ended without a winner.");
		}
		playerOne.removeStatus();
		playerTwo.removeStatus();
	}
	Player getWinner() {
		return winner;
	}
	Player getLoser() {
		return loser;
	}
}

class Turn {
	Player playerOne;
	Player playerTwo;
	Status statusP1;
	Status statusP2;

	Turn(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		statusP1 = new Status(playerOne.getStatus());
		statusP2 = new Status(playerTwo.getStatus());
	}
	Winner playTurn() {
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
		
		switch(nextMove) {
		case ATTACK:
			attack(statusP1, statusP2);
			break;
		case FOOD:
			food(statusP1);
			break;
		case FREEZE:
			freeze(statusP1, statusP2);
			break;
		case DOUBLEATK:
			doubleAtk(statusP1, statusP2);
			break;
		case SPATK3:
			spAtk3(statusP1, statusP2);
			break;
		case SPATK4:
			spAtk4(statusP1, statusP2);
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
			switch(nextMove) {
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
