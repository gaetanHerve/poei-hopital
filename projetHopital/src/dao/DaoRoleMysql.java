package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Role;

public class DaoRoleMysql extends DaoMysql implements DaoRole { 

	@Override
	public List<Role> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Role> roles = new ArrayList<Role>();
		String sql = "select * from role;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Role v = new Role(
				rs.getInt("id"),
				rs.getString("role"),
				rs.getInt("salle")
			);
			roles.add(v);
		}
		
		conn.close();
		return roles;
	}

	@Override
	public Role findById(Integer id) throws ClassNotFoundException, SQLException {
		Role role = null;
		String sql = "select * from role where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			role = new Role(
				rs.getInt("id"),
				rs.getString("role"),
				rs.getInt("salle")
			);
		}
		
		conn.close();
		return role;
	}

	@Override
	public int create(Role r) throws ClassNotFoundException, SQLException {
		int id = r.getId();
		String sql = "insert into role values (?, ?, ?);";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, r.getRole());
		ps.setInt(3, r.getSalle());
		ps.executeUpdate();
		conn.close();
		return id;
	}

	@Override
	public void update(Role r) throws ClassNotFoundException, SQLException {
		String sql = "update role set role = ?, salle = ? where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, r.getRole());
		ps.setInt(2, r.getSalle());
		ps.setInt(3, r.getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void delete(Role v) throws ClassNotFoundException, SQLException {
		String sql = "delete from role where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, v.getId());
		ps.executeUpdate();
		conn.close();		
	}

}
