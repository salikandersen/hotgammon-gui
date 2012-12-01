package hotgammon.gui;

import hotgammon.Game;
import hotgammon.Location;

import javax.swing.JTextField;

import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.StdViewWithBackground;

public class HotgammonFactory implements Factory{

	private Game game;
	public HotgammonFactory(Game game){
		this.game = game;
	}
	
	@Override
	public Drawing createDrawing(DrawingEditor arg0) {
		return new BoardDrawing<Location>(new HotgammonPieceFactory(game), 
                new HotgammonPositionStrategy(), 
                new HotgammonPropApperanceStrategy(game) );
	}

	@Override
	public DrawingView createDrawingView(DrawingEditor editor) {
		 DrawingView view = 
			      new StdViewWithBackground(editor,  "board");
			    return view;
	}

	@Override
	public JTextField createStatusField(DrawingEditor arg0) {
		// TODO Auto-generated method stub
		return null;
	}


}
