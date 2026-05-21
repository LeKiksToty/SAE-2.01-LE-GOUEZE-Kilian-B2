package org.uphf.sae01;

import javafx.application.Application;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("1 : Lancer le mode CONSOLE");
        System.out.println("2 : Lancer le mode GRAPHIQUE");
        System.out.print("Votre choix (1 ou 2) : ");

        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine().trim();

        if (choix.equals("2")) {
            System.out.println("\nLancement graphique...");
            Application.launch(MondeGraphique.class, args);
        } else {
            System.out.println("\nLancement console");
            Launcher.main(args);
        }
    }
}