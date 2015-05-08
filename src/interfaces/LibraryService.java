/**
 * 
 */
package interfaces;

import java.rmi.Remote;
import java.util.ArrayList;
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

	public void adaugaRentedBook(String ID, String bookId) throws LibraryException;
	public boolean hasRentedSameTitle(Book b, Subscriber s) throws LibraryException;
	public void adaugaSubscriber(String Id, String title) throws LibraryException;
	public void addBook(Integer Id, String title) throws LibraryException;
	public List<Subscriber> getFreeSubscribers() throws LibraryException;
	public List<Book> getFreeBooks() throws LibraryException;
	public List<Subscriber> getSubscribers() throws LibraryException;
	public List<Book> getBooks() throws LibraryException;
	public List<Book> getBookBySubstring(String subs) throws LibraryException;
	public Book getBookByTitle(String title) throws LibraryException;
	public Book getBookById(String Id) throws LibraryException;
	public Subscriber getSubscriberById(String id) throws Exception;
	public List<Book> getRentedBooks() throws LibraryException;
	public void releaseBook(String spuncte, String idP) throws Exception; 
	public void addUser(int sid, String user, String password) throws LibraryException;
	public Subscriber checkUser(String user, String pass) throws LibraryException;
	public Subscriber getAdmin(Subscriber s) throws LibraryException;
	public void addReservedBook(Integer bid, Integer sid) throws LibraryException;
	public void unReserveBook(Integer bid , Integer sid) throws LibraryException;
	public HashMap<Integer, Reserved> getAllReservedBooks() throws LibraryException;
	public void resignRights(Integer sid, String motive) throws LibraryException;
	public List<Resigned> getAllResigned() throws LibraryException;
	public void allowSubscribing(Integer sid) throws LibraryException;
	public List<Subscriber> getEligibleSubscribers() throws LibraryException;
	public List<Subscriber> getResignedSubscribers() throws LibraryException;
	public boolean isResigned(Integer sid) throws LibraryException;
	public void addObserver(RemoteObserver observer);

}
