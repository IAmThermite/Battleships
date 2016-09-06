import javax.swing.*;
import java.awt.*;

public class BattleshipsMainFrame {
    //setting up colours globally, they can be accessed by "BattleshipsMainFrame.<COLOUR_NAME>"
    public static final Color BG_COLOUR = new Color(205, 0, 35);
    public static final Color BUTTON_COLOUR = new Color(255, 205, 0);
    public static Font mainFont = new Font("Verdana", Font.PLAIN, 24);
    public static Font smallFont = new Font("Verdana", Font.PLAIN, 14);
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Battleships!");
        frame.setBackground(BG_COLOUR);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new BattleshipsHostJoin());
        frame.setVisible(true);
    }
}
