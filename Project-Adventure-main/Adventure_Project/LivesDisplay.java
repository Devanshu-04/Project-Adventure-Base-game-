import greenfoot.*;

public class LivesDisplay extends Actor {
    private GreenfootImage displayImage; // Image for displaying lives
    private ScrollingWorld world; // Reference to the world
    private int lives; 

    public LivesDisplay(int l) {
        lives = l;
        displayImage = new GreenfootImage(200, 50); // Create an image for the display
        updateDisplay(); // Initial render of lives
    }

    // Update the display with the player's current lives
    public void updateDisplay() {
        displayImage.clear(); // Clear the image for updating
        displayImage.setColor(Color.WHITE); // Set text color
        displayImage.setFont(new Font("Arial", true, false, 24)); // Set font

        // Display the player's current number of lives by checking Player's lives
        displayImage.drawString("Lives: " + lives, 10, 30);
        setImage(displayImage); // Update the display image
    }
    public void deacreaseLive(){
        lives--;
        updateDisplay();
    }
    public int getLives(){
        return lives;
    }
    public void setLives(int l){
        lives = l;
        updateDisplay();
    }
}
