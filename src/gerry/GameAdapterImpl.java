package gerry;

import hotgammon.Color;
import hotgammon.Game;
import hotgammon.Location;

public class GameAdapterImpl implements GameAdapter{
	
	private Game game;
	
	public GameAdapterImpl(Game game)
	{
		this.game = game;
	}

	public int[] getBoardState() {
		int[] boardState = new int[Location.values().length];
		for(Location location : Location.values())
		{
			int count = game.getCount(location);
			Color player = game.getColor(location);
			if(player == Color.RED)
			{
				count = count * -1;
			}
			boardState[location.getIndex()] = count;
		}
		return boardState;
	}

	public int[] getDiceState() {
		return game.diceThrown();
	}

	public void executeMove(Move move) {
		for(int i = 0; i != move.noOfMoves(); i++)
		{
			Location from = Location.getLocation(move.getFrom(i));
			Location to = Location.getLocation(move.getTo(i));
			game.move(from, to);
		}	
	}
}
