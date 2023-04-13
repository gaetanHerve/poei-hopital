package model;

public abstract class Personnel {
	private int id;
	private String nom;
	private String prenom;
	private int idRole;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", idRole=" + idRole + "]";
	}


	public Personnel(int id, String nom, String prenom, int idRole) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.idRole = idRole;
	}

	public Personnel() {
		super();
	}

}
