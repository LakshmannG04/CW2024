package com.example.demo;

import javafx.stage.Stage;

/**
 * Represents the second level in the game, extending the functionality provided by LevelParent.
 * This level introduces enemy planes that spawn based on a probability and includes gameplay 
 * mechanics such as the kill target required to advance to the next level.
 */
public class LevelTwo extends LevelParent {

    // Path to the background image for this level.
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

    // Total number of enemies to spawn in the level.
    private static final int TOTAL_ENEMIES = 10;

    // The number of kills required to advance to the next level.
    private static final int KILLS_TO_ADVANCE = 10;

    // The probability of spawning an enemy unit.
    private static final double ENEMY_SPAWN_PROBABILITY = 0.40;

    // Initial health for the player in this level.
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructor for LevelTwo. Sets the background image, screen dimensions, 
     * and initializes the level with player health.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth The width of the game screen.
     * @param stage The stage in which the level will be displayed.
     */
    public LevelTwo(double screenHeight, double screenWidth, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        this.stage = stage;
    }

    /**
     * Checks if the game is over by verifying if the user has been destroyed or if the user
     * has reached the kill target to advance to the next level.
     * If the user is destroyed, the player loses the game. If the kill target is met, 
     * the game transitions to LevelThree.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToLevel("com.example.demo.LevelThree");
        }
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
     * Spawns enemy units for this level based on a spawn probability and the current difficulty.
     * The method will add enemy planes to the level until the total number of enemies reaches
     * the specified maximum, with a random chance for each enemy to spawn.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < getSpawnProbability()) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, getDifficultyFactor(), "EnemyPlane2.png"); // Use EnemyPlane2.png
                addEnemyUnit(newEnemy);
            }
        }
    }

    /**
     * Instantiates the LevelView for this level, providing the user interface and game view.
     *
     * @return A new instance of LevelView, which displays the game's UI for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the user has reached the target number of kills to advance to the next level.
     *
     * @return True if the user has reached or exceeded the kill target, false otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getTotalKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Calculates the probability of spawning enemy units based on the current difficulty factor.
     *
     * @return The adjusted spawn probability, which increases with the difficulty factor.
     */
    private double getSpawnProbability() {
        return ENEMY_SPAWN_PROBABILITY * getDifficultyFactor();
    }
}
