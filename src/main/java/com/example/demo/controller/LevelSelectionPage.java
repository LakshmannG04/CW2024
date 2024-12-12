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
        // Background
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm()));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);

        // Buttons for levels
        Button level1Button = new Button("Level 1");
        Button level2Button = new Button("Level 2");
        level1Button.setFont(Font.font("Arial", 20));
        level2Button.setFont(Font.font("Arial", 20));

        // Button actions
        level1Button.setOnAction(e -> startLevel("com.example.demo.LevelOne"));
        level2Button.setOnAction(e -> startLevel("com.example.demo.LevelTwo"));

        // Layout
        VBox layout = new VBox(20, level1Button, level2Button);
        layout.setStyle("-fx-alignment: center;");
        StackPane root = new StackPane(background, layout);

        Scene levelSelectionScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(levelSelectionScene);
        stage.show();
    }

    private void startLevel(String levelClass) {
        try {
            Controller controller = new Controller(stage);
            controller.goToLevel(levelClass); // Navigate to the selected level
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

