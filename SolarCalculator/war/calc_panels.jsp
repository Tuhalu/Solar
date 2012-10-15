<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
 
	<title>Panel Information</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<%@ include file="header.jsp" %>
	
	<h1>Panel Information</h1>
	
	<div class="messagebar">
		Please enter your panel information.
	</div>
	
	<aside>
	
		<ul>
			<li>Calculation Type</li>
			<li>Location</li>
			<li class="current">Panels</li>
			<li>Inverter</li>
			<li>Results</li>
		</ul>
	
	</aside>
	
	<div class="body">
	
		<span class="error">${errors.paneloutput}</span>
		<span class="error">${errors.cost}</span>
		<span class="error">${errors.efficiencyloss}</span>
		<span class="error">${errors.angle}</span>
	
		<form name="panels" action="/panelsinput" method="post">
			<div class="left">
				<label for="paneloutput">System Size (KW)</label>
				<label for="cost">Cost ($)</label>
				<label for="efficiencyloss">Annual Efficiency Loss</label>
				<label for="angle">Panel Facing</label>
				<label for="bracing">Adjust for bracing?</label>
			</div>
			<div class="right">
				<input type="text" name="paneloutput" value="${param.paneloutput}"/>
				<input type="text" name="cost" value="${param.cost}"/>
				<input type="text" name="efficiencyloss" value="${param.efficiencyloss}"/>
				<input type="text" name="angle" value="${param.angle}"/>
				<input type="checkbox" name="bracing" value="yes" value="${param.bracing}"/>
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>

	</div>
		
	<%@ include file="footer.jsp" %>
  
</body>
</html>