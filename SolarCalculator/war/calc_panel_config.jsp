<!doctype html>

<html lang="en">

<head>
	<meta charset="utf-8">	
	<title>Panel Configuration</title>
	<%@ include file="header.html" %>
</head>

<body>

<div class="wrapper">

	<header>
	<%@ include file="nav_bar_top.html" %>
	</header>
	
	<aside>
	
		<ul>
			<li>Calculation Type</li>
			<li>Location</li>
			<li class="current">Panel Configuration</li>
			<li>Results</li>
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
			Please enter your panel configuration.
		</div>
		
		<form name="panel_config" action="" method="post">
			<div class="left">
				<label for="">Kilowatts</label>
				<label for="">Direction</label>
				<label for="">Slope</label>
				<label for="">Bracing</label>
			</div>
			<div class="right">
				<input type="text" name="kilowatts" />
				<input type="text" name="direction" />
				<input type="text" name="slope" />
				<input type="text" name="bracing" />
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>
	
	</article>
	
	<%@ include file="footer.html" %>

</div>
  
</body>
</html>