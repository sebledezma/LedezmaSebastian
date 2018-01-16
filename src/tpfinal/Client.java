package tpfinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.GregorianCalendar;

public class Client {
	private static int ClientPort = 330; // Client identification
	private static int ServerPort1 = 331; // Server1 identification
	private static int ServerPort2 = 332; // Server2 identification
	private static int ServerPort3 = 333; // Server3 identification
	private static int ServerPortStat = 334; // ServerStat identification
	private static int ServerPort = (int)(Math.random() * 3 + ServerPort1);
	private static int ServerName;
	private static int Nombre = (int)(Math.random() * 15 + 1);
	private static int NbStep = 1; // Features of msg to send
	private static void simple() {
		try {

			DatagramSocket ds = new DatagramSocket();
			
			do {
				Socket socket= new Socket(InetAddress.getLocalHost(), ServerPort);
				ObjectOutputStream oos = new ObjectOutputStream( socket.getOutputStream());
				long timeSend = (long) (new GregorianCalendar().getTimeInMillis());
				long data[] = {timeSend, Nombre};
				oos.writeObject(data);
				ObjectInputStream ois =	new ObjectInputStream( socket.getInputStream());
				Object receivedObj = ois.readObject();
				long[] inputData = (long[]) receivedObj;
				long timeServer = inputData[1];
				System.out.println(timeSend + " " + timeServer);
				socket.close();
				int dt = (int) (timeServer - timeSend);
				String deltaTime = String.valueOf(dt);
				switch (ServerPort) {
				case 331:
					ServerName = 1;
					break;
				case 332:
					ServerName = 2;
					break;
				case 333:
					ServerName = 3;
					break;
				default:
					break;
				}
				String messageToStat = deltaTime + " " + ServerName + "/e/";
				byte[] dataToStat = messageToStat.getBytes();
				DatagramPacket packet = new DatagramPacket (dataToStat, dataToStat.length, InetAddress.getByName("localhost"),ServerPortStat);
				ds.send(packet);
				NbStep++;
				
			} while (NbStep <= 5000);
			
			ds.close();
			
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
