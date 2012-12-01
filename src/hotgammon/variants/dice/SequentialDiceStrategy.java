package hotgammon.variants.dice;

import hotgammon.DiceStrategy;
import hotgammon.Game;

public class SequentialDiceStrategy implements DiceStrategy {	
	private static final int[][] DICE_VALUES = new int[][] { { 1, 2 },
		{ 3, 4 }, { 5, 6 } };
	
	@Override
	public int[] throwDice(Game game) {		
		return DICE_VALUES[(game.getTurnCount() - 1) % 3];
	}

}
