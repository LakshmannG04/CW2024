package com.example.demo;

import javafx.scene.image.Image;

/**
 * The Projectile class is an abstract class that represents a projectile in the game. 
 * It extends the ActiveActorDestructible class, meaning it has properties and behaviors
 * common to all destructible active actors, such as taking damage and being destroyed.
 * The Projectile class is designed to be subclassed for different types of projectiles
 * with specific behaviors for updating their position and appearance.
 */
public abstract class Projectile extends ActiveActorDestructible {

    /**
     * Constructor for the Projectile class. Initializes the projectile with an image, 
     * image height, and initial position on the screen.
     * 
     * @param imageName The name of the image to be used for the projectile.
     * @param imageHeight The height of the image (used for scaling).
     * @param initialXPos The initial X position of the projectile on the screen.
     * @param initialYPos The initial Y position of the projectile on the screen.
     */
    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        // Calls the constructor of the superclass with the image and position details.
        super(new Image(Projectile.class.getResourceAsStream("/com/example/demo/images/" + imageName)), 
              imageHeight, initialXPos, initialYPos);
    }

    /**
     * This method handles the damage interaction for the projectile. 
     * In this case, the projectile is destroyed when it takes damage.
     */
    @Override
    public void takeDamage() {
        this.destroy();  // Destroys the projectile
    }

    /**
     * Abstract method to update the position of the projectile. 
     * This method must be implemented by subclasses to define how 
     * the projectile's position changes during the game.
     */
    @Override
    public abstract void updatePosition();
}
