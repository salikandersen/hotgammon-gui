package hotgammon.variants.winning;

import hotgammon.Board;
import hotgammon.Color;
import hotgammon.Game;
import hotgammon.Location;
import hotgammon.WinningStrategy;

public class BackgammonWinningStrategy implements WinningStrategy {

	
	

	private Game game;

	@Override
	public Color getWinner() {
		Board board = game.getBoard();
		
		if (board.getCount(Location.B_BEAR_OFF) == 15 ) {
			return Color.BLACK;
		}
		
		if (board.getCount(Location.R_BEAR_OFF) == 15 ) {
			return Color.RED;
		}
		
		return Color.NONE;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}
	
}
