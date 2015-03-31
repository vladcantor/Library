package library.ctrl;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.TableModel;

import model.Librarian;
import model.LibraryException;



	public class ViewResultsHandler implements Observer {
	    private Librarian librarian;
	    private LibrarySubscriberTableModel partTM;
	    public ViewResultsHandler(Librarian l) throws LibraryException {
	        this.librarian = l;
	        partTM=new  LibrarySubscriberTableModel(librarian.getSubscribers());
	        librarian.addObserver(this);
	    }

	    public TableModel getSubscriberModel() {
	          return partTM;
	    }

	    public void update(Observable o, Object arg) {
	         try {
				partTM.setSubscribers(librarian.getSubscribers());
			} catch (LibraryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public void close() {
	        librarian.deleteObserver(this);

	    }
	}


