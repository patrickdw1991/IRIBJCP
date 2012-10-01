#include <winsock2.h>
#include <stdio.h>
#include <stdlib.h>
#include <String.h>



typedef struct
{
       int id;
       char name[20];             //Sensor_X
       int value;                 //0 or 1
       char unit[20];
       char timestamp[22];        //2012-12-31_12:59:59
       int is_alarm;              //True:False
}BinSen;


BinSen test[12];

int initializeDatabase()
{


      int isAlarm = 0;
      int i;
      for(i = 0; i < 12; i++){
              test[i].id = i;
              test[i].is_alarm = isAlarm;
              if(isAlarm == 0){
                         isAlarm =1;
              }else{
                         isAlarm =0;
              }
              test[i].value = 1337;
      }
}

char stringBuffer[50] = "S,";

int createValueString(){
    int i;
    int j;    
    for(i = 0; i < 12; i++){
          char tmp[10];
          char tmp2[5]; 
          itoa(test[i].id, tmp, 2);
          strcat(tmp, "=");
          itoa(test[i].value, tmp2, 5);
          strcat(tmp, tmp2);
          strcat(tmp, ",");
          strcat(stringBuffer, tmp);
    }    
    char end[10] = "E";
    strcat(stringBuffer, end);
    printf("Final buffer = %s \n",stringBuffer);

}

int main()
{
    initializeDatabase();
    createValueString();
    
	WORD wVersionRequested;
	WSADATA wsaData;
	int wsaerr;

	// Using MAKEWORD macro, Winsock version request 2.2
	wVersionRequested = MAKEWORD(2, 2);

	wsaerr = WSAStartup(wVersionRequested, &wsaData);
	if (wsaerr != 0)
	{
    		/* Tell the user that we could not find a usable */
   		/* WinSock DLL.*/
    		printf("The Winsock dll not found!\n");
    		return 0;
	}
	else
	{
       		printf("The Winsock dll found!\n");
       		printf("The status: %s.\n", wsaData.szSystemStatus);
	}

	/* Confirm that the WinSock DLL supports 2.2.*/
	/* Note that if the DLL supports versions greater    */
	/* than 2.2 in addition to 2.2, it will still return */
	/* 2.2 in wVersion since that is the version we      */
	/* requested.                                        */
	if (LOBYTE(wsaData.wVersion) != 2 || HIBYTE(wsaData.wVersion) != 2)
	{
    		/* Tell the user that we could not find a usable */
    		/* WinSock DLL.*/
    		printf("The dll do not support the Winsock version %u.%u!\n", LOBYTE(wsaData.wVersion), HIBYTE(wsaData.wVersion));
    		WSACleanup();
    		return 0;
	}
	else
	{
    		printf("The dll supports the Winsock version %u.%u!\n", LOBYTE(wsaData.wVersion), HIBYTE(wsaData.wVersion));
    		printf("The highest version this dll can support: %u.%u\n", LOBYTE(wsaData.wHighVersion), HIBYTE(wsaData.wHighVersion));
	}

	//////////Create a socket////////////////////////
	//Create a SOCKET object called m_socket.
	SOCKET m_socket;

	// Call the socket function and return its value to the m_socket variable.
	// For this application, use the Internet address family, streaming sockets, and
	// the TCP/IP protocol.
	// using AF_INET family, TCP socket type and protocol of the AF_INET - IPv4
	m_socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

	// Check for errors to ensure that the socket is a valid socket.
	if (m_socket == INVALID_SOCKET)
	{
    		printf("Error at socket(): %ld.\n", WSAGetLastError());
    		WSACleanup();
    		return 0;
	}
	else
	{
    		printf("socket() is OK!\n");
	}

	////////////////bind()//////////////////////////////
	// Create a sockaddr_in object and set its values.
	SOCKADDR_IN service;

	// AF_INET is the Internet address family.
	service.sin_family = AF_INET;
	// "127.0.0.1" is the local IP address to which the socket will be bound.
	service.sin_addr.s_addr = inet_addr("127.0.0.1");
	// 55555 is the port number to which the socket will be bound.
	service.sin_port = htons(55555);

	// Call the bind function, passing the created socket and the sockaddr_in structure as parameters.
	// Check for general errors.

	if (bind(m_socket, (SOCKADDR*)&service, sizeof(service)) == SOCKET_ERROR)
	{
    		printf("bind() failed: %ld.\n", WSAGetLastError());
    		closesocket(m_socket);
   	 	return 0;
	}
	else
	{
   		printf("bind() is OK!\n");
	}

	if (listen( m_socket, 1) == SOCKET_ERROR)
    		printf("listen(): Error listening on socket %ld.\n", WSAGetLastError());
	else
	{
    		printf("listen() is OK, I'm waiting for connections...\n");
	}
	// Create a temporary SOCKET object called AcceptSocket for accepting connections.
	SOCKET AcceptSocket;

	// Create a continuous loop that checks for connections requests. If a connection
	// request occurs, call the accept function to handle the request.
	printf("Server: Waiting for a client to connect...\n");
	printf("***Hint: Server is ready...run your client program...***\n");
	// Do some verification...
	while (1)
	{
    		AcceptSocket = SOCKET_ERROR;

      		while (AcceptSocket == SOCKET_ERROR)
       		{
        		AcceptSocket = accept(m_socket, NULL, NULL);
       		}

   		// else, accept the connection...
   		// When the client connection has been accepted, transfer control from the
   		// temporary socket to the original socket and stop checking for new connections.
    		printf("Server: Client Connected!\n");
    		m_socket = AcceptSocket;
    		break;
	}



	while(1){
         	int bytesSent = SOCKET_ERROR;
         	int bytesRecv = SOCKET_ERROR;
         	char sendbuf[100] = "test\n";
         	char recvbuf[100] = "";
         	char boem[] = "quit";

         while(1){
                  printf("Server: Ready to receive! \n");
                  bytesRecv = recv(m_socket, recvbuf, 100, 0);
                  if(bytesRecv != SOCKET_ERROR){
                               if(!strncmp(recvbuf,boem, 4)){
                                       exit(EXIT_SUCCESS);
                               }
                               break;
                  }
         }
         while(1){
                  bytesSent = send(m_socket, sendbuf, 100, 0);
                  if (bytesSent != SOCKET_ERROR){
                                break;
                  }
         }
}


WSACleanup();

 	system("PAUSE");
  	return 0;
}

