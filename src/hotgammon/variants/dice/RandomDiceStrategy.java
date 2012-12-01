package hotgammon.variants.dice;

import hotgammon.DiceStrategy;
import hotgammon.Game;

public class RandomDiceStrategy implements DiceStrategy {

	@Override
	public int[] throwDice(Game game) {
		return new int[]{getRandomDieValue(), getRandomDieValue()};
	}
	
	private int getRandomDieValue()
	{
		return 1+(int)(Math.random()*6);
	}
}