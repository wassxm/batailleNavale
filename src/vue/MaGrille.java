package src.vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.cotroller.Controller;
import src.model.Humain;
import src.model.ModelJeu;
import src.model.Navire;
import src.model.Ordinateur;
import src.model.Player;
import src.util.EcouteurModele;

public class MaGrille extends JFrame implements EcouteurModele {
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 10;
	private static final int COLS = 10;
	private PannelArrondi[][] grilleHumain;
	private PannelArrondi[][] grilleOrd;
	private JPanel panel ;
	JPanel panelOrdi;
	JPanel panelHumain ;
	private ModelJeu model;
	
    

	/**
	 * 
	 * @param model le model de type ModelJeu
	 */
	public MaGrille(ModelJeu model) {
		grilleHumain = new PannelArrondi[ROWS][COLS];
		grilleOrd = new PannelArrondi[ROWS][COLS];
		
		this.model = model;
		model.ajoutEcouteur(this);

		
		// initialiser les grilles avec des 1 et des 0 aléatoirement
		initialiserGrilles();
		
		setTitle("Bataille Navale");
		setPreferredSize(new Dimension(1300, 700));

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		setVisible(true);
		
		
		
	}
	
	private void initialiserGrilles() {
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		JPanel panel1 = new JPanel(new GridLayout(ROWS + 2, COLS + 3)); // ajouter 1 à ROWS et COLS pour les étiquettes
		JPanel panel2 = new JPanel(new GridLayout(ROWS + 2, COLS + 3));
		
		JLabel labelOrdi = new JLabel("Ordianteur");
		JLabel labelHumain = new JLabel(model.getHumain().getNom());
		
		panelOrdi = new JPanel(new GridLayout(2,1));
		panelHumain = new JPanel();
		// ajouter les étiquettes aux panneaux
		panel1.add(new JLabel("")); // coin supérieur gauche vide
		for (int j = 0; j < COLS; j++) {
			char letter = (char) ('A' + j); // lettre ASCII
			JLabel l = new JLabel(Character.toString(letter));
			l.setFont(new Font("Tahoma", 1, 16));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(l);
		}
		
		panel2.add(new JLabel("")); // coin supérieur gauche vide
		for (int j = 0; j < COLS; j++) {
			char letter = (char) ('A' + j); // lettre ASCII
			JLabel l = new JLabel(Character.toString(letter));
			l.setFont(new Font("Tahoma", 1, 16));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			panel2.add(l); // ajouter l'étiquette de lettre
		}

		for (int i = 0; i < ROWS; i++) {
			JLabel l2, l = new JLabel(Integer.toString(i + 1));
			l.setFont(new Font("Tahoma", 1, 16));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(l);
			l2 = new JLabel(Integer.toString(i + 1));
			l2.setFont(new Font("Tahoma", 1, 16));
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			panel2.add(l2);
			for (int j = 0; j < COLS; j++) {
				grilleHumain[i][j] = new PannelArrondi();
				grilleHumain[i][j].setBackground(Color.BLUE);
				panel1.add(grilleHumain[i][j]);

				grilleOrd[i][j] = new PannelArrondi();
				grilleOrd[i][j].setBackground(Color.BLUE);
				grilleOrd[i][j].addMouseListener(new Controller(model));
				panel2.add(grilleOrd[i][j]);
			}
		}

		panel.setPreferredSize(new Dimension(1300, 700));
		
		panelOrdi.setLayout(new BoxLayout(panelOrdi, BoxLayout.Y_AXIS));
		panelOrdi.add(labelOrdi);
		panelOrdi.add(panel1);
		
		panelHumain.setLayout(new BoxLayout(panelHumain, BoxLayout.Y_AXIS));
		panelHumain.add(labelHumain);
		panelHumain.add(panel2);
		
		//création des geles du jeu
		JPanel panelinfo = new JPanel();
		panelinfo.setLayout(new BoxLayout(panelinfo, BoxLayout.X_AXIS));
		JLabel[] regleLabel = new JLabel[3];
		JPanel[] reglePanel = new JPanel[3];
		JPanel reglePanelContainer = new JPanel() ;
		JPanel regleLabelContainer = new JPanel() ;
		regleLabelContainer.setLayout(new BoxLayout(regleLabelContainer, BoxLayout.Y_AXIS));
		reglePanelContainer.setLayout(new BoxLayout(reglePanelContainer, BoxLayout.Y_AXIS));
		
		
		String[] regles = {"Touché","Non joué","Raté"};
		Color[] colors = {Color.red,Color.blue,Color.GREEN};
		for(int i = 0;i<3;i++) {
			regleLabel[i] = new JLabel(regles[i]);
			regleLabel[i].setPreferredSize(new Dimension(100,40));
			regleLabel[i].setMinimumSize(new Dimension(100,40));
			regleLabel[i].setMaximumSize(new Dimension(100,40));
			reglePanel[i] =new PannelArrondi(colors[i]);
			
			reglePanel[i].setPreferredSize(new Dimension(40,40));
			reglePanel[i].setMaximumSize(new Dimension(40,40));
			reglePanel[i].setMinimumSize(new Dimension(40,40));
			
			regleLabelContainer.add(regleLabel[i]);
			reglePanelContainer.add(reglePanel[i]);
			
		}
		panelinfo.add(regleLabelContainer);
		panelinfo.add(reglePanelContainer);
		panel.add(panelOrdi);
		panel.add(panelHumain);
		panel.add(panelinfo);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				grilleHumain[i][j].putClientProperty("row", i);
				grilleHumain[i][j].putClientProperty("col", j);
				grilleHumain[i][j].putClientProperty("valeur", this.model.getHumain().getGrille().getcase(i, j));

				grilleOrd[i][j].putClientProperty("row", i);
				grilleOrd[i][j].putClientProperty("col", j);
				grilleOrd[i][j].putClientProperty("valeur", this.model.getOrdinateur().getGrille().getcase(i, j));
			}
		}
		add(panel);
		pack();
	}

	

	@Override
	public void modeleMisAJour() {
		System.out.println("je mis a jour ");
		if(!model.isOver()) {
			if (model.isEsttoucher()) {
				if (model.getCurrentPlayer() == model.getOrdinateur()) {
					this.grilleHumain[model.getPointjouer().getX()][model.getPointjouer().getY()].changecolor(Color.red);
	
				} else {
					this.grilleOrd[model.getPointjouer().getX()][model.getPointjouer().getY()].changecolor(Color.red);
	
				}
			} else {
				if (model.getCurrentPlayer() == model.getOrdinateur()) {
					this.grilleOrd[model.getPointjouer().getX()][model.getPointjouer().getY()].changecolor(Color.green);
	
				} else {
					this.grilleHumain[model.getPointjouer().getX()][model.getPointjouer().getY()]
							.changecolor(Color.green);
	
				}
	
			}
		}
		else {
			String winner = model.getwinner().getNom();
			
		     // Affiche une boîte de dialogue avec un message et un titre
			JOptionPane.showMessageDialog(null, "Le gagnant est : "+winner, "Partie terminée", JOptionPane.INFORMATION_MESSAGE);

		      // Affiche une boîte de dialogue avec un message et des boutons
		      int choix = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir rejouer?", "Partie terminée", JOptionPane.YES_NO_OPTION);
		      if (choix == JOptionPane.YES_OPTION) {
		    	
		  		
		    	  remove(panel);
		    	 model.init();
		  		this.initialiserGrilles();
		  		model.affichageGrille(model.getHumain().getGrille());
				model.affichageGrille(model.getOrdinateur().getGrille());
		      } else {
		         this.dispose();
		      }
		}
		
	}

	public void simulation() {
		while (!model.isOver()) {

			Player currentPlayer = model.getCurrentPlayer();
			if (currentPlayer == model.getOrdinateur()) {
				currentPlayer.generate();
			}
		}
	}
	public static void initialise() {
		
		ManualGrille mg = new ManualGrille();
		
	}
}
