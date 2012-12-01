package hotgammon.variants.factory;

import hotgammon.DiceStrategy;
import hotgammon.HotgammonFactory;
import hotgammon.StartingPositionStrategy;
import hotgammon.ValidateMoveStrategy;
import hotgammon.WinningStrategy;
import hotgammon.variants.dice.RandomDiceStrategy;
import hotgammon.variants.movevalidation.BackgammonValidateMoveStrategy;
import hotgammon.variants.startingposition.BackgammonStartingPositionStrategy;
import hotgammon.variants.winning.BackgammonWinningStrategy;

public class SemiMonFactory implements HotgammonFactory {

	@Override
	public ValidateMoveStrategy createValidateMoveStrategy() {
		return new BackgammonValidateMoveStrategy();
	}

	@Override
	public WinningStrategy createWinningStrategy() {
		return new BackgammonWinningStrategy();
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
