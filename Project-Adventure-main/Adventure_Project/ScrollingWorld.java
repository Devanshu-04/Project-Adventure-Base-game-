import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

// ScrollingWorld is a super-class for a scrolling world (horizontal, vertical, or both).
// This class allows you to create a scrolling world where the camera follows the main actor.
public class ScrollingWorld extends World {
    private int scrollWidth, scrollHeight;
    private int actorMinX, actorMaxX, actorMinY, actorMaxY;
    private int scrolledX, scrolledY;
    private int scrollMode;
    protected Actor mainActor = null;
    private List<Actor> scrollableActors = new ArrayList<>();
    private GreenfootImage bgImage = null;
    private boolean isMusicPlaying;
    
    public ScoreDisplay scoreDisplay;  // Declare the ScoreDisplay actor
    private GreenfootSound backgroundMusic;
    // Constructor to create a scrolling world with specified dimensions.
    // Sets up the world to scroll horizontally, vertically, or both based on the world size.
    public LivesDisplay lives; 
    public ScrollingWorld(int width, int height, int cellSize, int worldWidth, int worldHeight) {
        super(width, height, cellSize, false);
        backgroundMusic = new GreenfootSound("backgroundMusic.mp3");
        isMusicPlaying = false;  // Initially, music is not playing
        if (worldWidth > width) {
            scrollMode = 1;  // Enable horizontal scrolling
        } else {
            scrollMode = 0;  // No horizontal scrolling
        }
        
        if (worldHeight > height) {
            scrollMode += 2;  // Enable vertical scrolling if required
        }
        
        if (scrollMode % 2 == 1) {
            scrollWidth = worldWidth;  // Set scrollWidth to worldWidth if horizontal scrolling is enabled
        } else {
            scrollWidth = width;  // No horizontal scrolling, use window width
        }
    
        if (scrollMode / 2 == 1) {
            scrollHeight = worldHeight;  // Set scrollHeight to worldHeight if vertical scrolling is enabled
        } else {
            scrollHeight = height;  // No vertical scrolling, use window height
        }
        
        // Initialize the ScoreDisplay and add it to the world
        
        scoreDisplay = new ScoreDisplay(0);
        addObject(scoreDisplay, 720, 40);  // Initially place it somewhere (it will be updated)
        
        lives = new LivesDisplay(10);
        addObject(lives, 720, 40); 
        
    }

    // Constructor for horizontal scrolling world.
    // It uses the provided world width but defaults the height to the window height.
    public ScrollingWorld(int width, int height, int cellSize, int worldWidth) {
        this(width, height, cellSize, worldWidth, height);
    }

    // Sets the main actor that the camera will follow within certain movement limits.
    public void setMainActor(Actor actor, int xRange, int yRange) {
        if (actor == null) {
            System.out.println("Main has not been supplied.");
            return;
        }
        super.addObject(actor, getWidth() / 2, getHeight() / 2);
        mainActor = actor;
        xRange = Math.min(xRange, getWidth());
        yRange = Math.min(yRange, getHeight());
        actorMinX = getWidth() / 2 - xRange / 2;
        actorMaxX = getWidth() / 2 + xRange / 2;
        actorMinY = getHeight() / 2 - yRange / 2;
        actorMaxY = getHeight() / 2 + yRange / 2;
    }

    // Sets the background image of the scrolling world and scales it accordingly.
    public void setScrollingBackground(GreenfootImage background) {
        if (mainActor == null) {
            System.out.println("'setMainActor' must be called first.");
            return;
        }
        bgImage = new GreenfootImage(background);
        bgImage.scale(scrollWidth * getCellSize()+200, scrollHeight * getCellSize()+750);
        updateBackground();
    }

    // Adds an actor to the world and specifies if the actor should scroll with the world.
    public void addObject(Actor actor, int x, int y, boolean scrollable) {
        super.addObject(actor, x, y);
        if (scrollable) 
            scrollableActors.add(actor);
    }

    // Overloaded method to add an actor that scrolls by default.
    public void addObject(Actor actor, int x, int y) {
        addObject(actor, x, y, true);
    }

    // Removes an actor from the world, and if it's scrollable, it is removed from the scroll list.
    public void removeObject(Actor actor) {
        if (actor == null) return;
        if (actor.equals(mainActor)) 
            mainActor = null;
        else 
            scrollableActors.remove(actor);
        super.removeObject(actor);
    }

    // Main update loop for the world.
    // Handles scrolling of both the background and the objects.
    public void act() {
        updateObjects();
        updateBackground();
        if (!isMusicPlaying) {
            backgroundMusic.setVolume(40);
            backgroundMusic.playLoop();  // Start the music
            isMusicPlaying = true;       // Set the flag to true
        }
        // Manually set the ScoreDisplay to a fixed position (top-left corner)
        scoreDisplay.setLocation(getWidth() - 80, getHeight() - 760); 
        lives.setLocation(getWidth() - 35, getHeight() - 730);
        
        // Position relative to the screen, not the world
    }

    // Updates the background position to reflect the scrolling offsets.
    private void updateBackground() {
        if (bgImage == null) 
            return;
    
        GreenfootImage worldBackground = getBackground(); // Get the world's current background
        int cellSize = getCellSize();
    
        // Calculate the offset for the scrolling background (horizontal and vertical)
        int bgOffsetX = -(scrolledX * cellSize % bgImage.getWidth()) - 500;
        int bgOffsetY = -(scrolledY * cellSize % bgImage.getHeight()) - 750;
    
        // Clear the world's background to prepare for redrawing
        worldBackground.clear();
    
        // Draw the background image repeatedly to fill the world horizontally and vertically
        for (int x = bgOffsetX; x < worldBackground.getWidth(); x += bgImage.getWidth()) {
            for (int y = bgOffsetY; y < worldBackground.getHeight(); y += bgImage.getHeight()) {
                worldBackground.drawImage(bgImage, x, y);
            }
        }
    }


    // Updates the position of scrollable objects based on the main actor's movement.
    private void updateObjects() {
        if (mainActor == null) 
            return;

        int deltaX = 0, deltaY = 0;
        if (mainActor.getX() < actorMinX) 
            deltaX = actorMinX - mainActor.getX();
        if (mainActor.getX() > actorMaxX) 
            deltaX = actorMaxX - mainActor.getX();
        if (mainActor.getY() < actorMinY) 
            deltaY = actorMinY - mainActor.getY();
        if (mainActor.getY() > actorMaxY) 
            deltaY = actorMaxY - mainActor.getY();
        if (deltaX == 0 && deltaY == 0) 
            return;

        scrolledX -= deltaX;
        scrolledY -= deltaY;

        mainActor.setLocation(mainActor.getX() + deltaX, mainActor.getY() + deltaY);

        for (Actor actor : scrollableActors) {
            actor.setLocation(actor.getX() + deltaX, actor.getY() + deltaY);
        }
    }
    
    public void increaseScore(int points) {
        scoreDisplay.setScore(scoreDisplay.getScore() + points);  // Update the score in ScoreDisplay
    }
    
    // Returns the horizontal scroll offset.
    public int getScrolledX() {
        return scrolledX;
    }

    // Returns the vertical scroll offset.
    public int getScrolledY() {
        return scrolledY;
    }
    public void setScrolledY(int y){
        scrolledY = y;
    }
        public void setScrolledX(int x){
        scrolledX= x;
    }

    // Returns the total width of the scrollable area.
    public int getScrollWidth() {
        return scrollWidth;
    }

    // Returns the total height of the scrollable area.
    public int getScrollHeight() {
        return scrollHeight;
    }
    
    public void deacreaseLive(){
        lives.deacreaseLive();
    }
    public void killSound(){
        backgroundMusic.stop();
    }
    
}
