package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code GameOverImage} class represents the "Game Over" screen image in the game.
 * It extends the {@link ImageView} class and provides functionality for setting the image
 * and its position.
 */
public class GameOverImage extends ImageView {
	
	/** The file path of the "Game Over" image. */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructs a new {@code GameOverImage} object and sets its position on the screen.
	 *
	 * @param xPosition the X-coordinate position of the image
	 * @param yPosition the Y-coordinate position of the image
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}
