package model;

import java.io.Serializable;

public class Resigned implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
