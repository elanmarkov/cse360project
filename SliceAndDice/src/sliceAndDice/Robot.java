package sliceAndDice;
/**
 * Robot class. Inherits from Player and implements AI logic for computer
 * player. Generates random username using static method, if name is needed
 * for display to UI.
 * @author Elan Markov
 *
 */
public class Robot extends Player {
	private static Data computerData;
	private final static String[] title = {"Count", "Viscount", "Lord", "Duke", "Archsage",
			"Baron", "Earl", "Cardinal"};
	private final static String[] name = {"Richard", "James", "Athos", "John", "Reginald",
			"Achilles", "Darius", "Julius"};
	private final static String[] suffix = {"the Mighty", "the Wise", "the Valiant", 
			"the Wretched", "the Blighted", "the Lionheart", "the Great",
			"the Pretender"};
	private final static int robotID = -1337;
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
	 * Randomly generates a username for the computer from the name list.
	 * @return Randomly generated name, String.
	 */
	static String createRandomUsername() {
		String robotName = "";
		int[] roll = DiceRoll.roll(3, 8);
		robotName += title[roll[0] - 1];
		robotName += " " + name[roll[1] - 1];
		robotName += " " + suffix[roll[2] - 1];
		return robotName;
	}
	/**
	 * Method for getting next move. Calculates next move from status
	 * @param ownStatus Turn player's status
	 * @param oppStatus Opponent's status
	 * @return Next move to be performed by computer.
	 */
	Move getNextMove(Status ownStatus, Status oppStatus) {
		Move nextMove = Move.ATTACK;
		if(ownStatus.getHitPts() < 30 && ownStatus.getFoodCount() > 0) {
			nextMove = Move.FOOD;
		}
		else if((ownStatus.getCondition() == Condition.POISON1 ||
				ownStatus.getCondition() == Condition.POISON2 ||
				ownStatus.getCondition() == Condition.POISON3 ||
				ownStatus.getCondition() == Condition.POISON4 ||
				ownStatus.getCondition() == Condition.POISON5) &&
				ownStatus.getMana() >= Turn.getManaAura()) {
			nextMove = Move.AURA;
		}
		else if(ownStatus.getMana() >= Turn.getManaDouble() && oppStatus.getCondition() == Condition.NONE) {
			nextMove = Move.POISON;
		}
		else if(ownStatus.getMana() >= Turn.getManaDouble() && oppStatus.getDef() == 0) {
			nextMove = Move.DOUBLEATK;
		}
		else if(ownStatus.getMana() >= Turn.getManaFreeze() && oppStatus.getHitPts() < 25) {
			nextMove = Move.FREEZE;
		}
		else if(ownStatus.getHitPts() - oppStatus.getHitPts() > 35) {
			nextMove = Move.CHARGE;
		}
		else {
			nextMove = Move.ATTACK;
		}
		
		if(Turn.moveIsLegal(ownStatus, nextMove) != IllegalMove.NONE) {
			throw new IllegalStateException("Error: Computer logic allowed for an illegal move");
		}
		return nextMove;
	}
	/**
	 * Getter method for user's data statistics.
	 * @return Data object containing statistics for all games by computer (static Data).
	 */
	Data getPlayerData() {
		return computerData;
	}
	/**
	 * Get the robot's ID
	 * @return robot's ID
	 */
	int getID() {
		return robotID;
	}
	static int getRobotID() {
		return robotID;
	}
}