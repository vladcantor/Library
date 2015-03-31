package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import model.Administrator;
import model.Book;
import model.LibraryException;
import model.Reserved;
import model.Resigned;
import model.Subscriber;

public class LibraryRepositoryJDBC implements ILibraryRepository{

	
	private static Logger logger=Logger.getLogger("concurs");
	
	public LibraryRepositoryJDBC() {
		// TODO Auto-generated constructor stub
		logger.info("[Entering] ");
		
		
	}


	@Override
	public void addSubscriber(Subscriber s) throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] "+s);
		
		System.out.println("SubscriersJdbc:save");
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into subscribers (sid,name) values (?,?)");
            stmt.setInt(1, s.getId());
            stmt.setString(2, s.getName());
            
            int s2=stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }

		
		
	}

	@Override
	public void addBook(Book b) throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] "+b);
		
		System.out.println("BooksJdbc:save");
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into Books (bid,title) values (?,?)");
            stmt.setInt(1, b.getId());
            stmt.setString(2, b.getTitle());
            
            int s2=stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }
	}

	@Override
	public List<Book> getAllBooks() throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] ");
		
        System.out.println("BooksJdbc:getAllBooks");
        Connection con= JdbcUtils.getInstance().getConnection();
        List<Book> rez=new ArrayList<Book>();
        PreparedStatement stmt=null;
        PreparedStatement stmt2 = null;
        try {
            stmt=con.prepareStatement("select bid, title from books");
            ResultSet rs=stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("bid");
                String title = rs.getString("title");
                stmt2 = con.prepareStatement("select * from rentedbooks where bid=? ");
	            stmt2.setInt(1, id);
	            ResultSet rs2 = stmt2.executeQuery();
	            Book p ;
               
	            if(rs2.next())
	            {
	            
	               p= new Book( title, false);
	               p.setId(id);
	            }
	            else
	            {
	            	p= new Book( title, true);
		            p.setId(id);
	            }
                
               
               
                rez.add(p);
            }


        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }

        return rez;
	    
		
	}

	@Override
	public List<Subscriber> getAllSubscribers() throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] ");
		 System.out.println("BooksJdbc:getAllBooks");
	        Connection con= JdbcUtils.getInstance().getConnection();
	        List<Subscriber> rez=new ArrayList<Subscriber>();
	        PreparedStatement stmt=null;
	        PreparedStatement stmt2 = null;
	        try {
	            stmt=con.prepareStatement("select sid, name from subscribers");
	            ResultSet rs=stmt.executeQuery();

	            while(rs.next()){
	                int id = rs.getInt("sid");
	                String title = rs.getString("name");
	                stmt2 = con.prepareStatement("select * from rentedbooks where sid=? ");
		            stmt2.setInt(1, id);
		            ResultSet rs2 = stmt2.executeQuery();
		            Subscriber p = new Subscriber( title);
	                p.setId(id);
		            if(rs2.next())
	                {
	                	Integer bid = rs2.getInt("bid");
	                	Book b = this.findBookById(bid);
	                	p.setRented(b);
		                while(rs2.next())
		                {
		                	bid = rs2.getInt("bid");
		                	b = this.findBookById(bid);
		                	p.setRented(b);
		                }
		                
	                }
	               
	               
	               
	                rez.add(p);
	            }


	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }

	        return rez;
	}
		
		

	@Override
	public List<Book> findByTitle(String ti) throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] "+ti);
		List<Book> filter = new ArrayList<Book>();
		 System.out.println("BooksJdbc:findBookById");
	        Connection con= JdbcUtils.getInstance().getConnection();
	        Book rez=null;
	        PreparedStatement stmt=null;
	        PreparedStatement stmt2 = null;
	        try {
	            stmt=con.prepareStatement("select * from books where title=?");
	            stmt.setString(1,ti);
	            ResultSet rs=stmt.executeQuery();
	            
	            if(rs.next()){
	                Integer id = rs.getInt("bid");
	                String nume = rs.getString("title");
	                stmt2 = con.prepareStatement("select * from rentedbooks where bid=? ");
		            stmt2.setInt(1, id);
		            ResultSet rs2 = stmt2.executeQuery();
		            if(rs2.next())
		            {
		            
		               rez= new Book( nume, false);
		               rez.setId(id);
		            }
		            else
		            {
		            	rez= new Book( nume, true);
			            rez.setId(id);
		            }
	               filter.add(rez);
	                
	            }
	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }

	        return filter;
	}

	@Override
	public final Subscriber findSubscriberById(Integer Id) throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] "+Id.toString());
		 System.out.println("SubscribersJdbc:findSubscrierById");
	        Connection con= JdbcUtils.getInstance().getConnection();
	        Subscriber rez=null;
	        PreparedStatement stmt=null;
	        PreparedStatement stmt2 = null;
	        try {
	            stmt=con.prepareStatement("select * from Subscribers where sid=?");
	            stmt.setInt(1,Id);
	            ResultSet rs=stmt.executeQuery();
	            stmt2 = con.prepareStatement("select * from rentedbooks where sid=? ");
	            stmt2.setInt(1, Id);
	            
	            ResultSet rs2 = stmt2.executeQuery();

	            if(rs.next()){
	                Integer id = rs.getInt("sid");
	                String nume = rs.getString("name");
	                
	                rez= new Subscriber( nume);
	                rez.setId(id);
	                if(rs2.next())
	                {
	                	Integer bid = rs2.getInt("bid");
	                	Book b = this.findBookById(bid);
	                	rez.setRented(b);
		                while(rs2.next())
		                {
		                	bid = rs2.getInt("bid");
		                	b = this.findBookById(bid);
		                	rez.setRented(b);
		                }
		                
	                }
	                
	              
	            }
	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }

	        return rez;
	    }
	

	@Override
	public Book findBookById(Integer bookId) throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] "+bookId);
		 System.out.println("BooksJdbc:findBookById");
	        Connection con= JdbcUtils.getInstance().getConnection();
	        Book rez=null;
	        PreparedStatement stmt=null;
	        PreparedStatement stmt2 = null;
	        try {
	            stmt=con.prepareStatement("select * from books where bid=?");
	            stmt.setInt(1,bookId);
	            ResultSet rs=stmt.executeQuery();
	            stmt2 = con.prepareStatement("select * from rentedbooks where bid=?");
	            stmt2.setInt(1, bookId);
	            ResultSet rs2 = stmt2.executeQuery();

	            if(rs.next()){
	                Integer id = rs.getInt("bid");
	                String nume = rs.getString("title");
	                if(rs2.next())
	                {
		                rez= new Book( nume, false);
		                rez.setId(id);
	                }
	                else
	                {
	                	rez= new Book( nume, true);
		                rez.setId(id);
	                }
	            }
	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }

	        return rez;
	    }
	

	@Override
	public int getBookSize() throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("[Entering:] ");
		return this.getAllBooks().size();
	}
	public int getSubscribersSize() throws LibraryException{
		logger.info("[Entering:] ");
		return this.getAllSubscribers().size();
	}
	
	public void rentBook(Integer sid, Integer bid) throws LibraryException
	{
		logger.info("[Entering:] "+sid.toString() + " "+ bid.toString());
		System.out.println("RentJdbc:rentBook");
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into rentedbooks (bid,sid,subscribedate) values (?,?,?)");
            stmt.setInt(1, bid);
            stmt.setInt(2,sid);
            stmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            
            int s2=stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }


	}
	
	public void releaseBook(Integer sid, Integer bid) throws LibraryException
	{
		logger.info("[Entering:] "+sid.toString() + " "+ bid.toString());
		System.out.println("RentJdbc:releaseBook");
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from rentedbooks where sid=? and bid=?");
            stmt.setInt(1, sid);
            stmt.setInt(2,bid);
            
            int s2=stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }


	}
	public void addUser( Integer sid, String username, String password) throws LibraryException{
		System.out.println("SubscriersJdbc:save username and password");
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
        	String s = new String(password);
            stmt=con.prepareStatement("insert into users (sid,username, password) values (?,?, ?)");
            stmt.setInt(1, sid);
            stmt.setString(2,username);
            stmt.setString(3, s);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }
	}
	public Subscriber CheckUser(String username,String  pass) throws LibraryException{
		 Connection con= JdbcUtils.getInstance().getConnection();
	        Subscriber rez = null;
	        Boolean ok = false;
	        PreparedStatement stmt=null;
	        try {
	            stmt=con.prepareStatement("select * from users where username = ?");
	            stmt.setString(1, username);
	            ResultSet rs=stmt.executeQuery();

	            if(rs.next()){
	                int id = rs.getInt("sid");
	                String userpass = rs.getString("password");
	                if(pass.equals(userpass)){
	                	rez = this.findSubscriberById(id);
	                	ok  = true;
	                }
	               
	                
	            }
	            

	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }
	        
		return rez;
	}
	
	public Subscriber getAdministrator(Subscriber s) throws LibraryException{
		Connection con= JdbcUtils.getInstance().getConnection();
        Subscriber rez = null;
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("select * from administrators where pid = ?");
            stmt.setInt(1, s.getId());
            ResultSet rs=stmt.executeQuery();

            if(rs.next()){
                String lastn = rs.getString("lastname");
                String firstn = rs.getString("firstname");
                if(lastn.equals(s.getName()))
                	rez = new Administrator(lastn, firstn);
                
                
            }

        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }
      return rez;
	}


	@Override
	public void reserveBook(Integer bid, Integer sid) throws LibraryException {
		logger.info("[Entering:] "+sid.toString() + " "+ bid.toString());
		System.out.println("ReserveJdbc:rentBook");
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into reservedbooks (bid,sid) values (?,?)");
            stmt.setInt(1, bid);
            stmt.setInt(2,sid);
            
            
            int s2=stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }


		
	}


	@Override
	public void unReserveBook(Integer bid, Integer sid) throws LibraryException {
		logger.info("[Entering:] "+sid.toString() + " "+ bid.toString());
		
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("delete from reservedbooks where sid=? and bid=?");
            stmt.setInt(1, sid);
            stmt.setInt(2,bid);
            
            int s2=stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }

	}


	@Override
	public HashMap<Integer, Reserved> getAllReservedBooks() throws LibraryException {
		HashMap<Integer, Reserved> books = new  HashMap<Integer, Reserved>();
		logger.info("[Entering:] ");
		 
	        Connection con= JdbcUtils.getInstance().getConnection();
	        PreparedStatement stmt=null;
	       
	        try {
	            stmt=con.prepareStatement("select bid, sid from reservedbooks");
	            ResultSet rs=stmt.executeQuery();

	            while(rs.next()){
	                int bid = rs.getInt("bid");
	                int sid = rs.getInt("sid");
	                
	                Book b = this.findBookById(bid);
	                Subscriber s = this.findSubscriberById(sid);
	                books.put(bid, new Reserved(b, s));
	            }


	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }

		return books;
	}


	@Override
	public void resignRights(Integer sid, String motive) throws LibraryException {
		logger.info("[Entering:] "+sid.toString() + " "+motive);
		
        Connection con= JdbcUtils.getInstance().getConnection();
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement("insert into resignedsubscribers (sid,reason) values (?,?)");
            stmt.setInt(1, sid);
            stmt.setString(2,motive);
            
            
            int s2=stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error "+e);
        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement "+e);
            }
        }


		
	}


	@Override
	public List<Resigned> getAllResigned() throws LibraryException {
		List<Resigned> subs = new ArrayList<Resigned>();
		 Connection con= JdbcUtils.getInstance().getConnection();
	        PreparedStatement stmt=null;
	       
	        try {
	            stmt=con.prepareStatement("select sid, reason from resignedsubscribers");
	            ResultSet rs=stmt.executeQuery();

	            while(rs.next()){
	                int sid = rs.getInt("sid");
	                String reason = rs.getString("reason");
	               
	                Subscriber s = this.findSubscriberById(sid);
	                subs.add(new Resigned(s, reason));
	            }


	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }

		return subs;
		
	}


	@Override
	public void allowSubscribing(Integer sid) throws LibraryException {
		 Connection con= JdbcUtils.getInstance().getConnection();
	        PreparedStatement stmt=null;
	        try {
	            stmt=con.prepareStatement("delete from  resignedsubscribers where sid = ?");
	            stmt.setInt(1, sid);
	           
	            
	            
	            int s2=stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new LibraryException("Error "+e);
	        }finally {
	            if (stmt!=null) try {
	                stmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing statement "+e);
	            }
	        }
		
	}


	
}
