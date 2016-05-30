package main;

public class Deplacement {
	/**
	 * attribut de la classe deplacement :
	 * 0 = haut
	 * 1 = gauche
	 * 2 = droite
	 * 3 = bas
	 */
	private int deplacement;
	
	/**
	 * Constructeur de la classe à partir d'un string
	 * @param a
	 */
	public Deplacement(String a){
		if(a.equalsIgnoreCase("h")){
			this.deplacement = 0;
		}
		else if(a.equalsIgnoreCase("g")){
			this.deplacement = 1;
		}
		else if(a.equalsIgnoreCase("d")){
			this.deplacement = 2;
		}
		else if(a.equalsIgnoreCase("b")){
			this.deplacement = 3;
		}
		else{
			System.out.println("erreur deplacement");
		}
	}
	
	/**
	 * Constructeur à partir d'un integer
	 * @param a
	 */
	public Deplacement(int a){
		if(a>=0&&a<=3){
			this.deplacement = a;
		}
	}
	
	/**
	 * getter de l'attribut int
	 * @return
	 */
	public int getInt(){
		return this.deplacement;
	}
	
	/**
	 * getter de la string associee
	 * @return
	 */
	public String getString(){
		if(this.deplacement == 0){
			return "h";
		}
		else if(this.deplacement == 1){
			return "g";
		}
		else if(this.deplacement == 2){
			return "d";
		}
		else  {
			return "b";
		}
	}

}
