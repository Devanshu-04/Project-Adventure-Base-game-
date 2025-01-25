import greenfoot.*;

/**
 * 
 * 
 * @author Nathan Babineaux
 * @version 11/23/24
 */
public class WinObject extends GenericObject
{
    public boolean complete;
    public WinObject(int width, int height, Color color, boolean complete)
    {
        super(width, height, color); 
        this.complete = complete;
    }

    public void act() {
        // Define behavior for when the coin is touched by the player
        if (isTouching(Player.class)) {  // Assuming you have a Player class
            // Get reference to the world and cast it to your custom world class
            ScrollingWorld world = (ScrollingWorld)getWorld();
            
            //Mark the level as complete
            complete = true;
        }
    }
    public void SetFalse(){
        complete = false;

    }

}
