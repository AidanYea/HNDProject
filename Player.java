import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Class that allows the player to move around the map and shoot weapons
 * 
 * @author Aidan Yeaman
 */
public class Player extends Actor
{
    private int x, y, time, i, actual, pressed, hp, k;
    private final int speed=2;
    private Label kills, health;
    private Pistol p;
    private MachineGun m;
    private MyWorld world;
    
    /**
     * Constructor 
     * @param none
     */
    public Player()
    {
        
    }
    /**
     * Method that initialize some variables of the player class such as weapons
     */
    public void addedToWorld(World world)
    {
        actual=0; //variable used to determine which weapon is in use
        i=0;
        time=300;
        hp=100;
        p=new Pistol();
        m=new MachineGun();
        getWorld().addObject(p,1,1);
        getWorld().addObject(m,3,3);
        kills=new Label("Kills: "+k);
        health=new Label("Health: "+hp);
        getWorld().addObject(health,125,80);
        getWorld().addObject(kills,95,110);
        world=(MyWorld)world;
        setImage("PlayerPistol.gif");
    }
    /**
     * This method let the player move, change of direction, take items and shoot.
     */
    public void act() 
    {
        move();
        direction();
        addZombie();
        if(canSee(Pickup.class))
            eat();
        kills.setText("Kills: "+k);
        health.setText("Health: "+hp);
        getKill();
    }
    /**
     * Class that moves the player to the pressed direction
     * 
     */
    private void move()
    {
        if(Greenfoot.isKeyDown("w"))
        {
            x=getX();
            y=getY();
            setLocation(x,y-speed);
        }
        if(Greenfoot.isKeyDown("s"))
        {
            x=getX();
            y=getY();
            setLocation(x,y+speed);        
        }
        if(Greenfoot.isKeyDown("a"))
        {
            x=getX();
            y=getY();
            setLocation(x-speed,y);            
        }
        if(Greenfoot.isKeyDown("d"))
        {
            x=getX();
            y=getY();
            setLocation(x+speed,y);
        }
        if(Greenfoot.isKeyDown("q"))
        {
            if(pressed==10)
            {
                pressed=0;
                changeWeapon();
            }
            else
                pressed++;            
        }
        if(Greenfoot.isKeyDown("r"))
        {
            reload();
        }
        if(Greenfoot.mouseClicked(null))
        {
            shoot();
        }
        
        
    }
     /**
     * This method allows the player to face whatever direction
     * the mouse is pointing towards         
     */
    private void direction()
    {
        if(Greenfoot.mouseMoved(null)) 
        {
            x=getX();
            y=getY();
            MouseInfo mouse=Greenfoot.getMouseInfo();
            int Distx, Disty;
            double angle;
            Distx=this.x-mouse.getX();
            Disty=this.y-mouse.getY();
            angle=Math.toDegrees(Math.atan2(Disty,Distx))+180;
            setRotation((int)angle);
        }
    }
    /**
     * This methods makes zombies spawn randomly in the bottom half of
     * the world
     */
    private void addZombie()
    {
        if(i==time)
        {
            i=0;
            if(time>40) // spawn time for zombies
                time-=5; // allows more zombies to spawn on screen
            switch(Greenfoot.getRandomNumber(3))
            {
                case 0:getWorld().addObject(new Enemy(),Greenfoot.getRandomNumber(getWorld().getWidth()),getWorld().getHeight()-1);
                    break;
                case 1:getWorld().addObject(new Enemy(),1,getWorld().getHeight()/2+Greenfoot.getRandomNumber(getWorld().getHeight()/2));
                    break;
                case 2:getWorld().addObject(new Enemy(),getWorld().getWidth()-1,getWorld().getHeight()/2+Greenfoot.getRandomNumber(getWorld().getHeight()/2));
                    break;
            }
        }
        else
            i++;
    }
    /**
     * Method that is called when the left mouse button is clicked
     * and it will shoot in the world relating to which the weapon the player
     * has equipped
     */
    private void shoot()
    {
        switch(actual)
        {
            case 0:p.shoot(getX(),getY(),getRotation());
                break;
            case 1:m.shoot(getX(),getY(),getRotation());
                break;
        }
    }
    /**
     * Method that reloads the weapon which the player has equipped
     */
    private void reload()
    {
        switch(actual)
        {
            case 0:p.reload();
                break;
            case 1:m.reload();
                break;
        }
    }
    /**
     * Method that is called when the player is attacked by an enemy
     * if the player is too damaged the game is over
     */
    public void hurt()
    {
        hp-=5;
        if(hp<=0)
        {
            world.gameOver();
        }
    }
    /**
     * Method that is called when a zombie have died
     */
    public void setKill()
    {
        k++;
    }
    
    /**
     * Method that is called to obtain the amount of kills the player has
     * 
     */
    public int getKill()
    {
        return k;
    }
    
    /**
     * Method that changes the current players image
     * depending of what weapon is using.
     */
    private void changeWeapon()
    {
        if(actual<=1)
            actual++;
        else
            actual=0;
        switch (actual)
        {
            case 0:setImage("PlayerPistol.gif");
                    p.updateLabels();
                    m.invisible();
                    break;
            case 1:setImage("PlayerMachinegun.gif");
                    m.updateLabels();
                    p.invisible();
                    break;
        }
    }
    /**
     * Method that tells the player if a class is close enough to the player
     * @return true if the player can see that class and false if it cannot
     */
    private boolean canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        return actor != null;        
    }
    /**
     * Method that let the player pick-up an item class
     * then removes said item for world
     */
    private void eat()
    {
        if(canSee(Health.class))
        {
            Health h=(Health)getOneIntersectingObject(Health.class);
           
            
               if((hp+10)>100)
               {
                hp=100;
               }
               else
               {
               hp+=10;
               }
            getWorld().removeObject(h);
        }
        if(canSee(MGAmmo.class))
        {
            m.addItem();
            MGAmmo ma=(MGAmmo)getOneIntersectingObject(MGAmmo.class);
            getWorld().removeObject(ma);
        }
        switch(actual)
        {
            case 0:p.updateLabels();
                    break;
            case 1:m.updateLabels();
                    break;
        }
    }
    
}

