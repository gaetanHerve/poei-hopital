package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.Visite;

public class DaoVisiteMysql extends DaoMysql implements DaoVisite { 

	@Override
	public List<Visite> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Visite> visites = new ArrayList<Visite>();
		String sql = "select * from visite;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			LocalDate date = rs.getDate("date_heure").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Visite v = new Visite(
				rs.getInt("id"),
				rs.getInt("num_secu"),
				rs.getString("ville"),
				rs.getDouble("cout"),
				date
			);
			visites.add(v);
		}
		
		conn.close();
		return visites;
	}

	@Override
	public Visite findById(Integer id) throws ClassNotFoundException, SQLException {
		Visite visite = null;
		String sql = "select * from visite where id_visite = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			LocalDate date = rs.getDate("date_heure").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Visite v = new Visite(
				rs.getInt("id"),
				rs.getInt("num_secu"),
				rs.getString("ville"),
				rs.getDouble("cout"),
				date
			);
		}
		
		conn.close();
		return visite;
	}

	@Override
	public void create(Visite v) throws ClassNotFoundException, SQLException {
		String sql = "insert into visite values (?, ?, ?, ?);";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		Date date = (Date) Date.from(v.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		ps.setInt(1, v.getId());
		ps.setInt(2, v.getIdSecu());
		ps.setString(3, v.getNomMedecin());
		ps.setDate(4, date);
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void update(Visite v) throws ClassNotFoundException, SQLException {
		String sql = "update visite set num_secu = ?, nom_medecin = ?, cout= ?, date_heure = ? where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);

		PreparedStatement ps = conn.prepareStatement(sql);
		Date date = (Date) Date.from(v.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		ps.setInt(1, v.getIdSecu());
		ps.setString(2, v.getNomMedecin());
		ps.setDate(3, date);
		ps.setInt(4, v.getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void delete(Visite v) throws ClassNotFoundException, SQLException {
		String sql = "delete from visite where id = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, v.getId());
		ps.executeUpdate();
		conn.close();		
	}

}
