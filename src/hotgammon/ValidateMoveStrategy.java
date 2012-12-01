package hotgammon;


public abstract class ValidateMoveStrategy {
	
	protected boolean isEmpty(Location location, Board board) {
		return board.isEmpty(location);
	}
	
	protected boolean occupiedByOpponent(Location location, Color player, Board board) {
			return !isEmpty(location, board) && !player.equals(board.getColor(location));
	}
	
	public boolean isValidMove(Location from, Location to, Game game){
		if (isEmpty(from, game.getBoard())) {
			return false;
		}

		if (occupiedByOpponent(from, game.getPlayerInTurn(), game.getBoard())) {
			return false;
		}
		
		return true;
	}
}
