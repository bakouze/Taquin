package main;

public class Probleme {
	
	//Attributs
	private FilePriorite filePriorite;
	private TabPositions tabPositions;

	//Constructeur
	public Probleme(Plateau plateau){
		this.filePriorite=new FilePriorite();
		this.filePriorite.addPlateau(plateau);
		this.tabPositions=new TabPositions();
		this.tabPositions.addPositions(plateau);
	}
	
	public void creationFils(){
		Plateau plateau = this.filePriorite.getFirst();
		boolean[] deplacementsPossibles = plateau.deplacementsPossibles();
		for(int i=0;i<deplacementsPossibles.length;i++){
			if(deplacementsPossibles[i]){
				Plateau copie = new Plateau(plateau);
				Deplacement move = new Deplacement(i);
				copie.deplace(move.getString());
				if(this.tabPositions.isIn(copie)){
					this.filePriorite.add(copie);
				}
			}
		}
	}
}
