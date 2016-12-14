import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class for the world that the game plays in.
 * 
 * @author Aidan Yeaman
 * @version 
 */
public class MyWorld extends World
{
    private Player p; // variable space for which a player object can be created through

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
       super(800, 600, 1);
       p = new Player(); // instantiate a player object with object reference
       start(); //start game
    }
    
    public void start()
    {
        reset(); // reset the screen
        setBackground("Field1.jpg"); //set background to the first level image
        setPaintOrder(Player.class,Bullet.class,Pickup.class,Enemy.class,GunShell.class); // paint objects in suitable order
        addObject(p,350,350); //place player object at a point in the level
        if (p.getKill() == 5) //check for player obtaining a number of kills before the first level is complete
        {
            changeLevel(); //changes level
        }
    }
    /**
     * Main menu of the game,
     * it erases all the objects and then creates some buttons
     */
    void mainMenu()
    {
        reset();
        setBackground("mainMenu.jpg");
        if (Greenfoot.isKeyDown("up"))
        {
            start();
        }
    }
   
    
   public void changeLevel()
   {
       
      
           reset(); // reset the screen
           setBackground("Level2GU.png"); // load second level graphic
           setPaintOrder(Player.class,Bullet.class,Pickup.class,Enemy.class,GunShell.class); // paint objects in suitable order
           addObject(p,350,350); // add player object
       
       
   }
   
   
    /**
     * Method that remove all the objects from the world
     */
    private void reset()
    {
        removeObjects(getObjects(null));
    }
   
 
    /**
     * This method is used when the player dies.
     * It just changes the background to a game over screen
     */
    public void gameOver()
    {
        reset();
        setBackground("gameover.png");
        

    }
}
    

