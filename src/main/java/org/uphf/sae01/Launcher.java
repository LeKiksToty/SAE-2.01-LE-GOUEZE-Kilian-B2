package org.uphf.sae01;

import java.util.Scanner;
import java.util.Random;

public class Launcher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Monde monde = new Monde();
        Random rand = new Random();

        int nbRobots;
        do {
            System.out.print("Nombre de robots (2 à 5) : ");
            nbRobots = sc.nextInt();
        } while (nbRobots < 2 || nbRobots > 5);

        for (int i = 0; i < nbRobots; i++) {
            String type = (i % 2 == 0) ? "OR" : "NI";
            int l, c;
            do {
                l = rand.nextInt(10);
                c = rand.nextInt(10);
            } while (!monde.getSecteur(l, c).estDisponible());

            Robot r = new Robot(i + 1, type, 5 + rand.nextInt(5), 1 + rand.nextInt(3), l, c);
            monde.ajouterRobot(r);
        }

        boolean jeuEnCours = true;
        while (jeuEnCours) {
            monde.afficherConsole();

            for (Robot r : monde.getListeRobots()) {
                System.out.print("\n[Robot " + r.getId() + " (" + (r.getTypeSpecialite().equalsIgnoreCase("NI") ? "NICKEL" : "OR") + ")] Action (N, S, E, O ou q pour quitter) : ");
                String action = sc.next();

                if (action.equalsIgnoreCase("q")) {
                    jeuEnCours = false;
                    break;
                }

                monde.deplacerRobot(r, action);

            }

            if (jeuEnCours) {
                monde.executerTour();
            }
        }
        System.out.println("Fin du jeu");
    }
}