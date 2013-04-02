import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server
{
	final static int MAXBUFFER = 1048; // max packet size (LLC control�� ���� ���߿� �����ؾ� ��)
	static String save="";
	
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		if (args.length != 2)
		{
			System.out.println("����: java Server localhost port");
			System.exit(0);
		}
		int arg_port = Integer.parseInt(args[1]);
		DatagramPacket recv_packet;
		DatagramSocket socket = new DatagramSocket(arg_port);
		byte buffer[] = new byte[MAXBUFFER];
		recv_packet = new DatagramPacket(buffer, buffer.length);
		
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;

		System.out.println("Running the UDP Echo Server...");
		
		new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				try
				{
					save += reader.readLine();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
		
		while(true)
		{
			socket.receive(recv_packet); // ������ ����
			
			DatagramPacket send_packet = new DatagramPacket(
					recv_packet.getData(), recv_packet.getLength(),
					recv_packet.getAddress(), recv_packet.getPort()); // ����
			
			new Thread(new ReceiveFrame(socket, send_packet)).start();
		}
	}
}
