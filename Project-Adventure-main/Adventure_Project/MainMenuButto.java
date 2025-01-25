import greenfoot.*;

public class MainMenuButto extends Actor {
    private String label;

    public MainMenuButto(String label) {
        this.label = label;
        updateImage();
    }

    public void updateImage() {
        GreenfootImage buttonImage = new GreenfootImage(label.length() * 10 + 20, 40); // Dynamically adjust button size
        buttonImage.setColor(Color.CYAN); // Button color
        buttonImage.fill(); // Fill the button background
        buttonImage.setColor(Color.BLACK); // Set text color
        buttonImage.setFont(new Font("Arial", true, false, 20)); // Font for the text
        buttonImage.drawString(label, 10, 25); // Draw the button label
        setImage(buttonImage); // Set the button image
    }

    public void act() {
        // Check if the mouse is clicked on the button
        if (Greenfoot.mousePressed(this)) {
            Greenfoot.setWorld(new StartScreen()); // Switch to the home screen (make sure StartScreen is implemented)
        }
    }
}
