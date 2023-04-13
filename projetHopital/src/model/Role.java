package model;

public class Role {
	private int id;
	private String role;
	private int salle;
	
	public Role() {
		super();
	}

	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Role(int id, String role, int salle) {
		super();
		this.id = id;
		this.role = role;
		this.salle = salle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}
	
}
