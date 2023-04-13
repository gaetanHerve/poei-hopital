package model;

import java.util.ArrayList;
import java.util.List;

public class Hopital {
	private static Hopital instance;
	private List<Salle> salles;
	private Secretaire secretaire;

	private Hopital() {
		salles = new ArrayList<Salle>();
	}

	public static Hopital getInstance() {
		if (instance == null) {
			instance = new Hopital();
		}
		return instance;
	}

	public void addSalle(Salle salle) {
		salles.add(salle);
	}

	public List<Salle> getSalles() {
		return salles;
	}

	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}

	public Secretaire getSecretaire() {
		return secretaire;
	}

	public void setSecretaire(Secretaire secretaire) {
		this.secretaire = secretaire;
	}

	public static void setInstance(Hopital instance) {
		Hopital.instance = instance;

	}

	@Override
	public String toString() {
		return "Hopital [salles=" + salles + ", secretaire=" + secretaire + "]";
	}

}
