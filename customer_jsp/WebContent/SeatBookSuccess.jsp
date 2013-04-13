<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});
</script>
</head>
<script>
function flash() {
	var text = document.getElementById('flash');
	text.style.color = (text.style.color == 'red') ? 'blue' : 'red';
}
var clr = setInterval(flash, 300);

</script>
<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);"
		align="center">
		<br /> <br /> <br /> <br />
		<h3 align="center" id="flash">Congratulations! Your ticket has been successfully booked.</h3>
		<br/><br/>
		<s:form action="generateTicket" method="post">
			<div>
			<table class="span6 offset5">
				<tbody>
					
					<tr>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Username: </b></td>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property value="%{#session.userSession}" /></b></td>
					</tr>
					<tr>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Seat Quantity: </b></td>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property value="quantityList" /></b></td>
					</tr>
					<tr>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Seats Booked: </b></td>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property value="confirmedSeats" /></b></td>
					</tr>
					<tr>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Transaction Number: </b></td>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property value="transactionNum" /></b></td>
					</tr>
					<tr>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Total Price: </b></td>
						<td style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property value="totalPrice" /></b></td>
					</tr>
				</tbody>
			</table>
			</div>


			<div style="background-image: url(siteimages/bodypattern.png);">
			<s:hidden name="bookingId" value="%{bookingId}" />
			<s:hidden name="transactionNum" value="%{transactionNum}" />
			<s:hidden name="seatNums" value="%{confirmedSeats}" />
			<s:hidden name="totalPrice" value="%{totalPrice}" />
			<div align="center" style="background-image: url(siteimages/bodypattern.png);">
			<br/><br/><br/>
			<s:submit name="commandButton" value="GENERATE E-TICKET"
				align="center" cssClass="btn btn-large btn-inverse" />
				<br/><br/><br/><br/>
			</div>
			<br/><br/><br/><br/><br/>
			</div>
		</s:form>
	</div>
</body>

</html>