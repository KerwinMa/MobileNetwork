import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client
{
	public static int port_num = 1234;

	public static void main(String[] args) throws Exception
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress inetAddr = InetAddress.getByName("127.0.0.1");
		new Thread(new SendMessageThread(clientSocket, inetAddr, port_num))
				.start();
		new Thread(new ReceiveMessageThread(clientSocket)).start();
	}
}
