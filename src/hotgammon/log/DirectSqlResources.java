package hotgammon.log;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.log.entity.DiceThrownLog;
import hotgammon.log.entity.HotgammonLog;
import hotgammon.log.entity.MoveLog;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mysql.jdbc.Connection;

public class DirectSqlResources {
	String dbUrl = "jdbc:mysql://localhost:3306/test";
	String dbClass = "com.mysql.jdbc.Driver";
	String user = "admin";
	String password= "password";
	Connection connection;
	
	private void connect() throws SQLException {
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = (Connection) DriverManager.getConnection( dbUrl, user, password );
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void log(HotgammonLog log) throws SQLException {
		connect();
		PreparedStatement st;
		try {
			st = connection.prepareStatement("INSERT INTO hotgammonlog (id, created, player) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, log.getId());
			st.setTimestamp(2, new Timestamp(log.getCreated().getTime()));
			st.setString(3, log.getPlayer().name());
			st.executeUpdate();
			setIdAfterInsert(st, log);
			st.close();
						
			if (log instanceof MoveLog) {
				insertMoveLog((MoveLog) log);
			} else if (log instanceof DiceThrownLog) {
				insertDiceThrownLog((DiceThrownLog) log);
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw new RuntimeException("something went wrong", e);
		} finally {
			connection.close();
		}
	}

	private void setIdAfterInsert(PreparedStatement st, HotgammonLog log) throws SQLException {
		ResultSet rs = st.getGeneratedKeys();
		while(rs.next()) {
			log.setId(rs.getInt(1));
		}
	}

	private void insertDiceThrownLog(DiceThrownLog log) throws SQLException {
		PreparedStatement st;
		st = connection.prepareStatement("INSERT INTO dice_thrown_log (id, diceOne, diceTwo) values (?,?,?) ");
		st.setInt(1, log.getId());
		st.setInt(2, log.getDiceOne());
		st.setInt(3, log.getDiceTwo());
		st.executeUpdate();
		st.close();
	}

	private void insertMoveLog(MoveLog log) throws SQLException {
		PreparedStatement st;
		st = connection.prepareStatement("INSERT INTO move_log (id, fromLocation, toLocation, resultOfAction) values (?,?,?,?) ");
		st.setInt(1, log.getId());
		st.setString(2, log.getFromLocation().name() );
		st.setString(3, log.getToLocation().name() );
		st.setBoolean(4, log.getResultOfAction());
		st.executeUpdate();
		st.close();
	}
	
	public List<HotgammonLog> getHotgammonLogs() throws SQLException{
		connect();
		List<HotgammonLog> hotgammonlogs = new ArrayList<HotgammonLog>();
		
		getMoveLogs(hotgammonlogs);
		getDiceThrownLogs(hotgammonlogs);
		connection.close();
		Collections.sort(hotgammonlogs);
		return hotgammonlogs;
	}

	private void getMoveLogs(List<HotgammonLog> hotgammonlogs)
			throws SQLException {
		Statement st = connection.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM hotgammonlog h inner join move_log m on m.id = h.id");

		while (rs.next()) {
			MoveLog moveLog = new MoveLog();
			
			moveLog.setId(rs.getInt("id"));
			moveLog.setPlayer(Color.valueOf(rs.getString("player")));
			moveLog.setFromLocation(Location.valueOf(rs.getString("fromLocation")));
			moveLog.setToLocation(Location.valueOf(rs.getString("toLocation")));
			moveLog.setResultOfAction(rs.getBoolean("resultOfAction"));
			moveLog.setCreated(rs.getTimestamp("created")); // is timestamp as it returns both date and time
			hotgammonlogs.add(moveLog);
		}
	}
	
	private void getDiceThrownLogs(List<HotgammonLog> hotgammonlogs)
			throws SQLException {
		Statement st = connection.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM hotgammonlog h inner join dice_thrown_log dt on dt.id = h.id");

		while (rs.next()) {
			DiceThrownLog diceThrownLog = new DiceThrownLog();
			
			diceThrownLog.setId(rs.getInt("id"));
			diceThrownLog.setPlayer(Color.valueOf(rs.getString("player")));
			diceThrownLog.setCreated(rs.getTimestamp("created")); // is timestamp as it returns both date and time
			diceThrownLog.setDiceOne(rs.getInt("diceOne"));
			diceThrownLog.setDiceTwo(rs.getInt("diceTwo"));
			hotgammonlogs.add(diceThrownLog);
		}
	}

	public void deleteAllLogs() throws SQLException {
		connect();
		Statement st = connection.createStatement();
		st.execute("DELETE FROM move_log");
		connection.commit();
		st.execute("DELETE FROM dice_thrown_log");
		connection.commit();
		st.execute("DELETE FROM hotgammonlog");
		st.close();
		connection.commit();
	}
}
