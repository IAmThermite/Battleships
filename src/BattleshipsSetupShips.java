/*
* This class gets the user to setup their ships. It validates their locations as well
*/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipsSetupShips extends JFrame implements ActionListener {
    //location of starting square
    private String aircraftLocation;
    private String battleshipLocation;
    private String submarineLocation;
    private String destroyerLocation;
    private String minesweeperLocation;
    
    //lengths of each ship
    public final static int AIRCRAFT_LENGTH = 5;
    public final static int BATTLESHIP_LENGTH = 4;
    public final static int SUBMARINE_LENGTH = 3;
    public final static int DESTROYER_LENGTH = 3;
    public final static int MINESWEEPER_LENGTH = 2;
    
    //stores the locations of the ships
    public static ArrayList<String> aircraftLocations;
    public static ArrayList<String> battleshipLocations;
    public static ArrayList<String> submarineLocations;
    public static ArrayList<String> destroyerLocations;
    public static ArrayList<String> minesweeperLocations;
    
    private Ship aircraftShip;
    private Ship battleshipShip;
    private Ship submarineShip;
    private Ship destroyerShip;
    private Ship minesweeperShip;
    
    private JButton directionButton;
    private JButton doneButton;
    private JButton aircraftButton;
    private JButton battleshipButton;
    private JButton submarineButton;
    private JButton destroyerButton;
    private JButton minesweeperButton;
    
    private JPanel actionPanel;
    private JPanel fieldPanel;
    
    private String currentDirection = "Down";
    private String currentLocation = "A1";
    private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
    private boolean aircraftSet = false;
    private boolean battleshipSet = false;
    private boolean submarineSet = false;
    private boolean destroyerSet = false;
    private boolean minesweeperSet = false;
    
    private int shipsPlaced = 0; //number of ships placed
    
    private JLabel selectedLabel = new JLabel(currentLocation, JLabel.CENTER);
    private JLabel directionLabel = new JLabel("Direction:\n" + currentDirection, JLabel.CENTER);
    private JLabel numberSetLabel = new JLabel("Ships set: " + shipsPlaced + "/5", JLabel.CENTER);
    
    private ArrayList<BattleshipsSetupShipsPanel> setupPanelList = new ArrayList<BattleshipsSetupShipsPanel>();
    
    public BattleshipsSetupShips() {
        setTitle("Place Your Ships!");
        setBackground(BattleshipsMainFrame.BG_COLOUR);
        setSize(1050, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        
        
        GridBagLayout panelLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        actionPanel = new JPanel();
        actionPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        actionPanel.setLayout(panelLayout);
        
        
        //
        //direction button
        //
        directionButton = new JButton("Swap Direction");
        directionButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        directionButton.setForeground(Color.BLACK);
        directionButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        directionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        directionButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(5, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        //finally add
        actionPanel.add(directionButton, c);
        
        
        //
        //done button
        //
        doneButton = new JButton("Battle!");
        doneButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        doneButton.setForeground(Color.BLACK);
        doneButton.setFont(BattleshipsMainFrame.MAIN_FONT);
        doneButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        doneButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(25, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        //finally add
        actionPanel.add(doneButton, c);
        
        
        //
        //number set label
        //
        numberSetLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        numberSetLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        numberSetLabel.setForeground(Color.BLACK);
        numberSetLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //set constraints
        c.insets = new Insets(25, 5, 5, 5); //the padding around the component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        //finally add
        actionPanel.add(numberSetLabel, c);
        
        
        //
        //aircraft button
        //
        aircraftButton = new JButton("Aircraft Carrier (5)");
        aircraftButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        aircraftButton.setForeground(Color.BLACK);
        aircraftButton.setFont(BattleshipsMainFrame.MEDIUM_FONT);
        aircraftButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        aircraftButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(5, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        //finally add
        actionPanel.add(aircraftButton, c);
        
        
        //
        //battleship button
        //
        battleshipButton = new JButton("Battleship (4)");
        battleshipButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        battleshipButton.setForeground(Color.BLACK);
        battleshipButton.setFont(BattleshipsMainFrame.MEDIUM_FONT);
        battleshipButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        battleshipButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(5, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        //finally add
        actionPanel.add(battleshipButton, c);
        
        
        //
        //submarine button
        //
        submarineButton = new JButton("Submarine (3)");
        submarineButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        submarineButton.setForeground(Color.BLACK);
        submarineButton.setFont(BattleshipsMainFrame.MEDIUM_FONT);
        submarineButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        submarineButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(5, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        //finally add
        actionPanel.add(submarineButton, c);
        
        
        //
        //destroyer button
        //
        destroyerButton = new JButton("Destroyer (3)");
        destroyerButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        destroyerButton.setForeground(Color.BLACK);
        destroyerButton.setFont(BattleshipsMainFrame.MEDIUM_FONT);
        destroyerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        destroyerButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(5, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        actionPanel.add(destroyerButton, c);
        
        
        //
        //minesweeper button
        //
        minesweeperButton = new JButton("Minesweeper (2)");
        minesweeperButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        minesweeperButton.setForeground(Color.BLACK);
        minesweeperButton.setFont(BattleshipsMainFrame.MEDIUM_FONT);
        minesweeperButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        minesweeperButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(5, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        actionPanel.add(minesweeperButton, c);
        
        
        //
        //direction label
        //
        directionLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        directionLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        directionLabel.setForeground(Color.BLACK);
        directionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //set constraints
        c.insets = new Insets(20, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        actionPanel.add(directionLabel, c);
        
        
        //
        //selected label
        //
        selectedLabel.setFont(BattleshipsMainFrame.MAIN_FONT);
        selectedLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        selectedLabel.setForeground(Color.BLACK);
        selectedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //set constraints
        c.insets = new Insets(20, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        actionPanel.add(selectedLabel, c);
        
                
        //
        //setup fieldPanel that has all the squares in it
        //
        fieldPanel = new JPanel();
        GridLayout fieldLayout = new GridLayout(11, 11);
        
        JPanel blankPanel = new JPanel();
        blankPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        fieldPanel.setLayout(fieldLayout);
        fieldPanel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        fieldPanel.add(blankPanel);
        
        //add number labels
        for(int i = 0; i<10; i++) {
            JLabel l = new JLabel(Integer.toString(i+1), JLabel.CENTER);
            l.setForeground(Color.WHITE);
            l.setFont(BattleshipsMainFrame.MAIN_FONT);
            
            fieldPanel.add(l);
        }
        
        //add the rest
        for(int i = 0; i<10; i++) {
            String letter = letters[i];
            
            JLabel l = new JLabel(letter, JLabel.CENTER);
            l.setForeground(Color.WHITE);
            l.setFont(BattleshipsMainFrame.MAIN_FONT);
            
            fieldPanel.add(l); //add letter label
            
            //add the field panel
            for(int y = 0; y < 10; y++) {
                String fullName = letter + Integer.toString(y+1);
                
                BattleshipsSetupShipsPanel p = new BattleshipsSetupShipsPanel(fullName);
                
                fieldPanel.add(p);
                setupPanelList.add(p);
            }
        }
        
        add(fieldPanel);
        add(actionPanel);
        setVisible(true);
    }


    //initialise the ships variables
    private void setShip(int size) {
        if(validate(size)) {            
            if(size == AIRCRAFT_LENGTH) {
                if(!aircraftSet) {
                    aircraftShip = new Ship(currentLocation, AIRCRAFT_LENGTH, currentDirection);
                    aircraftLocations = aircraftShip.getLocations();
                    
                    if(aircraftShip.getSet()) { //check to see if the ship is validated correctly
                        aircraftSet = true;
                    } else { //unset the variables if failed
                        aircraftShip = null;
                        aircraftLocations = null;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You have already set this ship! Add another or press done to continue!", "Ship Already Set!", JOptionPane.ERROR_MESSAGE);
                }
            } else if(size == BATTLESHIP_LENGTH) {
                if(!battleshipSet) {
                    battleshipShip = new Ship(currentLocation, BATTLESHIP_LENGTH, currentDirection);
                    battleshipLocations = battleshipShip.getLocations();
                    
                    if(battleshipShip.getSet()) { //check to see if the ship is validated correctly
                        battleshipSet = true;
                    } else { //unset the variables if failed
                        battleshipShip = null;
                        battleshipLocations = null;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You have already set this ship! Add another or press done to continue!", "Ship Already Set!", JOptionPane.ERROR_MESSAGE);
                }
            } else if(size == SUBMARINE_LENGTH) {
                if(submarineSet) { //if the sub has been set then set the destroyer
                    if(!destroyerSet) {
                        destroyerShip = new Ship(currentLocation, DESTROYER_LENGTH, currentDirection);
                        destroyerLocations = destroyerShip.getLocations();
                        
                        if(destroyerShip.getSet()) { //check to see if the ship is validated correctly
                            destroyerSet = true;
                        } else { //unset the variables if failed
                            destroyerShip = null;
                            destroyerLocations = null;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "You have already set this ship! Add another or press done to continue!", "Ship Already Set!", JOptionPane.ERROR_MESSAGE);
                    }
                } else if(submarineSet && destroyerSet) {
                    JOptionPane.showMessageDialog(this, "You have already set this ship! Add another or press done to continue!", "Ship Already Set!", JOptionPane.ERROR_MESSAGE);
                } else {
                    submarineShip = new Ship(currentLocation, SUBMARINE_LENGTH, currentDirection);
                    submarineLocations = submarineShip.getLocations();
                    
                    if(submarineShip.getSet()) { //check to see if the ship is validated correctly
                        submarineSet = true;
                    } else { //unset the variables if failed
                        submarineShip = null;
                        submarineLocations = null;
                    }
                }
            
            } else if(size == MINESWEEPER_LENGTH) {
                if(!minesweeperSet) {
                    minesweeperShip = new Ship(currentLocation, MINESWEEPER_LENGTH, currentDirection);
                    minesweeperLocations = minesweeperShip.getLocations();
                    
                    if(minesweeperShip.getSet()) { //check to see if the ship is validated correctly
                        minesweeperSet = true;
                    } else { //unset the variables if failed
                        minesweeperShip = null;
                        minesweeperLocations = null;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You have already set this ship! Add another or press done to continue!", "Ship Already Set!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    //checks to see if the ship will not overlap. need to try-catch for incase null pointer exception
    private boolean checkSquares(ArrayList<String> list) {
        try {
            //check aircraft carrier
            for(int i = 0; i<aircraftLocations.size(); i++) {
                for(int x = 0; x<list.size(); x++) {
                    if(list.get(x).equals(aircraftLocations.get(i))) {
                        return false;
                    }
                }
            }
        } catch(Exception e) {} //ignore null pointer exception
        
        try {
            //check battleship
            for(int i = 0; i<battleshipLocations.size(); i++) {
                for(int x = 0; x<list.size(); x++) {
                    if(list.get(x).equals(battleshipLocations.get(i))) {
                        return false;
                    }
                }
            }
        } catch(Exception e) {} //ignore null pointer exception
        
        try {
            //check submarine
            for(int i = 0; i<submarineLocations.size(); i++) {
                for(int x = 0; x<list.size(); x++) {
                    if(list.get(x).equals(submarineLocations.get(i))) {
                        return false;
                    }
                }
            }
        } catch(Exception e) {} //ignore null pointer exception
        
        try {
            //check destroyer
            for(int i = 0; i<destroyerLocations.size(); i++) {
                for(int x = 0; x<list.size(); x++) {
                    if(list.get(x).equals(destroyerLocations.get(i))) {
                        return false;
                    }
                }
            }
        } catch(Exception e) {} //ignore null pointer exception
        
        try {
            //check minesweeper
            for(int i = 0; i<minesweeperLocations.size(); i++) {
                for(int x = 0; x<list.size(); x++) {
                    if(list.get(x).equals(minesweeperLocations.get(i))) {
                        return false;
                    }
                }
            }
        } catch(Exception e) {} //ignore null pointer exception
        
        //will only come to this if no square is duplicate as it will break at return
        return true;
    }
    
    //checks to see if the ship will fit
    private boolean validate(int size) {
        JPanel selectedPanel = null;
        char brokenName[];
        
        for(int i = 0; i< setupPanelList.size(); i++) {
            if(setupPanelList.get(i).getName().equals(currentLocation)) {
                selectedPanel = setupPanelList.get(i);
                
                brokenName = currentLocation.toCharArray();
                
                if(currentDirection.equals("Down")) { //down so letters
                    for(int x = 0; x<letters.length; x++) {
                        if(Character.toString(brokenName[0]).equals(letters[x])) {
                            try { //if there is no letter in the array at the end then there are not enough squares
                                String s = letters[x+(size-1)];
                                return true;
                            } catch(Exception e) { //no space in the array
                                JOptionPane.showMessageDialog(this, "Invalid location! Please place further up!", "Invalid Location!", JOptionPane.ERROR_MESSAGE);
                                return false;
                            }
                        }
                    }
                } else if(currentDirection.equals("Right")) { //right so numbers
                    int intValue;
                    if(brokenName.length == 2) { //therefore only one number
                        intValue = Integer.parseInt(String.valueOf(brokenName[1]));
                        if(intValue+(size-1) > 10) {
                            JOptionPane.showMessageDialog(this, "Invalid location! Please place further to the left!", "Invalid Location!", JOptionPane.ERROR_MESSAGE);
                            return false;
                        } else {
                            return true;
                        }
                    } else { //two numbers and therefore placing is impossible
                        JOptionPane.showMessageDialog(this, "Invalid location! Please place further to the left!", "Invalid Location!", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else { //should never come to this statement
                    return false;
                }
            }
        }
                
        return false; //it needs it annoyingly however it shouldnt happen
    }
    
    
    //change the label
    private void updateNumberToPlace() {
        shipsPlaced++;
        numberSetLabel.setText("Ships set: " + shipsPlaced + "/5");
    }
    
    //subclass
    private class BattleshipsSetupShipsPanel extends JPanel implements MouseListener {
        private String name;
        private boolean hasShip;
        
        public BattleshipsSetupShipsPanel(String name) {
            this.name = name;
            
            setBackground(BattleshipsMainFrame.NORMAL_COLOUR);
            setPreferredSize(new Dimension(15, 15));
            addMouseListener(this);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        
        public String getName() {
            return name;
        }
        
        public void setShip() {
            hasShip = true;
            setBackground(BattleshipsMainFrame.SHIP_COLOUR);
        }
        
        public void mouseClicked(MouseEvent e) {
            currentLocation = name;
            selectedLabel.setText(name);
        }
        
        public void mousePressed(MouseEvent e) {}
        
        public void mouseReleased(MouseEvent e) {}
        
        public void mouseEntered(MouseEvent e) {
            if(!hasShip) {
                setBackground(BattleshipsMainFrame.HOVER_COLOUR);
            }
        }
        
        public void mouseExited(MouseEvent e) {
            if(!hasShip) {
                setBackground(BattleshipsMainFrame.NORMAL_COLOUR);
            }
        }
    }
    
    //subclass
    private class Ship {
        private ArrayList<String> listOfFieldNames= new ArrayList<String>();
        
        private boolean isSet = false;
        
        private String location;
        private int size;
        private String direction;
        
        private int number;
        private char[] brokenName;
        private char letter;
        private int letterIndex;
    
        public Ship(String location, int size, String direction) {
            this.location = location;
            this.size = size;
            this.direction = direction;
            
            brokenName = location.toCharArray(); //convert the location to a char array
            
            letter = brokenName[0]; //the letter location
            
            //set the number
            if(brokenName.length == 2) {
                number = Integer.parseInt(String.valueOf(brokenName[1]));
            } else {
                char[] nums = {brokenName[1], brokenName[2]};
                String s = new String(nums);
                number = Integer.parseInt(s);
            }
            
            letterIndex = 0;
            //get the current letter index from array
            for(int i = 0; i<letters.length; i++) {
                if(letters[i].equals(String.valueOf(letter))) {
                    letterIndex = i;
                }
            }
            
            setOtherPanels(); //set the other panel names
            
            if(checkSquares(listOfFieldNames)) {
                finaliseOtherPanels();
                isSet = true;
            } else {
                JOptionPane.showMessageDialog(actionPanel, "The ships will overlap! Please choose another location or direction!", "Invalid Location!", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private void setOtherPanels() {
            //if the ship is down we need to find the next letter and place ships on the squares under it depending on the length
            if(direction.equals("Down")) {
                for(int i = 0; i<size;i++) {
                    String nextLetter = letters[letterIndex+i];
                    String nextLocation = new String(nextLetter + String.valueOf(number));
                    
                    listOfFieldNames.add(nextLocation);
                    
                }
            } else if(direction.equals("Right")) {
                for(int i = 0; i<size; i++) {
                    String nextLocation = new String(letter + String.valueOf(number+i));
                    
                    listOfFieldNames.add(nextLocation);
                    
                }
            }
        }
        
        //call when we have check that it does not overlap
        public void finaliseOtherPanels() {
            updateNumberToPlace();
            for(int i = 0; i<listOfFieldNames.size(); i++) {
                for(int x = 0; x<setupPanelList.size(); x++) {
                    if(setupPanelList.get(x).getName().equals(listOfFieldNames.get(i))) {
                        setupPanelList.get(x).setShip();
                    }
                }
            }
        }
        
        public boolean getSet() {
            return isSet;
        }
        
        public ArrayList<String> getLocations() {
            return listOfFieldNames;
        }
    }
    
    
    public void actionPerformed(ActionEvent e) {
        String location = selectedLabel.getText();
        String direction = directionLabel.getText();
        
        if(e.getSource() == directionButton) {
            if(currentDirection.equals("Down")) {
                currentDirection = "Right";
            } else if(currentDirection.equals("Right")) {
                currentDirection = "Down";
            }
            
            directionLabel.setText("Direction:\n" + currentDirection);
            
        } else if(e.getSource() == doneButton) {
            if(shipsPlaced == 5) {
                dispose();
                BattleshipsGame game = new BattleshipsGame();
                
            } else {
                JOptionPane.showMessageDialog(actionPanel, "You still have " + (5-shipsPlaced) + " Ships to place!", "Not done yet!", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == aircraftButton) {
            setShip(AIRCRAFT_LENGTH);
            
        } else if(e.getSource() == battleshipButton) {
            setShip(BATTLESHIP_LENGTH);
            
        } else if(e.getSource() == submarineButton) {
            setShip(SUBMARINE_LENGTH);
            
        } else if(e.getSource() == destroyerButton) {
            setShip(DESTROYER_LENGTH);
            
        } else if(e.getSource() == minesweeperButton) {
            setShip(MINESWEEPER_LENGTH);
            
        }
    }
}
