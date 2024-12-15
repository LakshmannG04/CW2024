package com.example.demo;

import javafx.stage.Stage;

/**
 * Represents the first level in the game. Inherits from the LevelParent class and implements 
 * specific logic for enemy spawning, player health, and game progression.
 * <p>
 * This class is responsible for managing the level's specific details, such as the background 
 * image, total number of enemies, kills required to advance, and player health.
 * </p>
 */
public class LevelOne extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs a LevelOne object with the specified screen dimensions and stage.
     * 
     * @param screenHeight the height of the screen.
     * @param screenWidth the width of the screen.
     * @param stage the JavaFX Stage to display the level.
     */
    public LevelOne(double screenHeight, double screenWidth, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        this.stage = stage;
    }

    /**
     * Checks if the game is over, either due to the player's death or the player reaching 
     * the target number of kills to advance to the next level.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToLevel("com.example.demo.LevelTwo");
        }
    }

    /**
     * Initializes the friendly units in the level, specifically the player character.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units in the level based on the spawn probability, ensuring the total 
     * number of enemies does not exceed the defined limit.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < getSpawnProbability()) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, getDifficultyFactor()); // Default image
                addEnemyUnit(newEnemy);
            }
        }
    }

    /**
     * Instantiates the level's view, passing the root node and initial player health.
     * 
     * @return a new instance of LevelView for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the player has reached the target number of kills required to advance to the next level.
     * 
     * @return true if the player has reached or exceeded the kill target, false otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getTotalKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Gets the probability of an enemy unit spawning, adjusted by the level's difficulty factor.
     * 
     * @return the spawn probability, modified by the difficulty factor.
     */
    private double getSpawnProbability() {
        return ENEMY_SPAWN_PROBABILITY * getDifficultyFactor();
    }
}

