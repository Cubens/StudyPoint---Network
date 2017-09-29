

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;


// @author Lasse
 
public class TCPMultiServer {

    private static ServerSocket serverSocket;
    ArrayList<Integer> list = new ArrayList<Integer>();
    
    public static void main(String[] args) throws IOException
    {
        boolean tryAgain = true;
        int port;
        
        while(tryAgain){
        System.out.println("Write a port to listen on.(matching client is expecting port 5114)");
        Scanner userPortInput = new Scanner(System.in);
        
        if(userPortInput.hasNextInt() == true){
           port = userPortInput.nextInt(); 
           tryAgain = false;
           startThread(port);
        }else{
            System.out.println("Port has to be a number between 1 and 65535");
        }
       
        
        
        }
    }
    
    
    
    public static void startThread(int port) throws IOException{
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        serverSocket = new ServerSocket(port);
        while (true){
            new connectionThread(serverSocket.accept(),list).start();
        }
   
    }
    
}
