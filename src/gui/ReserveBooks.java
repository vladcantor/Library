package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import library.ctrl.LibraryController;
import model.LibraryException;
import model.Subscriber;

import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

import java.awt.Point;
import java.rmi.RemoteException;

public class ReserveBooks extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBook;
	private JTextField textFieldSubscriber;

	private LibraryController ctrl;
	private Subscriber subs;
	private JFrame parinte;
	private JTable tableReserve;

	/**
	 * Create the frame.
	 */
	public ReserveBooks(LibraryController c, Subscriber s, JFrame parent) {
		this.ctrl = c;
		this.parinte = parent;
		this.subs = s;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 180);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 414, 175);
		panel.add(scrollPane);
		
		tableReserve = new JTable(ctrl.getReservedTM());
		tableReserve.setLocation(new Point(5, 5));
		tableReserve.setBounds(new Rectangle(20, 20, 150, 174));
		tableReserve.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableReserve.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableReserve.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tableReserve.setOpaque(false);
		tableReserve.setSelectionForeground(Color.GRAY);
		tableReserve.setSize(new Dimension(100, 100));
		scrollPane.setColumnHeaderView(tableReserve);
		tableReserve.getSelectionModel().addListSelectionListener(new BooksLSM());
		tableReserve.setMaximumSize(new Dimension(200, 200));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 182, 431, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReserver = new JButton("Reserver");
		btnReserver.setBounds(321, 31, 100, 32);
		panel_1.add(btnReserver);
		btnReserver.setName("buttonReserve");
		
		JLabel lblSubsciberId = new JLabel("Subsciber Id");
		lblSubsciberId.setBounds(10, 15, 59, 14);
		panel_1.add(lblSubsciberId);
		
		JLabel lblBookId = new JLabel("Book Id");
		lblBookId.setBounds(20, 40, 49, 14);
		panel_1.add(lblBookId);
		
		textFieldSubscriber = new JTextField();
		textFieldSubscriber.setBounds(79, 12, 138, 20);
		panel_1.add(textFieldSubscriber);
		textFieldSubscriber.setEditable(false);
		textFieldSubscriber.setText(this.subs.getId().toString());
		textFieldSubscriber.setName("textFieldSubscriber");
		textFieldSubscriber.setColumns(10);
		
		textFieldBook = new JTextField();
		textFieldBook.setBounds(79, 37, 138, 20);
		panel_1.add(textFieldBook);
		textFieldBook.setEditable(false);
		textFieldBook.setName("textFieldBook");
		textFieldBook.setColumns(10);
		btnReserver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.reserveBook(Integer.parseInt(textFieldBook.getText()), Integer.parseInt(textFieldSubscriber.getText()));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(ReserveBooks.this, e1.getMessage());
					e1.printStackTrace();
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(ReserveBooks.this, e1.getMessage());
					e1.printStackTrace();
				} 
				catch (LibraryException e1) {
					JOptionPane.showMessageDialog(ReserveBooks.this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
	}
	private class BooksLSM implements ListSelectionListener {
        @Override
		public void valueChanged(ListSelectionEvent e) {
        	 if (!e.getValueIsAdjusting()){
	                int index=tableReserve.getSelectedRow();
	                if (index>=0){
	                    Integer idSel=ctrl.selectedRentedBook(index);
	                    textFieldBook.setText(idSel.toString());
	                }

	            }
        }

		
    }
}
