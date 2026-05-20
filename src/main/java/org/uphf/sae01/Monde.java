package org.uphf.sae01;

import java.util.ArrayList;
import java.util.List;

public class Monde {
    private Secteur[][] matrice;
    private int tourActuel;
    private List<Robot> listeRobots;

    public Monde() {
        this.matrice = new Secteur[10][10];
        this.tourActuel = 0;
        this.listeRobots = new ArrayList<>();
        initialiserMonde();
    }

    private void initialiserMonde() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrice[i][j] = new Secteur(false);
            }
        }
    }

    public void ajouterRobot(Robot r) {
        listeRobots.add(r);
        matrice[r.getLigne()][r.getColonne()].setRobot(r);
    }

    public void ajouterMine(Mine m) {
        matrice[m.getLigne()][m.getColonne()].setMine(m);
    }

    public void ajouterEntrepot(Entrepot e) {
        matrice[e.getLigne()][e.getColonne()].setEntrepot(e);
    }

    public void executerTour() {
        tourActuel++;
    }

    public void afficherConsole() {
        System.out.println("--- Tour " + tourActuel + " ---");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Secteur s = matrice[i][j];
                if (s.getRobot() != null) {
                    System.out.print("[R] ");
                } else if (s.getMine() != null) {
                    System.out.print("[M] ");
                } else if (s.getEntrepot() != null) {
                    System.out.print("[E] ");
                } else {
                    System.out.print("[.] ");
                }
            }
            System.out.println();
        }
    }
}