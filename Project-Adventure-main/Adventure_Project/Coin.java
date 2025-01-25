import greenfoot.*;

/**
 * Write a description of class Coin here.
 * 
 * @author Zachary Magner
 * @version 10/20/24
 */
public class Coin extends Actor
{
    public Coin()
    {
        // Optional: Set an image for the coin (e.g., "coin.png" should be in your project images folder)
        setImage("coin.png");
    }

    public void act() {
        // Define behavior for when the coin is touched by the player
        if (isTouching(Player.class)) {  // Assuming you have a Player class
            // Get reference to the world and cast it to your custom world class
            ScrollingWorld world = (ScrollingWorld)getWorld();
            
            GreenfootSound coinSound = new GreenfootSound("coin.mp3");
            coinSound.play();
            // Increase the score by 10
            world.increaseScore(10);

            // Remove the coin after it's collected
            getWorld().removeObject(this);
        }
    }
}