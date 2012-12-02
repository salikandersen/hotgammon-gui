package hotgammon.log.gui;

import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.gui.HotgammonGui;
import hotgammon.log.HotgammonLogDecorator;
import hotgammon.log.LogStrategy;
import hotgammon.log.entity.HotgammonLog;
import hotgammon.log.entity.MoveLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ReplayPanel extends JFrame {

	private static final long serialVersionUID = 7263245354253455083L;

	public ReplayPanel(final HotgammonGui hotgammonGui) {
		setTitle("Replay Panel");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JButton quitButton = new JButton("Replay");
		quitButton.setBounds(50, 60, 80, 30);
		quitButton.addActionListener(new ReplayActionListener(hotgammonGui));

		this.add(quitButton);
	}

	private class ReplayActionListener implements ActionListener {
		HotgammonGui hotgammonGui;
		public ReplayActionListener(HotgammonGui hotgammonGui) {
			this.hotgammonGui = hotgammonGui;
		}
		public void actionPerformed(ActionEvent event) {
			Game game = new GameImpl(hotgammonGui.getHotgammonFactory());
			hotgammonGui.setGame(game);
			hotgammonGui.resetHotgammonGui(game);
			LogStrategy logStrategy = hotgammonGui.getLogStrategy();
			
			for (HotgammonLog log : logStrategy.getAllHotgammonLogs()) {
				if (log instanceof MoveLog) {
					MoveLog moveLog = (MoveLog) log;
					game.move(moveLog.getFromLocation(), moveLog.getToLocation());
				}
			}
			
			hotgammonGui.setGame(new HotgammonLogDecorator(hotgammonGui.getGame(), logStrategy));
		}
	}

}
