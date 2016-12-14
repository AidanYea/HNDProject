import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * This class creates labels onscreen for the user to see, acting like a kind of HUD
 * 
 * 
 * @author Aidan Yeaman
 */
public class Label  extends Actor
{
    /**
     * Constructor
     * @param text the text to show
     */
    public Label(String text)
    {
        GreenfootImage img=new GreenfootImage(text.length()*20,30);
        img.setColor(Color.red);
        img.drawString(text,2,20);
        setImage(img);
    }
    /**
     * This method change the text of the image.
     * It can be used as a update method
     */
    public void setText(String text)
    {
        GreenfootImage img=getImage();
        img.clear();
        img.drawString(text,2,20);
    }
    /**
     * This method is called when a weapon is changed and
     * the info have to ve invisible to let another label
     * appear
     */
    public void setInvisible()
    {
        GreenfootImage img=getImage();
        img.clear();
    }
}
