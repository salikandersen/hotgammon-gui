package hotgammon;

public class GameObserverStub implements GameObserver {

	public Location from;
	public Location to;
	public int[] values;

	@Override
	public void checkerMove(Location from, Location to) {
		this.from = from;
		this.to = to;

	}

	@Override
	public void diceRolled(int[] values) {
		this.values = values;

	}

}
