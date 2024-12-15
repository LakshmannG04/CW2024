package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ShieldImage class represents an image of a shield that can be displayed or hidden in the game.
 * It extends the ImageView class, which allows the shield image to be placed and manipulated in the scene.
 * The shield can be shown or hidden based on the game state.
 */
public class ShieldImage extends ImageView {

    // The image resource path for the shield image.
    private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
    // The size of the shield image.
    private static final int SHIELD_SIZE = 200;

    /**
     * Constructor to create a ShieldImage instance at the specified position.
     * The shield image is loaded, sized, and initially hidden.
     * 
     * @param xPosition The X position of the shield on the screen.
     * @param yPosition The Y position of the shield on the screen.
     */
    public ShieldImage(double xPosition, double yPosition) {
        // Set the initial position of the shield image.
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        
        // Load the shield image.
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        // Initially hide the shield image.
        this.setVisible(false);
        // Set the size of the shield.
        this.setFitHeight(SHIELD_SIZE);
        this.setFitWidth(SHIELD_SIZE);
    }

    /**
     * Makes the shield visible on the screen.
     */
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * Hides the shield from the screen.
     */
    public void hideShield() {
        this.setVisible(false);
    }
}
