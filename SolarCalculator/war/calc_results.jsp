<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
 
	<title>Results</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<% String userID = (String) session.getAttribute("user"); %>

	<jsp:useBean id="resultsbean" class="solarcalculator.SimpleResultsBean" scope="session" />
	<% resultsbean.setUserID(userID); %>
	
	<%@ include file="header.jsp" %>
		
	<h1>Results</h1>
			
	<span class="resulttitle">Estimated Time Till ROI :</span>
	<span class="result"><%= resultsbean.ROI() %></span></br>
	<span class="resulttitle">Expected Daily Output :</span>
	<span class="result"><%= resultsbean.dailyOutput() %></span></br>
	<span class="resulttitle">System Cost :</span>
	<span class="result"><%= resultsbean.cost() %></span></br>
	<span class="resulttitle">Savings (1st month) :</span>
	<span class="result"><%= resultsbean.savingsFirstMonth() %></span></br>
	<span class="resulttitle">Savings (Lifespan) :</span>
	<span class="result"><%= resultsbean.savingsLifespan()%></span></br>
	
	<form name="reset_data" action="/resetdata" method="post">
		<input type="submit" value="Reset Data" class="button"/>
	</form>
	
	<%@ include file="footer.jsp" %>
  
</body>
</html>