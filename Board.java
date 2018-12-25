import java.util.*;
import javax.swing.*;
import java.awt.*;


/**
 * Snakes and Ladders board class, it has everything here necessary to create a board
 * the board extends JPanel so it can be added to any JComponent or JFrame
 * @author Yoojoon Choi 101975675
 * @version 1.3
 */

public class Board extends JPanel{
    //constants
    private final int MAX = 50; //MAX number for the board
    
    //instance variables
    private String[] messageBoard;
    private int[] ladderStarts = { 4, 15, 22, 30, 38 };
    private int[] ladderEnds =   {14, 36, 42, 35, 42 };
    
    private int[] snakeStarts = {47, 34, 25, 18, 12 };
    private int[] snakeEnds =   {20, 24, 17,  8, 5  };

    private boolean tracing;
    private ArrayList<JButton> boardArray = new ArrayList<JButton>();

    /**
    * Default constructor for Board
    */
    public Board() {
        super(new GridLayout(0,10)); // calls JPanel constructor with a layout manager argument of GridLayout
        this.setBorder(BorderFactory.createLineBorder(Color.RED,4)); //sets the border of this game board
        this.setPreferredSize(new Dimension(500,500)); //sets the game board size in the frame
        
        //Add each JButton in the game board
        for(int i=0;i<MAX;i++)
        {
            boardArray.add(new JButton(String.valueOf(i+1)));
            add(boardArray.get(i));
            boardArray.get(i).setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        }
        
        //Changes board orientation to start from bottom_left and end at top_right, found from offical Java API
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        for(int i=0;i<boardArray.size();i++)
        {
            setComponentZOrder(boardArray.get(i),boardArray.size()-1-i);
        }
        
        //Add ladders
        for (int i = 0; i < ladderStarts.length; i++) {
            boardArray.get(ladderStarts[i]-1).setText("<html>"+String.valueOf(ladderStarts[i])+"<br>"+"Start Ladder"+ String.valueOf(i+1) + "</html>");
            boardArray.get(ladderEnds[i]-1).setText("<html>"+String.valueOf(ladderEnds[i])+"<br>"+"End Ladder"+ String.valueOf(i+1) + "</html>");
        }
        boardArray.get(42-1).setText("<html>42" + "<br>" + "End Ladder 3 and 5</html>"); //42 have 2 start points
        
        //Add snakes
        for (int i = 0; i < snakeStarts.length; i++) {
            boardArray.get(snakeStarts[i]-1).setText("<html>"+String.valueOf(snakeStarts[i])+"<br>"+"Start Snake"+ String.valueOf(i+1) + "</html>");
            boardArray.get(snakeEnds[i]-1).setText("<html>"+String.valueOf(snakeEnds[i])+"<br>"+"End Snake"+ String.valueOf(i+1) + "</html>");
        }
        
    }

    /**
    * getter for boardArray
    * @return Arraylist of board buttons
    */
    public ArrayList<JButton> getBoardArray()
    {
        return this.boardArray;
    }
    
    /**
    * getter for ladderStarts
    * @return list of numbers where ladder starts from
    */
    public int[] getLadderStarts()
    {
        return this.ladderStarts;
    }
  
    /**
    * getter for ladderEnds
    * @return list of numbers where ladder ends
    */
    public int[] getLadderEnds()
    {
        return this.ladderEnds;
    }
    
    /**
    * getter for snakeStarts
    * @return list of numbers where snake starts from
    */
    public int[] getSnakeStarts()
    {
        return this.snakeStarts;
    }
    
    /**
    * getter for snakeEnds
    * @return list of numbers where ladder starts from
    */
    public int[] getSnakeEnds()
    {
        return this.snakeEnds;
    }
}
