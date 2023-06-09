package model;

import java.sql.SQLException;
import java.util.List;

public class Secretaire extends Personnel {

	public Secretaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Secretaire(int id, String nom, String prenom, int idRole) {
		super(id, nom, prenom, idRole);
		// TODO Auto-generated constructor stub
	}
	
	public Patient notif(Salle salle) throws ClassNotFoundException, SQLException {
		return placerPatient(salle);
	}
	
	public Patient placerPatient(Salle salle) throws ClassNotFoundException, SQLException {
		Patient patient = null;
		List<Patient> fileAttente = Hopital.getInstance().getPatients();
		if (!fileAttente.isEmpty()) {
			patient = fileAttente.get(0);
			salle.setPatientActuel(patient);
			fileAttente.remove(0);
		}
		return patient;
	}

}
