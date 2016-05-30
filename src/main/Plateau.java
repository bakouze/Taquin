package main;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Représentation du plateau du jeu de taquin
 * @author guillaume
 *
 */
public class Plateau {

	/**
	 * Attribut modélisant le plateau physique
	 */
	private int[][] plateau;

	/**
	 * Attribut modelisant l'etat final attendu pour le plateau
	 */
	private int[][] etatFinal;

	/**
	 * nombre de coups utilis�s pour atteindre ce plateau
	 */
	private int profondeur;

	/**
	 * liste des mouvements effectues
	 */
	private LinkedList<String> listeDeplacements;

	/**
	 * entier stockant la taille du plateau
	 */
	private int n;

	/**
	 * Constructeur par défaut : construit un plateau de taquin résolu
	 */
	public Plateau(int n){
		int[][] pl = new int[n][n];
		for(int i=0;i<n;i++){
			for(int j = 0;j<n;j++){
				pl[i][j]=i+n*j;
			}
		}
		this.plateau = pl;
		this.etatFinal = pl;
		this.listeDeplacements=new LinkedList<String>();
	}

	/**
	 * constructeur clonant un plateau existant
	 * @param plateau
	 */
	@SuppressWarnings("unchecked")
	public Plateau(Plateau plateau){
		this.n=plateau.n;
		this.plateau = new int[n][n];
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				this.plateau[i][j] = plateau.getPosition()[i][j];
			}
		}
		this.etatFinal = new int[n][n];
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				this.etatFinal[i][j] = plateau.getPosFinale()[i][j];
			}
		}
		this.profondeur = plateau.getProfondeur();
		this.listeDeplacements=(LinkedList<String>) plateau.listeDeplacements.clone();
	}

	/**
	 * Constructeur à partir d'un entier n (la taille du plateau), et de deux tableaux d'entiers (état initial et final du plateau)
	 * @param n
	 * @param pl
	 * @param sol
	 */
	public Plateau (int n, int[][] pl, int[][] sol){
		this.n=n;
		this.listeDeplacements=new LinkedList<String>();
		this.plateau = pl;
		this.etatFinal = sol;
	}

	/**
	 * getter pour la profondeur du plateau
	 */
	public int getProfondeur(){
		return this.profondeur;
	}

	/**
	 * getter pour la position actuelle du plateau
	 * @return
	 */
	public int[][] getPosition(){
		return this.plateau;
	}

	/**
	 * getter pour la position finale du plateau
	 */
	public int[][] getPosFinale(){
		return this.etatFinal;
	}
	
	/**
	 * getter pour la taille du plateau
	 * @return
	 */
	public int getN(){
		return this.n;
	}

	/**
	 * return the index of the element a in plateau
	 * @param a
	 * @return
	 */
	private int[] getPosPl(int a){
		int[] couple = new int[2];
		for(int i=0;i<n;i++){
			for(int j = 0;j<n;j++){
				if(a==this.plateau[i][j]){
					couple[0]=i;
					couple[1]=j;
					return couple;
				}
			}
		}
		System.out.println("erreur getPosPl");
		couple[0]=-1;
		couple[1]=-1;
		return couple;
	}

	/**
	 * return the index of the element a in solution
	 * @param a
	 * @return
	 */
	private int[] getPosSol(int a){
		int[] couple = new int[2];
		for(int i=0;i<n;i++){
			for(int j = 0;j<n;j++){
				if(a==this.etatFinal[i][j]){
					couple[0]=i;
					couple[1]=j;
					return couple;
				}
			}
		}
		System.out.println("erreur getPosSol");
		couple[0]=-1;
		couple[1]=-1;
		return couple;
	}

	/**
	 * return the manhattan distance of the element a (from actual place to final place)
	 * @param a
	 * @return
	 */
	private int manhattanDistI(int a){
		int[] ini = this.getPosPl(a);
		int[] fin = this.getPosSol(a);
		return Math.abs(ini[0]-fin[0])+Math.abs(ini[1]-fin[1]);
	}

	/**
	 * return the sum of the manhattan distance
	 * @return
	 */
	public int manhattanDist(){
		int temp = 0;
		for(int i = 1;i<n*n;i++){
			temp += this.manhattanDistI(i);
		}
		return temp;
	}

	/**
	 * fonction donnant la valeur de la fonction F du plateau (la profondeur + sa distance de manhattan)
	 * @return
	 */
	public int getF(){
		return this.profondeur+this.manhattanDist();
	}

	//Test de faisabilite
	/**
	 * Echange les cases ini et sec du plateau
	 * @param ini
	 * @param sec
	 */
	private void permutation(int[] ini, int[] sec){
		int temp = this.plateau[ini[0]][ini[1]];
		this.plateau[ini[0]][ini[1]]=this.plateau[sec[0]][sec[1]];
		this.plateau[sec[0]][sec[1]]=temp;
	}

	/**
	 * Place la case a si elle n'est pas bien place avec une permutation. Renvoie le nombre de permutation necessaire.
	 * @param a
	 * @return
	 */
	private int placement(int a){
		if((this.getPosPl(a)[0]!=this.getPosSol(a)[0])||(this.getPosPl(a)[1]!=this.getPosSol(a)[1])){
			int[] ini = this.getPosPl(a);
			int[] fin = this.getPosSol(a);
			this.permutation(ini, fin);
			return 1;
		}
		else{
			return 0;
		}
	}

	/**
	 * Verifie que si le plateau est resolu
	 * @return
	 */
	public boolean estResolu(){
		boolean test = true;
		for(int i=0;i<n;i++){
			for(int j = 0;j<n;j++){
				test = test && (this.plateau[i][j]==this.etatFinal[i][j]);
			}
		}
		return test;
	}

	/**
	 * Verifie que le plateau est soluble
	 * @return
	 */
	public boolean estSoluble(){
		Plateau copie = new Plateau(this);
		int nbVide = copie.manhattanDistI(0);
		int nbPermutations = 0;
		int compteur = 1;
		while(!copie.estResolu()){
			nbPermutations += copie.placement(compteur);
			compteur++;
		}
		return ((nbVide%2)==(nbPermutations%2));
	}

	/**
	 * Renvoie un tableau de boolean correspondant au deplacement possibles : tab[0] = haut; 1 = gauche; 2 = droite; 3 = bas
	 * @return
	 */
	public boolean[] deplacementsPossibles(){
		boolean[] tab = new boolean[4];
		int[] pos0 = this.getPosPl(0);
		for (int i=0; i<4; i++){
			//ligne haut
			if (pos0[1]==0){
				tab[0]=false;
			} else {
				tab[0]=true;
			}
			//colonne gauche
			if (pos0[0]==0){
				tab[1]=false;
			} else {
				tab[1]=true;
			}
			//colonne droite
			if (pos0[0]==this.n-1){
				tab[2]=false;
			} else {
				tab[2]=true;
			}
			//ligne bas
			if (pos0[1]==this.n-1){
				tab[3]=false;
			} else {
				tab[3]=true;
			}
		}
		return tab;	
	}

	/**
	 * Fonction effectuant le deplacement donne en argument
	 * @param s
	 */
	public void deplace(String s){
		Deplacement move = new Deplacement(s);
		this.listeDeplacements.add(s);
		this.profondeur++;
		int[] pos0 = this.getPosPl(0);
		int[] posFin = new int[2];
		posFin[0] = pos0[0];
		posFin[1] = pos0[1];
		if (move.getInt()==0){
			posFin[1] -= 1;
			this.permutation(pos0, posFin);
		} else if (move.getInt()==1){
			posFin[0] -=1;
			this.permutation(pos0, posFin);
		} else if (move.getInt()==2){
			posFin[0] += 1;
			this.permutation(pos0, posFin);
		} else {
			posFin[1] += 1;
			this.permutation(pos0, posFin);
		}
	}


	/**
	 * fonction donnant la somme de hashage d'un plateau 
	 * @return
	 */
	public String hashSomme(){
		String somme = "-";
		for (int i=0; i<this.n; i++){
			for(int j=0;j<this.n;j++){
				somme += this.plateau[i][j]+"-";
			}
		}
		//System.out.println(somme);
		return somme;
	}

	/**
	 * Fonction donnant l'ensemble des deplacements effectues
	 * @return
	 */
	public String deplacementsEffectues(){
		String sol = "";
		Iterator<String> iter = this.listeDeplacements.iterator();
		while(iter.hasNext()){
			sol += iter.next();
		}
		return sol;
	}

	/**
	 * Fonction affichant le plateau
	 */
	public void afficher() {
		for (int j=0; j<n; j++){
			for (int i=0; i<n; i++){				
				System.out.print(this.plateau[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}


