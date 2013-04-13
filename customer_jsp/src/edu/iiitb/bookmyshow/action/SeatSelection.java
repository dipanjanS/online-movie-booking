package edu.iiitb.bookmyshow.action;

import java.sql.Connection;
import java.util.Random;

import Utils.DBManager;
import Utils.SeatingControl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SeatSelection extends ActionSupport{

	Connection con=null;


	int scheduleId;
	int quantityList;

	int genPrice;
	int silverPrice;
	int goldPrice;
	int genRows;
	int silverRows;
	int goldRows;
	String preBooked;

	int totalPrice;
	String confirmedSeats;
	String username;
	
	int bookingId;
	String transactionNum;
	
	



	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getConfirmedSeats() {
		return confirmedSeats;
	}
	public void setConfirmedSeats(String confirmedSeats) {
		this.confirmedSeats = confirmedSeats;
	}
	public String getPreBooked() {
		return preBooked;
	}
	public void setPreBooked(String preBooked) {
		this.preBooked = preBooked;
	}
	public int getGenRows() {
		return genRows;
	}
	public void setGenRows(int genRows) {
		this.genRows = genRows;
	}
	public int getSilverRows() {
		return silverRows;
	}
	public void setSilverRows(int silverRows) {
		this.silverRows = silverRows;
	}
	public int getGoldRows() {
		return goldRows;
	}
	public void setGoldRows(int goldRows) {
		this.goldRows = goldRows;
	}
	public int getGenPrice() {
		return genPrice;
	}
	public void setGenPrice(int genPrice) {
		this.genPrice = genPrice;
	}
	public int getSilverPrice() {
		return silverPrice;
	}
	public void setSilverPrice(int silverPrice) {
		this.silverPrice = silverPrice;
	}
	public int getGoldPrice() {
		return goldPrice;
	}
	public void setGoldPrice(int goldPrice) {
		this.goldPrice = goldPrice;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getQuantityList() {
		return quantityList;
	}
	public void setQuantityList(int quantityList) {
		this.quantityList = quantityList;
	}


	public String selectSeats(){
		System.out.println("in seat selection.....");
		System.out.println("schedule id: "+scheduleId);
		System.out.println("quantity: "+quantityList);
		SeatSelection details = new SeatSelection();
		con = DBManager.getConnection();
		details = SeatingControl.getDetails(con, scheduleId);
		setGenPrice(details.getGenPrice());
		setSilverPrice(details.getSilverPrice());
		setGoldPrice(details.getGoldPrice());
		setGenRows(details.getGenRows());
		setSilverRows(details.getSilverRows());
		setGoldRows(details.getGoldRows());
		setPreBooked(details.getPreBooked());
		System.out.println(getPreBooked());
		return SUCCESS;
	}

	public String bookSeats(){

		int result = 0;
		int quantity = getQuantityList();
		int price = getTotalPrice();
		String seatString = getConfirmedSeats();
		System.out.println("Schedule id: "+scheduleId);
		System.out.println(quantity+".... "+price+"..... "+seatString);

		if(price==0 || seatString==null){
			System.out.println("Error nothing selected");
			return "error";
		}

		if (ActionContext.getContext().getSession().get("userSession")==null){
			System.out.println("no user logged in yet");
			return "signin";
		}
		else{

			setUsername(ActionContext.getContext().getSession().get("userSession").toString());
			System.out.println("user is "+username);
			con = DBManager.getConnection();			
			int balance = SeatingControl.getBalance(con, username);
			System.out.println("Balance is:"+balance);

			if((balance - price) < 0){
				System.out.println("sorry you have insufficient funds");
				return "addmoney";
			}
			else{

				con = DBManager.getConnection();
				int customerId = SeatingControl.getCustomerID(con, username);
				System.out.println("Customer ID is: "+customerId);

				con = DBManager.getConnection();
				int bookingID = SeatingControl.bookTotalSeats(con, customerId, scheduleId, quantity);

				if(bookingID == 0){
					System.out.println("Error occured in bookTotalSeats");
					return "error";
				}
				else{
					System.out.println("Successful update");
				}

				con = DBManager.getConnection();
				result = SeatingControl.bookSeatNums(con, bookingID, seatString);

				if(result == 0){
					System.out.println("Error occured in bookSeatNums");
					return "error";
				}
				else{
					System.out.println("Successful update");
					result = 0;
				}

				con = DBManager.getConnection();
				result = SeatingControl.processTransaction(con, bookingID, price);

				if(result == 0){
					System.out.println("Error occured in processTransaction");
					return "error";
				}
				else{
					System.out.println("Successful update");
					result = 0;
				}

				con = DBManager.getConnection();
				result = SeatingControl.bookTicket(con, bookingID);

				if(result == 0){
					System.out.println("Error occured in bookTicket");
					return "error";
				}
				else{
					System.out.println("Successful update");
					result = 0;
				}

				balance= balance - price;
				System.out.println("new balance is: "+balance);

				con = DBManager.getConnection();
				result = SeatingControl.updateBalance(con, username, balance);

				if(result == 0){
					System.out.println("Error occured in updateBalance");
					return "error";
				}
				else{
					System.out.println("Successful update");
				}
				setBookingId(bookingID);
				
				con = DBManager.getConnection();
				transactionNum = SeatingControl.getTransactionNum(con, bookingID);
				setTransactionNum(transactionNum);
				
				
			}
		}
		return SUCCESS;
	}
	
}
