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
        setPreferredSize(new Dimension(350, 350));
        
        //add a blank panel
        JPanel blankPanel = new JPanel();
        blankPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        add(blankPanel);
        
        //add the numbers
        for(int i = 0; i<10; i++) {
            String number = String.valueOf(i+1);
            
            JLabel numLabel = new JLabel(number);
            numLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
            numLabel.setForeground(Color.WHITE);
            numLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
            
            add(numLabel);
        }
        
        //add the letter first then a new BattleshpsGameMainPanel
        for(int i = 0; i<letters.length; i++) {
            String letter = letters[i];
            
            JLabel letterLabel = new JLabel(letter);
            letterLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
            letterLabel.setForeground(Color.WHITE);
            letterLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
            
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
