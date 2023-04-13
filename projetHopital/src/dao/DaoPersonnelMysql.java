package dao;

import model.Personnel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoPersonnelMysql extends DaoMysql implements DaoPersonnel { 

	@Override
	public List<Personnel> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Personnel> personnels = new ArrayList<Personnel>();
		String sql = "select * from personnel;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Personnel m = new Personnel(
				rs.getInt("id"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getInt("role")
			);
			personnels.add(m);
		}
		
		conn.close();
		return personnels;
	}
	
	@Override
	public Personnel auth(String userLogin, String pwd) throws ClassNotFoundException, SQLException {
		Personnel personnel = null;
		int personnelId = checkCredentials(userLogin, pwd);
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		if (personnelId != -1) {
			String sql = "select * from personnel where id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userLogin);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				personnel = new Personnel(
						rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getInt("role")
				);
			}
		}
		
		conn.close();
		return personnel;
	}
	
	public int checkCredentials(String userLogin, String pwd) throws ClassNotFoundException, SQLException {
		int result = -1;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		String sql = "select * from authentification where login = ? AND mot_de_passe = ?;";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userLogin);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result = rs.getInt("id_personnel");
		}
		return result;
	}

	@Override
	public Personnel findById(Integer id) throws ClassNotFoundException, SQLException {
		Personnel personnel = null;
		String sql = "select * from personnel where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			personnel = new Personnel(
					rs.getInt("id"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getInt("role")
			);
		}
		
		conn.close();
		return personnel;
	}

	@Override
	public void create(Personnel obj) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Personnel obj) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Personnel obj) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
