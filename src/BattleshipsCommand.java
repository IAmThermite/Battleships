import java.io.*;
import java.net.*;
import java.util.*;

public class BattleshipsCommand {

    private Socket connection;
    private PrintWriter write;
    
    private String nextCommand = "command";
    
    public BattleshipsCommand() {
        this.connection = BattleshipsHostJoin.connection;
        
        try {
            write = new PrintWriter(connection.getOutputStream(), true); //auto flush
        } catch(Exception e) {
            System.out.println("Failed");
        }
    }
    
    public void sendCommand(String comm) {
        try {
            write.println(comm);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}


