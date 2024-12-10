package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuPage extends Application {

    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/menu-background.jpg"; // Path to your background image
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
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
        startButton.setFont(Font.font("Arial", 20));
        quitButton.setFont(Font.font("Arial", 20));

        startButton.setOnAction(e -> startGame(primaryStage)); // Start game action
        quitButton.setOnAction(e -> System.exit(0));           // Quit game action

        // VBox for title and buttons
        VBox menuLayout = new VBox(20, title, startButton, quitButton);
        menuLayout.setStyle("-fx-alignment: center;");

        // StackPane for combining background and menu layout
        StackPane root = new StackPane(background, menuLayout);
        Scene menuScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Sky Battle - Menu");
        primaryStage.show();
    }

    private void startGame(Stage primaryStage) {
    	 LevelParent game = new LevelOne(600, 800); // Provide screen height and width
    	    Scene gameScene = game.initializeScene();  // Initialize the game scene
    	    primaryStage.setScene(gameScene);          // Set the new scene to the primary stage
    	    game.startGame(); // Start the game logic
    }

    public static void main(String[] args) {
        launch(args);
    }
}

