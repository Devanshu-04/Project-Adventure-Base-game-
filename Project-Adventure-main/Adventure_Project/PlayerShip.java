import greenfoot.*;

public class PlayerShip extends Actor {
    private int speed = 5;
    private int laserCooldown = 0; // Cooldown timer
    private ScoreDisplay score;
    public PlayerShip(ScoreDisplay s) {
        // Set the image of the player ship to 'ship.png'
        setImage("ship.png");
        score = s;
        System.out.println(score);
        // Resize the image to fit the actor's size
        GreenfootImage img = getImage();
        img.scale(50, 50);
    }

    public void act() {
        handleInput();
        shoot();
        if (laserCooldown > 0) {
            laserCooldown--; // Decrease cooldown timer
        }
    }

    private void handleInput() {
        if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && getX() > 0) {
            move(-speed);
        }
        if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && getX() < getWorld().getWidth() - 1) {
            move(speed);
        }
    }

    private void shoot() {
        // Shoot a laser when space is pressed, and the cooldown is zero
        if (Greenfoot.isKeyDown("space") && laserCooldown == 0) {
            getWorld().addObject(new Laser(score), getX(), getY() - 30);
            laserCooldown = 20; // Set cooldown to prevent rapid firing
            GreenfootSound jumpSound = new GreenfootSound("missleLaunch.mp3");
            jumpSound.setVolume(40);
            jumpSound.play();
        }
    }
}
