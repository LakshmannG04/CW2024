/**
 * The module descriptor for the Sky Battle game application.
 * 
 * This module defines the required dependencies and packages for the Sky Battle game, a JavaFX-based game.
 * It requires the necessary JavaFX modules for building the GUI and handling graphics, controls, and FXML.
 */
module com.example.demo {
    /**
     * Requires the javafx.base module, which is necessary for basic JavaFX functionality.
     * It includes the core classes like Event, ObservableValue, and javafx.beans package.
     */
    requires transitive javafx.base;
    
    /**
     * Requires the javafx.controls module, providing the essential JavaFX controls such as Button, Label, TextField, etc.
     */
    requires javafx.controls;
    
    /**
     * Requires the javafx.fxml module, which is needed to work with FXML files for designing the user interface.
     */
    requires javafx.fxml;
    
    /**
     * Requires the javafx.graphics module, which includes classes for graphics operations like rendering, painting, and the Scene graph.
     */
    requires transitive javafx.graphics;

    /**
     * Opens the com.example.demo package to javafx.fxml to allow FXML-based controller access.
     */
    opens com.example.demo to javafx.fxml;

    /**
     * Exports the com.example.demo.controller package, making the controller classes accessible outside the module.
     */
    exports com.example.demo.controller;

    /**
     * Exports the main com.example.demo package to make the main classes accessible to other modules.
     */
    exports com.example.demo;
}
