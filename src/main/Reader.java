package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe permettant la lecture du fichier texte d'entree
 * @author guillaume
 *
 */
public class Reader {
	
	/**
	 * Le premier chiffre du fichier (a quoi sert-il ??)
	 */
	private int nb;
	
	/**
	 * Chiffres du plateau de taquin
	 */
	private int[] pl;
	
	/**
	 * chiffre du verteur de solution
	 */
	private int[] sol;
	
	/**
	 * Constructeur
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public Reader(String fileName) throws FileNotFoundException{
		//file reading
		Scanner sc = new Scanner(new File(fileName));
	    sc.useDelimiter("");
	    int[] temp = new int[19];
	    int i = 0;
	    while (sc.hasNext()) {
	    	int a = sc.nextInt();
	    	temp[i]=a;
	    	i++;
	    }
	    sc.close();
	    //nb attribution
	    this.nb = temp[0];
	    //pl attribution
	    int[] p = new int[9];
	    for(int j = 1;j<10;j++){
	    	p[j-1]=temp[j];
	    }
	    this.pl = p;
	    //sol attribution
	    int[] s = new int[9];
	    for(int j = 10;j<19;j++){
	    	s[j-10]=temp[j];
	    }
	    this.sol=s; 
	}
	
	/**
	 * Getter for pl attribute
	 * @return
	 */
	public int[] getPlateau(){
		return this.pl;
	}
	
	/**
	 * Getter for sol attribute
	 * @return
	 */
	public int[] getSolution(){
		return this.sol;
	}
	
	/**
	 * Getter for nb attribute
	 * @return
	 */
	public int getNb(){
		return this.nb;
	}

}
