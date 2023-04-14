package user;

import java.sql.SQLException;

import dao.DaoPersonnelMysql;
import model.Hopital;
import model.Personnel;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Hopital hopital = Hopital.getInstance();
		Personnel p = authentification();
		Menu menu = new Menu(hopital, p.getIdRole(), p.getNom());

	}
	
	public static Personnel authentification() throws ClassNotFoundException, SQLException{
		Personnel p = new DaoPersonnelMysql().auth("phenri", "1234");
		return p;
	}
}
