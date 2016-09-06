import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipsHostJoin extends JPanel implements ActionListener {
    
    //three panels that will be interchanged
    private JPanel mainPanel;
    private JPanel hostPanel;
    private JPanel joinPanel;
    
    //the current panel will be removed when another is added
    private JPanel currentPanel;
    
    //components for the mainPanel
    private JButton mainHostButton;
    private JButton mainJoinButton;
    private JButton mainHelpButton;
    private JTextField mainUsernameTextField;
    private JLabel mainUsernameLabel;
    
    //components for the hostPanel
    private JLabel hostHeaderLabel;
    private JLabel hostPortLabel;
    private JTextField hostPortTextField;
    private JButton hostHostButton;
    
    //components for the joinPanel
    private JLabel joinHeaderLabel;
    private JLabel joinHostLabel;
    private JTextField joinHostTextField;
    private JLabel joinPortLabel;
    private JTextField joinPortTextField;
    private JButton joinJoinButton;
    
    public String username;
    
    //constructor
    public BattleshipsHostJoin() {
    
        GridBagConstraints c = new GridBagConstraints(); //make the constraints for the layout
        
        //
        //set up main panel
        //
        mainPanel = new JPanel();
        mainPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        mainPanel.setLayout(new GridBagLayout());
        
        //mainHostButton
        mainHostButton = new JButton("Host");
        mainHostButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        mainHostButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainHostButton.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        mainPanel.add(mainHostButton, c);
        
        //mainJoinButton
        mainJoinButton = new JButton("Join");
        mainJoinButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        mainJoinButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainJoinButton.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        mainPanel.add(mainJoinButton, c);
        
        //mainHelpButton
        mainHelpButton = new JButton("Help");
        mainHelpButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        mainHelpButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainHelpButton.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2; //span 2 columns
        //finally add
        mainPanel.add(mainHelpButton, c);
        
        //mainUsernameLabel
        mainUsernameLabel = new JLabel("Username");
        mainUsernameLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        mainUsernameLabel.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        //finally add
        mainPanel.add(mainUsernameLabel);
        
        //mainUsernameTextField
        mainUsernameTextField = new JTextField(5);
        mainUsernameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainUsernameTextField.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        //finally add
        mainPanel.add(mainUsernameTextField, c);
        
        
        //
        //set up host panel
        //
        hostPanel = new JPanel();
        hostPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        hostPanel.setLayout(new GridBagLayout());
        
        //hostHeaderLabel
        hostHeaderLabel = new JLabel("Host A Game");
        hostHeaderLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        hostHeaderLabel.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        //finally add
        hostPanel.add(hostHeaderLabel, c);
        
        //hostPortLabel
        hostPortLabel = new JLabel("Port");
        hostPortLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        hostPortLabel.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        hostPanel.add(hostPortLabel, c);
        
        //hostPortTextField
        hostPortTextField = new JTextField(5);
        hostPortTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        hostPortTextField.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        hostPanel.add(hostPortTextField, c);
        
        //hostHostButton
        hostHostButton = new JButton("Start");
        hostHostButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        hostHostButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        hostHostButton.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        //finally add
        hostPanel.add(hostHostButton, c);
        
        
        //
        //set up join panel
        //
        joinPanel = new JPanel();
        joinPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        joinPanel.setLayout(new GridBagLayout());
        
        //joinHeaderLabel
        joinHeaderLabel = new JLabel("Join A Game");
        joinHeaderLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        joinHeaderLabel.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        //finally add
        joinPanel.add(joinHeaderLabel, c);
        
        //joinHostLabel
        joinHostLabel = new JLabel("Address");
        joinHostLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        joinHostLabel.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinHostLabel, c);
        
        //joinHostTextField
        joinHostTextField = new JTextField(5);
        joinHostTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        joinHostTextField.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinHostTextField, c);
        
        //joinPortLabel
        joinPortLabel = new JLabel("Address");
        joinPortLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        joinPortLabel.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinPortLabel, c);
        
        //joinPortTextField
        joinPortTextField = new JTextField(5);
        joinPortTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        joinPortTextField.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinPortTextField, c);
        
        //joinJoinButton
        joinJoinButton = new JButton("Join");
        joinJoinButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        joinJoinButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        joinJoinButton.setFont(BattleshipsMainFrame.mainFont);
        //set constraints
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        //finally add
        joinPanel.add(joinJoinButton, c);
        
        setBackground(BattleshipsMainFrame.BG_COLOUR); //set the bg colour
        add(mainPanel);
        currentPanel = mainPanel; //set the current selected panel to the mainPanel
    }
    
    //open the host a game screen
    private void openHost() {
        
    }
    
    //open the join a game screen
    private void openJoin() {
        
    }
    
    //open help dialog
    private void openHelp() {
        
    }
    
    //host
    private void startHost() {
        
    }
    
    //join
    private void startJoin() {
        
    }
    
    //override the abstract ActionListener method
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainHostButton) {
            openHost();
        } else if(e.getSource() == mainJoinButton) {
            openJoin();
        } else if(e.getSource() == mainHelpButton) {
            openHelp();
        } else if(e.getSource() == hostHostButton) {
            startHost();
        } else if(e.getSource() == joinJoinButton) {
            startJoin();
        }
    }
}
