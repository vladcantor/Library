package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import library.ctrl.LibraryController;
import library.ctrl.UnReserveTableModel;
import model.LibraryException;
import model.Reserved;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

import javax.swing.ListSelectionModel;

import java.awt.Point;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.UIManager;

public class UnReserveGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSubscriber;
	private JTextField txtBook;
	private LibraryController ctrl;
	private JFrame parinte;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public UnReserveGUI(LibraryController c, JFrame parinte) {
		this.ctrl = c;
		this.parinte = parinte;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 328);
		contentPane = new JPanel();
		contentPane.setLocation(new Point(5, 5));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 549, 214);
		contentPane.add(scrollPane);
		
		table = new JTable(ctrl.getUnReserveTM());
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new BooksLSM());
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 230, 554, 59);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSubscriberId = new JLabel("Subscriber ID");
		lblSubscriberId.setBounds(10, 11, 88, 14);
		panel_2.add(lblSubscriberId);
		
		JLabel lblBookId = new JLabel("Book Id");
		lblBookId.setBounds(10, 36, 88, 14);
		panel_2.add(lblBookId);
		
		txtSubscriber = new JTextField();
		txtSubscriber.setEditable(false);
		txtSubscriber.setText("Subscriber");
		txtSubscriber.setBounds(120, 8, 86, 20);
		panel_2.add(txtSubscriber);
		txtSubscriber.setColumns(10);
		
		txtBook = new JTextField();
		txtBook.setEditable(false);
		txtBook.setText("Book");
		txtBook.setBounds(120, 33, 86, 20);
		panel_2.add(txtBook);
		txtBook.setColumns(10);
		
		JButton btnUnreserve = new JButton("UnReserve");
		btnUnreserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ctrl.UnReserveBook(Integer.parseInt(txtBook.getText()), Integer.parseInt(txtSubscriber.getText()));
				} catch (NumberFormatException | LibraryException e) {
					JOptionPane.showMessageDialog(UnReserveGUI.this, e.getMessage());
					e.printStackTrace();
					return;
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(UnReserveGUI.this, e.getMessage());
					e.printStackTrace();
					return;
				}
			}
		});
		btnUnreserve.setBounds(284, 32, 140, 23);
		panel_2.add(btnUnreserve);
	}
	
	private class BooksLSM implements ListSelectionListener {
		@Override
        public void valueChanged(ListSelectionEvent e) {
        	 if (!e.getValueIsAdjusting()){
	                int index=table.getSelectedRow();
	                if (index>=0){
	                    Reserved res=ctrl.selectedReservation(index);
	                    txtBook.setText(res.getReservedBook().getId().toString());
	                    txtSubscriber.setText(res.gerReserver().getId().toString());
	                }

	            }
        }
    }
}
