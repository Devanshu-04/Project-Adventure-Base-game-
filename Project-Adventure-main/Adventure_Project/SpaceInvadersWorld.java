import greenfoot.*;
import java.util.Random;

public class SpaceInvadersWorld extends World {
    private PlayerShip player;
    private int enemySpawnTimer = 0;
    private int enemySpeed = 2; // Adjust speed as needed
    private int delayTimer = 0;
    private Popup popup;
    private ScoreDisplay scoreDisplay;
    private LivesDisplay lives;
    private String name;
    private boolean isMusicPlaying;
    private GreenfootSound backgroundMusic;
    public SpaceInvadersWorld() {
        super(800, 600, 1);

        // Set the background to black with random white stars
        GreenfootImage bgImage = new GreenfootImage(getWidth(), getHeight());
        bgImage.setColor(Color.BLACK); // Set the background color to black
        bgImage.fill(); // Fill the entire background with black

        addStars(bgImage, 100); // Add 100 random stars to the background
        setBackground(bgImage); // Set the background with stars
        
        scoreDisplay = new ScoreDisplay(0);
        addObject(scoreDisplay, 720, 40);  // Initially place it somewhere (it will be updated)
        
        lives = new LivesDisplay(5);
        addObject(lives, 760, 80);
        backgroundMusic = new GreenfootSound("backgroundMusicSpace.mp3");
        isMusicPlaying = false;
        
        player = new PlayerShip(scoreDisplay);
        addObject(player, getWidth() / 2, getHeight() - 50);
        
 
        
        // Create and display the popup when the world is initialized
        popup = new Popup("spaceshooter.png"); // Use your PNG image file name
        addObject(popup, 400, 300); // Center the popup
    }

    public void act() {
        // Handle popup removal
        if (popup != null) {
            if (Greenfoot.isKeyDown("space")) {
                removeObject(popup);
                popup = null; // Clear the reference to avoid duplicate removals
                delayTimer = 3 * 60; // 15 seconds delay (assuming 60 frames per second)
            }
        } else if (delayTimer > 0) {
            delayTimer--; // Count down the delay timer
        } else {
            spawnEnemies(); // Start spawning enemies after the delay
        }
        
        if (lives.getLives() <= 0) {
            // Show the game over screen
            this.killSound();
            Greenfoot.setWorld(new GameOverScreen(this.scoreDisplay.getScore(), name));
        }
        
        if (!isMusicPlaying) {
            backgroundMusic.setVolume(40);
            backgroundMusic.playLoop();  // Start the music
            isMusicPlaying = true;       // Set the flag to true
        }
    }

    private void spawnEnemies() {
        if (enemySpawnTimer == 0) {
            EnemyShip enemy = new EnemyShip(lives);
            enemy.setSpeed(enemySpeed); // Set enemy speed
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
            enemySpawnTimer = 50; // Adjust spawn rate as needed
        } else {
            enemySpawnTimer--;
        }
    }

    private void checkLoseCondition() {
        if (player == null || player.getY() <= 0) {
            Greenfoot.setWorld(new StartScreen()); // Reset to start screen or initial state
        }
    }

    /**
     * Add random white stars to the given background image.
     *
     * @param bgImage The background image to draw stars on.
     * @param numStars The number of stars to add.
     */
    private void addStars(GreenfootImage bgImage, int numStars) {
        Random random = new Random();

        // Set the color to white for stars
        bgImage.setColor(Color.WHITE);

        for (int i = 0; i < numStars; i++) {
            int x = random.nextInt(bgImage.getWidth()); // Random x-coordinate
            int y = random.nextInt(bgImage.getHeight()); // Random y-coordinate
            int size = random.nextInt(3) + 1; // Random size between 1 and 3 pixels
            bgImage.fillOval(x, y, size, size); // Draw a small white dot (star)
        }
    }
    public void setup(int s, int l, String nam){
        name = nam;
        lives.setLives(l);
        scoreDisplay.setScore(s);        
    }
    public void killSound(){
        backgroundMusic.stop();
    }
}
