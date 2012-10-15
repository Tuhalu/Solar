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
			<li>Inverter</li>
			<li>Results</li>
		</ul>
	
	</aside>
	
	<div class="body">
		
		<span class="error">${errors.usage}</span>
		<span class="error">${errors.hourly}</span>
		<span class="error">${errors.sunlight}</span>
		<span class="error">${errors.longitude}</span>
		<span class="error">${errors.latitude}</span>
		
		
		<form name="location" action="/locationinput" method="post">
			<div class="left">
				<label for="usage">Daily Energy Usage (KWh)</label>
				<label for="hourly">Day Time Hourly Usage (KWh)</label>
				<label for="longitude">Longitude</label>
				<label for="latitude">Latitude</label>
				<br /><br />
				<label for="sunlight">Daily hours of Sunlight</label>
			</div>
			<div class="right">
				<input type="text" name="usage" ${param.usage}/>
				<input type="text" name="hourly" value="${param.hourly}" />
				<input type="text" name="longitude" value="${param.longitude}" />
				<input type="text" name="latitude" value="${param.latitude}" />
				<br /><br />
				<input type="text" name="sunlight" value="${param.sunlight}" />
				
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>
	
	</div>
		
	<%@ include file="footer.jsp" %>
  
</body>
</html>