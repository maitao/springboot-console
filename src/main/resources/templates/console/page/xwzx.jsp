<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<!-- Content Header (Page header) -->
<!-- <section class="content-header">
	<h1>
		用户数据 <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">用户管理</li>
	</ol>
</section> -->

<!-- Main content -->
<section class="content">

	<!-- Info boxes -->
	<div class="row">
		<div class="col-md-3 col-sm-6 col-xs-12">
			<div class="info-box">
				<span class="info-box-icon bg-yellow"><i
					class="ion ion-ios-people-outline"></i></span>

				<div class="info-box-content">
					<span class="info-box-text">参加者</span> <span
						class="info-box-number" id="xwzx_a"></span>
				</div>
				<!-- /.info-box-content -->
			</div>
			<!-- /.info-box -->
		</div>
		<!-- /.col -->

		<div class="col-md-3 col-sm-6 col-xs-12">
			<div class="info-box">
				<span class="info-box-icon bg-aqua"><i
					class="ion ion-ios-gear-outline"></i></span>

				<div class="info-box-content">
					<span class="info-box-text">助力者</span> <span
						class="info-box-number" id="xwzx_b"></span>
				</div>
				<!-- /.info-box-content -->
			</div>
			<!-- /.info-box -->
		</div>
		<!-- /.col -->
		<div class="col-md-3 col-sm-6 col-xs-12">
			<div class="info-box">
				<span class="info-box-icon bg-red"><i
					class="fa fa-google-plus"></i></span>

				<div class="info-box-content">
					<span class="info-box-text">当天新增参与者</span> <span
						class="info-box-number" id="xwzx_c"></span>
				</div>
				<!-- /.info-box-content -->
			</div>
			<!-- /.info-box -->
		</div>
		<!-- /.col -->

		<!-- fix for small devices only -->
		<div class="clearfix visible-sm-block"></div>

		<div class="col-md-3 col-sm-6 col-xs-12">
			<div class="info-box">
				<span class="info-box-icon bg-green"><i
					class="ion ion-ios-cart-outline"></i></span>

				<div class="info-box-content">
					<span class="info-box-text">报表</span> <span class="info-box-number">3</span>
				</div>
				<!-- /.info-box-content -->
			</div>
			<!-- /.info-box -->
		</div>
		<!-- /.col -->

	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-md-6">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title xwzx-title"></h3>
					<div class="box-tools">
						<!-- Check all button -->
						<button type="button" class="btn btn-default btn-sm">
							<i class="fa fa-trash-o"></i>
						</button>
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-sm">
								<i class="fa fa-reply"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm"
								title="生成Excel">
								<i class="fa fa-file-excel-o text-green"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm"
								title="生成PDF">
								<i class="fa fa-file-pdf-o text-red"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm" title="打印">
								<i class="fa fa-print text-navy"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm">
								<i class="fa fa-download"></i>
							</button>
						</div>
						<!-- /.btn-group -->
						<button type="button" class="btn btn-default btn-sm" title="刷新列表">
							<i class="fa fa-refresh text-yellow"></i>
						</button>

					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive">
					<div class="overlay">
						<i class="fa fa-refresh fa-spin"></i>
					</div>
					<div>
						<div style="padding-bottom: 10px;">
							<label
								style="font-weight: normal; text-align: left; white-space: nowrap;">显示
								<select name="page_select_0" aria-controls="example1"
								class="form-control input-sm"
								style="display: inline-block; width: auto; vertical-align: middle;"><option
										value="10">10</option>
									<option value="25">25</option>
									<option value="50">50</option>
									<option value="100">100</option></select>
							</label>
							<div class="pull-right">
								<div class="input-group input-group-sm" style="width: 150px;">
									<input type="text" name="table_search"
										class="form-control pull-right" placeholder="搜索日志">

									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>

					<table class="table table-bordered table-hover">
						<thead id="tsHead">

						</thead>
						<tbody id="tsList">

						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
				<!-- /.box-body -->
				<div class="box-footer clearfix">
					<div class="row">
						<div class="col-sm-12">
							<ul
								class="pagination pagination-sm no-margin pull-right pagination_0">
							</ul>
						</div>
					</div>

				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>

		<div class="col-md-6">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title xwzx-agree-title"></h3>
					<div class="box-tools">
						<!-- Check all button -->
						<button type="button" class="btn btn-default btn-sm">
							<i class="fa fa-trash-o"></i>
						</button>
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-sm">
								<i class="fa fa-reply"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm"
								title="生成Excel">
								<i class="fa fa-file-excel-o text-green"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm"
								title="生成PDF">
								<i class="fa fa-file-pdf-o text-red"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm" title="打印">
								<i class="fa fa-print text-navy"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm">
								<i class="fa fa-download"></i>
							</button>
						</div>
						<!-- /.btn-group -->
						<button type="button" class="btn btn-default btn-sm" title="刷新列表">
							<i class="fa fa-refresh text-yellow"></i>
						</button>

					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive">
					<div class="overlay">
						<i class="fa fa-refresh fa-spin"></i>
					</div>
					<div>
						<div style="padding-bottom: 10px;">
							<label
								style="font-weight: normal; text-align: left; white-space: nowrap;">显示
								<select name="page_select_1" aria-controls="example1"
								class="form-control input-sm"
								style="display: inline-block; width: auto; vertical-align: middle;"><option
										value="10">10</option>
									<option value="25">25</option>
									<option value="50">50</option>
									<option value="100">100</option></select>
							</label>
							<div class="pull-right">
								<div class="input-group input-group-sm" style="width: 150px;">
									<input type="text" name="table_search"
										class="form-control pull-right" placeholder="搜索日志">

									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>

					<table class="table table-bordered table-hover">
						<thead id="tsHead_1">

						</thead>
						<tbody id="tsList_1">

						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
				<!-- /.box-body -->
				<div class="box-footer clearfix">
					<div class="row">
						<div class="col-sm-12">
							<ul
								class="pagination pagination-sm no-margin pull-right pagination_1">
							</ul>
						</div>
					</div>

				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>

</section>
<!-- /.content -->
<script src="mtadmin/js/page.js"></script>
<script src="mtadmin/js/xwzx.js"></script>