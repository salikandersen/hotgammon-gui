package hotgammon.log;

import hotgammon.log.entity.HotgammonLog;

import java.sql.SQLException;
import java.util.List;

public class DirectSqlLogStrategy implements LogStrategy {

	private DirectSqlResources directSqlResources;
	private boolean enabled = true;

	public DirectSqlLogStrategy() {
		directSqlResources = new DirectSqlResources();
	}
	
	@Override
	public void log(HotgammonLog hotgammonLog) {
		try {
			if (enabled)
				directSqlResources.log(hotgammonLog);
		} catch (SQLException e) {
			throw new RuntimeException("Something went wrong", e);
		}

	}

	@Override
	public List<HotgammonLog> getAllHotgammonLogs() {
		try {
			return directSqlResources.getHotgammonLogs();
		} catch (SQLException e) {
			throw new RuntimeException("Something went wrong", e);
		}
	}

	@Override
	public void logging(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void resetLogs() {
		try { 
			directSqlResources.deleteAllLogs();
		} catch (SQLException e) {
			throw new RuntimeException("Something went wrong", e);
		}
	}

}
