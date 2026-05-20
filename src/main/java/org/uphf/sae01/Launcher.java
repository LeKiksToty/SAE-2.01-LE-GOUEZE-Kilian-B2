package org.uphf.sae01;

public class Launcher {
    public static void main(String[] args) {
        Monde monde = new Monde();

        Mine m = new Mine(1, "OR", 100, 2, 3);
        Entrepot e = new Entrepot(1, "OR", 5, 7);
        Robot r = new Robot(1, "OR", 5, 2, 0, 0);

        monde.ajouterMine(m);
        monde.ajouterEntrepot(e);
        monde.ajouterRobot(r);

        monde.afficherConsole();
    }
}