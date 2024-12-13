package com.example.demo;

import javafx.stage.Stage;

/**
 * Represents the third level in the game, extending the base functionality provided by LevelParent.
 * This level introduces a Boss enemy and contains game-specific behavior such as initializing
 * friendly units, checking game-over conditions, and spawning the boss enemy when necessary.
 */
public class LevelThree extends LevelParent {

    // Path to the background image for the level.
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";

    // Initial health for the player in this level.
    private static final int PLAYER_INITIAL_HEALTH = 5;

    // The boss enemy specific to this level.
    private final Boss boss;

    /**
     * Constructor for LevelThree. Sets the background image, screen dimensions, 
     * and initializes the boss enemy.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth The width of the game screen.
     * @param stage The stage in which the level will be displayed.
     */
    public LevelThree(double screenHeight, double screenWidth, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        this.stage = stage;
        this.boss = new Boss();
    }

    /**
     * Initializes the friendly units for this level, specifically adding the user-controlled character
     * to the game scene.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over by verifying the destruction status of the user-controlled plane and the boss.
     * If the user plane is destroyed, the player loses. If the boss is destroyed, the player wins.
     */
    @Override 
    protected void checkIfGameOver() { 
        if (userIsDestroyed()) { 
            loseGame(); 
        } else if (boss.isDestroyed()) { 
            winGame(); // Call winGame when the boss is destroyed
        } 
    }

    /**
     * Spawns the enemy units for this level. In LevelThree, only the boss is spawned.
     * This method adds the boss to the enemy units when there are no existing enemies.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    /**
     * Instantiates the LevelView for this level, providing the user interface and game view.
     *
     * @return A new instance of LevelViewLevelTwo, which displays the game's UI for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
    }
}
