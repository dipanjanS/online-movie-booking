package edu.iiitb.bookmyshow.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Utils.DBManager;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.CheckValidEmail;
import edu.iiitb.bookmyshow.model.PasswordEncryption;

public class CannotSignIn extends ActionSupport{
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	int res=0;
	String email;
	String from;
	String body;
	String SenderPassword;
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

	public String getSenderPassword() {
		return SenderPassword;
	}
	public void setSenderPassword(String senderPassword) {
		SenderPassword = senderPassword;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public static Properties getProperties() {
		return properties;
	}
	public static void setProperties(Properties properties) {
		CannotSignIn.properties = properties;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String execute(){
		System.out.println("in cannot sign");
		return SUCCESS;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String forgotPassword(){
		System.out.println("in forgot password...."+email);
		int flag=CheckValidEmail.validity(email);
		if(email.equals("")){
			addActionError("Please fill email ID");
			return ERROR;
		}
		if(flag==1){
			addActionError("Please enter a valid email ID, entered Email is not registered with us");
			return ERROR;
		}
		else{
			StringBuffer sb = new StringBuffer();  
			for (int x = 0; x < 5; x++)  
			{  
				sb.append((char)((int)(Math.random()*26)+97));  
			}  
			System.out.println(sb.toString());
			setFrom("bookmymovienow@gmail.com");
			setSenderPassword("dipRituSuv");
			setBody("Your password is reset to "+sb.toString()+" \nPlease sign in with it the next time");
			try
			{
				Session session = Session.getDefaultInstance(properties,  
						new javax.mail.Authenticator() {
					protected PasswordAuthentication 
					getPasswordAuthentication() {
						return new 
								PasswordAuthentication(from,getSenderPassword() );
					}});

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, 
						InternetAddress.parse(email));
				message.setSubject("Forgot Password");
				message.setText(body);
				Transport.send(message);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			String encryptedPassword=PasswordEncryption.EncryptPassword(sb.toString());
			try{
				java.sql.Connection con=DBManager.getConnection();
				DBManager.useDataBase(con);
				sqlQuery = "update customer set password =? where userName=?;";
				statement=con.prepareStatement(sqlQuery);
				statement.setString(1, encryptedPassword);
				statement.setString(2, CheckValidEmail.getUserName());
				res = statement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
				return SUCCESS;
			}
		}
		public String forgotUserName(){
			System.out.println("in forgot user name...."+email);
			int flag=CheckValidEmail.validity(email);
			if(email.equals("")){
				addActionError("Please fill email ID");
				return ERROR;
			}
			if(flag==1){
				addActionError("Please enter a valid email ID, entered Email is not registered with us");
				return ERROR;
			}
			else{
				System.out.println(CheckValidEmail.getUserName());
				setFrom("bookmymovienow@gmail.com");
				setSenderPassword("dipRituSuv");
				setBody("Your user name is "+CheckValidEmail.getUserName()+" \nPlease sign in with it the next time");
				try
				{
					Session session = Session.getDefaultInstance(properties,  
							new javax.mail.Authenticator() {
						protected PasswordAuthentication 
						getPasswordAuthentication() {
							return new 
									PasswordAuthentication(from,getSenderPassword() );
						}});

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.setRecipients(Message.RecipientType.TO, 
							InternetAddress.parse(email));
					message.setSubject("Forgot User Name");
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
	}
