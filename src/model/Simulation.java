package src.model;

public class Simulation {

	private ModelJeu jeu;

	public Simulation(ModelJeu j) {
		this.jeu = j;
	}

	@SuppressWarnings("static-access")
	public void play() {
		while (!jeu.isOver()) {

			Player currentPlayer = jeu.getCurrentPlayer();
			Point coup = currentPlayer.generate();

			if (currentPlayer.isValid(coup)) {

				jeu.execute(coup);
				if(jeu.getCurrentPlayer() == jeu.getHumain()){
				jeu.affichageGrille(jeu.getCurrentPlayer().getGrille());
				}
				

				Player winner = jeu.getwinner();
				if (winner != null) {
					System.out.println("Le joueur(euse) " + winner.getNom() + " a gagné(e) !");
				}
			}
		}
	}

}
