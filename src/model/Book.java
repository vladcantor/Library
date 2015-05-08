package model;

import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int c = 0;
	int id;
	String title;
	Boolean available;
	
	public Book(String t, Boolean avi)
	{
		this.title = t;
		this.available = avi;
		this.id  = c;
		c++;
		
	}
	public void setTitle(String t)
	{
		this.title = t;
		
	}
	public void setId(Integer i)
	{
		this.id = i;
	}
	public void setAvailable(Boolean av)
	{
		this.available = av;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public Boolean getAvalability()
	{
		return this.available;
	}
	
	public Integer getId()
	{
		return this.id;
	}
}
