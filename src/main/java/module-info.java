module com.example.demo {
	requires transitive javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    exports com.example.demo;
    
}