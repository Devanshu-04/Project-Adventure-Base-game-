import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreDisplay here.
 * 
 * @author Zachary Magner
 * @version 10/20/24
 */
public class ScoreDisplay extends Actor {
    private int score;  // Initial score

    public ScoreDisplay(int Score) {
        score = Score;
        updateImage();
    }

    // Method to update the score display
    public void setScore(int newScore) {
        score = newScore;
        updateImage();
    }
    
     // Method to get the current score
    public int getScore() {
        return score;
    }

    // Method to create a transparent image with the updated score
    private void updateImage() {
        Color transparentColor = new Color(0, 0, 0, 0);
        GreenfootImage scoreImage = new GreenfootImage("Score: " + score, 24, Color.WHITE, transparentColor);
        setImage(scoreImage);  // Set this image as the actor's image
    }
}
