package hotgammon;

import hotgammon.variants.dice.DiceStrategyStub;

import java.io.IOException;
import java.io.OutputStream;

public class TranscriptDecorator implements Game {

	private final Game game;
	private final OutputStream output;

	public TranscriptDecorator(Game game, OutputStream output) {
		this.game = game;
		this.output = output;
	}

	@Override
	public void newGame() {
		try {
			output.write("New game started".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		game.newGame();
	}

	@Override
	public void nextTurn() {
		game.nextTurn();
		try {
			output.write(("Now it is " + game.getPlayerInTurn() + "'s turn")
					.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean move(Location from, Location to) {
		if (game.move(from, to)) {
			try {
				output.write((game.getPlayerInTurn() + " moves (" + from + ","
						+ to + ")").getBytes());
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			output.write((game.getPlayerInTurn() + " fails to move (" + from
					+ "," + to + ")").getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return game.move(from, to);
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
		int[] dieValues = game.diceThrown();
		try {
			output.write(("Dice rolled: " + dieValues[0] + "-" + dieValues[1])
					.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dieValues;
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
