import greenfoot.*;

public class Laser extends Actor {
    private int speed = 5;
    private ScoreDisplay score;
    public Laser(ScoreDisplay s){
        score = s;
        setImage("laser.png");

        // Resize the laser to fit the actor's size
        GreenfootImage img = getImage();
        img.scale(10, 30); 
    }
    public void act() {
        setLocation(getX(), getY() - speed);
        if (getY() <= 0) {
            getWorld().removeObject(this);
        } else {
            checkCollision();
        }
    }

    private void checkCollision() {
        Actor enemy = getOneIntersectingObject(EnemyShip.class);
        if (enemy != null) {
            GreenfootSound jumpSound = new GreenfootSound("explosion.mp3");
            jumpSound.setVolume(40);
            jumpSound.play();
            score.setScore(score.getScore() + 10);
            getWorld().removeObject(enemy);
            getWorld().removeObject(this);
        }
    }
}