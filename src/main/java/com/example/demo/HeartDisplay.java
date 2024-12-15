package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The {@code HeartDisplay} class is responsible for displaying a row of heart icons
 * representing the player's health. It manages the layout and the number of hearts displayed.
 */
public class HeartDisplay {
	
	/** The file path of the heart image. */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	
	/** The height of each heart image. */
	private static final int HEART_HEIGHT = 50;
	
	/** The index of the first item in the container. */
	private static final int INDEX_OF_FIRST_ITEM = 0;
	
	/** The container holding the heart icons. */
	private HBox container;
	
	/** The X-coordinate position of the heart container. */
	private double containerXPosition;
	
	/** The Y-coordinate position of the heart container. */
	private double containerYPosition;
	
	/** The number of hearts to display initially. */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a {@code HeartDisplay} object and initializes the heart icons and layout.
	 *
	 * @param xPosition the X-coordinate position of the heart display
	 * @param yPosition the Y-coordinate position of the heart display
	 * @param heartsToDisplay the initial number of hearts to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}
	
	/**
	 * Initializes the container (an {@link HBox}) that holds the heart icons.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);		
	}
	
	/**
	 * Populates the container with the specified number of heart icons.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}
		
	/**
	 * Removes one heart icon from the display, starting from the leftmost heart.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}
	
	/**
	 * Returns the container holding the heart icons.
	 *
	 * @return the {@link HBox} container
	 */
	public HBox getContainer() {
		return container;
	}

}
