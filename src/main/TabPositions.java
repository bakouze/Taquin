package main;

import java.util.Hashtable;

public class TabPositions {

	//Attributs
	/**
	 * Attribut table de hashage referencant toutes les positions deja rencontrees (codees avec leur somme de hashage), ainsi que la profondeur a laquelle elles ont ete rencontrees
	 */
	Hashtable<String, Integer> ht;
	
	//Constructeur
	/**
	 * Constructeur vide
	 */
	public TabPositions(){
		this.ht = new Hashtable<String, Integer>();
	}
	
	/**
	 * Fonction d'ajout de position dans la table de hashage
	 * @param plateau
	 */
	public void addPositions(Plateau plateau){
		ht.put(plateau.hashSomme(), plateau.getProfondeur());
	}
	
	/**
	 * fonction renvoyant vrai si le plateau est dans la table( avec une profondeur inférieur), faux sinon
	 * @param plateau
	 * @return
	 */
	public boolean isNotIn(Plateau plateau){
		boolean b = true;
		String somme = plateau.hashSomme();
		if (ht.containsKey(somme)){
			int value = ht.get(somme);
			b = b && plateau.getProfondeur() < value;
		}
		return b;
	}
	
}
