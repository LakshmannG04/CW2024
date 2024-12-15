# Table of Contents

---
- [GitHub](#github)
- [Compilation Instructions](#compilation-instructions)
- [Implemented and Working Properly](#implemented-and-working-properly)
- [Implemented but Not Working Properly](#implemented-but-not-working-properly)
- [Features Not Implemented](#features-not-implemented)
- [New Java Classes](#new-java-classes)
- [Modified Java Classes](#modified-java-classes)
- [Unexpected Problems](#unexpected-problems)

## GitHub

---
**Name:** Lakshmann Ganeson <br>
**Student ID:** 20493800 <br>
**Link:** https://github.com/LakshmannG04/CW2024.git

## Compilation Instructions
[Back to Table of Contents](#table-of-contents)

---

### 1. Check if you have installed JDK 21 or later
- Currently, Oracle offers JDK 21 or later for installation.
- JDK 22 is preferred
- You can find the download link here:
  https://www.oracle.com/cis/java/technologies/downloads/#jdk23-windows

- Verify your JDK version:
    ```bash
    java -version
    ```

### 2. Make sure you have installed JavaFX ver 21 and above
- Ensure that you are at least using JDK 17 or above as only those versions can support JavaFX 21++.
- You can find the download link here:
  https://gluonhq.com/products/javafx/

### 3. Install IntelliJ IDEA Community Edition 2023.3.4
- IntelliJ IDEA Community Edition was used as the IDE to refactor this project. Install 2023.3.4 or above to avoid any unexpected problems.
- You can download it here (make sure to download the community edition):
  https://www.jetbrains.com/idea/download/?section=windows

### 4. Install Git
- Windows: Download and install from [Git for Windows](https://gitforwindows.org/)
- macOS: Install via Homebrew: `brew install git`
- Linux: `sudo apt-get install git` (Ubuntu/Debian) or `sudo dnf install git` (Fedora)
- Verify installation:
  ```bash
  git --version
  ```

### 5. Clone the Repository
- Navigate a green button <> Code in github
- Copy the GitHub repository URL or Download ZIP
- Select "Clone Repository" in Intellij
- Paste the URL from earlier into the URL field in Repository URL
- Click "Clone", and IntelliJ will clone and open the repository.


### 6. Make sure that the compiler is running on JDK 22
- Go to `File` > `Project Structure` > `Project`.
- Set the `Project SDK` to JDK 22.
-  Go to `File` > `Settings` > `Build, Execution, Deployment` > `Compiler` > `Java Compiler`.
- Set the `Target bytecode version` to 22.

### 7. Make sure that all dependencies in pom.xml are added and up to 22
- Open your `pom.xml` file.
- Ensure the `maven.compiler.source` and `maven.compiler.target` properties are set to 22:
    ```xml
    <properties>
        <maven.compiler.source>22</maven.compiler.source>
        <maven.compiler.target>22</maven.compiler.target>
    </properties>
    ```
- Add and update dependencies to be compatible with JDK 22.
  ```xml
  <dependencies>
      <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-controls</artifactId>
          <version>22.0.2</version>
      </dependency>
      <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-fxml</artifactId>
          <version>22.0.1</version>
      </dependency>
      <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-media</artifactId>
          <version>22.0.1</version>
      </dependency>
      <!-- Add other dependencies here -->
  </dependencies>
  ```

### 8. Install dependencies
- Ensure you have Maven installed. Then, run:
  ```bash
  mvn clean install
  ```

### 9. Run the application
- to compile and run your JavaFX application
  ```bash
  mvn javafx:run
  ```
### 10. Run the Main.java file to start the game
- It is located in /src/main/java/com/example/demo/controller/Main.java
- You can click on the green play button on the top right of the screen to run the game.


## Implemented and Working Properly
[Back to Table of Contents](#table-of-contents)

---

### <ins>1. Main Menu</ins>
- Displaying main menu in game with **Start Game, Level Selection, KeyBindings and Quit Game** button to allow players to navigate key options easily.
  ![alt text](image-2.png)

### **Start Game**
- It will Immediately start the game at Level 1

### **Level Selection**
- Allow the player to choose levels.
- Include three levels.
- Provide a "Back to Main Menu" button to navigate back to the main menu page.
  ![alt text](image-3.png)

### **Key Bindings**
- Enable players to adjust the controls when playing the game.
- Display the key bindings for the control keys.
- You can choose between 2 presets which is 'WASD' or 'Arrow Keys'.
- Include a "Back" button to return to the previous page.
  ![alt text](image-4.png)

### <ins>4. New Level</ins>
- Introduced a new level called LevelTwo.java.
- This level is slightly more challenging than Level One but less difficult than Level Three (boss level).
- Features include spawning a new type of enemy with a higher spawn rate.
  ![alt text](image-5.png)

### <ins>8. Added Game Over and Win Screens/ins>
- Each level now includes a Game Over screen with an option to return to the main menu.
  ![alt text](image-6.png)
- A Win screen has been added to the third level, featuring an option to return to the main menu
  ![alt text](image-7.png)



## Implemented but Not Working Properly
[Back to Table of Contents](#table-of-contents)

---

### Not Applicable (N/A)
- All implemented features are working properly to prevent errors and warnings.



## Features Not Implemented
[Back to Table of Contents](#table-of-contents)

---

### <ins>5. Game Objectives</ins>
- The game objectives are not displayed during the levels, making it unclear to the player when or how they win.
- The player is unable to plan tactics while playing the game.
- For example, the player should know how many enemies need to be defeated to proceed to the next level, or how many waves they must survive to progress

### <ins>5. Health Bars</ins>
- A health bar should be displayed for enemies and bosses, as players are unaware of their initial health and the number of projectiles needed to defeat them.
- This would allow players to easily track the health of enemies and bosses, enhancing the gameplay experience.


## New Java Classes
[Back to Table of Contents](#table-of-contents)

---
### 1. MenuPage.java
#### (com/example/demo/controller/MenuPage.java)
- Responsible for displaying the main menu of the Sky Battle game.
- Provides buttons for starting the game, quitting the game, navigating to the key binding page, and selecting levels to play.
- Sets up the background image and title for the menu.

### 2.  KeyBindingPage.java
#### (com/example/demo/controller/KeyBindingPage.java)
- Displays the key binding settings page in the game.
- Allows the user to select between different key bindings (e.g., Arrow keys or WASD keys).
- Provides a button to return to the main menu.

### 3. LevelViewLevelThree.java
#### (com/example/demo/LevelViewLevelThree.java)
- A subclass of LevelView that manages the visual elements specific to Level Three.
- Displays a shield image in addition to the heart display, win image, and game over image.
- Provides methods to show and hide the shield image based on the game state.

### 4. LevelThree.java
#### (com/example/demo/Level/LevelThree.java)
- The boss level was initially level 2, but with the addition of a new level in between, it has now become level 3.
- Adjusted the boss's movements accordingly.

### 5. LevelSelectionPage.java
#### (com/example/demo/controller/LevelSelectionPage.java)
- Responsible for displaying the level selection screen in the game.
- Allows the user to choose from multiple levels and navigate to the selected level.
- Includes a button to return to the main menu.

### 6. KeyBindingSettings.java
#### (com/example/demo/controller/KeyBindingSettings.java)
- Manages the key binding configuration for the game.
- Allows the selection and retrieval of the current key binding, which can be either Arrow Keys or WASD Keys.
- Provides methods to get and set the current key binding.

## Modified Java Classes
[Back to Table of Contents](#table-of-contents)

---

### 1. EnemyPlane.java
#### (com/example/demo/EnemyPlane.java)
- Adjusted the image height and position, as well as the enemy's health and fire rate in Level 1.
- Changed the image of the enemy to update its appearance.
- The constructor now accepts a difficulty factor, which affects the initial health and fire rate of the enemy plane.
- The fireProjectile() method now calculates the adjusted fire rate based on the difficulty factor, allowing for dynamic gameplay adjustments.

### 2. EnemyProjectile.java
#### (com/example/demo/EnemyProjectile.java)
- Adjusted the image height and projectile velocity to enhance gameplay dynamics.
- The constructor now initializes the projectile with a specified position, ensuring it aligns correctly with the enemy's firing mechanics.
- The updatePosition() method ensures that the projectile moves horizontally at a consistent speed, providing a smoother experience during gameplay.

### 3. UserPlane.java
#### (com/example/demo/UserPlane.java)
- The fireProjectile() method creates and fires a User Projectile at specified offsets from the plane's current position, with projectiles fired at regular intervals controlled by a Timeline.
- When the player takes damage, the plane becomes invincible for a short duration. During this time, the plane blinks and its collision is disabled to prevent further damage.
- Increased the velocity of the user plane to enhance the gameplay experience and make it more fun.

### 4. Projectile.java
#### (com/example/demo/Actor/User/UserProjectile.java)
- The takeDamage() method is designed to destroy the projectile upon taking damage, ensuring that projectiles are removed from the game when they are no longer needed.
- The class is defined as abstract, requiring subclasses to implement the updatePosition() method, which allows for different projectile behaviors based on their specific types (e.g., user projectiles, enemy projectiles).
- This structure provides a clear and consistent way to manage projectiles in the game, allowing for easy extension and customization in subclasses.

### 5. Controller.java
#### (com/example/demo/controller/Controller.java)
- The controller is initialized with a Stage, which is used to manage the display of scenes.
- The launchGame() method now includes a call to stage.show() to ensure the game window is displayed before transitioning to the first level.
- The goToLevel() method dynamically loads the specified level class using reflection, allowing for flexible level transitions.
- The controller now observes the LevelParent, enabling it to respond to level changes and updates.
- Error handling has been improved with an Alert dialog to inform the user of any exceptions that occur during level transitions.

### 8. Main.java
#### (com/example/demo/controller/Main.java)
- The Main class serves as the entry point for the JavaFX application, initializing the game window with specified dimensions and title.
- The start() method sets the title of the stage and its dimensions, ensuring the game window is properly configured before launching.
- An instance of the Controller class is created, which manages the game flow and transitions between levels.
- The launchGame() method of the Controller is called to start the game, ensuring that the first level is displayed immediately upon launching the application.

### 10. LevelOne.java
#### (com/example/demo/LevelOne.java)
- Adjusted the kill count required to advance and modified the probability rates.
- It provides smooth transitions between the game state, offering the player the option to return to the main menu.

### 11. LevelTwo.java
#### (com/example/demo/Level/LevelTwo.java)
- Initially a boss level, it is now positioned as a level between Level One and the boss level.
- Adjusted the kill count required to advance and modified the probability rates.
- It provides smooth transitions between the game state, offering the player the option to return to the main menu.

### 12. LevelParent.java
#### (com/example/demo/LevelParent.java)
- The class now extends Observable, allowing it to notify observers (like the Controller) when certain events occur, such as transitioning to a new level.
- The management of userProjectiles and enemyProjectiles lists has been streamlined, but they are still retained.
- The levelView attribute is now initialized in the constructor, allowing for better management of the level's UI elements.
- The constructor now initializes the levelView using the instantiateLevelView() method, ensuring that the level's UI is set up correctly during initialization.
- The updateScene() method has been modified to include calls to checkIfGameOver() and updateLevelView(), ensuring that the game state is consistently updated during each cycle of the game loop.
- The collision handling methods have been streamlined to improve readability and maintainability. The handleCollisions() method now uses a more concise approach to check for collisions between two lists of actors.
- The winGame() and loseGame() methods have been added to handle the end of the game more effectively, stopping the timeline and displaying the appropriate images.
- The initializeBackground() method has been updated to handle key events more efficiently, allowing for smoother user input handling.
- Several utility methods have been added or modified to improve the management of game entities, such as updateNumberOfEnemies() and addEnemyUnit().

### 13. LevelView.java
#### (com/example/demo/LevelView.java)
- Adjusted the images position

## Unexpected Problems
[Back to Table of Contents](#table-of-contents)

---
### 1. Multiple Pop-Ups
- At the start, gotoNextLevel() did not properly handle the clearing of assets, which caused the game to crash and led to an excessive number of pop-ups, eventually crashing the PC.
  To resolve this, timeline.stop() was added to halt active timelines before proceeding to the next level.

### 2. Shield not appearing for Boss
- The shield was initially not visible during the boss stage.
- It was later identified that this issue stemmed from the order in which JavaFX elements were created, causing the shield to be layered behind the background.
- To resolve this, the shield was initialized as part of the Boss object, allowing it to correctly follow the boss’s movements while ensuring proper visibility.

### 3. Hitboxes Problems
- The hitboxes did not align accurately with the images, often being larger than expected, which caused unfair hits to the player.
- In the boss level, the boss's hitbox was disproportionately large, resulting in projectiles missing the boss visually but still causing damage.
- To address this, the images were resized individually after being replaced, and the hitbox sizes were adjusted to ensure fair and enjoyable gameplay.

### 4. UserProjectiles Not Aligning with Player's X Position
- After enabling the player to move left and right, it was observed that the projectiles did not align with the player's updated X position during movement.
- To resolve this, the player's current X position was incorporated into the projectile's creation logic, ensuring it spawns correctly relative to the player's position.

### 5. Userplane Immediately Takes Damage When Colliding with Enemies
- Without any "invincibility" frames, the player continuously took damage upon collision, unlike typical arcade games that provide a brief recovery period between hits.
- To address this, temporary invincibility (i-Frames) was implemented, accompanied by a blinking effect and disabled collision during this period to prevent consecutive damage.
