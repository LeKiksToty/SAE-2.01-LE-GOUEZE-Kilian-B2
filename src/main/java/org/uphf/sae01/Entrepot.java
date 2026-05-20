package org.uphf.sae01;

public class Entrepot {

    private int id;
    private String typeMinerai;
    private int stockActuel;
    private int ligne;
    private int colonne;


    public Entrepot(int id, String typeMinerai, int ligne, int colonne) {
        this.id = id;
        this.typeMinerai = typeMinerai;
        this.stockActuel = 0;
        this.ligne = ligne;
        this.colonne = colonne;
    }


    public void deposer(int quantite) {
        this.stockActuel += quantite;
    }


    public int getId() { return id; }
    public String getTypeMinerai() { return typeMinerai; }
    public int getStockActuel() { return stockActuel; }
    public int getLigne() { return ligne; }
    public int getColonne() { return colonne; }
}