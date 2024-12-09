package com.example.demo;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

	@Override
	protected void goToLevel(String levelName) {
		// Handle level transition logic
		try {
			System.out.println("Attempting to transition to level: " + levelName);

			// Instantiate the next level dynamically using reflection
			LevelParent nextLevel = (LevelParent) Class.forName(levelName)
					.getConstructor(double.class, double.class)
					.newInstance(getRoot().getScene().getHeight(), getRoot().getScene().getWidth());
			
			// Replace the current scene with the new level's scene
			getRoot().getScene().setRoot(nextLevel.initializeScene().getRoot());
			nextLevel.startGame();
		} catch (Exception e) {
			System.err.println("Failed to transition to level: " + levelName);
			e.printStackTrace();
		}
	}
}
