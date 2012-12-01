package gerry;

public interface GameAdapter {
	
	public int[] getBoardState();

	public int[] getDiceState();

	public void executeMove(Move move);	
}
