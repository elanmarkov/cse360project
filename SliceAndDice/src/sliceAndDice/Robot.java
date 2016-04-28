package sliceAndDice;
/**
 * Robot class. Inherits from Player and implements AI logic for computer
 * player. Generates random username using static method, if name is needed
 * for display to UI.
 * @author Elan Markov
 *
 */
enum StrategyShift {SHIFT, STANDBY1, STANDBY2};
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
	private StrategyShift strategyTimer = StrategyShift.SHIFT;
	private int strategyChoice;
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
		// switch strategies once every three runs.
		switch(strategyTimer) {
		case SHIFT:
			strategyChoice = 1 + DiceRoll.roll(1,4)[0];
			strategyTimer = StrategyShift.STANDBY1;
			break;
		case STANDBY1:
			strategyTimer = StrategyShift.STANDBY2;
			break;
		case STANDBY2:
			strategyTimer = StrategyShift.SHIFT;
			break;
		default:
			break;
		}
		
		//Randomize which strategy the computer uses
		switch(strategyChoice) {
		case 1: {
			// First strategy: Defensive poison-double strategy
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
				else if(ownStatus.getMana() >= Turn.getManaDouble() && 
						ownStatus.getMana() >= Turn.getManaPoison() &&
						oppStatus.getCondition() == Condition.NONE) {
					nextMove = Move.POISON;
				}
				else if(ownStatus.getMana() >= Turn.getManaDouble() && 
						oppStatus.getDef() == 0) {
					nextMove = Move.DOUBLEATK;
				}
				else if(ownStatus.getMana() >= Turn.getManaFreeze() && 
						oppStatus.getHitPts() < 25) {
					nextMove = Move.FREEZE;
				}
				else if(ownStatus.getHitPts() - oppStatus.getHitPts() > 35) {
					nextMove = Move.CHARGE;
				}
				else {
					nextMove = Move.ATTACK;
				}
			}
			break;
		case 2: {
			// Strategy 2: semi-defensive/double attack focus
				if(ownStatus.getHitPts() < 30 && ownStatus.getFoodCount() > 0) {
					nextMove = Move.FOOD;
				}
				else if((ownStatus.getCondition() == Condition.POISON4 ||
						ownStatus.getCondition() == Condition.POISON5) &&
						ownStatus.getMana() >= Turn.getManaAura()) {
					// heal only after damage has become significant
					nextMove = Move.AURA;
				}
				else if(ownStatus.getMana() >= Turn.getManaPoison() && 
						ownStatus.getMana() >= Turn.getManaDouble() &&
						oppStatus.getDef() <= 2) {
					nextMove = Move.DOUBLEATK;
				}
				else if(ownStatus.getMana() >= Turn.getManaPoison() && 
						oppStatus.getCondition() == Condition.NONE) {
					nextMove = Move.POISON;
				}
				else if(ownStatus.getMana() >= Turn.getManaFreeze() + Turn.getManaDouble() && 
						oppStatus.getHitPts() < 25) {
					nextMove = Move.FREEZE;
				}
				else if(ownStatus.getHitPts() - oppStatus.getHitPts() > 35) {
					nextMove = Move.CHARGE;
				}
				else {
					nextMove = Move.ATTACK;
				}
			}
			break;
		case 3: {
			// Strategy 3: Aggressive double-freeze strategy
				if(oppStatus.getHitPts() + oppStatus.getDef() < 30 && 
						ownStatus.getMana() >= Turn.getManaDouble() + Turn.getManaFreeze() &&
						oppStatus.getCondition() == Condition.NONE) {
					nextMove = Move.FREEZE;
				}
				else if(oppStatus.getHitPts() + 2 * oppStatus.getDef() < 25 && 
						ownStatus.getMana() >= Turn.getManaDouble()) {
					nextMove = Move.DOUBLEATK;
				}
				else if(ownStatus.getHitPts() < 30 && ownStatus.getFoodCount() > 0) {
					nextMove = Move.FOOD;
				}
				else if((ownStatus.getCondition() == Condition.POISON4 ||
						ownStatus.getCondition() == Condition.POISON5) &&
						ownStatus.getMana() >= Turn.getManaAura()) {
					nextMove = Move.AURA;
				}
				else if(ownStatus.getMana() >= Turn.getManaDouble() && 
						ownStatus.getMana() >= Turn.getManaPoison() &&
						oppStatus.getCondition() == Condition.NONE) {
					nextMove = Move.POISON;
				}
				else if(ownStatus.getMana() >= Turn.getManaDouble() && 
						oppStatus.getDef() == 0) {
					nextMove = Move.DOUBLEATK;
				}
				else if(ownStatus.getMana() >= Turn.getManaFreeze() && 
						oppStatus.getHitPts() < 25) {
					nextMove = Move.FREEZE;
				}
				else if(ownStatus.getHitPts() - oppStatus.getHitPts() > 35) {
					nextMove = Move.CHARGE;
				}
				else {
					nextMove = Move.ATTACK;
				}
			}
			break;
		case 4: {
				// Strategy 4: Aggressive poison-double strategy
				if(oppStatus.getHitPts() + 2 * oppStatus.getDef() < 25 && 
						ownStatus.getMana() >= Turn.getManaPoison()) {
					nextMove = Move.DOUBLEATK;
				}
				else if(ownStatus.getHitPts() < 30 && ownStatus.getFoodCount() > 0) {
					nextMove = Move.FOOD;
				}
				else if((ownStatus.getCondition() == Condition.POISON4 ||
						ownStatus.getCondition() == Condition.POISON5) &&
						ownStatus.getMana() >= Turn.getManaAura()) {
					nextMove = Move.AURA;
				}
				else if(ownStatus.getMana() >= Turn.getManaDouble() && 
						ownStatus.getMana() >= Turn.getManaPoison() &&
						oppStatus.getCondition() == Condition.NONE) {
					nextMove = Move.POISON;
				}
				else if(ownStatus.getMana() >= Turn.getManaDouble() && 
						oppStatus.getDef() == 0) {
					nextMove = Move.DOUBLEATK;
				}
				else if(ownStatus.getHitPts() - oppStatus.getHitPts() > 35) {
					nextMove = Move.CHARGE;
				}
				else {
					nextMove = Move.ATTACK;
				}
			}			
			break;
		default:
			throw new IllegalStateException("Error: no strategy was selected.");	
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