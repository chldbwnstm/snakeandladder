import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * SALFrame class is the actual frame that pops up
 * JPanel mainGame is added to this frame
 *
 * @author Yoojoon Choi 101975675
 * @version 1.3
 */
public class SALFrame extends JFrame {
    //instance data
    private SnakeAndLadder mainGame = new SnakeAndLadder(); // main game panel which has everything to run snake and ladders
    private Runnable r = mainGame; // sets the mainGame object as runnable
    private Player currentPlayer,nextPlayer; 
    private JButton restartButton = new JButton("<html>RESTART GAME</html>");
    private JPanel optionPanel = new JPanel(); //optional panel where restart button and etc will be placed
    Thread t = new Thread(r); // creates a thread to run the runnable
   
    /**
    * Constructor of SALFrame, it sets the size,title,content pane etc of the frame
    */
    public SALFrame() {
        super(); // JFrame default constructor
        setSize(1000,800);
        setTitle("Snake and ladder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        setContentPane(mainGame);
        
        //Setup option panel
        optionPanel.setBounds(50,630,100,50);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.RED,4));
        optionPanel.setLayout(new GridLayout());
        optionPanel.add(restartButton);
        add(optionPanel);
        
        //activate restart button
        restartButton.addActionListener(new ActionListener() 
        {
        public void actionPerformed(ActionEvent e) {
            t.interrupt(); //interrupt currently running thread
            mainGame = new SnakeAndLadder(); //reset game data
            r = mainGame;
            t = new Thread(r); //refresh the state of the thread to start another game
            setContentPane(mainGame);
            
            //Setup option panel
            optionPanel.setBounds(50,630,100,50);
            optionPanel.setBorder(BorderFactory.createLineBorder(Color.RED,4));
            optionPanel.setLayout(new GridLayout());
            optionPanel.add(restartButton);
            add(optionPanel);
            
            
            t.start(); //starts game
            
        }
        });
        
        t.start(); //starts game
    }
    
}
