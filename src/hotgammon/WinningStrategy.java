	package hotgammon;

public interface WinningStrategy {
	Color getWinner();

	void setGame(Game game);
}
