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


						<li class="active"><a href="home.htm">HOME</a></li> 
						<li style="list-style: none">|</li>
						<li class="active"><a href="apartments.htm">APARTMENTS</a></li> 
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

							<li><a href="login.htm">LOGIN</a></li> 
							<li style="list-style: none">|</li>
			<li><a href="registerHost.htm">BECOME HOST</a></li> 
			
						</c:if>

						<div class="clear"></div>
					</ul>
					
				</div>
				
			</div>
		</div>
	</div>
<div class="contact_right">
<div class="contact-form">
<c:if test="${!empty sessionScope.hostExists}">
<font color="red"><p>${sessionScope.hostExists}</p></font>
</c:if>

<c:if test="${!empty sessionScope.emailExists}">
<font color="red"><p>${sessionScope.emailExists}</p></font>
</c:if>
	<form:form action="registerHost.htm" commandName="host" method="post">
	<div>
			<span><label>FIRST NAME</label></span> 
			<span><form:input path="fName" pattern="[a-zA-Z]*" title="Enter a valid first name. Only alphabets" required = "true" size="30" type="text" class="textbox" />
			<font color="red"><form:errors path="fName"/></font>
			</span>
		</div>
		
		<div>
			<span><label>LAST NAME</label></span> 
			<span><form:input path="lName" pattern="[a-zA-Z]*" title="Enter a valid last name. Only alphabets" required = "true" size="30" type="text" class="textbox" />
			<font color="red"><form:errors path="lName"/></font>
			</span>
		</div>
		
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
			<span><label>EMAIL</label></span> 
			<span><form:input path="email.emailId" size="30" type="email" class="textbox" />
			<font color="red"><form:errors path="email.emailId"/></font>
			</span>
		</div>
		
		<div>
			<span><label>GOVERNMENT ID</label></span> 
			<span><form:input path="govtID" pattern="[0-9]*{8,9}" title="Enter a valid ID." required = "true" size="30" type="text" class="textbox" />
			<font color="red"><form:errors path="govtID"/></font>
			</span>
		</div>
		
		<div>
			<span><label>ID TYPE</label></span> 
			<span><form:select path="idType" onchange="change_country(this.value)" class="frm-field" required="true" readonly="true" >
			<font color="red"><form:errors path="idType"/></font>
			</span>
					<form:option value="SSN">SSN</form:option>
					<form:option value="Passport">Passport</form:option>
			</form:select>
		</div>
		
		<div>
			<span><input type="submit" value="Create Account"></span>
			<span></span>
			<span>Already registered? <a href="login.htm">Login now!!!</a></span>
		</div>
	</form:form>	
</div>
</div>	
	
	
</body>
</html>