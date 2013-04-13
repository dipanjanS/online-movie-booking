<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to seat selection</title>
<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});
</script>
<script type="text/javascript">
	numGoldSeatsPerRow = 10;
	numSilverSeatsPerRow = 10;
	numGenSeatsPerRow = 10;

	statusPics = new Array();
	statusPics['avail'] = new Image();
	statusPics['mine'] = new Image();
	statusPics['taken'] = new Image();
	statusPics['unavail'] = new Image();
	statusPics['avail'].src = 'seatlayoutimages/available.jpg';
	statusPics['mine'].src = 'seatlayoutimages/selected.jpg';
	statusPics['taken'].src = 'seatlayoutimages/confirmed.jpg';
	statusPics['unavail'].src = 'seatlayoutimages/unavailable.jpg';

	function createSeats(oSeatsContainer, seatsPerRow, rowNums, seatClass) {

		var oRow = document.createElement('tr');
		oRow.appendChild(document.createElement('th'));

		oSeatsContainer.appendChild(oRow); //this row contains the seat numbers
		k = 1;

		for (i = 0; i < rowNums; i++) {
			var oRow = document.createElement('tr');

			for (j = 0; j < seatsPerRow; j++) {
				var oCell = document.createElement('td');
				//oCell.innerHTML = seatClass+(j+k);
				oRow.appendChild(oCell);

				oCell = document.createElement('td');

				var oImg = document.createElement('img');
				oImg.src = statusPics['avail'].src;
				oImg.status = 'avail';
				oImg.id = seatClass + (j + k);

				c = 0;
				for (; c < dis.length; c++) {
					var x = dis[c];
					if (seatClass + (j + k) == x) {
						oImg.src = statusPics['unavail'].src;
						oImg.status = 'taken';
						oImg.id = seatClass + (j + k);
					}
				}

				oImg.onclick = function() {
					if (this.status != 'taken') {

						this.status = (this.status == 'avail') ? 'mine'
								: 'avail';
						this.src = (this.status == 'avail') ? statusPics['avail'].src
								: statusPics['mine'].src;
					}
					oTotalprice.innerHTML = '';
					oBookedSeatNos.innerHTML = '';
					oBooked.value = '';
					oTotalprice.value = '';
					oBtnCheckout.disabled = true;
					oBtnBookSeats.disabled = true;
				}
				oCell.appendChild(oImg);
				oRow.appendChild(oCell);
			}
			oSeatsContainer.appendChild(oRow);
			k = k + seatsPerRow;
		}
	}

	function confirmChoices() {

		goldSeatsSelected = new Array();
		for (i = 0; i < oGoldSeats.length; i++) {
			if (oGoldSeats[i].status == 'mine') {
				goldSeatsSelected.push(oGoldSeats[i].id);
				console
						.log("The chosen gold seat value is "
								+ oGoldSeats[i].id);
				oGoldSeats[i].src = statusPics['taken'].src
			}
		}

		silverSeatsSelected = new Array();
		for (i = 0; i < oSilverSeats.length; i++) {
			if (oSilverSeats[i].status == 'mine') {
				silverSeatsSelected.push(oSilverSeats[i].id);
				console.log("The chosen silver seat value is "
						+ oSilverSeats[i].id);

				oSilverSeats[i].src = statusPics['taken'].src
			}
		}

		genSeatsSelected = new Array();
		for (i = 0; i < oGenSeats.length; i++) {
			if (oGenSeats[i].status == 'mine') {
				genSeatsSelected.push(oGenSeats[i].id);
				console.log("The chosen general seat value is "
						+ oGenSeats[i].id);
				oGenSeats[i].src = statusPics['taken'].src
			}
		}
		oBtnCheckout.disabled = ((goldSeatsSelected.length
				+ genSeatsSelected.length + silverSeatsSelected.length) == totalSeatNums) ? false
				: true;
	}

	function checkout() {

		var strBookedSeats;
		var totalPrice = (goldSeatsSelected.length * goldSeatPrice)
				+ (silverSeatsSelected.length * silverSeatPrice)
				+ (genSeatsSelected.length * genSeatPrice);

		strBookedSeats = '';

		console.log("The value of gold seats length "
				+ goldSeatsSelected.length);

		for (i = 0; i < goldSeatsSelected.length; i++) {
			strBookedSeats += goldSeatsSelected[i] + ' ';
		}

		console.log("The value of silver seats length "
				+ silverSeatsSelected.length);
		for (i = 0; i < silverSeatsSelected.length; i++) {
			strBookedSeats += silverSeatsSelected[i] + ' ';
		}

		console.log("The value of general seats length "
				+ genSeatsSelected.length);
		for (i = 0; i < genSeatsSelected.length; i++) {
			strBookedSeats += genSeatsSelected[i] + ' ';
		}

		oBookedSeatNos.innerHTML = strBookedSeats;
		oBooked.value = strBookedSeats;
		oTotalprice.value = totalPrice;
		oDispPrice.innerHTML = totalPrice;
		oBtnBookSeats.disabled = false;
	}

	window.onload = function() {

		GenRowNums = document.getElementById('genRows').value;
		console.log("The value of general row nums is " + GenRowNums);
		SilverRowNums = document.getElementById('silverRows').value;
		console.log("The value of silver row nums is " + SilverRowNums);
		GoldRowNums = document.getElementById('goldRows').value;
		console.log("The value of gold row nums is " + GoldRowNums);

		genSeatPrice = document.getElementById('genPrice').value;
		console.log("The value of general seat price is " + genSeatPrice);
		silverSeatPrice = document.getElementById('silverPrice').value;
		console.log("The value of silver seat price is " + silverSeatPrice);
		goldSeatPrice = document.getElementById('goldPrice').value;
		console.log("The value of gold seat price is " + goldSeatPrice);

		oTblGold = document.getElementById('tblGold');
		oTblSilver = document.getElementById('tblSilver');
		oTblGeneral = document.getElementById('tblGeneral');

		oGoldSeats = oTblGold.getElementsByTagName('img');
		oSilverSeats = oTblSilver.getElementsByTagName('img');
		oGenSeats = oTblGeneral.getElementsByTagName('img');

		oBtnCheckout = document.getElementById('checkout');
		oBtnBookSeats = document.getElementById('bookseats');
		oTotalprice = document.getElementById('totalprice');
		oDispPrice = document.getElementById('dispprice');

		oBookedSeatNos = document.getElementById('bookedSeatNos');
		oBooked = document.getElementById("booked");
		
		totalSeatNums = document.getElementById("seatLimit").value;
		console.log("The value of total number seats to be booked is "
				+ totalSeatNums);

		str = document.getElementById("preBooked").value
		dis = str.split(" ");
		console.log("The value of seats already booked are " + dis);

		oBtnCheckout.onclick = checkout;

		createSeats(oTblGold, numGoldSeatsPerRow, GoldRowNums, 'G'); //create premium seats
		createSeats(oTblSilver, numSilverSeatsPerRow, SilverRowNums, 'S');
		createSeats(oTblGeneral, numGenSeatsPerRow, GenRowNums, 'N'); //create cheap seats

		document.getElementById('confirm').onclick = confirmChoices;

		document.getElementById('btnReset').onclick = function() {

			oTotalprice.innerHTML = '';
			oDispPrice.innerHTML = '';
			oBtnCheckout.disabled = true;
			oBtnBookSeats.disabled = true;
			oBookedSeatNos.innerHTML = '';

			for (i = 0; i < oGoldSeats.length; i++) {
				if (oGoldSeats[i].status != 'taken') {
					oGoldSeats[i].src = statusPics['avail'].src;
					oGoldSeats[i].status = 'avail';
				}
			}

			for (i = 0; i < oSilverSeats.length; i++) {
				if (oSilverSeats[i].status != 'taken') {
					oSilverSeats[i].src = statusPics['avail'].src;
					oSilverSeats[i].status = 'avail';
				}

			}

			for (i = 0; i < oGenSeats.length; i++) {
				if (oGenSeats[i].status != 'taken') {
					oGenSeats[i].src = statusPics['avail'].src;
					oGenSeats[i].status = 'avail';
				}

			}
		}
		document.getElementById('imgAvail').src = statusPics['avail'].src;
		document.getElementById('imgMine').src = statusPics['mine'].src;
		document.getElementById('imgTaken').src = statusPics['taken'].src;
		document.getElementById('imgUnavail').src = statusPics['unavail'].src;

	}
</script>
</head>


<body>
	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);" align="center">

		<br /> <br /> <br /> <br />
		<h2 align="center">Seat Selection</h2><br /> <br />
		&nbsp;&nbsp;<img id="imgAvail" src="" alt="Available" /> =
				Available; <img id="imgMine" src="" alt="Mine" /> = Selected; <img
					id="imgTaken" src="" alt="Taken" /> = Confirmed <img
					id="imgUnavail" src="" alt="Unavailable" /> = Unavailable
					<br/><br/><br/>
		<s:form action="bookConfirmedSeats" method="post">

			<input id="genRows" type="hidden" name="genRows" value="${genRows}" />
			<input id="silverRows" type="hidden" name="silverRows"
				value="${silverRows}" />
			<input id="goldRows" type="hidden" name="goldRows"
				value="${goldRows}" />
			<input id="seatLimit" type="hidden" name="quantityList"
				value="${quantityList}" />
			<input id="preBooked" type="hidden" name="preBooked"
				value="${preBooked}" />
			<input id="genPrice" type="hidden" name="genPrice"
				value="${genPrice}" />
			<input id="silverPrice" type="hidden" name="silverPrice"
				value="${silverPrice}" />
			<input id="goldPrice" type="hidden" name="goldPrice"
				value="${goldPrice}" />
			<input id="totalprice" type="hidden" name="totalPrice" />
			<input id="booked" type="hidden" name="confirmedSeats" />
			<input type="hidden" name="scheduleId" value="${scheduleId}" />


			

				
					<img src="seatlayoutimages/screenlayout.jpg" /><br /> <br />
				
				
					<h4>
						General Class Seating
					</h4>
					
					<table id="tblGeneral" style="width: 320px;"></table>
				
					<br/><br/>
					<h4>
						Silver Class Seating
					</h4>
					<table id="tblSilver" style="width: 320px;"></table>
				
					<br/><br/>				
					<h4>
						Gold Class Seating
					</h4>
					<table id="tblGold" style="width: 320px;"></table>
				
					<br/><br/>
					<table>
						<tr>
							<td><b>Selected Seats:&nbsp;</b></td>
							<td><b><div id="bookedSeatNos"></div></b></td>
						</tr>
						<tr>
							<td><b>Total Price:&nbsp; Rs.&nbsp;</b></td>
							<td><b><div id="dispprice"></div></b></td>
						</tr>
					</table>
					<br/><br/>
		
		
				<input type="button" value="Confirm Choices" id="confirm" class="btn btn-primary"/>&nbsp;
				<input type="reset" id="btnReset" value="Reset" class="btn btn-danger"/> 
				<input type="button" id="checkout" value="Check Out" disabled="true" class="btn btn-success"/>
					<br/><br/>


			
				<s:submit id="bookseats" name="commandButton" value="BOOK SEATS" align="center"
					cssClass="btn btn-large btn-inverse" disabled="true"/>
				<br /> <br /> <br /> <br />
		</s:form>

	</div>
</body>
</html>