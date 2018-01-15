package tpfinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStat {
	private static int ServerPort = 334; // Server identification
	private static int ClientPort = 331; // Client identification
	private static int MsgSize = 5;
	private static int NbStep = 1;
	
	private static int TimeStartClient = 0;
	private static int TimeServerStart = 0;
	//private static int 
	
	private static void simple() {
		try {
			ServerSocket serveur= new ServerSocket(ServerPort);
			boolean fini=false;
			Socket socket = serveur.accept();
			while (!fini) {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Object obj = ois.readObject();
			}
			socket.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		simple();
	}
}
