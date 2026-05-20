package org.uphf.sae01;

public class Mine {
    private int id;
    private String typeMinerai;
    private int quantiteActuelle;
    private int quantiteInitiale;
    private int ligne;
    private int colonne;

    public Mine(int id, String typeMinerai, int quantiteInitiale, int ligne, int colonne) {
        this.id = id;
        this.typeMinerai = typeMinerai;
        this.quantiteInitiale = quantiteInitiale;
        this.quantiteActuelle = quantiteInitiale;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int extraire(int capaciteExtraction) {
        if (quantiteActuelle >= capaciteExtraction) {
            quantiteActuelle -= capaciteExtraction;
            return capaciteExtraction;
        } else {
            int reste = quantiteActuelle;
            quantiteActuelle = 0;
            return reste;
        }
    }

    public int getId() { return id; }
    public String getTypeMinerai() { return typeMinerai; }
    public int getQuantiteActuelle() { return quantiteActuelle; }
    public int getQuantiteInitiale() { return quantiteInitiale; }
    public int getLigne() { return ligne; }
    public int getColonne() { return colonne; }
}