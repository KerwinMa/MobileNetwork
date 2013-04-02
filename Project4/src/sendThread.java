import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class sendThread implements Runnable {
	DatagramSocket serverSocket;
	DatagramPacket dataPacket;
	String user_name;
	
	public sendThread(DatagramSocket serverSocket, DatagramPacket dataPacket, String user_name) {
		this.serverSocket = serverSocket;
		this.dataPacket = dataPacket;
		this.user_name = user_name;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DatagramPacket send_packet;
		try{
			while(true){
				String str = br.readLine();
				str = user_name+ ": " + str;
				byte[] buffer = str.getBytes();
				send_packet = new DatagramPacket(buffer, buffer.length, 
						dataPacket.getAddress(), dataPacket.getPort());
				serverSocket.send(send_packet);
				System.out.println(str);
				buffer = null;
			}
		} catch (Exception e) { System.out.println(e); }
	}

}
