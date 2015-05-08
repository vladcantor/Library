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
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import library.ctrl.LibraryController;
import model.LibraryException;
import model.Subscriber;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class ResignRenting extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSubscriberid;
	private LibraryController ctrl;
	private JFrame parinte;
	private JTextField textFieldReason;
	
	public ResignRenting(final LibraryController ctrl, JFrame parinte) {
		this.ctrl = ctrl;
		this.parinte = parinte;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 404, 166);
		panel.add(scrollPane);
		
		table = new JTable(ctrl.getResignedTM());
		table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		table.getSelectionModel().addListSelectionListener(new SubsLSM());
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 198, 424, 58);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSubscriberId = new JLabel("Subscriber ID");
		lblSubscriberId.setBounds(10, 11, 85, 14);
		panel_1.add(lblSubscriberId);
		
		txtSubscriberid = new JTextField();
		txtSubscriberid.setEditable(false);
		txtSubscriberid.setText("SubscriberID");
		txtSubscriberid.setBounds(105, 8, 116, 20);
		panel_1.add(txtSubscriberid);
		txtSubscriberid.setColumns(10);
		
		JButton btnResign = new JButton("Resign");
		btnResign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ctrl.resignRights(Integer.parseInt(txtSubscriberid.getText()), textFieldReason.getText());
				} catch (NumberFormatException | LibraryException | RemoteException e) {
					JOptionPane.showMessageDialog(ResignRenting.this, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		btnResign.setBounds(325, 7, 89, 23);
		panel_1.add(btnResign);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(10, 36, 85, 14);
		panel_1.add(lblReason);
		
		textFieldReason = new JTextField();
		textFieldReason.setBounds(105, 39, 116, 20);
		panel_1.add(textFieldReason);
		textFieldReason.setColumns(10);
	}
	
	
	private class SubsLSM implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()){
                int index=table.getSelectedRow();
                if (index>=0){
                    Subscriber idSel=ctrl.selectedSubscriber(index);
                    txtSubscriberid.setText(idSel.getId().toString());
                }

            }
        }
    }

}
