package tests;
import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import model.Librarian;
import model.LibraryException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

import repository.*;
public class TestLibrarian {
	private Librarian l;
	LibraryRepositoryJDBC repoDB;
	ILibraryRepository repo;
	public TestLibrarian() throws LibraryException {
		Properties serverProps=new Properties(System.getProperties());
        try {
            serverProps.load(new FileReader("libraryjdbc.properties"));
            System.setProperties(serverProps);

        } catch (IOException e) {
            System.out.println("Cannot find libraryjdbc.properties "+e);
            return;
        }
		repoDB = new LibraryRepositoryJDBC();
		repo = new MemoryRepository(repoDB);
		this.l = new Librarian(repo);
	}
	
	@Test
	public void testReserveRelease() throws Exception
	{
		int i = l.getRentedBooks().size();
		l.adaugaRentedBook("1", "3");
		assertEquals(true, l.getRentedBooks().size() == i + 1);
		l.releaseBook("1", "3");
		assertEquals(true, l.getRentedBooks().size() == i);
	}
	
	@Test
	public void testReserveunReserve() throws Exception
	{
		int i = l.getAllReservedBooks().size();
		l.addReservedBook(1, 3);
		assertEquals(true,l.getAllReservedBooks().size() == i+1);
		l.unReserveBook(1, 3);
		assertEquals(true,l.getAllReservedBooks().size() == i);
	}
	@Test
	public void testResignAllow() throws LibraryException
	{
		int i = l.getAllResigned().size();
		l.resignRights(1, "test");
		assertEquals(true,	l.getAllResigned().size() == i+1);
		l.allowSubscribing(1);
		assertEquals(true,	l.getAllResigned().size() == i);
	}
	
	
	public static void main(String[] args) {
		Properties serverProps=new Properties(System.getProperties());
        try {
            serverProps.load(new FileReader("libraryjdbc.properties"));
            System.setProperties(serverProps);

        } catch (IOException e) {
            System.out.println("Cannot find libraryjdbc.properties "+e);
            return;
        }
	    org.junit.runner.Result result = JUnitCore.runClasses(TestLibrarian.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	  }
	

}
