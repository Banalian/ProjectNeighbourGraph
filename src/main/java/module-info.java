module main.projectneighbourgraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.projectneighbourgraph to javafx.fxml;
    exports main.projectneighbourgraph;
    exports main.projectneighbourgraph.linkStrategy;
    opens main.projectneighbourgraph.linkStrategy to javafx.fxml;

    opens main.projectneighbourgraph.graphdata to javafx.base;
}