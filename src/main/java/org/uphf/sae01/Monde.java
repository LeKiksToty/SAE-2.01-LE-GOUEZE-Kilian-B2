package org.uphf.sae01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monde {
    private Secteur[][] matrice;
    private int tourActuel;
    private List<Robot> listeRobots;
    private List<Mine> listeMines;
    private List<Entrepot> listeEntrepots;
    private Random rand = new Random();

    public Monde() {
        this.matrice = new Secteur[10][10];
        this.tourActuel = 0;
        this.listeRobots = new ArrayList<>();
        this.listeMines = new ArrayList<>();
        this.listeEntrepots = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrice[i][j] = new Secteur(false);
            }
        }
        initialiserMonde();
    }

    private void initialiserMonde() {
        int nbEau = rand.nextInt(11);
        for (int i = 0; i < nbEau; i++) {
            int l = rand.nextInt(10);
            int c = rand.nextInt(10);
            if (matrice[l][c].estDisponible()) matrice[l][c].setEstEau(true);
        }

        placerObjet(new Entrepot(1, "OR", 0, 0), "E");
        placerObjet(new Entrepot(2, "NI", 0, 0), "E");

        int nbOr = 1 + rand.nextInt(2);
        int nbNi = 1 + rand.nextInt(2);
        int idMineGlobal = 1;

        for(int i = 0; i < nbOr; i++) {
            placerObjet(new Mine(idMineGlobal, "OR", 50 + rand.nextInt(51), 0, 0), "M");
            idMineGlobal++;
        }
        for(int i = 0; i < nbNi; i++) {
            placerObjet(new Mine(idMineGlobal, "NI", 50 + rand.nextInt(51), 0, 0), "M");
            idMineGlobal++;
        }
    }

    private void placerObjet(Object obj, String type) {
        int l, c;
        do {
            l = rand.nextInt(10);
            c = rand.nextInt(10);
        } while (!matrice[l][c].estDisponible());

        if (type.equals("E")) {
            Entrepot e = (Entrepot) obj;
            e.setPosition(l, c);
            matrice[l][c].setEntrepot(e);
            listeEntrepots.add(e);
        } else if (type.equals("M")) {
            Mine m = (Mine) obj;
            m.setPosition(l, c);
            matrice[l][c].setMine(m);
            listeMines.add(m);
        }
    }

    public void ajouterRobot(Robot r) {
        if (matrice[r.getLigne()][r.getColonne()].estDisponible()) {
            listeRobots.add(r);
            matrice[r.getLigne()][r.getColonne()].setRobot(r);
        }
    }

    public void deplacerRobot(Robot r, String direction) {
        int ancienneLigne = r.getLigne();
        int ancienneColonne = r.getColonne();
        int nouvelleLigne = ancienneLigne;
        int nouvelleCol = ancienneColonne;

        switch (direction.toUpperCase()) {
            case "NORD", "N" -> nouvelleLigne--;
            case "SUD", "S" -> nouvelleLigne++;
            case "EST", "E" -> nouvelleCol++;
            case "OUEST", "O" -> nouvelleCol--;
            default -> { return; }
        }

        if (nouvelleLigne >= 0 && nouvelleLigne < 10 && nouvelleCol >= 0 && nouvelleCol < 10) {
            Secteur destination = matrice[nouvelleLigne][nouvelleCol];

            if (!destination.estEau() && destination.getRobot() == null) {
                matrice[ancienneLigne][ancienneColonne].setRobot(null);
                r.setPosition(nouvelleLigne, nouvelleCol);
                destination.setRobot(r);

                if (destination.getMine() != null) r.recolter(destination.getMine());
                if (destination.getEntrepot() != null) r.deposer(destination.getEntrepot());
            } else {
                System.out.println("Déplacement impossible (eau ou autre robot)");
            }
        }
    }

    public void executerTour() {
        tourActuel++;
        System.out.println("Tour " + tourActuel + " terminé.");
    }

    public void afficherConsole() {
        System.out.println("\n=============================================================== ");
        System.out.println("   GRILLE DU MONDE (Tour actuel : " + tourActuel + ")");
        System.out.println("=============================================================== ");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Secteur s = matrice[i][j];
                String contenu;

                if (s.estEau()) {
                    contenu = "X X";
                } else if (s.getMine() != null) {
                    contenu = "M " + s.getMine().getId();
                } else if (s.getEntrepot() != null) {
                    contenu = "E " + s.getEntrepot().getId();
                } else {
                    contenu = "   ";
                }
                System.out.print("| " + contenu + " ");
            }
            System.out.println("|");

            for (int j = 0; j < 10; j++) {
                Secteur s = matrice[i][j];
                String contenu;

                if (s.estEau()) {
                    contenu = "X X";
                } else if (s.getRobot() != null) {
                    contenu = "R " + s.getRobot().getId();
                } else {
                    contenu = "   ";
                }
                System.out.print("| " + contenu + " ");
            }
            System.out.println("|");

            System.out.println("---------------------------------------------------------------");
        }

        System.out.println("\n-----------------------------------------------------");
        System.out.println("  Caractéristiques du monde | Tour " + tourActuel);
        System.out.println("-----------------------------------------------------");
        System.out.printf("| %-9s | %-5s | %-5s | %-8s | %-9s |\n", "Element", "Ligne", "Col.", "Ress.", "Stock/Cap");
        System.out.println("-----------------------------------------------------");

        for (Mine m : listeMines) {
            String ressource = m.getTypeMinerai().equalsIgnoreCase("NI") ? "NICKEL" : "OR";
            String stockInfo = m.getQuantiteActuelle() + " / " + m.getQuantiteInitiale();
            System.out.printf("| Mine %-3d | %-5d | %-5d | %-8s | %-9s |\n",
                    m.getId(), (m.getLigne() + 1), (m.getColonne() + 1), ressource, stockInfo);
        }

        for (Entrepot e : listeEntrepots) {
            String ressource = e.getTypeMinerai().equalsIgnoreCase("NI") ? "NICKEL" : "OR";
            String stockInfo = String.valueOf(e.getStockActuel());
            System.out.printf("| Entr. %-2d | %-5d | %-5d | %-8s | %-9s |\n",
                    e.getId(), (e.getLigne() + 1), (e.getColonne() + 1), ressource, stockInfo);
        }

        for (Robot r : listeRobots) {
            String ressource = r.getTypeSpecialite().equalsIgnoreCase("NI") ? "NICKEL" : "OR";
            String stockInfo = r.getStockActuel() + " / " + r.getCapaciteStockageMax();
            System.out.printf("| Robot %-2d | %-5d | %-5d | %-8s | %-9s |\n",
                    r.getId(), (r.getLigne() + 1), (r.getColonne() + 1), ressource, stockInfo);
        }
        System.out.println("-----------------------------------------------------");
    }

    public Secteur getSecteur(int l, int c) { return matrice[l][c]; }
    public List<Robot> getListeRobots() { return listeRobots; }
}