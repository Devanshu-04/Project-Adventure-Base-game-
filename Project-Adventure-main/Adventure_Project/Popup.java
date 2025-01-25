import greenfoot.*;

public class Popup extends Actor {
    public Popup(String imageName) {
        GreenfootImage image = new GreenfootImage(imageName); // Load the PNG image
        image.scale(450, 550);
        setImage(image); // Set the resized image as the actor's image
    }
}
