import java.util.Random;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
/**
 * Represents a game die with between 4 and max int faces. Code includes a
 * mixture of explanatory comments to aid learner programmers and doc comments
 * of a level above where students are expected to reach.
 * @author Yoojoon Choi 101975675
 * @version 1.3
 */
public class Die extends JPanel {
    //Class data (constants)

    /** The default number of faces on a {@code Die}. */
    public static final int DEFAULT_FACES = 6;
    /** Minimum possible number of faces on a die with flat faces. */
    public static final int MIN_FACES = 4;

    //Instance data

    /** The number of faces on the die. */
    private int numFaces;
    /** The currently displayed face (the side that is 'up'). */
    private int faceValue;
    /** Source of next face. */
    private Random generator;
    /** the image of the dice */
    private BufferedImage diceImage = null;
    /** the button of the dice */
    private JButton rollButton = new JButton("Roll");
    /** the message that will appear when dice is rolled */
    private JLabel rollMessage = new JLabel("");

    //Constructors

    /**
     * Creates a new Die with the {@linkplain #DEFAULT_FACES default number} of
     * faces (6) and an initial face value of 1.
     */
    public Die() {
        // The this keyword can be used to call another of the class's constructors.
        // It must be the first statement in the calling constructor.
        this(DEFAULT_FACES);
       
    }

    /**
     * Creates a new Die with the given number of faces and an initial value
     * of 1. If the requested number of faces is below the {@linkplain
     * #MIN_FACES minimum} then it is set to the default number of faces (6).
     *
     * @param faces the number of faces of the new Die
     */
    public Die(int faces) {
        super(); // constructor of JPanel
        
        if (faces >= MIN_FACES) {
            numFaces = faces;
        } else {
            numFaces = DEFAULT_FACES;
        }
        faceValue = 1;
        generator = new Random();
        
        setBorder(BorderFactory.createLineBorder(Color.GRAY,4)); //sets the border of dice panel
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); //sets the layout of dice panel
        setPreferredSize(new Dimension(150,200)); // sets the preferred size of dice panel
        
        JLabel diceInstruction = new JLabel("<html>"+"Click the Roll button to roll!"+"</html>");
        add(diceInstruction); // adds the click the dice to roll message in the dice panel
        
        // tries to load dice image
        try
        {
            diceImage = ImageIO.read(new File("dice.png"));
        }
        catch (IOException e)
        {
            System.out.println("Error loading image");
        }
        JLabel dicePicLabel = new JLabel(new ImageIcon(diceImage)); //inserts image into a JLabel
        dicePicLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(dicePicLabel); //adds the dice image to the dice panel
        
        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollButtonClicked();
            }
        });
        add(rollButton); //adds the roll button to dice panel
        add(rollMessage); 
    }

    public Boolean rollButtonClicked() {
        roll();
        rollMessage.setText("<html><font color='red' size='3'>" + "Rolled: " + faceValue + "</font></html>");
        return true;
    }
    
    //Getters and modifiers

    /**
     * Returns the Die's current face value (i.e., the side facing 'up').
     *
     * @return the current face value
     */
    public int getFaceValue() {
        return faceValue;
    }

    /**
     * Simulates rolling the die by generating a new face number between 1 and
     * numFaces. Returns the new face.
     *
     * @return the new value of the Die after the roll
     */
    public int roll() {
        faceValue = generator.nextInt(numFaces) + 1;
        return faceValue;
    }

    /**
     * Returns the number of faces on the Die.
     * @return the number of faces
     */
    public int getFaces() {
        return numFaces;
    }

    /**
     * Returns the Jbutton that when clicked, rolls the dice
     * @return Jbutton that when clicked, rolls the dice
     */
    public JButton getRollButton()
    {
        return this.rollButton;
    }
}
