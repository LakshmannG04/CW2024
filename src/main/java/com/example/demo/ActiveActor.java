package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code ActiveActor} class represents an abstract base class for interactive actors
 * in a JavaFX application. It extends {@link ImageView} to provide graphical representation
 * and defines behavior for actors that can move and update their positions.
 */
public abstract class ActiveActor extends ImageView {

    /**
     * Constructs an {@code ActiveActor} with the specified image, height, and initial position.
     *
     * @param image the image to be displayed for the actor
     * @param imageHeight the height of the image to be displayed
     * @param initialXPos the initial X-coordinate position of the actor
     * @param initialYPos the initial Y-coordinate position of the actor
     */
    public ActiveActor(Image image, int imageHeight, double initialXPos, double initialYPos) {
        this.setImage(image);
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
    }

    /**
     * Updates the position of the actor. This method must be implemented by subclasses
     * to define specific behavior for updating the actor's position.
     */
    public abstract void updatePosition();

    /**
     * Moves the actor horizontally by the specified amount.
     *
     * @param horizontalMove the amount to move the actor horizontally (positive for right, negative for left)
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the actor vertically by the specified amount.
     *
     * @param verticalMove the amount to move the actor vertically (positive for down, negative for up)
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
}
