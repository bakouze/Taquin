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
	private int[] plateau;

	/**
	 * Attribut modelisant l'etat final attendu pour le plateau
	 */
	private int[] etatFinal;

	/**
	 * Attribut modelisant les distances de manhattan
	 */
	private int[][] dist;

	/**
	 * nombre de coups utilis�s pour atteindre ce plateau
	 */
	private int profondeur;

	/**
	 * liste des mouvements effectues
	 */
	private LinkedList<String> listeDeplacements;

	private int n;

	/**
	 * Constructeur par défaut : construit un plateau de taquin résolu
	 */
	public Plateau(){
		int[] pl = new int[9];
		for(int i=0;i<9;i++){
			pl[i]=i;
		}
		this.plateau = pl;
		this.etatFinal = pl;
		this.listeDeplacements=new LinkedList<String>();
		int[][] d = {{0,1,2,1,2,3,2,3,4},
				{1,0,1,2,1,2,3,2,3},
				{2,1,0,3,2,1,4,3,2},
				{1,2,3,0,1,2,1,2,3},
				{2,1,2,1,0,1,2,1,2},
				{3,2,1,2,1,0,3,2,1},
				{2,3,4,1,2,3,0,1,2},
				{3,2,3,2,1,2,1,0,1},
				{4,3,2,3,2,1,2,1,0}};
		this.dist = d;
	}

	/**
	 * constructeur clonant un plateau existant
	 * @param plateau
	 */
	@SuppressWarnings("unchecked")
	public Plateau(Plateau plateau){
		this.dist = plateau.dist;
		this.n=plateau.n;
		this.plateau = plateau.getPosition().clone();
		this.etatFinal = plateau.getPosFinale().clone();
		this.profondeur = plateau.getProfondeur();
		this.listeDeplacements=(LinkedList<String>) plateau.listeDeplacements.clone();
	}

	/**
	 * Constructeur à partir d'un entier n (le nombre de coup à obtenir), et de deux tableaux d'entiers (état initial et final du plateau)
	 * @param n
	 * @param pl
	 * @param sol
	 */
	public Plateau (int n, int[] pl, int[] sol){
		this.n=n;
		this.listeDeplacements=new LinkedList<String>();
		boolean test = true;
		for(int j=0;j<n*n;j++){
			int temp = pl[j];
			int tempSol = sol[j];
			if(temp<0||temp>n*n-1||tempSol<0||tempSol>n*n-1){
				test= false;
				System.out.println("out of bound");
			}
			for(int t=j+1;t<n*n;t++){
				int temp2 = pl[t];
				int temp2Sol = sol[t];
				if(temp == temp2||tempSol == temp2Sol){
					test = false;
				}
			}
		}
		if(test){
			this.plateau = pl;
			this.etatFinal = sol;
			int[][] d = new int[n*n][n*n];
			for (int i = 0; i < n*n; i++){
				for (int j = 0; j < n*n; j++){
					d[i][j] = Math.abs(j/n-i/n)+Math.abs(j%n-i%n);
				}
			}
			this.dist = d;
		}
		else{
			System.out.println("erreur chiffres !");
		}
	}

	/**
	 * Transforme une position int[9] en int unique
	 * @return
	 */
	public int intFromPosition(){
		int res = 0;
		for (int i = 0; i < n*n; i++){
			res += this.getPosition()[i]*Math.pow(10, i);
		}
		return res;
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
	public int[] getPosition(){
		return this.plateau;
	}

	/**
	 * getter pour la position finale du plateau
	 */
	public int[] getPosFinale(){
		return this.etatFinal;
	}

	/**
	 * return the index of the element a in plateau
	 * @param a
	 * @return
	 */
	private int getPosPl(int a){
		for(int i=0;i<n*n;i++){
			if(a==this.plateau[i]){
				return i;
			}
		}
		System.out.println("erreur getPos");
		return -1;
	}

	/**
	 * return the index of the element a in solution
	 * @param a
	 * @return
	 */
	private int getPosSol(int a){
		for(int i=0;i<n*n;i++){
			if(a==this.etatFinal[i]){
				return i;
			}
		}
		System.out.println("erreur getPos");
		return -1;
	}

	/**
	 * return the manhattan distance of the element a to the element b
	 * @param a
	 * @return
	 */
	private int manhattanDistI(int a){
		int i = this.getPosPl(a);
		int j = this.getPosSol(a);
		return this.dist[j][i];
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
	 * Echange les cases i et j du plateau
	 * @param i
	 * @param j
	 */
	private void permutation(int i, int j){
		int temp = this.plateau[i];
		this.plateau[i]=this.plateau[j];
		this.plateau[j]=temp;
	}

	/**
	 * Place la case a si elle n'est pas bien place avec une permutation. Renvoie le nombre de permutation necessaire.
	 * @param a
	 * @return
	 */
	private int placement(int a){
		if(this.getPosPl(a)!=this.getPosSol(a)){
			int i = this.getPosPl(a);
			int j = this.getPosSol(a);
			this.permutation(i, j);
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
		for(int i=0;i<n*n;i++){
			test = test && (this.plateau[i]==this.etatFinal[i]);
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
		int pos0 = this.getPosPl(0);
		for (int i=0; i<4; i++){
			//ligne haut
			if (pos0/this.n==0){
				tab[0]=false;
			} else {
				tab[0]=true;
			}
			//colonne gauche
			if (pos0%this.n==0){
				tab[1]=false;
			} else {
				tab[1]=true;
			}
			//colonne droite
			if (pos0%this.n==this.n-1){
				tab[2]=false;
			} else {
				tab[2]=true;
			}
			//ligne bas
			if (pos0/this.n==this.n-1){
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
		this.listeDeplacements.add(s);
		Deplacement move = new Deplacement(s);
		this.profondeur++;
		int pos0 = this.getPosPl(0);
		if (move.getInt()==0){
			this.permutation(pos0, pos0-this.n);
		} else if (move.getInt()==1){
			this.permutation(pos0, pos0-1);
		} else if (move.getInt()==2){
			this.permutation(pos0, pos0+1);
		} else {
			this.permutation(pos0, pos0+this.n);
		}
	}


	/**
	 * fonction donnant la somme de hashage d'un plateau 
	 * @return
	 */
	public String hashSomme(){
		String somme = "-";
		for (int i=0; i<this.plateau.length; i++){
			somme += this.plateau[i]+"-";
		}
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
				System.out.print(this.plateau[i+j*n]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}


