package hotgammon.gui;

import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.SemiMonFactory;
import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class Hotgammon {
	public static void main(String[] args) {
		
		Game game = new GameImpl(new SemiMonFactory());
		game.newGame();
		game.nextTurn();
		
		DrawingEditor editor = 
			      new MiniDrawApplication("", new HotgammonFactory(game));
		
		editor.open();
		
		editor.setTool( new BoardActionTool(editor) );
		BoardDrawing<Location> drawing = (BoardDrawing<Location>) editor.drawing();
	    game.addObserver(new GameObserverAdapter(drawing));
	}
}
