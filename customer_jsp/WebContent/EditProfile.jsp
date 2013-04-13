<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		<h3 align="center">
			Edit your personal information<br /> <br />
		</h3>
		<div align="center" class="span12 offset4">


			<s:form action="updateProfile.action" method="post">

				<s:if test="hasActionErrors()">
					<div class="alert alert-block alert-error">
						<h4 class="alert-heading">Error!</h4>
						<div class="errors">
							<s:actionerror />
						</div>
					</div>
				</s:if>

				<table class="table">
					<s:hidden name="userName"></s:hidden>

					<tr>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><font
							color="red">*</font><b>User Name:</b></td>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><s:property
								value="userName" /></td>
					</tr>


					<tr>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><font
							color="red">*</font><b>Phone:</b></td>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><s:textfield
								name="phone" theme="simple" /></td>
					</tr>

					<tr>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><font
							color="red">*</font><b>First Name:</b></td>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><s:textfield
								name="firstName" theme="simple" /></td>
					</tr>

					<tr>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Middle
								Name:</b></td>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><s:textfield
								name="middleName" theme="simple" /></td>
					</tr>

					<tr>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><font
							color="red">*</font><b>Last Name:</b></td>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><s:textfield
								name="lastName" theme="simple" /></td>
					</tr>
					<tr>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><font
							color="red">*</font><b>Balance:</b></td>
						<td
							style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><s:textfield
								name="balance" theme="simple" /></td>
					</tr>

				</table>
				<div align="center" class="span6 offset1">
					<br/><br/><br/>
					<s:submit cssClass="btn btn-primary" value="Save Changes"
						theme="simple" />
						<br/><br/><br/><br/>
				</div>
			</s:form>


		</div>
	</div>
</body>
</html>