package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import library.ctrl.LibraryController;

import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookKeeperGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LibraryController ctrl;
	private JTextField textField;
	private JTextField textField_1;
	

	/**
	 * Create the frame.
	 */
	public BookKeeperGUI(LibraryController c) {
		this.ctrl= c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 363, 163);
		contentPane.add(scrollPane);
		
		table = new JTable(c.getRentedBookTM());
		table.getSelectionModel().addListSelectionListener(new BooksLSM());
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		JMenu mnMore = new JMenu("More");
		menuBar.add(mnMore);
		
		JMenuItem mntmAddBook = new JMenuItem("Add Book");
		mntmAddBook.addActionListener(new MouseListeners());
		
			
		mnMore.add(mntmAddBook);
		
		JLabel lblSubscriberId = new JLabel("Subscriber ID");
		lblSubscriberId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubscriberId.setBounds(10, 203, 77, 14);
		contentPane.add(lblSubscriberId);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookId.setBounds(10, 233, 77, 14);
		contentPane.add(lblBookId);
		
		textField = new JTextField();
		textField.setBounds(108, 200, 116, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 230, 106, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		JButton btnRelease = new JButton("Release");
		btnRelease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = Integer.parseInt(textField.getText());
				try {
					ctrl.releaseBook(textField.getText(), textField_1.getText());
					textField.setText("");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		});
		btnRelease.setBounds(273, 213, 89, 23);
		contentPane.add(btnRelease);
	}
	
	private class MouseListeners  implements ItemListener, ActionListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			AddBook addb = new AddBook(BookKeeperGUI.this, ctrl);
			System.out.println("CliiiickkkkkkItem!!!!");
			addb.setVisible(true);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AddBook addb = new AddBook(BookKeeperGUI.this, ctrl);
			System.out.println("CliiiickkkkkkAction!!!!");
			addb.setBounds(150, 150, 200, 180);
			addb.setAlwaysOnTop(true);
			addb.setModal(true);
			addb.setVisible(true);
			
		}

	    
	}
	
	private class BooksLSM implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()){
                int index=table.getSelectedRow();
                if (index>=0){
                    Integer idSel=ctrl.selectedRentedBook(index);
                    textField_1.setText(idSel.toString());
                }

            }
        }
    }
}
