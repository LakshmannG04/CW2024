package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class is the entry point of the Sky Battle game.
 * It extends the Application class from JavaFX and sets up the initial game window,
 * including its size, title, and other configurations. It also starts the game by 
 * displaying the main menu.
 */
public class Main extends Application {

    // Screen dimensions and title for the game
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";

    /**
     * The start method is called by the JavaFX runtime when the application is launched.
     * It sets up the game window (Stage) with the specified title and dimensions.
     * Then, it initializes and shows the MenuPage where the player can start the game.
     * 
     * @param stage The primary stage for the game window.
     */
    @Override
    public void start(Stage stage) {
        // Set the window title and size
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setWidth(SCREEN_WIDTH);
        stage.setHeight(SCREEN_HEIGHT);

        // Configure memory settings (optional)
        System.setProperty("javafx.animation.fullspeed", "false"); // Reduce animation load

        // Display the menu page
        MenuPage menuPage = new MenuPage(stage);
        menuPage.show();

        // Handle clean exit of the game
        stage.setOnCloseRequest(event -> {
            System.out.println("Exiting game...");
            // Optional cleanup logic if necessary
        });
    }

    /**
     * The main method is the entry point of the application.
     * It launches the JavaFX application.
     * 
     * @param args Command line arguments (if needed).
     */
    public static void main(String[] args) {
        // Suggested JVM arguments for memory tuning (optional):
        // -Xms256m -Xmx512m
        launch();
    }
}
