package hotgammon.log;

import hotgammon.Board;
import hotgammon.Color;
import hotgammon.DiceStrategy;
import hotgammon.Game;
import hotgammon.GameObserver;
import hotgammon.Location;
import hotgammon.TurnManager;
import hotgammon.log.entity.DiceThrownLog;
import hotgammon.log.entity.MoveLog;

public class HotgammonLogDecorator implements Game  {

	private Game game;
	private LogStrategy logStrategy;

	public HotgammonLogDecorator(Game game, LogStrategy logStrategy){
		this.game = game;
		this.logStrategy = logStrategy;
	}


	public LogStrategy getLogStrategy() {
		return logStrategy;
	}

	@Override
	public void newGame() {
		game.newGame();
	}

	@Override
	public void nextTurn() {
		game.nextTurn();

		int[] diceThrown = diceThrown();

		DiceThrownLog diceThrownLog = new DiceThrownLog();
		diceThrownLog.setPlayer(game.getPlayerInTurn());
		diceThrownLog.setDiceOne(diceThrown[0]);
		diceThrownLog.setDiceTwo(diceThrown[1]);
		logStrategy.log(diceThrownLog);
	}

	@Override
	public boolean move(Location from, Location to) {
		boolean result = game.move(from, to);

		MoveLog moveLog = new MoveLog();
		moveLog.setFromLocation(from);
		moveLog.setToLocation(to);
		moveLog.setPlayer(game.getPlayerInTurn());
		moveLog.setResultOfAction(result);

		logStrategy.log(moveLog);
		return result;
	}

	@Override
	public Color getPlayerInTurn() {
		return game.getPlayerInTurn();
	}

	@Override
	public int getNumberOfMovesLeft() {
		return game.getNumberOfMovesLeft();
	}

	@Override
	public int[] diceThrown() {
		return game.diceThrown();
	}

	@Override
	public int[] diceValuesLeft() {
		return game.diceValuesLeft();
	}

	@Override
	public Color winner() {
		return game.winner();
	}

	@Override
	public Color getColor(Location location) {
		return game.getColor(location);
	}

	@Override
	public int getCount(Location location) {
		return game.getCount(location);
	}

	@Override
	public int getTurnCount() {
		return game.getTurnCount();
	}

	@Override
	public Board getBoard() {
		return game.getBoard();
	}

	@Override
	public TurnManager getTurnManager() {
		return game.getTurnManager();
	}

	@Override
	public void addObserver(GameObserver gameObserver) {
		game.addObserver(gameObserver);
	}

	@Override
	public DiceStrategy getDiceStrategy() {
		return game.getDiceStrategy();
	}

	@Override
	public void setDiceStrategy(DiceStrategy diceStrategy) {
		game.setDiceStrategy(diceStrategy);
	}
}
