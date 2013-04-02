import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Study_UDPClient_GUI1 {
	static String user_name;
	static JButton btn;
	static JFrame login_frame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		login_frame = new JFrame("login");
		login_frame.setPreferredSize(new Dimension(300, 80));
		login_frame.setLocation(500, 400);
		login_frame.setResizable(false);
		Container content_pane = login_frame.getContentPane();
		init_login(content_pane);
		
		login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login_frame.pack();
		login_frame.setVisible(true);
	}
	
	public static void init_login(Container content_pane){

		JPanel panel_west = new JPanel();
		panel_west.setLayout(new GridLayout(2, 1));
		
		JPanel panel_center = new JPanel();
		panel_center.setLayout(new GridLayout(2, 1));
		
		final JTextField edit_id = new JTextField();
		JTextField edit_passw = new JTextField();
		JLabel label_id = new JLabel(" id: ");
		JLabel label_passw = new JLabel(" password:  ");
		btn = new JButton("login");
		edit_id.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER && !edit_id.getText().isEmpty())
					login_success(edit_id);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		edit_passw.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER && !edit_id.getText().isEmpty())
					login_success(edit_id);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!edit_id.getText().isEmpty()){
					login_success(edit_id);
				}
			}
		});
		
		panel_west.add(label_id); 		panel_center.add(edit_id);
		panel_west.add(label_passw); 	panel_center.add(edit_passw);
		
		content_pane.add(panel_west, BorderLayout.WEST);
		content_pane.add(panel_center, BorderLayout.CENTER);
		content_pane.add(btn, BorderLayout.EAST);
	}

	public static void login_success(JTextField edit_id){
		user_name = edit_id.getText();
		login_frame.setVisible(false);
		ClientGUI client_gui = new ClientGUI(user_name);
		client_gui.start();
	}
}
