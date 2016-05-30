package main;

public class Probleme {
	
	//Attributs
	/**
	 * attribut 1 : file de priorite
	 */
	private FilePriorite filePriorite;
	/**
	 * attribut 2 : tabkeau de position
	 */
	private TabPositions tabPositions;
	/**
	 * Attribut 3 : borne sup
	 */
	private int borneSup;
	/**
	 * Attribut 4 : borne inf
	 */
	private Plateau solution;

	//Constructeur
	/**
	 * Constructeur a partir d'un plateau inital
	 * @param plateau
	 */
	public Probleme(Plateau plateau){
		if (plateau.estSoluble()){
			this.filePriorite=new FilePriorite();
			this.filePriorite.addPlateau(plateau);
			this.tabPositions=new TabPositions();
			//this.tabPositions.addPositions(plateau);
			this.borneSup=500;
			this.solution=new Plateau();
		} else {
			System.out.println("erreur de faisabilite");
		}
	}
	
	/**
	 * Fonction de creation des fils à partir des deplacements possibles et stockage de ces fils dans la file de priorite
	 * @param plateau
	 */
	public void creationFils(Plateau plateau){
		if (plateau.getF() <= this.borneSup){
			//boolean[] deplacementsPossibles = plateau.deplacementsPossibles();
			boolean[] deplacementsPossibles = plateau.deplacementsPossibles();
			for(int i=0;i<deplacementsPossibles.length;i++){
				if(deplacementsPossibles[i]){
					Plateau copie = new Plateau(plateau);
					Deplacement move = new Deplacement(i);
					//copie.deplace(move.getString());
					copie.deplace(move.getString());
					if(this.tabPositions.isIn(copie)){
						this.filePriorite.addPlateau(copie);
					}
				}
			}			
		}
	}
	
	/**
	 * fonction de resolution du probleme
	 */
	public void solve(){
		while(!this.filePriorite.isEmpty()){
			Plateau current = this.filePriorite.getFirst();
			//current.afficher();
			this.tabPositions.addPositions(current);
			if (current.estResolu()){
				this.solution = current;
				this.borneSup = current.getF();
			} else if (current.getF() <= this.borneSup){
				this.creationFils(current);				
			}
		}
	}
	
	/**
	 * Fonction donnant le plateau correspondant a la resolution
	 * @return
	 */
	public Plateau getSolution(){
		return this.solution;
	}
}
