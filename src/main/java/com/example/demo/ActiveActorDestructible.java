package com.example.demo;

import javafx.scene.image.Image;

/**
 * The {@code ActiveActorDestructible} class extends {@link ActiveActor} and implements {@link Destructible},
 * providing additional functionality for actors that can be destroyed and interact with other destructible actors.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

    /**
     * Indicates whether the actor is destroyed.
     */
    private boolean isDestroyed;

    /**
     * Constructs an {@code ActiveActorDestructible} with the specified image, height, and initial position.
     *
     * @param image the image to be displayed for the actor
     * @param imageHeight the height of the image to be displayed
     * @param initialXPos the initial X-coordinate position of the actor
     * @param initialYPos the initial Y-coordinate position of the actor
     */
    public ActiveActorDestructible(Image image, int imageHeight, double initialXPos, double initialYPos) {
        super(image, imageHeight, initialXPos, initialYPos);
        isDestroyed = false;
    }

    /**
     * Updates the position of the actor. This method must be implemented by subclasses
     * to define specific behavior for updating the actor's position.
     */
    @Override
    public abstract void updatePosition();

    /**
     * Updates the state of the actor. This method must be implemented by subclasses
     * to define specific behavior for actor updates.
     */
    public abstract void updateActor();

    /**
     * Applies damage to the actor. This method must be implemented by subclasses
     * to define specific behavior for taking damage.
     */
    @Override
    public abstract void takeDamage();

    /**
     * Destroys the actor by marking it as destroyed.
     */
    @Override
    public void destroy() {
        setDestroyed(true);
    }

    /**
     * Sets the destroyed state of the actor.
     *
     * @param isDestroyed {@code true} if the actor is destroyed; {@code false} otherwise
     */
    protected void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    /**
     * Checks whether the actor is destroyed.
     *
     * @return {@code true} if the actor is destroyed; {@code false} otherwise
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Checks if this actor collides with another destructible actor.
     *
     * @param other the other {@code ActiveActorDestructible} to check for collision
     * @return {@code true} if the actors collide; {@code false} otherwise
     */
    public boolean collidesWith(ActiveActorDestructible other) {
        return this.getBoundsInParent().intersects(other.getBoundsInParent());
    }

    /**
     * Checks if the actor is out of the screen bounds.
     *
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @return {@code true} if the actor is out of bounds; {@code false} otherwise
     */
    public boolean isOutOfBounds(double screenWidth, double screenHeight) {
        return getTranslateX() < -getWidth() || getTranslateX() > screenWidth ||
               getTranslateY() < -getHeight() || getTranslateY() > screenHeight;
    }

    /**
     * Returns the width of the actor. This method should be implemented to provide the correct width.
     *
     * @return the width of the actor
     */
    private int getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Returns the height of the actor. This method should be implemented to provide the correct height.
     *
     * @return the height of the actor
     */
    private int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }
}
