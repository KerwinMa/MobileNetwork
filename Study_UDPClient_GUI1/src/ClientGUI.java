import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.View;

public class ClientGUI extends Thread
{
	String user_name;
	String server_ip = "127.0.0.1";
	String client_ip;
	int server_port = 12345;
	DatagramSocket socket;

	String chatting_rec;
	JLabel text_area;
	JTextField user_input;
	JButton send_btn;
	JButton target_btn;

	public ClientGUI(String user_name)
	{
		// TODO Auto-generated constructor stub
		this.user_name = user_name;
		try
		{
			socket = new DatagramSocket();
			client_ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e)
		{
		}
		chatting_rec = "";

		JFrame chatting_frame = new JFrame("UDP Client");
		chatting_frame.setLocation(100, 100);
		chatting_frame.setPreferredSize(new Dimension(1150, 600));
		chatting_frame.setResizable(false);
		Container content_pane = chatting_frame.getContentPane();
		init_chatting(content_pane);

		chatting_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatting_frame.pack();
		chatting_frame.setVisible(true);

	}

	public void init_chatting(Container content_pane)
	{
		JPanel ip_panel = new JPanel();
		ip_panel.add(new JLabel("Server IP: " + server_ip));
		ip_panel.add(new JLabel("Client IP: " + client_ip));
		content_pane.add(ip_panel, BorderLayout.NORTH);

		text_area = new JLabel();
		text_area.setHorizontalTextPosition(JLabel.LEFT);
		text_area.setVerticalAlignment(JLabel.TOP);
		JScrollPane rec_scroll = new JScrollPane(text_area);
		content_pane.add(rec_scroll);

		JPanel input_panel = new JPanel();
		user_input = new JTextField();
		user_input.setPreferredSize(new Dimension(880, 30));
		user_input.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						&& !user_input.getText().isEmpty())
					send_message(socket);
			}

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}
		});
		send_btn = new JButton("send");
		send_btn.setPreferredSize(new Dimension(100, 30));
		send_btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				if (!user_input.getText().isEmpty())
					send_message(socket);
			}
		});
		target_btn = new JButton("target");
		target_btn.setPreferredSize(new Dimension(100, 30));
		target_btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				conf_target();
			}
		});

		input_panel.add(user_input);
		input_panel.add(send_btn);
		input_panel.add(target_btn);
		content_pane.add(input_panel, BorderLayout.SOUTH);
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				byte[] b = new byte[100];
				DatagramPacket recv_packet = new DatagramPacket(b, b.length);
				socket.receive(recv_packet);
				String s = new String(recv_packet.getData());
				s = s.trim();
				chatting_rec += (s + "<br>");
				text_area.setText("<html>" + chatting_rec + "</html>");
			} catch (Exception e)
			{
			}
		}
	}

	public void send_message(DatagramSocket socket)
	{
		String temp = user_name + ": " + user_input.getText();
		byte[] buf = temp.getBytes();
		try
		{
			DatagramPacket send_packet = new DatagramPacket(buf, buf.length,
					InetAddress.getByName(server_ip), server_port);
			socket.send(send_packet);
		} catch (Exception e)
		{
		}
		buf = null;
		// chatting_rec = chatting_rec + (user_input.getText()+"<br>");
		// text_area.setText("<html>"+chatting_rec+"</html>");
		user_input.setText("");
	}

	public void conf_target()
	{
		JFrame target_frame = new JFrame("Configure");
		target_frame.setLocation(100, 100);
		target_frame.setPreferredSize(new Dimension(500, 600));
		target_frame.setResizable(false);
		Container content_pane = target_frame.getContentPane();
		
		ArrayList<JCheckBox> iplist = new ArrayList<JCheckBox>();
		iplist.add(new JCheckBox("ip:"+server_ip+"port:"+server_port));
		
		/*JCheckBox iplist = new JCheckBox();
		JCheckBoxMenuItem test = new JCheckBoxMenuItem();
		content_pane.add(iplist);
		content_pane.add(test);*/

		JPanel ip_panel = new JPanel();
		ip_panel.setPreferredSize(new Dimension(200, 500));
		ip_panel.add(iplist.get(0));
		content_pane.add(ip_panel, BorderLayout.NORTH);
		//JPanel = 
		
		
		target_frame.pack();
		target_frame.setVisible(true);
	}
}
