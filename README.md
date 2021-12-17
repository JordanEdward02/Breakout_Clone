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

[Javadoc/index.html](Javadoc/index.html)

### Implemented features (working)

- Full start menu
- Highscores menu with file reader from highscores file
- Themes menu that swaps out css and texture image for all frames
- Level Choice menu that allows the user to start from any point
- Level reader that renders custom walls (levels) from external files
- Random speedup feature that increases user speed with brick breaks
- Score system that maintains across all levels
- Score counter that live updates on the screen when events happen
- Game finish popup that allows input of custom name when game finishes to be stored in external file
- Sound effects for menu buttons and ball collisions
- Texture images for all the gameplay elements
- Converted all code to javaFX, so it runs at PC frame rate

### New java classes

- SFXPlayer
- GameLoop
- GameBoardPainter
- ThemeMaintainer
- ScoreManager
- ElementsManager
- Paddle
- LevelManager
- BrickFactory
- DebugMenuController
- GameFinishedController
- HighMenuController
- MainGameController
- StartMenuController
- ThemeMenuController

### Modified java classes

- Wall
- Ball
- BallRubber
- Brick
- BrickClay
- BrickCement
- CrickSteel
- StartFrame