package hotgammon.gui;

import hotgammon.Game;
import minidraw.boardgame.PropAppearanceStrategy;

public class HotgammonPropApperanceStrategy implements PropAppearanceStrategy{
	
	private Game game;

	public HotgammonPropApperanceStrategy(Game game){
		this.game = game;
	}
	
	@Override
	public String calculateImageNameForPropWithKey(String key) {
		switch (key) {
		case "die0":
			return "die"+game.diceValuesLeft()[0];
		case "die1":
			return "die"+game.diceValuesLeft()[1];
		default:
			return null;
		}
		
	}

}
