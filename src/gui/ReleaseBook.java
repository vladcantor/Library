package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;

import library.ctrl.LibraryController;
import model.LibraryException;
import model.Subscriber;

public class ReleaseBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableCatalogBook;
	private Subscriber subs;
	private LibraryController ctrl;
	private JFrame parinte;
	private JButton okButton;
	private JTextField id, idSubscriber;

	
	/**
	 * Create the dialog.
	 */
	public ReleaseBook(LibraryController c, Subscriber s, JFrame par) {
		//super(par,"Release Book", true);
		setPreferredSize(new Dimension(600, 700));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.subs = s;
		this.ctrl = c;
		this.parinte = par;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setSize(new Dimension(600,700));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			{
				tableCatalogBook = new JTable(ctrl.getRentedBookTM());
				tableCatalogBook.setLocation(new Point(5, 5));
				tableCatalogBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		        tableCatalogBook.getSelectionModel().addListSelectionListener(new BooksLSM());
				JScrollPane spane = new JScrollPane(tableCatalogBook);
				spane.setPreferredSize(new Dimension(430, 190));
				
				spane.setLocation(new Point(0, 1));
				spane.setAlignmentY(1.0f);
				spane.setAlignmentX(1.0f);
				contentPanel.add(spane);
				
				
				
				tableCatalogBook.setBounds(new Rectangle(20, 20, 150, 150));
				
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Release Book");
				okButton.setActionCommand("ReleaseBook");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			   
			    
			 
			 buttonPane.setLayout(new GridLayout(3,1));
			 JPanel linia1=UtileGUI.putInPanel(new JLabel("IdBook:"));
			 linia1.add(id=new JTextField(7));
			 id.setEditable(false);
			 buttonPane.add(linia1);

			 JPanel linia2=UtileGUI.putInPanel(new JLabel("IdSubscriber:"));
			 linia2.add(idSubscriber=new JTextField(7));
			 idSubscriber.setEditable(false);
			 idSubscriber.setText(subs.getId().toString());
			 buttonPane.add(linia2);

			
			 okButton.addActionListener(new ButListener());
			 buttonPane.setBorder(BorderFactory.createTitledBorder("Release Book"));
			            
			    
		}
		
	}
	
	
	 private class BooksLSM implements ListSelectionListener {
	        public void valueChanged(ListSelectionEvent e) {
	        	 if (!e.getValueIsAdjusting()){
		                int index=tableCatalogBook.getSelectedRow();
		                if (index>=0){
		                    Integer idSel=ctrl.selectedRentedBook(index);
		                    id.setText(idSel.toString());
		                }

		            }
	        }
	    }
	 
	 private class ButListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String idBook=id.getText();
	            String idSubs=idSubscriber.getText();
	            if (tableCatalogBook.getSelectedRow()<0){
	                JOptionPane.showMessageDialog(ReleaseBook.this,"Trebuie sa selectati o carte", "Eroare adaugare Imprumut carte", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            try{
	                
	                ctrl.releaseBook(idSubs,idBook);
	            
	            }catch(NumberFormatException ex){
	                JOptionPane.showMessageDialog(ReleaseBook.this,"Id-ul trebuie sa fie un numar intreg", "Eroare restituire carte", JOptionPane.ERROR_MESSAGE);
	                return;
	            } catch (Exception e1) {
				
	            	JOptionPane.showMessageDialog(ReleaseBook.this,e1.getMessage(), "Eroare restituire carte", JOptionPane.ERROR_MESSAGE);
	            	e1.printStackTrace();
	            	return;
				}

	        }
	    }
	 
	

	 

}
