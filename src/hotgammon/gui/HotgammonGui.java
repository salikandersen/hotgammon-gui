package hotgammon.gui;

import hotgammon.Game;
import hotgammon.Location;
import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;


public class HotgammonGui {
	private static StartUpPanel startUpPanel;

	public static void setupHotgammonGui(Game game) {
		DrawingEditor editor = 
			      new MiniDrawApplication("", new HotgammonFactory(game));
		
		editor.open();
		editor.setTool( new BoardActionTool(editor) );
		BoardDrawing<Location> drawing = (BoardDrawing<Location>) editor.drawing();
	    
		game.addObserver(new GameObserverAdapter(drawing));
		startUpPanel.dispose();
	}

	public static void setStartUpPanel(StartUpPanel startUpPanel) {
		HotgammonGui.startUpPanel = startUpPanel;
		
	}
	
}
