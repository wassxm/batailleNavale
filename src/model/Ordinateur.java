package src.model;

import java.util.Random;

public class Ordinateur extends AbstractPlayer implements Player {

	public Ordinateur() {
		super();
	}
	
		@Override

	public Point generate() {

		Random random = new Random();
		int x = random.nextInt(10), y = random.nextInt(10);
		return new Point(x, y);

	}
	
	@Override
	public String getNom() {

		return "Ordinateur";
	}

	

}