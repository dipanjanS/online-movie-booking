package edu.iiitb.bookmyshow.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import Utils.DBManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.PasswordEncryption;

public class ChangePassword extends ActionSupport{
	String userName;
	String newPassword;
	String oldPassword;
	String confirmPassword;
	String checkPassword;

	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	int res=0;
	int checkFlag=0;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String execute(){
		System.out.println("in change password...");
		Map session=ActionContext.getContext().getSession();
		if(ActionContext.getContext().getSession().get("userSession")==null){
			return ERROR;
		}
		else{

			return SUCCESS;
		}
	}
	public String verifyChange(){
		checkFlag=0;
		setUserName(ActionContext.getContext().getSession().get("userSession").toString());
		String newEncryptedPassword=PasswordEncryption.EncryptPassword(newPassword);
		String oldEncryptedPassword=PasswordEncryption.EncryptPassword(oldPassword);
		System.out.println();
		try{
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			sqlQuery="select password from customer where username='"+userName+"';";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			while(rs.next()){
				checkPassword=rs.getString(1);
				System.out.println("value from database..."+checkPassword);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(oldPassword.equals("")){
			addActionError("Old Password cannot be empty");
			return ERROR;
		}
		if(newPassword.equals("")){
			addActionError("New Password cannot be empty");
			return ERROR;
		}
		if(confirmPassword.equals("")){
			addActionError("Confirm Password cannot be empty");
			return ERROR;
		}
		if(confirmPassword.equals(newPassword)){
			checkFlag=1;
		}
		if(checkFlag==0){
			addActionError("The new password and confirm password do not match !!!");
			return ERROR;
		}
		if(checkPassword.equals(oldEncryptedPassword)){
			try{
				java.sql.Connection con=DBManager.getConnection();
				DBManager.useDataBase(con);
				sqlQuery="update customer set password=? where userName = ?";
				statement=con.prepareStatement(sqlQuery);
				statement.setString(2, userName);
				statement.setString(1, newEncryptedPassword);
				res = statement.executeUpdate(); 

			} catch( Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}
		else 
			addActionError("Please enter correct old password !!!");
			return ERROR;

	}
}
