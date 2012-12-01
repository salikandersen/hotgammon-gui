package hotgammon.variants.factory;

import hotgammon.DiceStrategy;
import hotgammon.HotgammonFactory;
import hotgammon.StartingPositionStrategy;
import hotgammon.ValidateMoveStrategy;
import hotgammon.WinningStrategy;
import hotgammon.variants.dice.RandomDiceStrategy;
import hotgammon.variants.movevalidation.SimpleValidateMoveStrategy;
import hotgammon.variants.startingposition.BackgammonStartingPositionStrategy;
import hotgammon.variants.winning.SixTurnWinningStrategy;

public class EpsilonMonFactory implements HotgammonFactory {

	@Override
	public ValidateMoveStrategy createValidateMoveStrategy() {
		return new SimpleValidateMoveStrategy();
	}

	@Override
	public WinningStrategy createWinningStrategy() {
		return new SixTurnWinningStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new RandomDiceStrategy();
	}

	@Override
	public StartingPositionStrategy createStartingPositionStrategy() {
		return new BackgammonStartingPositionStrategy();
	}

}
