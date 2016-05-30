package main;

import java.util.LinkedList;

public class FilePriorite {
	
	//Attributs
	/**
	 * Attribut : linkedlist de plateau dans laquelle seront stocker tous les etats possibles a verifier
	 */
	private LinkedList<Plateau> listePlateau;
	
	//Constructeur
	/**
	 * Constructeur vide
	 */
	public FilePriorite(){
		this.listePlateau = new LinkedList<Plateau>();
	}
	
	/**
	 * Fonction d'ajout d'un plateau a notre liste de priorite
	 * @param plateau
	 */
	public void addPlateau(Plateau plateau){
		Plateau copie = new Plateau();
		copie = new Plateau(plateau);
		listePlateau.add(copie);
	}
	
	/**
	 * Fonction revoyant le premier plateau de la liste
	 * @return
	 */
	public Plateau getFirst(){
		return this.listePlateau.pollFirst();
	}

	/**
	 * Fonction renvoyant vrai si la linkedlist de plateau est vide, faux sinon
	 * @return
	 */
	public boolean isEmpty(){
		return this.listePlateau.isEmpty();
	}

}
