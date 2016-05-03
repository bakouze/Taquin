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
			}
			for(int t=j;t<9;t++){
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
	public int getPosPl(int a){
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
	public int getPosSol(int a){
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
	public int manhattanDistI(int a){
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
		for(int i = 0;i<9;i++){
			temp += this.manhattanDistI(i);
		}
		return temp;
	}

}
