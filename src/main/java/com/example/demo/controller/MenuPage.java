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

public class MenuPage {

    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/menu-background.jpg"; // Corrected image path
    private static final double SCREEN_WIDTH = 1300;
    private static final double SCREEN_HEIGHT = 750;

    private final Stage stage;

    public MenuPage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Background image
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm()));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);

        // Title
        Text title = new Text("SKY BATTLE");
        title.setFont(Font.font("Arial", 50));
        title.setStyle("-fx-fill: white; -fx-effect: dropshadow(gaussian, black, 5, 0.5, 0, 1);");

        // Buttons
        Button startButton = new Button("Start Game");
        Button quitButton = new Button("Quit Game");
        Button keyBindingsButton = new Button("Key Bindings"); // New button for key bindings
        startButton.setFont(Font.font("Arial", 20));
        quitButton.setFont(Font.font("Arial", 20));
        keyBindingsButton.setFont(Font.font("Arial", 20)); // Set font for the new button

        // Button actions
        startButton.setOnAction(e -> startGame());  // Navigate to Level One
        quitButton.setOnAction(e -> System.exit(0)); // Exit the application
        keyBindingsButton.setOnAction(e -> showKeyBindingPage()); // Navigate to KeyBindingPage

        // Layout
        VBox menuLayout = new VBox(20, title, startButton, quitButton, keyBindingsButton); // Add the new button to layout
        menuLayout.setStyle("-fx-alignment: center;");

        StackPane root = new StackPane(background, menuLayout);
        Scene menuScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        stage.setScene(menuScene);
        stage.show();
    }

    private void startGame() {
        try {
            Controller controller = new Controller(stage);
            controller.launchGame(); // Transition to Level One using the Controller
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showKeyBindingPage() {
        KeyBindingPage keyBindingPage = new KeyBindingPage(stage); // Create an instance of the KeyBindingPage
        keyBindingPage.show(); // Display the KeyBindingPage
    }
}
