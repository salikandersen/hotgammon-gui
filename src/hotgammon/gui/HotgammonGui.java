package hotgammon.gui;

import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.log.HibernateJPALogStrategy;
import hotgammon.log.HotgammonLogDecorator;
import hotgammon.log.LogStrategy;
import hotgammon.log.gui.ReplayPanel;
import hotgammon.variants.factory.SemiMonFactory;
import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class HotgammonGui {

	private Game game;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new HotgammonGui();
		
	    
	    
	}
	
	public void setupHotgammonGui() {
		LogStrategy logStrategy = new HibernateJPALogStrategy();
		
		game = new HotgammonLogDecorator(new GameImpl(new SemiMonFactory()), logStrategy);
		game.newGame();
		game.nextTurn();
		
		DrawingEditor editor = 
			      new MiniDrawApplication("", new HotgammonFactory(game));
		
		editor.open();
		
		editor.setTool( new BoardActionTool(editor) );
		BoardDrawing<Location> drawing = (BoardDrawing<Location>) editor.drawing();
	    game.addObserver(new GameObserverAdapter(drawing));
	}
	
	public void setGame(Game g) {
		game = g;
	}

}
