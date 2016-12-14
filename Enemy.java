import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Class that creates a zombie enemy in the game and controls all its behaviour.
 * 
 * @author Aidan Yeaman
 */
public class Enemy extends Actor

{
    private double speed;//Speed of the zombie
    private int canHurt, hp, type, delayHurt;
    private boolean isAlive;
    /**
     * Constructor that creates 3 diferent enemies depending
     * of a random number
     */
    public Enemy()
    {
        delayHurt=0;
        type=Greenfoot.getRandomNumber(3);
        speed=2;
        setImage("zombie-1.gif");
        hp=3;
        canHurt=0;
        isAlive=true;
    }


    /**
     * In this method the zombie walks only if the Player is present,
     * if the zombie is touching the player the zombie will attack the player and
     * cause the players health to decrease
     */
    public void act() 
    {
        if(isAlive)
        {
            if(!canSee(Player.class)&&delayHurt==0)
            {  
                move();
            }
            else
            {
                if(delayHurt==20)
                    delayHurt=0;
                else
                {
                    delayHurt++;
                    if(canSee(Player.class))
                    {
                         
                        if(canHurt==30)
                        {
                            hurtPlayer();
                            Greenfoot.playSound("zombiegrowl.wav");
                            canHurt=0;
                        }
                        else
                            canHurt++;
                    }
                    
                }
            }
        }
        else
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
    }
     /**
      * Move forward in the direction of where the player is situated in the world
      */
    private void move()
    {
        List p = getObjectsInRange(1000,Player.class);
        int Distx, Disty;
        double angle;

        Player P = (Player)p.get(0);
        
        if(p.size()>0)
       {
           //The next part of the code gives the zombie the rotation to where the player is located
            Distx=getX()-P.getX();
            Disty=getY()-P.getY();
            angle=Math.toDegrees(Math.atan2(Disty,Distx))+180;
            setRotation((int)angle);
            //The next part of the code make the zombie move forward depending of his speed and direction
            angle = Math.toRadians( getRotation() );
            int x = (int) Math.round(getX() + Math.cos(angle) * speed);
            int y = (int) Math.round(getY() + Math.sin(angle) * speed);
            setLocation(x, y);
       }

    }
    
    /**
     * @return true if the Zombie can see in front of him the Player class or return false
     * @param class the class that the zombie is looking for
     */
    private boolean canSee(Class cls)
    {
        Actor actor = getOneObjectAtOffset(0, 0, cls);
        return actor != null;        
    }
    
    /**
     * If the zombie is touching the Player it will attack
     */
    private void hurtPlayer()
    {
        Player p;
        p=(Player) getOneIntersectingObject( Player.class );
        p.hurt();
    }
   
   
    /**
     * This method is called from the bullet class when a bullet hit a zombie.
     * the method reduces 1 point of hp to the zombie health; if the zombie is too damaged
     * it will die, removing it from the world
     */
    public void hurt()
    {
        delayHurt++;
        hp--;
        Player p=(Player)getWorld().getObjects(Player.class).get(0);
        
        if(hp==0)
        {
            p.setKill();
            p.getKill();
            addItem();
            getWorld().removeObject(this);
        }

    }
    
   
    /**
     * This method is called when the zombie dies, it will generate sometimes
     * a diferent kind of item based on random numbers.
     */
    private void addItem()
    {
        if(Greenfoot.getRandomNumber(10)==0)//10% chance of dropping an item when dies
        {
            switch(Greenfoot.getRandomNumber(4))
            {
                case 0:getWorld().addObject(new Health(),getX(),getY());
                    break;
                case 1:getWorld().addObject(new MGAmmo(),getX(),getY());
                    break;
                
            }
        }
    }
    /**
     * This method is called from the bullet class; because sometimes 
     * the bullet can intersect dead bodies
     * and this method tells the bullet if the zombie is alive
     * @return true if the zombie is alive and false if it is dead
     */
    public boolean lifeState()
    {
        return isAlive;
    }
}