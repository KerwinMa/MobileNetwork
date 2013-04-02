import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendMessageThread implements Runnable
{
	DatagramSocket clientSocket;
	InetAddress inetAddr;
	int port_num;
	BufferedReader reader;

	public SendMessageThread(DatagramSocket clientSocket, InetAddress inetAddr,
			int port_num)
	{
		this.clientSocket = clientSocket;
		this.inetAddr = inetAddr;
		this.port_num = port_num;
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				String s = reader.readLine();

				byte[] sendData = new byte[80];
				sendData = s.getBytes();

				DatagramPacket dataPacket = new DatagramPacket(sendData,
						sendData.length, inetAddr, port_num);
				clientSocket.send(dataPacket);
				// sendData = null;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
