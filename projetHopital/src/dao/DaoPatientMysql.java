package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoPatientMysql extends DaoMysql implements DaoPatient { 

	@Override
	public List<Patient> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		String sql = "select * from patients;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Adresse adresse = new DaoAdresseMysql().getAdresse(rs.getInt("id_adresse"));
			Patient p = new Patient(
				rs.getInt("num_secu"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getInt("age"),
				rs.getString("telephone"),
				adresse
			);
			patients.add(p);
		}
		
		conn.close();
		return patients;
	}

	@Override
	public Patient findById(Integer id) throws ClassNotFoundException, SQLException {
		Patient patient = null;
		String sql = "select * from patients where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			patient = new Patient(
				rs.getInt("id"),
				rs.getString("nom")
			);
		}
		
		conn.close();
		return patient;
	}

	@Override
	public void create(Patient p) throws ClassNotFoundException, SQLException {
		String sql = "insert into patients values (?);";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, p.getNom());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void update(Patient p) throws ClassNotFoundException, SQLException {
		String sql = "update patients set nom = ? where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, p.getNom());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void delete(Patient p) throws ClassNotFoundException, SQLException {
		String sql = "delete from patients where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ps.executeUpdate();
		conn.close();		
	}

}
