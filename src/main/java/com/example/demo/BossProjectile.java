package com.example.demo;

/**
 * The {@code BossProjectile} class represents a projectile fired by the Boss character in the game.
 * It extends the {@link Projectile} class and defines specific behavior for the Boss's projectile.
 */
public class BossProjectile extends Projectile {
	
	/** Path to the image representing the Boss projectile. */
	private static final String IMAGE_NAME = "fireball.png";
	
	/** Height of the Boss projectile image. */
	private static final int IMAGE_HEIGHT = 75;
	
	/** Horizontal velocity of the Boss projectile. */
	private static final int HORIZONTAL_VELOCITY = -15;
	
	/** Initial X-coordinate position of the Boss projectile. */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a new {@code BossProjectile} with the specified initial Y-coordinate position.
	 *
	 * @param initialYPos the initial Y-coordinate position of the projectile
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	/**
	 * Updates the state of the projectile by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
