package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

/**
 * The Controller class is responsible for managing the flow of the game, including 
 * transitioning between levels and launching the game. It handles the dynamic 
 * creation of game level instances and setting up the game scene.
 */
public class Controller {

    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne"; // Default starting level
    private final Stage stage; // The primary stage of the application

    /**
     * Constructs a Controller object with the given stage.
     * 
     * @param stage The primary stage of the application.
     */
    public Controller(Stage stage) {
        this.stage = stage;
    }

    /**
     * Launches the game by initializing the first level and displaying it.
     * 
     * @throws ClassNotFoundException If the class for the level is not found.
     * @throws NoSuchMethodException If the constructor of the level class is not found.
     * @throws SecurityException If access to the constructor is denied.
     * @throws InstantiationException If the level class cannot be instantiated.
     * @throws IllegalAccessException If the constructor is not accessible.
     * @throws IllegalArgumentException If an invalid argument is passed to the constructor.
     * @throws InvocationTargetException If an exception is thrown by the invoked constructor.
     */
    public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        goToLevel(LEVEL_ONE_CLASS_NAME); // Starts the game at the first level
    }

    /**
     * Transitions to the specified game level.
     * Dynamically loads the class for the level, creates an instance, and sets up the scene.
     * 
     * @param className The fully qualified name of the class for the level to transition to.
     */
    public void goToLevel(String className) {
        try {
            Class<?> levelClass = Class.forName(className); // Load the class for the level
            Constructor<?> constructor = levelClass.getConstructor(double.class, double.class, Stage.class); // Get the constructor
            LevelParent level = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), stage); // Instantiate the level

            // Create a new scene with the level's root and set it on the stage
            Scene scene = level.initializeScene();
            stage.setScene(scene);
            level.startGame(); // Start the game for the new level
        } catch (Exception e) {
            System.err.println("Failed to transition to level: " + className);
            e.printStackTrace();
        }
    }
}
