package main;

public class Deplacement {
	private int deplacement;
	
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
	
	public int getInt(){
		return this.deplacement;
	}

}
