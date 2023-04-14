package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.DaoVisiteMysql;

public class Salle {
	private int num_salle;
	private Medecin medecin;
	private List<Visite> visites;
	private Patient patientActuel;

	public int getNum_salle() {
		return num_salle;
	}

	public void setNum_salle(int num_salle) {
		this.num_salle = num_salle;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Patient getPatientActuel() {
		return patientActuel;
	}

	public void setPatientActuel(Patient p) throws ClassNotFoundException, SQLException {
		this.patientActuel = p;
		if (p!=null) {
			if (this.visites.size() == 5) {
				for (Visite visite : visites) {
					new DaoVisiteMysql().create(visite);
				}
				this.visites.clear();
			}
			this.visites.add(new Visite(p.getNum_secu(), this.medecin.getNom(), LocalDate.now()));
		}
		
	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	@Override
	public String toString() {
		return "Salle [num_salle=" + num_salle + ", medecin=" + medecin + ", visites=" + visites + "]";
	}

	public Salle(int num_salle, Medecin medecin, List<Visite> visites) {
		super();
		this.num_salle = num_salle;
		this.medecin = medecin;
		this.visites = visites;
	}

	public Salle() {
		super();
	}

}
