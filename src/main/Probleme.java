package main;

public class Probleme {
	
	//Attributs
	private FilePriorite filePriorite;
	private TabPositions tabPositions;
	private int borneSup;
	private Plateau solution;

	//Constructeur
	public Probleme(Plateau plateau){
		if (plateau.estSoluble()){
			this.filePriorite=new FilePriorite();
			this.filePriorite.addPlateau(plateau);
			this.tabPositions=new TabPositions();
			this.tabPositions.addPositions(plateau);
			this.borneSup=500;
			this.solution=new Plateau();
		} else {
			System.out.println("erreur de faisabilite");
		}
	}
	
	public void creationFils(){
		Plateau plateau = this.filePriorite.getFirst();
		if (plateau.getF() <= this.borneSup){
			boolean[] deplacementsPossibles = plateau.deplacementsPossibles();
			for(int i=0;i<deplacementsPossibles.length;i++){
				if(deplacementsPossibles[i]){
					Plateau copie = new Plateau(plateau);
					Deplacement move = new Deplacement(i);
					copie.deplace(move.getString());
					if(this.tabPositions.isIn(copie)){
						this.filePriorite.addPlateau(copie);
					}
				}
			}			
		}
	}
	
	public void solve(){
		while(!this.filePriorite.isEmpty()){
			Plateau current = this.filePriorite.getFirst();
			if (current.estResolu()){
				this.solution = current;
				this.borneSup = current.getF();
			} else if (current.getF() <= this.borneSup){
				this.creationFils();				
			}
		}
	}
	
	public Plateau getSolution(){
		return this.solution;
	}
}
