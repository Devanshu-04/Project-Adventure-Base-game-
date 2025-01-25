import greenfoot.*;

public class EnemyShip extends Actor {
    private int speed = 2;
    private LivesDisplay lives;
    public EnemyShip(LivesDisplay l) {
        // Set the image of the enemy to 'asteroid.png'
        setImage("asteroid.png");
        lives = l;
        // Resize the asteroid to a random size between 30 and 60 pixels wide and 30 to 60 pixels tall
        GreenfootImage img = getImage();
        int width = Greenfoot.getRandomNumber(31) + 30; // Random width between 30 and 60
        int height = Greenfoot.getRandomNumber(31) + 30; // Random height between 30 and 60
        img.scale(width, height); // Scale the image to random dimensions
    }

    public void act() {
        setLocation(getX(), getY() + speed); // Move the asteroid downwards
        if (getY() >= getWorld().getHeight() -1) {
            getWorld().removeObject(this); // Remove asteroid when it reaches the bottom
            lives.deacreaseLive();
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed; // Set the speed of the asteroid
    }
}
