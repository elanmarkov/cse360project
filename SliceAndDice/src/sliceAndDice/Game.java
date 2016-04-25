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

enum Winner {NONE, PLAYER_ONE, PLAYER_TWO};
enum IllegalMove {NONE, NOFOOD, NOMANA};

/**
 * Game class. Runs an instance of a game played by the UI and
 * handles logic for the interaction between users and the UI.
 * Calls Turn to play individual turns, completes statistics
 * between turns.
 * 
 * @author Elan Markov, PIN 525, CSE 360, Spring 2016
 *
 */
public class Game {
	private Player playerOne;
	private Player playerTwo;
	private int winnerID;
	private int loserID;
	private Status playerOneStatus;
	private Status playerTwoStatus;
	private int totalTurns;
	private boolean playerOneTurn;
	private boolean lastConditionEvaluated;
	private Turn nextTurn;
	
	/**
	 * Constructor for Game class from two usernames.
	 * @param playerOne Username of player that goes first
	 * @param playerTwo Username of player that goes second
	 */
	Game(String playerOne, String playerTwo) {
		totalTurns = 0;
		this.playerOne = Scoreboard.getPlayerByUsername(playerOne);
		this.playerTwo = Scoreboard.getPlayerByUsername(playerTwo);
		//this.playerOne = new Player(playerOne, 1);
		//this.playerTwo = new Player(playerTwo, 2);
		playerOneStatus = new Status();
		playerTwoStatus = new Status();
		playerOneTurn = true;
		lastConditionEvaluated = false;
		winnerID = -1; // no winner yet
		loserID = -1; // no winner yet
		nextTurn = new Turn(playerOneStatus, playerTwoStatus);
	}
	/**
	 * Constructor for Game class from two Player objects.
	 * @param playerOne Player that goes first
	 * @param playerTwo Player that goes second
	 */
	Game(Player playerOne, Player playerTwo) {
		totalTurns = 0;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		playerOneStatus = new Status();
		playerTwoStatus = new Status();
		playerOneTurn = true;
		winnerID = -1; // no winner yet
		loserID = -1; // no winner yet
		nextTurn = new Turn(playerOneStatus, playerTwoStatus);
	}
	/**
	 * Constructor for Game class from one username, where the opponent
	 * is the computer.
	 * @param humanPlayer The username of the human player.
	 * @param humanGoesFirst Whether or not the human player won the dice roll.
	 */
	Game(String humanPlayer, boolean humanGoesFirst) {
		totalTurns = 0;
		if(humanGoesFirst) {
			this.playerOne = Scoreboard.getPlayerByUsername(humanPlayer);
			//this.playerOne = new Player(humanPlayer, 1);
			this.playerTwo = new Robot();
		}
		else {
			this.playerTwo = Scoreboard.getPlayerByUsername(humanPlayer);
			this.playerOne = new Robot();
			//this.playerTwo = new Player(humanPlayer, 1);
		}
		playerOneStatus = new Status();
		playerTwoStatus = new Status();
		playerOneTurn = true;
		winnerID = -1; // no winner yet
		loserID = -1; // no winner yet
		nextTurn = new Turn(playerOneStatus, playerTwoStatus);
	}
	/**
	 * Plays the next (half-) turn of the game.
	 * @param nextMove The move to be performed by the next player, must be legal.
	 * @return The winner of the game (player 1, player 2, or no winner yet).
	 */
	Winner  PlayNextTurn(Move nextMove) {
		Winner gameWinner = Winner.NONE; // If this turn is played, no one won yet.
		
		if(!lastConditionEvaluated) {
			throw new IllegalStateException("Error: Condition has not been evaluated.");
		}
		
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
		
		lastConditionEvaluated = false;
		return gameWinner;
		
	}
	/**
	 * Plays the next move, if the player is a computer (will return exception if not).
	 * @return The winner of the game (player 1, player 2, or none yet).
	 */
	Winner  PlayNextTurn() {
		if(!lastConditionEvaluated) {
		throw new IllegalStateException("Error: Condition has not been evaluated.");
		}
		Winner gameWinner = Winner.NONE; // If this turn is played, no one won yet.
		Move nextComputerMove = playerOne.getNextMove(playerOneStatus, playerTwoStatus);
		if(playerOneTurn){	// Different turn based on whose move it is
			nextTurn = new Turn(playerOneStatus, playerTwoStatus);
			
			gameWinner = nextTurn.playTurnPlayerOne(nextComputerMove);
			playerOneTurn = false;
			updateMoveCount(playerOne, nextComputerMove);
		}
		else{
			gameWinner = nextTurn.playTurnPlayerTwo(nextComputerMove);
			playerOneTurn = true;
			updateMoveCount(playerTwo, nextComputerMove);
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
		lastConditionEvaluated = false;
		return gameWinner;
		
	}
	/**
	 * Gets the condition of the current turn player.
	 * @return Condition of the current turn player.
	 */
	Condition getPlayerCondition() {
		Condition prevCondition = Condition.NONE;
		if(playerOneTurn) {
			prevCondition = playerOneStatus.getCondition();
		}
		else {
			prevCondition = playerTwoStatus.getCondition();
		}
		return prevCondition;
	}
	/**
	 * Updates condition for each player, including damage, and
	 * evaluates the game winner.
	 * @return Winner of the current game (player 1, player 2, or none yet).
	 */
	Winner updateCondition() {
		lastConditionEvaluated = true;
		Winner gameWinner = Winner.NONE;
		if(playerOneTurn) {
			if(playerOneStatus.getCondition() == Condition.FROZEN) {
				//skip a turn, must check condition for other player for their turn
				playerOneTurn = false;
				lastConditionEvaluated = false;
			}
			nextTurn.updateCondition(playerOneStatus);
		}
		else {
			if(playerTwoStatus.getCondition() == Condition.FROZEN) {
				//skip a turn, must check condition for other player for their turn
				playerOneTurn = true;
				lastConditionEvaluated = false;
			}
			nextTurn.updateCondition(playerTwoStatus);
		}
		if(playerOneStatus.getHitPts() == 0) {
			gameWinner = Winner.PLAYER_TWO;
		}
		else if (playerTwoStatus.getHitPts() == 0) {
			gameWinner = Winner.PLAYER_ONE;
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
	 * Getter method for player 1's status data.
	 * @return Player 1's Status object for this game.
	 */
	Status getPlayerOneStatus() {
		return playerOneStatus;
	}
	/**
	 * Getter method for player 2's status data.
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
	 * Getter method for Player 1 username.
	 * @return Player 1's username.
	 */
	String getPlayerOneUsername() {
		return playerOne.getUsername();
	}
	/**
	 * Getter method for Player 2 username.
	 * @return Player 2's username.
	 */
	String getPlayerTwoUsername() {
		return playerTwo.getUsername();
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
	private void updateStats() {
		playerOne.getPlayerData().incrGameCount();
		playerOne.getPlayerData().updateHealthLost(playerOneStatus.getHitPts());
		playerOne.getPlayerData().updateManaUsed(playerOneStatus.getMana());
		playerOne.getPlayerData().updateFoodUsed(playerOneStatus.getFoodCount());
		playerTwo.getPlayerData().incrGameCount();
		playerTwo.getPlayerData().updateHealthLost(playerTwoStatus.getHitPts());
		playerTwo.getPlayerData().updateManaUsed(playerTwoStatus.getMana());
		playerTwo.getPlayerData().updateFoodUsed(playerTwoStatus.getFoodCount());
		//Scoreboard.calculateNewScore(winnerID, loserID);
	}
	/**
	 * Updates the move count for each player after each turn.
	 * @param turnPlayer the Player who just made a move
	 * @param turnPlayerMove the move made by the player
	 */
	private void updateMoveCount(Player turnPlayer, Move turnPlayerMove) {
		switch (turnPlayerMove) {
		case ATTACK:
			turnPlayer.getPlayerData().incrNumAttacks();
			turnPlayer.getPlayerData().incrNumBaseAttacks();
			break;
		case FOOD:
			turnPlayer.getPlayerData().incrNumMeals();
			break;
		case FREEZE:
			turnPlayer.getPlayerData().incrNumAttacks();
			turnPlayer.getPlayerData().incrNumSPAttacks();
			break;
		case DOUBLEATK:
			turnPlayer.getPlayerData().incrNumAttacks();
			turnPlayer.getPlayerData().incrNumSPAttacks();
			break;
		case POISON:
			turnPlayer.getPlayerData().incrNumAttacks();
			turnPlayer.getPlayerData().incrNumSPAttacks();
			break;
		case AURA:
			turnPlayer.getPlayerData().incrNumAttacks();
			turnPlayer.getPlayerData().incrNumSPAttacks();
			break;
		case CHARGE:
			turnPlayer.getPlayerData().incrNumAttacks();
			turnPlayer.getPlayerData().incrNumSPAttacks();
			break;
		default:
			throw new IllegalArgumentException("Error: Illegal move not caught.");	
		}

	}
	private void abortGame() {
		//playerOne.getPlayerData().incrNumAborts();
		//playerTwo.getPlayerData().incrNumAborts();
		updateStats();
	}
}
/**
 * Turn class for the game logic. Plays one round between two players
 * and updates the Status objects associated with each player.
 * Evaluates legality for each move based on status of each player.
 * Performs and stores dice rolls for the previous move.
 *
 * @author Elan Markov, PIN 525, CSE 360, Spring 2016
 */
class Turn {
	private Status statusP1;
	private Status statusP2;
	static int[] lastRoll;
	private final static int manaFreeze = 6;
	private final static int manaDouble = 10;
	private final static int manaPoison = 8;
	private final static int manaAura = 10;
	private final static int manaCharge = 0;
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
	private void playNextTurn(Move nextMove, Status turnPlayer, Status otherPlayer) {
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
		case POISON:
			poison(turnPlayer, otherPlayer);
			break;
		case AURA:
			aura(turnPlayer);
			break;
		case CHARGE:
			charge(turnPlayer);
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
	 * Static method, evaluates given a Status and Move whether or not that move is legal.
	 * @param turnPlayer Status of the turn player.
	 * @param nextMove Move to be evaluated.
	 * @return Violation notification, or none if move is legal.
	 */
	static IllegalMove moveIsLegal(Status turnPlayer, Move nextMove) {
		IllegalMove violation = IllegalMove.NONE;
		if(turnPlayer.getCondition() == Condition.FROZEN) {
			throw new IllegalArgumentException("Error: Frozen players should not have a turn");
		}
		if(nextMove == Move.FOOD && turnPlayer.getFoodCount() <= 0) {
			violation = IllegalMove.NOFOOD;
		}
		else if (nextMove == Move.FREEZE && turnPlayer.getMana() < manaFreeze) { 
			violation = IllegalMove.NOMANA;
		}
		else if (nextMove == Move.DOUBLEATK && turnPlayer.getMana() < manaDouble) { 
			violation = IllegalMove.NOMANA;
		}
		else if (nextMove == Move.POISON && turnPlayer.getMana() < manaPoison) { 
			violation = IllegalMove.NOMANA;
		}
		else if (nextMove == Move.AURA && turnPlayer.getMana() < manaAura) { 
			violation = IllegalMove.NOMANA;
		}
		else if (nextMove == Move.CHARGE && turnPlayer.getMana() < manaCharge) { 
			violation = IllegalMove.NOMANA;
		}
		return violation;
	}
	/**
	 * Updates the condition of the turn player between moves.
	 * Performs temporal changes on current condition and lowers
	 * HP where needed.
	 * @param turnPlayer The player who will move next (for whom the condition is evaluated).
	 */
	void updateCondition(Status turnPlayer) {
		switch(turnPlayer.getCondition()) {
		case NONE:
			turnPlayer.setAtk(0);
			turnPlayer.setDef(0);
			break;
		case AURA1:
			turnPlayer.setCondition(Condition.AURA2);
			break;
		case AURA2:
			turnPlayer.setCondition(Condition.NONE);
			break;
		case FROZEN:
			turnPlayer.setCondition(Condition.NONE);
			break;
		case POISON1:
			turnPlayer.setCondition(Condition.POISON2);
			turnPlayer.reduceHP(1);
			break;
		case POISON2:
			turnPlayer.setCondition(Condition.POISON3);
			turnPlayer.reduceHP(2);
			break;
		case POISON3:
			turnPlayer.setCondition(Condition.POISON4);
			turnPlayer.reduceHP(3);
			break;
		case POISON4:
			turnPlayer.setCondition(Condition.POISON5);
			turnPlayer.reduceHP(4);
			break;
		case POISON5:
			turnPlayer.reduceHP(5);
			break;
		default:
			throw new IllegalArgumentException("Error: A non-condition was given.");
		}
	}
	/**
	 * Attack method. Performs basic attack based on dice roll value.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	private void attack(Status turnPlayer, Status otherPlayer) {
		// Roll 4 dice, do damage equal to combined result.
		int numRoll = 4;
		lastRoll = DiceRoll.roll(numRoll);
		int sumDamage = 0;
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			sumDamage += lastRoll[rollCount];
		}
		sumDamage += turnPlayer.getAtk();
		sumDamage -= otherPlayer.getDef();
		if(sumDamage > 0) {
			otherPlayer.reduceHP(sumDamage);
		}
	}
	/**
	 * Food method. Heals a given amount of HP for the turn player.
	 * @param turnPlayer Status of the turn player.
	 */
	private void food(Status turnPlayer) {
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
	 * Freeze attack. Damages opponent and has a chance of freezing.
	 * Mana cost associated with this attack.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	private void freeze(Status turnPlayer, Status otherPlayer) {
		// Roll 4 dice. Freeze if first two dice sum to 7 or more.
		// Do damage equal to sum of second two dice.
		int numRoll = 4;
		lastRoll = DiceRoll.roll(numRoll);
		int sumFreeze = lastRoll[0] + lastRoll[1];
		int sumDamage = 0;
		for(int rollCount = 2; rollCount < numRoll; rollCount++) {
			sumDamage += lastRoll[rollCount];
		}				
		sumDamage += turnPlayer.getAtk();
		sumDamage -= otherPlayer.getDef();
		if(sumDamage > 0) {
			otherPlayer.reduceHP(sumDamage);
		}
		if(sumFreeze > 6) {
			if(otherPlayer.getCondition() != Condition.AURA1 
					&& otherPlayer.getCondition() != Condition.AURA2) {
				otherPlayer.setCondition(Condition.FROZEN);
			}
		}
		turnPlayer.reduceMana(manaFreeze);
	}
	/**
	 * Double attack. Attacks twice in one turn.
	 * Mana cost associated with this attack.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	private void doubleAtk(Status turnPlayer, Status otherPlayer) {
		// Roll 8 dice, do damage equal to combined result.
		int numRoll = 8;
		lastRoll = DiceRoll.roll(numRoll);
		int sumDamage = 0;
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			sumDamage += lastRoll[rollCount];
		}
		sumDamage += 2 * turnPlayer.getAtk();
		sumDamage -= 2 * otherPlayer.getDef();
		if(sumDamage > 0) {
			otherPlayer.reduceHP(sumDamage);
		}
		turnPlayer.reduceMana(manaDouble);
	}
	/**
	 * Special attack. Not yet implemented.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	private void poison(Status turnPlayer, Status otherPlayer) {
		// Roll 4 dice. Poison if first two dice sum to 7 or more.
		// Do damage equal to sum of second two dice.
		int numRoll = 4;
		lastRoll = DiceRoll.roll(numRoll);
		int sumPoison = lastRoll[0] + lastRoll[1];
		int sumDamage = 0;
		for(int rollCount = 2; rollCount < numRoll; rollCount++) {
			sumDamage += lastRoll[rollCount];
		}				
		sumDamage += turnPlayer.getAtk();
		sumDamage -= otherPlayer.getDef();
		if(sumDamage > 0) {
			otherPlayer.reduceHP(sumDamage);
		}
		if(sumPoison > 6) {
			if(otherPlayer.getCondition() != Condition.AURA1 
					&& otherPlayer.getCondition() != Condition.AURA2) {
				otherPlayer.setCondition(Condition.POISON1);
			}
		}
		turnPlayer.reduceMana(manaPoison);
	}
	/**
	 * Special attack. Cures condition and gives status boosters.
	 * @param turnPlayer Status of turn player.
	 * @param otherPlayer Status of off-turn player.
	 */
	private void aura(Status turnPlayer) {
		// Roll 4 dice.
		// Change current condition to Aura and apply status boosters
		// if first die is 4 or more.
		// Add second die as attack booster.
		// Add third die as defense booster.
		// No mana cost if fourth die is 4 or more.
		int numRoll = 4;
		lastRoll = DiceRoll.roll(numRoll);
		if(lastRoll[0] > 3) {
			turnPlayer.setCondition(Condition.AURA1);
			turnPlayer.setAtk(lastRoll[1]);
			turnPlayer.setDef(lastRoll[2]);
		}
		if(lastRoll[3] < 4) {
			turnPlayer.reduceMana(manaAura);
		}
	}
	/**
	 * Special attack. Increases current mana.
	 * @param turnPlayer Status of turn player.
	 */
	private void charge(Status turnPlayer) {
		// Roll 4 dice. Increase mana by 50% of sum of roll, rounding up.
		int numRoll = 4;
		lastRoll = DiceRoll.roll(numRoll);
		int sumBoost = 0;
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			sumBoost += lastRoll[rollCount];
		}
		sumBoost = (int) (sumBoost * 0.5 + 0.5);
		turnPlayer.increaseMana(sumBoost);
		if(turnPlayer.getMana() > Status.getMaxMana()) {
			turnPlayer.setMana(Status.getMaxMana());
		}
	}
}

