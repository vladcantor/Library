package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import library.ctrl.LibraryController;

public class StartApp extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Integer type;
	private LibraryController ctrl;
	


	/**
	 * Create the frame.
	 */
	public StartApp(LibraryController c) {
		super("Start Application");
		this.type = -1;
		this.ctrl = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JRadioButton rdbtnSubscriber = new JRadioButton("Subscriber");
		rdbtnSubscriber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				type = 0;
			}
		});
		buttonGroup.add(rdbtnSubscriber);
		rdbtnSubscriber.setBounds(70, 17, 109, 23);
		panel.add(rdbtnSubscriber);
		
		JRadioButton rdbtnBookkeeper = new JRadioButton("BookKeeper");
		rdbtnBookkeeper.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				type = 1;
			}
		});
		buttonGroup.add(rdbtnBookkeeper);
		rdbtnBookkeeper.setBounds(70, 43, 109, 23);
		panel.add(rdbtnBookkeeper);
		
		JRadioButton rdbtnAdministrator = new JRadioButton("Administrator");
		rdbtnAdministrator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				type = 2;
			}
		});
		buttonGroup.add(rdbtnAdministrator);
		rdbtnAdministrator.setBounds(70, 69, 109, 23);
		panel.add(rdbtnAdministrator);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		btnExit.setBounds(55, 99, 89, 23);
		panel.add(btnExit);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(154, 99, 89, 23);
		panel.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(type != -1)
				{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Login frame = new Login(StartApp.this, ctrl, type);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(StartApp.this,"You have to pick one of the options before clicking on Next!", "Error at StartApplication", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
}
