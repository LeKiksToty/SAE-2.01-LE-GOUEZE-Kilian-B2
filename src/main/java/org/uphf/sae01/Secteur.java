package org.uphf.sae01;

public class Secteur {
    private boolean estEau;
    private Mine mine;
    private Entrepot entrepot;
    private Robot robot;

    public Secteur(boolean estEau) {
        this.estEau = estEau;
    }


    public void setEstEau(boolean estEau) {
        this.estEau = estEau;
    }

    public boolean estEau() {
        return estEau;
    }

    public boolean estDisponible() {
        return !estEau && robot == null && mine == null && entrepot == null;
    }

    public Mine getMine() { return mine; }
    public void setMine(Mine mine) { this.mine = mine; }

    public Entrepot getEntrepot() { return entrepot; }
    public void setEntrepot(Entrepot entrepot) { this.entrepot = entrepot; }

    public Robot getRobot() { return robot; }
    public void setRobot(Robot robot) { this.robot = robot; }
}