module com.gerini {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.gerini to javafx.fxml;
    exports com.gerini;
}
