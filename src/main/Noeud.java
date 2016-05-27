package main;

/**
 * Classe modelisant un noeud
 */
class Noeud{
 
	//Attributs
	Plateau plateau;
	int[] positionInitiale;
 	Arbre f0;
	Arbre f1;
	Arbre f2;
	Arbre f3;
	
	//Constructeurs
	Noeud(Plateau pl){
		this.plateau= pl;
		this.positionInitiale = pl.getPosition();
		this.f0 = null;
		this.f1 = null;
		this.f2 = null;
		this.f3 = null;
	}
	
	//Methode
	public int[] getPosition(){
		return this.plateau.getPosition();
	}
	
	public Arbre getF0(){
		return this.f0;
	}
	
	public Arbre getF1(){
		return this.f1;
	}
	
	public Arbre getF2(){
		return this.f2;
	}
	
	public Arbre getF3(){
		return this.f3;
	}
}
