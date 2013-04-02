import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ReceiveMessageThread implements Runnable {
	DatagramSocket clientSocket;
	
	public ReceiveMessageThread(DatagramSocket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				byte[] recvByte = new byte[80];
				DatagramPacket dataPacket = new DatagramPacket(recvByte, recvByte.length);
				clientSocket.receive(dataPacket);
				System.out.println(new String(dataPacket.getData()));
				recvByte = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
