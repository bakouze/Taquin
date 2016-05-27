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

	@SuppressWarnings("unchecked")
	public Plateau(Plateau plateau){
		this.dist = plateau.dist;
		this.n=plateau.n;
		this.plateau = plateau.getPosition().clone();
		this.etatFinal = plateau.getPosFinale().clone();
		this.profondeur = plateau.getProfondeur();
		this.listeDeplacements=(LinkedList<String>) plateau.listeDeplacements.clone();
	}
	
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
	 * Constructeur à base de deux tableaux de 9 entiers
	 */
//	public Plateau(int n, int[] pl, int[] sol){
//		this.listeDeplacements=new LinkedList<String>();
//		this.n=n;
//		boolean test = true;
//		for(int j=0;j<9;j++){
//			int temp = pl[j];
//			int tempSol = sol[j];
//			if(temp<0||temp>8||tempSol<0||tempSol>8){
//				test= false;
//				System.out.println("out of bound");
//			}
//			for(int t=j+1;t<9;t++){
//				int temp2 = pl[t];
//				int temp2Sol = sol[t];
//				if(temp == temp2||tempSol == temp2Sol){
//					test = false;
//				}
//			}
//		}
//		if(test){
//			this.plateau = pl;
//			this.etatFinal = sol;
//			int[][] d = {{0,1,2,1,2,3,2,3,4},
//					{1,0,1,2,1,2,3,2,3},
//					{2,1,0,3,2,1,4,3,2},
//					{1,2,3,0,1,2,1,2,3},
//					{2,1,2,1,0,1,2,1,2},
//					{3,2,1,2,1,0,3,2,1},
//					{2,3,4,1,2,3,0,1,2},
//					{3,2,3,2,1,2,1,0,1},
//					{4,3,2,3,2,1,2,1,0}};
//			this.dist = d;
//		}
//		else{
//			System.out.println("erreur chiffres !");
//		}
//	}

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

	//modelisation des deplacements
	/**
	 * calcule le nombre de deplacement possible suivant la position de la case vide (0)
	 * @return
	 */
	public int nbDeplacements(){
		if(this.getPosPl(0)==0||this.getPosPl(0)==2||this.getPosPl(0)==6||this.getPosPl(0)==8){
			return 2;
		}
		else if(this.getPosPl(0)==1||this.getPosPl(0)==3||this.getPosPl(0)==5||this.getPosPl(0)==7){
			return 3;
		}
		else{
			return 4;
		}
	}



	/**
	 * 
	 */
	public boolean[] deplacementsPossibles(){
		boolean[] tab = new boolean[4];
		for(int i=0;i<tab.length;i++){
			Deplacement deplacement = new Deplacement(i);
			tab[i]=this.deplaceBool(deplacement.getString());
		}
		return tab;
	}

	public boolean[] deplacementsPossiblesBis(){
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

	public void deplaceBis(String s){
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
	 * Effectue le delacement si possible et renvoie le boolean repondant a : le deplacement a ete effectue ?
	 * @param s
	 * @return
	 */
	public boolean deplaceBool(String s){
		Deplacement move = new Deplacement(s);
		int pos0 = this.getPosPl(0);
		if (pos0==0){
			if(move.getInt() == 0){
				return false;
			}
			else if(move.getInt() == 1){
				return false;
			}
			else if(move.getInt() == 2){
				return true;
			}
			else{
				return true;
			}
		}
		if (pos0==1){
			if(move.getInt() == 0){
				return false;
			}
			else if(move.getInt() == 1){
				return true;
			}
			else if(move.getInt() == 2){
				return true;
			}
			else{
				return true;
			}
		}
		if (pos0==2){
			if(move.getInt() == 0){
				return false;
			}
			else if(move.getInt() == 1){
				return true;
			}
			else if(move.getInt() == 2){
				return false;
			}
			else{
				return true;
			}
		}
		if (pos0==3){
			if(move.getInt() == 0){
				return true;
			}
			else if(move.getInt() == 1){
				return false;
			}
			else if(move.getInt() == 2){
				return true;
			}
			else{
				return true;
			}
		}
		if (pos0==4){
			if(move.getInt() == 0){
				return true;
			}
			else if(move.getInt() == 1){
				return true;
			}
			else if(move.getInt() == 2){
				return true;
			}
			else{
				return true;
			}
		}
		if (pos0==5){
			if(move.getInt() == 0){
				return true;
			}
			else if(move.getInt() == 1){
				return true;
			}
			else if(move.getInt() == 2){
				return false;
			}
			else{
				return true;
			}
		}
		if (pos0==6){
			if(move.getInt() == 0){
				return true;
			}
			else if(move.getInt() == 1){
				return false;
			}
			else if(move.getInt() == 2){
				return true;
			}
			else{
				return false;
			}
		}
		if (pos0==7){
			if(move.getInt() == 0){
				return true;
			}
			else if(move.getInt() == 1){
				return true;
			}
			else if(move.getInt() == 2){
				return true;
			}
			else{
				return false;
			}
		}
		if (pos0==8){
			if(move.getInt() == 0){
				return true;
			}
			else if(move.getInt() == 1){
				return true;
			}
			else if(move.getInt() == 2){
				return false;
			}
			else{
				return false;
			}
		} else {
			return false;
		}
	}


	public void deplace(String s){
		this.listeDeplacements.add(s);
		Deplacement move = new Deplacement(s);
		this.profondeur++;
		int pos0 = this.getPosPl(0);
		if (pos0==0){
			if(this.deplaceBool(s)){
				if(move.getInt() == 2){
					this.permutation(0, 1);
				}
				else{
					this.permutation(0, 3);
				}
			}
		}
		if (pos0==1){
			if(this.deplaceBool(s)){
				if(move.getInt() == 1){
					this.permutation(1, 0);
				}
				else if(move.getInt() == 2){
					this.permutation(1, 2);
				}
				else{
					this.permutation(1, 4);
				}
			}
		}
		if (pos0==2){
			if(this.deplaceBool(s)){
				if(move.getInt() == 1){
					this.permutation(2, 1);
				}
				else{
					this.permutation(2, 5);
				}
			}
		}
		if (pos0==3){
			if(this.deplaceBool(s)){
				if(move.getInt() == 0){
					this.permutation(3,0);
				}
				else if(move.getInt() == 2){
					this.permutation(3, 4);
				}
				else{
					this.permutation(3, 6);
				}
			}
		}
		if (pos0==4){
			if(this.deplaceBool(s)){
				if(move.getInt() == 0){
					this.permutation(4, 1);
				}
				else if(move.getInt() == 1){
					this.permutation(4, 3);
				}
				else if(move.getInt() == 2){
					this.permutation(4, 5);
				}
				else{
					this.permutation(4, 7);
				}
			}
		}
		if (pos0==5){
			if(this.deplaceBool(s)){
				if(move.getInt() == 0){
					this.permutation(5, 2);
				}
				else if(move.getInt() == 1){
					this.permutation(5, 4);
				}
				else{
					this.permutation(5, 8);
				}
			}
		}
		if (pos0==6){
			if(this.deplaceBool(s)){
				if(move.getInt() == 0){
					this.permutation(6, 3);
				}
				else{
					this.permutation(6, 7);
				}
			}
		}
		if (pos0==7){
			if(this.deplaceBool(s)){
				if(move.getInt() == 0){
					this.permutation(7, 4);
				}
				else if(move.getInt() == 1){
					this.permutation(7, 6);
				}
				else if(move.getInt() == 2){
					this.permutation(7, 8);
				}
			}
		}
		if (pos0==8){
			if(this.deplaceBool(s)){
				if(move.getInt() == 0){
					this.permutation(8, 5);
				}
				else if(move.getInt() == 1){
					this.permutation(8, 7);
				}
			}
		}	
	}

	public long hashSomme(){
		long somme = 0;
		for (int i=0; i<this.plateau.length; i++){
			somme += this.plateau[i]*Math.pow(10, this.plateau.length-1-i);
		}
		return somme;
	}

	public String deplacementsEffectues(){
		String sol = "";
		Iterator<String> iter = this.listeDeplacements.iterator();
		while(iter.hasNext()){
			sol += iter.next();
		}
		return sol;
	}
}

