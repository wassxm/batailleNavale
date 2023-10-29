package src.model;

import java.util.ArrayList;

public abstract class AbstractPlayer implements Player {

	protected Grille grille;
	protected  ArrayList<Navire> arrayOfNavire = new ArrayList<Navire>();
	public AbstractPlayer() {
		this.grille = new Grille();
		this.arrayOfNavire = new ArrayList<>();
	}
	@Override
	public boolean isValid(Point p) {
		return (p.getX() < 10 && p.getX() >= 0) && (p.getY() < 10 && p.getY() >= 0);
		
	}

	@Override
	public boolean isValidNavire(Navire n) {
		if (n.getDirection() == 1) {
			for (int i = 0; i < n.getTaille(); i++) {
				if (n.getPos().getX() + i < 0 || n.getPos().getX() + i > 9 || n.getPos().getY() < 0
						|| n.getPos().getY() > 9
						|| (this.grille.getValeurCase(new Point(n.getPos().getX() + i, n.getPos().getY()))) == 1) {

					return false;
				}
			}
		}
		else if (n.getDirection() == 0) {
			for (int i = 0; i < n.getTaille(); i++) {
				if (n.getPos().getX() < 0 || n.getPos().getX() > 9 || n.getPos().getY() + i < 0
						|| n.getPos().getY() + i > 9
						|| (this.grille.getValeurCase(new Point(n.getPos().getX(), n.getPos().getY() + i))) == 1) {

					return false;
				}
			}
		}
		return true;
	}



	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}


	public ArrayList<Navire> getArrayOfNavire() {
		return arrayOfNavire;
	}

	public void setArrayOfNavire(ArrayList<Navire> arrayOfNavire ) {
		this.arrayOfNavire = arrayOfNavire;
	}
	@Override
	public boolean allNavireCoule() {
		for(Navire n : this.arrayOfNavire) {
			if(!n.isCoule(getGrille())) {
				return false;
			}
		}
		return true;
	}
	@Override
	public void setValeurCase(Point p , int c) {
		this.grille.setValeurCase(p, c);
	}

}
