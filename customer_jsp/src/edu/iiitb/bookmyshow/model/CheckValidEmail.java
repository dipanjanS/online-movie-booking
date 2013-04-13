package edu.iiitb.bookmyshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Utils.DBManager;



public class CheckValidEmail {
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	static ArrayList<String>allEmail=new ArrayList<String>();
	static ArrayList<String>allUserName=new ArrayList<String>();
	static String userName;

	public static ArrayList<String> getAllUserName() {
		return allUserName;
	}

	public static void setAllUserName(ArrayList<String> allUserName) {
		CheckValidEmail.allUserName = allUserName;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		CheckValidEmail.userName = userName;
	}

	public ArrayList<String> getAllEmail() {
		return allEmail;
	}

	public void setAllEmail(ArrayList<String> allEmail) {
		this.allEmail = allEmail;
	}

	public static int validity(String email){
		try{
			Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = "select username,emailid from customer;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			
			while(rs.next()){
				allEmail.add(rs.getString(2));
				allUserName.add(rs.getString(1));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<allEmail.size();i++){
			if(allEmail.get(i).equals(email)){
				setUserName(allUserName.get(i));
				return 0;
			}
			
		}
		return 1;
	}
}
