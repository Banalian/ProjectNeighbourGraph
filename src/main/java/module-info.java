module main.projectneighbourgraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.projectneighbourgraph to javafx.fxml;
    exports main.projectneighbourgraph;
}