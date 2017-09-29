package ex1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


// @author Lasse
 
public class EchoServer {
    
    
    
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = null;
        Socket clientSocket = null; 
        
    try { 
         serverSocket = new ServerSocket(5114); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Could not listen on port: 5114. Probably occupied"); 
         System.exit(1); 
        }
  
    try { 
         clientSocket = serverSocket.accept(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Accept failed."); 
         System.exit(1); 
        } 
    
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    
    String inputLine;
    
    while ((inputLine = in.readLine()) != null) 
        { 
         String uppercased = inputLine.toUpperCase();   
         System.out.println ("Server: " + uppercased); 
         out.println(inputLine); 

         if (inputLine.equals("Bye."))
                System.out.println("Wallah vi ses");
             break; 
        } 
    
    out.close(); 
    in.close(); 
    clientSocket.close(); 
    serverSocket.close(); 
    
    
    }
        
    
}
