package hotgammon.gui;

import frs.hotgammon.view.Convert;
import hotgammon.Game;
import hotgammon.Location;
import minidraw.boardgame.Command;

public class MoveCommand implements Command {
	private Game game;
	private Location fromLocation;
	private Location toLocation;
	
	public MoveCommand(Game game){
		this.game = game;
	}
	
	@Override
	public boolean execute() {
		return game.move(fromLocation, toLocation);
	}

	@Override
	public void setFromCoordinates(int fromX, int fromY) {
		fromLocation = Convert.xy2Location(fromX, fromY);
	}

	@Override
	public void setToCoordinates(int toX, int toY) {
		toLocation = Convert.xy2Location(toX, toY);
	}

}
