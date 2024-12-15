package com.example.demo;

import java.util.*;
import javafx.scene.image.Image;

/**
 * The {@code Boss} class represents a powerful enemy in the game, extending the {@link FighterPlane} class.
 * It includes unique behaviors such as move patterns, firing projectiles, and activating a shield.
 */
public class Boss extends FighterPlane {

    /** Path to the boss's image. */
    private static final String IMAGE_NAME = "bossplane.png";
    private static final double INITIAL_X_POSITION = 900.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = .002;
    private static final int IMAGE_HEIGHT = 300;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 10;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 500;

    /** List representing the movement pattern of the boss. */
    private final List<Integer> movePattern;
    
    /** Indicates whether the boss is currently shielded. */
    private boolean isShielded;
    
    /** Tracks the number of consecutive moves in the same direction. */
    private int consecutiveMovesInSameDirection;

    /** Index of the current move in the move pattern. */
    private int indexOfCurrentMove;

    /** Tracks the number of frames the shield has been activated. */
    private int framesWithShieldActivated;

    /**
     * Constructs a new {@code Boss} with predefined image, initial position, and health.
     */
    public Boss() {
        super(new Image(Boss.class.getResourceAsStream("/com/example/demo/images/" + IMAGE_NAME)), IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();
    }

    /**
     * Updates the boss's position based on its move pattern.
     * Ensures the boss stays within the specified bounds.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Updates the boss's state, including its position and shield status.
     */
    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
    }

    /**
     * Fires a projectile from the boss's current position.
     *
     * @return a {@link BossProjectile} if fired in the current frame; otherwise {@code null}.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
    }

    /**
     * Reduces the boss's health if it is not shielded.
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    /**
     * Initializes the movement pattern for the boss.
     * The pattern alternates between moving up, down, and staying stationary.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Updates the boss's shield status, activating or deactivating it as needed.
     */
    private void updateShield() {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) activateShield();    
        if (shieldExhausted()) deactivateShield();
    }

    /**
     * Determines the next move for the boss based on its move pattern.
     *
     * @return the velocity for the next move.
     */
    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    /**
     * Determines if the boss should fire a projectile in the current frame.
     *
     * @return {@code true} if the boss fires a projectile; {@code false} otherwise.
     */
    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    /**
     * Calculates the initial Y-coordinate for the boss's projectile.
     *
     * @return the Y-coordinate for the projectile's initial position.
     */
    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    /**
     * Determines if the shield should be activated based on a probability.
     *
     * @return {@code true} if the shield should be activated; {@code false} otherwise.
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Checks if the shield has been active for its maximum duration.
     *
     * @return {@code true} if the shield duration is exhausted; {@code false} otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the shield for the boss.
     */
    private void activateShield() {
        isShielded = true;
    }

    /**
     * Deactivates the shield and resets the shield frame counter.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
    }
}
