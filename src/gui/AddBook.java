package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import library.ctrl.LibraryController;
import model.LibraryException;


public class AddBook extends JDialog {
	private JFrame parinte;
    private LibraryController ctrl;

    public AddBook( JFrame parinte, LibraryController ctrl) {
        super(parinte,"Add Book", true);
        this.parinte = parinte;
        this.ctrl = ctrl;
        getContentPane().add(creeazaAdaugare());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    private JTextField id, nume;
    private JButton addBook, clear;
    private JPanel creeazaAdaugare(){
        JPanel pan=new JPanel();
        pan.setLayout(new GridLayout(3,1));
            JPanel linia1=UtileGUI.putInPanel(new JLabel("Id:"));
            linia1.add(id=new JTextField(7));

            pan.add(linia1);

            JPanel linia2=UtileGUI.putInPanel(new JLabel("Titlu:"));
            linia2.add(nume=new JTextField(7));
            pan.add(linia2);

            JPanel linia3=UtileGUI.putInPanel(addBook=new JButton("Adauga"));
            linia3.add(clear=new JButton("Clear"));
            ActionListener al=new ButListener();
            addBook.addActionListener(al);
            clear.addActionListener(al);
            pan.add(linia3);
        return pan;
    }

    private class ButListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
             if (e.getSource()==addBook){
                 String idP=id.getText();
                 if (empty(idP)){
                     JOptionPane.showMessageDialog(AddBook.this,"Trebuie sa introduceti un ID", "Eroare adaugare carte", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 String numeP=nume.getText();
                 if (empty(numeP)){
                     JOptionPane.showMessageDialog(AddBook.this,"Trebuie sa introduceti titlul", "Eroare adaugare carte", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 try {
                     ctrl.AddBook(idP, numeP);
                 } catch (LibraryException ex) {
                     JOptionPane.showMessageDialog(AddBook.this,ex.getMessage(), "Eroare adaugare Carte", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 return;
             }
            if (e.getSource()==clear){
                id.setText("");
                nume.setText("");
            }
        }
    }

    private boolean empty(String s){
        return (s==null)|| (s.trim().length()==0);
    }
}


