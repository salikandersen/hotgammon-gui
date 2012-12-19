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
		case HotgammonGuiConstants.DIE0_NAME:
			return "die"+game.diceValuesLeft()[0];
		case HotgammonGuiConstants.DIE1_NAME:
			return "die"+game.diceValuesLeft()[1];
		default:
			return null;
		}
		
	}

}
