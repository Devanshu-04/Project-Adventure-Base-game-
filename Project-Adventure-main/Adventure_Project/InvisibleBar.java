import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InvisibleBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InvisibleBar extends Actor
{
    public InvisibleBar() {
        // Create an invisible image for the bar (setting transparency to 0)
        GreenfootImage image = new GreenfootImage(10,800);  // Vertical bar width is 10 pixels
        image.setTransparency(0);  // Make the bar invisible
        setImage(image);
    }
}