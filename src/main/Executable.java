package main;

import java.io.FileNotFoundException;

public class Executable {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Reader rd = new Reader("InputFiles/sp000.txt");
		Plateau p = new Plateau(rd.getPlateau(),rd.getSolution());
		System.out.println(p.manhattanDist());
		System.out.println(p.estSoluble());
	}

}
