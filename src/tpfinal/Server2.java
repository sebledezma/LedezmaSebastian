package tpfinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.GregorianCalendar;

public class Server2 {
	private static int ServerPort = 332; // Server identification
	private static int ClientPort = 331; // Client identification
	private static int NbStep = 1;
	private static void simple() {
		try {
			ServerSocket serveur = new ServerSocket(ServerPort);
			boolean fini=false;
			
			while (!fini) {
				
				Socket socket = serveur.accept();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Object receivedObj = ois.readObject();
				long[] inputData = (long[]) receivedObj;
				int nombre = (int) inputData[1];
				long timeReceived = inputData[0];
				long te = (long) (new GregorianCalendar().getTimeInMillis());
				System.out.println(NbStep + " " + te + " factorielle de " + nombre + " = " + factorielleIterative(nombre));
				long outputData[] = {timeReceived, te};
				ObjectOutputStream oos = new ObjectOutputStream( socket.getOutputStream());
				oos.writeObject(outputData);
				socket.close();
				NbStep++;
				
				if (NbStep > 5000) {
					fini = true;
				}
				
			}
			
			serveur.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		simple();
	}
	public static int factorielleIterative(int nombre) {
        int fact = 1;
        for (int i = 2; i <= nombre; i++) {
            fact *= i;
        }
        return fact;
    }
}
