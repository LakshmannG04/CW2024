package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The WinImage class represents an image that is displayed when the player wins the game.
 * It extends the ImageView class and handles the display of a "You Win" image.
 */
public class WinImage extends ImageView {
    
    private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png"; // Path to the win image
    private static final int HEIGHT = 500; // Height of the win image
    private static final int WIDTH = 600; // Width of the win image
    
    /**
     * Constructs a WinImage object at the specified position.
     * The image is initially hidden.
     * 
     * @param xPosition The X position to place the image.
     * @param yPosition The Y position to place the image.
     */
    public WinImage(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false); // Initially, the image is hidden
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    /**
     * Makes the win image visible.
     * This method is called when the player wins the game.
     */
    public void showWinImage() {
        this.setVisible(true);
    }
}
