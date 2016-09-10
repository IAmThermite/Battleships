/*
* This class is the main panel that holds all the components.
* It will also handel the networking recieving
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class BattleshipsGamePanel extends JPanel implements ActionListener {
    private JPanel opponentPanel;
    private JPanel playerPanel;
    private JPanel logPanel;
    private JPanel controlPanel;
    
    private JLabel opponentLabel;
    private JLabel playerLabel;
    
    private JButton controlFireButton;
    public static JLabel controlLocationLabel; //static so that it can be changed golbaly
    
    private JTextArea battleLog;
    
    private BattleshipsGameFieldPanel opponentFieldPanel;
    private BattleshipsGameFieldPanel playerFieldPanel;
    
    public static ArrayList<BattleshipsGameMainPanel> listOfOpponentPanels = new ArrayList<BattleshipsGameMainPanel>(); //stores the instances of the opponents panels
    public static ArrayList<BattleshipsGameMainPanel> listOfPlayerPanels = new ArrayList<BattleshipsGameMainPanel>(); //stores the instances of the opponents panels
    
    
    public BattleshipsGamePanel() {
        setBackground(BattleshipsMainFrame.BG_COLOUR);
        setLayout(new GridLayout(2, 2));
        
        
        //
        //player grid on top
        //
        playerFieldPanel = new BattleshipsGameFieldPanel(true);
        
        playerPanel = new JPanel();
        playerPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        playerLabel = new JLabel("Your grid");
        playerLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        
        playerPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        playerPanel.add(playerLabel);
        playerPanel.add(playerFieldPanel);
        
        
        //
        //opponent grid on bottom
        //
        opponentFieldPanel = new BattleshipsGameFieldPanel(false);
        
        opponentPanel = new JPanel();
        
        opponentLabel = new JLabel("Opponents grid");
        opponentLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        opponentLabel.setForeground(Color.WHITE);
        opponentLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        
        opponentPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        opponentPanel.add(opponentLabel);
        opponentPanel.add(opponentFieldPanel);
        
        
        //
        //log panel
        //
        logPanel = new JPanel();
        logPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        battleLog = new JTextArea();
        battleLog.setFont(BattleshipsMainFrame.SMALL_FONT);
        battleLog.setEditable(false);
        
        JScrollPane logScroll = new JScrollPane(battleLog,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        
        logPanel.add(logScroll);
        
        
        //
        //control panel
        //
        GridBagLayout controlPanelLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        controlPanel = new JPanel();
        controlPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        controlPanel.setLayout(controlPanelLayout);
        
        //label   
        controlLocationLabel = new JLabel("A1");
        controlLocationLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        controlLocationLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        controlLocationLabel.setForeground(Color.WHITE);
        controlLocationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        //set constraints
        c.insets = new Insets(5, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 0;
        //add
        controlPanel.add(controlLocationLabel, c);
        
        //button
        controlFireButton = new JButton("FIRE!");
        controlFireButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        controlFireButton.addActionListener(this);
        controlFireButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(30, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 1;
        //add
        controlPanel.add(controlFireButton, c);
        
        
        
        //add all the panels
        add(playerPanel);
        add(logScroll);
        add(opponentPanel);
        add(controlPanel);
    }
    
    public void actionPerformed(ActionEvent e) {
        String location = controlLocationLabel.getText(); 
        
        for(int i = 0; i <listOfOpponentPanels.size(); i++) {
            if(listOfOpponentPanels.get(i).getName().equals(location)) {
                if(listOfOpponentPanels.get(i).getIsHit()) {
                    JOptionPane.showMessageDialog(this, "You have already shot at that panel!", "Invalid Location!", JOptionPane.ERROR_MESSAGE);
                } else {
                    battleLog.append(location + " was shot at!\n");
                    listOfOpponentPanels.get(i).setHit();
                }
                break; //for some reason it loops through twice so break out of it
            }
        } 
    }
}
