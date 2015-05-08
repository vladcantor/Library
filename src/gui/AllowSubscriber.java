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

import library.ctrl.LibraryController;
import model.LibraryException;
import model.Subscriber;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class AllowSubscriber extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSubscriberid;
	private LibraryController ctrl;
	private JFrame parinte;

	
	public AllowSubscriber(LibraryController c, JFrame par) {
		this.ctrl = c;
		this.parinte = par;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 181);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 419, 165);
		panel.add(scrollPane);
		
		table = new JTable(ctrl.getAllowSubscrinersTM());
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new SubsLSM());
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 226, 434, 35);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSubscriberId = new JLabel("Subscriber ID");
		lblSubscriberId.setBounds(10, 11, 107, 14);
		panel_1.add(lblSubscriberId);
		
		txtSubscriberid = new JTextField();
		txtSubscriberid.setText("SubscriberID");
		txtSubscriberid.setBounds(127, 8, 127, 20);
		panel_1.add(txtSubscriberid);
		txtSubscriberid.setColumns(10);
		
		JButton btnAllow = new JButton("Allow");
		btnAllow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ctrl.allowSubscribing(Integer.parseInt(txtSubscriberid.getText()));
				} catch (NumberFormatException | LibraryException e) {
					JOptionPane.showMessageDialog(AllowSubscriber.this, e.getMessage());
					e.printStackTrace();
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(AllowSubscriber.this, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnAllow.setBounds(319, 7, 89, 23);
		panel_1.add(btnAllow);
	}
	
	
	private class SubsLSM implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()){
                int index=table.getSelectedRow();
                if (index>=0){
                    Subscriber idSel=ctrl.selectedAllowSubscriber(index);
                    txtSubscriberid.setText(idSel.getId().toString());
                }

            }
        }
    }
}
