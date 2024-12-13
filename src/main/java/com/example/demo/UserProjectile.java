package com.example.demo;

/**
 * The UserProjectile class represents a projectile fired by the user's plane in the game. 
 * It extends the Projectile class and handles the movement and updates of the user's projectile.
 * This class is responsible for the projectile's behavior, including its horizontal velocity and position updates.
 */
public class UserProjectile extends Projectile {

    private static final String IMAGE_NAME = "userfire.png"; // Image for the user projectile
    private static final int IMAGE_HEIGHT = 125; // Height of the projectile image
    private static final int HORIZONTAL_VELOCITY = 15; // Horizontal speed of the projectile

    /**
     * Constructs a new UserProjectile with the given initial position.
     * 
     * @param initialXPos The initial X position of the projectile.
     * @param initialYPos The initial Y position of the projectile.
     */
    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the projectile by moving it horizontally.
     * The projectile moves at a constant horizontal velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the projectile. This method is called to update the position of the projectile.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
