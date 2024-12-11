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

public class KeyBindingPage {

	private static final String BACKGROUND_IMAGE = "/com/example/demo/images/menu-background.jpg"; // Background image
    private static final double SCREEN_WIDTH = 1300;
    private static final double SCREEN_HEIGHT = 750;
    private final Stage stage;

    public KeyBindingPage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
    	// Background image
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm()));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);
        
        // Title
        Text title = new Text("Key Bindings");
        title.setFont(Font.font("Arial", 30));

        // Buttons for selecting key bindings
        Button arrowKeyButton = new Button("Arrow Keys");
        Button wasdButton = new Button("WASD Keys");
        arrowKeyButton.setFont(Font.font("Arial", 20));
        wasdButton.setFont(Font.font("Arial", 20));

        // Set button actions
        arrowKeyButton.setOnAction(e -> {
            KeyBindingSettings.setKeyBinding(KeyBindingSettings.Binding.ARROW_KEYS);
            goToMenuPage();
        });

        wasdButton.setOnAction(e -> {
            KeyBindingSettings.setKeyBinding(KeyBindingSettings.Binding.WASD_KEYS);
            goToMenuPage();
        });

        // Layout
        VBox layout = new VBox(20, title, arrowKeyButton, wasdButton);
        layout.setStyle("-fx-alignment: center;");

        StackPane root = new StackPane(background, layout);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    private void goToMenuPage() {
        MenuPage menuPage = new MenuPage(stage);
        menuPage.show(); // Return to MenuPage after selecting key binding
    }
}
