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
	 * Constructeur à base de neuf entier qui rempli le plateau de gauche à droite et de haut en bas
	 */
	public Plateau(int a,int b,int c,int d,int e,int f,int g,int h,int i){
		int[] pl = new int[9];
		pl[0]=a;
		pl[1]=b;
		pl[2]=c;
		pl[3]=d;
		pl[4]=e;
		pl[5]=f;
		pl[6]=g;
		pl[7]=h;
		pl[8]=i;
		boolean test = true;
		for(int j=0;j<9;j++){
			int temp = pl[j];
			if(temp<0||temp>8){
				test= false;
			}
			for(int t=j;t<9;t++){
				int temp2 = pl[t];
				if(temp == temp2){
					test = false;
				}
			}
		}
		if(test){
			this.plateau = pl;
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
	public int getPos(int a){
		for(int i=0;i<9;i++){
			if(a==this.plateau[i]){
				return i;
			}
		}
		System.out.println("erreur getPos");
		return -1;
	}

	/**
	 * return the manhattan distance of the element a
	 * @param a
	 * @return
	 */
	public int manhattanDistI(int a){
		int i = this.getPos(a);
		return this.dist[a][i];
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

	//test
}
