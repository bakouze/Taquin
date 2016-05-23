package main;

public class FilePriorite {
	
	//Attributs
	private LinkedList<Plateau> listePlateau;
	
	//Constructeur
	public FilePriorite(){
		this.listePlateau = new LinkedList<Plateau>();
	}
	
	public addPlateau(Plateau plateau){
		Plateau copie = new Plateau();
		copie = new Plateau(plateau);
		
	}

}
