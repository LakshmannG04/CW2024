package com.example.demo;

import javafx.scene.image.Image;

/**
 * The {@code EnemyPlane} class represents an enemy aircraft in the game. It extends the {@link FighterPlane} class
 * and includes functionality for movement, firing projectiles, and difficulty scaling.
 */
public class EnemyPlane extends FighterPlane {
	
	/** Height of the enemy plane image. */
	private static final int IMAGE_HEIGHT = 150;
	
	/** Horizontal velocity of the enemy plane. */
	private static final int HORIZONTAL_VELOCITY = -6;
	
	/** X-coordinate offset for the enemy plane's projectile. */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	
	/** Y-coordinate offset for the enemy plane's projectile. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	
	/** Initial health of the enemy plane. */
	private static final int INITIAL_HEALTH = 1;
	
	/** Base fire rate for the enemy plane's projectiles. */
	private static final double BASE_FIRE_RATE = .01;
	
	/** Difficulty factor affecting the enemy plane's health and fire rate. */
	private final double difficultyFactor;

	/**
	 * Constructs a new {@code EnemyPlane} with the specified position, difficulty factor, and image.
	 *
	 * @param initialXPos the initial X-coordinate position of the enemy plane
	 * @param initialYPos the initial Y-coordinate position of the enemy plane
	 * @param difficultyFactor the difficulty factor affecting health and fire rate
	 * @param imageName the name of the image file for the enemy plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos, double difficultyFactor, String imageName) {
		super(new Image(EnemyPlane.class.getResourceAsStream("/com/example/demo/images/" + imageName)), IMAGE_HEIGHT, initialXPos, initialYPos, (int) (INITIAL_HEALTH * difficultyFactor));
		this.difficultyFactor = difficultyFactor;
	}

	/**
	 * Constructs a new {@code EnemyPlane} with the specified position and difficulty factor,
	 * using a default image.
	 *
	 * @param initialXPos the initial X-coordinate position of the enemy plane
	 * @param initialYPos the initial Y-coordinate position of the enemy plane
	 * @param difficultyFactor the difficulty factor affecting health and fire rate
	 */
	public EnemyPlane(double initialXPos, double initialYPos, double difficultyFactor) {
		this(initialXPos, initialYPos, difficultyFactor, "enemyplane.png");
	}

	/**
	 * Returns the difficulty factor for the enemy plane.
	 *
	 * @return the difficulty factor
	 */
	public double getDifficultyFactor() {
		return difficultyFactor;
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane if the random fire rate condition is met.
	 *
	 * @return a new {@link EnemyProjectile} if fired, or {@code null} otherwise
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		double adjustedFireRate = BASE_FIRE_RATE * getDifficultyFactor();
		if (Math.random() < adjustedFireRate) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
