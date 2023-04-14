package model;

import java.util.ArrayList;
import java.util.List;

public class Hopital {
	private static Hopital instance;
	private List<Salle> salles;
	private Secretaire secretaire;
	private List<Patient> patients;

	private Hopital() {
		salles = new ArrayList<Salle>();
		patients = new ArrayList<Patient>();
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
	
	

//	public static void setInstance(Hopital instance) {
//		Hopital.instance = instance;
//
//	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Hopital [salles=" + salles + ", secretaire=" + secretaire + "]";
	}
	
	public void addPatient(Patient patient) {
		patients.add(patient);
	}
	
	public String affichePremierPatient() {
		Patient premierPatient = patients.get(0);
		String strPatient = "";
		
		if(premierPatient != null){
			strPatient = "Voici le prochain patient sur la file d'attente : \n"+premierPatient.toString();
		}else{
			strPatient = "Il n'y a pas encore de patient dans la file d'attente !";
		}
		
		return strPatient;
	}
	public String afficheListePatients() {
		String listePatients = "";
		
		if(patients.isEmpty()){
			listePatients = "Il n'y a aucun patient dans la file d'attente !";
		}else if(patients.isEmpty() == false) {
			listePatients = "Voici la liste des patients dans la file d'attente :";
			
			for(Patient p : patients){
				listePatients += "\n"+p.toString();
			}
		}
		
		return listePatients;
	}

}
