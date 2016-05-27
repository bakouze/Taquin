package main;

import java.util.Hashtable;

public class TabPositions {

	//Attributs
	Hashtable<Integer, Integer> ht;
	
	//Constructeur
	public TabPositions(){
		this.ht = new Hashtable<Integer, Integer>();
	}
	
	public void addPositions(Plateau plateau){
		ht.put(plateau.hashSomme(), plateau.getProfondeur());
	}
	
	public boolean isIn(Plateau plateau){
		boolean b = true;
		int somme = plateau.hashSomme();
		if (ht.containsKey(somme)){
			int value = ht.get(somme);
			b = b && plateau.getProfondeur() < value;
		}
		return b;
	}
	
}
