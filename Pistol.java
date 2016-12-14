import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that defines the Weapon abstract methods for the Pistol weapon.
 * 
 * @author Aidan Yeaman
 */
public class Pistol extends Weapon
{
    private int shootSpeed;//Speed between bullets
    private int cartdridgeCapacity;//maximum amount of bullets in the cartdridge
    private int actual;//Actual number of bullets in the cartdridge
    private int total;//Total number of amunitions availables
    private int i;//Variable that helps in the implementation of the speed between bullets.
    /**
     * Constructor for Pistol class
     */
    public Pistol()
    {
        shootSpeed=50;
        cartdridgeCapacity=25;
        actual=25;
        total=100;
        i=0;
        GreenfootImage img=new GreenfootImage(1,1);
        setImage(img);
        currentLabel=new Label("Clip: "+actual);
        totalLabel=new Label("Ammo: ---");
    }
    /**
     * When a pistol is added to the world this method creates labels with information
     * about the weapon
     */
    public void addedToWorld(World world)
    {
        getWorld().addObject(totalLabel,105,140);
        getWorld().addObject(currentLabel,100,165);
    }
    /**
     * This method updates a variable for a delayed shot
     */
    public void act()
    {
        if(i!=0)
        {
            if(i==shootSpeed)
                i=0;
            else
                i++;
        }
    }
    /**
     * Method that adds a bullet to the world when the mouse is clicked
     * @param pX the x coordinate of the weapon
     * @param pY the y coordinate of the weapon
     * @param rT the rotation of the weapon
     */
    public void shoot(int pX, int pY, int rT)
    {
        if(i==0&&actual>0)
        {
            getWorld().addObject(new Bullet(rT),pX,pY);
            getWorld().addObject(new PShell(Greenfoot.getRandomNumber(100),rT-90),pX,pY);
            Greenfoot.playSound("gunfire.wav");
            i++;
            actual--;
            updateLabels();
        }
        else
            if(actual==0&&i==0)
            {
                i++;
                Greenfoot.playSound("clipsound.wav");
            }
    }
    /**
     * This method reloads the actual weapon
     */
    public void reload()//Reloads actual weapon
    {
        if(actual<cartdridgeCapacity)//If the weapon can reload
        {
            actual=cartdridgeCapacity;
            updateLabels();
            Greenfoot.playSound("reloadersound.wav");
        }
    }
     /**
     * This method updates the information of the weapon
     */
    public void updateLabels()
    {
        currentLabel.setText("Clip: "+actual);
        totalLabel.setText("Ammo: ---");
    }
     /**
     * This method set the labels invisible when another weapon is in use
     */
    public void invisible()
    {
        currentLabel.setInvisible();
        totalLabel.setInvisible();
    }
}
