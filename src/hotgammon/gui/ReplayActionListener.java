package hotgammon.gui;

import hotgammon.Game;
import hotgammon.log.LogStrategy;
import hotgammon.replay.Replayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayActionListener implements ActionListener {

	private Game game;
	private LogStrategy logStrategy;

	public ReplayActionListener(Game game, LogStrategy logStrategy) {
		this.game = game;
		this.logStrategy = logStrategy;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		HotgammonGui.setupHotgammonGui(game);
		game.newGame();
		logStrategy.logging(false);
		Replayer replayer = new Replayer(game, logStrategy);
		replayer.replay();
		logStrategy.logging(true);
		
	}

}
