<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});
</script>
</head>
<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);"
		align="center">

		<br /> <br /> <br /> <br />
		<h3>Change Your Password</h3><br />
		<s:form action="successChangePassword.action" method="post"
				theme="simple">
		
		
		
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
		
		<div align="center">
				<table>

					<tbody>
						<tr>
							<td
								style='text-align: left; vertical-align: middle; font-size: 18px;'><b><font
									color="red">*</font>Enter Old Password:</b></td>
							<td style='vertical-align: middle; height: 60px;padding-left: 30px;'><s:password
									name="oldPassword" theme="simple" cssClass="input-xlarge"
									placeholder="Please enter old password" /></td>
						</tr>
						<tr>
							<td
								style='text-align: left; vertical-align: middle; font-size: 18px;'><b><font
									color="red">*</font>Enter New Password:</b></td>
							<td style='vertical-align: middle; height: 60px;padding-left: 30px;'><s:password
									name="newPassword" theme="simple" cssClass="input-xlarge"
									placeholder="Please enter new password" /></td>
									<tr>
							<td
								style='text-align: left; vertical-align: middle; font-size: 18px;'><b><font
									color="red">*</font>Confirm Password:</b></td>
							<td style='vertical-align: middle; height: 60px;padding-left: 30px;'><s:password
									name="confirmPassword" theme="simple" cssClass="input-xlarge"
									placeholder="Confirm Password" /></td>
						</tr>
						</tr>
					</tbody>
				</table>
			
		</div>
		<div align="center">
		<br/><br/><br/>
		<s:submit cssClass="btn btn-large btn-primary" value="Submit"
				align="center" />
				<br/><br/><br/><br/>
		</div>
		</s:form>
	</div>

</body>
</html>