package src.demo;

import java.util.Scanner;
import src.model.Humain;
import src.model.ModelJeu;
import src.model.Ordinateur;
import src.model.Simulation;
/**
 * 
 * Classe éxecutable mode console
 *
 */
public class DemoConsole {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println(" 					_______________________________________________                  ");
        System.out.println("					|BIENVENUE DANS LE JEU DE LA BATAILLE NAVALE  ! |                 ");
        System.out.println("					_______________________________________________                  ");
        
        System.out.println("************************");
        System.out.println("*Information Sur le Jeu*");
        System.out.println("*************************");
        System.out.println("Joueur N° 1 HUMAIN VS Joueur N° 2 ORDINATEUR .");
        System.out.println("Niveau Facile .");
        System.out.println("______________________");
        System.out.println("|LA PARTIE COMMANCE. |");
        System.out.println("______________________");
       
		System.out.println("Humain veuillez saisir votre Nom : ");
		
		String name = sc.next();
		
		while (!name.matches("[a-zA-Z]+")) {
			System.out.println("Nom incorrecte veuilliez saisir un nom valide : ");
            name = sc.nextLine();
            
            
        } 
		
		Humain h = new Humain(name);
		Ordinateur ord = new Ordinateur();
		
		ModelJeu model = new ModelJeu(h, ord);
		
		model.init();
		System.out.println(model.getHumain().getNom()+" Votre adversaire est l'ordinateur . ");                      
	   System.out.println("Le plateau de l'ordinateur est masqué .");
	   model.affichageGrilleOrdinateur(model.getOrdinateur().getGrille());
	   System.out.println(model.getHumain().getNom()+" Voici votre plateau :");
	   model.affichageGrille(model.getHumain().getGrille());
	   System.out.println(model.getHumain().getNom()+" Veulliez commancer le jeu en tapant les coordoneés de votre premier tire .");
		
		
		Simulation sm = new Simulation(model);
		sm.play();

	}

}
