package edu.iiitb.bookmyshow.action;

import java.sql.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import Utils.DBManager;
import edu.iiitb.bookmyshow.model.TicketFormat;

public class TicketGeneration {

	int bookingId;
	String transactionNum;
	String seatNums;
	int totalPrice;


	static PdfWriter writer;
	private InputStream fileStream;
	private static String FILE = "e:/First.pdf";

	private static Font catFont = new Font(Font.FontFamily.HELVETICA, 20,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.HELVETICA, 14,
			Font.BOLD, BaseColor.RED);

	private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 14,
			Font.BOLD);


	public void setFileStream(InputStream arg) {
		fileStream = arg;
	}

	public InputStream getFileStream() {
		return fileStream;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

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

	public String getSeatNums() {
		return seatNums;
	}

	public void setSeatNums(String seatNums) {
		this.seatNums = seatNums;
	}

	public String generateTicket() throws Exception{
		System.out.println(bookingId+"....."+transactionNum+"......"+seatNums+"...."+totalPrice);

		TicketFormat ticket = new TicketFormat();
		Connection con = DBManager.getConnection();
		String sqlQuery = "";
		PreparedStatement statement=null;
		ResultSet rs=null;

		String username = "", fName = "", mName = "",lName = "", name = "",
				ticketNum = "", transactionNum = "", movieName = "", movieUrl = "", 
				cinemaName = "", showDate = "", showTime = "";

		int screenNum = 0, totalSeats = 0;

		java.sql.Date date = null;
		java.sql.Time time = null;



		try{

			DBManager.useDataBase(con);

			sqlQuery = "select customer.userName, customer.firstName, customer.middleName, customer.lastName," +
					"ticket.ticketNum, transaction.transactionNum, movie.movieName, movie.imageURL, cinema.cinemaName, " +
					"screen.screenNum, bookinginfo.totalSeats, schedule.showDate, schedule.showtime from customer, ticket, " +
					"transaction, movie, cinema, screen, bookinginfo, schedule where bookinginfo.bookingID = ticket.bookingID " +
					"and ticket.transactionID = transaction.transactionID and bookinginfo.customerID = customer.customerID " +
					"and bookinginfo.scheduleID = schedule.scheduleID and schedule.screenID = screen.screenID " +
					"and cinema.cinemaID = screen.cinemaID and movie.movieID = schedule.movieID and bookinginfo.bookingID = ?;";

			statement=con.prepareStatement(sqlQuery);
			statement.setInt(1, bookingId);
			rs=statement.executeQuery();

			while(rs.next()){

				username = rs.getString(1);
				fName = rs.getString(2);
				mName = rs.getString(3);
				lName = rs.getString(4);
				ticketNum = rs.getString(5);
				transactionNum = rs.getString(6);
				movieName = rs.getString(7);
				movieUrl = rs.getString(8);
				cinemaName = rs.getString(9);
				screenNum = rs.getInt(10);
				totalSeats = rs.getInt(11);
				date = rs.getDate(12);
				time = rs.getTime(13);

			}
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		if(mName == ""){
			name = fName + " " + lName;
		}
		else{
			name = fName + " " + mName + " " + lName;
		}

		Formatter fmt =new Formatter();
		showDate = fmt.format("%tA, %td/%tm/%tY",date, date, date, date).toString();
		fmt.close();

		fmt = new Formatter();
		showTime = fmt.format("%tr",time).toString();
		fmt.close();

		ticket.setCinemaName(cinemaName);
		ticket.setCustomerName(name);
		ticket.setMovieName(movieName);
		ticket.setMoviePoster(movieUrl);
		ticket.setScreenNumber(screenNum);
		ticket.setSeatNumbers(seatNums);
		ticket.setShowDate(showDate);
		ticket.setShowTime(showTime);
		ticket.setTicketNumber(ticketNum);
		ticket.setTicketPrice(totalPrice);
		ticket.setTotalSeats(totalSeats);
		ticket.setTransactionNumber(transactionNum);
		ticket.setUserName(username);


		try {

			Document document = new Document();
			writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTicketData(document, ticket);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			fileStream = new DataInputStream(new FileInputStream(FILE));
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}

		return "success";
	}


	private static void addMetaData(Document document) {

		document.addTitle("Book My Movie E-Ticket");
		document.addSubject("Auto generated movie ticket");
		document.addKeywords("movie, e-ticket");
		document.addAuthor("BookMyMovie Enterprises");
		document.addCreator("BookMyMovie Entertainment Enterprise");

	}

	private static void addTicketData(Document document, TicketFormat ticket) throws DocumentException, MalformedURLException, IOException {

		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);

		Chunk title = new Chunk("Book My Movie E-Ticket", catFont);
		title.setUnderline(BaseColor.DARK_GRAY, 3f, -0.1f , 30f, -2f , PdfContentByte.LINE_CAP_ROUND );

		preface.add(title);
		preface.setAlignment(Element.ALIGN_CENTER);
		preface.setIndentationLeft(150f);

		Paragraph p = new Paragraph(ticket.getMovieName(), catFont );
		p.setAlignment(Element.ALIGN_CENTER);
		p.setIndentationLeft(30f);

		addEmptyLine(preface, 1);
		String pic = ticket.getMoviePoster();

		addEmptyLine(p, 12);
		document.add(preface);
		document.add(p);

		PdfContentByte canvas = writer.getDirectContent();
		PdfTemplate celluloid = canvas.createTemplate(150f, 300f);

		celluloid.rectangle(8, 8, 579, 193);
		for (float f = 8.25f; f < 583; f+= 6.5f) {
			celluloid.roundRectangle(f, 8.5f, 6, 10, 1.5f);
			celluloid.roundRectangle(f, 190.5f, 6, 10, 1.5f);
		}
		celluloid.setGrayFill(0.1f);
		celluloid.eoFill();
		writer.releaseTemplate(celluloid);
		canvas.addTemplate(celluloid, 230f, 510f);

		Image img = Image.getInstance(String.format("E:/EclipseIndigo/customer_jsp/WebContent/images/%s", pic));
		img.scaleAbsolute(141f, 169f);

		img.setAbsolutePosition(238.5f, 530.5f);
		canvas.addImage(img);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{2,2});
		PdfPCell cell = new PdfPCell(new Phrase("E-Ticket Details",catFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		cell.setPadding(10);
		table.addCell(cell);


		cell = new PdfPCell(new Phrase("Username: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getUserName()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Customer Name: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getCustomerName()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);


		cell = new PdfPCell(new Phrase("Ticket Number: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getTicketNumber()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Transaction Number: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getTransactionNumber()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Movie Name: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getMovieName()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Cinema Theatre Name: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getCinemaName()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Screen Number: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(String.valueOf(ticket.getScreenNumber())));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Total Seats Booked: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(String.valueOf(ticket.getTotalSeats())));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Show Date: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getShowDate()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Show Time: "));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getShowTime()));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Seat Numbers: ",smallBold));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(ticket.getSeatNumbers(),smallBold));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);


		cell = new PdfPCell(new Phrase("Ticket Price: ",smallBold));
		cell.setPadding(10);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(String.valueOf(ticket.getTicketPrice()),redFont));
		cell.setPadding(10);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);

		Paragraph footer = new Paragraph();
		addEmptyLine(footer, 5);
		Chunk ch = new Chunk("Ticket generated at: " + new Date(), smallBold);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setIndentationLeft(110f);
		footer.add(ch);
		document.add(footer);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {

		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}

	}

}
