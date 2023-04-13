package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoSecretaireMysql extends DaoMysql implements DaoSecretaire {

	@Override
	public List<Secretaire> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Secretaire> secretaires = new ArrayList<Secretaire>();
		String sql = "select * from secretaires;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Secretaire s = new Secretaire(
				rs.getInt("id"),
				rs.getString("nom")
			);
			secretaires.add(s);
		}
		
		conn.close();
		return secretaires;
	}

	@Override
	public Secretaire findById(Integer id) throws ClassNotFoundException, SQLException {
		Secretaire secretaire = null;
		String sql = "select * from secretaires where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			secretaire = new Secretaire(
				rs.getInt("id"),
				rs.getString("nom")
			);
		}
		
		conn.close();
		return secretaire;
	}

	@Override
	public void create(Secretaire s) throws ClassNotFoundException, SQLException {
		String sql = "insert into secretaires values (?);";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, s.getNom());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void update(Secretaire s) throws ClassNotFoundException, SQLException {
		String sql = "update secretaires set nom = ? where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, s.getNom());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void delete(Secretaire s) throws ClassNotFoundException, SQLException {
		String sql = "delete from secretaires where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, s.getId());
		ps.executeUpdate();
		conn.close();		
	}
	
	
}
