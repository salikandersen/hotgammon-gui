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
		Map<Location, List<BoardFigure>> pieceMultiMap = 
			      new HashMap<Location, List<BoardFigure>>();
		
		for (Location location : Location.values()) {
			Color color = game.getBoard().getColor(location);
			int count = game.getBoard().getCount(location);
			
			List<BoardFigure> locationList = new ArrayList<BoardFigure>();
			
			for (int i = 0; i < count; i++) {
				switch (color) {
				case BLACK:
					locationList.add(new BoardFigure(HotgammonGuiConstants.BLACKCHECKER, true, new MoveCommand(game)));
					break;
				case RED:
					locationList.add(new BoardFigure(HotgammonGuiConstants.REDCHECKER, true, new MoveCommand(game)));
					break;
				default:
					break;
				}
			}
			
			pieceMultiMap.put(location, locationList);
		}
		
		return pieceMultiMap;
	}

	@Override
	public Map<String, BoardFigure> generatePropMap() {
		BoardFigure die0 = new BoardFigure(HotgammonGuiConstants.DEFAULT_DIE_PICTURE, false, new DieRollCommand(game));
		BoardFigure die1 = new BoardFigure(HotgammonGuiConstants.DEFAULT_DIE_PICTURE, false, new DieRollCommand(game));
		Map<String,BoardFigure> propMap = new HashMap<String,BoardFigure>();
		propMap.put(HotgammonGuiConstants.DIE0_NAME, die0);
		propMap.put(HotgammonGuiConstants.DIE1_NAME, die1);
		return propMap;
	}


}
