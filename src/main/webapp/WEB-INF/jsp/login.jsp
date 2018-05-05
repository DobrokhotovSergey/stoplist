<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page session="true" %>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Meta, title, CSS, favicons, etc. -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Login </title>

	<!-- Bootstrap -->
	<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
	<!-- Font Awesome -->
	<link href="resources/font-awesome/css/font-awesome.css" rel="stylesheet">
	<!-- NProgress -->
	<link href="resources/nprogress/nprogress.css" rel="stylesheet">
	<!-- Animate.css -->
	<link href="resources/animate/animate.css" rel="stylesheet">

	<!-- Custom Theme Style -->
	<link href="resources/build/css/custom.css" rel="stylesheet">
	<link href="resources/project/css/project.css" rel="stylesheet">
</head>

<body class="login">

<div>
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
	<a class="hiddenanchor" id="signup"></a>
	<a class="hiddenanchor" id="signin"></a>

	<div class="login_wrapper">
		<div class="animate form login_form">
			<section class="login_content">
				<form role="form" action="login" method="POST">
					<h2><div class="glyphicon glyphicon-user"></div></h2>
					<div>
						<input type="text" class="form-control" name="username" placeholder="username" required="" />
					</div>
					<div>
						<input type="password" class="form-control" name="password" placeholder="Password" required="" />
					</div>
					<div>
						<input type="submit" class="btn btn-default btn-block" value="войти в систему">

					</div>

					<div class="clearfix"></div>
					<div class="separator">

						<div class="clearfix"></div>
						<br>

						<div>
							<h1>Stop List</h1>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						   value="${_csrf.token}" />
				</form>
			</section>
		</div>


	</div>
</div>
</body>
</html>
