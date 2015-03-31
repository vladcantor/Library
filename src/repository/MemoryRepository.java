package repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.Administrator;
import model.Book;
import model.BookKeeper;
import model.LibraryException;
import model.Pair;
import model.Rented;
import model.Reserved;
import model.Resigned;
import model.Subscriber;

public class MemoryRepository  implements ILibraryRepository{

	private LibraryRepositoryJDBC repoDB;
	private ArrayList<Subscriber> subscList;
	private ArrayList<Book> bookList;
	private HashMap<Integer, Rented> rentedList;
	private ArrayList<Subscriber> admins;
	private ArrayList<BookKeeper> bookK;
	private HashMap<String, Pair< Integer, String>> users;
	private HashMap<Integer, Reserved> reservedBooks;
	private List<Resigned> resigned;
	public MemoryRepository(LibraryRepositoryJDBC repoDB) throws LibraryException {
		
		this.admins = new ArrayList<Subscriber>();
		this.bookList = new ArrayList<Book>();
		this.subscList = new ArrayList<Subscriber>();
		this.rentedList = new HashMap<Integer, Rented>();
		this.users = new HashMap<String, Pair<Integer, String>>();
		this.bookK = new ArrayList<BookKeeper>();
		this.repoDB = repoDB;
		this.subscList.addAll(this.repoDB.getAllSubscribers());
		this.bookList.addAll(this.repoDB.getAllBooks());
		this.reservedBooks = this.repoDB.getAllReservedBooks();
		this.resigned = this.repoDB.getAllResigned();
		
		
		
	}

	@Override
	public void addSubscriber(Subscriber s) throws LibraryException {
		this.subscList.add(s);
		this.repoDB.addSubscriber(s);
		
	}

	@Override
	public void addBook(Book b) throws LibraryException {
		
		this.bookList.add(b);
		this.repoDB.addBook(b);
	}

	@Override
	public List<Book> getAllBooks() throws LibraryException {
		
		return this.bookList;
	}

	@Override
	public List<Subscriber> getAllSubscribers() throws LibraryException {
		
		return this.subscList;
	}

	@Override
	public List<Book> findByTitle(String ti) throws LibraryException {
		
		ArrayList<Book> subList = new ArrayList<Book>();
		for(Book b:this.bookList)
			if(b.getTitle().equals(ti))
				subList.add(b);
		return subList;
	}

	@Override
	public final Subscriber findSubscriberById(Integer Id) throws LibraryException {
		
		
		for(Subscriber s:this.subscList)
			if(s.getId() == Id)
				return s;
		return null;
		
	}

	@Override
	public Book findBookById(Integer bookId) throws LibraryException {
		
		for(Book b:this.bookList)
			if(b.getId() == bookId)
				return b;
		return null;
	}

	@Override
	public int getBookSize() throws LibraryException {
		
		return this.bookList.size();
	}

	@Override
	public int getSubscribersSize() throws LibraryException {
		
		return this.subscList.size();
	}

	@Override
	public void releaseBook(Integer sid, Integer bid) throws LibraryException {
		
		for(Rented p: this.rentedList.values())
			if(p.getBook().getId().equals(bid) && p.getSubscriber().getId().equals(sid))
				this.rentedList.remove(p);
		
		this.repoDB.releaseBook(sid, bid);
	}

	@Override
	public void rentBook(Integer sid, Integer bid) throws LibraryException {
		Book b = this.findBookById(bid);
		Subscriber s = this.findSubscriberById(sid);
		this.rentedList.put(bid, new Rented(b,s));
		this.repoDB.rentBook(sid, bid);
		
	}

	@Override
	public void addUser(Integer sid, String username,String password) 
			throws LibraryException {
		this.users.put(username, new Pair<Integer, String>(sid, password));
		this.repoDB.addUser(sid, username, password);
		
	}

	@Override
	public Subscriber CheckUser(String username, String pass)
			throws LibraryException {
		Subscriber s = null;
		if(this.users.containsKey(username))
			if(this.users.get(username).getElement1() == pass)
			{
				s = this.findSubscriberById(this.users.get(username).getElement0());
				
				return s;
			}
		Subscriber s2 = this.repoDB.CheckUser(username, pass);
		if(s2!= null)
		{
			if(s2!=null)
			{
				
					s = s2;
					this.users.put(username, new Pair<Integer, String>(s2.getId(),pass));
					return s;
				
			}
		}
		
		
		return s;
	}

	@Override
	public Subscriber getAdministrator(Subscriber s) throws LibraryException {
		
		for(Subscriber subs:this.admins)
			if(subs.getId() == s.getId())
				return subs;
		Subscriber subs = null;
		subs = this.repoDB.getAdministrator(s);
		if(subs != null)
			this.admins.add(s);
		return subs;
		
	}

	@Override
	public void reserveBook(Integer bid, Integer sid) throws LibraryException {
		Book b = this.findBookById(bid);
		Subscriber s = this.findSubscriberById(sid);
		this.reservedBooks.put(bid,new Reserved(b, s));
		this.repoDB.reserveBook(bid, sid);
		
	}

	@Override
	public void unReserveBook(Integer bid, Integer sid) throws LibraryException {
		// TODO Auto-generated method stub
		for(Integer i:this.reservedBooks.keySet())
			if(bid == i)
				this.reservedBooks.remove(i);
		this.repoDB.unReserveBook(bid, sid);
		
	}

	@Override
	public HashMap<Integer, Reserved> getAllReservedBooks() {
		return this.reservedBooks;
	}

	@Override
	public void resignRights(Integer sid, String motive)
			throws LibraryException {
		this.resigned.add(new Resigned(this.findSubscriberById(sid), motive));
		this.repoDB.resignRights(sid, motive);
		
	}

	@Override
	public List<Resigned> getAllResigned() throws LibraryException {
		return this.resigned;
	}

	@Override
	public void allowSubscribing(Integer sid) throws LibraryException {
		for(int i = 0; i < this.resigned.size(); i++)
			if(resigned.get(i).getResinedSubscriber().getId() == sid)
				this.resigned.remove(i);
		this.repoDB.allowSubscribing(sid);
		
	}

}
