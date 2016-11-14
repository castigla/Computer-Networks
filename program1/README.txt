Ashley Castiglione
Program1
README.txt

To compile this program:

-Place all files in same place in directory

**To Run chatserve.java
-open one terminal and run the following commands:

javac chatserve.java
jar cfm chatserve.jar Manifest.txt chatserve.class
java -jar chatserve.jar 8080

*to start the java server after initial compiling, only the java -jar chatserve.jar 8080 command is needed.

**To run chatclient.py
-open a second terminal and run the following command:

python chatclient.py localhost 8080

The client will then ask for your name (handle). After entering, it will reiterate that handle and you will see ':'. This means it is the client's turn to enter input. Once input is entered, it will wait for input on the server side to be sent to the client side. 

To quit, enter \quit on either the client side or server side.

This was run both locally on my Mac and on the OSU flip server.