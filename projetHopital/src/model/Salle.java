package model;

import java.util.List;

public class Salle {
	private int num_salle;
	private Medecin medecin;
	private List<Visite> visites;

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
