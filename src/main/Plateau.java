package main;
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
	 * Constructeur par défaut : construit un plateau de taquin résolu
	 */
	public Plateau(){
		int[] pl = new int[9];
		for(int i=0;i<9;i++){
			pl[i]=i;
		}
		this.plateau = pl;
		this.etatFinal = pl;
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
	 * Constructeur à base de deux tableaux de 9 entiers
	 */
	public Plateau(int[] pl, int[] sol){
		boolean test = true;
		for(int j=0;j<9;j++){
			int temp = pl[j];
			int tempSol = sol[j];
			if(temp<0||temp>8||tempSol<0||tempSol>8){
				test= false;
				System.out.println("out of bound");
			}
			for(int t=j+1;t<9;t++){
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
		else{
			System.out.println("erreur chiffres !");
		}
	}
	
	/**
	 * return the index of the element a in plateau
	 * @param a
	 * @return
	 */
	private int getPosPl(int a){
		for(int i=0;i<9;i++){
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
		for(int i=0;i<9;i++){
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
		for(int i = 1;i<9;i++){
			temp += this.manhattanDistI(i);
		}
		return temp;
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
	private boolean estResolu(){
		boolean test = true;
		for(int i=0;i<9;i++){
			test = test && (this.plateau[i]==this.etatFinal[i]);
		}
		return test;
	}
	
	/**
	 * Verifie que le plateau est soluble
	 * @return
	 */
	public boolean estSoluble(){
		int nbVide = this.manhattanDistI(0);
		int nbPermutations = 0;
		int compteur = 1;
		while(!this.estResolu()){
			nbPermutations += this.placement(compteur);
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
	 * Effectue le delacement si possible et renvoie le boolean repondant a : le deplacement a ete effectue ?
	 * @param s
	 * @return
	 */
	public boolean deplace(String s){
		Deplacement move = new Deplacement(s);
		switch (this.getPosPl(0)){
		case 0:
			if(move.getInt() == 0){
				return false;
			}
			else if(move.getInt() == 1){
				return false;
			}
			else if(move.getInt() == 2){
				this.permutation(0, 1);
				return true;
			}
			else{
				this.permutation(0, 3);
				return true;
			}
		case 1:
			if(move.getInt() == 0){
				return false;
			}
			else if(move.getInt() == 1){
				this.permutation(1, 0);
				return true;
			}
			else if(move.getInt() == 2){
				this.permutation(1, 2);
				return true;
			}
			else{
				this.permutation(1, 4);
				return true;
			}
		case 2:
			if(move.getInt() == 0){
				return false;
			}
			else if(move.getInt() == 1){
				this.permutation(2, 1);
				return true;
			}
			else if(move.getInt() == 2){
				return false;
			}
			else{
				this.permutation(2, 5);
				return true;
			}
		case 3:
			if(move.getInt() == 0){
				this.permutation(3,0);
				return true;
			}
			else if(move.getInt() == 1){
				return false;
			}
			else if(move.getInt() == 2){
				this.permutation(3, 4);
				return true;
			}
			else{
				this.permutation(3, 6);
				return true;
			}
		case 4:
			if(move.getInt() == 0){
				this.permutation(4, 1);
				return true;
			}
			else if(move.getInt() == 1){
				this.permutation(4, 3);
				return true;
			}
			else if(move.getInt() == 2){
				this.permutation(4, 5);
				return true;
			}
			else{
				this.permutation(4, 7);
				return true;
			}
		case 5:
			if(move.getInt() == 0){
				this.permutation(5, 2);
				return true;
			}
			else if(move.getInt() == 1){
				this.permutation(5, 4);
				return true;
			}
			else if(move.getInt() == 2){
				return false;
			}
			else{
				this.permutation(5, 8);
				return true;
			}
		case 6:
			if(move.getInt() == 0){
				this.permutation(6, 3);
				return true;
			}
			else if(move.getInt() == 1){
				return false;
			}
			else if(move.getInt() == 2){
				this.permutation(6, 7);
				return true;
			}
			else{
				return false;
			}
		case 7:
			if(move.getInt() == 0){
				this.permutation(7, 4);
				return true;
			}
			else if(move.getInt() == 1){
				this.permutation(7, 6);
				return true;
			}
			else if(move.getInt() == 2){
				this.permutation(7, 8);
				return true;
			}
			else{
				return false;
			}
		case 8:
			if(move.getInt() == 0){
				this.permutation(8, 5);
				return true;
			}
			else if(move.getInt() == 1){
				this.permutation(8, 7);
				return true;
			}
			else if(move.getInt() == 2){
				return false;
			}
			else{
				return false;
			}
		default: 
			return false;
		}
		
	}
}
