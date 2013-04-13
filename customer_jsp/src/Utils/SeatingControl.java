package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import edu.iiitb.bookmyshow.action.SeatSelection;

public class SeatingControl {
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	static Random rand;

	public static SeatSelection getDetails(Connection con, int scheduleId){

		SeatSelection details = new SeatSelection();

		try{
			DBManager.useDataBase(con);

			sqlQuery = "select genClass, silverClass, goldClass from schedule where scheduleID = ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, scheduleId);
			rs=statement.executeQuery();

			while(rs.next()){
				details.setGenPrice(rs.getInt(1));
				details.setSilverPrice(rs.getInt(2));
				details.setGoldPrice(rs.getInt(3));												
			}

			sqlQuery = "select genCapacity, silverCapacity, goldCapacity from screen, schedule where schedule.screenID = screen.screenID and schedule.scheduleID= ? ;";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, scheduleId);
			rs=statement.executeQuery();

			while (rs.next()){
				int genCapacity = rs.getInt(1);
				details.setGenRows(genCapacity/10);
				int silverCapacity = rs.getInt(2);
				details.setSilverRows(silverCapacity/10);
				int goldCapacity = rs.getInt(3);
				details.setGoldRows(goldCapacity/10);				
			}

			sqlQuery = "select seatNum from seatmap,bookedseats,bookinginfo,schedule where bookedseats.bookingID = bookinginfo.bookingID and bookedseats.seatMapID = seatmap.seatMapID and bookinginfo.scheduleID = schedule.scheduleID and schedule.scheduleID = ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, scheduleId);
			rs=statement.executeQuery();

			String preBookedSeats = "";

			while (rs.next()){
				preBookedSeats += rs.getString(1);
				preBookedSeats += " ";
			}

			details.setPreBooked(preBookedSeats);
			con.close();

		}


		catch (Exception e) {

			e.printStackTrace();
		}

		return details;
	}

	public static int getBalance(Connection con, String username){

		int balance=0;

		try{

			DBManager.useDataBase(con);

			sqlQuery = "select balance from customer where username = ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setString(1, username);
			rs=statement.executeQuery();

			while(rs.next()){
				balance = rs.getInt(1);
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return balance;

	}

	public static int updateBalance(Connection con, String username, int balance){

		int res=0;

		try{

			DBManager.useDataBase(con);

			sqlQuery = "update customer set balance = ? where username = ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, balance);
			statement.setString(2, username);
			res=statement.executeUpdate();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return res;

	}

	public static int getCustomerID(Connection con, String username){

		int customerID=0;

		try{

			DBManager.useDataBase(con);

			sqlQuery = "select customerID from customer where username = ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setString(1, username);
			rs=statement.executeQuery();

			while(rs.next()){
				customerID = rs.getInt(1);
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return customerID;

	}

	public static int bookTotalSeats(Connection con, int customerID, int scheduleID, int seatQuantity){

		int bookingId = 0;
		

		try{

			DBManager.useDataBase(con);
			
			java.sql.Time bookingTime = new java.sql.Time(System.currentTimeMillis());
			java.sql.Date bookingDate = new java.sql.Date(System.currentTimeMillis());
			
			sqlQuery = "insert into bookinginfo(customerID,scheduleID,totalSeats,bookTime,bookDate) values(?,?,?,?,?);";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, customerID);
			statement.setInt(2, scheduleID);
			statement.setInt(3, seatQuantity);
			statement.setTime(4, bookingTime);
			statement.setDate(5, bookingDate);
			statement.executeUpdate();
			
			sqlQuery = "select bookingID from bookinginfo where customerID = ? and bookTime = ? and bookDate = ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, customerID);
			statement.setTime(2, bookingTime);
			statement.setDate(3, bookingDate);
			rs=statement.executeQuery();

			while(rs.next()){
				bookingId = rs.getInt(1);
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return bookingId;

	}

	public static int bookSeatNums(Connection con, int bookingID, String seatString){

		int res=0;

		try{

			DBManager.useDataBase(con);

			String[] seatNums = seatString.split(" ");
			System.out.println(seatNums.length);
			int[] seatmapIDs = new int[seatNums.length];
			for(int i = 0; i< seatNums.length; i++){
				String seatNumber = seatNums[i];
				System.out.println(seatNumber);
				sqlQuery = "select seatmapID from seatmap s, bookinginfo bi, schedule sc where bi.bookingID=? and bi.scheduleID = sc.scheduleID and sc.screenID = s.screenID and seatNum=?;";
				statement=con.prepareStatement(sqlQuery);
				statement.setInt(1, bookingID);
				statement.setString(2, seatNumber);
				rs=statement.executeQuery();
				while(rs.next()){
					seatmapIDs[i] = rs.getInt(1);
				}
			}

			for(int i = 0; i < seatmapIDs.length; i++){
				sqlQuery = "insert into bookedseats(bookingID,seatMapID) values(?,?);";
				statement=con.prepareStatement(sqlQuery);
				statement.setInt(1, bookingID);
				statement.setInt(2, seatmapIDs[i]);
				res=statement.executeUpdate();				
			}	
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public static int processTransaction(Connection con, int bookingID, int price){

		int res=0;

		rand = new Random();
		int randomNum = rand.nextInt(1547483647 - 147483647 + 1) + 147483647;
		System.out.println(randomNum);

		try{

			DBManager.useDataBase(con);

			sqlQuery = "insert into transaction(bookingID,transactionNum,amount) values(?,?,?);";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, bookingID);
			statement.setInt(2, randomNum);
			statement.setInt(3, price);
			res=statement.executeUpdate();
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return res;

	}
	
	public static int bookTicket(Connection con, int bookingID){

		int res=0;

		rand = new Random();
		int randomNum = rand.nextInt(1547483647 - 147483647 + 1) + 147483647;
		System.out.println(randomNum);
		int transactionId = 0;
		try{

			DBManager.useDataBase(con);
			
			sqlQuery = "select transactionID from transaction where bookingID= ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, bookingID);
			rs=statement.executeQuery();
			
			while(rs.next()){
				transactionId = rs.getInt(1);
			}

			sqlQuery = "insert into ticket(bookingID,transactionID,ticketNum) values(?,?,?);";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, bookingID);
			statement.setInt(2, transactionId);
			statement.setInt(3, randomNum);
			res=statement.executeUpdate();
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return res;

	}
	
	
	public static String getTransactionNum(Connection con, int bookingID){

		String transNum ="";

		try{

			DBManager.useDataBase(con);
			
			sqlQuery = "select transaction.transactionNum from transaction, bookinginfo where transaction.bookingID = bookinginfo.bookingID and bookinginfo.bookingID= ?;";
			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, bookingID);
			rs=statement.executeQuery();
			
			while(rs.next()){
				transNum = rs.getString(1);
			}
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return transNum;
	}
	
	
	
}
