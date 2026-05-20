module org.uphf.sae01 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.uphf.sae01 to javafx.fxml;
    exports org.uphf.sae01;
}