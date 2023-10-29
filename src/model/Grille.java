package src.model;

public class Grille {
	public static final int tailleGrille = 10;

	private int[][] grilleJeu;
	/**
	 * initialisation
	 */
	public Grille() {
		this.grilleJeu = new int[tailleGrille][tailleGrille];
	}
	/**
	 * initialisation
	 */
	public Grille(int[][] grilleJeu) {
		this();
		for (int i =0;i<grilleJeu.length;i++) {
			for(int j = 0; j<grilleJeu[i].length;j++) {
				this.grilleJeu[i][j] = grilleJeu[i][j];
			}
		}
	}
	/**
	 * @param p le point 
	 * @return la valeur de la case du point p
	 */
	public int getValeurCase(Point p) {
		return grilleJeu[p.getX()][p.getY()];
	}
	/**
	 * 
	 * @param p le point
	 * @param c la valeur à effectuer
	 */
	public void setValeurCase(Point p, int c) {
		this.grilleJeu[p.getX()][p.getY()] = c;
	}
	public void setCase(int i ,int j ,int c) {
		this.grilleJeu[i][j] = c;
	}
	/**
	 * 
	 * @param i la coordonnée x
	 * @param j la coordonnée Y
	 * @return la valeur de la case (x,y)
	 */
	public int getcase(int i,int j) {
		return grilleJeu[i][j];
	}
	public void setGrilleJeu(int[][] grilleJeu) {
		this.grilleJeu = grilleJeu;
	}
	public int[][] getGrilleJeu() {
		return grilleJeu;
	}
	
}