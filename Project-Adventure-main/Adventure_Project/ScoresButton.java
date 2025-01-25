import greenfoot.*;

class ScoresButton extends Actor {
    public ScoresButton() {
        GreenfootImage image = new GreenfootImage("startButton.png");
        image.scale(80, 80); 
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new ScoresScreen());
        }
    }
}