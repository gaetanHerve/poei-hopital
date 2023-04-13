package user;

public class Menu {
	
	private int role;
	private String nom;
	private String affichage;
	
	
	public Menu(int role, String nom) {
		this.role = role;
		this.nom = nom;
		
		
		if(role == 1 || role == 2){
			afficheMenuMedecin();
		}else if(role == 0){
			afficheMenuSecretaire();
		}
		
	}
	
	public void afficheMenuMedecin(){
		
		System.out.println("1. Prochain patient");
		System.out.println("2. Afficher la file d'attente");
		System.out.println("3. Sauvegarder la liste des consultations");
		System.out.println("4. Quitter");
	}
	
	public void afficheMenuSecretaire(){
		
		System.out.println("1. Ajouter un patient dans la salle d'attente");
		System.out.println("2. Afficher la file d'attente");
		System.out.println("3. Afficher le prochain patient");
		System.out.println("4. Quitter");
		
	}

}
