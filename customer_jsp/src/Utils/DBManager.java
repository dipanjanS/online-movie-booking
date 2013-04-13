package Utils;


import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

public class DBManager {
	static java.sql.PreparedStatement statement=null;

	public static void main(String arg[]){

		DBManager.createDatabase();
	}

	/*
                        This function creates sql connection and returns a connection object
	 */
	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = RunTimeSettings.url
					+ RunTimeSettings.dbName;

			con = DriverManager.getConnection(connectionUrl,
					RunTimeSettings.dbUser, RunTimeSettings.dbPwd);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}

	/*
        This function creates the database "bookmyshow" if it dosent exists and calls other functions used for creating the tables
	 */

	public static void createDatabase(){
		try{
			Connection con=DBManager.getConnection();
			statement=con.prepareStatement("create database if not exists bookmymovie;");
			statement.execute();

			//calls the methods for table creations
			DBManager.useDataBase(con);
			DBManager.createCity(con);
			DBManager.createCinema(con);
			DBManager.createScreen(con);
			DBManager.createMovie(con);
			DBManager.createSchedule(con);
			DBManager.createCustomer(con);
			DBManager.createTicket(con);
			DBManager.createSeatMap(con);
			DBManager.createBookingInfo(con);
			DBManager.createBookedSeats(con);
			DBManager.createTransaction(con);

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/* This function creates city table
	 * It has two columns---city ID (number) and cityName (varchar(30))
	 *
	 */

	public static void useDataBase(Connection con){

		try {
		
			statement=con.prepareStatement("use bookmymovie;");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void createCity(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists city(cityID int(10) auto_increment primary key,cityName varchar(30) not null);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createCinema(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists cinema(cinemaID int auto_increment primary key,cinemaName varchar(100) not null,address varchar(100) not null,cityID int(10) not null,rating int not null,numOfScreens int not null,capacity int not null,foreign key(cityID) references city(cityID) on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public static void createScreen(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists screen(screenID int auto_increment primary key,cinemaID int not null,screenNum int not null,genCapacity int not null,silverCapacity int not null,goldCapacity int not null,totalCapacity int not null ,foreign key(cinemaID) references cinema(cinemaID)on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createMovie(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists movie(movieID int auto_increment primary key,movieName varchar(100) not null,storyLine text not null,imageURL varchar(250) not null,rating int not null,trailer varchar(250) not null,category varchar(250) not null,cast text not null);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createSchedule(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists schedule(scheduleID int auto_increment primary key,showDate date not null,showtime timestamp not null,screenID int not null,movieID int not null,genClass int ,silverClass int ,goldClass int,foreign key(screenID) references screen(screenID)on delete cascade on update cascade,foreign key(movieID) references movie(movieID)on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createCustomer(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists customer(customerID int auto_increment primary key,userName varchar(30)not null,password varchar(32)not null,emailID varchar(30) not null,firstName varchar(50) not null,middleName varchar(50),lastName varchar(50),phone varchar(20) not null,balance int(10) not null,lastVisit date,totalVisits int,totalTickets int);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createTicket(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists ticket(ticketID int auto_increment primary key,customerID int not null,scheduleID int not null,quantity int not null,price int not null,totalAmount int not null,seatNums int not null,dateSold date not null,ticketNum int unique not null,foreign key(customerID) references customer(customerID)on delete cascade on update cascade,foreign key(scheduleID) references schedule(scheduleID)on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createBookingInfo(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists bookingInfo(bookingID int auto_increment primary key,customerID int not null,scheduleID int not null,totalSeats int not null,foreign key(customerID) references customer(customerID)on delete cascade on update cascade,foreign key(scheduleID) references schedule(scheduleID)on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public static void createSeatMap(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists seatMap(seatMapID int auto_increment primary key,screenID int not null,seatClass varchar(8)not null,seatNum int not null,foreign key(screenID) references screen(screenID)on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createBookedSeats(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists bookedSeats(bookedSeatsID int auto_increment primary key,bookingID int not null,seatMapID int not null,foreign key(bookingID) references bookingInfo(bookingID)on delete cascade on update cascade,foreign key(seatMapID) references seatMap(seatMapID)on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public static void createTransaction(Connection con){
		try {
			statement=con.prepareStatement("create table if not exists transaction(transactionID int auto_increment primary key,bookingID int not null,transactionNum int unique not null,amount int not null,transactionDate date not null,foreign key(bookingID) references bookingInfo(bookingID)on delete cascade on update cascade);");
			statement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}