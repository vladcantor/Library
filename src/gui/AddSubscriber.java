package gui;


	

	import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

	import library.ctrl.LibraryController;
import model.LibraryException;
import model.Subscriber;


	public class AddSubscriber  extends JDialog {
		private JFrame parinte;
	    private LibraryController ctrl;

	    public AddSubscriber( JFrame parinte, LibraryController ctrl) {
	        super(parinte,"Add Book", true);
	        this.parinte = parinte;
	        this.ctrl = ctrl;
	        getContentPane().add(creeazaAdaugare());
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        
	    }
	    private JTextField id, nume, user;
	    private JPasswordField password;
	    private JButton addBook, clear;
	    private JPanel creeazaAdaugare(){
	        JPanel pan=new JPanel();
	        pan.setLayout(new GridLayout(5,2));
	            JPanel linia1=UtileGUI.putInPanel(new JLabel("Id:"));
	            linia1.add(id=new JTextField(7));
	           
	            

	            pan.add(linia1);

	            JPanel linia2=UtileGUI.putInPanel(new JLabel("Name:"));
	            linia2.add(nume=new JTextField(7));
	            pan.add(linia2);
	            
	            
	            JPanel linia3 = UtileGUI.putInPanel(new JLabel("Username:"));
	            linia3.add(user = new JTextField(7));
	            pan.add(linia3);
	            
	            JPanel linia4 = UtileGUI.putInPanel(new JLabel("Password"));
	            linia4.add(password = new JPasswordField(7));
	            pan.add(linia4);
	            
	            
	            JPanel linia5=UtileGUI.putInPanel(addBook=new JButton("Adauga"));
	            linia5.add(clear=new JButton("Clear"));
	            ActionListener al=new ButListener();
	            addBook.addActionListener(al);
	            clear.addActionListener(al);
	            pan.add(linia5);
	        return pan;
	    }

	    private class ButListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	             if (e.getSource()==addBook){
	                 String idP=id.getText();
	                 if (empty(idP)){
	                     JOptionPane.showMessageDialog(AddSubscriber.this,"Trebuie sa introduceti un ID", "Eroare adaugare subscriber", JOptionPane.ERROR_MESSAGE);
	                     return;
	                 }
	                 String numeP=nume.getText();
	                 if (empty(numeP)){
	                     JOptionPane.showMessageDialog(AddSubscriber.this,"Trebuie sa introduceti nume", "Eroare adaugare subscriber", JOptionPane.ERROR_MESSAGE);
	                     return;
	                 }
	                 String userP=user.getText();
	                 if (empty(userP)){
	                     JOptionPane.showMessageDialog(AddSubscriber.this,"Trebuie sa introduceti username", "Eroare adaugare subscriber", JOptionPane.ERROR_MESSAGE);
	                     return;
	                 }
	                 char[] passP=password.getPassword();
	                 if (passP.length == 0 || passP == null){
	                     JOptionPane.showMessageDialog(AddSubscriber.this,"Trebuie sa introduceti parola", "Eroare adaugare subscriber", JOptionPane.ERROR_MESSAGE);
	                     return;
	                 }
	                 try {
	                     ctrl.AddSubscriber(idP, numeP);
	                     ctrl.addUser(Integer.parseInt(idP), user.getText(), new String(password.getPassword()));
	                     Subscriber s = ctrl.getLibrarian().getSubscriberById(idP);
	                     SubscriberGUI2 dialog = new SubscriberGUI2 (ctrl, s);
	 					 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 					 dialog.setVisible(true);
	                     
	                 } catch (LibraryException ex) {
	                     JOptionPane.showMessageDialog(AddSubscriber.this,ex.getMessage(), "Eroare adaugare subscriber", JOptionPane.ERROR_MESSAGE);
	                     return;
	                 } catch (Exception e1) {
						
	                	 JOptionPane.showMessageDialog(AddSubscriber.this,e1.getMessage(), "Eroare adaugare subscriber", JOptionPane.ERROR_MESSAGE);
	                	 return;
					}
	                 return;
	             }
	            if (e.getSource()==clear){
	                id.setText("");
	                nume.setText("");
	                user.setText("");
	                password.setText("");
	            }
	        }
	    }

	    private boolean empty(String s){
	        return (s==null)|| (s.trim().length()==0);
	    }
	}





