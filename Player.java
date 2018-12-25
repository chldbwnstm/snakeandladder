import java.awt.*;
import javax.swing.*;

/**
 * Player class represents a player which has a name,position,previous position and color property
 * @author Yoojoon Choi 101975675
 * @version 1.3
 */
public class Player extends JButton {
    //instance variables
    private String name;
    private int position = 0;
    private int prevPos = 0;
    private Color color;
    
    /**
    * Constructor for player. takes in width, height, name and color and sets initial data
    */
    public Player(int width,int height,String name,Color color) {
        super(name);
        this.name = name;
        this.color = color;
        this.setBackground(color);
        setSize(new Dimension(width,height));

    }
    
    /**
     * Returns the color of this player
     * @return color of this player
     */
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * sets the color of the object
     * @param color takes in a color with the Color data type
     */
    public void setColorTo(Color color)
    {
        this.setBackground(color);
    }
    
    /**
     * Returns the name of the player
     * @return name of the player
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * takes in the position in int format to set the position of the player
     * @param toSet takes in the position in int format to set the position of the player
     */
    public void setPosition(int toSet)
    {
        this.position = toSet;
    }
    
    /**
     * Returns the position of the player
     * @return position of player
     */
    public int getPosition()
    {
        return this.position;
    }
    
    /**
     * takes in the previous position in int format to set the previous position of the player
     * @param toSet previous position 
     */
    public void setPrevPosition(int toSet)
    {
        this.prevPos = toSet;
    }
    
    /**
     * Returns the previous position of the player
     * @return previous position of player
     */
    public int getPrevPosition()
    {
        return this.prevPos;
    }
}
