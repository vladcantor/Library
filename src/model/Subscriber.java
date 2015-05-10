package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subscriber extends XMLDeserialization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private Integer id;
	private String name;
	private List<Book> rented;

	public Subscriber(
			HashMap<String, String> stringRepresentation) {
		super(stringRepresentation);
		if (stringRepresentation.containsKey("name") && stringRepresentation.containsKey("id")) {
			this.name = stringRepresentation.get("name");
			this.id = Integer.parseInt(stringRepresentation.get("id"));
		}
	}
	
	public Subscriber(String n) {
		this.rented = new ArrayList<Book>();
		this.id = count;
		this.name = n;
		count++;
	}

	public List<Book> getRentedBooks() {
		return this.rented;
	}

	public void setId(Integer i) {
		this.id = i;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setRented(Book b) throws LibraryException {
		synchronized (this) {
			if (this.rented.size() >= 3)
				throw new LibraryException(
						"You have the maximum number of rents!");
			else
				this.rented.add(b);
		}

	}

	public void returnBook(int bookId) throws LibraryException {
		synchronized (this) {

			Book q = null;
			for (Book q2 : this.rented)
				if (q2.getId().equals(bookId))
					q = q2;
			if (q != null)
				this.rented.remove(q);
			else
				throw new LibraryException(
						"You can not release a book that has not been rented by you!!!!");
		}
	}

	public void returnBook(Book b) {
		this.rented.remove(b);

	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Subscriber))
			return false;
		Subscriber otherSubs = (Subscriber) other;
		if (this.getId() != otherSubs.getId())
			return false;
		return true;
	}

}
