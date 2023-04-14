package user;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DaoPatientMysql;
import dao.DaoPersonnelMysql;

import dao.DaoVisiteMysql;


import model.Adresse;
import model.Hopital;
import model.Medecin;
import model.Patient;

import model.Salle;
import model.Visite;

import model.Personnel;


public class Menu {

	private int role;
	private String nom;
	private String affichage;
	private Hopital hopital;


	public Menu() {
	}

	public Menu(Hopital hopital, int role, String nom, Personnel personnel) throws ClassNotFoundException, SQLException {
		this.hopital = hopital;
		this.role = role;
		this.nom = nom;

		System.out.println("Bonjour " + nom + ", que voulez vous faire aujourd'hui : ");
		if (role == 1 || role == 2) {
			afficheMenuMedecin(personnel);
		} else if (role == 0) {
			afficheMenuSecretaire();
		}

	}

	public void afficheMenuMedecin(Personnel p) throws ClassNotFoundException, SQLException {
		// lors de l'authentification d'un medecin, instancier la bonne salle
		boolean relancerMenu = true;
		int role = p.getIdRole();
		Medecin medecin = new Medecin(p.getId(), p.getNom(), p.getPrenom(), role);
		medecin.setSalleActuelle(Hopital.getInstance().getSalles().get(role));
		
		
		Scanner scannerChoix = new Scanner(System.in);

		System.out.println("1. Prochain patient");
		System.out.println("2. Afficher la file d'attente");
		System.out.println("3. Sauvegarder la liste des consultations");
		System.out.println("4. Quitter");

		int choix = scannerChoix.nextInt();

		switch (choix) {
		case 1:
			// Pattern Observer

			medecin.libererSalle();
			System.out.println("You selected option 1");
			break;
		case 2:
			// List patient 2
			String patientsStr = Hopital.getInstance().afficheListePatients();
			System.out.println(patientsStr);
			break;
		case 3:
			// Save consult list 3
			DaoVisiteMysql daoVisit = new DaoVisiteMysql();
			Salle salle = medecin.getSalleActuelle();
			salle.getVisites();
			List<Visite> visites = salle.getVisites();

			for (Visite visite : visites) {
				daoVisit.create(visite);
			}

			visites.clear();
			salle.setVisites(visites);

			System.out.println("Sauvegarde des consultations OK");
			break;

		// leave app
		case 4:
			relancerMenu = false;

			System.out.println("Au revoir !");
			break;
		default:
			// Invalid input
			System.out.println("Choix invalide !");
			afficheMenuMedecin(medecin);
		}
		if (relancerMenu) {
			afficheMenuMedecin(medecin);
		} else {
			authentification();
		}
	}

	public void afficheMenuSecretaire() throws ClassNotFoundException, SQLException{
		

		Scanner scannerChoix = new Scanner(System.in);

		System.out.println("1. Ajouter un patient dans la salle d'attente");
		System.out.println("2. Afficher la file d'attente");
		System.out.println("3. Afficher le prochain patient");
		System.out.println("4. Quitter");

		int choix = scannerChoix.nextInt();
		
		boolean relancerMenu = true;

        switch (choix) {
            case 1:
            	// doit on mettre les scanner ici ou dans la methode Hopital
            	Scanner scannerNumSecu = new Scanner(System.in);
            	System.out.println("Saisir le numéro de sécu du patient :");
            	int numSecu = scannerNumSecu.nextInt();
            	
            	Patient p = new DaoPatientMysql().findById(numSecu);
            	
                if( p != null){
                	Hopital.getInstance().addPatient(p);
                	System.out.println("Vous avez ajouté le patient dans la file d'attente !");
                }else{
                	System.out.println("Le patient n'existe pas, il faut le créer :");
                	Scanner scannerString = new Scanner(System.in);
                	Scanner scannerInt = new Scanner(System.in);
                	
                	System.out.println("Saisir son nom :");
                	String nomPatient = scannerString.nextLine();
                	
                	System.out.println("Saisir son prenom :");
                	String prenomPatient = scannerString.nextLine();
                	
                	System.out.println("Saisir son age :");
                	int agePatient = scannerInt.nextInt();

                	System.out.println("Saisir son numéro de téléphone :");
                	String telPatient = scannerString.nextLine();
                	
                	
                	System.out.println("Saisir son numéro de rue :");
                	int numAdresse = scannerInt.nextInt();
                	
                	System.out.println("Saisir le nom de la rue :");
                	String rueAdresse = scannerString.nextLine();
                	System.out.println("Saisir la ville :");
                	String villeAdresse = scannerString.nextLine();
                	System.out.println("Saisir le code postal :");
                	int cpAdresse = scannerInt.nextInt();
                	
                	
                	
                	//ajouter l'adresse dans la BDD
                	Adresse adressePatient = new Adresse(numAdresse,rueAdresse,villeAdresse,cpAdresse);
                	
                	Patient newPatient = new Patient(numSecu,nomPatient,prenomPatient,agePatient,telPatient,adressePatient);

                	new DaoPatientMysql().create(newPatient);
                	
                	Hopital.getInstance().addPatient(newPatient);
                	System.out.println("Patient ajouté dans la base et dans la file d'attente !");
                }
                
                break;
            case 2:
            	System.out.println(Hopital.getInstance().afficheListePatients());
                break;
            case 3:
            	System.out.println(Hopital.getInstance().affichePremierPatient());
                break;
            case 4:
                System.out.println("Aurevoir !");
                relancerMenu = false;
                break;
            default:
                System.out.println("Choix invalide !");
                break;
        }
        
        if(relancerMenu){
        	afficheMenuSecretaire();
        }else{
        	authentification();
        }
		
	}
	
	public void authentification() throws ClassNotFoundException, SQLException {
		Scanner scannerString = new Scanner(System.in);
		System.out.print("Veuillez vous connectez \nLogin:");
		String login = scannerString.nextLine();
		
		System.out.print("Mot de passe :");
		String mdp = scannerString.nextLine();
		
		Personnel p = new DaoPersonnelMysql().auth(login, mdp);
		
		if(p == null){
			System.out.println("Erreur avec le login ou le mot de passe ! Réessayez !\n");
			authentification();
		}else{
			Menu menu = new Menu(hopital, p.getIdRole(), p.getNom()+" "+p.getPrenom(), p);
		}
	}

}
