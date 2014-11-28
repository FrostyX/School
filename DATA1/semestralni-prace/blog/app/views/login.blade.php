<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="favicon.ico">

		<title>Login | FMRI Register</title>

		<!-- Bootstrap core CSS -->
		<link href="bundle/bootstrap/css/bootstrap.min.css" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="login.css" rel="stylesheet">

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<div id="content">

			@if($message)
				{{ HTML::alert($message) }}
			@endif

			<form method="post" aciton="" class="form-signin" role="form">
				<h2 class="form-signin-heading">DATA1 - Blog</h2>
				<input type="text" name="login" class="form-control" placeholder="Login" value="{{ Input::old('login') }}" required autofocus>
				<input type="password" name="password" class="form-control" placeholder="Password" required>
				<input type="hidden" name="remember" value="1">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</form>
		</div>
	</body>
</html>
