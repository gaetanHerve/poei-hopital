package user;

public class Test {

	public static void main(String[] args) {
		int role = authentification();
		
		if(role == 1 || role == 2){
			menuMedecin();
		}
		if(role == 0){
			menuSecretaire();
		}

	}
	
	public static int authentification(){
		
		return 1;
	}
	
	public static void menuMedecin(){
		String menu = "1.";
	}
	
	public static void menuSecretaire(){
		authentification();
		String menu = "1.";
	}
}
