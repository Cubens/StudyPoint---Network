package ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


// @author Lasse
 
public class TCPclient {
    
 public static void main(String[] args) throws IOException {

        String serverHostname = "127.0.0.1";

        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            clientSocket = new Socket(serverHostname, 5114);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "+"the connection to: " + serverHostname);
            System.exit(1);
        }

	BufferedReader infoIN = new BufferedReader(new InputStreamReader(System.in));
        
	String userInput;

        System.out.print ("input: ");
        
	while ((userInput = infoIN.readLine()) != null) {
	    out.println(userInput);
	    System.out.println("echo: " + in.readLine());
            System.out.print ("input: ");
	}

	out.close();
	in.close();
	infoIN.close();
	clientSocket.close();
    }
}