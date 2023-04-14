package user;

import java.sql.SQLException;

import dao.DaoPersonnelMysql;
import model.Hopital;
import model.Personnel;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Hopital hopital = Hopital.getInstance();
		Menu menu = new Menu();
		menu.authentification();
	}
}
