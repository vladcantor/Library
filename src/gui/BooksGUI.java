package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import library.ctrl.LibraryController;
import model.LibraryException;



public class BooksGUI extends JFrame{
	 private LibraryController ctrl;
	 private JFrame parinte;
	    public BooksGUI(JFrame par, LibraryController ctrl){
	        super("LibraryService");
	        this.parinte = par;
	        this.ctrl=ctrl;
	        setJMenuBar(creeazaMeniu());
	        add(creeazaTabelBooks(), BorderLayout.CENTER);
	        add(creeazaRentBook(), BorderLayout.SOUTH);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    }
	    private JTextField id, idSubscriber;
	    private JButton addRez;
	    private JPanel creeazaRentBook() {
	            JPanel pan=new JPanel();
	            pan.setLayout(new GridLayout(3,1));
	            JPanel linia1=UtileGUI.putInPanel(new JLabel("IdBook:"));
	            linia1.add(id=new JTextField(7));
	            id.setEditable(false);
	            pan.add(linia1);

	            JPanel linia2=UtileGUI.putInPanel(new JLabel("IdSubscriber:"));
	            linia2.add(idSubscriber=new JTextField(7));
	            pan.add(linia2);

	            JPanel linia3=UtileGUI.putInPanel(addRez=new JButton("Rent"));
	            addRez.addActionListener(new ButListener());
	            pan.add(linia3);
	            pan.setBorder(BorderFactory.createTitledBorder("Rent Book"));
	            return pan;
	    }
	    private JTable tabelB;
	    private JScrollPane creeazaTabelBooks() {
	       JScrollPane pan=new JScrollPane();
	        pan.setLayout(new GridLayout(1,1));
	        tabelB=new JTable(ctrl.getBookTM());
	        tabelB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        tabelB.getSelectionModel().addListSelectionListener(new BooksLSM());
	        JScrollPane pane=new JScrollPane(tabelB);
	        pan.add(pane);
	        return pan;
	    }

	    private JMenuBar creeazaMeniu() {
	        JMenuBar mb=new JMenuBar();
	        JMenu part=new JMenu("Books");
	        JMenuItem adaugBook=new JMenuItem("Add Book");
	        part.add(adaugBook);
	        
	        JMenu parts=new JMenu("Subscribers");
	        JMenuItem adaugSubs=new JMenuItem("Add Subscriber");
	        parts.add(adaugSubs);
	        
	        mb.add(part);
	        mb.add(parts);
	        
	        adaugBook.setActionCommand("adaugaBook");
	        adaugSubs.setActionCommand("AddSubs");
	        ActionListener al=new MeniuListener();
	        adaugBook.addActionListener(al);
	        adaugSubs.addActionListener(al);
	       
	        return mb;
	    }

	    private class MeniuListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String cmd=e.getActionCommand();
	            if ("adaugaBook".equals(cmd)){
	                AddBook addWin=new AddBook(BooksGUI.this, ctrl);
	                addWin.setSize(200,200);
	                addWin.setLocation(175,175);
	                addWin.setVisible(true);
	                return;
	            }
	            if ("AddSubs".equals(cmd)){
	                AddSubscriber addWin=new AddSubscriber(BooksGUI.this, ctrl);
	                addWin.setSize(200,200);
	                addWin.setLocation(175,175);
	                addWin.setVisible(true);
	                return;
	            }
	           

	        }
	    }

	    private class BooksLSM implements ListSelectionListener {
	        public void valueChanged(ListSelectionEvent e) {
	            if (!e.getValueIsAdjusting()){
	                int index=tabelB.getSelectedRow();
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
	            if (tabelB.getSelectedRow()<0){
	                JOptionPane.showMessageDialog(BooksGUI.this,"Trebuie sa selectati o carte", "Eroare adaugare Imprumut carte", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            try{
	                
	                ctrl.rentABook(spuncte,idP);
	                idSubscriber.setText("");
	            }catch(NumberFormatException ex){
	                JOptionPane.showMessageDialog(BooksGUI.this,"Id-ul trebuie sa fie un numar intreg", "Eroare adaugare imprumut", JOptionPane.ERROR_MESSAGE);
	                return;
	            } catch (LibraryException ex) {
	                JOptionPane.showMessageDialog(BooksGUI.this,ex.getMessage(), "Eroare adaugare imprumut", JOptionPane.ERROR_MESSAGE);

	            }

	        }
	    }
	}


