package user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoPatientMysql;
import dao.DaoPersonnelMysql;
import model.Hopital;
import model.Medecin;
import model.Patient;
import model.Personnel;
import model.Salle;
import model.Secretaire;
import model.Visite;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Hopital hopital = Hopital.getInstance();
		
		// set jeu de test
		Patient patient0 = new DaoPatientMysql().findById(1465);
		Personnel s = new DaoPersonnelMysql().findById(1);
		Personnel m1 = new DaoPersonnelMysql().findById(2);
		Personnel m2 = new DaoPersonnelMysql().findById(3);
		Secretaire secretaire = new Secretaire(s.getId(), s.getNom(), s.getPrenom(), s.getIdRole());
		Medecin medecin1 = new Medecin(m1.getId(), m1.getNom(), m1.getPrenom(), m1.getIdRole());
		Medecin medecin2 = new Medecin(m2.getId(), m2.getNom(), m2.getPrenom(), m2.getIdRole());
		List<Visite> visites1 = new ArrayList<Visite>();
		List<Visite> visites2 = new ArrayList<Visite>();
		Salle salle1 = new Salle(1, medecin1, visites1);
		Salle salle2 = new Salle(1, medecin2, visites2);
		 
		hopital.addPatient(patient0);
		hopital.setSecretaire(secretaire);
		hopital.addSalle(salle1);
		hopital.addSalle(salle2);
		medecin1.setSalleActuelle(salle1);
		medecin2.setSalleActuelle(salle2);
		
		
		
		Menu menu = new Menu();
		menu.authentification();
	}
}
