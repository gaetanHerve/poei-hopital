package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Adresse;
import model.Patient;

public class DaoPatientMysql extends DaoMysql implements DaoPatient { 

	@Override
	public List<Patient> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		String sql = "select * from patient;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Adresse adresse = new DaoAdresseMysql().findById(rs.getInt("id_adresse"));
			Patient p = new Patient(
				rs.getInt("id_secu"),
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
		String sql = "select * from patient where id_secu = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Adresse adresse = new DaoAdresseMysql().findById(rs.getInt("id_adresse"));
			Patient p = new Patient(
				rs.getInt("num_secu"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getInt("age"),
				rs.getString("telephone"),
				adresse
			);
		}
		
		conn.close();
		return patient;
	}

	@Override
	public void create(Patient p) throws ClassNotFoundException, SQLException {
		String sql = "insert into patient values (?);";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, p.getNum_secu());
		ps.setString(2, p.getNom());
		ps.setString(3, p.getPrenom());
		ps.setInt(4, p.getAge());
		ps.setString(5, p.getTelephone());
		ps.setInt(6, p.getAdresse().getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void update(Patient p) throws ClassNotFoundException, SQLException {
		String sql = "update patient set nom = ?, prenom = ?, age = ?, telephone = ?, id_adresse = ? where id_secu = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, p.getNom());
		ps.setString(2, p.getPrenom());
		ps.setInt(3, p.getAge());
		ps.setString(4, p.getTelephone());
		ps.setInt(5, p.getAdresse().getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void delete(Patient p) throws ClassNotFoundException, SQLException {
		String sql = "delete from patient where id_secu = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, p.getNum_secu());
		ps.executeUpdate();
		conn.close();		
	}

}
