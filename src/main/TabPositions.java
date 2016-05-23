package main;

import java.util.Hashtable;

public class TabPositions {

	//Attributs
	Hashtable<Integer, Integer> ht;
	
	//Constructeur
	public TabPositions(){
		this.ht = new HashTable();
	}
	
	public void addPositions(Plateau plateau){
		ht.put(plateau.hashSomme(), plateau.getProfondeur());
	}
	
	public boolean isIn(Plateau plateau){
		int somme = plateau.hashSomme();
		int value = ht.get(somme);
		return (plateau.getProfondeur < value);
	}
}
