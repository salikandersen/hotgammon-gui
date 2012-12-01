package hotgammon;

public interface HotgammonFactory {
	public ValidateMoveStrategy createValidateMoveStrategy();
	public WinningStrategy createWinningStrategy();
	public DiceStrategy createDiceStrategy(); 
	public StartingPositionStrategy createStartingPositionStrategy();
}
