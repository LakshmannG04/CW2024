package com.example.demo;

import javafx.scene.image.Image;

/**
 * The UserPlane class represents the player's plane in the game. It extends the FighterPlane class and manages 
 * the movement, projectile firing, and tracking of kills for the player.
 * The user plane has vertical and horizontal movement, can fire projectiles, and tracks the player's progress 
 * through the game by counting kills.
 */
public class UserPlane extends FighterPlane {

    // Constants for the boundaries and initial positions of the user plane
    private static final double Y_UPPER_BOUND = -40;
    private static final double Y_LOWER_BOUND = 600.0;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 150;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int PROJECTILE_X_POSITION = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private static final double X_LEFT_BOUND = 0.0;
    private static final double X_RIGHT_BOUND = 800.0; // Adjust based on screen width
    private static final int HORIZONTAL_VELOCITY = 8;

    private int velocityMultiplier;
    private int totalKills; // Tracks total kills
    private int killsSinceLastReset; // Tracks kills since the last difficulty increment
    private int horizontalVelocityMultiplier = 0;

    /**
     * Constructs a new UserPlane with the given initial health.
     * The user plane's position is initialized, and the image is loaded.
     * 
     * @param initialHealth The initial health of the user plane.
     */
    public UserPlane(int initialHealth) {
        super(new Image(UserPlane.class.getResourceAsStream("/com/example/demo/images/userplane.png")), IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        velocityMultiplier = 0;
    }

    /**
     * Updates the position of the user plane based on its current velocity.
     * The plane moves vertically and horizontally within the defined screen bounds.
     */
    @Override
    public void updatePosition() {
        if (isMoving()) {
            double initialTranslateY = getTranslateY();
            this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
            double newYPosition = getLayoutY() + getTranslateY();
            if (newYPosition < Y_UPPER_BOUND || newYPosition > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
        }

        if (isMovingHorizontally()) {
            double initialTranslateX = getTranslateX();
            this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
            double newXPosition = getLayoutX() + getTranslateX();
            if (newXPosition < X_LEFT_BOUND || newXPosition > X_RIGHT_BOUND) {
                this.setTranslateX(initialTranslateX);
            }
        }
    }

    /**
     * Checks if the user plane is moving horizontally.
     * 
     * @return true if the user plane is moving horizontally, false otherwise.
     */
    private boolean isMovingHorizontally() {
        return horizontalVelocityMultiplier != 0;
    }

    /**
     * Updates the user plane's position and state.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the user plane.
     * The projectile is fired slightly in front of the user plane.
     * 
     * @return a new UserProjectile instance.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        double currentX = this.getLayoutX() + this.getTranslateX();
        double currentY = this.getLayoutY() + this.getTranslateY();
        double projectileXPosition = currentX + PROJECTILE_X_POSITION;
        double projectileYPosition = currentY + PROJECTILE_Y_POSITION_OFFSET;

        return new UserProjectile(projectileXPosition, projectileYPosition);
    }

    /**
     * Checks if the user plane is moving vertically.
     * 
     * @return true if the user plane is moving vertically, false otherwise.
     */
    private boolean isMoving() {
        return velocityMultiplier != 0;
    }

    /**
     * Moves the user plane up by setting the vertical velocity multiplier to -1.
     */
    public void moveUp() {
        velocityMultiplier = -1;
    }

    /**
     * Moves the user plane down by setting the vertical velocity multiplier to 1.
     */
    public void moveDown() {
        velocityMultiplier = 1;
    }

    /**
     * Stops the user plane's vertical movement by resetting the vertical velocity multiplier to 0.
     */
    public void stop() {
        velocityMultiplier = 0;
    }

    /**
     * Returns the total number of kills made by the user.
     * 
     * @return the total kills.
     */
    public int getTotalKills() {
        return totalKills;
    }

    /**
     * Returns the number of kills since the last difficulty increment.
     * 
     * @return the number of kills since the last reset.
     */
    public int getKillCountSinceLastReset() {
        return killsSinceLastReset;
    }

    /**
     * Increments both the total kills and the kills since the last reset.
     */
    public void incrementKillCount() {
        totalKills++;
        killsSinceLastReset++;
    }

    /**
     * Resets the kill count since the last difficulty increment to 0.
     */
    public void resetKillCountSinceLastReset() {
        killsSinceLastReset = 0;
    }

    /**
     * Moves the user plane left by setting the horizontal velocity multiplier to -1.
     */
    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    /**
     * Moves the user plane right by setting the horizontal velocity multiplier to 1.
     */
    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    /**
     * Stops the user plane's horizontal movement by resetting the horizontal velocity multiplier to 0.
     */
    public void stopHorizontalMovement() {
        horizontalVelocityMultiplier = 0;
    }
}
