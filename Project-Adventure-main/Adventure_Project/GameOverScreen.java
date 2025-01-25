import greenfoot.*;
import java.io.*;
import java.util.*;

public class GameOverScreen extends World {
    private int score;
    private String name;

    public GameOverScreen(int score, String name) {
        super(800, 600, 1); // Create the world with specified width and height

        this.score = score; // Initialize the score
        this.name = name; // Initialize the player's name

        // Set the background color to black
        GreenfootImage bgImage = getBackground();
        bgImage.setColor(Color.BLACK);
        bgImage.fill(); // Fill the background with black

        // Create and add the "Game Over" message as an actor
        GreenfootImage gameOverImage = new GreenfootImage(600, 100);
        gameOverImage.setColor(Color.RED);
        gameOverImage.setFont(new Font("Arial", true, false, 48));
        gameOverImage.drawString("GAME OVER", 150, 50);
        Actor gameOverText = new Actor() {
            {
                setImage(gameOverImage);
            }
        };

        addObject(gameOverText, getWidth() / 2, getHeight() / 4); // Place "Game Over" text

        // Display the current score and name
        displayCurrentScoreAndName();

        // Display the current score and ranking
        displayRanking();

        // Create and add a button to return to the home screen
        MainMenuButton backButton = new MainMenuButton();
        addObject(backButton, getWidth() / 2, getHeight() - 100);
    }

    private void displayCurrentScoreAndName() {
        // Create an image to display the player's name and score
        GreenfootImage currentScoreImage = new GreenfootImage(600, 50);
        currentScoreImage.setColor(Color.WHITE);
        currentScoreImage.setFont(new Font("Arial", true, false, 24));
        currentScoreImage.drawString("Player: " + name + " | Score: " + score, 50, 30);

        Actor currentScoreText = new Actor() {
            {
                setImage(currentScoreImage);
            }
        };

        // Add the current score display below the "Game Over" message
        addObject(currentScoreText, getWidth() / 2, getHeight() / 3);
    }

    private void displayRanking() {
        try {
            // Read scores from the file
            List<String> scoresList = readScores();

            // Add the current score to the list and sort it
            scoresList.add(name + " " + score);
            scoresList = sortScores(scoresList);

            // Check if the current score is in the top 5
            boolean isTop5 = false;
            if (scoresList.indexOf(name + " " + score) < 5) {
                isTop5 = true;
                writeScores(scoresList); // Write back only top 5 scores
            }

            // Display the ranking
            GreenfootImage rankImage = new GreenfootImage(600, 200);
            rankImage.setColor(Color.WHITE);
            rankImage.setFont(new Font("Arial", true, false, 24));
            StringBuilder rankingText = new StringBuilder("Top Scores:\n");
            for (int i = 0; i < Math.min(5, scoresList.size()); i++) {
                rankingText.append(scoresList.get(i)).append("\n");
            }
            if (isTop5) {
                rankingText.append("\nYour score is in the top 5!");
            } else {
                rankingText.append("\nYour score is not in the top 5.");
            }
            rankImage.drawString(rankingText.toString(), 50, 50);

            Actor rankText = new Actor() {
                {
                    setImage(rankImage);
                }
            };
            addObject(rankText, getWidth() / 2, getHeight() / 2);

        } catch (IOException e) {
            System.out.println("Error handling scores file: " + e.getMessage());
        }
    }

    private List<String> readScores() throws IOException {
        List<String> scores = new ArrayList<>();
        File scoresFile = new File("scores.txt");

        if (scoresFile.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(scoresFile));
            String line;
            while ((line = br.readLine()) != null) {
                scores.add(line.trim());
            }
            br.close();
        }

        return scores;
    }

    private void writeScores(List<String> scores) throws IOException {
        // Only write the top 5 scores back to the file
        BufferedWriter bw = new BufferedWriter(new FileWriter("scores.txt"));
        for (int i = 0; i < Math.min(5, scores.size()); i++) {
            bw.write(scores.get(i));
            bw.newLine();
        }
        bw.close();
    }

private List<String> sortScores(List<String> scores) {
    List<String> validScores = new ArrayList<>();

    for (String scoreEntry : scores) {
        try {
            // Split the entry into name and score
            String[] parts = scoreEntry.split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid score format: " + scoreEntry);
            }

            // Ensure the score is a valid integer
            Integer.parseInt(parts[1]);
            validScores.add(scoreEntry); // Add only valid entries
        } catch (NumberFormatException e) {
            System.err.println("Invalid score value in entry: " + scoreEntry);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    // Sort valid scores in descending order based on the numeric value
    validScores.sort((s1, s2) -> {
        int score1 = Integer.parseInt(s1.split(" ")[1]);
        int score2 = Integer.parseInt(s2.split(" ")[1]);
        return score2 - score1; // Sort in descending order
    });

    return validScores;
}
}
