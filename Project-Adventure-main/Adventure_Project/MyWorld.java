import greenfoot.*;

/**
 * MyWorld is the main world where the game takes place.
 */
public class MyWorld extends ScrollingWorld {
    private GenericObject ground;
    private Player player;
    private boolean eventOneTriggered = false; //event trigger
    private boolean levelDone = false; //Level done checker
    private WinObject win; //Object that will tell us end of level
    private short levelCount = 1; //Level counter
    private String name;
    //Objects for level one and level 2
    private GenericObject obstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6, 
         obstacle7, obstacle8, obstacle9, obstacle10, obstacle11, obstacle12, 
         obstacle13, raisedPlatform1, raisedPlatform2, raisedPlatform3,
         raisedPlatform4, raisedPlatform5, raisedPlatform6, raisedPlatform7, raisedPlatform8, 
         raisedPlatform9, raisedPlatform10, raisedPlatform11, raisedPlatform12, 
         raisedPlatform13, raisedPlatform14, raisedPlatform15;
    private Lava lava,lava1,lava2,lava3;
    private Checkpoint checkpoint, checkpoint1, checkpoint2;
    private Popup popup; 
    //: For score tracking
    /**
     * Constructor for objects of class MyWorld.
     */
    
    public MyWorld() {
        // Create a world with window size 800x800, cell size 1, and scrolling width 1000
        super(800, 800, 1, 1000);
        
        GreenfootImage mario = new GreenfootImage("marioStanding.png");
        player = new Player(50, 100, mario,this.lives);
                
        Greenfoot.setWorld(new StartScreen());
        // Create and set the background image matching the scrolling dimensions
        
        // Initialize and add the ground object
        GreenfootImage customImage = new GreenfootImage("ground.png");
        GenericObject customObject = new GenericObject(5000, 250, customImage); // Using image-based constructor with resize
        addObject(customObject, 2000, 740);  // Position adjusted to place ground at bottom

        // Initialize and add the player

        setMainActor(player,250,300);
        player.setLocation(100, 342);
        
       
        GreenfootImage background = new GreenfootImage("sky.png");
        setScrollingBackground(background); // Set the scrolling background// Set initial player position

        
        //Create a new win object, that is made and will be placed dependent on level
        win = new WinObject(10, 10, Color.YELLOW, false);
        
        // Randomly generate some coins
        addCoins(5);
        
        //testing
        //this.killSound();
        //Greenfoot.setWorld(new SpaceInvadersWorld());
        
        level1(win);
        
        // Create and display the popup when the world is initialized
        popup= new Popup("intro.png"); // Use your PNG image file name
        addObject(popup, 225, 400); // Center the popup
    }

    /**
     * The act method handles game updates.
     */
    public void act() {
        super.act(); // Important to call to ensure scrolling works

        checkWorldBounds(player);
        checkForEventTrigger();
        checkForLevelFinished(win, levelDone);
        
        if (player.getLives() <= 0) {
            // Show the game over screen
            Greenfoot.setWorld(new GameOverScreen(this.scoreDisplay.getScore(), name));
            player.killSound();
            this.killSound();
            deleteLevel();
        }

        // Only removes popup if there is one up and spacebar is pressed
        if (popup != null && Greenfoot.isKeyDown("space")) {
            removeObject(popup);
            popup = null; // Clear the reference to avoid duplicate removals
        }
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

    private void checkForLevelFinished(WinObject win, boolean levelDone) {
        if (win.complete) {
            System.out.println("Level Done");
            levelDone = true;
            if (levelCount == 3) {
                player.killSound();
                this.killSound();
                SpaceInvadersWorld space = new SpaceInvadersWorld();
                space.setup(this.scoreDisplay.getScore(),this.lives.getLives(), name); 
                Greenfoot.setWorld(space);
            } else {
                levelReset(win, levelDone);
            }
        }
    }

private void setLevelBackground(int level) {
    // Adjust the background based on the level
    GreenfootImage background = new GreenfootImage("sky.png");
    setScrollingBackground(background);
}

private void levelReset(WinObject win, boolean levelDone){
    System.out.println("Level Reset");
     //delete the ground, to restart position artificially
    //removeObject(player); // delete the player to reset them
    
    
    
    
    // Set the appropriate background for the current level
    //setLevelBackground(levelCount);

    // Initialize and add the player
    //player = new Player(50, 100, Color.RED);
    //setMainActor(player,250,300);
    //player.setLocation(100, 342); // Set initial player position

    

    int relativeCheckpointX = 300 - super.getScrolledX();
    int relativeCheckpointY = 542 - super.getScrolledY();
    player.setLocation(relativeCheckpointX, relativeCheckpointY);
    super.act();
    removeObject(win);
    
    if (levelCount == 1){
        deleteLevel(); // deletes all possible objects for any level
        levelCount++; //increase level counter to 2
        win.SetFalse(); //set the win level to false
        level2(win); //construct next level
    }
    if (levelCount == 2){
        deleteLevel(); //delete all objects again
        levelCount++; //increment level counter
    }
    return;
}


    private void deleteLevel(){
        //If win is true, then delete all objects
        //deletes all objects in any possible level
         if(win.complete){ 
            removeObject(obstacle1); removeObject(obstacle2); removeObject(obstacle3); removeObject(obstacle4); removeObject(obstacle5); removeObject(obstacle13);
            removeObject(obstacle6); removeObject(obstacle7); removeObject(obstacle8); removeObject(obstacle9); removeObject(obstacle10); removeObject(obstacle11); removeObject(obstacle12);
            removeObject(raisedPlatform1); removeObject(raisedPlatform2); removeObject(raisedPlatform3);
            removeObject(raisedPlatform4); removeObject(raisedPlatform5);removeObject(raisedPlatform6); 
            removeObject(raisedPlatform7); removeObject(raisedPlatform8); removeObject(raisedPlatform9);
            removeObject(raisedPlatform10); removeObject(raisedPlatform11); removeObject(raisedPlatform12);
            removeObject(raisedPlatform13); removeObject(raisedPlatform14); removeObject(raisedPlatform15);
            removeObject(lava); removeObject(lava1); removeObject(lava2); removeObject(lava3);
            removeObject(checkpoint); removeObject(checkpoint1); removeObject(checkpoint2); 
            return;
            }
    }
    
    private void triggerEvent() {
        System.out.println("Event triggered");
        eventOneTriggered = true;
    }
    
    
    private void level1(WinObject win){
         GreenfootImage bricks = new GreenfootImage("bricks.png");
        //Gives the obstacles actual color and shapes
         GreenfootImage bricks1 = new GreenfootImage("bricks.png");
         obstacle1 = new GenericObject(100, 50, bricks);
         obstacle2 = new GenericObject(100, 50, bricks1);
         obstacle3 = new GenericObject(100, 50, bricks);
         obstacle4 = new GenericObject(100, 50, bricks);
         obstacle5 = new GenericObject(100, 150, bricks);
         obstacle6 = new GenericObject(100, 250, bricks);
         obstacle7 = new GenericObject(100, 250, bricks);
         obstacle8 = new GenericObject(100, 150, bricks);
         obstacle9 = new GenericObject(100, 50, bricks);
         obstacle10 = new GenericObject(400, 50, bricks);
         obstacle11 = new GenericObject(200, 150, bricks); 
         obstacle12 = new GenericObject(100, 250, bricks);
         obstacle13 = new GenericObject(50, 100, bricks);
         raisedPlatform1 = new GenericObject(100, 50, bricks);
         raisedPlatform2 = new GenericObject(100, 50, bricks);
         raisedPlatform3 = new GenericObject(100, 50, bricks);
         raisedPlatform4 = new GenericObject(100, 50, bricks);
         raisedPlatform5 = new GenericObject(100, 50, bricks);
         raisedPlatform6 = new GenericObject(100, 50, bricks);
         
         lava = new Lava(120, 50, Color.RED);
         addObject(lava, 1350, 620);
         
         checkpoint = new Checkpoint(40, 40, Color.GREEN);
         addObject(checkpoint, 1050, 500);
         //places the obstacles at desired positions;
         addObject(obstacle4, 1050, 600);
         addObject(new Coin(), 1100, 575);
         addObject(obstacle5, 1150, 550);
         addObject(obstacle6, 1250, 500);
         
         checkpoint1 = new Checkpoint(40, 40, Color.GREEN);
         addObject(checkpoint1, 1450, 550);
         
         lava1 = new Lava(400, 50, Color.RED);
         addObject(lava1, 2000, 620);
         
         addObject(raisedPlatform1, 1750, 400);
         addObject(raisedPlatform2, 1900, 300);
         addObject(raisedPlatform3, 2050, 200);
         addObject(obstacle7, 2200, 500);
         addObject(obstacle8, 2300, 550);
         addObject(obstacle9, 2400, 600);
         addObject(obstacle10, 3100, 600);
         
         checkpoint2 = new Checkpoint(40, 40, Color.GREEN);
         addObject(checkpoint2, 3250, 500);
         
         addObject(obstacle11, 3400, 550);
         addObject(obstacle12, 3550, 500);
         addObject(obstacle13, 3625, 450);
         lava2 = new Lava(1000, 50, Color.RED);
         addObject(lava2, 3930, 620);
         addObject(raisedPlatform4, 3800, 200);
         addObject(raisedPlatform5, 4000, 100);
         addObject(raisedPlatform6, 4200, 0);
         addObject(win, 4200, -50); //puts win into correct position
         //addObject(win, 120, 500);
    }
    
    
    private void level2(WinObject win){
         addObject(win, 4700, 50); //puts win into correct position
         //all obstacles are initizialed with different values for a different level
         GreenfootImage bricks = new GreenfootImage("bricks.png");
         obstacle1 = new GenericObject(300, 500, Color.YELLOW);
         obstacle2 = new GenericObject(100, 100, bricks);
         obstacle3 = new GenericObject(100, 150, bricks);
         obstacle4 = new GenericObject(100, 50, bricks);
         obstacle5 = new GenericObject(100, 350, bricks);
         obstacle6 = new GenericObject(100, 275, bricks);
         obstacle7 = new GenericObject(100, 200, bricks);
         obstacle8 = new GenericObject(100, 500, bricks);
         obstacle9 = new GenericObject(100, 550, bricks); 
         raisedPlatform1 = new GenericObject(100, 50, bricks);
         raisedPlatform2 = new GenericObject(100, 50, bricks);
         raisedPlatform3 = new GenericObject(100, 50, bricks);
         raisedPlatform4 = new GenericObject(100, 50, bricks);
         raisedPlatform5 = new GenericObject(75, 50, bricks);
         raisedPlatform6 = new GenericObject(75, 50, bricks);
         raisedPlatform7 = new GenericObject(100, 50, bricks);
         raisedPlatform8 = new GenericObject(100, 50, bricks);
         raisedPlatform9 = new GenericObject(100, 50, bricks);
         raisedPlatform10 = new GenericObject(100, 50, bricks);
         raisedPlatform11 = new GenericObject(100, 50, bricks);
         raisedPlatform12 = new GenericObject(100, 50, bricks);
         raisedPlatform13 = new GenericObject(100, 50, bricks);
         raisedPlatform14 = new GenericObject(100, 50, bricks);
         raisedPlatform15 = new GenericObject(100, 50, bricks);
         //placement of objects
         Checkpoint checkpoint = new Checkpoint(40, 40, Color.GREEN);
         addObject(checkpoint, 300, 550);
         
         addObject(raisedPlatform1, 500, 400);
         
         lava1 = new Lava(550, 50, Color.RED);
         addObject(lava1, 750, 620);
         
         addObject(raisedPlatform2, 650, 300);
         addObject(raisedPlatform3, 800, 200);
         checkpoint1 = new Checkpoint(40, 40, Color.GREEN);
         addObject(checkpoint1, 1200, 100);
         
         addObject(obstacle1, 1150, 375);
         addObject(new Coin(), 1200, 100);
         lava1 = new Lava(1500, 50, Color.RED);
         addObject(lava1, 2000, 620);
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
         
         checkpoint1 = new Checkpoint(40, 40, Color.GREEN);
         addObject(checkpoint1, 3150, 550);
         addObject(obstacle2, 3250, 575);
         addObject(obstacle3, 3400, 550);
         addObject(obstacle4, 3500, 600);
         addObject(obstacle5, 3600, 450);
         addObject(obstacle6, 3700, 585);
         addObject(obstacle7, 3800, 525);
         lava2 = new Lava(2000, 50, Color.RED);
         addObject(lava2, 4300, 620);
         addObject(raisedPlatform13, 4000, 300);
         addObject(raisedPlatform14, 4200, 200);
         addObject(raisedPlatform15, 4400, 100);
         addObject(obstacle8, 4600, 375);
         addObject(obstacle9, 4700, 350);
        }
    public void setName(String n){
        name = n;
    }
}
