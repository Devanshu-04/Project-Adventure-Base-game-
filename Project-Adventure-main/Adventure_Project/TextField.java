import greenfoot.*;


public class TextField extends Actor {
    private String text;
    private GreenfootImage image;

    public TextField(String initialText) {
        text = initialText;
        image = new GreenfootImage(200, 30);
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, 199, 29);
        image.drawString(text, 5, 20);
        setImage(image);
    }

    public String getText() {
        return text;
    }

    public void act() {
        if (Greenfoot.mousePressed(this)) {
            // Allow text to be edited (you can extend this with a keyboard for full functionality)
            String newText = Greenfoot.ask("Enter your name:");
            if (newText != null && newText.length() <= 15) {
                text = newText;
                image.clear();
                image.setColor(Color.BLACK);
                image.fillRect(0, 0, 200, 30); // Fill the text field background
                image.setColor(Color.WHITE);
                image.drawString(text, 5, 20); // Draw the new text
                setImage(image);
            }
        }
    }
}
