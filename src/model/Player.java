package src.model;

import java.util.ArrayList;

public interface Player {
	/**
	 * 
	 * @param p le point à verifier
	 * @return true si le hoeur peut jouer le point p
	 */
	public boolean isValid(Point p);
	/**
	 * 
	 * @param n le navire à vériier
	 * @return true si le placement du navire est valide et false sinon
	 */
	public boolean isValidNavire(Navire n);
	/**
	 * 
	 * @return un point généré à partir du choix du joueur 
	 */
	public Point generate();
	/**
	 * 
	 * @return le nom
	 */
	public String getNom();
	/**
	 * 
	 * @return la grille 
	 */
	public Grille getGrille() ;
	/**
	 * 
	 * @param grille la nouvelle grille
	 */
	public void setGrille(Grille grille) ;
	/**
	 * 
	 * @return la liste des navires
	 */
	public ArrayList<Navire> getArrayOfNavire() ;
	/**
	 * 
	 * @param arrayOfNavire la nouvelle liste 
	 */
	public void setArrayOfNavire(ArrayList<Navire> arrayOfNavire) ;

	/**
	 * 
	 * @return true si tout les navires sont coulés
	 */
	public boolean allNavireCoule();
	/**
	 * 
	 * @param p le point à modifier 
	 * @param c la valeur à mettre dans la case p
	 * met à jour la case p dans la grille du joueur avec la valeur c
	 */
	public void setValeurCase(Point p,int c);
	
}