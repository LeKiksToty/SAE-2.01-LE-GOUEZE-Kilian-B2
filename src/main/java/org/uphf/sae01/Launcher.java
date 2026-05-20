package org.uphf.sae01;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Monde monde = new Monde();

        Mine m = new Mine(1, "OR", 100, 2, 3);
        Entrepot e = new Entrepot(1, "OR", 5, 7);
        Robot r = new Robot(1, "OR", 5, 2, 0, 0);

        monde.ajouterMine(m);
        monde.ajouterEntrepot(e);
        monde.ajouterRobot(r);

        boolean continuer = true;
        while (continuer) {
            monde.afficherConsole();
            System.out.println("Actions : N, S, E, O, QUITTER");
            System.out.print("Choix : ");
            String choix = scanner.nextLine().toUpperCase();

            if (choix.equals("QUITTER")) {
                continuer = false;
            } else {
                monde.deplacerRobot(r, choix);
                monde.executerTour();
            }
        }
        scanner.close();
    }
}