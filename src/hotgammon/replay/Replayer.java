package hotgammon.replay;

import hotgammon.DiceStrategy;
import hotgammon.Game;
import hotgammon.log.LogStrategy;
import hotgammon.log.entity.DiceThrownLog;
import hotgammon.log.entity.HotgammonLog;
import hotgammon.log.entity.MoveLog;
import hotgammon.variants.dice.DiceStrategyStub;

public class Replayer {
	
	
	private Game game;
	private LogStrategy logStrategy;

	public Replayer(Game game, LogStrategy logStrategy) {
		this.game = game;
		this.logStrategy = logStrategy;
	}

	public void replay() {
		for (HotgammonLog log : logStrategy.getAllHotgammonLogs()) {
			if(log instanceof DiceThrownLog){
				DiceThrownLog diceThrownLog = (DiceThrownLog) log;
				DiceStrategy diceStrategy = game.getDiceStrategy();
				game.setDiceStrategy(new DiceStrategyStub(new int[]{diceThrownLog.getDiceOne(), diceThrownLog.getDiceTwo() }));
				game.nextTurn();
				game.setDiceStrategy(diceStrategy);
				System.out.println("Dice rolled [" + game.diceThrown()[0] + "," + game.diceThrown()[1] + "], "+ game.getPlayerInTurn()+" is in turn");
			} else{
				MoveLog moveLog = (MoveLog)log;
				game.move(moveLog.getFromLocation(), moveLog.getToLocation());
				System.out.println(game.getPlayerInTurn() + " moved from " + moveLog.getFromLocation() + " to " + moveLog.getToLocation() + ". Numbers of moves left: " + game.getNumberOfMovesLeft());
			}
		}
	}
	

}
