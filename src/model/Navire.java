package src.model;

public class Navire {

	private int taille;
	private Point pos;
	private int direction;
	/**
	 * 
	 * @param taille la taille du navire
	 * @param x la position x du début de la place du navire
	 * @param y la position y du début de la place du navire
	 * @param direction la direction de placement
	 */
	public Navire(int taille, int x, int y, int direction) {
		this.pos = new Point(x, y);
		this.taille = taille;
		this.direction = direction;
	}
	/**
	 * 
	 * @return la position 
	 */
	public Point getPos() {
		return pos;
	}
	/**
	 * 
	 * @param pos la nouvelle position
	 */
	public void setPos(Point pos) {
		this.pos = pos;
	}
	/**
	 * 
	 * @return la taille
	 */
	public int getTaille() {
		return taille;
	}
	/**
	 * 
	 * @return la direction
	 */
	public int getDirection() {
		return direction;
	}
	@Override
	public String toString() {
		return this.pos.toString()+" taille : "+this.taille+" direction : "+this.direction;
	}
	/**
	 * 
	 * @return true i le point (x,y) appartient au navire
	 */
	public boolean isInNavire(int x,int y) {
		if(this.direction == 0) {//horizental
			if(x!=this.pos.getX() || !(this.pos.getY() <= y && y< this.getPos().getY()+this.taille))
				return false ;
		}
		else {
			if(y!=this.pos.getY() || !(this.pos.getX() <= x && x< this.getPos().getX()+this.taille))
				return false ;
		}
		return true;
	}
	/**
	 * 
	 * @return true si le navire est compeltemnt coulé dans la grille g et false sinon
	 */
	public boolean isCoule(Grille g) {
		if(direction==0) {//horizental
			for(int j =0 ; j<this.taille;j++) {
				if(g.getValeurCase(new Point(pos.getX(),pos.getY()+j))==1)
					return false;
			}
		}
		else {
			for(int j =0 ; j<taille;j++) {
				if(g.getValeurCase(new Point(pos.getX()+j,pos.getY()))==1)
					return false;
			}
		}
		return true;
	}
}