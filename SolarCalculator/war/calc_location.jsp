<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
 
	<title>Location Information</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<%@ include file="header.jsp" %>
		
	<h1>Location Information</h1>
	
	<div class="messagebar">
		Please enter your location information.
	</div>
	
	<aside>
	
		<ul>
			<li>Calculation Type</li>
			<li class="current">Location</li>
			<li>Panels</li>
			<li>Panel Configuration</li>
			<li>Inverter</li>
			<li>Electricity Information</li>
			<li>Results</li>
		</ul>
	
	</aside>
	
	<div class="body">
		
		<form name="location" action="/locationinput" method="post">
			<div class="left">
				<label for="street">Longitude</label>
				<label for="city">Latitude</label>
				<label for="usage">Daily Energy Usage (KWh)</label>
				<label for="hourly">Day Time Hourly Usage (KWh)</label>
				<br /><br />
				<label for="sunlight">Daily hours of Sunlight</label>
			</div>
			<div class="right">
				<input type="text" name="longitude" />
				<input type="text" name="latitude" />
				<input type="text" name="usage" />
				<input type="text" name="hourly" />
				<br /><br />
				<input type="text" name="sunlight" />
				<input type="hidden" name="id" value=<%= request.getParameter("id") %> />
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>
	
	</div>
	
	<div class="controlbox">
		<ul>
			<li><a class="left" href="calc_type.jsp"></a></li>
			<li><a class="center" href="#"></a></li>
			<li><a class="right" href="calc_panels.jsp"></a></li>
		</ul>
	</div>
		
	<%@ include file="footer.jsp" %>
  
</body>
</html>