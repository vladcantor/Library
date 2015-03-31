package gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import library.ctrl.LibraryController;



public class SubscribersGUI extends JFrame {
	    private LibraryController ctrl;
	    private JTable tabel;

	    public SubscribersGUI(LibraryController ctrl) {
	        super("Subscribers");
	        this.ctrl = ctrl;
	        add(creeazaTabel());
	        addWindowListener(new WindowAdapter(){
	            @Override
	            public void windowClosing(WindowEvent e){
	                SubscribersGUI.this.ctrl.close();
	            }
	        });
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	    }


	   private JPanel creeazaTabel(){
	       JPanel pan=new JPanel();
	       pan.setLayout(new GridLayout(1,1));
	       tabel=new JTable(ctrl.getSubscriberModel());
	       JScrollPane pane=new JScrollPane(tabel);
	       pan.add(pane);
	       return pan;
	   }

}
