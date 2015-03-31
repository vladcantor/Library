package startUp;

import java.awt.EventQueue;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import gui.StartApp;
import library.ctrl.LibraryController;
import model.Librarian;
import model.LibraryException;
import repository.ILibraryRepository;
import repository.LibraryRepositoryJDBC;
import repository.MemoryRepository;


public class Start {
	
	 public static void main(String[] args) throws LibraryException {
	    	//PropertyConfigurator.configure(args[0]); 
		 Properties serverProps=new Properties(System.getProperties());
	        try {
	            serverProps.load(new FileReader("libraryjdbc.properties"));
	            System.setProperties(serverProps);

	            System.out.println("Properties set: ");
	            System.getProperties().list(System.out);
	        } catch (IOException e) {
	            System.out.println("Cannot find libraryjdbc.properties "+e);
	            return;
	        }
	        //ILibraryRepository parts=new LibraryRepositoryJDBC();
	        ILibraryRepository memo = new MemoryRepository(new LibraryRepositoryJDBC());
	        Librarian concurs=new Librarian(memo);
	        final LibraryController ctrl=new LibraryController(concurs);
	       
	        EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						StartApp frame = new StartApp(ctrl);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	    }

}
