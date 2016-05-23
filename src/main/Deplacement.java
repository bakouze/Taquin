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
	
	public Deplacement(int a){
		if(a>=0&&a<=3){
			this.deplacement = a;
		}
	}
	
	public int getInt(){
		return this.deplacement;
	}
	
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
		else if(this.deplacement == 3){
			return "b";
		}
	}

}
