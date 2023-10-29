package src.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.PublicKey;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import src.cotroller.Controller;
import src.model.Grille;
import src.model.Humain;
import src.model.ModelJeu;
import src.model.Navire;
import src.model.Ordinateur;
import src.model.Player;
import src.model.Point;

public class ManualGrille extends JFrame {
	private static final int ROWS = 10;
	private static final int COLS = 10;
	
	private Grille grille;
	private PannelArrondi[][] grilleHumain;
	public ManualGrille() {
		
		
		setPreferredSize(new Dimension(850, 800));
		init();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		setVisible(true);
		setTitle("Remplissage De Ma Grille");
		
	}
	
	public void init() {
		this.grille = new Grille();
		grilleHumain = new PannelArrondi[ROWS][COLS];
		JPanel panel2 = new JPanel(new GridLayout(ROWS + 2, COLS + 3));
		JPanel panelHumain = new JPanel();
		// ajouter les étiquettes aux panneaux
		panel2.add(new JLabel("")); // coin supérieur gauche vide
		for (int j = 0; j < COLS; j++) {
			char letter = (char) ('A' + j); // lettre ASCII
			JLabel l = new JLabel(Character.toString(letter));
			l.setFont(new Font("Tahoma", 1, 16));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			panel2.add(l); // ajouter l'étiquette de lettre
		}
		
		for (int i = 0; i < ROWS; i++) {
			JLabel l2= new JLabel(Integer.toString(i + 1));
			
			l2 = new JLabel(Integer.toString(i + 1));
			l2.setFont(new Font("Tahoma", 1, 16));
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			panel2.add(l2);
			for (int j = 0; j < COLS; j++) {
				grilleHumain[i][j] = new PannelArrondi();
				grilleHumain[i][j].setBackground(Color.BLUE);
				grilleHumain[i][j].addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						JPanel source = (JPanel) e.getSource();
						int row = (int) source.getClientProperty("row");
						int col = (int) source.getClientProperty("col");
						
						if (e.getButton() == MouseEvent.BUTTON1) {
							
							if(grille.getcase(row, col)==0) {
								grille.setValeurCase(new Point(row,col) ,1);
								grilleHumain[row][col].changecolor(Color.RED);
							}

						} 
						
					}
				});
				panel2.add(grilleHumain[i][j]);
				

			}
		}
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				grilleHumain[i][j].putClientProperty("row", i);
				grilleHumain[i][j].putClientProperty("col", j);
				
			}
		}
		
		JButton confirmButton = new JButton("Confirmer");
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				ModelJeu.affichageGrille(grille);
				Grille gr = new Grille(grille.getGrilleJeu());
				ArrayList<Navire> res = ModelJeu.verifyGrille(gr, ROWS, COLS);
				
				if(res==null) {
					 // Affiche une boîte de dialogue avec un message et un titre
					JOptionPane.showMessageDialog(null, "La compostion de navire est incorrecte veuillez réessayer en respectant les conditions ", "Erreur", JOptionPane.INFORMATION_MESSAGE);

					init();
				}
				else {
					
					Humain h = new Humain("Humain");
					h.setGrille(grille);
					h.setArrayOfNavire(res);
					Ordinateur ord = new Ordinateur();
					ModelJeu jeu = new ModelJeu(h, ord);
					jeu.initManual();
					dispose();
					jeu.affichageGrille(jeu.getHumain().getGrille());
					jeu.affichageGrille(jeu.getOrdinateur().getGrille());
					MaGrille mg = new MaGrille(jeu);
				}
				
				
			}
				
			
		});
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel informationContainer = new JPanel();
		informationContainer.setLayout(new BoxLayout(informationContainer, BoxLayout.Y_AXIS));
		
		JLabel l1 = new JLabel("Regle 1 : Les navires ne doivent pas etre supeposés");
		JLabel l2 = new JLabel("Regle 2 : Les navires ne doivent pas etre en diagonal");
		JLabel l3 = new JLabel("Regle 3 : Les navires ne doivent pas etre l'un à coté de l'autre");
		JLabel l4 = new JLabel("Regle 4 : Placez vos navires , un de taille 2 ,deux de taille 3, un de taille 4 et un de taille 5");
		informationContainer.add(l1);
		informationContainer.add(l2);
		informationContainer.add(l3);
		informationContainer.add(l4);
		
		container.add(informationContainer);
		container.add(panel2);
		container.add(confirmButton);
		add(container);
		pack();
	}
}
