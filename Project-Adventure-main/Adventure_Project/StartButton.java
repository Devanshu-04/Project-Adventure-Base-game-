import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor
{
    public StartButton() {
        
        GreenfootImage buttonImage = new GreenfootImage("startButton.png");
        // Set the start button image 
        buttonImage.scale(80, 80);
        setImage(buttonImage);
    }

    public void act() {
        // If the start button is clicked, transition to the game world
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new NamePopup());  // Change to the game world when clicked
        }
    }
}