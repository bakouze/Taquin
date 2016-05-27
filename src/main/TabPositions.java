package main;

import java.util.Hashtable;

public class TabPositions {

	//Attributs
	Hashtable<Long, Integer> ht;
	
	//Constructeur
	public TabPositions(){
		this.ht = new Hashtable<Long, Integer>();
	}
	
	public void addPositions(Plateau plateau){
		ht.put(plateau.hashSomme(), plateau.getProfondeur());
	}
	
	public boolean isIn(Plateau plateau){
		boolean b = true;
		long somme = plateau.hashSomme();
		if (ht.containsKey(somme)){
			long value = ht.get(somme);
			b = b && plateau.getProfondeur() < value;
		}
		return b;
	}
	
}
