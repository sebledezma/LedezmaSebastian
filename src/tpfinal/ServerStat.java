package tpfinal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerStat {
	private static int ServerPort = 334; // Server identification
	private static int ClientPort = 331; // Client identification
	private static int MsgSize = 5;
	private static int NbStep = 1;
	private static void simple() {
		try {
			DatagramSocket ds = new DatagramSocket(ServerPort);
			boolean fini=false;
			while (!fini) {
				DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
				ds.receive(packet);
				System.out.println("Message: " + new String (packet.getData(), 0, packet.getLength()));
				NbStep++;
				if (NbStep > 5000) {
					fini = true;
				}
			}
			ds.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		simple();
	}
}
