<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

							<li><a href="acountInfo.htm">My Account</a></li> 
							<li style="list-style: none">|</li>
			<c:if test="${!empty sessionScope.isHost}">
								<li><a href="newAdvertisement.htm">POST A NEW ADVERTISEMENT</a></li>
								<li><a href="myListings.htm">MY LISTINGS</a></li> 
								<li style="list-style: none">|</li>
			</c:if>
							<li><a href="logout.htm">Logout</a></li> 
							<li style="list-style: none">|</li>
		</c:if>

						<c:if test="${empty sessionScope.loggedInUser}">

							<li><a href="login.htm">LOGIN</a></li> |
			<li><a href="registerHost.htm">BECOME HOST</a></li> 
			
						</c:if>

					
					</ul>
					
				</div>
				<div class="clear"></div>
				
			</div>
		</div>
	</div>
	
    <c:forEach var="booking" items="${bookings}">
    <h2>Apartment: ${booking.bookingID}</h2>
    <table border="1" cellpadding="5" cellspacing="5">
    <tr>
			<td>Date:</td>
			<td>${booking.bookingDate}</td>
		</tr>
		
		<tr>
			<td>Check In Date:</td>
			<td>${booking.checkInDate}</td>
		</tr>
		
		<tr>
			<td>Check Out Date:</td>
			<td>${booking.checkOutDate}</td>
		</tr>
		
		<tr>
			<td>Number of Rooms:</td>
			<td>${booking.advertisementApartment.rooms}</td>
		</tr>
		
		<tr>
			<td>Price Per Night:</td>
			<td>${booking.advertisementApartment.price}</td>
		</tr>

		<tr>
			<td>Number of occupants:</td>
			<td>${booking.occupants}</td>
		</tr>

		<tr>
			<td>Price per extra occupant:</td>
			<td>${booking.advertisementApartment.priceExtraOccupant}</td>
		</tr>

		<tr>
			<td>Furnished:</td>
			<td>${booking.advertisementApartment.furnished}</td>
		</tr>
		
		<tr>
			<td>Street:</td>
			<td>${booking.advertisementApartment.address.street}</td>
		</tr>
		
		<tr>
			<td>City:</td>
			<td>${booking.advertisementApartment.address.city}</td>
		</tr>
		
		<tr>
			<td>State:</td>
			<td>${booking.advertisementApartment.address.state}</td>
		</tr>
		
		<tr>
			<td>Zip:</td>
			<td>${booking.advertisementApartment.address.zip}</td>
		</tr>

    </table>
    <br>
    <br>
    </c:forEach>
    </body>
</html>