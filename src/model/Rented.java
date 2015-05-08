package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class Rented implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private Book b;
	private Subscriber s;
	public Rented(Book book,Subscriber subs) {
		this.b = book;
		this.s = subs;
		this.date = new Date(new java.util.Date().getTime());
		
	}
	public Book getBook()
	{
		return this.b;
	}
	public Subscriber getSubscriber()
	{
		return this.s;
	}
	public Date getDate()
	{
		return this.date;
	}
	
	public void setBook(Book b2)
	{
		this.b = b2;
	}
	public void setSubscriber(Subscriber s2)
	{
		this.s = s2;
	}
	public void setDate(Date date2)
	{
		this.date = date2;;
	}
	

}
