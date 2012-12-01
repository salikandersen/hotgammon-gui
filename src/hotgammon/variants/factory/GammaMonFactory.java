package hotgammon.variants.factory;

import hotgammon.DiceStrategy;
import hotgammon.HotgammonFactory;
import hotgammon.StartingPositionStrategy;
import hotgammon.ValidateMoveStrategy;
import hotgammon.WinningStrategy;
import hotgammon.variants.dice.SequentialDiceStrategy;
import hotgammon.variants.movevalidation.SimpleValidateMoveStrategy;
import hotgammon.variants.startingposition.BackgammonStartingPositionStrategy;
import hotgammon.variants.winning.BackgammonWinningStrategy;

public class GammaMonFactory implements HotgammonFactory {

	@Override
	public ValidateMoveStrategy createValidateMoveStrategy() {
		return new SimpleValidateMoveStrategy();
	}

	@Override
	public WinningStrategy createWinningStrategy() {
		return new BackgammonWinningStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new SequentialDiceStrategy();
	}

	@Override
	public StartingPositionStrategy createStartingPositionStrategy() {
		return new BackgammonStartingPositionStrategy();
	}

}
