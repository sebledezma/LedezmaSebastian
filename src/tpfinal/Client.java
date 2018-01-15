package tpfinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;

public class Client {
	private static int ClientPort = 330; // Client identification
	private static int ServerPort1 = 331; // Server1 identification
	private static int ServerPort2 = 332; // Server2 identification
	private static int ServerPort3 = 333; // Server3 identification
	private static int ServerPortStat = 334; // ServerStat identification
	private static int ServerPort = (int)(Math.random() * 3 + ServerPort1);
	private static int Nombre = (int)(Math.random() * 15 + 1);
	private static int NbStep = 1; // Features of msg to send
	private static void simple() {
		try {
			DatagramSocket ds = new DatagramSocket();
			int debut =  (int) new GregorianCalendar().getTimeInMillis();
			InetAddress addr;
			addr = InetAddress.getByName("localhost");
			do {
				String message = "" + Nombre;
				byte[] data = message.getBytes();
				DatagramPacket packet = new DatagramPacket (data, data.length, addr, ServerPort1);
				ds.send(packet);
				NbStep++;
			} while (NbStep <= 5000);
			ds.close();
			ServerSocket serveur= new ServerSocket(ClientPort);
			Socket socket = serveur.accept();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Object obj = ois.readObject();
			if (obj == "EOE") {
				System.out.println("Task finished !");
				serveur.close();
			} else {
				System.out.println("Task Error !");
				serveur.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		simple();
	}
}
