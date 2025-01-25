import greenfoot.*;

public class Player extends GenericObject {
    private int speed = 5;  // Speed multiplier for velocity changes
    private int gravity = 2;  // Gravity applied to the player
    private boolean onGround = false;  // Track if the player is on the ground
    private int checkpointX, checkpointY;  // Store world coordinates for respawn
    private boolean isMirrored = false;  // Track if the image is mirrored
    private GreenfootImage standingImage;  // The standing image for the player
    private GreenfootImage runningImageRight;   // The running animation for moving right
    private GreenfootImage runningImageLeft; 
    private int lives;
    private LivesDisplay livesDis;
    private int score; 

    // Constructor for Player
    public Player(int width, int height, GreenfootImage mario,LivesDisplay l) {
        super(width, height, mario);
        standingImage = new GreenfootImage("marioStanding.png");  // Load standing image
        runningImageRight = new GreenfootImage("marioRunning.gif"); // Load running gif for right direction
        runningImageLeft = new GreenfootImage(runningImageRight); // Copy the right-running image
        runningImageLeft.mirrorHorizontally(); // Mirror for left direction
        standingImage.scale(width, height);  // Scale the standing image to match the player's size
        runningImageRight.scale(width, height);   // Scale the running image to match the player's size
        runningImageLeft.scale(width, height);   // Scale the mirrored running image
        setImage(standingImage);  // Set the initial image to the standing image
        checkpointX = -100;  // Initial checkpoint X in world coordinates
        checkpointY = 342;
        lives = 10;
        livesDis = l;

    }

    // Act method to handle input, gravity, movement, and collisions
    public void act() {
        handleInput();
        applyGravity();
        move();
        checkPlatformCollision();
        checkLavaCollision();
        checkCheckpointCollision();
        
    }

    // Handle player input (keyboard controls)
private GreenfootSound walkingSound = new GreenfootSound("walking.mp3.wav");

public void handleInput() {
    boolean isWalking = false;
    walkingSound.setVolume(100);
    // Check for left or right movement
    if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
        dx = -speed;
        setImage(runningImageLeft);
        isWalking = true;  // Player is walking
    } else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
        dx = speed;
        setImage(runningImageRight);
        isWalking = true;  // Player is walking
    } else {
        dx = 0;
        setImage(standingImage);  // Switch back to standing image when not moving
    }

    // Handle jumping
    if ((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) && onGround) {
        dy = -35;  // Jump force
        onGround = false;

        // Play jump sound
        GreenfootSound jumpSound = new GreenfootSound("jump.mp3");
        jumpSound.setVolume(40);
        jumpSound.play();
    }

    // Play walking sound when moving left or right
    if (isWalking) {
        if (!walkingSound.isPlaying()) {
            walkingSound.playLoop();  // Start playing the walking sound in a loop
        }
    } else {
        walkingSound.stop();  // Stop the walking sound if not walking
    }
}

    // Apply gravity to the player
    private void applyGravity() {
        if (!onGround) {
            dy += gravity;
        } else {
            dy = 0;
        }
    }

    // Check for collisions with platforms
    private void checkPlatformCollision() {
        GenericObject platform = (GenericObject) getOneObjectAtOffset(0, getImage().getHeight() / 2, GenericObject.class);
        if (platform != null && dy > 0) {
            onGround = true;
            dy = 0;
            setLocation(getX(), platform.getY() - (getImage().getHeight() / 2 + platform.getImage().getHeight() / 2));
        } else {
            onGround = false;
        }

        checkSideCollisions();
    }

    private void checkSideCollisions() {
        GenericObject leftPlatform = (GenericObject) getOneObjectAtOffset(-getImage().getWidth() / 2, 0, GenericObject.class);
        if (leftPlatform != null && dx < 0) {
            setLocation(getX() + speed, getY());
        }

        GenericObject rightPlatform = (GenericObject) getOneObjectAtOffset(getImage().getWidth() / 2, 0, GenericObject.class);
        if (rightPlatform != null && dx > 0) {
            setLocation(getX() - speed, getY());
        }
    }

    @Override
    public void move() {
        setLocation(getX() + dx, getY() + dy);
    }

    // Check for collision with Lava
    private void checkLavaCollision() {
        Lava lava = (Lava) getOneIntersectingObject(Lava.class);
        if (lava != null) {
            GreenfootSound coinSound = new GreenfootSound("lava.wav");
            coinSound.play();
            // Get reference to the scrolling world
            ScrollingWorld world = (ScrollingWorld) getWorld();
            lives--; 
            livesDis.deacreaseLive();
            // Convert the checkpoint's absolute world coordinates to relative screen coordinates
            int relativeCheckpointX = checkpointX - world.getScrolledX();
            int relativeCheckpointY = checkpointY - world.getScrolledY();
    
            // Move the player to the checkpoint's screen-relative position
            setLocation(relativeCheckpointX, relativeCheckpointY);
        }
    }

    // Check for collision with Checkpoint
    private void checkCheckpointCollision() {
        Checkpoint checkpoint = (Checkpoint) getOneIntersectingObject(Checkpoint.class);
        if (checkpoint != null) {
            // Get the scrolling offsets from the world
            ScrollingWorld scrollingWorld = (ScrollingWorld) getWorld();
            int absoluteCheckpointX = checkpoint.getX() + scrollingWorld.getScrolledX();
            int absoluteCheckpointY = checkpoint.getY() + scrollingWorld.getScrolledY();
    
            // Update checkpoint to absolute world position
            checkpointX = absoluteCheckpointX;
            checkpointY = absoluteCheckpointY;
        
        }
    }

    
    
    public int getLives(){
        return lives;
    }
    
    public void killSound(){
        walkingSound.stop();
    }
}
