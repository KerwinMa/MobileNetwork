import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Server
{
	private static ArrayList<ClientClass> client_list;
	private static int port_num = 12345;

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		client_list = new ArrayList<ClientClass>();
		DatagramSocket server_socket = new DatagramSocket(port_num);
		DatagramPacket recv_packet, send_packet;

		while (true)
		{
			byte[] b = new byte[100];
			recv_packet = new DatagramPacket(b, b.length);
			server_socket.receive(recv_packet);
			ClientClass temp = new ClientClass(recv_packet.getAddress(),
					recv_packet.getPort());
			boolean exist = CheckExistence(client_list, recv_packet);

			if (exist == false)
				client_list.add(temp);

			for (ClientClass client : client_list)
			{
				if(temp.port_num != client.port_num)
				{
					send_packet = new DatagramPacket(b, b.length,
							client.IP_address, client.port_num);
					server_socket.send(send_packet);
				}
			}
			b = null;
		}
	}

	public static boolean CheckExistence(ArrayList<ClientClass> clients,
			DatagramPacket packet)
	{
		if (!clients.isEmpty())
		{
			for (ClientClass client : clients)
			{
				System.out.println(client_list.size() + ": "
						+ client.IP_address + ", " + client.port_num);
				if (client.IP_address.equals(packet.getAddress())
						&& client.port_num == packet.getPort())
				{
					return true;
				}
			}
		}
		return false;
	}
}
