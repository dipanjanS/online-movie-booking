<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>

<link rel="stylesheet" href="resources/chosenselect/chosen.css" />
<script src="resources/chosenselect/chosen.jquery.js"></script>
<script type="text/javascript">
	//$('#myModal').modal();

	jQuery(document).ready(function($) {
                $('#myModal').modal({
                	'show':true,
                	'backdrop'  : 'static'
                
                });
                

        });
	
		
	function passVal(){
			window.location.href='seatSelection.action?scheduleId=<s:property value="scheduleId"/>&quantityList='+document.getElementById('quantityList').value;
		
	}
	
	$(function() {
		$('.chzn-select').chosen();
		$('.chzn-select-deselect').chosen({
			allow_single_deselect : true
		});
	});
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);height: 700px">

		<br /> <br /> <br /> <br /><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/>
		<div align="center">
			<font style="font-size: 35px; color: red;">Processing your request...</font>
		</div>
	<form name="myForm">
		<div id="myModal" class="modal hide fade" class="span9">
			<div class="modal-header">

				<h3 id="myModalLabel">
					<s:property value="movieName" />
				</h3>

			</div>
			<div style="padding-left: 30px">
				<br /> <b>Number of seats:&nbsp;&nbsp;</b>
				<s:select name="quantityList" id="quantityList" list="quantityList"
					theme="simple" cssClass="chzn-select"/>



			</div>
			<div align="center">
				<br />
				<br /> <a href="javascript:passVal();" class="btn btn-primary"><i class="icon-refresh icon-spin"></i>&nbsp;&nbsp;Proceed
					to seat selection</a> <br />
				<br />
			</div>
			<div class="modal-footer">

				<div align="left">
					Please select the number of seats you want to book. Your request is being
					processed
					<div class="progress progress-danger progress-striped active">
						<div class="bar" style="width: 95%;"></div>
					</div>
					<hr size="3" color="gray">
					<div align="justify">
						<b>Note: &nbsp;</b>A convenience charge (per ticket) will be levied.
						Check the same before completing the transaction. Please Note: For
						3D Glasses a USAGE CHARGE (non-refundable) between Rs.20 to Rs.30
						& DEPOSIT (refundable) between Rs.50 to 100 will have to be paid
						per ticket at the Cinema. The exact amount will be intimated at
						the cinema.

					</div>

				</div>
			</div>
		</div>
	</form>

</div>
</body>
</html>