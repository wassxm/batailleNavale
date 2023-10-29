package src.model;

import java.util.*;

public class Humain extends AbstractPlayer implements Player {

	private String nom;

	
	public Humain(String nom) {
		super();
		this.nom = nom;
		
	}
	public Humain(String nom,Grille g) {
		super();
		this.grille = g;
		this.nom = nom;
		
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override

	public Point generate() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
			System.out.println("veuillez saisir x");
			int x = scanner.nextInt();
			System.out.println("veuillez saisir y");
			int y = scanner.nextInt();

			return new Point(x, y);
		}

	


	@Override
	public String getNom() {
		return this.nom;
	}

}