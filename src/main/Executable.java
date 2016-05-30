package main;

import java.io.FileNotFoundException;

public class Executable {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Reader rd = new Reader("InputFiles/sp0400.txt");
		Plateau p = new Plateau(rd.getNb(), rd.getPlateau(),rd.getSolution());
		p.afficher();
		System.out.println(p.manhattanDist());
		p.afficher();
		System.out.println(p.estSoluble());
		p.afficher();
		Probleme probleme = new Probleme(p);
		p.afficher();
		probleme.solve();
		System.out.println(probleme.getSolution().deplacementsEffectues());
		System.out.println(probleme.getSolution().getProfondeur());
	}

}
