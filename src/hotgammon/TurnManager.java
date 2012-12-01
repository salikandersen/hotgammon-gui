package hotgammon;

public class TurnManager {

	private Color player = Color.NONE;
	private int turnCount;
	private static final Color[] COLOR_TURNS = { Color.BLACK, Color.RED };
	
	
	public void reset() {
		player = Color.NONE;
		turnCount = 0;
	}


	public void setPlayer(Color player) {
		this.player = player;
		
	}
	
	public void changeTurn() {
		player = COLOR_TURNS[turnCount % 2];
		turnCount++;
	}


	public Color getPlayerInTurn() {
		return player;
	}


	public int getTurnCount() {
		return turnCount;
	}
	
	
	
}
