Ashley Castiglione
Program2
README.txt

To compile this program:

-Place all files in same place in directory

**To Run ftclient.java
-open one terminal and run the following commands:

javac ftclient.java
jar cfm ftclient.jar Manifest.txt ftclient.class
java -jar ftclient.jar 15123

**To run ftserver.c
-open a second terminal and run the following command:

gcc -o ftserver ftserver.c
./ftserver 15123

*To see the directory contents, enter on the client side:
java -jar ftclient.c 127.0.0.1 15123 -l 20202

*To transfer a file, enter on the client side:
java -jar ftclient.c 127.0.0.1 15123 -g 'yourfilename' 20202

*To exit the server, press command c.

*both of these run on the same flip server

*Please note that it may take a few times of running a command to have it successfully work on flip. It is unknown why this issue exists, but it is a possible issue. 

This was run both locally on my Mac and on the OSU flip server.