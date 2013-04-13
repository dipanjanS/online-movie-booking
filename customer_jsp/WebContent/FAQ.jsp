<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	$(function() {
		$("#accordion").accordion({
			collapsible : true,
			heightStyle : "content"
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});
</script>
<title>FAQ</title>
</head>
<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);">
		<br /> <br /> <br /> <br /> <br />



		<div>
			<h1 align="center">
				<i class="icon-question-sign icon-1x"></i>&nbsp;&nbsp;Frequently
				Asked Questions
			</h1>
			<hr size="4" color="#009987">
			<br />

		</div>
		<br />
		<div id="accordion">
			<h3>How to book tickets from 'Book My Movie' ?</h3>
			<div>
				<ol>
					<li>Sign In or Register with the site</li>
					<li>Select the city, movie and date and search for theatre</li>
					<li>Select the theatre of your choice and view the schedule</li>
					<li>Select the schedule and proceed to select the seats</li>
					<li>Select the number of seats and proceed to seat selection</li>
					<li>Book your tickets and make the payment</li>

				</ol>
			</div>
			<h3>How to Register</h3>
			<div>
				<ol>
					<li>Go to to the Register page</li>
					<li>Enter the require(* marked fields)</li>
					<li>Click on register to be registered member of the site</li>

				</ol>
			</div>
			<h3>View Booked History</h3>
			<div>
				<ol>
					<li>Sign in with the site</li>
					<li>Click on view booked history to view the list of your
						transactions</li>

				</ol>
			</div>
			<h3>Cancel Booking</h3>
			<div>
				<ol>
					<li>Sign in with the site</li>
					<li>Click on view booked history to view the list of your
						transactions</li>
					<li>Click on the button 'Cancel Booking' to cancel the
						transaction</li>
					<li>Amount will be successfully refunded to your account</li>
				</ol>
			</div>
			<h3>Cancellation and Refund Policy</h3>
			<div>
				<ol>
					<li>You can cancel any booking made by you easily</li>
					<li>Cancellations can be made for shows booked atleast one day in advance</li>
					<li>You cannot cancel any tickets booked by you on the same day</li>
					<li>All successful cancellations will refund the correct amount to your account</li>
				</ol>
			</div>
			<h3>Is the site really free?</h3>
			<div>
				<ol>
					<li>Registration is completely free</li>
					<li>Booking require you to have some balance in your account</li>
					<li>You cannot book tickets if your account balance is too low</li>
					<li>All successful cancellations will refund the correct amount to your account</li>
				</ol>
			</div>
		</div>

		<br /> <br />
	</div>

</body>
</html>