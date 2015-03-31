package model;

public class BookKeeper extends Subscriber{
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
