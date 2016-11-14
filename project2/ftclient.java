//Ashley Castiglione
//Project2
//ftclient.java
//the below links were used for this assignment
//http://mrbool.com/file-transfer-between-2-computers-with-java/24516
//http://hepunx.rl.ac.uk/~adye/javatutorial/networking/sockets/readingWriting.html
//http://www.tutorialspoint.com/java/io/inputstream_read.htm
import java.net.*;
import java.io.*; 
public class ftclient { 
  public static void main (String [] args ) throws IOException { 
    int filesize=1022386; 
    int bytesRead; 
    int currentTot = 0; 
    int dataPort = 0;
    //port number creation
    int portNumber = Integer.parseInt(args[1]);
    String command = args[2];
    String filename = null;
    Socket socket = new Socket(args[0],portNumber); 

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

    //for printing directory contents
    if (command.equals("-l")) 
    {
        dataPort = Integer.parseInt(args[3]);
        System.out.println("l is working");
        out.println(command);
        out.println(filename);
        // BufferedReader in = new BufferedReader(
        //         new InputStreamReader(socket.getInputStream()));
        InputStream is = socket.getInputStream();
        
        String inputLine = null;
        // while ((inputLine = in.readLine()) != null) {
        //     System.out.println(inputLine + '\n');

        // }
        int i;
        char c;
        while((i=is.read())!=-1)
         {
            // converts integer to character
            c=(char)i;
            
            // prints character
            System.out.print(c);
         }

        System.out.println(inputLine);

    }
    //for file transfer
    else if (command.equals("-g")) { 
         filename = args[3];
         dataPort = Integer.parseInt(args[4]);

         out.println(command);
         out.println(filename);
         
        System.out.println("File Transfer Complete." + '\n');   
        byte [] bytearray = new byte [filesize]; 
        InputStream is = socket.getInputStream(); 
        FileOutputStream fos = new FileOutputStream(filename + ".copy"); 
        BufferedOutputStream bos = new BufferedOutputStream(fos); 
        bytesRead = is.read(bytearray,0,bytearray.length); 
        currentTot = bytesRead; 

        do { bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot)); 
            if(bytesRead >= 0) 
            currentTot += bytesRead; 
        } while(bytesRead > -1);

        bos.write(bytearray, 0 , currentTot); 
        bos.flush(); 
        bos.close(); 

    }
    //if bad command entered
    else {
         System.out.println("Invalid Command.");
    }


    socket.close(); 

    } 
}