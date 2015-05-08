package model;

import java.io.Serializable;

public class BookKeeper extends Subscriber implements Serializable{
	private String firstN;

	public BookKeeper(String lname, String fname) {
		super(lname);
		this.firstN = fname;
		
	}
	public void setFirstName(String s)
	{
		this.firstN = s;
	}
	public String getFirstName()
	{
		return this.firstN;
	}
	@Override
	public String toString() {
		return "BookKeeper: "+super.getId().toString() + " " + super.getName() + " "  +this.firstN;
	}

}
