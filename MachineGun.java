import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that defines the Weapon abstract methods for the machine gun weapon.
 * 
 * @author Aidan Yeaman
 */
public class MachineGun extends Weapon
{
    /**
     * Constructor for Machine Gun class
     */
    public MachineGun()
    {
        shootSpeed=10;
        cartdridgeCapacity=150;
        actual=150;
        total=1000;
        i=0;
        GreenfootImage img=new GreenfootImage(1,1);
        setImage(img);
        currentLabel=new Label("Clip: "+actual);
        totalLabel=new Label("Ammo: "+total);
    }
    /**
     * When a minigun is added to the world, it creates two labels with info
     * about the state of the weapon
     */
    public void addedToWorld(World world)
    {
        getWorld().addObject(totalLabel,105,140);
        getWorld().addObject(currentLabel,100,165);
        invisible();
    }
    /**
     * This method updates a variable for a delayed shoot
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
     * This method add some bullets to the world when the space bar is pressed
     * @param pX the x coordinate of the weapon
     * @param pY the y coordinate of the weapon
     * @param rT the rotation of the weapon
     */
    public void shoot(int X, int Y, int r)
    {
        if(i==0&&actual>0)
        {
            getWorld().addObject(new Bullet(r),X,Y);
            getWorld().addObject(new MGShell(Greenfoot.getRandomNumber(100),r-90),X,Y);
            Greenfoot.playSound("machinegunfire.wav");
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
     * This method is called when the "r" key is pressed
     * and it reload the weapon ammo
     */
    public void reload()//Reloads actual weapon
    {
        if(actual<cartdridgeCapacity)//If the weapon can reload
        {
            if ((total-(cartdridgeCapacity-actual))>0)//If a complete reload can be done
            {
                total-=(cartdridgeCapacity-actual);
                actual=cartdridgeCapacity;
            }
            else//If there is not enough ammunition for a complete reload
            {
                actual+=total;
                total=0;
            }
             Greenfoot.playSound("reloadersound.wav");
        }
        updateLabels();
    }
    /**
     * This method updates the information of the weapon
     */
    public void updateLabels()
    {
        currentLabel.setText("Clip: "+actual);
        totalLabel.setText("Ammo: "+total);
    }
    /**
     * This method set the labels invisible when another weapon is in use
     */
    public void invisible()
    {
        currentLabel.setInvisible();
        totalLabel.setInvisible();
    }
    /**
     * This method is called when the player eats a shotgun ammo item
     * and it add 7 bullets to the total ammo.
     */
    public void addItem()
    {
        total+=cartdridgeCapacity;
    }
}

