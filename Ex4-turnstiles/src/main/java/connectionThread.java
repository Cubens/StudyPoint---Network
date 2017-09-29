

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


// @author Lasse
 
class connectionThread extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        ArrayList<Integer> list;
 

    public connectionThread(Socket accept)
    {
    this.clientSocket = accept;
    }

    connectionThread(Socket accept, ArrayList<Integer> list)
    {
    this.clientSocket = accept;
    this.list = list;    
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

                if (inputLine.equals("+1")){
                    list.add(1);
                    out.print("Added 1 to the counter");
                }
                else if (inputLine.equals("count")){
                    out.print("Current count is: "+list.size());
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
