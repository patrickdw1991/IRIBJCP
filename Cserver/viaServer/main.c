#include <winsock2.h>
#include <stdio.h>
#include <stdlib.h>
#include <String.h>
#include <process.h>
#include <time.h>


typedef struct
{
       int id;
       char name[40];             //Binairy_X
       int value;                 //0 or 1
       char unit[15];             //ex: N/m2 or C    
       char timestamp[22];        //2012-12-31_12:59:59
       char alarm[20];            //if value is 1, alarm or no alarm if no alarm
}BinSen;


BinSen binary[12];


typedef struct
{
       int id;
       char name[40];             //Analog_X
       int value;                 //anythin
       char unit[15];             //ex: N/m2 or C
       char timestamp[22];        //2012-12-31_12:59:59
       char alarm[20];            //low or high alarm msg
       int low;                   //low alarm limit                            
       int high;                  //high alarm limit
       char lowAlarm[20];         //low alarm message
       char highAlarm[20];        //high alarm message
}AnaSen;


AnaSen analog[12];



void sendSocket();
void receiveSocket();
SOCKET acceptSocket(SOCKET);
char *createBinaryMsg(int);
char *createAnalogMsg(int);
char *timestamp();
int initializeDatabase();


int main()
{

    
    initializeDatabase();
              
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
	
	

    //_beginthread( sendSocket, 0, &m_socket);
    //_beginthread( receiveSocket, 0, &m_socket);
    
    while(1){
        m_socket = acceptSocket(m_socket);
		sendSocket(&m_socket);
        sleep(100);         
    }


	WSACleanup();

 	system("PAUSE");
  	return 0;
}

void sendSocket(SOCKET *m_socket){
     //Handshake
	while(1){
		//char *p1;
		char sendBuff[100] = "shakeStart\n";
		char recvbuf[100] = "";
		//p1 = createValueString();
		int bytesSent = SOCKET_ERROR;
		int bytesRecv = SOCKET_ERROR;
		bytesSent = send(*m_socket, sendBuff, 100, 0);
        if (bytesSent != SOCKET_ERROR){                      
			bytesRecv = recv(*m_socket, recvbuf, 100, 0);
            printf("%s", recvbuf);
            break;
        }
		sleep(100);
    }
    //Sensor send
    while(1){
        int i;     
        //Analog Sensors
        for(i = 0; i<10; i++){
			char *p1;
			char recvbuf[100] = "";
			p1 = createAnalogMsg(i);
			int bytesSent = SOCKET_ERROR;
			int bytesRecv = SOCKET_ERROR;
			bytesSent = send(*m_socket, p1, 100, 0);
			if (bytesSent != SOCKET_ERROR){                      
				bytesRecv = recv(*m_socket, recvbuf, 100, 0);
				//printf("%s", recvbuf);
			}else{
                printf("client disconnected");  
                exit(EXIT_SUCCESS);
            }
        }
        //Binary Sensors
        for(i = 0; i<10; i++){
			char *p1;
			char recvbuf[100] = "";
			p1 = createBinaryMsg(i);
			int bytesSent = SOCKET_ERROR;
			int bytesRecv = SOCKET_ERROR;
			bytesSent = send(*m_socket, p1, 100, 0);
			if (bytesSent != SOCKET_ERROR){                      
				bytesRecv = recv(*m_socket, recvbuf, 100, 0);
			}else{
                printf("client disconnected");  
                exit(EXIT_SUCCESS);
            }
        }		
		sleep(100);     
                      
    }
}

void receiveSocket(SOCKET *m_socket){
     while(1){
        int bytesRecv = SOCKET_ERROR;
		char recvbuf[100] = "";
		char quit[] = "quit";
		bytesRecv = recv(*m_socket, recvbuf, 100, 0);
		if(bytesRecv != SOCKET_ERROR){
			//Check for a certain message
			if(!strncmp(recvbuf,quit, 4)){
				exit(EXIT_SUCCESS);
			}else{
				printf("Unrecognized command: %s \n", recvbuf);
			}
		}
		sleep(100);         
     }    
}

SOCKET acceptSocket(SOCKET m_socket){
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
    	
    	return AcceptSocket;
	}    
}

//Create a char array containing all the data of a binary sensor
//ex: B;Sensor1;0;C;No Alarm\n
char *createBinaryMsg(int sensor){
	char temp[100] = "B;";
	char buffer[10];
	char alarm[20];
	char *tStamp;
    tStamp = timestamp();
	
	strcat(temp, binary[sensor].name);
	strcat(temp, ";");
	
	binary[sensor].value = (float)rand()/((float)RAND_MAX/1);
    itoa(binary[sensor].value, buffer, 10);
    strcat(temp, buffer);
    strcat(temp, ";");
    
    strcat(temp, tStamp);
    strcat(temp, ";");
    
    strcat(temp, binary[sensor].unit);
    strcat(temp, ";");
    
    if(binary[sensor].value == 1){
          strcpy(alarm, binary[sensor].alarm);
    }else{
          strcpy(alarm, "No Alarm");      
    }
    strcat(temp, alarm);
    
    strcat(temp, "\n");

	static char stringBuffer[100];
	strcpy(stringBuffer, temp);
    
	return stringBuffer;
}

//Create a char array containing all the data of a analog sensor
//ex: A;Sensor1;10;C;lowAlarm;lowerLimit;highLimit;lowAlarmMsg;highAlarmMsg\n
char *createAnalogMsg(int sensor){
	char temp[100] = "A;";
	char buffer[10];
	char alarm[20];
    char *tStamp;
    tStamp = timestamp();
    
	strcat(temp, analog[sensor].name);
	strcat(temp, ";");
	
	analog[sensor].value = (float)rand()/((float)RAND_MAX/10);
    itoa(analog[sensor].value, buffer, 10);
    strcat(temp, buffer);
    strcat(temp, ";");
    
    strcat(temp, analog[sensor].unit);
    strcat(temp, ";");
    
    strcat(temp, tStamp);
    strcat(temp, ";");
    
    if(analog[sensor].value <= analog[sensor].low){
         strcpy(alarm, analog[sensor].lowAlarm);                  
    }else if(analog[sensor].value >= analog[sensor].high){
         strcpy(alarm, analog[sensor].highAlarm);
    }
    strcat(temp, alarm);
    strcat(temp, ";");
    
    itoa(analog[sensor].low, buffer, 10);
    strcat(temp, buffer);
    strcat(temp, ";");
    
    itoa(analog[sensor].high, buffer, 10);
    strcat(temp, buffer);
    strcat(temp, ";");
    
    strcat(temp, analog[sensor].lowAlarm);
    strcat(temp, ";");
    
    strcat(temp, analog[sensor].highAlarm);
    strcat(temp, "\n");

	static char stringBuffer[100];
	strcpy(stringBuffer, temp);
    
	return stringBuffer;
}

int initializeDatabase()
{
      //Initialise Binary data
      int i;
      char binPrefix[12] = "Binary_";
      for(i = 0; i < 12; i++){
              
              binary[i].id = i;             
              
              char buffer[10];
              itoa(i, buffer, 10);
              strcat(binPrefix, buffer);
              strcpy(binary[i].name, binPrefix);
              
              strcpy(binary[i].unit, "Celcius");             
              
              strcpy(binary[i].alarm, "Nuclear Meltdown");
              
              //EXAMPLE: LO + (float)rand()/((float)RAND_MAX/(HI-LO));
                            
      }
      
      //Initialise Analog data
      int j;
      char anaPrefix[12] = "Analog_";
      for(j = 0; j < 12; j++){
              
              analog[j].id = j;             
              
              char buffer[10];
              itoa(j, buffer, 10);
              strcat(anaPrefix, buffer);
              strcpy(binary[i].name, anaPrefix);
              
              strcpy(binary[i].unit, "Celcius");       
              
              //TODO: Timestamp 
              
              analog[j].low = 0;
              analog[j].high = 8;
              
              strcpy(analog[j].lowAlarm, "Empty vessel");
              strcpy(analog[j].highAlarm, "Overflowing vessel");
              
              
              
      }
}

char *timestamp()
{
    time_t ltime;
    struct tm *Tm;
 
    ltime=time(NULL);
    Tm=localtime(&ltime);
    
    
    char time[30] = "";
    char buffer[10];
    
    itoa(Tm->tm_mday, buffer, 10);
    strcpy(time, buffer);
    
    strcat(time, "-");
    
    itoa((Tm->tm_mon +1), buffer, 10);
    strcat(time, buffer);
    
    strcat(time, "-");
    
    itoa((Tm->tm_year + 1900), buffer, 10);
    strcat(time, buffer);
    
    strcat(time, " ");
    
    itoa(Tm->tm_hour, buffer, 10);
    strcat(time, buffer);
    
    strcat(time, ":");
    
    itoa(Tm->tm_min, buffer, 10);
    strcat(time, buffer);
    
    strcat(time, ":");
    
    itoa(Tm->tm_sec, buffer, 10);
    strcat(time, buffer);
    
    //strcat(time, ":");
    
    //TODO: milliseconds
    //itoa(Tm->tm_msec, buffer, 10);
    //strcat(time, buffer);
    
    printf(time);
    
}
