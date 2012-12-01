package hotgammon.variants.movevalidation;

import hotgammon.Board;
import hotgammon.Color;
import hotgammon.Game;
import hotgammon.Location;
import hotgammon.ValidateMoveStrategy;

public class BackgammonValidateMoveStrategy extends ValidateMoveStrategy {

	@Override
	public boolean isValidMove(Location from, Location to, Game game) {
		Color playerInTurn = game.getPlayerInTurn();
		if (!super.isValidMove(from, to, game)) {
			return false;
		}

		if (!isRightDirection(playerInTurn, from, to)) {
			return false;
		}

		if (!distanceMatchesDieValues(game.diceValuesLeft(), from, to)) {
			return false;
		}

		if (!isToOccupiedByMoreThanOneOpponent(to, playerInTurn,
				game.getBoard())) {
			return false;
		}

		if (playerHaveCheckerInBarAndPlayerAreNotTryingToMoveFromTheBar(
				playerInTurn, from, game.getBoard())) {
			return false;
		}

		return true;
	}

	private boolean playerHaveCheckerInBarAndPlayerAreNotTryingToMoveFromTheBar(
			Color player, Location from, Board board) {

		Location location = null;
		switch (player) {
		case BLACK:
			location = Location.B_BAR;
			break;
		case RED:
			location = Location.R_BAR;
			break;
		default:
			assert false;
		}

		if (location.equals(from)) { // Verifying if player is trying to move
										// from BAR.
			return false;
		}

		return board.getCount(location) > 0;
	}

	private boolean isToOccupiedByMoreThanOneOpponent(Location to,
			Color player, Board board) {
		if (!player.equals(board.getColor(to))){
			return !(board.getCount(to) > 1);
		}
		return true; 
	}

	private boolean distanceMatchesDieValues(int[] dieValues, Location from,
			Location to) {
		int dieIndex = -1;
		for (int i = 0; i != dieValues.length; i++) {
			if (Math.abs(Location.distance(from, to)) == dieValues[i]) {
				dieIndex = i;
				break;
			}
		}

		return dieIndex != -1;
	}

	private boolean isRightDirection(Color player, Location from, Location to) {
		switch (player) {
		case BLACK:
			return Location.distance(from, to) > 0;
		case RED:
			return Location.distance(from, to) < 0;
		default:
			throw new IllegalStateException("should never happen");
		}
	}
}
