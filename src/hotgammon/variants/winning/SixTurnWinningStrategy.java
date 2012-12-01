package hotgammon.variants.winning;

import hotgammon.Color;
import hotgammon.Game;
import hotgammon.WinningStrategy;

public class SixTurnWinningStrategy implements WinningStrategy {


	private Game game;

	@Override
	public Color getWinner() {
		if (game.getTurnManager().getTurnCount() == 6) { // Violating law of Demeter - talking to a stranger (TurnManager). Vi kan omgøre refaktoreringen af turnManager og board?
			return Color.RED;
		}
		return Color.NONE;
	}


	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
