package user;

import java.sql.SQLException;
import java.util.Scanner;

import dao.DaoPatientMysql;
import model.Adresse;
import model.Hopital;
import model.Patient;

public class Menu {
	
	private int role;
	private String nom;
	private String affichage;
	private Hopital hopital;
	
	
	public Menu(Hopital hopital, int role, String nom) throws ClassNotFoundException, SQLException {
		this.hopital = hopital;
		this.role = role;
		this.nom = nom;
		
		System.out.println("Bonjour "+nom+", que voulez vous faire aujourd'hui : ");
		if(role == 1 || role == 2){
			afficheMenuMedecin();
		}else if(role == 0){
			afficheMenuSecretaire();
		}
		
	}
	
	public void afficheMenuMedecin(){
		//lors de l'authentification d'un medecin, instancier la bonne salle 
		
		Scanner scannerChoix = new Scanner(System.in);
		
		System.out.println("1. Prochain patient");
		System.out.println("2. Afficher la file d'attente");
		System.out.println("3. Sauvegarder la liste des consultations");
		System.out.println("4. Quitter");
		
		int choix = scannerChoix.nextInt();

        switch (choix) {
            case 1:
                // Handle option 1
                System.out.println("You selected option 1");
                break;
            case 2:
                // Handle option 2
                System.out.println("You selected option 2");
                break;
            case 3:
                // Handle option 3
                System.out.println("You selected option 3");
                break;
            case 4:
                System.out.println("Aurevoir !");
                break;
            default:
                // Invalid input
                System.out.println("Choix invalide !");
                afficheMenuMedecin();
        }
	}
	
	public void afficheMenuSecretaire() throws ClassNotFoundException, SQLException{
		
		Scanner scannerChoix = new Scanner(System.in);
		
		System.out.println("1. Ajouter un patient dans la salle d'attente");
		System.out.println("2. Afficher la file d'attente");
		System.out.println("3. Afficher le prochain patient");
		System.out.println("4. Quitter");
		
		int choix = scannerChoix.nextInt();

        switch (choix) {
            case 1:
            	// doit on mettre les scanner ici ou dans la methode Hopital
            	Scanner scannerNumSecu = new Scanner(System.in);
            	System.out.println("Saisir le num�ro de s�cu du patient :");
            	int numSecu = scannerNumSecu.nextInt();
            	
            	Patient p = new DaoPatientMysql().findById(numSecu);
            	
                if( p != null){
                	hopital.addPatient(p);
                	System.out.println("Vous avez ajout� le patient dans la file d'attente !");
                }else{
                	System.out.println("Le patient n'existe pas, il faut le cr�er :");
                	Scanner scannerString = new Scanner(System.in);
                	Scanner scannerInt = new Scanner(System.in);
                	
                	System.out.println("Saisir son nom :");
                	String nomPatient = scannerString.nextLine();
                	
                	System.out.println("Saisir son prenom :");
                	String prenomPatient = scannerString.nextLine();
                	
                	System.out.println("Saisir son age :");
                	int agePatient = scannerInt.nextInt();
                	
                	System.out.println("Saisir son num�ro de t�l�phone :");
                	String telPatient = scannerString.nextLine();
                	
                	
                	System.out.println("Saisir son num�ro de rue :");
                	int numAdresse = scannerInt.nextInt();
                	
                	System.out.println("Saisir le nom de la rue :");
                	String rueAdresse = scannerString.nextLine();
                	System.out.println("Saisir la ville :");
                	String villeAdresse = scannerString.nextLine();
                	System.out.println("Saisir le code postal :");
                	int cpAdresse = scannerInt.nextInt();
                	
                	Adresse adressePatient = new Adresse(numAdresse,rueAdresse,villeAdresse,cpAdresse);
                	
                	Patient newPatient = new Patient(numSecu,nomPatient,prenomPatient,agePatient,telPatient,adressePatient);
                	new DaoPatientMysql().create(newPatient);
                	
                	hopital.addPatient(newPatient);
                	System.out.println("Patient ajout� dans la base et dans la file d'attente !");
                }
                
                
                afficheMenuSecretaire();
            case 2:
            	hopital.afficheListePatients();
                afficheMenuSecretaire();
            case 3:
            	hopital.affichePremierPatient();
                afficheMenuSecretaire();
            case 4:
                System.out.println("Aurevoir !");
                break;
            default:
                System.out.println("Choix invalide !");
                afficheMenuSecretaire();
        }
		
	}

}
