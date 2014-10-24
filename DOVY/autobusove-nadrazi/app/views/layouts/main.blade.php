<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>{{ $title }} | Autobusové nádraží</title>

		<!-- Bootstrap -->
		<link href="bundle/bootstrap/css/bootstrap.min.css" rel="stylesheet">

		<link href="css/main.css" rel="stylesheet">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="/">Autobusové nádraží</a>
				</div>
				<div class="collapse navbar-collapse navbar-right">
					<ul class="nav navbar-nav">
						<li><a href="/ridici">Řidiči</a></li>
						<li><a href="/autobusy">Autobusy</a></li>
						<li><a href="/spoje">Spoje</a></li>
						<li><a href="/trzba">Tržba</a></li>
						<li><a href="/autobusy">Spotřeba</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="starter-template">
				@yield('content')
			</div>
		</div>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="bundle/jquery/js/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="bundle/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
