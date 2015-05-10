package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import model.LibraryException;
import repository.*;
public class Librarian extends Observable {
	
	    private ILibraryRepository libRepository;


	    public Librarian(ILibraryRepository repository) {
	       libRepository=repository;
	    }

	    public void adaugaRentedBook(String ID, String bookId) throws LibraryException{
	        
	            Subscriber p=libRepository.findSubscriberById(Integer.parseInt(ID));
	            Book b = libRepository.findBookById(Integer.parseInt(bookId));
	            if(b.getAvalability() == false)
	            	throw new LibraryException("The book is already rented!!!");
	            	else
	            		if(p == null)
	            			throw new LibraryException("The Subscriber's id is not valid");
			            else if(!isResigned(p.getId()))
			            {
			            	if(!this.hasRentedSameTitle(b, p))
			            	{
				            	HashMap<Integer, Reserved> data = this.getAllReservedBooks();
				            	if(data.containsKey(b.getId()))
				            		if(data.get(b.getId()).gerReserver().getId() == p.getId())
				            		{
				            			
				            			
				            			this.unReserveBook(b.getId(), p.getId());
				            			
				            		}
				            		else
				            			throw new LibraryException("This book has been reserved by someone, you can not rent it!");
				            				     
				            	p.setRented(b);
		            			b.setAvailable(false);
				            	this.libRepository.rentBook(p.getId(), b.getId());
				            	setChanged();
				            	notifyObservers();
			            	}
			            	else{
			            		throw new LibraryException("You have already rented a book with the same title");
			            	}
			            }
			            else{
			            	throw new LibraryException("You have no right to rent a Book! Your acount has been Resigned!");
			            }
	        
	    }
	    public boolean hasRentedSameTitle(Book b, Subscriber s) throws LibraryException
	    {
	    	List<Book> books = s.getRentedBooks();
	    	for(Book booki:books)
	    	{
	    		if(booki.getTitle().equals(b.getTitle()))
	    			return true;
	    	}
	    	
	    		
	    	return false;
	    }

	    public void adaugaSubscriber(String Id, String title) throws LibraryException{
	        Subscriber part=new Subscriber(title);
	       part.setId(Integer.parseInt(Id));
	            libRepository.addSubscriber(part);;
	            setChanged();
	            notifyObservers();
	       
	           
	       
	    }
	    public void addBook(Integer Id, String title) throws LibraryException
	    {
	    	Book b = new Book(title, true);
	    	b.setId( Id);
	    	if(this.getBookById(Id.toString()) != null)
	    		throw new  LibraryException("A book with this id already exists");
	    	else
	    	{
		    	libRepository.addBook(b);
		    	setChanged();
		    	notifyObservers();
	    	}
	    }
	    
	    public List<Subscriber> getFreeSubscribers() throws LibraryException
	    {
	    	List<Subscriber> ls = new ArrayList<Subscriber>();
	    	for(Subscriber s:this.libRepository.getAllSubscribers())
	    		if(s.getRentedBooks().size() <= 3 )
	    			ls.add(s);
	    	return ls;
	    }
	    public List<Book> getFreeBooks() throws LibraryException
	    {
	    	List<Book> lb = new ArrayList<Book>();
	    	for(Book b:this.libRepository.getAllBooks())
	    		if(b.getAvalability() == true )
	    			lb.add(b);
	    	return lb;
	    }
	    public List<Subscriber> getSubscribers() throws LibraryException{
	        return libRepository.getAllSubscribers();
	    }
	    public List<Book> getBooks() throws LibraryException
	    {
	    	return libRepository.getAllBooks();
	    }

	    public List<Book> getBookBySubstring(String subs) throws LibraryException{
	        return libRepository.findByTitle(subs);
	    }

	   public Book getBookByTitle(String title) throws LibraryException
	   {
		   for(Book b:this.libRepository.getAllBooks())
			   if(b.getTitle() == title)
				   return b;
		   return null;
			   
		  
	   }
	   public Book getBookById(String Id) throws LibraryException
	   {
		   
		   return this.libRepository.findBookById(Integer.parseInt(Id));
	   }
	   public Subscriber getSubscriberById(String id) throws LibraryException
	   {
		   		return this.libRepository.findSubscriberById(Integer.parseInt(id));
			  
	   }

	public List<Book> getRentedBooks() throws LibraryException {
		List<Book> blist = new ArrayList<Book>();
		for(Book b: this.libRepository.getAllBooks())
			if(b.getAvalability() == false)
				blist.add(b);
			
		return blist;
	}
	public void releaseBook(String spuncte, String idP) throws LibraryException {
	
		
		Subscriber s = this.getSubscriberById(spuncte);
		Book b = this.getBookById(idP);
			
			
			
			s.returnBook(b.getId());
			b.setAvailable(true);
			this.libRepository.releaseBook(s.getId(), b.getId());
			setChanged();
	    	notifyObservers();
		
			
	}
	public void addUser(int sid, String user, String password) throws LibraryException
	{
		this.libRepository.addUser(sid, user, password);
	}
	
	public Subscriber checkUser(String user, String pass) throws LibraryException
	{
		return this.libRepository.CheckUser(user, pass);
	}
	public Subscriber getAdmin(Subscriber s) throws LibraryException
	{
		return this.libRepository.getAdministrator(s);
	}
	
	public void addReservedBook(Integer bid, Integer sid) throws LibraryException
	{
		if(!isResigned(sid))
		{
			this.libRepository.reserveBook(bid, sid);
		}
		else
		{
			throw new LibraryException("You have no right to rent a Book! Your acount has been Resigned!");
		}
		setChanged();
    	notifyObservers();
	}
	public void unReserveBook(Integer bid , Integer sid) throws LibraryException
	{
		this.libRepository.unReserveBook(bid, sid);
		setChanged();
    	notifyObservers();
	}
	public HashMap<Integer, Reserved> getAllReservedBooks() throws LibraryException
	{
		return this.libRepository.getAllReservedBooks();
	}
	public void resignRights(Integer sid, String motive) throws LibraryException{
		this.libRepository.resignRights(sid, motive);
		setChanged();
    	notifyObservers();
	}
	public List<Resigned> getAllResigned() throws LibraryException{
		return this.libRepository.getAllResigned();
	}
	public void allowSubscribing(Integer sid) throws LibraryException{
		this.libRepository.allowSubscribing(sid);
		setChanged();
    	notifyObservers();
	}
	public List<Subscriber> getEligibleSubscribers() throws LibraryException
	{
		List<Resigned> resigned = this.getAllResigned();
		List<Subscriber> subscr = this.getSubscribers();
		List<Subscriber> rest = new ArrayList<Subscriber>();
		boolean ok = false;
		for(Subscriber s : subscr)
		{
			for(Resigned r: resigned)
				if(r.getResinedSubscriber().getId() == s.getId())
					{
						ok = true;
						break;
					}
			if(!ok)
			{
				rest.add(s);
				
			}
			else
				ok = false;
		}
				
		return rest;
	}
	
	public List<Subscriber> getResignedSubscribers() throws LibraryException{
		List<Resigned> resigned = this.getAllResigned();
		List<Subscriber> rest = new ArrayList<Subscriber>();
		
			for(Resigned r: resigned)
				rest.add(r.getResinedSubscriber());
				
		return rest;
	}
	public boolean isResigned(Integer sid) throws LibraryException
	{
		for(Resigned r:this.getAllResigned())
			if(r.getResinedSubscriber().getId() == sid)
				return true;
		return false;
	}
	   
}


