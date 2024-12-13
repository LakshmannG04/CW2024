package com.example.demo;

import javafx.scene.Group;

/**
 * The LevelViewLevelThree class is a subclass of LevelView that handles the display of specific
 * visual elements for Level Three, including a shield image in addition to the heart display, 
 * win image, and game over image from its superclass.
 */
public class LevelViewLevelThree extends LevelView {

    // Constants for the shield image's position on the screen.
    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;

    // Reference to the root group and the shield image to be displayed.
    private final Group root;
    private final ShieldImage shieldImage;

    /**
     * Constructor for the LevelViewLevelThree class.
     * Initializes the level view with the given root group, the number of hearts to display, 
     * and adds the shield image to the root.
     *
     * @param root The root group to which the visual elements will be added.
     * @param heartsToDisplay The number of hearts to display on the screen.
     */
    public LevelViewLevelThree(Group root, int heartsToDisplay) {
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
     * This method is used to show the shield at its predefined position.
     */
    public void showShield() {
        shieldImage.showShield();
    }

    /**
     * Hides the shield image from the screen.
     * This method is used to hide the shield when it is no longer needed.
     */
    public void hideShield() {
        shieldImage.hideShield();
    }
}
