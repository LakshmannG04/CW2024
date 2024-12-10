package com.example.demo;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
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
    
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}
	
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

	private boolean isMovingHorizontally() {
	    return horizontalVelocityMultiplier != 0;
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		 // Get the current X and Y position of the user plane
	    double currentX = this.getLayoutX() + this.getTranslateX();
	    double currentY = this.getLayoutY() + this.getTranslateY();
	    
	    // Spawn the projectile slightly in front of the user plane
	    double projectileXPosition = currentX + PROJECTILE_X_POSITION;
	    double projectileYPosition = currentY + PROJECTILE_Y_POSITION_OFFSET;

	    return new UserProjectile(projectileXPosition, projectileYPosition);
	}

	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	public void moveUp() {
		velocityMultiplier = -1;
	}

	public void moveDown() {
		velocityMultiplier = 1;
	}

	public void stop() {
		velocityMultiplier = 0;
	}

	// Tracks total kills made by the user
    public int getTotalKills() {
        return totalKills;
    }

    // Tracks kills since the last difficulty increment
    public int getKillCountSinceLastReset() {
        return killsSinceLastReset;
    }

    // Increments both total kills and kills since last reset
    public void incrementKillCount() {
        totalKills++;
        killsSinceLastReset++;
    }

    // Resets the kill counter since the last difficulty increment
    public void resetKillCountSinceLastReset() {
        killsSinceLastReset = 0;
    }
    
    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    public void stopHorizontalMovement() {
        horizontalVelocityMultiplier = 0;
    }

}