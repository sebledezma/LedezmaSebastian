package tpfinal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class ServerStat {
	private static int NbStep = 1;
	private static int ServerPort = 334; // Server identification
	private static int ClientPort = 331; // Client identification
	private static int[] milli1 = {0,0,0};
	private static int[] milli2 = {0,0,0};
	private static int[] milli3 = {0,0,0};
	private static void simple() {
		try {
//			Scanner scan = new Scanner (System.in);
			DatagramSocket ds = new DatagramSocket(ServerPort);
			boolean fini=false;
			int[] milli1 = {0,0,0};
			int[] milli2 = {0,0,0};
			int[] milli3 = {0,0,0};
			do {
				do {
					DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
					ds.receive(packet);
					byte[] data = packet.getData();
					String stat = new String(data);
					stat = stat.substring(0, stat.indexOf("/e/"));
					String[] parts = stat.split(" ");
					Integer milli = Integer.valueOf(parts[0]);
					Integer server = Integer.valueOf(parts[1]);
					System.out.println("Server: " + server + " took " + milli + " millisecond to process.");
//					resume(milli, server);
				} while (NbStep <= 5000);
//				System.out.print("Press Enter to continue...");
//				String choice = scan.next();
//				if (choice == "") {
//					break;
//				} else {
//					fini = true;
//					System.out.println("Server: {0 milli, 1 milli, >1 milli}");
//					System.out.println("1: {" + milli1[0] + ", " + milli1[1] + ", " + milli1[2] + "}");
//					System.out.println("2: {" + milli2[0] + ", " + milli2[1] + ", " + milli2[2] + "}");
//					System.out.println("3: {" + milli3[0] + ", " + milli3[1] + ", " + milli3[2] + "}");
//				}
			} while (!fini);
			ds.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void resume(int milli, int server) {
		Thread thread = new Thread("Processing") {
			public void run() {
				switch (server) {
				case 1:
					if (milli == 0) {
						milli1[0]++;
					} else if (milli == 1) {
						milli1[1]++;
					} else {
						milli1[2]++;
					}
					break;
				case 2:
					if (milli == 0) {
						milli2[0]++;
					} else if (milli == 1) {
						milli2[1]++;
					} else {
						milli2[2]++;
					}
					break;
				case 3:
					if (milli == 0) {
						milli3[0]++;
					} else if (milli == 1) {
						milli3[1]++;
					} else {
						milli3[2]++;
					}
					break;

				default:
					break;
				}
			}
		};
		thread.start();
	}
	
	public static void main(String[] args) {
		simple();
	}
}
