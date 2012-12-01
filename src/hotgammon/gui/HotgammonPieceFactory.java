package hotgammon.gui;

import hotgammon.Color;
import hotgammon.Game;
import hotgammon.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.FigureFactory;

public class HotgammonPieceFactory  implements FigureFactory<Location>{

	private Game game;
	public HotgammonPieceFactory(Game game){
		this.game = game;
	}
	
	@Override
	public Map<Location, List<BoardFigure>> generatePieceMultiMap() {
		Map<Location, List<BoardFigure>> m = 
			      new HashMap<Location, List<BoardFigure>>();
		
		for (Location location : Location.values()) {
			Color color = game.getBoard().getColor(location);
			int count = game.getBoard().getCount(location);
			
			List<BoardFigure> locationList = new ArrayList<BoardFigure>();
			
			for (int i = 0; i < count; i++) {
				switch (color) {
				case BLACK:
					locationList.add(new BoardFigure("blackchecker", true, new MoveCommand(game)));
					break;
				case RED:
					locationList.add(new BoardFigure("redchecker", true, new MoveCommand(game)));
					break;
				default:
					break;
				}
			}
			
			m.put(location, locationList);
		}
		
		return m;
	}

	@Override
	public Map<String, BoardFigure> generatePropMap() {
		BoardFigure die0 = new BoardFigure("die0", false, new DieRollCommand(game));
		BoardFigure die1 = new BoardFigure("die0", false, new DieRollCommand(game));
		Map<String,BoardFigure> m = new HashMap<String,BoardFigure>();
		m.put("die0", die0);
		m.put("die1", die1);
		return m;
	}


}
