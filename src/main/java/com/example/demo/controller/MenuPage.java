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
 * The MenuPage class is responsible for displaying the main menu of the Sky Battle game.
 * It provides buttons for starting the game, quitting the game, navigating to the key binding page,
 * and selecting levels to play.
 */
public class MenuPage {

    // Constants for screen size and background image
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/menu-background.jpg"; // Corrected image path
    private static final double SCREEN_WIDTH = 1300;
    private static final double SCREEN_HEIGHT = 750;

    // Stage for the game window
    private final Stage stage;

    /**
     * Constructs a MenuPage with the given stage.
     * 
     * @param stage The primary stage for the game window.
     */
    public MenuPage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Displays the main menu scene with the background image, title, and buttons.
     * The menu allows the player to start the game, quit the game, select key bindings, or choose a level.
     */
    public void show() {
        // Background image setup
        ImageView background = createBackgroundImage();

        // Title setup
        Text title = createTitle();

        // Buttons for the menu actions
        Button startButton = createButton("Start Game", e -> startGame());
        Button quitButton = createButton("Quit Game", e -> System.exit(0));
        Button keyBindingsButton = createButton("Key Bindings", e -> showKeyBindingPage());
        Button levelSelectionButton = createButton("Level Selection", e -> showLevelSelectionPage());

        // Layout configuration
        VBox menuLayout = new VBox(20, title, startButton, levelSelectionButton, keyBindingsButton, quitButton);
        menuLayout.setStyle("-fx-alignment: center;");

        // Create the root layout and scene
        StackPane root = new StackPane(background, menuLayout);
        Scene menuScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Set the scene and display the menu
        stage.setScene(menuScene);
        stage.show();
    }

    /**
     * Creates an ImageView for the background image.
     * 
     * @return The ImageView with the background image.
     */
    private ImageView createBackgroundImage() {
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm()));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);
        return background;
    }

    /**
     * Creates a title text for the menu.
     * 
     * @return The Text object representing the title.
     */
    private Text createTitle() {
        Text title = new Text("SKY BATTLE");
        title.setFont(Font.font("Arial", 50));
        title.setStyle("-fx-fill: white; -fx-effect: dropshadow(gaussian, black, 5, 0.5, 0, 1);");
        return title;
    }

    /**
     * Creates a button with the specified text and action.
     * 
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return The Button object.
     */
    private Button createButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 20));
        button.setOnAction(action);
        return button;
    }

    /**
     * Starts the game by transitioning to Level One.
     */
    private void startGame() {
        try {
            Controller controller = new Controller(stage);
            controller.launchGame(); // Transition to Level One using the Controller
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the Key Binding page where the player can select the key bindings.
     */
    private void showKeyBindingPage() {
        try {
            KeyBindingPage keyBindingPage = new KeyBindingPage(stage);
            keyBindingPage.show(); // Display the KeyBindingPage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the Level Selection page where the player can choose which level to play.
     */
    private void showLevelSelectionPage() {
        try {
            LevelSelectionPage levelSelectionPage = new LevelSelectionPage(stage);
            levelSelectionPage.show(); // Display the LevelSelectionPage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
