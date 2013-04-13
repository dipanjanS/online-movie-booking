<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});
</script>
</head>
<body>
	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);">
		<br /> <br /> <br /> <br />
		<h3 align="center">Booking History</h3>
		<hr size="4" color="#009987">
		<div class="row-fluid">
			<div class="span12">
				<table class="table">
					<thead>
						<tr>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Transaction#</h4></th>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Movie</h4></th>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Show
									Date</h4></th>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Show
									Time</h4></th>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Seats
									Count</h4></th>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Amount
									Paid</h4></th>
							<th style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Cancel Booking</h4></th>
						</tr>
					</thead>

					<tbody>
						<s:iterator value="bookHistoryList">
							<tr>
								<td style='text-align: left; vertical-align: middle; font-size: 18px'><s:property value="transactionNum" /></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px'><a
									href="movieDetail.action?movie=<s:property value="movieName"/>">
										<s:property value="movieName" />
								</a></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px'><s:property
										value="showDate" /></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px'><s:property
										value="showTime" /></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px'><s:property
										value="totalSeats" /></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px'><s:property
										value="amount" /></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px'>
									<a class="btn btn-danger btn-block"
									href="cancelBooking.action?bookingId=<s:property value="bookingId"/>"><i class="icon-remove"></i><b>&nbsp;&nbsp;Cancel Booking</b></a>
								</td>
							</tr>
						</s:iterator>
					<tbody>
				</table>
			</div>
		</div>

		<br /> <br /> <br />
	</div>
</body>

</html>