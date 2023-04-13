package dao;

import java.sql.SQLException;

import model.Personnel;

public interface DaoPersonnel extends Dao<Personnel, Integer> {
	public Personnel auth(String login, String pwd) throws ClassNotFoundException, SQLException;
}
