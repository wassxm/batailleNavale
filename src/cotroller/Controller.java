package src.cotroller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import src.model.ModelJeu;
import src.model.Player;
import src.model.Point;

public class Controller implements MouseListener {
	ModelJeu model;
	public Controller(ModelJeu model) 
	{
		this.model = model ;
	}
	public void mouseClicked(MouseEvent e) {
		JPanel source = (JPanel) e.getSource();
		int row = (int) source.getClientProperty("row");
		int col = (int) source.getClientProperty("col");
		int valeur = (int) source.getClientProperty("valeur");

		if (e.getButton() == MouseEvent.BUTTON1) {
			
				Player currentPlayer = model.getCurrentPlayer();
				if (currentPlayer != model.getOrdinateur()) {
					System.out.println("Clic gauche sur la case (" + row + ", " + col + ") de valeur " + valeur);
					Point p = new Point(row, col);
					model.execute(p);
					currentPlayer = model.getCurrentPlayer();
					if (currentPlayer == model.getOrdinateur()) {
						p = currentPlayer.generate();
						//si la case est deja joué on l'ignore et on choisi uune autre case non jouée
						while(model.getHumain().getGrille().getValeurCase(p)==2) {
							p = currentPlayer.generate();
						}
						model.execute(p);
						while (model.isEsttoucher() && !model.isOver()) {
							p = currentPlayer.generate();
							model.execute(p);
						}
					}
				}
			

		} 
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	
}
