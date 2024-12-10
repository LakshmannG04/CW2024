package com.example.demo;

public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double BASE_FIRE_RATE = .01;
	
	private final double difficultyFactor;
	
	public EnemyPlane(double initialXPos, double initialYPos, double difficultyFactor) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, (int) (INITIAL_HEALTH * difficultyFactor));
        this.difficultyFactor = difficultyFactor; // Initialize final field
    }
	 
	 public double getDifficultyFactor() {
	        return difficultyFactor; // Add a getter for difficultyFactor
	    }

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

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

	@Override
	public void updateActor() {
		updatePosition();
	}

}
