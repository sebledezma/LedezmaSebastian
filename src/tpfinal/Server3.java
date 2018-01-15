package tpfinal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.GregorianCalendar;

public class Server3 {
	private static int ServerPort = 333; // Server identification
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
				System.out.println(NbStep + " " + (new GregorianCalendar().getTime()) + " " + (new GregorianCalendar().getTimeInMillis()) + " " + factorielleRecursive(nombre));
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
    public static int factorielleRecursive(int nombre) {
        if (nombre == 0) {
            return 1;
        } else {
            return (nombre * factorielleRecursive(nombre - 1));
        }
    }
}
