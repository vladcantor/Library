package model;

public class Administrator extends Subscriber{
	private String firstN;

	public Administrator(String lname, String first) {
		super(lname);
		this.firstN = first;
		
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
		return "Administrator: "+super.getId().toString() + " " + super.getName() + " "  +this.firstN;
	}

}
