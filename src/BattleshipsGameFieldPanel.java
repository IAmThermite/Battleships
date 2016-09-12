/*
* This class is the class that adds all the labels and the grid squares
*/

import javax.swing.*;
import java.awt.*;

public class BattleshipsGameFieldPanel extends JPanel {
    private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
    private boolean isPlayer;
    
    public BattleshipsGameFieldPanel(boolean isPlayer) {
        this.isPlayer = isPlayer;
    
        setBackground(BattleshipsMainFrame.BG_COLOUR);
        setLayout(new GridLayout(11, 11));
        setPreferredSize(new Dimension(300, 300));
        
        //add a blank panel
        JPanel blankPanel = new JPanel();
        blankPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        add(blankPanel);
        
        //add the numbers
        for(int i = 0; i<10; i++) {
            String number = String.valueOf(i+1);
            
            JLabel numLabel = new JLabel(number, JLabel.CENTER);
            numLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
            numLabel.setForeground(Color.WHITE);
            numLabel.setFont(BattleshipsMainFrame.MEDIUM_FONT);
            
            add(numLabel);
        }
        
        //add the letter first then a new BattleshpsGameMainPanel
        for(int i = 0; i<letters.length; i++) {
            String letter = letters[i];
            
            JLabel letterLabel = new JLabel(letter, JLabel.CENTER);
            letterLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
            letterLabel.setForeground(Color.WHITE);
            letterLabel.setFont(BattleshipsMainFrame.MEDIUM_FONT);
            
            add(letterLabel);
            
            //now add the grid panels
            for(int x = 0; x<10; x++) {
                String fullName = letter + Integer.toString(x+1);
                BattleshipsGameMainPanel p = new BattleshipsGameMainPanel(isPlayer, fullName);
                add(p);
                
                if(isPlayer) {
                    BattleshipsGamePanel.listOfPlayerPanels.add(p); //add to the list of player panels
                } else {
                    BattleshipsGamePanel.listOfOpponentPanels.add(p); //add to the list of opponent panels
                }
            }
        }
    }
}
