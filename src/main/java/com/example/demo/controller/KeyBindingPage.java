package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The KeyBindingPage class is responsible for displaying the key binding settings page in the game.
 * It allows the user to select between different key bindings (e.g., Arrow keys or WASD keys) 
 * and navigate back to the main menu.
 */
public class KeyBindingPage {
    
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/menu-background.jpg"; // Background image 
    private static final double SCREEN_WIDTH = 1300; // Width of the screen
    private static final double SCREEN_HEIGHT = 750; // Height of the screen
    private final Stage stage; // The primary stage of the application

    /**
     * Constructs a KeyBindingPage object with the specified stage.
     *
     * @param stage The primary stage of the application.
     */
    public KeyBindingPage(Stage stage) { 
        this.stage = stage; 
    } 

    /**
     * Displays the key binding settings page.
     * This page allows the user to choose between Arrow keys or WASD keys for movement controls
     * and provides a button to return to the main menu.
     */
    public void show() { 
        // Create and set up the background image
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm()));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);

        // Create and set up the title
        Text title = new Text("Key Bindings");
        title.setFont(Font.font("Arial", 30));

        // Create buttons for selecting key bindings
        Button arrowKeyButton = new Button("Arrow Keys");
        Button wasdButton = new Button("WASD Keys");
        arrowKeyButton.setFont(Font.font("Arial", 20));
        wasdButton.setFont(Font.font("Arial", 20));

        // Set actions for the key binding buttons
        arrowKeyButton.setOnAction(e -> { 
            KeyBindingSettings.setKeyBinding(KeyBindingSettings.Binding.ARROW_KEYS); 
            goToMenuPage(); 
        });

        wasdButton.setOnAction(e -> { 
            KeyBindingSettings.setKeyBinding(KeyBindingSettings.Binding.WASD_KEYS); 
            goToMenuPage(); 
        });

        // Create a button to go back to the main menu
        Button backToMenuButton = new Button("Back to Main Menu");
        backToMenuButton.setFont(Font.font("Arial", 20));
        backToMenuButton.setOnAction(e -> goToMenuPage());

        // Create layout with buttons and title
        VBox layout = new VBox(20, title, arrowKeyButton, wasdButton, backToMenuButton);
        layout.setStyle("-fx-alignment: center;");

        // Create a root node to hold the background and layout
        StackPane root = new StackPane(background, layout);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Set the scene and display the page
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates back to the main menu page after selecting a key binding.
     */
    private void goToMenuPage() { 
        MenuPage menuPage = new MenuPage(stage); 
        menuPage.show(); // Return to MenuPage after selecting key binding 
    } 
}

