package main;

import java.io.FileNotFoundException;

public class Executable {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Reader rd = new Reader("InputFiles/sp009.txt");
		Plateau p = new Plateau(rd.getNb(), rd.getPlateau(),rd.getSolution());
		System.out.println(p.manhattanDist());
		System.out.println(p.estSoluble());
		Probleme probleme = new Probleme(p);
		probleme.solve();
		System.out.println(probleme.getSolution().deplacementsEffectues());
		System.out.println(probleme.getSolution().getProfondeur());
	}

}
