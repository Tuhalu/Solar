<!doctype html>

<html lang="en">

<head>
	<meta charset="utf-8">
	
	<title>Location</title>
	<%@ include file="header.html" %>
</head>

<body>

<div class="wrapper">

	<header>
	<%@ include file="nav_bar_top.html" %>
	</header>
	
	<aside>
	
		<ul>
			<li><a href="">Calculation Type</a></li>
			<li class="current"><a href="">Location</a></li>
			<li><a href="">Panel Configuration</a></li>
			<li><a href="">Results</a></li>
		</ul>
	
	</aside>
	
	<article>
		
		<div class="controlbox">
			<ul>
				<li><a class="left" href=""></a></li>
				<li><a class="center" href=""></a></li>
				<li><a class="right" href=""></a></li>
			</ul>
		</div>
		
		<h1>Calculation Type</h1>
		
		<div class="messagebar">
			Please enter your location information.
		</div>
		
		<form name="location" action="" method="post">
			<div class="left">
				<label for="">Street Address</label>
				<label for="">City/Suburb</label>
				<label for="">State</label>
				<label for="">Postcode</label>
			</div>
			<div class="right">
				<input type="text" name="street" />
				<input type="text" name="city" />
				<input type="text" name="state" />
				<input type="text" name="postcode" />
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>
	
	</article>
	
	<%@ include file="footer.html" %>

</div>

<script src="JS/functions.js"></script>
  
</body>
</html>