import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipsSetupShips extends JFrame implements ActionListener {
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
    
    private JLabel selectedLabel = new JLabel("A1");
    private JLabel directionLabel = new JLabel("Down");
    
    private String currentDirection = "Down"; //default value is Down
    
    public BattleshipsSetupShips() {
        selectedLabel.setFont(BattleshipsMainFrame.mainFont);
        selectedLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        directionLabel.setFont(BattleshipsMainFrame.mainFont);
        directionLabel.setBackground(BattleshipsMainFrame.BG_COLOUR);
        
        setBackground(BattleshipsMainFrame.BG_COLOUR);
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        GridBagLayout panelLayout = new GridBagLayout();
        GridBagConstraints panelConstraints = new GridBagConstraints();
        
        JPanel panel = new JPanel();
        
        //
        //direction button
        //
        directionButton = new JButton("Swap Direction");
        directionButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        directionButton.setFont(BattleshipsMainFrame.mainFont);
        directionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        directionButton.addActionListener(this);
        //set constraints
        
        panel.add(directionButton);
        
        
        //
        //done button
        //
        doneButton = new JButton("Battle!");
        doneButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        doneButton.setFont(BattleshipsMainFrame.mainFont);
        doneButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        doneButton.addActionListener(this);
        //set constraints
        
        panel.add(doneButton);
        
        
        //
        //aircraft button
        //
        aircraftButton = new JButton("Aircraft Carrier");
        aircraftButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        aircraftButton.setFont(BattleshipsMainFrame.mainFont);
        aircraftButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        aircraftButton.addActionListener(this);
        //set constraints
        
        panel.add(aircraftButton);
        
        
        //
        //battleship button
        //
        battleshipButton = new JButton("Battleship");
        battleshipButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        battleshipButton.setFont(BattleshipsMainFrame.mainFont);
        battleshipButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        battleshipButton.addActionListener(this);
        //set constraints
        
        panel.add(battleshipButton);
        
        
        //
        //submarine button
        //
        submarineButton = new JButton("Submarine");
        submarineButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        submarineButton.setFont(BattleshipsMainFrame.mainFont);
        submarineButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        submarineButton.addActionListener(this);
        //set constraints
        
        panel.add(submarineButton);
        
        
        //
        //destroyer button
        //
        destroyerButton = new JButton("Destroyer");
        destroyerButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        destroyerButton.setFont(BattleshipsMainFrame.mainFont);
        destroyerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        destroyerButton.addActionListener(this);
        //set constraints
        
        panel.add(destroyerButton);
        
        
        //
        //minesweeper button
        //
        minesweeperButton = new JButton("Minesweeper");
        minesweeperButton.setBackground(BattleshipsMainFrame.BUTTON_COLOUR);
        minesweeperButton.setFont(BattleshipsMainFrame.mainFont);
        minesweeperButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        minesweeperButton.addActionListener(this);
        //set constraints
        
        panel.add(minesweeperButton);
        
        panel.add(directionLabel);
        panel.add(selectedLabel);
        
        add(panel);
        
        setVisible(true);
    }







    //subclass
    public class SetupPanel extends JPanel implements MouseListener {
        public SetupPanel() {
            setBackground(BattleshipsMainFrame.BG_COLOUR);
            setPreferredSize(new Dimension(600, 600));
        }
        
        
        
        
        public void mouseClicked(MouseEvent e) {
        
        }
        
        public void mousePressed(MouseEvent e) {}
        
        public void mouseReleased(MouseEvent e) {}
        
        public void mouseEntered(MouseEvent e) {
        
        }
        
        public void mouseExited(MouseEvent e) {
        
        }
    }
    
    public class Ship {
        public Ship(String location, int size) {
            
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == directionButton) {
            System.out.println(currentDirection);
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
            
        } else if(e.getSource() == battleshipButton) {
            System.out.println("battleship");
            
        } else if(e.getSource() == submarineButton) {
            System.out.println("submarine");
            
        } else if(e.getSource() == destroyerButton) {
            System.out.println("destroyer");
            
        } else if(e.getSource() == minesweeperButton) {
            System.out.println("minesweeper");
            
        }
    }
    
    
    
    
}
