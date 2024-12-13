package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class LevelSelectionPage {

    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/menu-background.jpg"; // Background image path
    private static final double SCREEN_WIDTH = 1300;
    private static final double SCREEN_HEIGHT = 750;

    private final Stage stage;

    public LevelSelectionPage(Stage stage) {
        this.stage = stage;
    }

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

        // Layout configuration
        VBox layout = new VBox(20, level1Button, level2Button, level3Button);
        layout.setStyle("-fx-alignment: center;");
        StackPane root = new StackPane(background, layout);

        // Scene setup
        Scene levelSelectionScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(levelSelectionScene);
        stage.show();
    }

    private Button createLevelButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 20));
        button.setPrefWidth(200);
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2;"));
        return button;
    }

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
