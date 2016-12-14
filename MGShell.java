import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Add a machine gun shell to the world and describes the behaviour of the shell once added
 * 
 * @author Aidan Yeaman
 */
public class MGShell  extends GunShell
{
    /**
     * Constructor of machine gun shell
     * @param time of the rotation
     * @param direction of the case
     */
    public MGShell(int newTime, int newDirection)
    {
        time=newTime;
        i=0;
        direction = newDirection;
        setImage("Pshell.gif");
    }
    /**
     * This method let the case rotate for a short period
     * and removes this case when the counter reaches 1500
     */
    public void act()
    {
        if(i<time)
        {
            i++;
            setRotation(getRotation()+10);
            if(i%2 == 0)
                move();
        }
        else
        {
            if(i==1500)
            {
                GreenfootImage img = getImage();
                if(img.getTransparency() == 0)
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
