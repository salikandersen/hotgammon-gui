package hotgammon.variants.dice;


import hotgammon.DiceStrategy;
import hotgammon.Game;

public class DiceStrategyStub implements DiceStrategy {
	
	private int[] diceValues;
	
	public DiceStrategyStub(int[] diceValues){
		this.diceValues = diceValues;
	}
	
	@Override
	public int[] throwDice(Game game) {
		return diceValues;
	}

}
