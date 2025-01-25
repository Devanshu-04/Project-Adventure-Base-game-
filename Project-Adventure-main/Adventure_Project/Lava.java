import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class Lava extends Actor {
    private int width;  // Width of the lava object
    private int height; // Height of the lava object
    private Color color; // Color of the lava

    // Constructor
    public Lava(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
        createImage();  // Generate the image for the lava object
    }

    // Create the image for the lava object
    private void createImage() {
        GreenfootImage image = new GreenfootImage(width, height);
        GreenfootImage lava = new GreenfootImage("lava.jpg");
        lava.scale(width, height);
        setImage(lava);
    }

    @Override
    public void act() {
        checkPlayerCollision();
    }

    // Check if the player collides with the lava
    private void checkPlayerCollision() {
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
            

            Greenfoot.stop(); // Stop the game or implement custom behavior
        }
    }
}
