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
    private final int AIRCRAFT_LENGTH = 5;
    private final int BATTLESHIP_LENGTH = 4;
    private final int SUBMARINE_LENGTH = 3;
    private final int DESTROYER_LENGTH = 3;
    private final int MINESWEEPER_LENGTH = 2;
    
    //stores the locations of the 
    private ArrayList<String> aircraftLocations;
    private ArrayList<String> battleshipLocations;
    private ArrayList<String> submarineLocations;
    private ArrayList<String> destroyerLocations;
    private ArrayList<String> minesweeperLocations;
    
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
    
    private boolean subSet = false;
    
    private int shipsPlaced = 0; //number of ships placed
    
    private JLabel selectedLabel = new JLabel(currentLocation);
    private JLabel directionLabel = new JLabel(currentDirection);
    
    private ArrayList<BattleshipsSetupShipsPanel> setupPanelList = new ArrayList<BattleshipsSetupShipsPanel>();
    
    public BattleshipsSetupShips() {
        selectedLabel.setFont(BattleshipsMainFrame.mainFont);
        selectedLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        directionLabel.setFont(BattleshipsMainFrame.mainFont);
        directionLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        setTitle("Place Your Ships!");
        setBackground(BattleshipsMainFrame.BG_COLOUR);
        setSize(1000, 550);
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
        directionButton.setFont(BattleshipsMainFrame.mediumFont);
        directionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        directionButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(5, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        //finally add
        actionPanel.add(directionButton, c);
        
        
        //
        //done button
        //
        doneButton = new JButton("Battle!");
        doneButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        doneButton.setFont(BattleshipsMainFrame.mainFont);
        doneButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        doneButton.addActionListener(this);
        //set constraints
        c.insets = new Insets(25, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        //finally add
        actionPanel.add(doneButton, c);
        
        
        //
        //aircraft button
        //
        aircraftButton = new JButton("Aircraft Carrier");
        aircraftButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        aircraftButton.setFont(BattleshipsMainFrame.mediumFont);
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
        battleshipButton = new JButton("Battleship");
        battleshipButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        battleshipButton.setFont(BattleshipsMainFrame.mediumFont);
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
        submarineButton = new JButton("Submarine");
        submarineButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        submarineButton.setFont(BattleshipsMainFrame.mediumFont);
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
        destroyerButton = new JButton("Destroyer");
        destroyerButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        destroyerButton.setFont(BattleshipsMainFrame.mediumFont);
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
        minesweeperButton = new JButton("Minesweeper");
        minesweeperButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        minesweeperButton.setFont(BattleshipsMainFrame.mediumFont);
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
        
        
        //set constraints for direction label
        c.insets = new Insets(20, 5, 5, 5); //the padding around component
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        actionPanel.add(directionLabel, c);
        
        
        //set constraints for selected label
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
        fieldPanel.setPreferredSize(new Dimension(600, 600));
        fieldPanel.add(blankPanel);
        
        //add number labels
        for(int i = 0; i<10; i++) {
            JLabel l = new JLabel(Integer.toString(i+1));
            l.setFont(BattleshipsMainFrame.mainFont);
            l.setForeground(Color.WHITE);
            fieldPanel.add(l);
        }
        
        //add the rest
        for(int i = 0; i<10; i++) {
            String letter = letters[i];
            
            JLabel l = new JLabel();
            l.setForeground(Color.WHITE);
            l.setFont(BattleshipsMainFrame.mainFont);
            l.setText(letter);
            
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
        
        
        public void mouseClicked(MouseEvent e) {
            currentLocation = name;
            selectedLabel.setText(name);
        }
        
        public void setShip() {
            hasShip = true;
            setBackground(BattleshipsMainFrame.SHIP_COLOUR);
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
    
    private class Ship {
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
           
            //set the ship on the first location
            for(int i = 0; i< setupPanelList.size(); i++) {
                if(setupPanelList.get(i).getName().equals(location)) {
                    setupPanelList.get(i).setShip(); //put the ship on the board
                    setOtherPanels();
                }
            }
        }
        
        private void setOtherPanels() {
            //if the ship is down we need to find the next letter and place ships on the squares under it depending on the length
            if(direction.equals("Down")) {
                for(int i = 0; i<size;i++) {
                    String nextLetter = letters[letterIndex+1];
                    String nextLocation = new String(nextLetter + String.valueOf(number));
                    
                    for(int x = 0; x<setupPanelList.size(); x++) {
                        if(setupPanelList.get(x).getName().equals(nextLocation)) { //we found the next ship
                            setupPanelList.get(x).setShip();
                            
                            nextLetter = letters[letterIndex+i];
                            nextLocation = new String(nextLetter + String.valueOf(number));
                        }
                    }
                }
            } else if(direction.equals("Right")) {
                for(int i = 0; i<size; i++) {
                    String nextLocation = new String(letter + String.valueOf(number+i));
                    for(int x = 0; x<setupPanelList.size(); x++) {
                        if(setupPanelList.get(x).getName().equals(nextLocation)) {
                            setupPanelList.get(x).setShip();
                        }
                    }
                }
            }
        }
    }
    
    private void setShip(int size) {
        if(validate(size)) {
            System.out.println("success");
            
            if(size == AIRCRAFT_LENGTH) {
                aircraftShip = new Ship(currentLocation, AIRCRAFT_LENGTH, currentDirection);
            } else if(size == BATTLESHIP_LENGTH) {
                battleshipShip = new Ship(currentLocation, BATTLESHIP_LENGTH, currentDirection);
            } else if(size == SUBMARINE_LENGTH) {
                if(subSet) { //if the sub has been set the set the destroyer
                    destroyerShip = new Ship(currentLocation, DESTROYER_LENGTH, currentDirection);
                } else {
                    submarineShip = new Ship(currentLocation, SUBMARINE_LENGTH, currentDirection);
                    subSet = true;
                }
            
            } else if(size == MINESWEEPER_LENGTH) {
                minesweeperShip = new Ship(currentLocation, MINESWEEPER_LENGTH, currentDirection);
            }
        }
    }
    
    //checks to see if the ship will not overlap
    private boolean checkSquares() {
        return false;
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
                    System.out.println("Something went wrong!");
                    return false;
                }
            }
        }
                
        return false; //it needs it annoyingly however it shouldnt happen
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
            
            directionLabel.setText(currentDirection);
            
        } else if(e.getSource() == doneButton) {
            System.out.println("done");
            
        } else if(e.getSource() == aircraftButton) {
            System.out.println("aircraft");
            setShip(AIRCRAFT_LENGTH);
            
        } else if(e.getSource() == battleshipButton) {
            System.out.println("battleship");
            setShip(BATTLESHIP_LENGTH);
            
        } else if(e.getSource() == submarineButton) {
            System.out.println("submarine");
            setShip(SUBMARINE_LENGTH);
            
        } else if(e.getSource() == destroyerButton) {
            System.out.println("destroyer");
            setShip(DESTROYER_LENGTH);
            
        } else if(e.getSource() == minesweeperButton) {
            System.out.println("minesweeper");
            setShip(MINESWEEPER_LENGTH);
            
        }
    }
}
