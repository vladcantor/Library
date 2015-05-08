package library.ctrl;

import interfaces.LibraryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.TableModel;

import model.Book;
import model.Librarian;
import model.LibraryException;
import model.Reserved;
import model.Resigned;
import model.Subscriber;

public class LibraryController {
	private LibraryService lib;
	LibraryBookTableModel bookTM;
	LibrarySubscriberTableModel subsTM;
	LibraryRentedBooksTableModel bookrentedTM;
	LibraryReservedBooksTableModel reservedTM;
	UnReserveTableModel unreserveTM;
	LibrarySubscriberTableModel resignedTM;
	LibrarySubscriberTableModel allowTM;
	
	
	public LibraryController(LibraryService l) throws LibraryException
	{
		this.lib = l;
		HashMap<Integer, Reserved> res = lib.getAllReservedBooks();
    	List<Book> books = lib.getRentedBooks();
    	List<Book> notReserved = new ArrayList<Book>();
    	for(Book b: books)
    		if(!res.containsKey(b.getId()))
    			notReserved.add(b);
		
		this.bookTM = new LibraryBookTableModel(this.lib.getFreeBooks());
		this.subsTM = new LibrarySubscriberTableModel(this.lib.getSubscribers());
		this.bookrentedTM = new LibraryRentedBooksTableModel(books);
		this.reservedTM = new LibraryReservedBooksTableModel(notReserved);
		this.unreserveTM = new UnReserveTableModel(lib.getAllReservedBooks());
		this.resignedTM = new LibrarySubscriberTableModel(this.lib.getEligibleSubscribers());
		this.allowTM = new LibrarySubscriberTableModel(lib.getResignedSubscribers());
		
		//lib.addObserver(this);
		
	}
	public TableModel getBookTM()
	{
		return this.bookTM;
	}
	public TableModel getRentedBookTM()
	{
		return this.bookrentedTM;
	}
	public TableModel getReservedTM()
	{
		return this.reservedTM;
	}
	public Subscriber getSubscriberById(String subscriberId) throws Exception
	{
		return this.lib.getSubscriberById(subscriberId);
	}
	public TableModel getUnReserveTM()
	{
		return this.unreserveTM;
	}
	public TableModel getResignedTM()
	{
		return this.resignedTM;
	}
	public TableModel getAllowSubscrinersTM()
	{
		return this.allowTM;
	}
    public int selectedBook(int index) {
        Book part=bookTM.get(index);
        return part.getId();
    }
    public int selectedRentedBook(int index)
    {
    	Book part = this.bookrentedTM.get(index);
    	return part.getId();
    }public int selectedReserveBook(int index)
    {
    	Book b = reservedTM.get(index);
    	return b.getId();
    }
    public Reserved selectedReservation(int index)
    {
    	return this.unreserveTM.get(index);
    }
    public void rentABook(String sId, String bId) throws LibraryException
    {
    	this.lib.adaugaRentedBook(sId, bId);
    	
    }
    public void reserveBook(Integer bid, Integer sid) throws LibraryException
    {
    	this.lib.addReservedBook(bid, sid);
    }
    public void AddBook(String Id, String Title)throws LibraryException
    {
    	try
    	{
    	this.lib.addBook(Integer.parseInt(Id), Title);
    	
    	
    	}catch(Exception e)
    	{
    		throw new LibraryException(e.getMessage());
    	}
    }
    public void AddSubscriber(String Id, String name) throws LibraryException
    {
    	try {
    		this.lib.adaugaSubscriber(Id, name);
    		
    	}catch(Exception e)
    	{
    		throw new LibraryException(e.getMessage());
    	}
    }

	public void update() {
        try {
			subsTM.setSubscribers(lib.getFreeSubscribers());
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        try {
			bookTM.setBooks(lib.getFreeBooks());
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			bookrentedTM.setBooks(lib.getRentedBooks());
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try{
        	HashMap<Integer, Reserved> res = lib.getAllReservedBooks();
        	List<Book> books = lib.getRentedBooks();
        	List<Book> notReserved = new ArrayList<Book>();
        	for(Book b: books)
        		if(!res.containsKey(b.getId()))
        			notReserved.add(b);
        			
        	reservedTM.setReservedBooks(notReserved);
        }catch(LibraryException e)
        {
        	e.printStackTrace();
        }
        try {
			this.unreserveTM.setUnReservedBooks(this.lib.getAllReservedBooks());
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			this.resignedTM.setSubscribers(this.lib.getEligibleSubscribers());
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			this.allowTM.setSubscribers(this.lib.getResignedSubscribers());
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }

   public void close() {
      // lib.deleteObserver(this);
		
	}
   public TableModel getSubscriberModel() {
       return subsTM;
 }
   public Subscriber selectedSubscriber(int index)
   {
	   return this.resignedTM.get(index);
   }
   public Subscriber selectedAllowSubscriber(int index)
   {
	   return this.allowTM.get(index);
   }
	public void releaseBook(String spuncte, String idP) throws Exception {
		
		this.lib.releaseBook(spuncte, idP);
	}
	public void addUser(int sid, String user, String pass) throws LibraryException{
		this.lib.addUser(sid, user, pass);
	}
	public Subscriber checkLogin(String user, String pass, Boolean check) throws LibraryException
	{
		
		return this.lib.checkUser(user, pass);
	}
	public Subscriber getAdministrator(Subscriber s) throws LibraryException{
		return this.lib.getAdmin(s);
	}
	
	public void UnReserveBook(Integer bid, Integer sid) throws LibraryException
	{
		this.lib.unReserveBook(bid, sid);
	}
	public void resignRights(Integer sid, String motive) throws LibraryException{
		this.lib.resignRights(sid, motive);
	}
	public List<Resigned> getAllResigned() throws LibraryException{
		return this.getAllResigned();
	}
	public void allowSubscribing(Integer sid) throws LibraryException{
	this.lib.allowSubscribing(sid);
}
}
