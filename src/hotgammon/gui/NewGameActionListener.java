package hotgammon.gui;

import hotgammon.Game;
import hotgammon.log.LogStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameActionListener  implements ActionListener{

	private Game game;
	private LogStrategy logStrategy;
	public NewGameActionListener(Game game, LogStrategy logStrategy) {
		this.game = game;
		this.logStrategy = logStrategy;
		
	}
	public void actionPerformed(ActionEvent event) {
		logStrategy.resetLogs();
		HotgammonGui.setupHotgammonGui(game);
		game.newGame();
		game.nextTurn();
	}
}
