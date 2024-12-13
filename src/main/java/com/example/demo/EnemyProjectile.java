package com.example.demo;

/**
 * The {@code EnemyProjectile} class represents a projectile fired by an enemy.
 * It extends the {@link Projectile} class and defines its behavior and attributes.
 */
public class EnemyProjectile extends Projectile {
	
	/** The image file name for the enemy projectile. */
	private static final String IMAGE_NAME = "enemyFire.png";
	
	/** The height of the enemy projectile image. */
	private static final int IMAGE_HEIGHT = 50;
	
	/** The horizontal velocity of the enemy projectile. */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs a new {@code EnemyProjectile} with the specified initial position.
	 *
	 * @param initialXPos the initial X-coordinate position of the projectile
	 * @param initialYPos the initial Y-coordinate position of the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the enemy projectile. Currently, it only updates its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
