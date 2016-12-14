import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract Weapon class that have some variables that are common for
 * all the weapons in the game
 * 
 * @author Aidan Yeaman 
 */
abstract public class Weapon extends Actor
{
    protected int shootSpeed;//Speed between bullets
    protected int cartdridgeCapacity; //maximum value of bullets that can be stored in weapon
    protected int actual;//Actual number of bullets in the cartdridge
    protected int total;//Total number of amunitions availables
    protected int i;//Variable that helps in the implementation of the speed between bullets.
    protected Label currentLabel, totalLabel;

    abstract public void shoot(int pX, int pY, int rT);
    abstract public void reload();
}