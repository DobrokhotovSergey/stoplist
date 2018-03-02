<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title>Main Page | Stop List</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="resources/Ionicons/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="resources/dist/css/AdminLTE.min.css">
	<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
	<link rel="stylesheet" href="resources/dist/css/skins/skin-blue.min.css">


	<link href="resources/nprogress/nprogress.css" rel="stylesheet">
	<link href="resources/pnotify/pnotify.css" rel="stylesheet">
	<link href="resources/pnotify/pnotify.buttons.css" rel="stylesheet">
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="resources/dist/js/html5shiv.min.js"></script>
	<script src="resources/dist/js/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" href="/resources/singlePage/assets/css/spapp.css" />

	<!-- Google Font -->
	<link rel="stylesheet" href="resources/dist/css/google-font.css">
	<link rel="stylesheet" href="resources/iCheck/all.css">
	<link rel="stylesheet" href="resources/bootstrap-datepicker/css/bootstrap-datepicker.css">
	<style>
		.btn-search{
			margin-right: 10px;

		}
	</style>
</head>

<div class="modal modal-form fade bs-example-modal-nm" id="update-profile-modal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-nm">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" class="place-progress" id="update-profile">Обновление Профиля</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-5">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body box-profile">
								<img class="img-responsive" id="preload-profile" src="/stoplist/getAvatar/${login}"  onerror="this.src='/resources/project/images/admin.png'" alt="User profile picture">

								<h3 class="profile-username text-center"></h3>
								<p class="text-muted text-center" profile-username-position></p>
							</div>
							<input type="file"  name="file" id="fileLoader"  value="browse" onchange="readURL(this);" />
							<input type="button" id="fileSubmit" value="change image"/>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-md-7">
						<div class="box box-primary" id="profile" class="x_panel">
							<div class="box-header">
								<i class="fa fa-user"></i>
								<h3 class="box-title">Профиль</h3>

							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="x_content">
									<form id="update-profile-form" data-parsley-validate class="form-horizontal form-label-left">
										<%--<input id="update-profile-login" style="display: none" value="${login}">--%>
										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12" for="profile-name">Имя
											</label>
											<div class="col-md-9 col-sm-9 col-xs-12">
												<input type="text" id="profile-name" name="firstname" value ="${firstname}"name="profileName" class="form-control" autocomplete="off">
											</div>
										</div>
										<p>
										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12" for="profile-lastName">Фамилия
											</label>
											<div class="col-md-9 col-sm-9 col-xs-12">
												<input type="text" id="profile-lastName" name="lastname" value ="${lastname}" name="profileLastName" class="form-control" autocomplete="off">
											</div>
										</div>
										<p>
										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12" for="profile-position">Должность
											</label>
											<div class="col-md-9 col-sm-9 col-xs-12">
												<input type="text" id="profile-position" name="position" value ="${position}" name="profileLastName" class="form-control" autocomplete="off">
											</div>
										</div>
										<p>

									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
				<button type="submit" class="btn btn-success" id="update-profile-submit">Сохранить</button>
			</div>

		</div>
	</div>
</div>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<!-- Main Header -->
	<header class="main-header">

		<!-- Logo -->
		<a href="index2.html" class="logo">
			<!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>S</b>L</span>
			<!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>Stop</b>List</span>
		</a>

		<!-- Header Navbar -->
		<nav class="navbar navbar-static-top" role="navigation">
			<!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>
			<!-- Navbar Right Menu -->
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">

					<!-- User Account Menu -->
					<li class="dropdown user user-menu">
						<!-- Menu Toggle Button -->
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<!-- The user image in the navbar-->
							<img src="/stoplist/getAvatar/${login}"  onerror="this.src='/resources/project/images/admin.png'" class="user-image" alt="User Image">
							<!-- hidden-xs hides the username on small devices so only the image appears. -->
							<span class="hidden-xs">${firstname} ${lastname}</span>
						</a>
						<ul class="dropdown-menu">
							<!-- The user image in the menu -->
							<li class="user-header">
								<img src="/stoplist/getAvatar/${login}" onerror="this.src='/resources/project/images/admin.png'"  class="img-circle" alt="User Image">

								<p>
									${firstname} ${lastname} - ${position}
								</p>
							</li>
							<li class="user-footer">
								<div class="pull-left">
									<a  class="btn btn-primary btn-flat" onclick="getProfile();">Профиль</a>
								</div>
								<div class="pull-right">
									<a href="/logout" class="btn btn-primary btn-flat">Выйти</a>
								</div>
							</li>
						</ul>
					</li>

				</ul>
			</div>
		</nav>
	</header>
	<!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">

		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">

			<!-- Sidebar user panel (optional) -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="/stoplist/getAvatar/${login}" onerror="this.src='/resources/project/images/admin.png'"  class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${firstname} ${lastname}</p>
					<!-- Status -->
					<a href="#"><i class="fa fa-circle text-success"></i> Онлайн</a>
				</div>
			</div>


			<!-- Sidebar Menu -->
			<ul class="sidebar-menu" data-widget="tree">
				<li class="header">Главное</li>
				<li class="treeview">
				<li><a href="#tableStopList"><i class="fa fa-list"></i> <span>Единный стоплист</span></a></li>

				<sec:authorize access="hasRole('ROLE_RADMIN') or hasRole('ROLE_SADMIN') or hasRole('ROLE_SADMIN')">
					<li class="header">Управление Пользователями</li>
					<li><a href="#users"><i class="fa fa-users"></i> <span>Пользователи</span></a></li>
				</sec:authorize>
			</ul>
			<!-- /.sidebar-menu -->
		</section>
		<!-- /.sidebar -->
	</aside>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" id="spapp" role="main">
		<section id="tableStopList" class="content container-fluid" data-load="tableStopList"></section>
		<section id="error_404" class="content container-fluid"><h1>Page not found</h1></section>
		<section id="users" class="content container-fluid" data-load="users"></section>

	</div>
	<!-- /.content-wrapper -->

	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="pull-right hidden-xs">

		</div>
		<!-- Default to the left -->

	</footer>

	<!-- Control Sidebar -->
	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/project/js/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link href="resources/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">

<style>
	.dataTables_wrapper .dt-buttons {
		float: left;
	}
	.dataTables_wrapper .dataTables_filter {
		float: right;
		text-align: right;
	}
	.dataTables_wrapper .dataTables_length {
		float: right;
		text-align: right;
		margin: auto;
		width: 50%;
	}

</style>

<script src="resources/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="resources/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<link href="resources/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
<link href="resources/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
<%--<link href="resources/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">--%>
<link href="resources/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
<script src="resources/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
<script src="resources/nprogress/nprogress.js"></script>
<script src="resources/pnotify/pnotify.js"></script>
<script src="resources/pnotify/pnotify.buttons.js"></script>
<script src="resources/chartjs/Chart.js"></script>
<script src="resources/build/js/custom.js"></script>
<script src="resources/project/js/project.js"></script>
<script src="resources/singlePage/assets/js/jquery.spapp.js"></script>
<script src="resources/singlePage/assets/js/custom.js"></script>
<script src="resources/iCheck/icheck.min.js"></script>
<script src="resources/bootstrap-datepicker/bootstrap-datepicker.js"></script>
<script src="resources/bootstrap-datepicker/locales/bootstrap-datepicker.ru.js"></script>
<script>
    function getProfile(){
        $('#update-profile-modal').modal('show');
    };


</script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->

<style>
	.spinner {
		left: 50%;
		margin-left: -4em;
		color: red;
	}
</style>

</body>
</html>