package model;

public class Reserved {
	private Book b;
	private Subscriber s;
	public Reserved(Book b, Subscriber s) {
		this.b = b;
		this.s = s;
		
	}
	public Book getReservedBook(){
		return this.b;
	}
	public Subscriber gerReserver()
	{
		return this.s;
	}

}
