import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * StartScreen - Displays the start screen with buttons and corresponding labels.
 */
public class StartScreen extends World {

    private int previousScore;
    private GreenfootSound backgroundMusic;
    public StartScreen() {
        this(0);
    }

    public StartScreen(int previousScore) {
        // Create a new world with a size of 800x800
        super(800, 800, 1);
        this.previousScore = previousScore;

        // Set the background image
        GreenfootImage background = new GreenfootImage("background.png");
        background.scale(getWidth(), getHeight());  // Scale the image to fit 800x800
        setBackground(background);

        // Display the game title
        showText("Adventure Time", getWidth() / 2, getHeight() / 2 - 200);

        // Add buttons and their labels
        addButtonsWithLabels();
    }

    // Method to add buttons and their corresponding labels
    private void addButtonsWithLabels() {
        int buttonX = getWidth() / 2; // Center the buttons horizontally
        int labelX = getWidth() / 2; // Center the labels horizontally
        int yOffset = getHeight() / 2 - 100; // Starting vertical position for buttons

        // Add Start button
        StartButton startButton = new StartButton();
        addObject(startButton, buttonX, yOffset);
        showText("Start the game", labelX, yOffset + 40); // Add text below the button

        // Add Scores button
        ScoresButton scoresButton = new ScoresButton();
        addObject(scoresButton, buttonX, yOffset + 100);
        showText("View high scores", labelX, yOffset + 140); // Add text below the button

        // Add Exit button
        ExitButton exitButton = new ExitButton();
        addObject(exitButton, buttonX, yOffset + 200);
        showText("Exit the game", labelX, yOffset + 240); // Add text below the button
    }
}
