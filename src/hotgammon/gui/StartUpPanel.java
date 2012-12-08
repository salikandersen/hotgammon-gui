package hotgammon.gui;

import hotgammon.GameImpl;
import hotgammon.log.HibernateJPALogStrategy;
import hotgammon.log.HotgammonLogDecorator;
import hotgammon.log.LogStrategy;
import hotgammon.variants.factory.SemiMonFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartUpPanel extends JFrame {

	private static final long serialVersionUID = 7263245354253455083L;
	private HotgammonLogDecorator game;
	private LogStrategy logStrategy;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StartUpPanel startUpPanel = new StartUpPanel();
		startUpPanel.setVisible(true);
		HotgammonGui.setStartUpPanel(startUpPanel);
	}
	
	public StartUpPanel() {
		JPanel panel = new JPanel();
		setTitle("Replay Panel");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		logStrategy = new HibernateJPALogStrategy();
		game = new HotgammonLogDecorator(new GameImpl(new SemiMonFactory()), logStrategy);
		
		JButton newGamButton = new JButton("New Game");
		newGamButton.setBounds(50, 60, 80, 30);
		newGamButton.addActionListener(new NewGameActionListener(game, logStrategy));
		
		JButton replayButton = new JButton("Replay");
		replayButton.setBounds(newGamButton.getX()+50, 60, 80, 30);
		replayButton.addActionListener(new ReplayActionListener(game, logStrategy));

		panel.setSize(300, 200);
		add(panel);
		panel.add(newGamButton);
		panel.add(replayButton);
		
	}
}
