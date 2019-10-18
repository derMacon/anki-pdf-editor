module com.dermacon {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dermacon to javafx.fxml;
    exports com.dermacon;
}