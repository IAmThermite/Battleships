/*
* This is the main class. It opens a jframe with the main menu panel.
* It also contains all the global final variables.
*/

import javax.swing.*;
import java.awt.*;

public class BattleshipsMainFrame {
    //setting up colours globally, they can be accessed by "BattleshipsMainFrame.<COLOUR_NAME>"
    public static final Color BG_COLOUR = new Color(205, 0, 35);
    public static final Color BUTTON_COLOUR = new Color(255, 205, 0);
    
    //colours for the panels
    public static final Color HIT_COLOUR = new Color(245, 245, 90);
    public static final Color MISS_COLOUR = Color.WHITE;
    public static final Color NORMAL_COLOUR = new Color(0, 165, 165);
    public static final Color HOVER_COLOUR = new Color(0, 125, 125);
    public static final Color SHIP_COLOUR = Color.BLACK;
    
    public static final Font MAIN_FONT = new Font("Verdana", Font.PLAIN, 24);
    public static final Font SMALL_FONT = new Font("Verdana", Font.PLAIN, 14);
    public static final Font MEDIUM_FONT = new Font("Verdana", Font.PLAIN, 18);
    
    public static JFrame frame = new JFrame("Battleships!");
    
    public static void main(String[] args) {
        frame.setBackground(BG_COLOUR);
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new BattleshipsHostJoin());
        frame.setVisible(true);
    }
}
