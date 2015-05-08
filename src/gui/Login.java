package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Component;








import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

import library.ctrl.LibraryController;
import model.LibraryException;
import model.Subscriber;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private final  LibraryController ctrl;
	private final Integer type;
	private JFrame parinte;


	/**
	 * Create the frame.
	 */
	public Login(JFrame par, LibraryController c, Integer t) {
		super("Login");
		this.parinte = par;
		this.ctrl = c;
		this.type = t;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 287, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(1, 1));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 7, 59, 16);
		panel.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(92, 5, 114, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(29, 39, 58, 16);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField(10);
		passwordField.setBounds(92, 35, 114, 20);
		passwordField.setBackground(UIManager.getColor("Button.light"));
		panel.add(passwordField);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parinte.setVisible(true);
				dispose();
			}
		});
		btnExit.setBounds(28, 67, 65, 26);
		panel.add(btnExit);
		
		JButton btnLogIn = new JButton("Login");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Boolean check = false;
				Subscriber s = ctrl.checkLogin(textField.getText(), new String(passwordField.getPassword()), check);
				if(s!= null)
				{//TODO
				switch(type){
				case 0:
					 try {
				        	
								SubscriberGUI2 dialog = new SubscriberGUI2 (ctrl, s);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
				        	
						} catch (Exception eex) {
							eex.printStackTrace();
						}
					break;
				case 1:
					try {
						BookKeeperGUI frame = new BookKeeperGUI(ctrl);
						frame.setVisible(true);
					} catch (Exception exc) {
						exc.printStackTrace();
					}
					
					break;
				case 2:
					Subscriber admin = ctrl.getAdministrator(s);
					if(admin != null)
						try {
							AdministratorGUI frame = new AdministratorGUI(Login.this, ctrl);
							frame.setVisible(true);
							dispose();
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						
					break;
				default:
					break;
				}
				}
				else
				{
					passwordField.setText("");
					JOptionPane.showMessageDialog(Login.this,"Username or password have been incorrect!", "Error at Login", JOptionPane.ERROR_MESSAGE);
				}
			}catch(LibraryException ex)
			{
				JOptionPane.showMessageDialog(Login.this,ex.getMessage(), "Error at Login", JOptionPane.ERROR_MESSAGE);
			} catch (RemoteException ex) {
				JOptionPane.showMessageDialog(Login.this,ex.getMessage(), "Error at Login", JOptionPane.ERROR_MESSAGE);
			}
			
			}
		});
		btnLogIn.setBounds(141, 67, 65, 26);
		btnLogIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnLogIn);
	}

}
