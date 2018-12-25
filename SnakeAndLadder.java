import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;
import java.util.*;
import java.io.IOException;
import java.util.logging.*;
import java.awt.event.*;
/**
 * SnakeAndLadder class where everything about running the game is created
 * it is responsible for the sequence of the game and the layout of the game
 *
 * @author Yoojoon Choi 101975675
 * @version 1.3
 */

public class SnakeAndLadder extends JPanel implements Runnable
{
    // instance data
    private Board gameBoard = new Board(); //gameboard with 50 panels
    private Die dice = new Die(); //a dice that has 6 faces
    private Player human = new Player(100,100,"human",new Color(255,0,0)); //human player
    private Player computer = new Player(100,100,"computer",new Color(0,191,255)); //computer player
    private JPanel leftPanel = new JPanel(); //panel placed on left responsible of displaying the dice
    private JPanel playerPanel = new JPanel(); //panel placed on left bottom where 2 player icons are shown
    private JPanel boardPanel = new JPanel(); //panel where gameboard is placed
    private JPanel messagePanel = new JPanel(); //panel where message lable is placed
    private JLabel messageLabel = new JLabel("<html><h1>"+"WELCOME TO SNAKE AND LADDER"+"</h1></html>",SwingConstants.CENTER); //label where all messages to user will be shown
    private boolean rollClicked = false;
    private boolean correctButtonClicked = false;
    private boolean reroll = false;
    private int p1RollValue,p2RollValue;

    /**
    * Constructor for SnakeAndLadder, it sets up all the panels required
    */
    public SnakeAndLadder() 
    {
        super();
        //Setup overall game panel
        setLayout(null);
        leftPanel.setBounds(50,100,200,500);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.RED,4));
        leftPanel.setLayout(new GridLayout(2,0));
        leftPanel.add(dice);
        add(leftPanel);
        
        //Setup players panel
        playerPanel.setLayout(new GridLayout(0,2));
        playerPanel.add(human);
        playerPanel.add(computer);
        leftPanel.add(playerPanel);
        
        //Setup game board panel
        boardPanel.setBounds(350,100,500,700);
        boardPanel.add(gameBoard);
        add(boardPanel);
        
        //Setup message board
        messagePanel.setBounds(50,0,800,80);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,4));
        messagePanel.setBackground(Color.white);
        messagePanel.add(messageLabel);
        add(messagePanel);

    }
    
    /**
    * @override 
    * overrides the run method of runnable interface, it starts the game
    */
    public void run()
    {
        startGame();
    }
    
    /**
    * returns a player who got the higher number or null if tie
    * @param human,computer takes in 2 players
    * @return returns a player who got the higher number or null if tie
    */
    public Player decideFirstPlayer(Player human,Player computer)
    {
        int humanFace,computerFace;
        
        rollClicked = false;
        messageLabel.setText("<html><h1>Player: " + human.getName() + " roll your dice!</h1></html>");
        
        //Waits until human rolls the dice
        while(rollClicked == false)
        {
            dice.getRollButton().addActionListener(new ActionListener() 
            {
            public void actionPerformed(ActionEvent e) {
                rollClicked = dice.rollButtonClicked();
            }
            });
            delayText(1000);
            messageLabel.setText("<html><h1>Player: " + human.getName() + " roll your dice!</h1></html>");
            delayText(1000);
            messageLabel.setText("<html><h1>Player: " + human.getName() + " roll your dice! ROLL ROLL!</h1></html>");
        }
        humanFace = dice.getFaceValue();
        
        rollClicked = false;
        messageLabel.setText("<html><h1>Player: " + computer.getName() + " is rolling dice</h1></html>");
        
        //computer rolls dice
        dice.rollButtonClicked();
        delayText(1000);
        
        //computer's face value is set
        computerFace = dice.getFaceValue();
        
        messageLabel.setText("<html><h1>Player: " + human.getName() + " rolled: " + humanFace +"</h1></html>");
        delayText(2000);
        messageLabel.setText("<html><h1>Player: " + computer.getName() + " rolled: " + computerFace +"</h1></html>");
        delayText(2000);
        
        if(humanFace>computerFace) //handles when human face value is bigger than computer face value
        {
            messageLabel.setText("<html><h1>Player: " + human.getName() + " is starting first!!</h1></html>");
            delayText(2000);
            return human;
        }
        else if(computerFace>humanFace) //handles when computer face value is bigger than p1 face value
        {
            messageLabel.setText("<html><h1>Player: " + computer.getName() + " is starting first!!</h1></html>");
            delayText(2000);
            return computer;
        }
        else //handles the tie situation
        {
            messageLabel.setText("<html><h1>Tied! Roll again!!</h1></html>");
            delayText(2000);
            return null;
        }
    }
    
    /**
    * startGame method is responsible for whole sequence of the game
    */
    public void startGame() throws NullPointerException
    {
        
        showIntroMessage(); 
        Player player1=null;
        // decides the first player, reroll if tie
        while(player1==null)
        {
            player1 = decideFirstPlayer(human,computer);
        }
        
        // if the first player is human
        if(player1!=null && player1.equals(human)) 
        {
            
            while(true)
            {
                //human's turn
                humanTurn();
                System.out.println(human.getPosition());
                if(human.getPosition()==50)
                {
                    reroll = false;
                    break;
                }
                while(reroll){humanTurn();}
                //computer's turn
                computerTurn();
                System.out.println(computer.getPosition());
                if(computer.getPosition()==50)
                {
                    reroll = false;
                    break;
                }
                while(reroll){computerTurn();}
            }
            if(human.getPosition()==50)
            {
                messageLabel.setText("<html><h1> YOU WON! CONGRATULATIONS! </h1></html>");
            }
            if(computer.getPosition()==50)
            {
                messageLabel.setText("<html><h1> COMPUTER WON.. BETTER LUCK NEXT TIME! </h1></html>");
            }            
        }
        else //if the first turn is computer
        {
            while(player1!=null && true)
            {
                
                //computer's turn
                computerTurn();
                if(computer.getPosition()==50)
                {
                    reroll = false;
                    break;
                }
                while(reroll){computerTurn();}
                //human's turn
                humanTurn();
                if(human.getPosition()==50)
                {
                    reroll = false;
                    break;
                }
                while(reroll){humanTurn();}
            }
            if(player1!=null && computer.getPosition()==50)
            {
                messageLabel.setText("<html><h1> COMPUTER WON.. BETTER LUCK NEXT TIME! </h1></html>");
            }
            if(player1!=null && human.getPosition()==50)
            {
                messageLabel.setText("<html><h1> YOU WON! CONGRATULATIONS! </h1></html>");
            }
        }
        
         
                   
    }

    /**
    * shows the intro message on message board
    */
    public void showIntroMessage()
    {
        messageLabel.setText("<html><h1> WELCOME TO SNAKES AND LADDERS </h1></html>");
        delayText(2000);
        messageLabel.setText("<html><h1> Character will be highlighted in yellow if its' turn! Lets start! </h1></html>");
        delayText(2000);
    }
    
    /**
    * runs the sequence when its computer's turn
    * @param computer the first turn player is computer
    */
    public void computerTurn()
    {
        //computer's turn
        
        computer.setColorTo(new Color(255,255,0)); //highlight computer component as yellow and indicate its' turn
        
        //computer's turn computer rolls the dice
        messageLabel.setText("<html><h1>Player: " + computer.getName() + "'s Turn!</h1></html>");
        delayText(2000);
        messageLabel.setText("<html><h1>Player: " + computer.getName() + " is rolling the dice..</h1></html>");
        delayText(2000);
        dice.rollButtonClicked();
        p2RollValue = dice.getFaceValue(); // roll value of computer is set here 
        if(p2RollValue==6) // if roll value is 6, gives another turn
        {
            messageLabel.setText("<html><h1>Player: " + computer.getName() + " rolled: " + p2RollValue + " and gets another turn!</h1></html>");
            delayText(1000);
            reroll = true;
        }
        else
        {
            reroll = false;
        }
        
        // if roll value + current position of player is less than or equal to 50, move computer to that position and check for snake and ladders
        if((p2RollValue+computer.getPosition())<=50)
        {
            computer.setPrevPosition(computer.getPosition());
            computer.setPosition(computer.getPosition()+p2RollValue);
            messageLabel.setText("<html><h1>Player: " + computer.getName() + " rolled: " + p2RollValue + "</h1></html>");
            delayText(1000);
            messageLabel.setText("<html><h1>Moving Player:" + computer.getName() + " to position: " + computer.getPosition() + "</h1></html>");
            if(computer.getPrevPosition()!=0)
            {
                gameBoard.getBoardArray().get(computer.getPrevPosition()-1).setBackground(new JButton().getBackground());
            }
            if(human.getPosition()!=0 && human.getPosition()!=0 && computer.getPrevPosition()==human.getPosition())
            {
                gameBoard.getBoardArray().get(computer.getPrevPosition()-1).setBackground(human.getColor());
            }
            gameBoard.getBoardArray().get(computer.getPosition()-1).setBackground(computer.getColor());
            delayText(1000);
            checkForSnakesAndLadders(computer);
        }
        // if roll value + current position of player is over 50, show must land exactly on 50 message
        else
        {
            messageLabel.setText("<html><h1>Player: " + computer.getName() + " rolled: " + p2RollValue + "</h1></html>");
            delayText(1000);
            messageLabel.setText("<html><h1> Computer must land exactly on the last square to win. End of turn.  </h1></html>");
            delayText(1000);
        }
        
        computer.setColorTo(computer.getColor()); //highlight computer component back to its original color
    }

    /**
    * runs the sequence of human turn
    * @param human the first turn player is human
    */
    public void humanTurn()
    {
        //human's turn
        
        human.setColorTo(new Color(255,255,0)); //highlight human component as yellow and indicate its' turn
        
        // waits for the human player to click the roll button
        rollClicked = false;
        while(rollClicked == false)
        {
            dice.getRollButton().addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) {
                    rollClicked = dice.rollButtonClicked();
                }
            });
            
            delayText(1000);
            messageLabel.setText("<html><h1>Player: " + human.getName() + "'s turn! roll your dice!</h1></html>");
            delayText(1000);
            messageLabel.setText("<html><h1>Player: " + human.getName() + " roll your dice! ROLL ROLL!</h1></html>");
        }
        
        p1RollValue = dice.getFaceValue(); // sets the human player roll value

        // if roll value is 6, gives another turn
        if(p1RollValue==6)
        {
            messageLabel.setText("<html><h1>Player: " + human.getName() + " rolled: " + p1RollValue + " and gets another turn!</h1></html>");
            delayText(1000);
            reroll = true;
        }
        else
        {
            reroll = false;
        }
        
        // if roll value + current position of player is less than or equal to 50, move computer to that position and check for snake and ladders
        if((p1RollValue+human.getPosition())<=50)
        {
            messageLabel.setText("<html><h1>Player: " + human.getName() + " rolled: " + p1RollValue +"</h1></html>");
            delayText(1000);
            human.setPrevPosition(human.getPosition());
            human.setPosition(human.getPosition()+p1RollValue);
            
            
            correctButtonClicked = false;
            while(correctButtonClicked == false)
            {
                this.gameBoard.getBoardArray().get(human.getPosition()-1).addActionListener(new ActionListener() 
                {
                public void actionPerformed(ActionEvent e) {
                    if(human.getPrevPosition()!=0)
                    {
                        gameBoard.getBoardArray().get(human.getPrevPosition()-1).setBackground(new JButton().getBackground()); //resets the previous button
                    }
                    gameBoard.getBoardArray().get(human.getPosition()-1).setBackground(human.getColor());
                    messageLabel.setText("<html><h1>Moving Player:" + human.getName() + " to position: " + human.getPosition() + "</h1></html>");

                    //if computer was in the same position, leaves the JButton as computer color
                    if(human.getPosition()!=0 && computer.getPosition()!=0 && human.getPrevPosition()==computer.getPosition())
                    {
                        gameBoard.getBoardArray().get(human.getPrevPosition()-1).setBackground(computer.getColor());
                    }
                    correctButtonClicked = true;
                }
                });

                //sets all other JButtons as wrong button
                for(int i=0;i<this.gameBoard.getBoardArray().size();i++)
                {
                    if(i!=human.getPosition()-1)
                    {
                        this.gameBoard.getBoardArray().get(i).addActionListener(new ActionListener() 
                        {
                        public void actionPerformed(ActionEvent e) {
                            messageLabel.setText("<html><h1> You are trying to move to WRONG place </h1></html>");
                        }
                        });
                    }
                }
                delayText(1000);
                messageLabel.setText("<html><h1>Player: " + human.getName() + " move to position: " + human.getPosition() + "</h1></html>");
            }
            checkForSnakesAndLadders(human); //checks for snakes and ladders for current player
            
            
            //resets action listers for the next human turn
            for( JButton currentButton: gameBoard.getBoardArray() ) 
            {
                for( ActionListener eachAction : currentButton.getActionListeners() ) 
                {
                    currentButton.removeActionListener( eachAction );
                }
            }
        }
        else
        {
            messageLabel.setText("<html><h1>Player: " + human.getName() + " rolled: " + p1RollValue + "</h1></html>");
            delayText(1000);
            messageLabel.setText("<html><h1> You must land exactly on the last square to win. End of turn.  </h1></html>");
            delayText(1000);
        }
        
        human.setColorTo(human.getColor()); //highlight human component back to its' original color
    }

    
    /**
    * checks if the curentplayer position is on any start of snake or ladder
    * @param currentPlayer the player to check for snake and ladder
    */
    public void checkForSnakesAndLadders(Player currentPlayer)
    {
        //checks for ladders after player rolls the dice and moves to rolled spot
        for(int i=0;i<gameBoard.getLadderStarts().length;i++)
        {
            if(currentPlayer.getPosition()==gameBoard.getLadderStarts()[i])
            {
                currentPlayer.setPrevPosition(currentPlayer.getPosition());
                currentPlayer.setPosition(gameBoard.getLadderEnds()[i]);
                messageLabel.setText("<html><h1>Player: " + currentPlayer.getName() + " found a LADDER! climbing to " + currentPlayer.getPosition() + "</h1></html>");
                delayText(1500);
                gameBoard.getBoardArray().get(currentPlayer.getPrevPosition()-1).setBackground(new JButton().getBackground());
                gameBoard.getBoardArray().get(currentPlayer.getPosition()-1).setBackground(currentPlayer.getColor());
                
            }
        }
        
        //checks for snakes after player rolls the dice and moves to rolled spot
        for(int i=0;i<gameBoard.getSnakeStarts().length;i++)
        {
            if(currentPlayer.getPosition()==gameBoard.getSnakeStarts()[i])
            {
                currentPlayer.setPrevPosition(currentPlayer.getPosition());
                currentPlayer.setPosition(gameBoard.getSnakeEnds()[i]);
                messageLabel.setText("<html><h1> OH NO!!! Player: " + currentPlayer.getName() + " found a SNAKE! falling down to " + currentPlayer.getPosition() + "</h1></html>");
                delayText(1500);
                gameBoard.getBoardArray().get(currentPlayer.getPrevPosition()-1).setBackground(new JButton().getBackground());
                gameBoard.getBoardArray().get(currentPlayer.getPosition()-1).setBackground(currentPlayer.getColor());
                
            }
        }
    }
    
    /**
    * delays the system by value in parameter
    * @param milliSeconds millisecond as an int
    */
    public void delayText(int milliSeconds)
    {
        try{
            Thread.sleep(milliSeconds);//if you want to do it every .1
            //seconds, just wait 100 milliseconds.
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }   
 
    /**
    * returns the human player
    * @return human player
    */
    public Player getHumanPlayer()
    {
        return this.human;
    }
    
    /**
    * returns the computer player
    * @return computer player
    */
    public Player getComputerPlayer()
    {
        return this.computer;
    }
    
    /**
    * returns true if rollclicked
    * @return if roll button is clicked or not
    */
    public boolean getRollClicked()
    {
        return this.rollClicked;
    }
    
    /**
    * set the roll button as clicked or not clicked
    */
    public void setRollClicked(boolean toSet)
    {
        this.rollClicked = toSet;
    }
    
}
