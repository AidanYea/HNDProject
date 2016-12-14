import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Class that describes a bullet
 * 
 * @author Aidan Yeaman
 */
public class Bullet  extends Actor
{
    private final int shootSpeed=8;
    /**
     * Constructor
     * @param rtt the rotation of the new bullet
     */
    public Bullet(int rtt)
    {
        setRotation(rtt);
        setImage("Bullet.gif");
    }
    /**
     * This method makes the bullet advance to where the rotation is pointing, also condition is set up to check if bullet leaves the window
     * then removes bullet if true.
     */
    public void act() 
    {
        double angle=Math.toRadians(getRotation());
        int x=(int)Math.round(getX()+Math.cos(angle)*shootSpeed);
        int y=(int)Math.round(getY()+Math.sin(angle)*shootSpeed);
        setLocation(x, y);
        if(touchesEnemy()||leavesWorld())
            getWorld().removeObject(this);
    }
    /**
     * Method that checks if the bullet has touched a zombie
     * if that happens the bullet will removed from the world
     * @return true if the bullet touched a zombie and false if it didn't
     */
    private boolean touchesEnemy()
    {
        List<Object> l;
        l=getObjectsAtOffset(0,0,Enemy.class);
        boolean hit=false;
        int i=0;
        while(!hit&&i<l.size())
        {
            Enemy e=(Enemy)l.get(i);
            if(e!=null)
            {
                if(e.lifeState())
                {
                    hit=true;
                    e.hurt();
                }
                else
                    i++;
            }
        }
        return hit;
    }
    /**
     * This method checks if the bullet is on the edge of the world
     * @return true if the bullet is in the edge of the world and false if not
     */
    private boolean leavesWorld()
    {
        if(getX()==0||getX()==getWorld().getWidth()-1)
        {
            return true;
        }
        else
            if(getY()==0||getY()==getWorld().getHeight()-1)
            {
                return true;
            }
            else
                return false;
    }
}