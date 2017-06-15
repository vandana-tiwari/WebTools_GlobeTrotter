<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Globe-trotter</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700'
	rel='stylesheet' type='text/css'>
<link href="resources/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<script src="resources/js/jquery.min.js"></script>
<!---strat-date-piker---->
<link rel="stylesheet" href="resources/css/jquery-ui.css" />
<script src="resources/js/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker,#datepicker1").datepicker();
	});
</script>
<!---/End-date-piker---->
<link type="text/css" rel="stylesheet" href="resources/css/JFGrid.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/JFFormStyle-1.css" />
<script type="text/javascript" src="resources/js/JFCore.js"></script>
<script type="text/javascript" src="resources/js/JFForms.js"></script>
<!-- Set here the key for your domain in order to hide the watermark on the web server -->
<script type="text/javascript">
	(function() {
		JC.init({
			domainKey : ''
		});
	})();
</script>
<!--nav-->
<script>
	$(function() {
		var pull = $('#pull');
		menu = $('nav ul');
		menuHeight = menu.height();

		$(pull).on('click', function(e) {
			e.preventDefault();
			menu.slideToggle();
		});

		$(window).resize(function() {
			var w = $(window).width();
			if (w > 320 && menu.is(':hidden')) {
				menu.removeAttr('style');
			}
		});
	});
</script>
</head>

<body>
	<div class="header_bg">
		<div class="wrap">
			<div class="header">
				<div class="logo">
					<a href="index.html"><img src="resources/images/globetrotter.png"
						alt="" height ="50" width="200"></a>
				</div>
				<div class="h_right">
					<!--start menu -->
					<ul class="menu">


						<li><a href="home.htm">HOME</a></li> 
						<li style="list-style: none">|</li>
						<li><a href="apartments.htm">APARTMENTS</a></li> 
						<li style="list-style: none">|</li>
						<li><a href="contactus.htm">CONTACT US</a></li> 
						<li style="list-style: none">|</li>
						<!--  <li><a href="login.htm">LOGIN</a></li>-->
						<c:if test="${!empty sessionScope.loggedInUser}">
							<!--  Welcome ${sessionScope.loggedInUserName} ${sessionScope.loggedInUser}Name: -->

							<li><a href="acountInfo.htm">MY ACCOUNT</a></li> 
							<li style="list-style: none">|</li>
			<c:if test="${!empty sessionScope.isHost}">
								<li><a href="newAdvertisement.htm">POST A NEW ADVERTISEMENT</a></li>
								<li><a href="myListings.htm">MY LISTINGS</a></li> 
								<li style="list-style: none">|</li>
			</c:if>
							<li><a href="logout.htm">LOGOUT</a></li> 
							<li style="list-style: none">|</li>
		</c:if>

						<c:if test="${empty sessionScope.loggedInUser}">

							<li class="active"><a href="login.htm">LOGIN</a></li> 
							<li style="list-style: none">|</li>
			<li><a href="registerHost.htm">BECOME HOST</a></li> 
			
						</c:if>

					</ul>
					<!-- start profile_details -->
					
				</div>
				<div class="clear"></div>
				
			</div>
		</div>
	</div>
	
	<c:if test="${!empty sessionScope.loginStatus}">
	<font color="red"><p>${sessionScope.loginStatus}</p></font>
	</c:if>
<div class="contact_right">
<div class="contact-form">

<form:form action="login.htm" commandName="person" method="post">
		<div>
			<span><label>USERNAME</label></span> 
			<span><form:input path="userName" size="30" type="text" class="textbox" />
			<font color="red"><form:errors path="userName"/></font>
			</span>
		</div>
		<div>
			<span><label>PASSWORD</label></span> 
			<span><form:password path="password" size="30" class="textbox" />
			<font color="red"><form:errors path="password"/></font>
			</span>
		</div>
		
		<div>
			<span><input type="submit" value="Login"></span>
			<span><a href="register.htm">New user? Create Account</a></span>
			<span><a href="forgotPassword.htm">Forgot password?</a></span>
		</div>
</form:form>
</div>
</div>
		
	
	
</body>
</html>