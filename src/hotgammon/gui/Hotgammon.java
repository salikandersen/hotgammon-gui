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

	/**
	 * @param args
	 */
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
	    
	    
	    game.nextTurn();
	    int[] values = game.diceThrown();
	    
	    for (int i : values) {
	    	boolean moveValid =false;
	    	switch(i) {
	    	case 1:
	    		moveValid = game.move(Location.R6, Location.R5);
	    		break;
	    	case 2:
	    		moveValid = game.move(Location.R6, Location.R4);
	    		break;
	    	case 3:
	    		moveValid = game.move(Location.R6, Location.R3);
	    		break;
	    	case 4:
	    		moveValid = game.move(Location.R6, Location.R2);
	    		break;
	    	case 5:
	    		moveValid = game.move(Location.R8, Location.R3);
	    		break;
	    	case 6:
	    		moveValid = game.move(Location.R8, Location.R2);
	    		break;
    		default:
    			throw new RuntimeException("smth");
	    	}
	    	
	    	System.out.println("Move valid? "+moveValid);
		}
	}

}
