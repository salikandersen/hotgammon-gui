package hotgammon;

import java.util.ArrayList;
import java.util.List;

/**
 * Skeleton implementation of HotGammon.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game {
	private ValidateMoveStrategy moveValidateStrategy;
	private WinningStrategy winningStrategy;
	private DiceStrategy diceStrategy;

	private List<Integer> dieValues = new ArrayList<Integer>();
	private Board board;
	private final TurnManager turnManager = new TurnManager();
	private StartingPositionStrategy startingPostionStrategy;
	private int movesLeft;

	private final List<GameObserver> observers = new ArrayList<GameObserver>();

	public GameImpl(HotgammonFactory factory) {
		this.moveValidateStrategy = factory.createValidateMoveStrategy();
		this.winningStrategy = factory.createWinningStrategy();
		this.winningStrategy.setGame(this);
		this.diceStrategy = factory.createDiceStrategy();
		this.startingPostionStrategy = factory.createStartingPositionStrategy();
		newGame();
	}

	public void newGame() {
		board = new Board(startingPostionStrategy);
		turnManager.reset();
	}

	public TurnManager getTurnManager() {
		return turnManager;
	}

	public Board getBoard() {
		return board;
	}

	public void nextTurn() {
		if (isGameOver()) {
			turnManager.setPlayer(Color.NONE);
			return;
		}

		turnManager.changeTurn();
		diceThrown();
		movesLeft = 2;
	}

	public boolean move(Location from, Location to) {
		if (isTurnOver()) {
			return false;
		}

		if (!moveValidateStrategy.isValidMove(from, to, this)) {
			return false;
		}

		moveChecker(from, to);
		update(from, to);
		dieValues.remove((Integer) Math.abs(Location.distance(from, to)));
		movesLeft--;
		return true;
	}

	private void update(Location from, Location to) {
		for (GameObserver observer : observers) {
			observer.checkerMove(from, to);
		}

	}

	private void moveChecker(Location from, Location to) {
		Color playerInTurn = getPlayerInTurn();
		if (moveValidateStrategy.occupiedByOpponent(to, playerInTurn, board)) {
			switch (playerInTurn) {
			case BLACK:
				board.moveChecker(to, Location.R_BAR, Color.RED);
				update(to, Location.R_BAR);
				break;
			case RED:
				board.moveChecker(to, Location.B_BAR, Color.BLACK);
				update(to, Location.B_BAR);
				break;
			default:
				throw new IllegalStateException("should never happen");
			}
		}
		board.moveChecker(from, to, playerInTurn);
	}

	private boolean isTurnOver() {
		return movesLeft == 0;
	}

	public Color getPlayerInTurn() {
		return turnManager.getPlayerInTurn();
	}

	public int getNumberOfMovesLeft() {
		return movesLeft;
	}

	public int[] diceThrown() {
		int[] diceThrown;
		if (!isGameStartet()) {
			diceThrown = new int[] { 0, 0 };
		} else {
			diceThrown = diceStrategy.throwDice(this);
		}
		
		dieValues = new ArrayList<Integer>();

		for (Integer dieValue : diceThrown) {
			dieValues.add(dieValue);
		}
		
		update(diceThrown);
		return diceThrown;
	}

	private void update(int[] diceThrown) {
		for (GameObserver observer : observers) {
			observer.diceRolled(diceThrown);
		}

	}

	private boolean isGameStartet() {
		return !getPlayerInTurn().equals(Color.NONE);
	}

	public int[] diceValuesLeft() {
		int[] dieValuesArray = new int[dieValues.size()];
		for (int i = 0; i != dieValues.size(); i++) {
			dieValuesArray[i] = dieValues.get(i);
		}
		return dieValuesArray;
	}

	public Color winner() {
		return winningStrategy.getWinner();
	}

	private boolean isGameOver() {
		return winningStrategy.getWinner() != Color.NONE;
	}

	public Color getColor(Location location) {
		return board.getColor(location);
	}

	public int getCount(Location location) {
		return board.getCount(location);
	}

	@Override
	public int getTurnCount() {
		return turnManager.getTurnCount();
	}

	@Override
	public void addObserver(GameObserver gameObserver) {
		observers.add(gameObserver);
	}

}
