import greenfoot.*;

public class WorldWithMusic extends World {
    private GreenfootSound backgroundMusic;

    public WorldWithMusic() {
        // Create the world with dimensions 600x400
        super(600, 400, 1); 
        
        // Load the music file
        backgroundMusic = new GreenfootSound("backgroundMusic.mp3");
        
        // Play the music in a loop
        backgroundMusic.playLoop();  // playLoop() makes it loop
    }
}