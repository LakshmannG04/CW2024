package com.example.demo;

import javafx.scene.image.Image;        

/**
 * The {@code FighterPlane} class serves as an abstract base class for different types of fighter planes.
 * It extends the {@link ActiveActorDestructible} class and includes functionality for managing health
 * and firing projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

    /** The health of the fighter plane. */
    private int health;

    /**
     * Constructs a new {@code FighterPlane} with the specified image, size, position, and health.
     *
     * @param image the {@link Image} representing the fighter plane
     * @param imageHeight the height of the fighter plane image
     * @param initialXPos the initial X-coordinate position of the fighter plane
     * @param initialYPos the initial Y-coordinate position of the fighter plane
     * @param health the initial health of the fighter plane
     */
    public FighterPlane(Image image, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(image, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    /**
     * Abstract method to fire a projectile. Subclasses must implement the logic for creating a projectile.
     *
     * @return a new {@link ActiveActorDestructible} projectile
     */
    public abstract ActiveActorDestructible fireProjectile();

    /**
     * Reduces the health of the fighter plane by one. If health reaches zero, the plane is destroyed.
     */
    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    /**
     * Calculates the X-coordinate position for a projectile based on the fighter plane's position and an offset.
     *
     * @param xPositionOffset the offset for the X-coordinate position
     * @return the X-coordinate position for the projectile
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Calculates the Y-coordinate position for a projectile based on the fighter plane's position and an offset.
     *
     * @param yPositionOffset the offset for the Y-coordinate position
     * @return the Y-coordinate position for the projectile
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Checks if the fighter plane's health has reached zero.
     *
     * @return {@code true} if health is zero, otherwise {@code false}
     */
    private boolean healthAtZero() {
        return health == 0;
    }

    /**
     * Returns the current health of the fighter plane.
     *
     * @return the current health
     */
    public int getHealth() {
        return health;
    }
        
}
