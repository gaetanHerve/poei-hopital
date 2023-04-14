package model;

import java.time.LocalDate;

public class Visite {
	private int id;
	private int idSecu;
	private String nomMedecin;
	private double cout = 23;
	private LocalDate date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSecu() {
		return idSecu;
	}

	public void setIdSecu(int idSecu) {
		this.idSecu = idSecu;
	}

	public String getNomMedecin() {
		return nomMedecin;
	}

	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Visite(int idSecu, String nomMedecin, LocalDate date) {
		super();
		this.idSecu = idSecu;
		this.nomMedecin = nomMedecin;
		this.date = date;
	}
	
	public Visite(int id, int idSecu, String nomMedecin, LocalDate date) {
		super();
		this.id = id;
		this.idSecu = idSecu;
		this.nomMedecin = nomMedecin;
		this.date = date;
	}
	
	public Visite(int id, int idSecu, String nomMedecin, LocalDate date, double cout) {
		super();
		this.id = id;
		this.idSecu = idSecu;
		this.nomMedecin = nomMedecin;
		this.cout = cout;
		this.date = date;
	}

	public Visite() {
		super();
	}

}
