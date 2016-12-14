import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Add a Pistol shell to the world and describes the behaviour of the shell once added
 * 
 * @author Aidan Yeaman
 */
public class PShell  extends GunShell
{
    /**
     * Constructor of pistol shell
     * @param time of the rotation
     * @param direction of the case
     */
    public PShell(int newTime, int newDirection)
    {
        time=newTime;
        i=0;
        direction=newDirection;
        setImage("Pshell.gif");
    }
    /**
     * This method let the case rotate for a short period
     * and removes this case when the counter reaches 2000
     */
    public void act()
    {
        if(i<time)
        {
            i++;
            setRotation(getRotation()+10);
            if(i%2==0)
                move();
        }
        else
        {
            if(i==2000)
            {
                GreenfootImage img = getImage();
                if(img.getTransparency()==0)
                    getWorld().removeObject(this);
                else
                {
                    img.setTransparency(img.getTransparency()-1);
                    setImage(img);
                }
            }
            else
                i++;
        }
    }
    /**
     * Method that makes the shell move to where the rotation is pointing
     */
    private void move()
    {
        double angle=Math.toRadians(direction);
        int x=(int)Math.round(getX()+Math.cos(angle));
        int y=(int)Math.round(getY()+Math.sin(angle));
        setLocation(x,y);  
    }
}
