package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoPersonnelMysql extends DaoMysql implements DaoMedecin { 

	@Override
	public List<Medecin> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Medecin> medecins = new ArrayList<Medecin>();
		String sql = "select * from medecins;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Medecin m = new Medecin(
				rs.getInt("id"),
				rs.getString("nom")
			);
			medecins.add(m);
		}
		
		conn.close();
		return medecins;
	}

	@Override
	public Medecin findById(Integer id) throws ClassNotFoundException, SQLException {
		Medecin medecin = null;
		String sql = "select * from medecins where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			medecin = new Medecin(
				rs.getInt("id"),
				rs.getString("nom")
			);
		}
		
		conn.close();
		return medecin;
	}

	@Override
	public void create(Medecin m) throws ClassNotFoundException, SQLException {
		String sql = "insert into medecins values (?);";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, m.getNom());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void update(Medecin m) throws ClassNotFoundException, SQLException {
		String sql = "update medecins set nom = ? where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, m.getNom());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void delete(Medecin m) throws ClassNotFoundException, SQLException {
		String sql = "delete from medecins where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, m.getId());
		ps.executeUpdate();
		conn.close();		
	}

}
