package tpfinal;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.GregorianCalendar;

public class Server1 {
	private static int ServerPort = 331; // Server identification
	private static int ClientPort = 331; // Client identification
	private static int NbStep = 1;
	private static void simple() {
		try {
			DatagramSocket ds = new DatagramSocket(ServerPort);
			boolean fini=false;
			while (!fini) {
				DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
				ds.receive(packet);
				int nombre = Integer.parseInt(new String (packet.getData(), 0, packet.getLength()));
				long te = (long) (new GregorianCalendar().getTimeInMillis());
				System.out.println(NbStep + " " + te + " " + factorielleIterative(nombre));
				NbStep++;
				if (NbStep > 5000) {
					fini = true;
				}
			}
			ds.close();
			Socket socket= new Socket(InetAddress.getLocalHost(), ClientPort);
			ObjectOutputStream oos = new ObjectOutputStream( socket.getOutputStream());
			oos.writeObject("EOE");
			socket.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		simple();
	}
	public static long factorielleIterative(int nombre) {
	        int fact = 1;
	        for (int i = 2; i <= nombre; i++) {
	            fact *= i;
	        }
	        return fact;
	    }
}