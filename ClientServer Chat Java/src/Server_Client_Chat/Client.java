package Server_Client_Chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//Client class
public class Client {
	ServerListener sL;
	Socket serverSocket;
	String name;
	
	//Client method
	Client(){
		
		System.out.println("Enter your Name: (Type in your name, then press Enter)");
		//Connect to server 
		Scanner in = new Scanner (System.in);
		name = in.next();
		
		try{
			System.out.println("Connecting");
		serverSocket = new Socket("localhost",4444);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println(name + " Connected!");
		System.out.println("Type a message to send to other clients");
		
		String clientMessage = in.nextLine();
		
		//object to listen to server
		sL = new ServerListener(this, serverSocket);
		new Thread(sL).start();
		
		PrintWriter out;
		
		try {
		out = new PrintWriter(new BufferedOutputStream(serverSocket.getOutputStream()));
		//send name to server
		out.println(name);
		out.flush();
		//Keep sending messages to server 
		while(in.hasNextLine()){
		clientMessage = in.nextLine();
		out.println(clientMessage);
		out.flush();
		}
		//close both scanners to avoid leak
		out.close();
		in.close();
		
		
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		Client cl = new Client();
	}
}
//Gets data from server
class ServerListener implements Runnable {
	Client cl;
	Scanner in;
	
	ServerListener(Client cl,Socket socket){
		try {
			this.cl = cl;
			in = new Scanner(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	//Receive other clients messages from server and print
	@Override
	public void run() {
		
		while(in.hasNextLine()){
			String clientMessage = in.nextLine();
			System.out.println(clientMessage);
		}
			}
		}	