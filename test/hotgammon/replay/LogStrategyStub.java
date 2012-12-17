package hotgammon.replay;

import hotgammon.log.LogStrategy;
import hotgammon.log.entity.HotgammonLog;

import java.util.ArrayList;
import java.util.List;

public class LogStrategyStub implements LogStrategy {

	private List<HotgammonLog> hotgammonLogs = new ArrayList<HotgammonLog>();

	private boolean enabled = true;
	@Override
	public void log(HotgammonLog hotgammonLog) {
		if (enabled) {
			hotgammonLogs.add(hotgammonLog);
		}
	}

	@Override
	public List<HotgammonLog> getAllHotgammonLogs() {
		return hotgammonLogs;
	}

	@Override
	public void logging(boolean enabled) {
		this.enabled = enabled;
		
	}

	@Override
	public void resetLogs() {
		hotgammonLogs = new ArrayList<HotgammonLog>();
	}

}
