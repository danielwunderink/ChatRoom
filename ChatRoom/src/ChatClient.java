import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This class implements java socket client
 * @author pankaj
 *
 */
public class ChatClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //IP and port of the server
    	String ip = "10.0.28.131";
        int port = 25565;
        
        //new socket for the server
        Socket socket = new Socket(ip, port);
        
        //Create a PrintWriter to write what the client says to the server
        PrintWriter clientOutputToServer = new PrintWriter(socket.getOutputStream(), true);
        
        //Create a BufferedReader to read what the server says
        BufferedReader clientInputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        //Create a Scanner to read what the user types
        Scanner userInputToClient = new Scanner(System.in);
        
        String userInput = "";
        String exitToken = "exit";
        do {
        	System.out.print("- ");
        	userInput = userInputToClient.nextLine();
        	clientOutputToServer.println(userInput);
        	System.out.println("Server: "+ clientInputFromServer.readLine());
        } while (!userInput.equals(exitToken));
        
        socket.close();
        userInputToClient.close();
        
/*        for(int i=0; i<10;i++){
            //establish socket connection to server
            //socket = new Socket(host.getHostName(), 9876);
        	socket = new Socket("10.0.29.197", 25565);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4)oos.writeObject("exite");
            else oos.writeObject(""+"THIS IS HOW IT WORKS " + i);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }*/
    }
}
