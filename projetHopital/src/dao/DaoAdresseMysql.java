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

public class DaoAdresseMysql extends DaoMysql implements DaoAdresse { 

	@Override
	public List<Adresse> findAll() throws ClassNotFoundException, SQLException {
		ArrayList<Adresse> adresses = new ArrayList<Adresse>();
		String sql = "select * from adresse;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Adresse p = new Adresse(
				rs.getInt("id_adresse"),
				rs.getInt("numero"),
				rs.getString("rue"),
				rs.getString("ville"),
				rs.getInt("code_postal")
			);
			adresses.add(p);
		}
		
		conn.close();
		return adresses;
	}

	@Override
	public Adresse findById(Integer id) throws ClassNotFoundException, SQLException {
		Adresse adresse = null;
		String sql = "select * from adresse where id_adresse = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			adresse = new Adresse(
				rs.getInt("id_adresse"),
				rs.getInt("numero"),
				rs.getString("rue"),
				rs.getString("ville"),
				rs.getInt("code_postal")
			);
		}
		
		conn.close();
		return adresse;
	}

	@Override
	public int create(Adresse a) throws ClassNotFoundException, SQLException {
		int newId = -1;
		String sql = "INSERT INTO adresse (numero, rue, ville, code_postal) VALUES (?, ?, ?, ?);";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, a.getNumero());
		ps.setString(2, a.getRue());
		ps.setString(3, a.getVille());
		ps.setInt(4, a.getCode_postale());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			newId = rs.getInt(1);
		}
		conn.close();
		return newId;
	}

	@Override
	public void update(Adresse a) throws ClassNotFoundException, SQLException {
		String sql = "update adresse set numero = ?, rue = ?, ville = ?, code_postal = ? where id_adresse = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, a.getNumero());
		ps.setString(2, a.getRue());
		ps.setString(3, a.getVille());
		ps.setInt(4, a.getCode_postale());
		ps.setInt(5, a.getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void delete(Adresse a) throws ClassNotFoundException, SQLException {
		String sql = "delete from adresse where id_adresse = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionStr, login, pwd);
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, a.getId());
		ps.executeUpdate();
		conn.close();		
	}

}
