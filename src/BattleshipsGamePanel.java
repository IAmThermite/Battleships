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
    
    //stores the opponents ships - probably no longer need these
    private ArrayList<BattleshipsGameMainPanel> opponentAircraftPanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> opponentBattleshipPanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> opponentSubmarinePanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> opponentDestroyerPanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> opponentMinesweeperPanels = new ArrayList<BattleshipsGameMainPanel>();
    
    private ArrayList<BattleshipsGameMainPanel> opponentPanels = new ArrayList<BattleshipsGameMainPanel>();
    
    //stores the players ships - still do need these though
    private ArrayList<BattleshipsGameMainPanel> playerAircraftPanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> playerBattleshipPanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> playerSubmarinePanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> playerDestroyerPanels = new ArrayList<BattleshipsGameMainPanel>();
    private ArrayList<BattleshipsGameMainPanel> playerMinesweeperPanels = new ArrayList<BattleshipsGameMainPanel>();
    
    //stuff for networking
    private BufferedReader in;
    private Socket connection;
    
    private BattleshipsCommand battleshipsCommandObject;
    private GetCommand commandObject;
    
    private boolean isMyTurn = false;
    private int myNumber;
    private String myName = BattleshipsHostJoin.username;
    private String opponentName;
    
    
    private final int TOTAL_LENGTH = BattleshipsSetupShips.AIRCRAFT_LENGTH + BattleshipsSetupShips.BATTLESHIP_LENGTH + BattleshipsSetupShips.SUBMARINE_LENGTH + BattleshipsSetupShips.DESTROYER_LENGTH + BattleshipsSetupShips.MINESWEEPER_LENGTH;
    public BattleshipsGamePanel() {
        this.connection = BattleshipsHostJoin.connection;
        
        setBackground(BattleshipsMainFrame.BG_COLOUR);
        setLayout(new GridLayout(2, 2));
        
        
        //
        //player grid on top
        //
        playerFieldPanel = new BattleshipsGameFieldPanel(true);
        
        playerPanel = new JPanel();
        playerPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        playerLabel = new JLabel(BattleshipsHostJoin.username + "\'s grid");
        playerLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setFont(BattleshipsMainFrame.MEDIUM_FONT);
        
        playerPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        playerPanel.add(playerLabel);
        playerPanel.add(playerFieldPanel);
        
        
        //
        //opponent grid on bottom
        //
        opponentFieldPanel = new BattleshipsGameFieldPanel(false);
        
        opponentPanel = new JPanel();
        
        opponentLabel = new JLabel("Opponent's grid");
        opponentLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        opponentLabel.setForeground(Color.WHITE);
        opponentLabel.setFont(BattleshipsMainFrame.MEDIUM_FONT);
        
        opponentPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        opponentPanel.add(opponentLabel);
        opponentPanel.add(opponentFieldPanel);
        
        
        //
        //log
        //
        battleLog = new JTextArea();
        battleLog.setFont(BattleshipsMainFrame.SMALL_FONT);
        battleLog.setForeground(Color.BLACK);
        battleLog.setEditable(false);
        
        JScrollPane logScroll = new JScrollPane(battleLog,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        
        
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
        controlLocationLabel.setForeground(Color.BLACK);
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
        controlFireButton.setForeground(Color.BLACK);
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
        
        
        
        //add all the panels/log
        add(playerPanel);
        add(logScroll);
        add(opponentPanel);
        add(controlPanel);
        
        
        
        //setup stuff
        setupPlayerShips();
        
        battleshipsCommandObject = new BattleshipsCommand();
        
        commandObject = new GetCommand();
        Thread t1 = new Thread(commandObject);
        t1.start();
        
        //give the opponent important stuff
        giveOpponentName();
        giveRandomNumber();
        giveOpponentShips();
        
        
    }
    
    private class GetCommand extends Thread {
        //this subclass will continuously check for a new command
        private String command;
        private ArrayList<String> allLocations = new ArrayList<String>();
        private boolean setup = false;
        private boolean name = false;
        private boolean random = false;
        
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while(true) {
                    command = in.readLine();
                    
                    if(command == null) { //we have lost connection, notify the user exit this loop
                        lostConnection();
                        break;
                    } else if(command.equals("setup")) {  //this means that we are going to get the locations of ships
                        setup = true;
                        while(setup) {
                            String line = in.readLine();
                            if(line.equals("end")) { //when the line is end we have finished
                                setup = false;
                                setOpponentShips();
                                break;
                            } else {
                                allLocations.add(line);
                                System.out.println(allLocations);
                            }
                        }
                    } else if(command.equals("name")) { //the opponent is giving us their name
                        name = true;
                        while(name) {
                            String line = in.readLine();
                            if(line.equals("end")) { //when we have finished
                                name = false;
                                break;
                            } else {
                                System.out.println("opponents name " + line);
                                opponentName = line;
                                setOpponentName();
                            }
                        }
                    } else if(command.equals("random")) { //we are getting a random number to find out whos turn it is
                        random = true;
                        while(random) {
                            String line = in.readLine();
                            int number = 0;
                            try {
                                number = Integer.parseInt(line);
                            } catch(Exception e) {} //ignore failure
                            
                            if(line.equals("end")) { //when we have finished
                                random = false;
                                break;
                            } else if(number > myNumber) {
                                isMyTurn = false;
                                battleLog.append(opponentName + " is starting!\n");
                            } else if(number < myNumber){
                                isMyTurn = true;
                                battleLog.append("We are starting!\n");
                            } else {
                                System.out.println("random tie");
                                giveRandomNumber();
                            }
                        }
                    } else {
                        System.out.println(command);
                        setOpponentShot(command);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        public ArrayList<String> getLocations() {
            return allLocations;
        }
    }
    
    //the opponent has shot at us, where and did it hit?
    private void setOpponentShot(String location) {        
        battleLog.append(opponentName + " shot at " + location + "\n");
        for(int i = 0; i<listOfPlayerPanels.size(); i++) {
            if(listOfPlayerPanels.get(i).getName().equals(location)) {
                listOfPlayerPanels.get(i).setHit();
                if(listOfPlayerPanels.get(i).getHasShip()) {
                    battleLog.append(opponentName + " hit one!\n");
                    isMyTurn = false;
                } else {
                    battleLog.append(opponentName + " missed!\n");
                    isMyTurn = true;
                }
                break;
            }
        }
    }
    
    //send a random number (0-2^32) to the opponent, if it is greater than theirs then we start
    private void giveRandomNumber() {
        Random rand = new Random();
        myNumber = rand.nextInt();
        
        battleshipsCommandObject.sendCommand("random");
        battleshipsCommandObject.sendCommand(String.valueOf(myNumber));
        battleshipsCommandObject.sendCommand("end");
    }
    
    //set the opponents name on their grid
    private void setOpponentName() {
        opponentLabel.setText(opponentName + "'s grid");
    }
    
    //give the opponent our name
    private void giveOpponentName() {
        battleshipsCommandObject.sendCommand("name");
        battleshipsCommandObject.sendCommand(myName);
        battleshipsCommandObject.sendCommand("end");
    }
    
    //when we loose connection
    private void lostConnection() {
        JOptionPane.showMessageDialog(this, "We have lost connection with the other user! Sorry!", "Oh Noes!", JOptionPane.ERROR_MESSAGE);
        isMyTurn = false;
        try {
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //maybe redo this one??
    private void setupPlayerShips() {
        //add the aircraft panels to list
        for(int i = 0; i<BattleshipsSetupShips.aircraftLocations.size(); i++) {
            for(int x = 0; x<listOfPlayerPanels.size(); x++) {
                if(BattleshipsSetupShips.aircraftLocations.get(i).equals(listOfPlayerPanels.get(x).getName())) {
                    playerAircraftPanels.add(listOfPlayerPanels.get(x));
                }
            }
        }
        
        //add the battleship panels to list
        for(int i = 0; i<BattleshipsSetupShips.battleshipLocations.size(); i++) {
            for(int x = 0; x<listOfPlayerPanels.size(); x++) {
                if(BattleshipsSetupShips.battleshipLocations.get(i).equals(listOfPlayerPanels.get(x).getName())) {
                    playerBattleshipPanels.add(listOfPlayerPanels.get(x));
                }
            }
        }
        
        //add the submarine panels to list
        for(int i = 0; i<BattleshipsSetupShips.submarineLocations.size(); i++) {
            for(int x = 0; x<listOfPlayerPanels.size(); x++) {
                if(BattleshipsSetupShips.submarineLocations.get(i).equals(listOfPlayerPanels.get(x).getName())) {
                    playerSubmarinePanels.add(listOfPlayerPanels.get(x));
                }
            }
        }
        
        //add the destroyer panels to list
        for(int i = 0; i<BattleshipsSetupShips.destroyerLocations.size(); i++) {
            for(int x = 0; x<listOfPlayerPanels.size(); x++) {
                if(BattleshipsSetupShips.destroyerLocations.get(i).equals(listOfPlayerPanels.get(x).getName())) {
                    playerDestroyerPanels.add(listOfPlayerPanels.get(x));
                }
            }
        }
        
        //add the minesweeper panels to list
        for(int i = 0; i<BattleshipsSetupShips.minesweeperLocations.size(); i++) {
            for(int x = 0; x<listOfPlayerPanels.size(); x++) {
                if(BattleshipsSetupShips.minesweeperLocations.get(i).equals(listOfPlayerPanels.get(x).getName())) {
                    playerMinesweeperPanels.add(listOfPlayerPanels.get(x));
                }
            }
        }
        
        
        //set all the ships on the board
        for(int i = 0; i<playerAircraftPanels.size(); i++) {
            playerAircraftPanels.get(i).setShip();
        }
        for(int i = 0; i<playerBattleshipPanels.size(); i++) {
            playerBattleshipPanels.get(i).setShip();
        }
        for(int i = 0; i<playerSubmarinePanels.size(); i++) {
            playerSubmarinePanels.get(i).setShip();
        }
        for(int i = 0; i<playerDestroyerPanels.size(); i++) {
            playerDestroyerPanels.get(i).setShip();
        }
        for(int i = 0; i<playerMinesweeperPanels.size(); i++) {
            playerMinesweeperPanels.get(i).setShip();
        }
    }
    
    //checks to see if there is a ship on player board and will set the panel colour
    private void checkShip(String location) {
        for(int i = 0; i <listOfOpponentPanels.size(); i++) {
            if(listOfOpponentPanels.get(i).getName().equals(location)) {
                if(!listOfOpponentPanels.get(i).getIsPlayer()) { //if it isnt the player panel
                    if(listOfOpponentPanels.get(i).getIsHit()) {
                        JOptionPane.showMessageDialog(this, "You have already shot at that panel!", "Invalid Location!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        battleLog.append(location + " was shot at!\n");
                        battleshipsCommandObject.sendCommand(location); //send command
                        listOfOpponentPanels.get(i).setHit();
                        
                        if(listOfOpponentPanels.get(i).getHasShip()) {
                            battleLog.append("You hit one!\n");
                            isMyTurn = true;
                        } else {
                            battleLog.append("You missed!\n");
                            isMyTurn = false;
                            battleLog.append("It is no longer your turn!\n");
                        }
                    }
                }
                break; //for some reason it loops through twice so break out of it
            }
        }
    }
    
    //setup the opponent ships
    private void setOpponentShips() {
        ArrayList<String> opponentsLocations = commandObject.getLocations(); //list of the locations
        
        for(int i = 0; i<opponentsLocations.size(); i++) {
            for(int x = 0; x<listOfOpponentPanels.size(); x++) {
                if(listOfOpponentPanels.get(x).getName().equals(opponentsLocations.get(i))) {
                    listOfOpponentPanels.get(x).setShip();
                }
            }
        }
    }
    
    //give the opponent our ship locations
    private void giveOpponentShips() {
        battleshipsCommandObject.sendCommand("setup"); //say that we are giving them ships
        
        for(int i = 0; i<playerAircraftPanels.size(); i++) {
            battleshipsCommandObject.sendCommand(playerAircraftPanels.get(i).getName());
        }
        for(int i = 0; i<playerBattleshipPanels.size(); i++) {
            battleshipsCommandObject.sendCommand(playerBattleshipPanels.get(i).getName());
        }
        for(int i = 0; i<playerSubmarinePanels.size(); i++) {
            battleshipsCommandObject.sendCommand(playerSubmarinePanels.get(i).getName());
        }
        for(int i = 0; i<playerDestroyerPanels.size(); i++) {
            battleshipsCommandObject.sendCommand(playerDestroyerPanels.get(i).getName());
        }
        for(int i = 0; i<playerMinesweeperPanels.size(); i++) {
            battleshipsCommandObject.sendCommand(playerMinesweeperPanels.get(i).getName());
        }
        
        battleshipsCommandObject.sendCommand("end"); //say that we have finished
    }
    
    
    public void actionPerformed(ActionEvent e) {
        String location = controlLocationLabel.getText();
        if(isMyTurn) {
            battleshipsCommandObject.sendCommand(location);
            checkShip(location);
        } else {
            battleLog.append("It is not your turn!\n");
        }
    }
}
