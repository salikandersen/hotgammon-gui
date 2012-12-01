package hotgammon.variants.movevalidation;

import hotgammon.Game;
import hotgammon.Location;
import hotgammon.ValidateMoveStrategy;

public class HandicapValidateMoveStrategy extends ValidateMoveStrategy {

	private final ValidateMoveStrategy simpleMoveStrategy = new SimpleValidateMoveStrategy();
	private final ValidateMoveStrategy backMoveStrategy = new BackgammonValidateMoveStrategy();

	@Override
	public boolean isValidMove(Location from, Location to, Game game) {
		ValidateMoveStrategy currentState;
		switch (game.getPlayerInTurn()) {
		case BLACK:
			currentState = simpleMoveStrategy;
			break;
		case RED:
			currentState = backMoveStrategy;
			break;
		case NONE:
		default:
			throw new IllegalStateException();
		}
		return currentState.isValidMove(from, to, game);

	}

}
