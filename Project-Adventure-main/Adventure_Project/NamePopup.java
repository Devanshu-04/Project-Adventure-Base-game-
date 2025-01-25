import greenfoot.*;


public class NamePopup extends World {
    private GreenfootImage promptImage;
    private TextField nameInput;
    private Button submitButton;
    
    public NamePopup() {
        super(400, 200, 1); // Create a world of appropriate size for the popup
        
        // Set background color to white
        GreenfootImage bg = getBackground();
        bg.setColor(Color.WHITE);
        bg.fill();

        // Add the prompt for name input
        promptImage = new GreenfootImage("Enter your 4-letter name:", 24, Color.BLACK, Color.WHITE);
        getBackground().drawImage(promptImage, 10, 10);
        
        // Create a text field for name input
        nameInput = new TextField("Name");
        addObject(nameInput, getWidth() / 2, getHeight() / 2 - 30);

        // Add the submit button
        submitButton = new Button("Submit");
        addObject(submitButton, getWidth() / 2, getHeight() / 2 + 30);
    }

    public void act() {
        // If the submit button is clicked and the name is valid, close the popup
        if (Greenfoot.mousePressed(submitButton)) {
            String name = nameInput.getText();
            if (name.length() >= 4) {
                // Here, you can handle the name, such as storing it or passing it to another world
                System.out.println("Name entered: " + name); // Just a debug print for now
                MyWorld newWorld = new MyWorld();
                newWorld.setName(name);
                Greenfoot.setWorld(newWorld); // Go back to the start screen (or any world you want)
            } 
        }
    }
}
