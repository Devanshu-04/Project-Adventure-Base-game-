import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class Checkpoint extends Actor {
    // Constructor for Checkpoint
    public Checkpoint(int width, int height, Color color) {
        // Create a GreenfootImage with the specified dimensions and color
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(color);
        image.fillRect(0, 0, width, height);
        setImage(image); // Set the image to the Checkpoint
    }

    @Override
    public void act() {
        // Checkpoints don't need specific behavior
    }
}
