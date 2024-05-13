module com.example.sortingvisualisation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sortingvisualisation to javafx.fxml;
    exports com.example.sortingvisualisation;
}