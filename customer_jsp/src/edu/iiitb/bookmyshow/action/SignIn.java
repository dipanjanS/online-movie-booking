package edu.iiitb.bookmyshow.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import Utils.DBManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.PasswordEncryption;

public class SignIn extends ActionSupport{
	String userName;
	String password;

	Boolean flag=false;

	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	Map session=ActionContext.getContext().getSession();
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute(){
		
		return SUCCESS;
	}
	
	public String successful(){
		System.out.println("checking in sign in page for duplicate sign in....");
		if(ActionContext.getContext().getSession().get("userSession")==null){
		System.out.println("in sign in button......");
		System.out.println("username from jsp page....."+userName);
		System.out.println("username from sessionnnn page....."+ActionContext.getContext().getSession().get("userSession"));
		
		if(userName.equals("")){
			addActionError("User name cannot be empty");
			return ERROR;
		}
		else if(password.equals("")){
			addActionError("Password cannot be empty");
			return ERROR;
		}
		setUserName(userName);
		try{
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = "select username,password,customerID from customer where username='"+userName+"';";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			int custID = 0;
			while(rs.next()){
				
				//encryption
				String getEncryption=PasswordEncryption.EncryptPassword(password);
				
				if(rs.getString(1).equals(userName) && rs.getString(2).equals(getEncryption)){
					custID =  rs.getInt(3);
					flag=true;
					//session.put("customerID", rs.getInt(3));
					}
				
			}
			
			if(flag==true){
				session.put("userSession",userName);
				session.put("customerID", custID);
				return SUCCESS;
			}
			else{
				addActionError("User name and password do not match !!! Try again");
				return ERROR;
			}
		
		}
			catch( Exception e){
				e.printStackTrace();
			}
		}
		else{
			addActionError("You are already signed in as  "+ActionContext.getContext().getSession().get("userSession").toString()+" Please logout to sign in or register as different user");
			return ERROR;
		}
		return ERROR;
	}
	
	public String logout(){
		System.out.println("in logout...");
		if(ActionContext.getContext().getSession().get("userSession")==null){
			System.out.println("user not registered from logout.......");
			return ERROR;
		}
		else{
			//System.out.println(ActionContext.getContext().getSession().get("userSession"));
			System.out.println("will kill the session");
			session.remove("userSession");
			session.remove("customerID");
			return SUCCESS;
		}
	}
	
}
