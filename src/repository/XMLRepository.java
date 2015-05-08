/**
 * 
 */
package repository;

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
public class XMLRepository implements ILibraryRepository {

	/**
	 * 
	 */
	public XMLRepository() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#addSubscriber(model.Subscriber)
	 */
	@Override
	public void addSubscriber(Subscriber s) throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#addBook(model.Book)
	 */
	@Override
	public void addBook(Book b) throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#getAllBooks()
	 */
	@Override
	public List<Book> getAllBooks() throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#getAllSubscribers()
	 */
	@Override
	public List<Subscriber> getAllSubscribers() throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#findByTitle(java.lang.String)
	 */
	@Override
	public List<Book> findByTitle(String ti) throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#findSubscriberById(java.lang.Integer)
	 */
	@Override
	public Subscriber findSubscriberById(Integer Id) throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#findBookById(java.lang.Integer)
	 */
	@Override
	public Book findBookById(Integer bookId) throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#getBookSize()
	 */
	@Override
	public int getBookSize() throws LibraryException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#getSubscribersSize()
	 */
	@Override
	public int getSubscribersSize() throws LibraryException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#releaseBook(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void releaseBook(Integer sid, Integer bid) throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#rentBook(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void rentBook(Integer sid, Integer bid) throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#addUser(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public void addUser(Integer sid, String username, String password)
			throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#CheckUser(java.lang.String, java.lang.String)
	 */
	@Override
	public Subscriber CheckUser(String username, String pass)
			throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#getAdministrator(model.Subscriber)
	 */
	@Override
	public Subscriber getAdministrator(Subscriber s) throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#reserveBook(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void reserveBook(Integer bid, Integer sid) throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#unReserveBook(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void unReserveBook(Integer bid, Integer sid) throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#getAllReservedBooks()
	 */
	@Override
	public HashMap<Integer, Reserved> getAllReservedBooks()
			throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#resignRights(java.lang.Integer, java.lang.String)
	 */
	@Override
	public void resignRights(Integer sid, String motive)
			throws LibraryException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#getAllResigned()
	 */
	@Override
	public List<Resigned> getAllResigned() throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see repository.ILibraryRepository#allowSubscribing(java.lang.Integer)
	 */
	@Override
	public void allowSubscribing(Integer sid) throws LibraryException {
		// TODO Auto-generated method stub

	}

}
