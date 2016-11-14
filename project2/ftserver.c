//Ashley Castiglione
//Project2
//ftserver.c
//the below links were used in this program
//http://codereview.stackexchange.com/questions/43914/client-server-implementation-in-c-sending-data-files
//http://pubs.opengroup.org/onlinepubs/9699919799/functions/getcwd.html
//http://www.gnu.org/savannah-checkouts/gnu/libc/manual/html_node/Simple-Directory-Lister.html
//http://stackoverflow.com/questions/20265328/readdir-beginning-with-dots-instead-of-files
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>

int main(int argc, char **argv)
{
    int listenfd = 0;
    int connfd = 0;
    struct sockaddr_in serv_addr;
    char sendBuff[1025];
    int numrv;
    char buffer[1025];

    listenfd = socket(AF_INET, SOCK_STREAM, 0);

    printf("Socket retrieve success\n");

    memset(&serv_addr, '0', sizeof(serv_addr));
    memset(sendBuff, '0', sizeof(sendBuff));

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(atoi(argv[1]));

    bind(listenfd, (struct sockaddr*)&serv_addr,sizeof(serv_addr));

    if(listen(listenfd, 10) == -1)
    {
        printf("Failed to listen\n");
        return -1;
    }


    while(1)
    {
        connfd = accept(listenfd, (struct sockaddr*)NULL ,NULL);

        //read the filename and save to a variable, use variable instead "testfile.txt"
        bzero(buffer,256);
        int n = read(connfd,buffer,255);
        if (n < 0){
            perror("ERROR reading from socket");
            exit(1);
        }

        char *command = malloc(n);
        snprintf(command, n, "%s", buffer);
        // char* command[n];
        //printf("command is %s\n", buffer);

        if (strcmp(command, "-g") == 0)
        {
           
            n = read(connfd,buffer,255);
            if (n < 0){
                perror("ERROR reading from socket");
                exit(1);
            }


            char *filename = malloc(n);
            snprintf(filename, n, "%s", buffer);
            //printf("filename is: %s\n", filename);
        
            FILE *fp = fopen(filename, "rb");
            if (!fp) {
                printf("Can't open file %s for reading\n", filename);
                exit(1);
            }



            /* Open the file that we wish to transfer */
            //FILE *fp = fopen(buffer,"rb");
            if(fp==NULL)
            {
                printf("File open error");
                return 1;   
            }   

            /* Read data from file and send it */
            while(1)
            {
                /* First read file in chunks of 256 bytes */
                unsigned char buff[256]={0};
                int nread = fread(buff,1,256,fp);
                //printf("Bytes read %d \n", nread);        

                /* If read was success, send data. */
                if(nread > 0)
                {
                    //printf("Sending \n");
                    write(connfd, buff, nread);
                }

                if (nread < 256)
                {
                    
                    if (feof(fp))
                        printf("File Transfer Complete\n");


                    if (ferror(fp))
                        printf("Error reading\n");
                    break;
                }

            }

        }
    
        else if (strcmp(command, "-l") == 0)
        {
            
            DIR *dir;
            struct dirent *dp;
            char * file_name;
            dir = opendir(".");
            while ((dp=readdir(dir)) != NULL) {
                //printf("debug: %s\n", dp->d_name);
                if ( !strcmp(dp->d_name, ".") || !strcmp(dp->d_name, "..") )
                {
                    // do nothing (straight logic)
                } 
                else 
                {
                    file_name = dp->d_name; // use it
                    printf("file_name: \"%s\"\n",file_name);
                    write(connfd, file_name, strlen(dp->d_name));

                }
            }
            closedir(dir);
        }


        close(connfd);
        sleep(1);
    }

    return 0;
}