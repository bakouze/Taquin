package main;

import java.util.LinkedList;

public class FilePriorite {
	
	//Attributs
	private LinkedList<Plateau> listePlateau;
	
	//Constructeur
	public FilePriorite(){
		this.listePlateau = new LinkedList<Plateau>();
	}
	
	public void addPlateau(Plateau plateau){
		Plateau copie = new Plateau();
		copie = new Plateau(plateau);
		listePlateau.add(copie);
	}
	
	public Plateau getFirst(){
		return this.listePlateau.getFirst();
	}

	public boolean isEmpty(){
		return this.listePlateau.isEmpty();
	}

}
