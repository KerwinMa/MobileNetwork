import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server
{
	static int server_port = 1234;

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = new DatagramSocket(server_port);
		byte[] buf;
		byte[] sendData;
		while (true)
		{
			buf = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			System.out.println(capitalizedSentence);
			System.out.println("ip : " + receivePacket.getAddress() + " port : " + receivePacket.getPort());
			sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}
