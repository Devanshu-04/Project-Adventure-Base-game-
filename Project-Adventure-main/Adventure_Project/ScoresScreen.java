import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ScoresScreen - Displays player names and scores on a black background.
 */
public class ScoresScreen extends World {
    private static List<PlayerScore> scores = new ArrayList<>();

    public ScoresScreen() {
        super(800, 800, 1);

        // Load scores from the file
        loadScoresFromFile();

        // Set a black background
        GreenfootImage background = new GreenfootImage(800, 800);
        background.setColor(Color.BLACK);
        background.fill();
        setBackground(background);

        // Show title at the top
        GreenfootImage title = new GreenfootImage("SCORES", 48, Color.WHITE, null);
        getBackground().drawImage(title, (getWidth() - title.getWidth()) / 2, 50);

        // Display the scores
        displayScores();

        // Add a button to return to the main menu
        addObject(new MainMenuButton(), getWidth() / 2, getHeight() - 100);
    }

    // Method to display the scores
    private void displayScores() {
        int yOffset = 150; // Starting vertical position for scores
        int xName = getWidth() / 3; // Name column position
        int xScore = getWidth() * 2 / 3; // Score column position

        // Loop through the scores and display them
        for (int i = 0; i < scores.size(); i++) {
            PlayerScore playerScore = scores.get(i);

            // Display name
            GreenfootImage nameText = new GreenfootImage(playerScore.getName(), 36, Color.WHITE, null);
            getBackground().drawImage(nameText, xName - nameText.getWidth() / 2, yOffset);

            // Display score
            GreenfootImage scoreText = new GreenfootImage(String.valueOf(playerScore.getScore()), 36, Color.WHITE, null);
            getBackground().drawImage(scoreText, xScore - scoreText.getWidth() / 2, yOffset);

            yOffset += 50; // Move to the next line
        }

        if (scores.isEmpty()) {
            GreenfootImage noScoresText = new GreenfootImage("No scores available.", 36, Color.WHITE, null);
            getBackground().drawImage(noScoresText, (getWidth() - noScoresText.getWidth()) / 2, getHeight() / 2);
        }
    }

    // Method to load scores from the file
    private void loadScoresFromFile() {
        scores.clear(); // Clear any existing scores
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); // Split by whitespace
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    addScore(name, score);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading scores file: " + e.getMessage());
        }
    }

    // Method to add a new score
    private void addScore(String name, int score) {
        if (name.length() > 4) {
            name = name.substring(0, 4); // Ensure names are 4 letters
        }
        scores.add(new PlayerScore(name, score));
    }
}

/**
 * PlayerScore - Helper class to store player names and scores.
 */
class PlayerScore {
    private String name;
    private int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

/**
 * MainMenuButton - Returns to the main menu.
 */
class MainMenuButton extends Actor {
    public MainMenuButton() {
        GreenfootImage image = new GreenfootImage("Main Menu", 24, Color.WHITE, Color.BLACK);
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new StartScreen()); // Go back to the StartScreen
        }
    }
}
