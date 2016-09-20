/*
* This class is the main menu panel
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class BattleshipsHostJoin extends JPanel implements ActionListener {
    //three panels that will be interchanged
    private JPanel mainPanel;
    private JPanel hostPanel;
    private JPanel joinPanel;
    
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
    
    private JFrame helpFrame;
    
    //for networking
    public static String username;
    public static String hostname;
    public static int port;
    
    public static ServerSocket server;
    public static Socket connection;
    
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
        mainHostButton.setForeground(Color.BLACK);
        mainHostButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainHostButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        mainHostButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(60, 0, 10, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        mainPanel.add(mainHostButton, c);
        
        //mainJoinButton
        mainJoinButton = new JButton("Join");
        mainJoinButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        mainJoinButton.setForeground(Color.BLACK);
        mainJoinButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainJoinButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        mainJoinButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(60, 0, 10, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        mainPanel.add(mainJoinButton, c);
        
        //mainHelpButton
        mainHelpButton = new JButton("Help");
        mainHelpButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        mainHelpButton.setForeground(Color.BLACK);
        mainHelpButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainHelpButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        mainHelpButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(20, 0, 20, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2; //span 2 columns
        //finally add
        mainPanel.add(mainHelpButton, c);
        
        //mainUsernameLabel
        mainUsernameLabel = new JLabel("Username");
        mainUsernameLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        mainUsernameLabel.setForeground(Color.BLACK);
        mainUsernameLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(10, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        //finally add
        mainPanel.add(mainUsernameLabel);
        
        //mainUsernameTextField
        mainUsernameTextField = new JTextField(5);
        mainUsernameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainUsernameTextField.setForeground(Color.BLACK);
        mainUsernameTextField.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(10, 10, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
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
        hostHeaderLabel.setForeground(Color.BLACK);
        hostHeaderLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(10, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        //finally add
        hostPanel.add(hostHeaderLabel, c);
        
        //hostPortLabel
        hostPortLabel = new JLabel("Port");
        hostPortLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        hostPortLabel.setForeground(Color.BLACK);
        hostPortLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(40, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        hostPanel.add(hostPortLabel, c);
        
        //hostPortTextField
        hostPortTextField = new JTextField(5);
        hostPortTextField.setForeground(Color.BLACK);
        hostPortTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        hostPortTextField.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(40, 10, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        hostPanel.add(hostPortTextField, c);
        
        //hostHostButton
        hostHostButton = new JButton("Start");
        hostHostButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        hostHostButton.setForeground(Color.BLACK);
        hostHostButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        hostHostButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        hostHostButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(40, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
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
        joinHeaderLabel.setForeground(Color.BLACK);
        joinHeaderLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(10, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        //finally add
        joinPanel.add(joinHeaderLabel, c);
        
        //joinHostLabel
        joinHostLabel = new JLabel("Address");
        joinHostLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        joinHostLabel.setForeground(Color.BLACK);
        joinHostLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(20, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinHostLabel, c);
        
        //joinHostTextField
        joinHostTextField = new JTextField(5);
        joinHostTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        joinHostTextField.setForeground(Color.BLACK);
        joinHostTextField.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(20, 10, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinHostTextField, c);
        
        //joinPortLabel
        joinPortLabel = new JLabel("Port");
        joinPortLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        joinPortLabel.setForeground(Color.BLACK);
        joinPortLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(20, 10, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinPortLabel, c);
        
        //joinPortTextField
        joinPortTextField = new JTextField(5);
        joinPortTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        joinPortTextField.setForeground(Color.BLACK);
        joinPortTextField.setFont(BattleshipsMainFrame.MAIN_FONT);
        //set constraints
        c.insets = new Insets(20, 0, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        //finally add
        joinPanel.add(joinPortTextField, c);
        
        //joinJoinButton
        joinJoinButton = new JButton("Join");
        joinJoinButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        joinJoinButton.setForeground(Color.BLACK);
        joinJoinButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        joinJoinButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        joinJoinButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(30, 10, 0, 0); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        //finally add
        joinPanel.add(joinJoinButton, c);
        
        setBackground(BattleshipsMainFrame.BG_COLOUR); //set the bg colour
        add(mainPanel);
    }
    
    //open the host a game screen
    private void openHost() {
        removeAll();
        add(hostPanel);
        validate();
        repaint();
    }
    
    //open the join a game screen
    private void openJoin() {
        removeAll();
        add(joinPanel);
        validate();
        repaint();
    }
    
    //open help dialog
    private void openHelp() {
        helpFrame = new JFrame("Help");
        helpFrame.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        JPanel panel = new JPanel();
        panel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        JButton button = new JButton("Close");
        button.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFont(BattleshipsMainFrame.MAIN_FONT);
        button.addActionListener(new ActionListener() { //action listener
            public void actionPerformed(ActionEvent e) {
                helpFrame.dispose(); //close the helpFrame
            }
        });
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(BattleshipsMainFrame.SMALL_FONT);
        
        
        try {
            InputStream input = getClass().getResourceAsStream("assets/help.txt"); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line = reader.readLine();
            
            while (line != null) { //while the line is not blank
                textArea.append(line + "\n"); //add to text area
                line = reader.readLine();
            }
            
            reader.close(); //close the file
            textArea.setEditable(false); //stop the user from being able to change text
        } catch(Exception e) {
            e.printStackTrace();
        }
                
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 350));
        
        panel.add(scrollPane);
        panel.add(button);
        
        helpFrame.add(panel);
        helpFrame.setSize(600, 430);
        helpFrame.setResizable(false);
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dispose instead of exit so the other one doesnt close
        helpFrame.setVisible(true);
    }
    
    private void startHost() {
        if(testHost()) {
            //start main game
            setUsername(true); //set the username
            BattleshipsMainFrame.frame.dispose();
            BattleshipsSetupShips s = new BattleshipsSetupShips();
        }
    }
    
    private void startJoin() {
        if(testJoin()) {
            //start main game
            setUsername(false); //set the username
            BattleshipsMainFrame.frame.dispose();
            BattleshipsSetupShips s = new BattleshipsSetupShips();
        }
    }
    
    //test host connection
    private boolean testHost() {
        try { //try and make the port an int
            int portNum = Integer.parseInt(hostPortTextField.getText());
            
            if(portNum>=1025 && portNum <=65535) { //ports less than 1024 are reserved. 65535 is the max port
                try { //attempt to make a new server socket
                    server = new ServerSocket(portNum);
                    connection = server.accept();
                    
                    return true;
                } catch(Exception e) {
                    //show failed dialog
                    JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    hostPortTextField.setText("");
                    
                    return false;
                }
            } else { //incorrect port
                //show error dialog invalid port
                hostPortTextField.setText("");
                JOptionPane.showMessageDialog(mainPanel, "Invalid Port (1025-65535 are valid)!", "Invalid Port", JOptionPane.ERROR_MESSAGE);
                
                return false;
            }
        } catch(Exception e) { //port not a number
            JOptionPane.showMessageDialog(mainPanel, "Invalid Port (1025-65535 are valid)!", "Invalid Port", JOptionPane.ERROR_MESSAGE);
            hostPortTextField.setText("");
            
            return false;
        }
    }
    
    //test join connection
    private boolean testJoin() {
        hostname = joinHostTextField.getText();
        
        String[] parts = hostname.split( "\\." ); //split the hostname into parts. a valid ip will have 4 parts
        
        if(parts.length == 4) {
            try {
                port = Integer.parseInt(joinPortTextField.getText());
                try { //try to connect to a server
                    connection = new Socket();
                    connection.connect(new InetSocketAddress(hostname, port), 5000); //5 second timeout
                    
                    return true;
                } catch(Exception e) {
                    //show failed dialog
                    JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Failed to connect", JOptionPane.ERROR_MESSAGE);
                    joinPortTextField.setText("");
                    joinHostTextField.setText("");
                    
                    return false;
                }
                
            } catch(Exception e) { //port not a number or couldnt bind to it
                JOptionPane.showMessageDialog(mainPanel, "Could not bind to port!", "Invalid Port", JOptionPane.ERROR_MESSAGE);
                hostPortTextField.setText("");
                
                return false;
            }
            
        } else { //invalid hostname
            JOptionPane.showMessageDialog(mainPanel, "Invalid IP address!", "IP Error", JOptionPane.ERROR_MESSAGE);
            joinHostTextField.setText("");
            
            return false;
        }
    }
    
    private void setUsername(boolean isHost) {
        username = mainUsernameTextField.getText();
        if(username == null || username.equals("")) {
            if(isHost) {
                username = "Host";
            } else {
                username = "Player";
            }
        }
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
