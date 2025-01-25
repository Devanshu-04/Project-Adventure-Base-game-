import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitButton extends Actor
{
    public ExitButton() {
        GreenfootImage buttonImage = new GreenfootImage("exitButton.png");
         
        // Set the exit button image
        buttonImage.scale(75, 75); 
        setImage(buttonImage);
    }

    public void act() {
        // If the exit button is clicked, close the game
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.stop();  // Stop the game when exit is clicked
        }
    }
}