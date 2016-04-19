package sliceAndDice;
/**
 * Robot class. Inherits from Player and implements AI logic for computer
 * player. Generates random username using static method, if name is needed
 * for display to UI.
 * @author Elan Markov
 *
 */
public class Robot extends Player {
	static Data computerData;
	final static String[] title = {"Count", "Viscount", "Lord", "Duke", "Archsage",
			"Baron", "Earl", "Cardinal"};
	final String[] name = {"Richard", "James", "Athos", "John", "Reginald",
			"Achilles", "Darius", "Julius"};
	final String[] suffix = {"the Mighty", "the Wise", "the Valiant", 
			"the Wretched", "the Blighted", "the Lionheart", "the Great",
			"the Pretender"};
	/**
	 * Default constructor for Robot class. No arguments.
	 */
	Robot() {
		
	}
	/**
	 * Initializes static instance of data statistics
	 * that stores all data statistics for all games by computer.
	 */
	static {
		computerData = new Data();
	}
	/**
	 * Randomly generates a username for the computer from the 
	 * @return
	 */
	static String createRandomUsername() {
		String robotName = "";
		int[] roll = DiceRoll.roll(3, 8);
		robotName += title[roll[0] - 1];
		robotName += " " + title[roll[1] - 1];
		robotName += " " + title[roll[2] - 1];
		return robotName;
	}
	/**
	 * Method for getting next move. Calculates next move from status
	 * @param ownStatus Turn player's status
	 * @param oppStatus Opponent's status
	 * @return Next move to be performed by computer.
	 */
	Move getNextMove(Status ownStatus, Status oppStatus) {
		return Move.ATTACK;
	}
	/**
	 * Getter method for user's data statistics.
	 * @return Data object containing statistics for all games by computer (static Data).
	 */
	Data getPlayerData() {
		return computerData;
	}
}
