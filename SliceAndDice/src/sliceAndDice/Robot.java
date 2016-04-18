package sliceAndDice;

public class Robot extends Player {
	static Data computerData;
	final static String[] title = {"Count", "Viscount", "Lord", "Duke", "Archsage",
			"Baron", "Earl", "Cardinal"};
	final String[] name = {"Richard", "James", "Athos", "John", "Reginald",
			"Achilles", "Darius", "Julius"};
	final String[] suffix = {"the Mighty", "the Wise", "the Valiant", 
			"the Wretched", "the Blighted", "the Lionheart", "the Great",
			"the Pretender"};
	Robot() {
		
	}
	static String createRandomUsername() {
		String robotName = "";
		int[] roll = DiceRoll.roll(3, 8);
		robotName += title[roll[0] - 1];
		robotName += " " + title[roll[1] - 1];
		robotName += " " + title[roll[2] - 1];
		return robotName;
	}
	Move getNextMove(Status ownStatus, Status oppStatus) {
		return Move.ATTACK;
	}

	Data getPlayerData() {
		return computerData;
	}
}
