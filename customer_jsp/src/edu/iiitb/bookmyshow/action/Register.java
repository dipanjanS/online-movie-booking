package edu.iiitb.bookmyshow.action;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import Utils.DBManager;
import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.CheckValidEmail;

public class Register extends ActionSupport{
	String userName;
	String password;
	String email;
	String phone;
	String firstName;
	String middleName;
	String lastName;
	String from;
	String senderPassword;
	String body;
	ArrayList<String> validateUserName=new ArrayList<String>();

	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	int res=0;
	int checkExistingEmail;
	Boolean flag=true;
	Map session=ActionContext.getContext().getSession();
	static Properties properties = new Properties();
	static
	{
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}

	public String registration(){
		System.out.println("in registration.....");
		return SUCCESS;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public static Properties getProperties() {
		return properties;
	}
	public static void setProperties(Properties properties) {
		Register.properties = properties;
	}
	public String getSenderPassword() {
		return senderPassword;
	}
	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String successful(){
		if(ActionContext.getContext().getSession().get("userSession")==null){
			checkExistingEmail=CheckValidEmail.validity(email);
			if(userName.equals("")){
				addActionError("User name cannot be empty");
				return ERROR;
			}
			else if(password.equals("")){
				addActionError("Password cannot be empty");
				return ERROR;
			}
			else if(firstName.equals("")){
				addActionError("First Name cannot be empty");
				return ERROR;
			}
			else if(lastName.equals("")){
				addActionError("Last Name cannot be empty");
				return ERROR;
			}
			else if(email.equals("")){
				addActionError("Email cannot be empty");
				return ERROR;
			}
			else if(phone.equals("")){
				addActionError("Phone Number cannot be empty");
				return ERROR;
			}
			else if(phone.length()<10){
				addActionError("Phone Number should be of 10 digits !!!");
				return ERROR;
			}
			/*else if(email.isEmpty()==false){
				Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
				 Matcher m = p.matcher(email);
				 boolean matchFound = m.matches();
				 if(matchFound==false){
					 addActionError("Email is invalid !!!");
					 flag=false;
				 }

			}
			 */
			
			else if(checkExistingEmail==0){
				addActionError("Email address already in use. Please use different email address");
				return ERROR;
			}
			
			else {

				if(email.isEmpty()==false){
					Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
					Matcher m = p.matcher(email);
					boolean matchFound = m.matches();
					if(matchFound==false){
						addActionError("Email is invalid !!!");
						return ERROR;
					}
				}
				try{
					java.sql.Connection con=DBManager.getConnection();
					DBManager.useDataBase(con);

					sqlQuery="select userName from customer;";
					statement=con.prepareStatement(sqlQuery);
					rs=statement.executeQuery();
					while(rs.next()){
						if(rs.getString(1).equals(userName)){
							addActionError("User Name already exists, Please choose some other user name");
							return ERROR;
						}

					}
					
					//encryption
					String encryptedPassword=EncryptPassword(password);

					sqlQuery = "insert into customer(userName,password,emailId,firstName,middleName,lastName,phone,balance) values(?,?,?,?,?,?,?,?);";
					statement=con.prepareStatement(sqlQuery);
					statement.setString(1, userName);
					statement.setString(2, encryptedPassword);
					statement.setString(3, email);
					statement.setString(4, firstName);
					statement.setString(5, middleName);
					statement.setString(6, lastName);
					statement.setString(7, phone);
					statement.setInt(8, 2000);
					res = statement.executeUpdate(); 

					try{
						//code to get customerID after the latest insert (above)
						sqlQuery = "SELECT MAX(customerID) FROM customer;";
						statement=con.prepareStatement(sqlQuery);
						rs=statement.executeQuery();
						while(rs.next()){
							if(rs.getInt(1)>0)
								session.put("customerID", rs.getInt(1));
						}
					}
					catch( Exception e){
						e.printStackTrace();
					}

				} catch( Exception e){
					e.printStackTrace();
				}
				session.put("userSession",userName);
				setFrom("bookmymovienow@gmail.com");
				setSenderPassword("dipRituSuv");
				body="Welcome "+firstName+" to Book My Movie. Now enjoy booking movie tickets online. You are now successfully registerd to our website. ";
				try
				{
					Session session = Session.getDefaultInstance(properties,  
							new javax.mail.Authenticator() {
						protected PasswordAuthentication 
						getPasswordAuthentication() {
							return new 
									PasswordAuthentication(from,senderPassword );
						}});

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.setRecipients(Message.RecipientType.TO, 
							InternetAddress.parse(email));
					message.setSubject("Registration Successul");
					message.setText(body);

					Transport.send(message);
				}
				catch(Exception e)
				{

					e.printStackTrace();
				}


				return SUCCESS;
			}
		}
		else{
			addActionError("You are already signed in as  "+ActionContext.getContext().getSession().get("userSession").toString()+" Please logout to sign in or register as different user");
			return ERROR;
		}
	}

	//method for encryption
	public static String EncryptPassword (String passwd){

		byte[] unencodedPassword = passwd.getBytes();
		MessageDigest md = null;

		try{
			md = MessageDigest.getInstance("MD5");
		} 
		catch (Exception e) {}

		md.reset();
		md.update(unencodedPassword);

		byte[] encodedPassword = md.digest();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if (((int) encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) encodedPassword[i] & 0xff, 16));
		}
		String passw=buf.toString(); 		
		return passw;
	}
}