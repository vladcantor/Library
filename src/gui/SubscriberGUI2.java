package gui;





import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JScrollPane;

import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTree;

import java.awt.Point;

import library.ctrl.LibraryController;
import model.LibraryException;
import model.Subscriber;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Dimension;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class SubscriberGUI2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableCatalogBook;
	private JButton okButton;
	private JTextField id, idSubscriber;
	private library.ctrl.LibraryController ctrl;
	private Subscriber subs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the dialog.
	 */
	public SubscriberGUI2(LibraryController c, Subscriber s) {
		setPreferredSize(new Dimension(600, 700));
		this.subs = s;
		this.ctrl = c;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setSize(new Dimension(300, 200));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			{
				tableCatalogBook = new JTable(ctrl.getBookTM());
				tableCatalogBook.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
				tableCatalogBook.setLocation(new Point(5, 5));
				tableCatalogBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		        tableCatalogBook.getSelectionModel().addListSelectionListener(new BooksLSM());
				contentPanel.setLayout(null);
				JScrollPane spane = new JScrollPane(tableCatalogBook);
				spane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				spane.setSize(430, 124);
				spane.setPreferredSize(new Dimension(430, 190));
				spane.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 3, true), null));
				spane.setLocation(new Point(2, 10));
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
				okButton = new JButton("Rent");
				okButton.setActionCommand("Rent");
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
			 buttonPane.setBorder(BorderFactory.createTitledBorder("Rent Book"));
			            
			    
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnBook = new JMenu("Book");
				menuBar.add(mnBook);
				{
					JMenuItem mntmReserve = new JMenuItem("ReserveBook");
					mntmReserve.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ReserveBooks rb = new ReserveBooks(ctrl,subs , (JFrame) JFrame.getFrames()[0]);
							rb.setVisible(true);
						}
					});
					mnBook.add(mntmReserve);
				}
				
			}
		}
	}
	
	
	 private class BooksLSM implements ListSelectionListener {
	        public void valueChanged(ListSelectionEvent e) {
	        	 if (!e.getValueIsAdjusting()){
		                int index=tableCatalogBook.getSelectedRow();
		                if (index>=0){
		                    Integer idSel=ctrl.selectedBook(index);
		                    id.setText(idSel.toString());
		                }

		            }
	        }
	    }
	 
	 private class ButListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String idP=id.getText();
	            String spuncte=idSubscriber.getText();
	            if (tableCatalogBook.getSelectedRow()<0){
	                JOptionPane.showMessageDialog(SubscriberGUI2.this,"Trebuie sa selectati o carte", "Eroare adaugare Imprumut carte", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            try{
	                
	                ctrl.rentABook(spuncte,idP);
	            
	            }catch(NumberFormatException ex){
	                JOptionPane.showMessageDialog(SubscriberGUI2.this,"Id-ul trebuie sa fie un numar intreg", "Eroare adaugare imprumut", JOptionPane.ERROR_MESSAGE);
	                return;
	            } catch (LibraryException ex) {
	                JOptionPane.showMessageDialog(SubscriberGUI2.this,ex.getMessage(), "Eroare adaugare imprumut", JOptionPane.ERROR_MESSAGE);

	            }

	        }
	    }
	 
	
	           
	           

	        
	    }

	 
	 
