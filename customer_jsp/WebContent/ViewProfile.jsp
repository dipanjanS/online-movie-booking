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
			User Profile Page<br /> <br />
		</h3>
		<div align="center" class="span10 offset5">
			<table class="table">
				<tr>

					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>User
						Name :</b></td>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property
							value="userName" /></b></td>
				</tr>

				<tr>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Email
						Id :</b></td>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property
							value="emailID" /></b></td>
				</tr>

				<tr>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Phone
						:</b></td>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property
							value="phone" /></b></td>
				</tr>

				<tr>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>First
						Name :</b></td>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property
							value="firstName" /></b></td>
				</tr>

				<tr>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Middle
						Name :</b></td>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property
							value="middleName" /></b></td>
				</tr>

				<tr>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Last
						Name :</b></td>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property
							value="lastName" /></b></td>
				</tr>
				<tr>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b>Current
						Balance :</b></td>
					<td
						style='text-align: left; vertical-align: middle; font-size: 18px; height: 40px;'><b><s:property
							value="balance" /></b></td>
				</tr>
			</table>
			

		</div>
		<br />
		<br />
		<br /><br />
		<br />
		<div align="center" class="span10 offset3">
		<a href="editProfile.action?userName=<s:property value="userName"/>"
					class="btn btn-primary">Edit Profile</a>
					<br/><br/><br/><br/><br/>
		</div>
	</div>
</body>
</html>