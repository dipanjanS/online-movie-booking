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
<title>Cannot Sign In</title>
</head>
<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);">
		<br /> <br /> <br />



		<div>
			<h1 align="center">Having trouble signing in?</h1>
			<hr size="4" color="#009987">
			<br />

		</div>
		<br />
<div>
		<s:if test="hasActionErrors()">
			<s:iterator value="actionErrors">
				<div class="span9">
					<div class="alert alert-error">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<s:property />
					</div>
				</div>
			</s:iterator>
		</s:if>

<br/><br/>
		<div id="accordion">
			<h3>I don't know my password</h3>
			<ul>
				<s:form action="forgotPassword.action" method="post" theme="simple">
					<li><div align="justify">
							To reset your password, enter the username you use to sign in to
							Book My Movie.<br /> <br />
						</div></li>

					<li><b>Email Address</b></li>
					<li><s:textfield name="email" theme="simple"
							cssClass="input-xxlarge"
							placeholder="Please enter your email Address" /></li>
					<li><s:submit cssClass="btn btn-large btn-primary"
							value="Retrieve password" align="center" /></li>
				</s:form>

			</ul>
			<h3>I don't know my username</h3>
			<ul>
				<s:form action="forgotUserName.action" method="post" theme="simple">
					<li><div>
							Enter your Email Address<br /> <br />
						</div></li>
					<li><s:textfield name="email" theme="simple" rel="popover"
							title="Hint:You may have added this email address to your account when you signed up for Book My Movie"
							cssClass="input-xxlarge"
							placeholder="Please enter your email Address" /></li>
					<li><s:submit cssClass="btn btn-large btn-primary"
							value="Retrieve UserName" align="center" /></li>
				</s:form>
			</ul>
		</div>

		<br /> <br />
	</div>
</div>
</body>
</html>