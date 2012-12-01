package hotgammon.gui;

import minidraw.boardgame.BoardGameObserver;
import hotgammon.GameObserver;
import hotgammon.Location;

public class GameObserverAdapter implements GameObserver {
	BoardGameObserver<Location> boardGameObserver;
	public GameObserverAdapter(BoardGameObserver<Location> boardGameObserver){
		this.boardGameObserver = boardGameObserver;
	}
	@Override
	public void checkerMove(Location from, Location to) {
		boardGameObserver.pieceMovedEvent(from, to);
		
	}
	@Override
	public void diceRolled(int[] values) {
		boardGameObserver.propChangeEvent("die0");
		boardGameObserver.propChangeEvent("die1");
	}
	
	
}
