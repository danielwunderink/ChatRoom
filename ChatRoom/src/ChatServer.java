import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 25565;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        
        
        PrintWriter outputToClient;
        BufferedReader inputFromClient;
        String clientInput;
        String serverOutput;
        while(true){
            System.out.println("Waiting for client request");
            //creating socket and waiting for client connection
            Socket clientSocket = server.accept();
            //read from socket to ObjectInputStream object
            outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            do {
            	clientInput = inputFromClient.readLine();
            	System.out.println("Client: "+clientInput);
                serverOutput = clientInput;
                outputToClient.println(serverOutput);
            } while (!clientInput.equalsIgnoreCase("exit"));
            
            /*ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            //write object to Socket
            oos.writeObject("Hi Client "+message);
            //close resources
            ois.close();
            oos.close();*/
            clientSocket.close();
            //terminate the server if client sends exit request
            if(clientInput.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

}
