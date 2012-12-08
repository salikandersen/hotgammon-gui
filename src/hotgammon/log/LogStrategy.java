package hotgammon.log;

import java.util.List;

import hotgammon.log.entity.HotgammonLog;

public interface LogStrategy {
	void log(HotgammonLog hotgammonLog);
	List<HotgammonLog> getAllHotgammonLogs();
	void logging(boolean enabled);
	void resetLogs();
}
