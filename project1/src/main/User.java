package main;


public class User {
	
	private String id;
	private String pin;
	private String name;
	private double balance;
	
	public User(String id, String pin, String name, double balance) {
		this.id = id;
		this.pin = pin;
		this.name = name;
		this.balance = balance;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPin() {
		return this.pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return this.id + "," + this.pin + "," + this.name + "," + this.balance;
	}
	
}