package src.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import src.util.AbstractModeleEcoutable;

public class ModelJeu extends AbstractModeleEcoutable {

	private Player humain, ordinateur, joueurCourent;
	/**
	* 
	* @return l'instance du joueur machine 
	*/
	public Player getOrdinateur() {
		return ordinateur;
	}
	/**
	* 
	* @return l'instance du joeur humain
	*/
	public Player getHumain() {
		return humain;
	}
	
	private Point pointjouer;
	private boolean esttoucher;
	
	/**
	* 
	* @param hm l'instance du joueur hmain
	* @param ord l'instace du joueur machine
	* @param gHm l'instance de la grille du joeur humain
	* @param gOrd l'instance de la grille du joeur machine
	*/
	public ModelJeu(Humain hm, Ordinateur ord) {
		this.humain = hm;
		this.ordinateur = ord;
		this.joueurCourent = hm;
	}
	/**
	* @return le dernier point joué
	*/
	public Point getPointjouer() {
		return pointjouer;
	}
	
	/**
	* @retun un booléen True si le joueur a touché un navire de l'adversaire et False sinon
	*/
	public boolean isEsttoucher() {
		return esttoucher;
	}
	/**
	* change le joeur actuel
	*/
	public void switchPlayer() {
		if (this.joueurCourent == this.humain) {
			this.joueurCourent = this.ordinateur;
			
		}

		else {

			this.joueurCourent = this.humain;
			

		}
	}
	/**
	* 
	* @param p le point à jouer 
	*/
	public void execute(Point p) {
		//s'il existe un navire sur la case choisi
		if(this.getCurrentPlayer() == this.humain) {
			if(this.getOrdinateur().getGrille().getValeurCase(p) == 1) {
				this.esttoucher = true;
				//le point sera un point joué
				System.out.println("AVANT : "+this.ordinateur.getGrille().getValeurCase(p));
				this.ordinateur.setValeurCase(p, 2);
				System.out.println("APRES : "+this.ordinateur.getGrille().getValeurCase(p));
				
				
				System.out.println("L'Humain A JOUER Coup TOUCHE");
				

			}
			else {
				
				this.esttoucher = false;
				System.out.println("L'Humain A JOUER Coup RATE");
				
				this.switchPlayer();
				
				
			}
		}
		else {
			System.out.println("Voici le point joue par l'ordinateur  : "+p);
			if(this.getHumain().getGrille().getValeurCase(p)==1) {
				//le point sera un point joué
				this.humain.setValeurCase(p, 2);
				this.esttoucher = true;
				System.out.println("L'ordinateur A JOUER Coup TOUCHE");
				
			}
			else {
				//marquer la case comme jouer pour eviter que l'ordianteur la choisisse la prochaine fois
				if (this.getHumain().getGrille().getValeurCase(p)==0) {
					this.humain.setValeurCase(p, 2);
				}
				System.out.println("L'ordinateur A JOUER RATE");
				this.esttoucher = false;
				
				this.switchPlayer();
			}
		}
		
		
		this.pointjouer = p;
		fireChange();
	}
	/**
	* @return le joeur gagnant si la partie est fini sinon Null
	*/
	public Player getwinner() {
		//si tout les navire du joueur humain sount coulés alors l'ordianteur a gagné
		if (this.humain.allNavireCoule()) {
			return this.ordinateur;
		}
		//si tout les navire du joueur ordinateur sount coulés alors l'humain a gagné
		if (this.ordinateur.allNavireCoule()) {
			return this.humain;
		}
		//la partie n'est pas encore finie
		return null;
	}
	/**
	* 
	* @param p le joeur lequel on veut remplire sa grille
	* la grille sera replie comme ceci :
	* 0 : il n'y a pas de bateau 
	* 1 : presence de bateau sur la case
	* 2 : case déja joué(l'ors de la création y'aura pas de cases == 2)
	*/
	public  void remplissageGrille(Player p) {
		int[] LONGUEURS_BATEAUX = { 2, 3, 3, 4, 5 };
		Random rand = new Random();
		Grille cases = new Grille();
		ArrayList<Navire> arrayOfNavire = new ArrayList<Navire>();
		// On place les bateaux de longueur 2 à 5 et 6 qui sera ramené à 3
		for (int i = 0; i < 5; i++) {
			int longueur = LONGUEURS_BATEAUX[i];
			if (longueur == 6)
				longueur = 3;

			// On continue tant que le placement n'est pas ok
			boolean placeOK = false;
			while (!placeOK) {
				// Tirage au sort de la case de début
				int x = rand.nextInt(10);
				int y = rand.nextInt(10);

				// Tirage au sort de l'orientation : 0 horizontal et 1 vertical
				int orientation = rand.nextInt(2);

				// On vérifie si le bateau sort de la grille

				// On parcourt l'emplacement du bateau si il ne dépasse pas la grille
				if (!verifyDepassement(x,y,longueur,orientation)) {
					
					// Si aucun bateau adjacent, on peut placer le bateau
					if (!verifyAdjacence(x, y, longueur, cases, orientation, i)) {
						arrayOfNavire.add(new Navire(longueur, y, x, orientation));
						for (int j = 0; j < longueur; j++) {
							if (orientation == 0) { // Horizontal
								cases.setValeurCase(new Point(y, x + j), 1);
								//arrayOfNavire.add(new Navire(longueur, y, x+j, 0));

							} else { // Vertical
								cases.setValeurCase(new Point(y + j, x), 1);
								//arrayOfNavire.add(new Navire(longueur, y, x+j, 1));
							}
						}
						placeOK = true;
					}
				}
			}
		}
		p.setArrayOfNavire(arrayOfNavire);
		p.setGrille(cases);

	}
	
	// on peut ecrire deux fonction qui verifie si deux case sont adjacente ou pas (vertical et horizontal)
	// et une autre fonction qui permet de generer les bateaux

	public static void affichageGrille(Grille cases) {

		System.out.print("  ");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 10; j++) {
				if (cases.getValeurCase(new Point(i, j)) == 0 || cases.getValeurCase(new Point(i, j)) == 2) {
					System.out.print("!" + " ");
				} else {
					System.out.print(cases.getValeurCase(new Point(i, j)) + " ");
				}
			}
			System.out.println();
		}
	}
	public static void affichageGrilleOrdinateur(Grille cases){

		System.out.print("  ");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 10; j++) {
				
					System.out.print("_" + " ");
				
				
			}
			System.out.println();
		}
	}
	
	
	
	
	/**
	* 
	* @return true si la partie est finie et false sinon
	*/
	public boolean isOver() {
		if (getwinner() == null) {
			return false;
		} else {
			return true;
		}
	}
	/**
	* 
	* @return le joeur courant 
	*/
	public Player getCurrentPlayer() {
		return this.joueurCourent;
	}
	
	/**
	* 
	* @param x la coordonnée x du point du début de placement du navire
	* @param y la coordonnée y du point du début de placement du navire
	* @param longueur la longueur du navire à placer
	* @param cases la grille
	* @param orientation l'orientation de placement
	* @param i
	* @return true s'il ya déjà un bateau sur place et false sinon
	*/
	public static boolean verifyAdjacence(int x,int y,int longueur ,Grille cases,int orientation,int i ) {
		boolean bateauxAdjacents = false;
		if (orientation == 0) { // Horizontal
			for (int j = 0; j < longueur; j++) { // Tout le bateau de longueur longueur
				// Vérifier les cases adjacentes
				if ((x + j > 0 && cases.getValeurCase(new Point(y, x + j - 1)) != 0
						&& cases.getValeurCase(new Point(y, x + j - 1)) != i + 1)
						|| (x + j < 10 - 1 && cases.getValeurCase(new Point(y, x + j + 1)) != 0
								&& cases.getValeurCase(new Point(y, x + j + 1)) != i + 1)
						|| (y > 0 && cases.getValeurCase(new Point(y - 1, x + j)) != 0
								&& cases.getValeurCase(new Point(y - 1, x + j)) != i + 1)
						|| (y < 10 - 1 && cases.getValeurCase(new Point(y + 1, x + j)) != 0
								&& cases.getValeurCase(new Point(y + 1, x + j)) != i + 1)) {
					bateauxAdjacents = true;
					break;
				}
			}
		} else { // Vertical
			for (int j = 0; j < longueur; j++) { // Tout le bateau de longueur longueur
				// Vérifier les cases adjacentes
				if ((x > 0 && cases.getValeurCase(new Point(y + j, x - 1)) != 0
						&& cases.getValeurCase(new Point(y + j, x - 1)) != i + 1)
						|| (x < 10 - 1 && cases.getValeurCase(new Point(y + j, x + 1)) != 0
								&& cases.getValeurCase(new Point(y + j, x + 1)) != i + 1)
						|| (y + j > 0 && cases.getValeurCase(new Point(y + j - 1, x)) != 0
								&& cases.getValeurCase(new Point(y + j - 1, x)) != i + 1)
						|| (y + j < 10 - 1 && cases.getValeurCase(new Point(y + j + 1, x)) != 0
								&& cases.getValeurCase(new Point(y + j + 1, x)) != i + 1)) {
					bateauxAdjacents = true;
					break;
				}
			}
	}
		return bateauxAdjacents;
	}
	/**
	* 
	* @param x la coordonnée x du point du début de placement du navire
	* @param y la coordonnée y du point du début de placement du navire
	* @param longueur la longueur du navire à placer
	* @param cases la grille
	* @param orientation l'orientation de placement
	* @return true si le navire va dépasser la grille false sinon
	*/
	public static boolean verifyDepassement(int x,int y,int longueur,int orientation) {
		// On vérifie si le bateau sort de la grille
		if (((orientation == 0) && (x + longueur > 10)) || ((orientation == 1) && (y + longueur > 10))) {
			return true;
		}
		return false;
	}
	/**
	* initialisation des grilles des joueurs aléatoirement
	*/
	public void init() {
		remplissageGrille(humain);
		remplissageGrille(ordinateur);
		
	}
	/**
	* initialisation des grilles de l'ordinateur aléatoirement
	*/
	public void initManual() {
		remplissageGrille(ordinateur);
		
	}
	
	/*
	* mettre la case p de la grille du joueur humain à la valeur c
	*/
	public void setHumainCaseValue(Point p,int c) {
		this.humain.setValeurCase(p, c);
	}
	/**
	* 
	* @param girlle la grille à verifier
	* @return List de navire si la grille est bien structurée sinon renvoie null
	*/
	public static ArrayList<Navire> verifyGrille(Grille grille,int rows,int cols){
		ArrayList<Navire> navires = new ArrayList();
		Grille gr = new Grille(grille.getGrilleJeu());
		
		ArrayList<Integer> liste = new ArrayList<Integer>();
		liste.add(2);
		liste.add(3);
		liste.add(3);
		liste.add(4);
		liste.add(5);
		for(int i =0 ;i<rows;i++) {
			for(int j = 0;j<cols;j++) {
				if(gr.getcase(i, j) == 1) {
					Queue<Integer> queue = new LinkedList<>();
                    queue.add(i);
                    queue.add(j);
                    gr.setCase(i,j, 0);; // marque la case comme visitée pour éviter les doublons
                    int tailleNavire = 1;
                    int direction = 0;
                    while (!queue.isEmpty()) {
                    	int x = queue.remove();
                        int y = queue.remove();
                        if (x > 0 && gr.getcase(x-1, y) == 1) {
                            queue.add(x-1);
                            queue.add(y);
                            gr.setCase(x-1,y,0);
                            tailleNavire++;
                            direction = 1;
                        }
                        if (x < rows-1 && gr.getcase(x+1,y) == 1) {
                            queue.add(x+1);
                            queue.add(y);
                            gr.setCase(x+1,y,0);
                            tailleNavire++;
                            direction = 1;
                        }
                        if (y > 0 && gr.getcase(x,y-1) == 1) {
                            queue.add(x);
                            queue.add(y-1);
                            gr.setCase(x,y-1,0);
                            tailleNavire++;
                            direction = 0;
                        }
                        if (y < rows-1 && gr.getcase(x,y+1) == 1) {
                            queue.add(x);
                            queue.add(y+1);
                            gr.setCase(x,y+1,0);
                            tailleNavire++;
                            direction = 0;
                        }
                    }    
                    if(!liste.isEmpty()&& liste.contains(tailleNavire)) {
                    	System.out.println("JE REMOVE : "+tailleNavire+" dans :"+liste);
                    	liste.remove(Integer.valueOf(tailleNavire));
                    	navires.add(new Navire(tailleNavire, i, j, i));
                    }
                    else {
                    	System.out.println("TAILLE DEPASSE OU INVALIDE");
                    	return null;
                    }
				}
			}
		}
		System.out.println("TOUTE LES TAILLES SONT BONNE");
		if (liste.size() > 0) {
			System.out.print("Voici les taille qui rst : "+liste);
			return null;
		}
		
		return navires;
	}
}
