package org.uphf.sae01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;

public class MondeGraphique extends Application {

    public static class Model {
        private Monde monde;
        private int indexRobotActuel = 0;
        private boolean jeuEnCours = false;

        public void initialiserJeu(int nbRobots) {
            monde = new Monde();
            Random rand = new Random();
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
            indexRobotActuel = 0;
            jeuEnCours = true;
        }

        public void avancerTour(String direction) {
            if (!jeuEnCours || monde.getListeRobots().isEmpty()) return;
            Robot r = monde.getListeRobots().get(indexRobotActuel);
            monde.deplacerRobot(r, direction);
            indexRobotActuel++;
            if (indexRobotActuel >= monde.getListeRobots().size()) {
                indexRobotActuel = 0;
                monde.executerTour();
            }
        }

        public Monde getMonde() { return monde; }
        public int getIndexRobotActuel() { return indexRobotActuel; }
        public boolean isJeuEnCours() { return jeuEnCours; }
        public void setJeuEnCours(boolean jeuEnCours) { this.jeuEnCours = jeuEnCours; }

        public Robot getRobotActuel() {
            if (monde == null || monde.getListeRobots().isEmpty()) return null;
            return monde.getListeRobots().get(indexRobotActuel);
        }
    }

    public static class View extends BorderPane {
        private GridPane grille = new GridPane();
        private VBox configurationPanel = new VBox(15);
        private VBox jeuPanel = new VBox(10);
        private Label lblStatus = new Label();
        private Button btnN = new Button("N");
        private Button btnS = new Button("S");
        private Button btnE = new Button("E");
        private Button btnO = new Button("O");
        private Button btnStop = new Button("Stop");
        private Button[] btnConfigRobots = new Button[4];

        public View() {
            this.setPadding(new Insets(15));
            initConfigurationPanel();
            initJeuPanel();
            showConfiguration();
        }

        private void initConfigurationPanel() {
            configurationPanel.setAlignment(Pos.CENTER);
            Label title = new Label("Sélectionnez le nombre de robots (2 à 5) :");
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            configurationPanel.getChildren().add(title);
            HBox buttonsBox = new HBox(10);
            buttonsBox.setAlignment(Pos.CENTER);
            for (int i = 0; i < 4; i++) {
                btnConfigRobots[i] = new Button(String.valueOf(i + 2));
                btnConfigRobots[i].setPrefSize(50, 40);
                buttonsBox.getChildren().add(btnConfigRobots[i]);
            }
            configurationPanel.getChildren().add(buttonsBox);
        }

        private void initJeuPanel() {
            jeuPanel.setAlignment(Pos.CENTER);
            grille.setAlignment(Pos.CENTER);
            grille.setGridLinesVisible(false);

            lblStatus.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            GridPane pad = new GridPane();
            pad.setAlignment(Pos.CENTER);
            pad.setHgap(5);
            pad.setVgap(5);

            btnN.setPrefSize(45, 45);
            btnS.setPrefSize(45, 45);
            btnE.setPrefSize(45, 45);
            btnO.setPrefSize(45, 45);
            btnStop.setPrefSize(70, 45);
            btnStop.setStyle("-fx-base: #ff4d4d;");

            pad.add(btnN, 1, 0);
            pad.add(btnO, 0, 1);
            pad.add(btnStop, 1, 1);
            pad.add(btnE, 2, 1);
            pad.add(btnS, 1, 2);

            Label titreLegende = new Label("Légende : ");
            titreLegende.setStyle("-fx-font-weight: bold; -fx-font-size: 11px;");

            Rectangle rectEau = new Rectangle(12, 12, Color.LIGHTBLUE);
            rectEau.setStroke(Color.GRAY);
            Label txtEau = new Label(" Eau   ");
            txtEau.setStyle("-fx-font-size: 11px;");

            Rectangle rectOr = new Rectangle(12, 12, Color.GOLD);
            rectOr.setStroke(Color.GRAY);
            Label txtOr = new Label(" Or    ");
            txtOr.setStyle("-fx-font-size: 11px;");

            Rectangle rectNi = new Rectangle(12, 12, Color.LIGHTSTEELBLUE);
            rectNi.setStroke(Color.GRAY);
            Label txtNi = new Label(" Nickel  ");
            txtNi.setStyle("-fx-font-size: 11px;");

            HBox legendeBox = new HBox(5, titreLegende, rectEau, txtEau, rectOr, txtOr, rectNi, txtNi);
            legendeBox.setAlignment(Pos.CENTER);
            legendeBox.setPadding(new Insets(5, 0, 10, 0));

            jeuPanel.getChildren().addAll(lblStatus, grille, pad, legendeBox);
        }

        public void showConfiguration() {
            this.setCenter(configurationPanel);
        }

        public void showJeu() {
            this.setCenter(jeuPanel);
        }

        public void dessinerMonde(Monde monde, Robot robotActuel) {
            grille.getChildren().clear();
            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    StackPane cell = new StackPane();
                    Rectangle background = new Rectangle(55, 55);
                    background.setStroke(Color.DARKGRAY);

                    VBox textContainer = new VBox(2);
                    textContainer.setAlignment(Pos.CENTER);
                    Label topLabel = new Label(" ");
                    Label bottomLabel = new Label(" ");
                    topLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: black;");
                    bottomLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: black;");

                    Secteur s = monde.getSecteur(r, c);

                    if (s.estEau()) {
                        background.setFill(Color.LIGHTBLUE);
                        topLabel.setText("X X");
                        bottomLabel.setText("X X");
                    } else {
                        background.setFill(Color.WHITE);
                        if (s.getMine() != null) {
                            Mine m = s.getMine();
                            topLabel.setText("M" + m.getId() + ":" + m.getQuantiteActuelle());
                            background.setFill(m.getTypeMinerai().equalsIgnoreCase("OR") ? Color.GOLD : Color.LIGHTSTEELBLUE);
                        } else if (s.getEntrepot() != null) {
                            Entrepot e = s.getEntrepot();
                            topLabel.setText("E" + e.getId() + ":" + e.getStockActuel());
                            background.setFill(e.getTypeMinerai().equalsIgnoreCase("OR") ? Color.GOLD : Color.LIGHTSTEELBLUE);
                        }

                        if (s.getRobot() != null) {
                            Robot bot = s.getRobot();
                            bottomLabel.setText("R" + bot.getId() + "(" + bot.getStockActuel() + ")");
                            background.setFill(bot.getTypeSpecialite().equalsIgnoreCase("OR") ? Color.GOLD : Color.LIGHTSTEELBLUE);
                            if (bot == robotActuel) {
                                background.setStroke(Color.GREEN);
                                background.setStrokeWidth(3);
                            }
                        }
                    }

                    textContainer.getChildren().addAll(topLabel, bottomLabel);
                    cell.getChildren().addAll(background, textContainer);
                    grille.add(cell, c, r);
                }
            }
        }

        public Button[] getBtnConfigRobots() { return btnConfigRobots; }
        public Button getBtnN() { return btnN; }
        public Button getBtnS() { return btnS; }
        public Button getBtnE() { return btnE; }
        public Button getBtnO() { return btnO; }
        public Button getBtnStop() { return btnStop; }
        public void setStatusText(String text) { lblStatus.setText(text); }
    }

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View();

        for (int i = 0; i < 4; i++) {
            final int nb = i + 2;
            view.getBtnConfigRobots()[i].setOnAction(e -> {
                model.initialiserJeu(nb);
                view.showJeu();
                rafraichirInterface(model, view);
            });
        }

        view.getBtnN().setOnAction(e -> handleAction(model, view, "N"));
        view.getBtnS().setOnAction(e -> handleAction(model, view, "S"));
        view.getBtnE().setOnAction(e -> handleAction(model, view, "E"));
        view.getBtnO().setOnAction(e -> handleAction(model, view, "O"));

        view.getBtnStop().setOnAction(e -> {
            model.setJeuEnCours(false);
            view.setStatusText("Fin du jeu");
            view.getBtnN().setDisable(true);
            view.getBtnS().setDisable(true);
            view.getBtnE().setDisable(true);
            view.getBtnO().setDisable(true);
            view.getBtnStop().setDisable(true);
        });

        primaryStage.setScene(new Scene(view, 650, 750));
        primaryStage.setTitle("SAE 01 - Simulation Monde Graphique");
        primaryStage.show();
    }

    private void handleAction(Model model, View view, String direction) {
        if (!model.isJeuEnCours()) return;
        model.avancerTour(direction);
        rafraichirInterface(model, view);
    }

    private void rafraichirInterface(Model model, View view) {
        if (!model.isJeuEnCours()) return;
        Robot r = model.getRobotActuel();
        if (r != null) {
            view.setStatusText("Robot " + r.getId() + " (" + (r.getTypeSpecialite().equalsIgnoreCase("NI") ? "NICKEL" : "OR") + ") | Stock: " + r.getStockActuel() + "/" + r.getCapaciteStockageMax());
        }
        view.dessinerMonde(model.getMonde(), r);
    }

    public static void main(String[] args) {
        launch(args);
    }
}