import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class receiveThread implements Runnable {
	
	DatagramSocket socket;
	DatagramPacket dataPacket;
	
	public receiveThread(DatagramSocket serverSocket) {
		socket = serverSocket;
	}

	@Override
	public void run() {
		byte[] buffer;
		try{
			while(true){
				buffer = new byte[80];
				dataPacket = new DatagramPacket(buffer, buffer.length);
				socket.receive(dataPacket);
				String str= new String(dataPacket.getData());
				System.out.println(str);
				buffer = null;
			}
		}catch (Exception e) { System.out.println(e); }
	}

}
