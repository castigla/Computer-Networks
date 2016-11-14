//Ashley Castiglione
//Program1
//chatserve.java
//the following code was found at the websites below
//http://docs.oracle.com/javase/tutorial/networking/sockets/index.html
//http://cs.lmu.edu/~ray/notes/javanetexamples/

//import java
import java.net.*;
import java.io.*;

//create class to run server
//will need to create Manifest.txt with name of this class in file
public class chatserve {
    public static void main(String[] args) throws IOException {

        //error check
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        //port number creation
        int portNumber = Integer.parseInt(args[0]);
        //create socket, get the output from the client, read that input in
        try (
            ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        ) {
            //while not null, get input from client, print to server side, 
            //and answer on server side to be sent to client side.
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
              //String outputLine = "Server says: " + inputLine;
               // out.println(outputLine);
                System.out.println(inputLine);
                String userInput;
                userInput = stdIn.readLine();
                System.out.println("server user typed: " + userInput);
                String outputLine = "Serverhandle> " + userInput;
                out.println(outputLine);
            }
            //exception call
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}