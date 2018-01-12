package tpfinal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;

public class Client {
	private static int ServerPortStat = 334; // Server1 identification
	private static int ServerPort1 = 331; // Server1 identification
	private static int ServerPort2 = 332; // Server2 identification
	private static int ServerPort3 = 333; // Server3 identification
	private static int ServerPort = (int)(Math.random() * 3 + ServerPort1); // Server3 identification
	private static int ClientPort = 330; // Client identification
	private static String ServerName = null;
	private static int NbStep = 1; // Features of msg to send
	private static int MsgSize = 5;
	private static void simple() {
		try {
			System.out.println(new GregorianCalendar().getTimeInMillis());
			do {
				String message = "" + NbStep;
				byte[] data = message.getBytes();
				InetAddress addr;
				addr = InetAddress.getByName("localhost");
				DatagramPacket packet = new DatagramPacket (data, data.length, addr, ServerPort);
				DatagramSocket ds = new DatagramSocket();
				ds.send(packet);
				ds.close();
				NbStep++;
			} while (NbStep <= 5000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		simple();
	}
}
