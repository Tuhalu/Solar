<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
 
	<title>Inverter Information</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<%@ include file="header.jsp" %>
		
	<h1>Inverter Information</h1>
	
	<div class="messagebar">
		Please enter your inverter information.
	</div>
	
	<aside>
	
		<ul>
			<li>Calculation Type</li>
			<li>Location</li>
			<li>Panels</li>
			<li class="current">Inverter</li>
			<li>Results</li>
		</ul>
	
	</aside>
	
	<div class="body">
	
		<span class="error">${errors.cost}</span>
		<span class="error">${errors.efficiency}</span>
		<span class="error">${errors.efficiencyloss}</span>
	
		<form name="inverter" action="/inverterinput" method="post" >
			<div class="left">
				<label for="cost">Inverter Cost</label>
				<label for="efficiency">Inverter Efficiency</label>
				<label for="efficiencyloss">Annual Efficiency Loss</label>
			</div>
			<div class="right">
				<input type="text" name="cost" ${param.cost} />
				<input type="text" name="efficiency" ${param.efficiency} />
				<input type="text" name="efficiencyloss" ${param.efficiencyloss} />
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>

	</div>
		
	<%@ include file="footer.jsp" %>
  
</body>
</html>