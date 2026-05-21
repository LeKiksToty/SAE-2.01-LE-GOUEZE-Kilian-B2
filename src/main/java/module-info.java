module org.uphf.sae01 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.uphf.sae01 to javafx.fxml, javafx.graphics;
    exports org.uphf.sae01;
}