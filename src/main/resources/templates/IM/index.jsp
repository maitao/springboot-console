<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="Common/common-header.jsp" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>websocket | index</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="mtcommon/lte/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="mtcommon/lte/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="mtcommon/lte/dist/css/skins/_all-skins.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<jsp:include page="Common/header.jsp" />
		<!-- Full Width Column -->
		<div class="content-wrapper">
			<div class="container">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
						Top Navigation <small>Example 2.0</small>
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
						<li><a href="#">Layout</a></li>
						<li class="active">Top Navigation</li>
					</ol>
				</section>

				<!-- Main content -->
				<section class="content">
					<div class="callout callout-info">
						<h4>Tip!</h4>

						<p id="chat">Add the layout-top-nav class to the body tag to get this
							layout. This feature can also be used with a sidebar! So use this
							class if you want to remove the custom dropdown menus from the
							navbar and use regular links instead.</p>
					</div>
					<div class="callout callout-danger">
						<h4>Warning!</h4>

						<p>The construction of this layout differs from the normal
							one. In other words, the HTML markup of the navbar and the
							content will slightly differ than that of the normal layout.</p>
					</div>
					<div class="box box-default">
						<div class="box-header with-border">
							<h3 class="box-title">Blank Box</h3>
						</div>
						<div class="box-body">The great content goes here</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</section>
				<!-- /.content -->
			</div>
			<!-- /.container -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<div class="container">
				<div class="pull-right hidden-xs">
					<b>Version</b> 1.0.1
				</div>
				<strong>Copyright &copy; 2014-2016 <a
					href="http://almsaeedstudio.com">Almsaeed Studio</a>.
				</strong> All rights reserved.
			</div>
			<!-- /.container -->
		</footer>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.2.3 -->
	<script src="mtcommon/lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="mtcommon/lte/bootstrap/js/bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="mtcommon/lte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="mtcommon/lte/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="mtcommon/lte/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="mtcommon/lte/dist/js/demo.js"></script>
	<script src="mtim/js/index.js"></script>

</body>
</html>
