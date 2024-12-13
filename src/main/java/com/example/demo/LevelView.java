package com.example.demo;

import javafx.scene.Group;

/**
 * The LevelView class is responsible for displaying the visual elements of the game level, including
 * the heart display, win image, and game over image. It provides methods to update and manage these 
 * visual elements based on the game's progress.
 */
public class LevelView {

    // Position constants for the various elements displayed on the screen.
    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private static final int WIN_IMAGE_X_POSITION = 355;
    private static final int WIN_IMAGE_Y_POSITION = 175;
    private static final int LOSS_SCREEN_X_POSITION = -160;
    private static final int LOSS_SCREEN_Y_POSITION = -375;

    // References to the visual elements displayed during the game.
    private final Group root;
    private final WinImage winImage;
    private final GameOverImage gameOverImage;
    private final HeartDisplay heartDisplay;

    /**
     * Constructor for the LevelView class.
     * Initializes the level view with the given root group and the number of hearts to display.
     *
     * @param root The root group to which the visual elements will be added.
     * @param heartsToDisplay The number of hearts to display on the screen.
     */
    public LevelView(Group root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
        this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
    }

    /**
     * Displays the heart display on the screen.
     * Adds the heart display container to the root group.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    /**
     * Displays the win image on the screen.
     * Adds the win image to the root group and shows it.
     */
    public void showWinImage() {
        root.getChildren().add(winImage);
        winImage.showWinImage();
    }

    /**
     * Displays the game over image on the screen.
     * Adds the game over image to the root group.
     */
    public void showGameOverImage() {
        root.getChildren().add(gameOverImage);
    }

    /**
     * Removes hearts from the display based on the number of hearts remaining.
     * It updates the heart display by removing hearts to reflect the current health.
     *
     * @param heartsRemaining The number of hearts to be displayed after removal.
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

    /**
     * Clears all child nodes from the root group.
     * This method is used to remove all visual elements from the screen during cleanup.
     */
    public void clearView() {
        root.getChildren().clear();
    }
}
