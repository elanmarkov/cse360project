package sliceAndDice;
/**
 * Game class to run a game, including create turns, track
 * interface with GUI, and update statistics between turns.
 * Turn class to play turns, roll dice, and evaluate legality
 * of any given move.
 * 
 * @author Team 1, CSE 360 Spring 2016
 * Group members:
 * Jacob Loden
 * Elan Markov
 * Daniel Saman
 * Eisher Saroya
 * Andrew Stanton
 *
 */
import java.util.ArrayList;
enum Winner {NONE, PLAYER_ONE, PLAYER_TWO};
enum IllegalMove {NONE, NOFOOD, NOMANA, NOTIMPLEMENTED};

/**
 * Game class. Runs an instance of a game played by the UI and
 * handles logic for the interaction between users and the UI.
 * Calls Turn to play individual turns, completes statistics
 * between turns.
 *
 */
public class Game {
	Player playerOne;
	Player playerTwo;
	int winnerID;
	int loserID;
	Status playerOneStatus;
	Status playerTwoStatus;
	int totalTurns;
	boolean playerOneTurn;
	Turn nextTurn;
	
	/**
	 * Constructor for Game class from two usernames.
	 * @param playerOne Username of player that goes first
	 * @param playerTwo Username of player that goes second
	 */
	Game(String playerOne, String playerTwo) {
		totalTurns = 0;
		//this.playerOne = Scoreboard.getPlayerByUsername(playerOne);
		//this.playerTwo = Scoreboard.getPlayerByUsername(playerOne);
		this.playerOne = new Player(playerOne, 0);
		this.playerTwo = new Player(playerTwo, 1);
		playerOneStatus = new Status();
		playerTwoStatus = new Status();
		playerOneTurn = true;
		winnerID = -1; // no winner yet
		loserID = -1; // no winner yet
	}
	
	/**
	 * Plays the next (half-) turn of the game.
	 * @param nextMove The move to be performed by the next player, must be legal.
	 * @return The winner of the game (player 1, player 2, or no winner yet).
	 */
	Winner  PlayNextTurn(Move nextMove) {
		Winner gameWinner = Winner.NONE; // If this turn is played, no one won yet.
		
		if(playerOneTurn){	// Different turn based on whose move it is
			nextTurn = new Turn(playerOneStatus, playerTwoStatus);
			gameWinner = nextTurn.playTurnPlayerOne(nextMove);
			playerOneTurn = false;
			updateMoveCount(playerOne, nextMove);
		}
		else{
			gameWinner = nextTurn.playTurnPlayerTwo(nextMove);
			playerOneTurn = true;
			updateMoveCount(playerTwo, nextMove);
		}

		totalTurns++;
		
		//Evaluate the game winner.
		if(gameWinner == Winner.PLAYER_ONE){
			winnerID = playerOne.getID();
			loserID = playerTwo.getID();
			playerOne.getPlayerData().incrWinCount();
			updateStats();
		}
		else if(gameWinner == Winner.PLAYER_TWO){
			winnerID = playerTwo.getID();
			loserID = playerOne.getID();
			playerTwo.getPlayerData().incrWinCount();
			updateStats();
		}
		
		return gameWinner;
		
	}
	/**
	 * Evaluate legality of the next move.
	 * @param nextMove Next move to be performed.
	 * @return The violation of the current move, or none.
	 */
	IllegalMove nextMoveLegality(Move nextMove) {
		IllegalMove violation = IllegalMove.NONE;
		if(playerOneTurn) {
			violation = Turn.moveIsLegal(playerOneStatus, nextMove);
		}
		else {
			violation = Turn.moveIsLegal(playerTwoStatus, nextMove);
		}
		return violation;
	}
	/**
	 * Returns if it is currently player 1's turn 
	 * (otherwise, it is player 2's turn).
	 * @return true if it is player 1's turn, otherwise false.
	 */
	boolean isPlayerOneTurn(){
		return playerOneTurn;
	}
	/**
	 * Getter class for player 1's status data.
	 * @return Player 1's Status object for this game.
	 */
	Status getPlayerOneStatus() {
		return playerOneStatus;
	}
	/**
	 * Getter class for player 2's status data.
	 * @return Player 2's Status object for this game.
	 */
	Status getPlayerTwoStatus() {
		return playerTwoStatus;
	}
	/**
	 * Gives the ID of the winner for a completed game.
	 * @return ID of the player who won this game, -1 if no winner.
	 */
	int getWinnerID() {
		return winnerID;
	}
	/**
	 * Gives the ID of the loser for a completed game.
	 * @return ID of the player who lost this game, -1 if no winner.
	 */
	int getLoserID() {
		return loserID;
	}
	/**
	 * Gives the number of total (half) turns played this game.
	 * @return Total half-turns played in this game.
	 */
	int getTotalTurns() {
		return totalTurns;
	}
	/**
	 * Returns the last roll made in the current turn.
	 * @return Array containing each individual roll for this game.
	 */
	int[] getLastRoll() {
		return nextTurn.getLastRoll();
	}
	/**
	 * Updates the player statistics for each player at the end of the game.
	 */
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
	/**
	 * Updates the move count for each player after each turn.
	 * @param turnPlayer the Player who just made a move
	 * @param turnPlayerMove the move made by the player
	 */
	void updateMoveCount(Player turnPlayer, Move turnPlayerMove) {
		switch (turnPlayerMove) {
		case ATTACK:
			turnPlayer.getPlayerData().incrNumAttacks();
			break;
		case FOOD:
			turnPlayer.getPlayerData().incrNumMeals();
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
/**
 * Turn class for the game logic. Plays one round between two players
 * and updates the Status objects associated with each player.
 * Evaluates legality for each move based on status of each player.
 * Performs and stores dice rolls for the previous move.
 *
 */
class Turn {
	Status statusP1;
	Status statusP2;
	static int[] lastRoll;

	/**
	 * Constructor for the Turn class.
	 * @param statusP1 Status object for a player.
	 * @param statusP2 Status object for another player.
	 */
	Turn(Status statusP1, Status statusP2) {
		this.statusP1 = statusP1;
		this.statusP2 = statusP2;
	}
	
	/**
	 * Plays a move and evaluates win condition if it is player 1's turn.
	 * @param moveP1 the most recent (legal) move of player 1.
	 * @return Winner of the current game, or no winner.
	 */
	Winner playTurnPlayerOne(Move moveP1) {
		Winner gameWinner = Winner.NONE;	
		
		if(Turn.moveIsLegal(statusP1, moveP1) != IllegalMove.NONE) {
			throw new IllegalArgumentException("Error: Illegal move not caught.");
		}
		
		playNextTurn(moveP1, statusP1, statusP2);
		
		if(statusP2.getHitPts() == 0) {
			// Return if player one has won
			gameWinner = Winner.PLAYER_ONE;
		}
		return gameWinner;
	}
	/**
	 * Plays a move and evaluates win condition if it is player 2's turn.
	 * @param moveP2 the most recent (legal) move of player 2.
	 * @return Winner of the current game, or no winner yet.
	 */
	Winner playTurnPlayerTwo(Move moveP2){	
		Winner gameWinner = Winner.NONE;
		
		if(Turn.moveIsLegal(statusP2, moveP2) != IllegalMove.NONE) {
				throw new IllegalArgumentException("Error: Illegal move not caught.");
		}
		
		playNextTurn(moveP2, statusP2, statusP1);
			
		if(statusP1.getHitPts() == 0) {
			gameWinner = Winner.PLAYER_TWO;
		}

		return gameWinner;
	}
	/**
	 * Executes move by calling relevant function.
	 * @param nextMove Move to be performed by turn player.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	void playNextTurn(Move nextMove, Status turnPlayer, Status otherPlayer) {
		switch(nextMove) {
		case ATTACK:
			attack(turnPlayer, otherPlayer);
			break;
		case FOOD:
			food(turnPlayer);
			break;
		case FREEZE:
			freeze(turnPlayer, otherPlayer);
			break;
		case DOUBLEATK:
			doubleAtk(turnPlayer, otherPlayer);
			break;
		case SPATK3:
			spAtk3(turnPlayer, otherPlayer);
			break;
		case SPATK4:
			spAtk4(turnPlayer, otherPlayer);
			break;
		default:
			throw new IllegalArgumentException("Error: Illegal move not caught.");	
		}
	}
	/**
	 * Returns the last value rolled by a player.
	 * @return
	 */
	int[] getLastRoll(){
		return lastRoll;
	}
	/**
	 * Static class, evalates given a Status and Move whether or not that move is legal.
	 * @param turnPlayer Status of the turn player.
	 * @param nextMove Move to be evaluated.
	 * @return Violation notification, or none if move is legal.
	 */
	static IllegalMove moveIsLegal(Status turnPlayer, Move nextMove) {
		IllegalMove violation = IllegalMove.NONE;
		if(nextMove == Move.FOOD && turnPlayer.getFoodCount() == 0) {
			violation = IllegalMove.NOFOOD;
		}
		else if (nextMove != Move.ATTACK) {
			violation = IllegalMove.NOTIMPLEMENTED;
		}
		return violation;
	}
	/**
	 * Attack method. Performs basic attack based on dice roll value.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	void attack(Status turnPlayer, Status otherPlayer) {
		// Roll 4 dice, do damage equal to combined result.
		int numRoll = 4;
		lastRoll = DiceRoll.roll(numRoll);
		int sumDamage = 0;
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			sumDamage += lastRoll[rollCount];
		}
		
		int oppHP = otherPlayer.getHitPts();
		if(oppHP <= sumDamage) {
			otherPlayer.setHitPts(0);
		}
		else {
			otherPlayer.setHitPts(oppHP - sumDamage);
		}
	}
	/**
	 * Food method. Heals a given amount of HP for the turn player.
	 * @param turnPlayer Status of the turn player.
	 */
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
	/**
	 * Special attack. Not yet implemented.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	void freeze(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	/**
	 * Special attack. Not yet implemented.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	void doubleAtk(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	/**
	 * Special attack. Not yet implemented.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	void spAtk3(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	/**
	 * Special attack. Not yet implemented.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	void spAtk4(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
}

