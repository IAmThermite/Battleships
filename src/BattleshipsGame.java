/*
* This is the main game frame. it will open a jframe with the main panel
*/

import javax.swing.*;

public class BattleshipsGame extends JFrame {    
    public BattleshipsGame() {
        setBackground(BattleshipsMainFrame.BG_COLOUR);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Battleships!");
        
        add(new BattleshipsGamePanel());
        
        setResizable(false);
        setVisible(true);
    }
}
