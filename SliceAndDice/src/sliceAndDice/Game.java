package sliceAndDice;

import java.util.ArrayList;

public class Game {
	ArrayList<Turn> gameTurns;
	Player playerOne;
	Player playerTwo;
	Player winner;
	Player loser;
	Game(Player Player1, Player Player2) {
		//Determine who is first and second by dice roll
		//TODO: Add GUI interface where needed
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
	}
	boolean winCondition () {
		//check if there is a winner
		if(playerOne.getHitPts() == 0) {
			winner = playerTwo;
			loser = playerOne;
			return true;
		}
		else if(playerTwo.getHitPts() == 0) {
			winner = playerOne;
			loser = playerTwo;
			return true;
		}
		else {
			return false;
		}
	}
}

class Turn {
	Turn() {
		
	}
	void playTurn(Player playerOne, Player playerTwo) {
		while(!moveIsLegal(playerOne, playerOne.getNextMove())) {
			// Move not legal, inform user
		}
		// Perform move, update stats
		if(playerTwo.getHitPts() == 0) {
			return;
		}
		else {
			while(!moveIsLegal(playerTwo, playerTwo.getNextMove())) {
				// Move not legal, inform user
			}
			// Perform move, update stats
		}
	}
	boolean moveIsLegal(Player turnPlayer, Move nextMove) {
		// check if last move was legal
		return false;
	}
	void updateStats() {
		
	}
}
