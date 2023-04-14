package model;

import java.sql.SQLException;

public class Medecin extends Personnel{
	private Salle salleActuelle;
	
	public Medecin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medecin(int id, String nom, String prenom, int idRole) {
		super(id, nom, prenom, idRole);
		// TODO Auto-generated constructor stub
	}
	
	public Salle getSalleActuelle() {
		return salleActuelle;
	}

	public void setSalleActuelle(Salle salleActuelle) {
		this.salleActuelle = salleActuelle;
	}

	public Patient libererSalle() throws ClassNotFoundException, SQLException {
		if (salleActuelle != null) {
			salleActuelle.setPatientActuel(null);
		}
		Patient patient = Hopital.getInstance().getSecretaire().notif(salleActuelle);
		return patient;
	}

}
