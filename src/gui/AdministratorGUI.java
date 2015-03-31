package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import library.ctrl.LibraryController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministratorGUI extends JFrame {

	private JPanel contentPane;
	private Integer pick;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private LibraryController ctrl;
	private JFrame parinte;




	/**
	 * Create the frame.
	 */
	public AdministratorGUI(JFrame par, LibraryController c) {
		super("Administrator");
		this.parinte = par;
		this.ctrl = c;
		pick = -1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnAddSubscriber = new JRadioButton("Add Subscriber");
		rdbtnAddSubscriber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pick = 0;
			}
		});
		buttonGroup.add(rdbtnAddSubscriber);
		rdbtnAddSubscriber.setBounds(79, 7, 139, 23);
		contentPane.add(rdbtnAddSubscriber);
		
		JRadioButton rdbtnAllowSubscriberRent = new JRadioButton("Allow Renting");
		rdbtnAllowSubscriberRent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pick = 2;
			}
		});
		buttonGroup.add(rdbtnAllowSubscriberRent);
		rdbtnAllowSubscriberRent.setBounds(79, 37, 139, 23);
		contentPane.add(rdbtnAllowSubscriberRent);
		
		JRadioButton rdbtnResignSubs = new JRadioButton("Resign Renting\r\n");
		rdbtnResignSubs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pick = 3;
			}
		});
		buttonGroup.add(rdbtnResignSubs);
		rdbtnResignSubs.setBounds(79, 63, 139, 23);
		contentPane.add(rdbtnResignSubs);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (pick){
				case 0:
					 AddSubscriber addWin=new AddSubscriber(AdministratorGUI.this, ctrl);
		                addWin.setSize(200,200);
		                addWin.setLocation(175,175);
		                addWin.setVisible(true);
					break;
				case 2:
					AllowSubscriber alls = new AllowSubscriber(ctrl, AdministratorGUI.this);
					alls.setVisible(true);
					break;
				case 3:
					ResignRenting rr = new ResignRenting(ctrl, AdministratorGUI.this);
					rr.setVisible(true);
					break;
				case 4:
					UnReserveGUI unRes = new UnReserveGUI(ctrl, parinte);
					unRes.setVisible(true);
					break;
				default:
					JOptionPane.showMessageDialog(AdministratorGUI.this,"You have to pick one of the options before clicking on Next!", "Error at Administrator", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		});
		
		JRadioButton rdbtnUnReserveBook = new JRadioButton("Un Reserve Book");
		buttonGroup.add(rdbtnUnReserveBook);
		rdbtnUnReserveBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pick = 4;
			}
		});
		rdbtnUnReserveBook.setBounds(79, 89, 139, 23);
		contentPane.add(rdbtnUnReserveBook);
		btnNext.setBounds(25, 152, 89, 23);
		contentPane.add(btnNext);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parinte.setVisible(true);
				dispose();
			}
		});
		btnExit.setBounds(175, 152, 89, 23);
		contentPane.add(btnExit);
		
	}
}
