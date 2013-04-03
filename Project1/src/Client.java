import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client
{
	static String ip = "127.0.0.1";
	static int server_port = 1234;

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		DatagramSocket clientSocket = new DatagramSocket();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			String sentence = br.readLine();
			byte[] buf = sentence.getBytes();
			InetAddress IPAddress = InetAddress.getByName(ip);
			DatagramPacket sendPacket = new DatagramPacket(buf, buf.length,
					IPAddress, server_port);
			clientSocket.send(sendPacket);
			byte[] recv_buf = new byte[1024];
			DatagramPacket recvPacket = new DatagramPacket(recv_buf,
					recv_buf.length);
			clientSocket.receive(recvPacket);
			System.out.print(new String(recvPacket.getData(), 0, recvPacket.getLength()));
			System.out.println("\tfrom ip : " + recvPacket.getAddress() + " port : " + recvPacket.getPort());
		}
	}
}
