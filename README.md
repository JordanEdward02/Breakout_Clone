# Breakout_Clone 
## Jordan Lovett - 20236464

---

### How to compile the code

- Download the git repo
- Extract the files from the zip
- Create a new IntelliJ project from existing sources
- Import project from external model - choose "Maven"
- Build the project based on the POM file
- Go to the StartFrame class and run the application

### Javadoc Location

[Javadoc/index.html](Javadoc/index.html) (on GIT)

Or index.html in the javadocs folder

### Implemented features (working)

|Feature| Location|
|---|---|
|Full start menu| in FXML with controllers|
|Highscores menu with file reader from highscores file |HighscoresMenu.fxml|
|Themes menu that swaps out css and texture image for all frames|ThemeMenu.fxml|
|Level Choice menu that allows the user to start from any point|
|Level reader that renders custom walls (levels) from external files|code/GameplayElemenents/Levels/LevelManager.java|
|Random speedup feature that increases user speed with brick breaks | code/GameplayElements/Wall.java|
|Score system that's maintained across all levels|code/GameplayElements/ScoreManager.java|
| Score counter that live updates on the screen when events happen|Part of GameBoard.fxml|
| Game finish popup that allows input of custom name when game finishes to be stored in external file|GameFinish.fxml and GameFinishController |
| Sound effects for menu buttons and ball collisions| SFXPlayer.java|
| Texture images for all the gameplay elements (sprites)| resources/assets/*|
| Converted all code to javaFX, so it runs at PC frame rate|
| Added MVC, factory and singleton patterns|
| Debug Menu dialog box that live updates the game with level skips, ball speed changes and ball count reset| DebugMenu.fxml and DebugMenuController.java|
| Added JUnit tests for model classes| test/* |

### New java classes

- SFXPlayer
- GameLoop
- GameBoardPainter
- ThemeMaintainer
- ScoreManager - Manages the total score and score given for each feature of the game. This can easily be swapped out
- ElementsManager - Interface for all the model (GameplayElements)
- Paddle
- LevelManager - Renders walls (Brick[]) from external files
- BrickFactory - Factory for making any type of brick
- DebugMenuController
- GameFinishedController
- HighMenuController
- MainGameController
- StartMenuController
- ThemeMenuController

### Modified java classes

- Wall
- Ball - Kept as abstract, so you can easily expand this to add new balls
- BallRubber
- Brick - Abstract for different brick types to be added
- BrickClay
- BrickCement
- BrickSteel
- StartFrame