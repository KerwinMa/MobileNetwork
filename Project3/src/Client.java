import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client {
	public static String user_name;
	public static String IP_addr = "127.0.0.1";
	public static int port_num = 12345;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress inetAddr = InetAddress.getByName(IP_addr);
		
		System.out.print("Input user name: ");
		user_name = input.readLine();
		String sentence = "Connect from "+user_name;
		
		byte[] sendData = new byte[80];
		sendData = sentence.getBytes();
		
		DatagramPacket sendPacket = 
			new DatagramPacket(sendData, sendData.length, inetAddr, port_num);
		clientSocket.send(sendPacket);
		
		new Thread(new SendMessageThread
				(user_name, clientSocket, inetAddr, port_num)).start();
		new Thread(new ReceiveMessageThread(clientSocket)).start();
	}
}
