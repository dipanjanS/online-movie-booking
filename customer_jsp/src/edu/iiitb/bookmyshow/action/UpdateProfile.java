package edu.iiitb.bookmyshow.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.DBManager;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateProfile extends ActionSupport{
	
	String userName;
	String emailID;
	String firstName;
	String middleName;
	String lastName;
	String phone;
	String balance;
	
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	int res=0;
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmailID() {
		return emailID;
	}


	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}
//xjuvr

	public String execute(){
		//System.out.println("in update prfile action..."+userName+"...."+password+"new passwod..."+passwordNew);
		
		if(firstName.equals("")){
			addActionError("First Name cannot be empty");
			return ERROR;
		}
		else if(lastName.equals("")){
			addActionError("Last Name cannot be empty");
			return ERROR;
		}
		
		else if(phone.equals("")){
			addActionError("Phone Number cannot be empty");
			return ERROR;
		}
		else if(balance.equals("")){
			addActionError("Please provide some balance");
			return ERROR;
		}
		else if(phone.length()<10){
			addActionError("Phone Number should be of 10 digits !!!");
			return ERROR;
		}
		
			try{
				java.sql.Connection con=DBManager.getConnection();
				DBManager.useDataBase(con);
				//sqlQuery = "update customer set userName,password,emailId,firstName,middleName,lastName,phone,balance) values(?,?,?,?,?,?,?,?);";
				sqlQuery="update customer set firstName=?,middleName=?,lastName=?,phone=?,balance=? where userName = ?";
				statement=con.prepareStatement(sqlQuery);
				statement.setString(6, userName);
				statement.setString(1, firstName);
				statement.setString(2, middleName);
				statement.setString(3, lastName);
				statement.setString(4, phone);
				statement.setInt(5, Integer.parseInt(balance));
				res = statement.executeUpdate(); 

			} catch( Exception e){
				e.printStackTrace();
			}
			
			return SUCCESS;
		}
	
	}

