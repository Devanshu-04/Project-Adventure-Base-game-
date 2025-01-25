import greenfoot.*;

/**
 * MyWorld is the main world where the game takes place.
 */
public class MyWorld extends ScrollingWorld {
    private GenericObject ground;
    private Player player;
    private boolean eventOneTriggered = false;
    private boolean levelDone = false;
    //: For score tracking
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {
        // Create a world with window size 800x800, cell size 1, and scrolling width 1000
        super(800, 800, 1, 1000);

        // Create and set the background image matching the scrolling dimensions
        GreenfootImage bg = new GreenfootImage(1000, 1000);
        bg.setColor(Color.CYAN);
        bg.fill();

        // Initialize and add the ground object
        ground = new GenericObject(5000, 250, Color.GREEN);
        addObject(ground, 2000, 740); // Position adjusted to place ground at bottom

        // Initialize and add the player
        player = new Player(50, 100, Color.RED, ground);
        setMainActor(player,250,300);
        player.setLocation(100, 342); // Set initial player position
        setScrollingBackground(bg);
        
        // Randomly generate some coins
        //addCoins(5);
        
        //level1();
        level2();
    }

    /**
     * The act method handles game updates.
     */
    public void act() {
        super.act(); // Important to call to ensure scrolling works
        //moves objects based on velocity, refer to generic object for more information.
        //object1.move();
        //object2.move();

        //collision checker, check generic object for more information.
        //object1.handleCollision(object2);
        //object2.handleCollision(object1);
        //Ground.handleCollision(Player);
        player.handleCollision(ground);
        
        //Ground.handleCollision(object2);

        //make sure objects dont leave the world.
        //checkWorldBounds(object1);
        //checkWorldBounds(object2);
        checkWorldBounds(player);

        //event triggers handler
        checkForEventTrigger();
        checkForLevelFinished(levelDone);

    }

    /**
     * Prevents the player from moving beyond the scrolling boundaries.
     *
     * @param obj The object to check bounds for
     */
    private void checkWorldBounds(GenericObject obj) {
        // Get half the object's width and height for boundary checks
        int halfWidth = obj.getImage().getWidth() / 2;
        int halfHeight = obj.getImage().getHeight() / 2;

        // Check if the object is at the left or right boundary
        if (obj.getX() <= halfWidth || obj.getX() >= getWidth() - halfWidth) {
            obj.setDx(-obj.getDx());  // Reverse x direction
        }

        // Check if the object is at the top or bottom boundary
        if (obj.getY() <= halfHeight || obj.getY() >= getHeight() - halfHeight) {
            obj.setDy(-obj.getDy());  // Reverse y direction
        }
    }
    
    private void addCoins(int numCoins)
    {
        for(int i = 0; i< numCoins; i++)
        {
            int x = Greenfoot.getRandomNumber(5000);
            int y = 400 + Greenfoot.getRandomNumber(200);
            addObject(new Coin(), x, y);
        }
    }

    private void checkForEventTrigger() {
        if (!eventOneTriggered) {
            int playerX = player.getX();
            int playerY = player.getY();

            if (playerX == 100) {
                triggerEvent();
            }
        }
    }
    
    private void checkForLevelFinished(boolean levelDone) {
        if (!levelDone) {
            int playerX = player.getX();
            int playerY = player.getY();

            if (playerX == 5000) {
                System.out.println("Level Done");
                levelDone = true;
                levelReset(levelDone);
            }
            return;
        }
    }

    private boolean levelReset(boolean levelDone){
        System.out.println("Level Reset");
        setMainActor(player,250,300);
        player.setLocation(100, 342);
        levelDone = false;
        //Level2();
        return levelDone;
        //getWorld().removeObject(obstacle1); 
    }

    private void triggerEvent() {
        System.out.println("Event triggered");
        eventOneTriggered = true;
    }
    
    public void level1(){
         GenericObject obstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6, 
         obstacle7, obstacle8, obstacle9, obstacle10, obstacle11, obstacle12, 
         obstacle13, raisedPlatform1, raisedPlatform2, raisedPlatform3,
         raisedPlatform4, raisedPlatform5, raisedPlatform6;
         obstacle1 = new GenericObject(100, 50, Color.YELLOW);
         obstacle2 = new GenericObject(100, 50, Color.RED);
         obstacle3 = new GenericObject(100, 50, Color.BLUE);
         obstacle4 = new GenericObject(100, 50, Color.YELLOW);
         obstacle5 = new GenericObject(100, 150, Color.RED);
         obstacle6 = new GenericObject(100, 250, Color.BLUE);
         obstacle7 = new GenericObject(100, 250, Color.BLUE);
         obstacle8 = new GenericObject(100, 150, Color.RED);
         obstacle9 = new GenericObject(100, 50, Color.YELLOW);
         obstacle10 = new GenericObject(400, 50, Color.YELLOW);
         obstacle11 = new GenericObject(200, 150, Color.RED); 
         obstacle12 = new GenericObject(100, 250, Color.RED);
         obstacle13 = new GenericObject(50, 350, Color.BLUE);
         raisedPlatform1 = new GenericObject(100, 10, Color.YELLOW);
         raisedPlatform2 = new GenericObject(100, 10, Color.RED);
         raisedPlatform3 = new GenericObject(100, 10, Color.BLUE);
         raisedPlatform4 = new GenericObject(100, 10, Color.YELLOW);
         raisedPlatform5 = new GenericObject(100, 10, Color.RED);
         raisedPlatform6 = new GenericObject(100, 10, Color.BLUE);
         addObject(obstacle1, 250, 600);
         addObject(obstacle2, 450, 600);
         addObject(obstacle3, 650, 600);
         addObject(obstacle4, 1050, 600);
         addObject(new Coin(), 1100, 575);
         addObject(obstacle5, 1150, 550);
         addObject(obstacle6, 1250, 500);
         addObject(raisedPlatform1, 1750, 400);
         addObject(raisedPlatform2, 1900, 300);
         addObject(raisedPlatform3, 2050, 200);
         addObject(obstacle7, 2200, 500);
         addObject(obstacle8, 2300, 550);
         addObject(obstacle9, 2400, 600);
         addObject(obstacle10, 3100, 600);
         addObject(obstacle11, 3400, 550);
         addObject(obstacle12, 3550, 500);
         addObject(obstacle13, 3625, 450);
         addObject(raisedPlatform4, 3800, 200);
         addObject(raisedPlatform5, 4000, 100);
         addObject(raisedPlatform6, 4200, 0);
        //Level1 level1 = new Level1(); 
    }
    
    private void level2(){
         GenericObject obstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6, 
         obstacle7, obstacle8, obstacle9, obstacle10, raisedPlatform1, raisedPlatform2, raisedPlatform3,
         raisedPlatform4, raisedPlatform5, raisedPlatform6, raisedPlatform7, raisedPlatform8, 
         raisedPlatform9, raisedPlatform10, raisedPlatform11, raisedPlatform12, 
         raisedPlatform13, raisedPlatform14, raisedPlatform15, WinObject;
         // WinObject win;
         // win = new WinObject(10,10, Color.YELLOW);
         // if(win.IsTouching(Player.class)){
         //  
         // if(LevelTwoDone){
         //    Greenfoot.Stopped(); 
         //   }
         // if(LevelOneDone){
         //       Level2();
         //   }
         //    
         //   
         // }
         WinObject = new GenericObject(10, 10, Color.YELLOW);
         obstacle1 = new GenericObject(300, 500, Color.YELLOW);
         obstacle2 = new GenericObject(100, 100, Color.YELLOW);
         obstacle3 = new GenericObject(100, 150, Color.RED);
         obstacle4 = new GenericObject(100, 50, Color.BLUE);
         obstacle5 = new GenericObject(100, 350, Color.YELLOW);
         obstacle6 = new GenericObject(100, 275, Color.RED);
         obstacle7 = new GenericObject(100, 200, Color.BLUE);
         obstacle8 = new GenericObject(100, 500, Color.YELLOW);
         obstacle9 = new GenericObject(100, 550, Color.GREEN); 
         raisedPlatform1 = new GenericObject(100, 10, Color.YELLOW);
         raisedPlatform2 = new GenericObject(100, 10, Color.RED);
         raisedPlatform3 = new GenericObject(100, 10, Color.BLUE);
         raisedPlatform4 = new GenericObject(100, 10, Color.YELLOW);
         raisedPlatform5 = new GenericObject(75, 10, Color.RED);
         raisedPlatform6 = new GenericObject(75, 10, Color.BLUE);
         raisedPlatform7 = new GenericObject(100, 10, Color.YELLOW);
         raisedPlatform8 = new GenericObject(100, 10, Color.RED);
         raisedPlatform9 = new GenericObject(100, 10, Color.BLUE);
         raisedPlatform10 = new GenericObject(100, 10, Color.YELLOW);
         raisedPlatform11 = new GenericObject(100, 10, Color.RED);
         raisedPlatform12 = new GenericObject(100, 10, Color.BLUE);
         raisedPlatform13 = new GenericObject(100, 10, Color.YELLOW);
         raisedPlatform14 = new GenericObject(100, 10, Color.RED);
         raisedPlatform15 = new GenericObject(100, 10, Color.BLUE);
         addObject(raisedPlatform1, 500, 400);
         addObject(raisedPlatform2, 650, 300);
         addObject(raisedPlatform3, 800, 200);
         addObject(obstacle1, 1150, 375);
         addObject(new Coin(), 1200, 100);
         addObject(raisedPlatform4, 1500, 150);
         addObject(raisedPlatform5, 1650, 100);
         addObject(raisedPlatform6, 1800, 50);
         addObject(raisedPlatform7, 1900, 0);
         addObject(raisedPlatform8, 2000, 0);
         addObject(raisedPlatform9, 2100, 0);
         addObject(new Coin(), 2000, -50);
         addObject(raisedPlatform10, 2350, 200);
         addObject(raisedPlatform11, 2550, 300);
         addObject(raisedPlatform12, 2750, 400);
         addObject(obstacle2, 3250, 575);
         addObject(obstacle3, 3400, 550);
         addObject(obstacle4, 3500, 600);
         addObject(obstacle5, 3600, 450);
         addObject(obstacle6, 3700, 485);
         addObject(obstacle7, 3800, 525);
         addObject(raisedPlatform13, 4000, 200);
         addObject(raisedPlatform14, 4200, 100);
         addObject(raisedPlatform15, 4400, 0);
         addObject(obstacle8, 4600, 375);
         addObject(obstacle9, 4700, 350);
         addObject(WinObject, 4700, 50);
        }
}