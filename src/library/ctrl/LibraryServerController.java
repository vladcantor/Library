package library.ctrl;

import interfaces.LibraryService;
import interfaces.RemoteObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Book;
import model.Librarian;
import model.LibraryException;
import model.Reserved;
import model.Resigned;
import model.Subscriber;

public class LibraryServerController extends Observable implements
		LibraryService, Observer {

	private Librarian librarian;

	private class WrappedObserver implements Observer, Serializable {

		private static final long serialVersionUID = 1L;

		private RemoteObserver ro = null;

		public WrappedObserver(RemoteObserver ro) {
			this.ro = ro;
		}

		@Override
		public void update(Observable o, Object arg) {
			try {
				ro.update(o.toString(), arg);
			} catch (RemoteException e) {
				System.out
						.println("Remote exception removing observer:" + this);
				o.deleteObserver(this);
			}
		}

	}

	public LibraryServerController(Librarian librarian) {
		this.librarian = librarian;
		this.librarian.addObserver(this);
	}

	@Override
	public void adaugaRentedBook(String ID, String bookId)
			throws LibraryException {
		this.librarian.adaugaRentedBook(ID, bookId);

	}

	@Override
	public boolean hasRentedSameTitle(Book b, Subscriber s)
			throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.hasRentedSameTitle(b, s);
	}

	@Override
	public void adaugaSubscriber(String Id, String title)
			throws LibraryException {
		this.librarian.adaugaSubscriber(Id, title);

	}

	@Override
	public void addBook(Integer Id, String title) throws LibraryException {
		this.librarian.addBook(Id, title);

	}

	@Override
	public List<Subscriber> getFreeSubscribers() throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getFreeSubscribers();
	}

	@Override
	public List<Book> getFreeBooks() throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getFreeBooks();
	}

	@Override
	public List<Subscriber> getSubscribers() throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getSubscribers();
	}

	@Override
	public List<Book> getBooks() throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getBooks();
	}

	@Override
	public List<Book> getBookBySubstring(String subs) throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getBookBySubstring(subs);
	}

	@Override
	public Book getBookByTitle(String title) throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getBookByTitle(title);
	}

	@Override
	public Book getBookById(String Id) throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getBookById(Id);
	}

	@Override
	public Subscriber getSubscriberById(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.librarian.getSubscriberById(id);
	}

	@Override
	public List<Book> getRentedBooks() throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getRentedBooks();
	}

	@Override
	public void releaseBook(String spuncte, String idP) throws Exception {
		this.librarian.releaseBook(spuncte, idP);

	}

	@Override
	public void addUser(int sid, String user, String password)
			throws LibraryException {
		this.librarian.addUser(sid, user, password);

	}

	@Override
	public Subscriber checkUser(String user, String pass)
			throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.checkUser(user, pass);
	}

	@Override
	public Subscriber getAdmin(Subscriber s) throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getAdmin(s);
	}

	@Override
	public void addReservedBook(Integer bid, Integer sid)
			throws LibraryException {
		this.librarian.addReservedBook(bid, sid);

	}

	@Override
	public void unReserveBook(Integer bid, Integer sid) throws LibraryException {
		this.librarian.unReserveBook(bid, sid);

	}

	@Override
	public HashMap<Integer, Reserved> getAllReservedBooks()
			throws LibraryException {
		return this.librarian.getAllReservedBooks();
	}

	@Override
	public void resignRights(Integer sid, String motive)
			throws LibraryException {
		this.librarian.resignRights(sid, motive);

	}

	@Override
	public List<Resigned> getAllResigned() throws LibraryException {
		return this.librarian.getAllResigned();
	}

	@Override
	public void allowSubscribing(Integer sid) throws LibraryException {
		this.librarian.allowSubscribing(sid);
	}

	@Override
	public List<Subscriber> getEligibleSubscribers() throws LibraryException {
		// TODO Auto-generated method stub
		return this.librarian.getEligibleSubscribers();
	}

	@Override
	public List<Subscriber> getResignedSubscribers() throws LibraryException {
		return this.librarian.getResignedSubscribers();
	}

	@Override
	public boolean isResigned(Integer sid) throws LibraryException {
		return librarian.isResigned(sid);
	}

	@Override
	public void addObserver(RemoteObserver observer) {
		WrappedObserver mo = new WrappedObserver(observer);
		addObserver(mo);
		System.out.println("Added observer:" + mo);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}

}
