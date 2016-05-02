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
	 * Constructeur par défaut : construit un plateau de taquin résolu
	 */
	public Plateau(){
		int[] pl = new int[9];
		for(int i=0;i<9;i++){
			pl[i]=i;
		}
		this.plateau = pl;
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
	


}
