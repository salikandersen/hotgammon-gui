package hotgammon.gui;

import hotgammon.Game;
import minidraw.boardgame.Command;

public class DieRollCommand implements Command {
	private Game game;

	public DieRollCommand(Game game){
		this.game = game;
	}
	
	@Override
	public boolean execute() {
		game.nextTurn();
		return true;
	}

	@Override
	public void setFromCoordinates(int arg0, int arg1) {
		//not implemented because it should not be moved
	}

	@Override
	public void setToCoordinates(int arg0, int arg1) {
		//not implemented because it should not be moved		
	}

}
