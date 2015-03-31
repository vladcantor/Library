package repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.LibraryException;
import model.Reserved;
import model.Resigned;
import model.Subscriber;
import model.Book;

public interface ILibraryRepository {
	
	public void addSubscriber(Subscriber s) throws LibraryException;
	public void addBook(Book b) throws LibraryException;
	public List<Book> getAllBooks() throws LibraryException;
	public List<Subscriber> getAllSubscribers() throws LibraryException;
	public List<Book> findByTitle(String ti) throws LibraryException;
	public Subscriber findSubscriberById(Integer Id) throws LibraryException;
	public Book findBookById(Integer bookId) throws LibraryException;
	public int getBookSize() throws LibraryException;
	public int getSubscribersSize() throws LibraryException;
	public void releaseBook(Integer sid, Integer bid) throws LibraryException;
	public void rentBook(Integer sid, Integer bid) throws LibraryException;
	public void addUser( Integer sid, String username, String password) throws LibraryException;
	public Subscriber CheckUser(String username,String  pass) throws LibraryException;
	public  Subscriber getAdministrator(Subscriber s) throws LibraryException;
	public void reserveBook(Integer bid, Integer sid) throws LibraryException;
	public void unReserveBook(Integer bid, Integer sid ) throws LibraryException;
	public HashMap<Integer, Reserved> getAllReservedBooks() throws LibraryException;
	public void resignRights(Integer sid, String motive) throws LibraryException;
	public List<Resigned> getAllResigned() throws LibraryException;
	public void allowSubscribing(Integer sid) throws LibraryException;
}
