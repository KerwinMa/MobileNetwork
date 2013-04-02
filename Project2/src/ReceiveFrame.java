import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveFrame implements Runnable
{
	DatagramSocket socket;
	DatagramPacket send_packet;

	public ReceiveFrame(DatagramSocket socket, DatagramPacket send_packet) throws Exception
	{
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.send_packet = send_packet;
		
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			socket.send(send_packet);
			
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
