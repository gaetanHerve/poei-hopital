package model;

public class Patient {
	private int num_secu;
	private String nom;
	private String prenom;
	private int age;
	private String telephone;
	private Adresse adresse;

	public int getNum_secu() {
		return num_secu;
	}

	public void setNum_secu(int num_secu) {
		this.num_secu = num_secu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Patient [num_secu=" + num_secu + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", telephone="
				+ telephone + ", adresse=" + adresse + "]";
	}

	public Patient(int num_secu, String nom, String prenom, int age, String telephone, Adresse adresse) {
		super();
		this.num_secu = num_secu;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.telephone = telephone;
		this.adresse = adresse;
	}

	public Patient() {
		super();
	}

}
