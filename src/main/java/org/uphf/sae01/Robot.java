package org.uphf.sae01;

public class Robot {
    private int id, capaciteStockageMax, stockActuel, capaciteExtraction, ligne, colonne;
    private String typeSpecialite;

    public Robot(int id, String typeSpecialite, int capaciteStockageMax, int capaciteExtraction, int ligne, int colonne) {
        this.id = id;
        this.typeSpecialite = typeSpecialite;
        this.capaciteStockageMax = capaciteStockageMax;
        this.stockActuel = 0;
        this.capaciteExtraction = capaciteExtraction;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public void setPosition(int l, int c) { this.ligne = l; this.colonne = c; }

    public void avancer(String direction) {
        switch (direction.toUpperCase()) {
            case "NORD", "N" -> ligne--;
            case "SUD", "S" -> ligne++;
            case "EST", "E" -> colonne++;
            case "OUEST", "O" -> colonne--;
        }
    }

    public void recolter(Mine mine) {
        if (mine.getTypeMinerai().equals(this.typeSpecialite)) {
            int espaceLibre = this.capaciteStockageMax - this.stockActuel;
            int aExtraire = Math.min(this.capaciteExtraction, espaceLibre);
            this.stockActuel += mine.extraire(aExtraire);
        }
    }

    public void deposer(Entrepot entrepot) {
        if (entrepot.getTypeMinerai().equals(this.typeSpecialite)) {
            entrepot.deposer(this.stockActuel);
            this.stockActuel = 0;
        }
    }

    public int getId() { return id; }
    public String getTypeSpecialite() { return typeSpecialite; }
    public int getStockActuel() { return stockActuel; }
    public int getCapaciteStockageMax() { return capaciteStockageMax; }
    public int getLigne() { return ligne; }
    public int getColonne() { return colonne; }
}