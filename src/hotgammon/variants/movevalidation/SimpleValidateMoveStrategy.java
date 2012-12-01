package hotgammon.variants.movevalidation;

import hotgammon.Game;
import hotgammon.Location;
import hotgammon.ValidateMoveStrategy;


public class SimpleValidateMoveStrategy extends ValidateMoveStrategy {
	
	@Override
	public boolean isValidMove(Location from, Location to, Game game) {
		if (!super.isValidMove(from, to, game)){
			return false;
		}

		if (occupiedByOpponent(to, game.getPlayerInTurn(), game.getBoard())) {
			return false;		
		}
		
		return true;
	}

}
