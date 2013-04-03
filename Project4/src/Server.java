import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server {
	public static final int port_num = 12345;
	public static String user_name;
	
	public static void main(String[] args) throws Exception {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input user name: ");
		user_name = br.readLine();
		*/
		DatagramSocket serverSocket = new DatagramSocket(port_num);
		System.out.println("-----Datagram server socket created-----");
	
		byte[] recvData = new byte[80];
		
		DatagramPacket dataPacket = new DatagramPacket(recvData, recvData.length);
		serverSocket.receive(dataPacket);
		LoginGUI login_gui = new LoginGUI(serverSocket);
		/*System.out.println(new String(dataPacket.getData()));
		recvData = null;
*/
		new Thread(new receiveThread(serverSocket)).start();
		new Thread(new sendThread(serverSocket, dataPacket, user_name)).start();
	}
}
