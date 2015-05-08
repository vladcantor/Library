/**
 * 
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import model.Book;
import model.LibraryException;
import model.Reserved;
import model.Resigned;
import model.Subscriber;

/**
 * @author Vlad Mihai
 *
 */
public interface LibraryService extends Remote {

	public void adaugaRentedBook(String ID, String bookId) throws LibraryException, RemoteException;
	public boolean hasRentedSameTitle(Book b, Subscriber s) throws LibraryException, RemoteException;
	public void adaugaSubscriber(String Id, String title) throws LibraryException, RemoteException;
	public void addBook(int Id, String title) throws LibraryException, RemoteException;
	public List<Subscriber> getFreeSubscribers() throws LibraryException, RemoteException;
	public List<Book> getFreeBooks() throws LibraryException, RemoteException;
	public List<Subscriber> getSubscribers() throws LibraryException, RemoteException;
	public List<Book> getBooks() throws LibraryException, RemoteException;
	public List<Book> getBookBySubstring(String subs) throws LibraryException, RemoteException;
	public Book getBookByTitle(String title) throws LibraryException, RemoteException;
	public Book getBookById(String Id) throws LibraryException, RemoteException;
	public Subscriber getSubscriberById(String id) throws Exception, RemoteException;
	public List<Book> getRentedBooks() throws LibraryException, RemoteException;
	public void releaseBook(String spuncte, String idP) throws Exception, RemoteException; 
	public void addUser(int sid, String user, String password) throws LibraryException, RemoteException;
	public Subscriber checkUser(String user, String pass) throws LibraryException, RemoteException;
	public Subscriber getAdmin(Subscriber s) throws LibraryException, RemoteException;
	public void addReservedBook(int bid, int sid) throws LibraryException, RemoteException;
	public void unReserveBook(int bid , int sid) throws LibraryException, RemoteException;
	public HashMap<Integer ,Reserved> getAllReservedBooks() throws LibraryException, RemoteException;
	public void resignRights(int sid, String motive) throws LibraryException, RemoteException;
	public List<Resigned> getAllResigned() throws LibraryException, RemoteException;
	public void allowSubscribing(int sid) throws LibraryException, RemoteException;
	public List<Subscriber> getEligibleSubscribers() throws LibraryException, RemoteException;
	public List<Subscriber> getResignedSubscribers() throws LibraryException, RemoteException;
	public boolean isResigned(int sid) throws LibraryException, RemoteException;
	public void addObserver(RemoteObserver observer)throws RemoteException;

}
