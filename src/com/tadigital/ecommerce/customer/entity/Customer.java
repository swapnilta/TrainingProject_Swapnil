package com.tadigital.ecommerce.customer.entity;

public class Customer 
{
	private String email;
	private String pswd;
	private String fName;
	private String lName;
	private String gender;
	private String address;
	private String city;
	private String zip;
	private String state;
	private String country;
	private String contact_no;
	public String getEmail() {
		return email;
	}
	public Customer()
	{
		super();
		this.email=null;
		this.pswd=null;
		this.lName=null;
		this.fName=null;
		this.address=null;
		this.gender=null;
		this.address=null;
		this.city=null;
		this.zip=null;
		this.state=null;
		this.country=null;
		this.contact_no=null;
	}
	public Customer(String email, String pswd, String fName, String lName, String gender, String address, String city,
			String zip, String state, String country, String contact_no) {
		super();
		this.email = email;
		this.pswd = pswd;
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
		this.contact_no = contact_no;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	@Override
	public String toString() {
		return "Customer [email=" + email + ", pswd=" + pswd + ", fName=" + fName + ", lName=" + lName + ", gender="
				+ gender + ", address=" + address + ", city=" + city + ", zip=" + zip + ", state=" + state
				+ ", country=" + country + ", contact_no=" + contact_no + "]";
	}
	




}
