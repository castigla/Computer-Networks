#Ashley Castiglione
#Program1
#chatclient.py
#the following code was found at the websites below
#https://docs.python.org/2/library/socketserver.html
#http://stackoverflow.com/questions/70797/python-user-input-and-commandline-arguments

import socket
import sys

HOST = sys.argv[1]
PORT = int(sys.argv[2])
data = " ".join(sys.argv[1:])

# Create a socket (SOCK_STREAM means a TCP socket)
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    #Connect to server and send data
    sock.connect((HOST, PORT))
    #sock.sendall(data + "\n")

    #getting and printing the user handle
    userhandle = raw_input("Please enter your name up to 10 letters: ")
    print "you entered", userhandle

    #create bool that = false for the while loop
    #while quit == false, get command line input. send to server, and wait for server message
    #when quit == true (by entering \quit), print program ending, break loop, jump to 
    #finally: block to close socket
    quit = False
    while quit == False:
        print ":"
        message = raw_input()
        if message == "\quit":
            quit = True

        if quit == True:
            print "program ending"
            break
        if quit == False:
            servermessage = (userhandle + "> " + message)
            sock.sendall(servermessage + "\n")
            print "*waiting for reply*"
            received = sock.recv(1024)
            #print "{}".format(received)
            print received
        if received == "Serverhandle> \quit" + "\n":
            print "client closing"
                
            break
            


    

finally:
    sock.close()

#print "Sent:   {}".format(data)
#print "Received: {}".format(received)