package ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


// @author Lasse
 
class connectionThread extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
 

    public connectionThread(Socket accept)
    {
    this.clientSocket = accept;
    }
 
        @Override
        public void run() {
            try
            {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("Waiting for connection on port: "+clientSocket.getLocalPort());
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    System.out.println("Server log: Received input - " + inputLine );

                if (inputLine.startsWith("UPPERCASE#" ))
                {  
                    System.out.println("Server log: Asked for uppercased...");
                    
                    out.println("UPPERCASED..." + inputLine.substring(10, inputLine.length()).toUpperCase() );
                } 
                else if (inputLine.startsWith("LOWERCASE#" ))
                {  
                    System.out.println("Server log: Asked for lowercased...");
                    
                    out.println("lowercased..." + inputLine.substring(10, inputLine.length()).toLowerCase() );
                }  
                else if (inputLine.equals( "TIME#" ))
                {  
                    System.out.println("Server log: Asked for the time...");
                    
                    out.println("THE TIME IS NOW:   "+new Date());
                }
                else if (inputLine.equals( "LEAVE#" ))
                {
                    System.out.println("Server log: Asked to leave...");
                    System.out.println("Server log: Client disconnected...");
                    
                    out.println("GOODBYE..." );
                    
                    break;
                }
                else
                {
                    System.out.println("Server log: Asked for something unknown...");
                    
                    out.println( "DO NOT UNDERSTAND..." );    
                }     
                }
            out.close();
            in.close();
            clientSocket.close();
                  } catch (IOException ex)
            {
                Logger.getLogger(connectionThread.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
