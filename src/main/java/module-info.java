module main.projectneighbourgraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.projectneighbourgraph to javafx.fxml;
    exports main.projectneighbourgraph;
    exports main.projectneighbourgraph.strategy;
    opens main.projectneighbourgraph.strategy to javafx.fxml;
}