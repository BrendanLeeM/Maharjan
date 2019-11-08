package Server_Client_Chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/* My Server class creates a socket and infinitely connects to clients. The server can hold as many clients as it wants
   but for simplicity I have hard coded the max to be 10. It first connects to clients through sockets, then it starts 
   threads to handle each client */ 
public class Server {
	static ArrayList <ClientHandler> clientList = new ArrayList <ClientHandler> (10);
	/*My main method is used to connect to clients and start the client threads. It is also worth mentioning I have 
	 * instantiated an arrayList of type ClientHandler. This holds a list of my clients and allows me to access their data 
	 * fields whenever I need. Primarily I use it to access the PrintWriter out field. */
	public static void main(String[] args) throws IOException{
		//Create server Socket
		ServerSocket serverSocket = null;
		int numClients = 0;
		try {
		serverSocket = new ServerSocket(4444);
		
		System.out.println(serverSocket);
		} catch (IOException e) {
				System.out.println("Port 4444 in use");
				System.exit(-1);
		}
		//Connect to clients
		while(true) {
			Socket clientSocket = null;
			try {
				clientSocket = null;
				System.out.println("Accepting clients to connect");
				clientSocket = serverSocket.accept();
				
				System.out.println("Connected to client number: " + ++numClients);
				
				//Create PrintWriter object here so we can pass as an argument to use later
				PrintWriter out = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
				ClientHandler handler = new ClientHandler(clientSocket, numClients,out);
				//add client to arrayList
				clientList.add(handler);
				//Create a thread for client
				Thread t = new Thread(handler);
				t.start();
			} catch (IOException e) {
				System.out.println("Connection failed on port: 4444");
			}
		}
		
		
	}
}
//Class to handle each clients request
class ClientHandler implements Runnable {
	Socket socket;
	int clientNum;
	String clientName;
	PrintWriter out;
	//Static variable to share between clients
	static String sendMessage;
	
	ClientHandler(Socket socket, int clientNum, PrintWriter out){
		this.socket = socket;
		this.clientNum = clientNum;
		this.out = out;
	}

public void run() {
	Scanner in;

	try {
	//Read in from current client and send information to handleRequest method
		in = new Scanner(new BufferedInputStream(socket.getInputStream()));
		
			String name = in.nextLine();
			System.out.println(name + " has joined chat");
			
			while(in.hasNextLine()){
					sendMessage = in.nextLine();
					handleRequest(name,sendMessage,clientNum);
				}
		
	}catch (IOException e){
		e.printStackTrace();
	}
}

void handleRequest(String clientName, String sendMessage,int numClients){
		
	
		//Print message to server console
		System.out.println(clientName + ": " + sendMessage);
		//Send information to all client sockets
		try {
			for(int i = 0; i != Server.clientList.size();i++){
				if(Server.clientList.get(i).socket != this.socket){
			out = new PrintWriter(new BufferedOutputStream(Server.clientList.get(i).socket.getOutputStream()));
			out.println(clientName + ": " + sendMessage);
			out.flush();
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
}
}