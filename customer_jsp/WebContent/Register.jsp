<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});
	
	function flash() {
		var text = document.getElementById('flash');
		text.style.color = (text.style.color == 'red') ? 'blue' : 'red';
	}
	var clr = setInterval(flash, 300);
</script>
</head>
<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);"
		align="center">

		<br /> <br /> <br /> <br />
		<h3 align="center">Don't have an account?&nbsp;&nbsp;&nbsp;<font id="flash">Register now, its free!</font></h3>
		<br /><br/>
		<s:if test="hasActionErrors()">
			<s:iterator value="actionErrors">
				<div class="span6 offset6" align="center">
					<div class="alert alert-error">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<s:property />
					</div>
				</div>
			</s:iterator>
		</s:if>
		<div class="span9 offset4">
		<s:form action="successfulRegistration.action" method="post" theme="simple">
		
			
		<table class="table">

				<tbody>
					<tr>
						<td
							style='text-align: center; vertical-align: middle; font-size: 18px;'><b><font
								color="red">*</font>User Name:</b></td>
						<td
							style='vertical-align: middle; height: 60px'><s:textfield
								name="userName" theme="simple" cssClass="input-xxlarge" placeholder="Please enter your username"/></td>
					</tr>
					<tr>
						<td
							style='text-align: center; vertical-align: middle; font-size: 18px'><b><font
								color="red">*</font>Password:</b></td>
						<td
							style='vertical-align: middle; height: 60px'><s:password
								name="password" theme="simple" cssClass="input-xxlarge" placeholder="Please enter your password"/></td>
					</tr>

					<tr>
						<td
							style='text-align: center; vertical-align: middle; font-size: 18px'><b><font
								color="red">*</font>Email:</b></td>
						<td
							style='vertical-align: middle; height: 60px'><s:textfield
								name="email" theme="simple" cssClass="input-xxlarge" placeholder="Please enter your e-mail address"/></td>
					</tr>

					<tr>
						<td
							style='text-align: center; vertical-align: middle; font-size: 18px'><b><font
								color="red">*</font>Phone:</b></td>
						<td
							style='vertical-align: middle; height: 60px'><s:textfield
								name="phone" theme="simple" cssClass="input-xxlarge" placeholder="Please enter your phone number"/></td>
					</tr>

					<tr>
						<td
							style='text-align: center; vertical-align: middle; font-size: 18px'><b><font
								color="red">*</font>First Name:</b></td>
						<td
							style='vertical-align: middle; height: 60px'><s:textfield
								name="firstName" theme="simple" cssClass="input-xxlarge" placeholder="Please enter your first name"/></td>
					</tr>

					<tr>
						<td
							style='text-align: center; vertical-align: middle; font-size: 18px'><b>Middle
								Name:</b></td>
						<td
							style= 'vertical-align: middle; height: 60px'><s:textfield
								name="middleName" theme="simple" cssClass="input-xxlarge" placeholder="Please enter your middle name (optional)"/></td>
					</tr>

					<tr>
						<td
							style='text-align: center; vertical-align: middle; font-size: 18px'><b><font
								color="red">*</font>Last Name:</b></td>
						<td
							style='vertical-align: middle; height: 60px'><s:textfield
								name="lastName" theme="simple" cssClass="input-xxlarge" placeholder="Please enter your last name"/></td>
					</tr>

				</tbody>

			</table>
		
			<br />
			<s:submit cssClass="btn btn-large btn-primary" value="Sign Up"
				align="center" />
			<br /><br /><br /><br />
		</s:form>
		</div>
		<br />
		<br />
		<br />
	</div>
</body>

</html>