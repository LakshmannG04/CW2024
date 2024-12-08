package com.example.demo;

public class LevelOne extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget())
			goToNextLevel(NEXT_LEVEL);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	@Override
	protected void goToLevel(String levelName) {
		// Handle level transition logic
		try {
			if (levelName.equals(NEXT_LEVEL)) {
				System.out.println("Transitioning to Level Two...");
				// Logic for transitioning to the next level
				// For example, instantiate the next level class dynamically
				LevelParent nextLevel = (LevelParent) Class.forName(levelName)
					.getConstructor(double.class, double.class)
					.newInstance(getRoot().getScene().getHeight(), getRoot().getScene().getWidth());
				
				// Replace current scene with the new level's scene
				getRoot().getScene().setRoot(nextLevel.initializeScene().getRoot());
				nextLevel.startGame();
			} else {
				System.out.println("Unknown level: " + levelName);
			}
		} catch (Exception e) {
			System.err.println("Failed to transition to level: " + levelName);
			e.printStackTrace();
		}
	}
}
