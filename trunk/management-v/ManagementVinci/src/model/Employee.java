package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Employee {

	private int id;
	private String firstName;
	private boolean available;
	private String lastName;
	private String phoneNumber,phoneNumberSecond,phoneNumberClient,email,emailSecond,emailThird,status;
	private ArrayList<Case> cases;
	
	public Employee(int id,boolean available,String firstName,String lastName,String phoneNumber,String phoneNumber2,String phoneNumerClient,String email, String emailSecond,String emailThird,String status, ArrayList<Case> cases){
		this.setId(id);
		this.setAvailable(available);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNumber(phoneNumber);
		this.setPhoneNumberSecond(phoneNumber2);
		this.setPhoneNumberClient(phoneNumerClient);
		this.setEmail(email);
		this.setStatus(status);
		this.setEmailSecond(emailSecond);
		this.setEmailThird(emailThird);
		if(cases!=null)
			this.setCases(cases);
		else
			this.setCases(new ArrayList<Case>());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<Case> getCases() {
		return cases;
	}

	public void setCases(ArrayList<Case> cases) {
		this.cases = cases;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(firstName + " " + lastName + " ("+phoneNumber+")");
		Iterator<Case> it = cases.iterator();
		while(it.hasNext()){
			sb.append(it.next());
		}
		
		return sb.toString();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailSecond() {
		return emailSecond;
	}

	public void setEmailSecond(String emailSecond) {
		this.emailSecond = emailSecond;
	}

	public String getPhoneNumberSecond() {
		return phoneNumberSecond;
	}
	
	public void setPhoneNumberSecond(String s){
		this.phoneNumberSecond=s;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhoneNumberClient() {
		return phoneNumberClient;
	}

	public void setPhoneNumberClient(String phoneNumberClient) {
		this.phoneNumberClient = phoneNumberClient;
	}

	public String getEmailThird() {
		return emailThird;
	}

	public void setEmailThird(String emailThird) {
		this.emailThird = emailThird;
	}
}
