package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
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
	    LinkedList<Integer> temp = new LinkedList<Integer>();
	    while (sc.hasNextInt()) {
	    	int a = sc.nextInt();
	    	temp.add(a);
	    }
	    sc.close();
	    //nb attribution
	    this.nb = temp.pollFirst();
	    //pl attribution
	    int[] p = new int[this.nb*this.nb];
	    for(int j = 0;j<this.nb*this.nb;j++){
	    	p[j] = temp.pollFirst();
	    }
	    this.pl = p;
	    //sol attribution
	    int[] s = new int[this.nb*this.nb];
	    for(int j = 0;j<this.nb*this.nb;j++){
	    	s[j]=temp.pollFirst();
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
