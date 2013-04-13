package edu.iiitb.bookmyshow.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import Utils.DBManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ViewProfile extends ActionSupport{

	String userName;
	String emailID;
	String firstName;
	String middleName;
	String lastName;
	String phone;
	int balance;

	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";

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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String viewProfile(){
		System.out.println("in view profile action....");
		Map session=ActionContext.getContext().getSession();
		if(ActionContext.getContext().getSession().get("userSession")==null){
			return ERROR;
		}
		else{
			userName=ActionContext.getContext().getSession().get("userSession").toString();
			try{
				java.sql.Connection con=DBManager.getConnection();
				DBManager.useDataBase(con);

				sqlQuery = "select username,emailID,firstName,middleName,lastName,phone,balance from customer where username='"+userName+"';";
				statement=con.prepareStatement(sqlQuery);
				rs=statement.executeQuery();

				while(rs.next()){
					setUserName(rs.getString(1));
					setEmailID(rs.getString(2));
					setFirstName(rs.getString(3));
					setMiddleName(rs.getString(4));
					setLastName(rs.getString(5));
					setPhone(rs.getString(6));
					setBalance(rs.getInt(7));
				}
			}
			catch( Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}
	}
}
