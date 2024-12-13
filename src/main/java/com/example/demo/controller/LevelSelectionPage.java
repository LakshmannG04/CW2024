package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

/**
 * The LevelSelectionPage class is responsible for displaying the level selection screen in the game.
 * It allows the user to choose from multiple levels and navigate to the selected level.
 */
public class LevelSelectionPage { 

    // Path to the background image
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/menu-background.jpg"; 

    // Screen dimensions
    private static final double SCREEN_WIDTH = 1300; 
    private static final double SCREEN_HEIGHT = 750; 

    private final Stage stage; 

    /**
     * Constructs a LevelSelectionPage with the specified stage.
     * 
     * @param stage The stage on which the level selection page will be displayed.
     */
    public LevelSelectionPage(Stage stage) { 
        this.stage = stage; 
    } 

    /**
     * Displays the level selection page with a background and buttons for selecting levels.
     * The user can select one of the levels (Level 1, Level 2, or Level 3), or navigate back to the main menu.
     */
    public void show() { 
        // Background setup 
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm())); 
        background.setFitWidth(SCREEN_WIDTH); 
        background.setFitHeight(SCREEN_HEIGHT); 

        // Buttons for level selection 
        Button level1Button = createLevelButton("Level 1"); 
        Button level2Button = createLevelButton("Level 2"); 
        Button level3Button = createLevelButton("Level 3");

        // Button actions 
        level1Button.setOnAction(e -> startLevel("com.example.demo.LevelOne")); 
        level2Button.setOnAction(e -> startLevel("com.example.demo.LevelTwo")); 
        level3Button.setOnAction(e -> startLevel("com.example.demo.LevelThree")); 

        // Back to Main Menu Button
        Button backToMenuButton = new Button("Back to Main Menu");
        backToMenuButton.setFont(Font.font("Arial", 20));
        backToMenuButton.setOnAction(e -> {
            MenuPage menuPage = new MenuPage(stage);
            menuPage.show(); // Navigate back to the MenuPage
        });

        // Layout configuration 
        VBox layout = new VBox(20, level1Button, level2Button, level3Button, backToMenuButton); 
        layout.setStyle("-fx-alignment: center;"); 
        StackPane root = new StackPane(background, layout); 

        // Scene setup 
        Scene levelSelectionScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); 
        stage.setScene(levelSelectionScene); 
        stage.show(); 
    } 

    /**
     * Creates a button for selecting a level.
     * 
     * @param text The text displayed on the button.
     * @return The created button.
     */
    private Button createLevelButton(String text) { 
        Button button = new Button(text); 
        button.setFont(Font.font("Arial", 20)); 
        button.setPrefWidth(200); 
        return button; 
    } 

    /**
     * Starts the specified level by navigating to the corresponding level screen.
     * 
     * @param levelClass The class name of the level to start (e.g., "com.example.demo.LevelOne").
     */
    private void startLevel(String levelClass) { 
        try { 
            Controller controller = new Controller(stage); 
            controller.goToLevel(levelClass); // Navigate to the selected level 
        } catch (Exception e) { 
            System.err.println("Failed to start level: " + levelClass); 
            e.printStackTrace(); 
        } 
    } 
}
