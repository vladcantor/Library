package model;

public class Resigned {
	private Subscriber s;
	private String reason;
	public Resigned(Subscriber s, String reason) {
		this.reason = reason;
		this.s = s;
	}
	public Subscriber getResinedSubscriber()
	{
		return this.s;
	}
	public String getReason()
	{
		return this.reason;
	}
}
