package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Player;

public class PlayerDao {

	private Connection connection;
	private final String GET_FULL_ROSTER = "SELECT * FROM team";
	private final String GET_POSITION_GROUP = "SELECT * FROM team WHERE position = ?";
	private final String CUT_PLAYER = "DELETE FROM team WHERE id = ?";
	private final String SIGN_PLAYER = "INSERT INTO team(name, position, starter) values (?, ?, ?)";
	private final String UPDATE_PLAYER = "UPDATE team SET name = ?, position = ?, starter = ? WHERE id = ?";
	
	
	public PlayerDao() {
		connection = DBConnection.getConnection();
	}
	
	
	// should I be returning a list of names.... or list of objects (players)?
	public List<Player> getFullRoster() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_FULL_ROSTER).executeQuery();
		List<Player> roster = new ArrayList<Player>();
		
		while (rs.next()) {
			roster.add(objectifyPlayer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		
		return roster;
	}
	
	public List<Player> getPositionGroup(String position) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_POSITION_GROUP);
		ps.setString(1, position);
		ResultSet rs = ps.executeQuery();
		List<Player> position_group = new ArrayList<Player>();
		
		while (rs.next()) {
			position_group.add(objectifyPlayer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		
		return position_group;
	}
	
	private Player objectifyPlayer(int id, String name, String position, String starter) {
		return new Player(id, name, position, starter);
	}
	
	public void signNewPlayer(String name, String position, String starter) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(SIGN_PLAYER);
		ps.setString(1, name);
		ps.setString(2, position);
		ps.setString(3, starter);
		ps.executeUpdate();
	}
	
	public void cutPlayer(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CUT_PLAYER);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updatePlayerInfo(int id, String name, String position, String starter) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_PLAYER);
		ps.setString(1, name);
		ps.setString(2, position);
		ps.setString(3, starter);
		ps.setInt(4, id);
		ps.executeUpdate();
	}
}
