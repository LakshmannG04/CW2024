package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import javafx.animation.*;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.example.demo.controller.KeyBindingSettings;
import com.example.demo.controller.MenuPage;
import javafx.geometry.Pos;

/**
 * Abstract class representing the parent for all levels in the game.
 * Provides common functionality for managing game entities, handling user input, 
 * and controlling the game loop.
 * <p>
 * This class is responsible for initializing the game scene, managing game 
 * entities such as the player and enemies, and handling game mechanics like 
 * projectile firing and collision detection.
 * </p>
 */
public abstract class LevelParent {

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;
    protected static final double BASE_FIRE_RATE = 0.01;

    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private final Group root;
    private final Scene scene;
    private final ImageView background;
    private final UserPlane user;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private Timeline timeline;
    private Timeline autoFireTimeline;
    private int currentNumberOfEnemies;
    private LevelView levelView;

    private double difficultyFactor = 1.0;
    private int killsSinceLastIncrement = 0;

    private final StringProperty levelName = new SimpleStringProperty();
    protected Stage stage;

    /**
     * Constructs a LevelParent object with the specified background image, screen dimensions,
     * and initial player health. Initializes game entities and prepares the level for play.
     * 
     * @param backgroundImageName the path to the background image for the level.
     * @param screenHeight the height of the screen.
     * @param screenWidth the width of the screen.
     * @param playerInitialHealth the initial health of the player.
     */
    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);

        this.timeline = new Timeline();
        this.autoFireTimeline = new Timeline();

        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(backgroundImageName)));
        this.background = new ImageView(bgImage);

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;

        initializeTimeline();
        initializeAutoFire();
        friendlyUnits.add(user);

        levelName.addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                goToLevel(newValue);
            }
        });
    }

    /**
     * Initializes the friendly units for the level, such as the user-controlled plane.
     */
    protected abstract void initializeFriendlyUnits();

    /**
     * Checks if the game is over, such as when the player is destroyed or reaches 
     * a target number of kills to advance to the next level.
     */
    protected abstract void checkIfGameOver();

    /**
     * Spawns enemy units in the level based on the game mechanics and level progression.
     */
    protected abstract void spawnEnemyUnits();

    /**
     * Instantiates the view for this level, displaying relevant information like health and score.
     * 
     * @return the LevelView instance for this level.
     */
    protected abstract LevelView instantiateLevelView();

    /**
     * Transitions to the next level based on the provided level name. 
     * Dynamically loads the level and clears the current level's resources.
     * 
     * @param levelName the fully qualified name of the next level's class.
     */
    protected void goToLevel(String levelName) {
        try {
            // Dynamically load the next level using reflection
            Class<?> levelClass = Class.forName(levelName);
            LevelParent nextLevel = (LevelParent) levelClass
                    .getConstructor(double.class, double.class, Stage.class)
                    .newInstance(getScreenHeight(), getScreenWidth(), stage);

            // Clear current level's resources
            clearLevel();

            // Create and set the new scene
            Scene nextScene = nextLevel.initializeScene();
            stage.setScene(nextScene);

            // Start the new level
            nextLevel.startGame();
        } catch (Exception e) {
            System.err.println("Failed to transition to level: " + levelName);
            e.printStackTrace();
        }
    }

    /**
     * Initializes the scene for the level, setting up the background, friendly units, and UI components.
     * 
     * @return the initialized scene for the level.
     */
    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    /**
     * Starts the game by playing the timeline animation for the game loop.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }


    /**
     * Updates the game state during each cycle of the game loop. This includes increasing difficulty,
     * spawning enemy units, updating actors, handling projectile collisions, and checking for win/lose conditions.
     */
    private void updateScene() {
        increaseDifficulty();
        spawnEnemyUnits();
        updateActors();
        generateEnemyFire();
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateLevelView();
        checkIfGameOver();
    }

    /**
     * Initializes the auto-fire functionality, allowing the player to automatically fire projectiles at regular intervals.
     */
    private void initializeAutoFire() {
        autoFireTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame autoFireFrame = new KeyFrame(Duration.millis(500), e -> fireProjectile());
        autoFireTimeline.getKeyFrames().add(autoFireFrame);
        autoFireTimeline.play();
    }

    /**
     * Initializes the game loop, which repeatedly updates the game state at regular intervals.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Initializes the background for the game scene, including setting up user input handling for key press and release events.
     */
    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);

        background.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        background.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));

        root.getChildren().add(background);
    }

    /**
     * Handles key press events based on the current key binding settings. This allows the player to move
     * the user-controlled character using either arrow keys or WASD keys.
     *
     * @param kc The key that was pressed.
     */
    private void handleKeyPress(KeyCode kc) {
        KeyBindingSettings.Binding binding = KeyBindingSettings.getKeyBinding();

        if (binding == KeyBindingSettings.Binding.ARROW_KEYS) {
            if (kc == KeyCode.UP) user.moveUp();
            if (kc == KeyCode.DOWN) user.moveDown();
            if (kc == KeyCode.LEFT) user.moveLeft();
            if (kc == KeyCode.RIGHT) user.moveRight();
        } else if (binding == KeyBindingSettings.Binding.WASD_KEYS) {
            if (kc == KeyCode.W) user.moveUp();
            if (kc == KeyCode.S) user.moveDown();
            if (kc == KeyCode.A) user.moveLeft();
            if (kc == KeyCode.D) user.moveRight();
        }
    }

    /**
     * Handles key release events based on the current key binding settings. This stops the user-controlled
     * character from moving when the corresponding key is released.
     *
     * @param kc The key that was released.
     */
    private void handleKeyRelease(KeyCode kc) {
        KeyBindingSettings.Binding binding = KeyBindingSettings.getKeyBinding();

        if (binding == KeyBindingSettings.Binding.ARROW_KEYS) {
            if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
            if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.stopHorizontalMovement();
        } else if (binding == KeyBindingSettings.Binding.WASD_KEYS) {
            if (kc == KeyCode.W || kc == KeyCode.S) user.stop();
            if (kc == KeyCode.A || kc == KeyCode.D) user.stopHorizontalMovement();
        }
    }

    /**
     * Fires a projectile from the user-controlled character. The projectile is added to the game scene
     * and tracked in the list of user projectiles.
     */
    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile();
        if (projectile != null) {
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
        }
    }

    /**
     * Generates enemy fire by iterating through all enemy units and spawning their projectiles.
     */
    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    /**
     * Spawns an enemy projectile and adds it to the game scene. The projectile is also added to the list of enemy projectiles.
     *
     * @param projectile The projectile to spawn.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Updates all actors in the game (user units, enemy units, and projectiles). This method is called in every game loop cycle.
     */
    private void updateActors() {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(ActiveActorDestructible::updateActor);
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes all destroyed actors (user units, enemy units, and projectiles) from the game scene.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes actors from the game scene and their respective lists if they are destroyed.
     *
     * @param actors The list of actors to check and remove.
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
            .filter(ActiveActorDestructible::isDestroyed)
            .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between friendly units and enemy units, causing both to take damage.
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between user projectiles and enemy units, causing both to take damage.
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units, causing both to take damage.
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles collisions between two lists of actors. When a collision occurs, both actors take damage.
     *
     * @param firstActors The first list of actors to check for collisions.
     * @param secondActors The second list of actors to check for collisions.
     */
    private void handleCollisions(List<ActiveActorDestructible> firstActors, List<ActiveActorDestructible> secondActors) {
        for (ActiveActorDestructible first : firstActors) {
            for (ActiveActorDestructible second : secondActors) {
                if (first.collidesWith(second)) {
                    first.takeDamage();
                    second.takeDamage();
                }
            }
        }
    }

    /**
     * Checks for any enemy units that have penetrated the user's defenses. If an enemy has crossed the
     * screen, the user takes damage, and the enemy is destroyed.
     */
    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Updates the game view, such as adjusting health indicators, based on the user's current health.
     */
    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    /**
     * Updates the player's kill count by comparing the difference between the current number of enemies
     * and the number of remaining enemy units, incrementing the kill count accordingly.
     */
    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Checks if the enemy has penetrated the defenses by determining if its horizontal position
     * exceeds the screen width.
     *
     * @param enemy The enemy actor to check.
     * @return True if the enemy has crossed the screen boundary, false otherwise.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    /**
     * Increases the difficulty of the game based on the number of kills the player has achieved.
     * Every 10 kills, the difficulty factor is increased by 0.1.
     */
    private void increaseDifficulty() {
        killsSinceLastIncrement += user.getKillCountSinceLastReset();
        if (killsSinceLastIncrement >= 10) { // Increase difficulty every 10 kills
            killsSinceLastIncrement = 0;
            difficultyFactor += 0.1; // Increment difficulty factor
        }
    }

    /**
     * Handles the game win condition by stopping the game timelines, displaying a win image, and providing
     * an option to return to the main menu.
     */
    protected void winGame() {
        timeline.stop();
        autoFireTimeline.stop();

        // Create the win image
        WinImage winImage = new WinImage(355, 175); // Adjust the position as needed
        winImage.showWinImage();

        // Create the "Back to Menu" button
        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setFont(Font.font("Arial", 20));
        backToMenuButton.setOnAction(e -> backToMenu()); // Redirect to the menu

        // Create a VBox to center the image and button
        VBox winLayout = new VBox(20); // Spacing of 20 pixels between elements
        winLayout.setAlignment(Pos.CENTER); // Center align all children
        winLayout.getChildren().addAll(winImage, backToMenuButton);

        // Create a semi-transparent overlay
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // Semi-transparent black background
        overlay.getChildren().add(winLayout);
        overlay.setPrefSize(getScreenWidth(), getScreenHeight()); // Full screen overlay

        // Add the overlay to the root without clearing the existing game elements
        root.getChildren().add(overlay);
    }

    /**
     * Handles the game over condition by stopping the game timelines, displaying a game over image,
     * and providing an option to return to the main menu.
     */
    protected void loseGame() {
        timeline.stop();  // Stop the game loop
        autoFireTimeline.stop();  // Stop the auto-firing timeline

        // Create the game over image
        ImageView gameOverImage = new ImageView(new Image(getClass().getResource("/com/example/demo/images/gameover.png").toExternalForm()));

        // Resize the image (adjust size as needed)
        gameOverImage.setFitWidth(500);
        gameOverImage.setFitHeight(300);
        gameOverImage.setPreserveRatio(true);  // Keep the aspect ratio intact

        // Create the "Back to Menu" button
        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setFont(Font.font("Arial", 20));
        backToMenuButton.setOnAction(e -> backToMenu());  // Redirect to the menu

        // Create a VBox to centralize the image and button
        VBox gameOverLayout = new VBox(20); // Spacing of 20 pixels between elements
        gameOverLayout.setAlignment(Pos.CENTER); // Center align all children
        gameOverLayout.getChildren().addAll(gameOverImage, backToMenuButton);

        // Create a semi-transparent overlay
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // Semi-transparent black background
        overlay.getChildren().add(gameOverLayout);
        overlay.setPrefSize(getScreenWidth(), getScreenHeight()); // Full screen overlay

        // Add the overlay to the root without clearing the existing game elements
        root.getChildren().add(overlay);
    }

    /**
     * Returns the user-controlled plane object.
     *
     * @return The user-controlled plane.
     */
    protected UserPlane getUser() {
        return user;
    }

    /**
     * Returns the root group of the game scene, which contains all game elements.
     *
     * @return The root group of the game scene.
     */
    protected Group getRoot() {
        return root;
    }

    /**
     * Returns the current number of enemy units in the game.
     *
     * @return The current number of enemy units.
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Adds a new enemy unit to the game scene and the list of enemy units.
     *
     * @param enemy The enemy unit to add.
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Returns the maximum Y position for enemy units, used for boundary calculations.
     *
     * @return The maximum Y position for enemy units.
     */
    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    /**
     * Returns the width of the game screen.
     *
     * @return The width of the screen.
     */
    protected double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Returns the height of the game screen.
     *
     * @return The height of the screen.
     */
    public double getScreenHeight() {
        return screenHeight;
    }

    /**
     * Returns the current difficulty factor of the game.
     *
     * @return The difficulty factor.
     */
    protected double getDifficultyFactor() {
        return difficultyFactor;
    }

    /**
     * Checks if the user-controlled plane is destroyed.
     *
     * @return True if the user-controlled plane is destroyed, false otherwise.
     */
    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    /**
     * Updates the current number of enemy units by checking the size of the enemyUnits list.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    /**
     * Displays a popup at the end of a level, allowing the user to either return to the main menu or
     * proceed to the next level.
     *
     * @param isFinalLevel True if this is the final level, in which case the "Proceed to Next Level" button is disabled.
     */
    protected void showWinPopup(boolean isFinalLevel) {
        // Create the popup layout
        VBox popupLayout = new VBox(20);
        popupLayout.setStyle("-fx-alignment: center; -fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20;");

        // "Back to Main Menu" button
        Button backToMenuButton = new Button("Back to Main Menu");
        backToMenuButton.setFont(Font.font("Arial", 20));
        backToMenuButton.setOnAction(e -> backToMenu());

        // "Proceed to Next Level" button (disabled if it's the final level)
        Button nextLevelButton = new Button("Proceed to Next Level");
        nextLevelButton.setFont(Font.font("Arial", 20));
        nextLevelButton.setOnAction(e -> goToNextLevel());

        // Add buttons to the layout
        popupLayout.getChildren().addAll(backToMenuButton, nextLevelButton);

        // If it's the final level, don't show the "Proceed to Next Level" button
        if (isFinalLevel) {
            nextLevelButton.setDisable(true);  // Disable the button if it's the final level
        }

        // Show the popup
        Scene popupScene = new Scene(new StackPane(popupLayout), getScreenWidth(), getScreenHeight());
        Stage popupStage = new Stage();
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    /**
     * Redirects the user back to the main menu by creating a new instance of the MenuPage and displaying it.
     */
    private void backToMenu() {
        MenuPage menuPage = new MenuPage(stage);  // Create an instance of MenuPage
        menuPage.show();  // Show the menu page
    }

    /**
     * Clears the current level by stopping the game loop, stopping auto-firing, and removing all actors
     * and projectiles from the game scene.
     */
    protected void clearLevel() {
        timeline.stop();            // Stop the game loop for the current level
        autoFireTimeline.stop();    // Stop the auto-fire timeline
        root.getChildren().clear(); // Remove all children (actors, projectiles, etc.)
    }

    /**
     * Checks if all game objectives have been completed, such as if all enemy units have been defeated.
     *
     * @return True if all objectives are complete, false otherwise.
     */
    protected boolean allObjectivesComplete() {
        // Example logic, adjust based on your game's objectives
        return enemyUnits.isEmpty(); // Or other condition specific to your game
    }

    /**
     * Proceeds to the next level by stopping the current level's game loop, cleaning up assets, and transitioning
     * to the next level.
     */
    protected void goToNextLevel() {
        timeline.stop();
        cleanAssets(); // Clean all assets on current screen
        goToLevel("com.example.demo.LevelTwo"); // Transition to LevelTwo
    }

    /**
     * Returns the name of the current level.
     *
     * @return The name of the current level.
     */
    protected String getLevelName() {
        return levelName.get(); // Assuming levelName is a StringProperty
    }

    /**
     * Cleans up all game assets for the current level before transitioning to the next level.
     */
    private void cleanAssets() {
        // Do a proper cleaning of all assets on the screen before proceeding to the next level.
        user.destroy();
        userProjectiles.clear();
        friendlyUnits.clear();
        enemyUnits.clear();
        enemyProjectiles.clear();
    }
}
