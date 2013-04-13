package edu.iiitb.bookmyshow.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import Utils.DBManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import edu.iiitb.bookmyshow.model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class BookedHistory extends ActionSupport{

	int bookingId, amount;

	ArrayList<TicketBooking> bookHistoryList = new ArrayList<TicketBooking>();

	Map session=ActionContext.getContext().getSession();

	PreparedStatement statement=null;
	ResultSet rs=null;
	String sqlQuery="";

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ArrayList<TicketBooking> getBookHistoryList() {
		return bookHistoryList;
	}

	public void setBookHistoryList(ArrayList<TicketBooking> bookHistoryList) {
		this.bookHistoryList = bookHistoryList;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String execute(){
		System.out.println();

		bookHistoryList.clear();

		if(session.get("customerID")==null)
			return ERROR;
		else{
			//getting CustomerId from session which was set in SignIn/ Register page
			int currentCustomerId=Integer.parseInt(session.get("customerID").toString());

			System.out.println("showing customerid in bookedHistory.java:"+currentCustomerId);

			bookHistoryList=TicketBooking.fetchCustomerBookings(currentCustomerId);
			//
			//String test = bookHistoryList.get(0).getShowDate();
			
			if(bookHistoryList.size()==0)
				return "noData";
			else{
				System.out.println("reversed show date....."+bookHistoryList.get(0).getShowDate());
				return SUCCESS;
			}
		}
	}

	public String cancellation(){

		//getting CustomerId from session which was set in SignIn/ Register page
		int currentCustomerId=Integer.parseInt(session.get("customerID").toString());

		try{
			Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);

			System.out.println("Booking id is:"+bookingId);

			//getting the amount to be refunded to the customer
			sqlQuery = "SELECT amount FROM transaction WHERE bookingID= "+ bookingId + ";";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();

			while(rs.next()){
				setAmount(rs.getInt(1));
			}

			try{			
				//updating the customer balance with the refund amount
				sqlQuery = "UPDATE customer SET balance = balance + " + getAmount() + " WHERE customerID= "+ currentCustomerId + ";";
				statement=con.prepareStatement(sqlQuery);
				statement.executeUpdate();

				try{
					//deleting the booking history
					sqlQuery = "DELETE FROM bookinginfo WHERE bookingID= "+ bookingId + ";";
					statement=con.prepareStatement(sqlQuery);
					statement.executeUpdate();
				}
				catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

}
