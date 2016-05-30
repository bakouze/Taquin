package main;

import java.util.LinkedList;

public class FilePriorite {
	
	//Attributs
	/**
	 * Attribut : linkedlist de plateau dans laquelle seront stocker tous les etats possibles a verifier
	 */
	private LinkedList<Plateau> listePlateau;
	
	private long maxSize;
	
	//Constructeur
	/**
	 * Constructeur vide
	 */
	public FilePriorite(){
		this.listePlateau = new LinkedList<Plateau>();
		this.maxSize = 0;
	}
	
	/**
	 * Fonction d'ajout d'un plateau a notre liste de priorite
	 * @param plateau
	 */
	public void addPlateau(Plateau plateau){
		Plateau copie = new Plateau(plateau.getN());
		copie = new Plateau(plateau);
		listePlateau.add(copie);
	}
	
	/**
	 * Fonction revoyant le premier plateau de la liste
	 * @return
	 */
	public Plateau getFirst(){
		this.maxSize = Math.max(maxSize, this.listePlateau.size());
		System.out.println(this.maxSize);
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
