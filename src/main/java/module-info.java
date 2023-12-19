module com.example.mediafilter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.medianfilter to javafx.fxml;
    exports com.example.medianfilter;
}