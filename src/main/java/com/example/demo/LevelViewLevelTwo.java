package com.example.demo;

import javafx.scene.Group;

/**
 * The LevelViewLevelTwo class is a subclass of LevelView that manages the visual elements 
 * specific to Level Two, including displaying a shield image along with the heart display 
 * and other UI elements inherited from the superclass LevelView.
 */
public class LevelViewLevelTwo extends LevelView {

    // Constants for the shield image's position on the screen.
    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;

    // Reference to the root group and the shield image to be displayed.
    private final Group root;
    private final ShieldImage shieldImage;

    /**
     * Constructor for the LevelViewLevelTwo class.
     * Initializes the level view with the given root group and the number of hearts to display,
     * then adds the shield image to the root.
     *
     * @param root The root group to which the visual elements will be added.
     * @param heartsToDisplay The number of hearts to display on the screen.
     */
    public LevelViewLevelTwo(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);  // Calls the superclass constructor to initialize hearts display
        this.root = root;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        addImagesToRoot();  // Adds the shield image to the root group
    }

    /**
     * Adds the shield image to the root group.
     * This method is called during initialization to display the shield.
     */
    private void addImagesToRoot() {
        root.getChildren().addAll(shieldImage);
    }

    /**
     * Displays the shield image on the screen.
     * This method is used to make the shield visible at its predefined position.
     */
    public void showShield() {
        shieldImage.showShield();
    }

    /**
     * Hides the shield image from the screen.
     * This method is used to remove the shield from view when it is no longer needed.
     */
    public void hideShield() {
        shieldImage.hideShield();
    }
}
